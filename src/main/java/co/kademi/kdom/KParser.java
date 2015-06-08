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
package co.kademi.kdom;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlEngine;

/**
 *
 * @author brad
 */
public class KParser {

    private Tokenizer tokenizer;
    private final JexlEngine jexl;

    public KParser(Tokenizer tokenizer, JexlEngine jexl) {
        this.tokenizer = tokenizer;
        this.jexl = jexl;
    }

    public KParser() {
        tokenizer = new Tokenizer();
        jexl = new JexlEngine();
    }

    public KDocument parse(InputStream in) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        IOUtils.copy(in, bout);
        String s = bout.toString("UTF8");
        final KDocument doc = new KDocument();
        List<String> beginTagTextStack = new ArrayList<>();
        List<List<KDocument.KDomNode>> stack = new ArrayList();

        Tokenizer.TokenHandler handler = new Tokenizer.TokenHandler() {
            List<KDocument.KDomNode> currentChildren;

            @Override
            public void onToken(Tokenizer.TokenType type, String text) {
                System.out.println("Token " + type + " - " + text);
                KDocument.KDomElement newEl;
                String beginTagText;
                switch (type) {
                    case DECLARATION:
                        // ignore for now
                        break;
                    case OPEN_TAG:
                        System.out.println(" - PUSH el " + text);
                        beginTagTextStack.add(0, text);
                        currentChildren = new ArrayList<>();
                        stack.add(0, currentChildren);
                        break;
                    case CLOSE_TAG:
                        text = text.substring(2, text.length() - 1);
                        if (beginTagTextStack.isEmpty()) {
                            throw new RuntimeException("Unbalanced tags: " + text);
                        }
                        beginTagText = beginTagTextStack.remove(0);
                        System.out.println(" - POP el " + beginTagText);
                        beginTagText = beginTagText.substring(1, beginTagText.length() - 1);
                        if (!beginTagText.startsWith(text)) {
                            throw new RuntimeException("Unbalanced tags. Start=" + beginTagText + " finished: " + text);
                        }
                        newEl = createAndAddElement(beginTagText, currentChildren, doc);
                        stack.remove(0);
                        if (!stack.isEmpty()) {
                            currentChildren = stack.get(0);
                            currentChildren.add(newEl);
                        } else {
                            doc.setRoot(newEl);
                        }
                        break;
                    case SELF_CLOSE_TAG:
                        text = text.substring(1, text.length() - 2);
                        newEl = createAndAddElement(text, Collections.EMPTY_LIST, doc);
                        currentChildren.add(newEl);
                        break;
                    case MUSTACHE:
                        String s = text.substring(2, text.length() - 2);
                        Expression expr = jexl.createExpression(s);
                        try {
                            KDocument.KDomExpressionNode n = doc.createExpressionNode(text, expr);
                            currentChildren.add(n);
                        } catch (Exception e) {
                            throw new RuntimeException("Couldnt parse: " + text, e);
                        }

                        break;
                    case MUSTACHE_OPEN:
                        System.out.println(" - PUSH mu " + text);
                        beginTagTextStack.add(0, text);
                        currentChildren = new ArrayList<>();
                        stack.add(0, currentChildren);
                        break;
                    case MUSTACHE_CLOSE:
                        beginTagText = beginTagTextStack.remove(0);
                        System.out.println(" - POP mu " + beginTagText);

                        String startTag = beginTagText.substring(3, beginTagText.length() - 2);
                        int i = startTag.indexOf(" ");
                        String helper = null;
                        String exprText = startTag;
                        if (i > 0) {
                            helper = startTag.substring(0, i);
                            exprText = startTag.substring(i);
                            if (!text.startsWith("{{/" + helper)) {
                                throw new RuntimeException("Unbalanced tags. Start=" + beginTagText + " finished: " + text);
                            }

                        } else {
                            helper = null;
                            exprText = startTag;
                            if (!text.startsWith("{{/" + exprText)) {
                                throw new RuntimeException("Unbalanced tags. Start=" + beginTagText + " finished: " + text);
                            }

                        }

                        try {
                            expr = jexl.createExpression(exprText);
                        } catch (Exception e) {
                            throw new RuntimeException("Couldnt parse: " + exprText, e);
                        }
                        KDocument.KDomExpressionSection section;
                        if (helper != null) {
                            section = doc.createHelperExpressionSection(helper, exprText, expr, currentChildren);
                        } else {
                            section = doc.createExpressionSection(startTag, expr, currentChildren);
                        }
                        stack.remove(0);
                        if( stack.isEmpty()) {
                            throw new RuntimeException("Unclosed tag: " + text);
                        }
                        currentChildren = stack.get(0);
                        currentChildren.add(section);
                        break;

                    case TEXT:
                        KDocument.KDomTextNode tn = doc.createTextNode(text);
                        currentChildren.add(tn);
                        break;

                }

            }
        };

        tokenizer.tokenize(s, handler);
        if (doc.getRoot() == null) {
            throw new RuntimeException("Did not get a root element");
        }
        return doc;
    }

    private KDocument.KDomElement createAndAddElement(String beginTagText, List<KDocument.KDomNode> currentChildren, final KDocument doc) {
        int firstSpace = beginTagText.indexOf(" ");
        String tag;
        Map<String, String> atts;
        if (firstSpace > 0) {
            tag = beginTagText.substring(0, firstSpace);
            String sAtts = beginTagText.substring(firstSpace + 1);
            atts = parseAttributes(sAtts);
        } else {
            tag = beginTagText;
            atts = Collections.EMPTY_MAP;
        }
        KDocument.KDomElement el = doc.createElement(tag, atts, currentChildren);
        return el;
    }

    public Map<String, String> parseAttributes(String s) {
        int pos = 0;
        Map<String, String> atts = new HashMap<>();
        boolean done = false;
        while (pos < s.length()) {
            int eqPos = s.indexOf("=", pos);
            if (eqPos <= 0) {
                break;
            }
            String attName = s.substring(pos, eqPos).trim();
            // find first quote mark, then second
            int quoteFirstPos = findNextQuote(s, eqPos);
            if (quoteFirstPos <= 0) {
                break;
            }
            boolean useDouble = s.charAt(quoteFirstPos) == '"';
            int quoteSecondPos;
            if (useDouble) {
                quoteSecondPos = s.indexOf("\"", quoteFirstPos + 1);
            } else {
                quoteSecondPos = s.indexOf("'", quoteFirstPos + 1);
            }
            if (quoteSecondPos <= 0) {
                break;
            }

            String attVal = s.substring(quoteFirstPos + 1, quoteSecondPos);
            atts.put(attName, attVal);
            pos = quoteSecondPos + 1;
        }
        return atts;
    }

    public int findNextQuote(String s, int startFrom) {
        int pos = startFrom;
        while (pos < s.length()) {
            char c = s.charAt(pos);
            if (c == '\'') {
                return pos;
            } else if (c == '"') {
                return pos;
            }
            pos++;
        }
        return -1;
    }

    public Tokenizer getTokenizer() {
        return tokenizer;
    }

    public JexlEngine getJexl() {
        return jexl;
    }
}
