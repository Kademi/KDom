/*
 * Copyright 2015 McEvoy Software Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package co.kademi.kdom.template;

import co.kademi.kdom.KDocument;
import co.kademi.kdom.KDocument.KDomContainerNode;
import co.kademi.kdom.KDocument.KDomElement;
import co.kademi.kdom.KDocument.KDomExpressionNode;
import co.kademi.kdom.KDocument.KDomExpressionSection;
import co.kademi.kdom.KDocument.KDomNode;
import co.kademi.kdom.KDocument.KDomTextNode;
import co.kademi.kdom.KDocument.KQuery;
import co.kademi.kdom.MultiJexlContext;
import co.kademi.kdom.NestableJexlContext;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.jexl2.ObjectContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Templating process 1. Merge templates to get a new document 2. Evaluate
 * template expressions, foreach loops, etc to get a new document 3. Evaluate
 * DOM scripts, to manipulate the doc
 *
 * @author brad
 */
public class KDomTemplater {

    private static final Logger log = LoggerFactory.getLogger(KDomTemplater.class);

    private final TemplateLocator templateLocator;
    private final JexlEngine jexl;

    public KDomTemplater( JexlEngine jexl) {
        this.templateLocator = null;
        this.jexl = jexl;
    }

    public KDomTemplater(TemplateLocator templateLocator, JexlEngine jexl) {
        this.templateLocator = templateLocator;
        this.jexl = jexl;
    }

    public void render(KDocument doc, OutputStream out, Map vars) {
        if( doc.getRoot() == null ) {
            throw new NullPointerException("Document has no root element");
        }
        KDocument newDoc = new KDocument();
        MapContext c = new MapContext(vars);
        List<TemplateCall> contextStack = new ArrayList<>();
        c.set("doc", doc);
        c.set("topDoc", doc);
        KDomElement merged = merge(doc.getRoot(), newDoc, c, contextStack);
        newDoc.setRoot(merged);
        // at this point we should look for server side scripts and run them

        newDoc.write(out);
    }

    private KDomElement merge(KDocument.KDomElement el, KDocument newDoc, JexlContext vars, List<TemplateCall> contextStack) {
//        String templatePath = el.getAttributes().get("template");
//        if (StringUtils.isNotBlank(templatePath)) {
//            KDocument templateDoc = templateLocator.locate(templatePath);
//            if (templateDoc == null) {
//                throw new RuntimeException("Couldnt find " + templatePath);
//            }
//            Map<String, Object> newVars = new HashMap<>();
//            newVars.put("doc", templateDoc);
//            JexlContext docContext = new MapContext(newVars);
//            vars = new NestableJexlContext(vars, docContext);
//            el = templateDoc.getRoot();
//            System.out.println("using template: " + el);
//        }

        List<KDocument.KDomNode> nodes = new ArrayList<>();
        mergeElementContent(el, el, newDoc, vars, nodes, contextStack);
        return newDoc.createElement(el.getName(), el.getAttributes(), nodes);
    }

    private void mergeElementContent(KDomElement el, KDomContainerNode containerNode, KDocument newDoc, JexlContext vars, List<KDomNode> nodes, List<TemplateCall> contextStack) {
        containerNode.getNodes().forEach((KDocument.KDomNode t) -> {
            mergeNode(t, newDoc, vars, nodes, el, contextStack);
        });
    }

    private void mergeNode(KDomNode sourceNode, KDocument newDoc, JexlContext vars, List<KDomNode> nodesToAddTo, KDomElement parentElement, List<TemplateCall> contextStack) {
        if (sourceNode instanceof KDomElement) {
            KDomElement te = (KDomElement) sourceNode;
            te = merge(te, newDoc, vars, contextStack);
            nodesToAddTo.add(te);
        } else if (sourceNode instanceof KDocument.KDomExpressionNode) {
            KDomExpressionNode en = (KDomExpressionNode) sourceNode;
            System.out.println("Evaluate expre: " + en.getText() + " with context parent: " + vars.get("parent"));
            Expression expr = en.getExpr();
            Object exprResult = expr.evaluate(vars);
            addNodesFromExpression(exprResult, newDoc, vars, nodesToAddTo, parentElement, contextStack);

        } else if (sourceNode instanceof KDocument.KDomExpressionSection) {
            KDomExpressionSection section = (KDomExpressionSection) sourceNode;
            Expression expr = section.getExpr();

            if (section.getHelperName() != null) {

                Object exprResult;
                switch (section.getHelperName()) {
                    case "template":
                        // Evaluate the expression to get the template path (probably a const)
                        // Needs to push the current context onto the template context stack
                        System.out.println("execute template: " + section.getText());
                        exprResult = expr.evaluate(vars);
                        String templatePath = exprResult + "";
                        KDocument templateDoc = templateLocator.locate(templatePath);

                        MultiJexlContext c = MultiJexlContext.build(vars, new ObjectContext(jexl, section));
                        contextStack.add(0, new TemplateCall( c, section)); // push. include section as root object for use with #merge find('..')

                        Map<String, Object> newVars = new HashMap<>();
                        newVars.put("doc", templateDoc);
                        JexlContext docContext = new MapContext(newVars);
                        vars = new NestableJexlContext(vars, docContext);
                        vars = MultiJexlContext.build(vars, new ObjectContext(jexl, section));
                        KDomElement templateRootEl = templateDoc.getRoot();
                        System.out.println("using template: " + templateRootEl);
                        mergeElementContent(parentElement, templateRootEl, newDoc, vars, nodesToAddTo, contextStack);

                        break;
                    case "merge":
                        // this is a template calling back to the page that called it. Needs to pop the execution context, and evaluate
                        TemplateCall templateCall = contextStack.remove(0); // pop
                        System.out.println("merge:" + section.getText());
                        JexlContext templateCallContext = templateCall.getContext();

                        System.out.println("-----");
                        Object o = jexl.createExpression("this.find('body')").evaluate(templateCallContext);
                        System.out.println("------");

                        exprResult = expr.evaluate(templateCallContext);
                        addNodesFromExpression(exprResult, newDoc, vars, nodesToAddTo, parentElement, contextStack);

                        break;
                }
            } else {
                Object exprResult = expr.evaluate(vars);

                JexlContext context = vars;
                if (exprResult instanceof Iterable) {
                    // The expression returned a list, so we evaluate the child nodes for each item in the list
                    Iterable it = (Iterable) exprResult;
                    it.forEach((Object listItem) -> {
                        JexlContext c = new ObjectContext(jexl, listItem);
                        NestableJexlContext jc = new NestableJexlContext(context, c);
                        mergeElementContent(parentElement, section, newDoc, jc, nodesToAddTo, contextStack);
                    });
                } else {
                    // not iterable, so use as an if condition, check if value is truthy
                    boolean b = toTruthy(exprResult);
                    if (b) {
                        JexlContext c = new ObjectContext(jexl, exprResult);
                        NestableJexlContext jc = new NestableJexlContext(vars, c);
                        mergeElementContent(parentElement, section, newDoc, jc, nodesToAddTo, contextStack);
                    }
                }
            }
        } else if (sourceNode instanceof KDocument.KDomTextNode) {
            KDomTextNode tt = (KDomTextNode) sourceNode;
            KDocument.KDomTextNode n = newDoc.createTextNode(tt.getText());
            nodesToAddTo.add(n);
            System.out.println("added node: " + n);
        }
    }

    private void addNodesFromExpression(Object o, KDocument newDoc, JexlContext vars, List<KDomNode> nodes, KDomElement parentElement, List<TemplateCall> contextStack) {
        if (o instanceof KDocument.KDomNode) {
            KDomNode n = (KDomNode) o;
            mergeNode(n, newDoc, vars, nodes, parentElement, contextStack);
        } else if (o instanceof KDocument.KQuery) {
            KQuery q = (KQuery) o;
            for (KDomElement n : q) {
                mergeNode(n, newDoc, vars, nodes, parentElement, contextStack);
            }
        } else if (o instanceof List) {
            List l = (List) o;
            for (Object item : l) {
                addNodesFromExpression(item, newDoc, vars, nodes, parentElement, contextStack);
            }

        } else if (o != null) {
            String s = o.toString();
            KDocument.KDomTextNode n = newDoc.createTextNode(s);
            nodes.add(n);
        }

    }

    private boolean toTruthy(Object o) {
        if (o == null) {
            return false;
        } else if (o instanceof Boolean) {
            return (Boolean) o;
        } else if (o instanceof String) {
            String s = (String) o;
            return s.equalsIgnoreCase("true");
        } else if (o instanceof Integer) {
            Integer i = (Integer) o;
            return i != 0;
        }
        return true; // not null
    }


    public class TemplateCall {

        private JexlContext context;
        private KDomExpressionSection section;

        public TemplateCall(JexlContext context, KDomExpressionSection section) {
            this.context = context;
            this.section = section;
        }

        public JexlContext getContext() {
            return context;
        }

        public KDomExpressionSection getSection() {
            return section;
        }

    }
}
