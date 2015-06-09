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

import co.kademi.kdom.KDocument.KDomTextNode;
import co.kademi.kdom.template.KDomTemplater;
import co.kademi.kdom.template.TemplateLocator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.ObjectContext;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brad
 */
public class KParserTest {

    public KParserTest() {
    }

    //@Test
    public void test() {
        JexlEngine jexl = new JexlEngine();
        Customer c1 = new Customer("brad", "mac");
        Customer c2 = new Customer("joe", "blogs");

        JexlContext childContext = new ObjectContext(jexl, c1);
        ObjectContext parentContext = new ObjectContext(jexl, c2);
        JexlContext jc = new NestableJexlContext(parentContext, childContext);

        Expression expr = jexl.createExpression("firstName");
        Object o = expr.evaluate(jc);
        System.out.println("result1: " + o);

        expr = jexl.createExpression("parent.firstName");
        o = expr.evaluate(jc);
        System.out.println("result2: " + o);
    }


    @Test
    public void test1() throws IOException {
        KParser k = new KParser();
        String html = "<html>"
                + "<title>ABC XYZ</title>"
                + "<body>"
                + "A,B C 123&amp; - !"
                + "</body>"
                + "</html>";
        ByteArrayInputStream in = new ByteArrayInputStream(html.getBytes());
        KDocument page = k.parse(in);
        System.out.println("page: " + page);
        KDocument.KDomElement elTitle = (KDocument.KDomElement) page.getRoot().getNodes().get(0);
        System.out.println(elTitle);
        KDomTextNode t = (KDomTextNode) elTitle.getNodes().get(0);
        assertEquals("ABC XYZ", t.getText());
    }


    //@Test
    public void testSomeMethod() throws IOException {
        KParser k = new KParser();
        String html = "<!DOCTYPE html>\n"
                + "<html>"
                + "{{#template 'templates/page'}}"
                + "<title>XXX</title>"
                + "<body class='main'>"
                + "{{p.name}}BBB"
                + "<ul>"
                + "{{#parent.customers}}"
                + "<li>"
                + "<h1>{{firstName}} {{surName}}</h1>"
                + "</li>"
                + "{{#addresses}}"
                + "<li>"
                + "<b>{{parent.firstName}} lives at {{line1}} {{city}}</b>"
                + "</li>"
                + "{{/addresses}}"
                + "{{/parent.customers}}"
                + "</ul>"
                + "<br/>AAA\n"
                + "</body>"
                + "{{/template}}"
                + "</html>";
        ByteArrayInputStream in = new ByteArrayInputStream(html.getBytes());
        KDocument page = k.parse(in);
        assertNotNull(page.getRoot());

        String templateHtml = "<html>"
                + "<head>"
                + "<title>{{parent.doc.find('title').content}}</title>"
                + "</head>"
                + "<body>\n"
                + "<h1>{{parent.doc.find('title').content}}</h1>"
                + "<div>"
                + "{{#merge this.find('body').content }}{{/merge}}\n" // + make self closing "{{#/merge find('body')}}\n"
                + "</div>"
                + "</body>"
                + "</html>";
        KDocument template = k.parse(new ByteArrayInputStream(templateHtml.getBytes()));
        assertNotNull(template.getRoot());

        TemplateLocator templateLocator = new TemplateLocator() {

            @Override
            public KDocument locate(String path) {
                return template;
            }
        };

        KDomTemplater t = new KDomTemplater(templateLocator, k.getJexl());

        Map vars = new HashMap();
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("brad", "mac"));
        customers.add(new Customer("Joe", "Bloggs"));
        vars.put("customers", customers);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        t.render(page, out, vars);
        System.out.println("---------- FINAL RESULT -----------");
        System.out.println(out);

    }

    //@Test
    public void testParseAtts() throws IOException {
        KParser k = new KParser(new RegexTokenizer(), new JexlEngine());
        String html = "class='body other' id=\"yayme\"";
        Map<String, String> atts = k.parseAttributes(html);
        assertEquals(2, atts.size());
        assertTrue(atts.containsKey("class"));
        assertTrue(atts.containsKey("id"));
        assertEquals("body other", atts.get("class"));
        assertEquals("yayme", atts.get("id"));
    }


}
