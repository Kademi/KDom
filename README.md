# KDom
A template engine with Handlebars.js style syntax and a DOM-like structure. Allows jquery paradigm in templates.

Usage:

        KParser k = new KParser();

        String html = "<!DOCTYPE html>\n"
                + "<html>"
                + "<title>XXX</title>"
                + "<body class='main'>"
                + "{{p.name}}BBB"
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
        customers.add(new Customer("brad", "mac")); // customer gets created with 2 Addresses
        customers.add(new Customer("Joe", "Bloggs"));
        vars.put("customers", customers);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        t.render(page, out, vars);

Want to use nested templates?

First implement your own template locator:
    TemplateLocator templateLocator = new TemplateLocator() {

        @Override
        public KDocument locate(String path) {
            return template;
        }
    };

Specify a template in the above by changing like this:
    String html = "<!DOCTYPE html>\n"
            + "<html>"
            + "{{#template 'templates/page'}}"
            + "<title>XXX</title>"

    KDomTemplater t = new KDomTemplater(templateLocator, k.getJexl()); // re-use jexl from the parser

    Map vars = new HashMap();
    List<Customer> customers = new ArrayList<>();
    customers.add(new Customer("brad", "mac"));
    customers.add(new Customer("Joe", "Bloggs"));
    vars.put("customers", customers);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    t.render(page, out, vars);


todo:
http://stackoverflow.com/questions/6551073/how-do-you-create-a-secure-jexl-scripting-sandbox?rq=1

and maybe this
http://codeutopia.net/blog/2009/01/02/sandboxing-rhino-in-java/