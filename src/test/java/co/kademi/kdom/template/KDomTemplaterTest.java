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

import co.kademi.kdom.Customer;
import co.kademi.kdom.KDocument;
import co.kademi.kdom.KParser;
import co.kademi.kdom.KParserTest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brad
 */
public class KDomTemplaterTest {

    public KDomTemplaterTest() {
    }

    @Test
    public void testSomeMethod() throws IOException {
        KParser k = new KParser();

        String html = "<!DOCTYPE html>\n"
                + "<html>"
                + "<title>XXX YYY ZZZ</title>"
                + "<body class='main'>"
                + "{{p.name}}B B B"
                + "<ul>"
                + "{{#customers}}"
                + "<li>"
                + "<h1>{{firstName}} {{surName}}</h1>"
                + "</li>"
                + "{{#addresses}}"
                + "<li>"
                + "<b>{{parent.firstName}} lives at {{line1}} {{city}}</b>"
                + "</li>"
                + "{{/addresses}}"
                + "{{/customers}}"
                + "</ul>"
                + "<br/>AAA\n"
                + "</body>"
                + "</html>";
        ByteArrayInputStream in = new ByteArrayInputStream(html.getBytes());
        KDocument page = k.parse(in);
        assertNotNull(page.getRoot());

        KDomTemplater t = new KDomTemplater( k.getJexl());
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

}
