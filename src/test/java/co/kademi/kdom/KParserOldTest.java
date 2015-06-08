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

import co.kademi.kdom.template.KDomTemplater;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.jexl2.JexlEngine;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brad
 */
public class KParserOldTest {

    public KParserOldTest() {
    }

    @Test
    public void testRender() throws IOException {
        String pageHtml = "<html template='templates/page'>"
                + "<head><title>test title</title></head>"
                + "<body>"
                + "<p class='hello-world'>hello\n world</p> aaa <br/> bbb"
                + "<img src='robot.png' />"
                + "</body>"
                + "</html>";

//        String templateHtml = "<html><head><title><div parameter='title'></title></head><body>\n"
//                + "<h1><div parameter='title'></div></h1>"
//                + "<div parameter='body'></div></html>";
        String templateHtml = "<html><head><title>{{caller.find('title').content}}</title></head><body>\n"
                + "<h1>{{caller.find('title').content}}</h1>"
                + "{{caller.find('body').content}}\n"
                + "<ul foreach='customers' var='cust'>\n"
                + "{{#customers}}\n"
                + "<li>{{firstName}} {{surName}}</li>\n"
                + "{{/customers}}\n"
                + "</ul>\n"
                + "</body>"
                + "</html>";
//        String templateHtml = "<html><head><title>{{caller.find('title').content}}</title></head><body>\n"
//                + "<h1>xxx</h1>"
//                + "yyy\n"
//                + "</html>";
        JexlEngine jexl = new JexlEngine();
        //KParser p = new KParser(jexl);
        KParser k = new KParser(new Tokenizer(), new JexlEngine());
        KDocument page = k.parse(new ByteArrayInputStream(pageHtml.getBytes()));
        assertNotNull(page.getRoot());
        KDocument template = k.parse(new ByteArrayInputStream(templateHtml.getBytes()));
        assertNotNull(template.getRoot());
        //System.out.println("page = " + page.getText());
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxx");
        System.out.println("template = " + template.getText());
        List<KDocument.KDomElement> list = page.getElementsByClassName("hello-world");
        System.out.println("found " + list.size());
        System.out.println("---");
        KDocument.KQuery titlElements = page.getRoot().find("title");
        assertEquals(1, titlElements.size());
        System.out.println("---------------------");
        KDomTemplater t = new KDomTemplater((String path) -> template, jexl);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Map vars = new HashMap();
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("brad", "mac"));
        customers.add(new Customer("Joe", "Bloggs"));
        vars.put("customers", customers);
        t.render(page, out, vars);
        System.out.println("---------- FINAL RESULT -----------");
        System.out.println(out);
    }

    public class Customer {
        private String firstName;
        private String surName;

        public Customer(String firstName, String surName) {
            this.firstName = firstName;
            this.surName = surName;
        }


        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getSurName() {
            return surName;
        }

        public void setSurName(String surName) {
            this.surName = surName;
        }


    }

}
