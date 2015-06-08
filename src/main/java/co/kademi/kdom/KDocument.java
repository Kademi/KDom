package co.kademi.kdom;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author brad
 */
public class KDocument {

    private KDomElement root;
    private final Map<String, KDomElement> mapOfElementsById = new HashMap<>();
    private final Map<String, List<KDomElement>> mapOfElementsByClass = new HashMap<>();

    public KDocument() {
    }

    public KQuery find(String selector) {
        if (root == null) {
            throw new RuntimeException("Root element is null");
        }
        return new KQuery(root, selector);
    }

    public KDomElement getElementById(String id) {
        return mapOfElementsById.get(id);
    }

    public List<KDomElement> getElementsByClassName(String c) {
        return mapOfElementsByClass.get(c);
    }

    public KDomElement getRoot() {
        return root;
    }

    public void setRoot(KDomElement root) {
        this.root = root;
        scan();
    }

    public void scan() {
        mapOfElementsByClass.clear();
        mapOfElementsById.clear();
        scan(root);
    }

    private void scan(KDomElement el) {
        String id = el.getAttributes().get("id");
        if (StringUtils.isNotBlank(id)) {
            mapOfElementsById.put(id, el);
        }
        String classes = el.getAttributes().get("class");
        if (StringUtils.isNotBlank(classes)) {
            for (String s : classes.split(" ")) {
                s = s.trim();
                if (StringUtils.isNotBlank(s)) {
                    List<KDomElement> list = mapOfElementsByClass.get(s);
                    if (list == null) {
                        list = new ArrayList<>();
                        mapOfElementsByClass.put(s, list);
                    }
                    list.add(el);
                }
            }
        }
        el.getNodes().stream().forEach((KDomNode t) -> {
            if (t instanceof KDomElement) {
                KDomElement tt = (KDomElement) t;
                scan(tt);
            }
        });
    }

    public String getText() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        write(out);
        return out.toString();
    }

    public void write(OutputStream out) {
        PrintWriter pw = new PrintWriter(out);
        root.write(pw);
        pw.flush();
    }

    public KDomElement createElement(String name, Map<String, String> atts, List<KDomNode> nodes) {
        return new KDomElement(this, name, atts, nodes, null, null);
    }

    public KDomElement creaateElement(String name, Map<String, String> atts, List<KDomNode> nodes, Expression foreachExpr, String loopVarName) {
        return new KDomElement(this, name, atts, nodes, foreachExpr, loopVarName);
    }

    public KDomTextNode createTextNode(String text) {
        return new KDomTextNode(this, text);
    }

    public KDomExpressionNode createExpressionNode(String exprText, Expression expr) {
        return new KDomExpressionNode(this, expr, exprText);
    }

    public KDomExpressionSection createHelperExpressionSection(String helper, String exprText, Expression expr, List<KDomNode> nodes) {
        return new KDomExpressionSection(this, helper, expr, exprText, nodes);
    }

    public KDomExpressionSection createExpressionSection(String exprText, Expression expr, List<KDomNode> nodes) {
        return new KDomExpressionSection(this, null, expr, exprText, nodes);
    }

    public static abstract class KDomNode implements KDomAcceptor {

        private KDocument doc;

        abstract void write(PrintWriter pw);

        public KDomNode(KDocument doc) {
            this.doc = doc;
        }

        public KDocument getDocument() {
            return doc;
        }

    }

    public class KDomTextNode extends KDomNode {

        private String text;

        private KDomTextNode(KDocument doc, String text) {
            super(doc);
            this.text = text;
        }

        public String getText() {
            return text;
        }

        @Override
        public void write(PrintWriter pw) {
            pw.append(text);
        }

        @Override
        public void accept(KDomVisitor v) {
            v.visit(this);
        }

        @Override
        public String toString() {
            return text;
        }

    }

    /**
     * A single expression, without any child elements
     *
     */
    public class KDomExpressionNode extends KDomNode {

        private Expression expr;
        private String text;

        private KDomExpressionNode(KDocument doc, Expression expr, String expression) {
            super(doc);
            this.expr = expr;
            this.text = expression;
        }

        public String getText() {
            return text;
        }

        public Expression getExpr() {
            return expr;
        }

        @Override
        public void write(PrintWriter pw) {
            pw.append(text);
        }

        @Override
        public void accept(KDomVisitor v) {
            v.visit(this);
        }
    }

    public interface KDomContainerNode extends KDomAcceptor {

        List<KDomNode> getNodes();

    }

    public class KDomExpressionSection extends KDomNode implements KDomContainerNode {

        private Expression expr;
        private String text;
        private List<KDomNode> nodes;
        private String helperName;

        private KDomExpressionSection(KDocument doc, String helperName, Expression expr, String expression, List<KDomNode> nodes) {
            super(doc);
            this.expr = expr;
            this.text = expression;
            this.nodes = nodes;
            this.helperName = helperName;
        }

        public KQuery find(String selector) {
            return new KQuery(this, selector);
        }

        public String getHelperName() {
            return helperName;
        }

        public String getText() {
            return text;
        }

        public Expression getExpr() {
            return expr;
        }

        @Override
        public void write(PrintWriter pw) {
            pw.append(text);
        }

        @Override
        public void accept(KDomVisitor v) {
            v.visit(this);
        }

        @Override
        public List<KDomNode> getNodes() {
            return nodes;
        }

        public KDomExpressionSection getThis() {
            return this;
        }
    }

    public class KDomElement extends KDomNode implements KDomContainerNode {

        private String name;
        private Map<String, String> attributes;
        private List<KDomNode> nodes;
        private Expression foreachExpr;
        private String loopVarName;

        private KDomElement(KDocument doc, String name, Map<String, String> attributes, List<KDomNode> nodes, Expression foreachExpr, String loopVarName) {
            super(doc);
            this.name = name;
            this.attributes = attributes;
            this.nodes = nodes;
            this.foreachExpr = foreachExpr;
            this.loopVarName = loopVarName;
        }

        public String getName() {
            return name;
        }

        public Map<String, String> getAttributes() {
            return attributes;
        }

        @Override
        public List<KDomNode> getNodes() {
            return nodes;
        }

        @Override
        void write(PrintWriter pw) {
            pw.append("<").append(name);
            attributes.forEach((String t, String u) -> {
                pw.append(" ").append(t).append("=").append("\"").append(u).append("\"");
            });
            pw.append(">");

            nodes.forEach((KDomNode t) -> {
                t.write(pw);
            });

            pw.append("</").append(name).append(">\n");
        }

        @Override
        public String toString() {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintWriter pw = new PrintWriter(out);
            write(pw);
            pw.flush();
            return out.toString();
        }

        public KQuery find(String selector) {
            return new KQuery(this, selector);
        }

        @Override
        public void accept(KDomVisitor v) {
            v.visit(this);
        }

        public void walk(KDomVisitor v) {
            this.accept(v);
            this.getNodes().stream().forEach((KDomNode t) -> {
                if (t instanceof KDomElement) {
                    KDomElement e = (KDomElement) t;
                    e.walk(v);
                } else {
                    t.accept(v);
                }
            });

        }

        private boolean matches(String selector) {
            // TODO, simple hacky implementation
            if (selector.startsWith(".")) {
                String clas = selector.substring(1);
                String s = getAttributes().get("class");
                return s != null && s.contains(clas);
            } else if (selector.startsWith("#")) {
                String id = selector.substring(1);
                return id.equals(getAttributes().get("id"));
            } else {
                return getName().equals(selector);
            }
        }

        public Expression getForeachExpr() {
            return foreachExpr;
        }

        public String getLoopVarName() {
            return loopVarName;
        }
    }

    public class KQuery extends ArrayList<KDomElement> {

        public KQuery() {

        }

        public KQuery(KDomNode source, String selector) {
            final KDomVisitor v = new KDomVisitor() {

                @Override
                public void visit(KDomElement el) {
                    if (el.matches(selector)) {
                        KQuery.this.add(el);
                    }
                }

                @Override
                public void visit(KDomTextNode n) {

                }

                @Override
                public void visit(KDomExpressionNode el) {

                }

                @Override
                public void visit(KDomExpressionSection n) {
                }

            };
            NodeWalker w = new NodeWalker();
            w.walk(source, v);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            this.stream().forEach((KDomElement t) -> {
                sb.append(t.toString()).append(",");
            });
            sb.append("]");
            return sb.toString();
        }

        public List<KDomNode> getContent() {
            List<KDomNode> q = new ArrayList<>();
            // add nodes of current elements
            this.stream().forEach((KDomElement t) -> {
                q.addAll(t.nodes);
            });
            return q;
        }

    }

    public interface KDomVisitor {

        void visit(KDomElement el);

        void visit(KDomTextNode n);

        void visit(KDomExpressionNode n);

        void visit(KDomExpressionSection n);
    }

    public interface KDomAcceptor {

        void accept(KDomVisitor v);
    }

    public class NodeWalker {

        public void walk(KDomNode visitNode, KDomVisitor v) {
            visitNode.accept(v);
            if (visitNode instanceof KDomContainerNode) {
                KDomContainerNode container = (KDomContainerNode) visitNode;
                for (KDomNode n : container.getNodes()) {
                    walk(n,v);
                }
            }
        }
    }
}
