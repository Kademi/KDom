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

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brad
 */
public class TokenizerTest {

    public TokenizerTest() {
    }

    //@Test
//    public void testTokeniseMath() {
//        Tokenizer tokenizer = new Tokenizer();
//        tokenizer.add("sin|cos|exp|ln|sqrt", 1);
//        tokenizer.add("\\(", 2);
//        tokenizer.add("\\)", 3);
//        tokenizer.add("\\+|-", 4);
//        tokenizer.add("\\*|/", 5);
//        tokenizer.add("[0-9]+", 6);
//        tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 7);
//
//        tokenizer.tokenize(" sin(x) * (1 - var_12) ");
//
//        for (Tokenizer.Token tok : tokenizer.getTokens()) {
//            System.out.println("" + tok.token + " " + tok.sequence);
//        }
//
//    }

    @Test
    public void testTokeniseMustacheHtml() {
        Tokenizer tokenizer = new Tokenizer();
        Tokenizer.TokenHandler handler = new Tokenizer.TokenHandler() {

            @Override
            public void onToken(Tokenizer.TokenType type, String text) {
                System.out.println("" + type + " " + text);
            }
        };

        tokenizer.tokenize("<!DOCTYPE html>\n<html><title>XXX</title><body class='main'>{{p.name}}BBB{{#person}}XX{{/person}}<br/>AAA\n</body></html>", handler);

    }

    //@Test
    public void testTokeniseMustacheHtml2() {
        Tokenizer tokenizer = new Tokenizer();
//        tokenizer.add("<![a-zA-Z][a-zA-Z0-9_]*>", 0);
//        tokenizer.add("<[a-zA-Z][a-zA-Z0-9_]*>", 1);
//        tokenizer.add("</[a-zA-Z][a-zA-Z0-9_]*>", 2);
//        tokenizer.add("<[a-zA-Z][a-zA-Z0-9_]*/>", 3);
//        tokenizer.add("\\{\\{[a-zA-Z][a-zA-Z0-9_]*\\}\\}", 4);
//        tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 7);


        String s = "<!DOCTYPE html>\n" +
"<!--[if IE 7]>\n" +
"<html class=\"ie ie7\" lang=\"en-US\">\n" +
"<![endif]-->\n" +
"<!--[if IE 8]>\n" +
"<html class=\"ie ie8\" lang=\"en-US\">\n" +
"<![endif]-->\n" +
"<!--[if !(IE 7) | !(IE 8)  ]><!-->\n" +
"<html lang=\"en-US\">\n" +
"<!--<![endif]-->\n" +
"<head>\n" +
"  <meta charset=\"UTF-8\" /> \n" +
"  <meta name=\"viewport\" content=\"width=device-width\" />  \n" +
"  \n" +
"<title>Writing a Parser in Java: The Tokenizer | Cogito Learning</title>\n" +
"<style type='text/css'></style><style type='text/css' media='screen' >.socialwrap li.icon_text a img, .socialwrap li.iconOnly a img, .followwrap li.icon_text a img, .followwrap li.iconOnly a img{border-width:0 !important;background-color:none;}#follow.bottom {width:100%; position:fixed; left:0px; bottom:0px;background-color:#878787;border:2px solid #fff;border-width:2px 0 0 0;}\n" +
"}\n" +
"#follow.bottom ul {padding-left:20px;list-style-type:none;} #follow.bottom ul li {float:left;padding-top:4px;margin-right:10px;list-style-type:none;}\n" +
"#follow.bottom ul li.follow {color:#000;line-height:24px;}\n" +
".share {margin:0 3px 3px 0;}\n" +
".phat span {display:inline;}\n" +
"ul.row li {float:left;list-style-type:none;}\n" +
"li.iconOnly a span.head {display:none}\n" +
"#follow.left ul.size16 li.follow{margin:0px auto !important}\n" +
"li.icon_text a {padding-left:0;margin-right:3px}\n" +
"li.text_only a {background-image:none !important;padding-left:0;}\n" +
"li.text_only a img {display:none;}\n" +
"li.icon_text a span{background-image:none !important;padding-left:0 !important; }\n" +
"li.iconOnly a span.head {display:none}\n" +
"ul.socialwrap li {margin:0 3px 3px 0 !important;}\n" +
"ul.socialwrap li a {text-decoration:none;}ul.row li {float:left;line-height:auto !important;}\n" +
"ul.row li a img {padding:0}.size16 li a,.size24 li a,.size32 li a, .size48 li a, .size60 li a {display:block}ul.socialwrap {list-style-type:none !important;margin:0; padding:0;text-indent:0 !important;}\n" +
"ul.socialwrap li {list-style-type:none !important;background-image:none;padding:0;list-style-image:none !important;}\n" +
"ul.followwrap {list-style-type:none !important;margin:0; padding:0}\n" +
"ul.followwrap li {margin-right:3px;margin-bottom:3px;list-style-type:none !important;}\n" +
"#follow.right ul.followwrap li, #follow.left ul.followwrap li {margin-right:0px;margin-bottom:0px;}\n" +
".shareinpost {clear:both;padding-top:0px}.shareinpost ul.socialwrap {list-style-type:none !important;margin:0 !important; padding:0 !important}\n" +
".shareinpost ul.socialwrap li {padding-left:0 !important;background-image:none !important;margin-left:0 !important;list-style-type:none !important;text-indent:0 !important}\n" +
".socialwrap li.icon_text a img, .socialwrap li.iconOnly a img{border-width:0}ul.followrap li {list-style-type:none;list-style-image:none !important;}\n" +
"div.clean {clear:left;}\n" +
"div.display_none {display:none;}\n" +
".button_holder_left{margin-right:5px;display:inline}.button_holder_right{margin-left:5px;display:inline}.button_holder_show_interactive{display:inline}</style><style type='text/css' media='print' >.socialwrap li.icon_text a img, .socialwrap li.iconOnly a img, .followwrap li.icon_text a img, .followwrap li.iconOnly a img{border-width:0 !important;background-color:none;}#follow.bottom {width:100%; position:fixed; left:0px; bottom:0px;background-color:#878787;border:2px solid #fff;border-width:2px 0 0 0;}\n" +
"}\n" +
"#follow.bottom ul {padding-left:20px;list-style-type:none;} #follow.bottom ul li {float:left;padding-top:4px;margin-right:10px;list-style-type:none;}\n" +
"#follow.bottom ul li.follow {color:#000;line-height:24px;}\n" +
".share {margin:0 3px 3px 0;}\n" +
".phat span {display:inline;}\n" +
"ul.row li {float:left;list-style-type:none;}\n" +
"li.iconOnly a span.head {display:none}\n" +
"#follow.left ul.size16 li.follow{margin:0px auto !important}\n" +
"li.icon_text a {padding-left:0;margin-right:3px}\n" +
"li.text_only a {background-image:none !important;padding-left:0;}\n" +
"li.text_only a img {display:none;}\n" +
"li.icon_text a span{background-image:none !important;padding-left:0 !important; }\n" +
"li.iconOnly a span.head {display:none}\n" +
"ul.socialwrap li {margin:0 3px 3px 0 !important;}\n" +
"ul.socialwrap li a {text-decoration:none;}ul.row li {float:left;line-height:auto !important;}\n" +
"ul.row li a img {padding:0}.size16 li a,.size24 li a,.size32 li a, .size48 li a, .size60 li a {display:block}ul.socialwrap {list-style-type:none !important;margin:0; padding:0;text-indent:0 !important;}\n" +
"ul.socialwrap li {list-style-type:none !important;background-image:none;padding:0;list-style-image:none !important;}\n" +
"ul.followwrap {list-style-type:none !important;margin:0; padding:0}\n" +
"ul.followwrap li {margin-right:3px;margin-bottom:3px;list-style-type:none !important;}\n" +
"#follow.right ul.followwrap li, #follow.left ul.followwrap li {margin-right:0px;margin-bottom:0px;}\n" +
".shareinpost {clear:both;padding-top:0px}.shareinpost ul.socialwrap {list-style-type:none !important;margin:0 !important; padding:0 !important}\n" +
".shareinpost ul.socialwrap li {padding-left:0 !important;background-image:none !important;margin-left:0 !important;list-style-type:none !important;text-indent:0 !important}\n" +
".socialwrap li.icon_text a img, .socialwrap li.iconOnly a img{border-width:0}ul.followrap li {list-style-type:none;list-style-image:none !important;}\n" +
"div.clean {clear:left;}\n" +
"div.display_none {display:none;}\n" +
".button_holder_left{margin-right:5px;display:inline}.button_holder_right{margin-left:5px;display:inline}.button_holder_show_interactive{display:inline}</style><link rel=\"alternate\" type=\"application/rss+xml\" title=\"Cogito Learning &raquo; Feed\" href=\"http://cogitolearning.co.uk/?feed=rss2\" />\n" +
"<link rel=\"alternate\" type=\"application/rss+xml\" title=\"Cogito Learning &raquo; Comments Feed\" href=\"http://cogitolearning.co.uk/?feed=comments-rss2\" />\n" +
"<link rel=\"alternate\" type=\"application/rss+xml\" title=\"Cogito Learning &raquo; Writing a Parser in Java: The Tokenizer Comments Feed\" href=\"http://cogitolearning.co.uk/?feed=rss2&#038;p=525\" />\n" +
"<link rel='stylesheet' id='cntctfrm_form_style-css'  href='http://cogitolearning.co.uk/wp-content/plugins/contact-form-plugin/css/form_style.css?ver=4.1.5' type='text/css' media='all' />\n" +
"<link rel='stylesheet' id='cookielawinfo-style-css'  href='http://cogitolearning.co.uk/wp-content/plugins/cookie-law-info/css/cli-style.css?ver=4.1.5' type='text/css' media='all' />\n" +
"<link rel='stylesheet' id='foreverwood-elegantfont-css'  href='http://cogitolearning.co.uk/wp-content/themes/foreverwood/css/elegantfont.css?ver=4.1.5' type='text/css' media='all' />\n" +
"<link rel='stylesheet' id='foreverwood-style-css'  href='http://cogitolearning.co.uk/wp-content/themes/foreverwood/style.css?ver=4.1.5' type='text/css' media='all' />\n" +
"<link rel='stylesheet' id='foreverwood-google-font-default-css'  href='//fonts.googleapis.com/css?family=PT+Sans&#038;subset=latin%2Clatin-ext&#038;ver=4.1.5' type='text/css' media='all' />\n" +
"<script type='text/javascript' src='http://cogitolearning.co.uk/wp-includes/js/jquery/jquery.js?ver=1.11.1'></script>\n" +
"<script type='text/javascript' src='http://cogitolearning.co.uk/wp-includes/js/jquery/jquery-migrate.min.js?ver=1.2.1'></script>\n" +
"<script type='text/javascript' src='http://cogitolearning.co.uk/wp-content/plugins/jquery-vertical-accordion-menu/js/jquery.hoverIntent.minified.js?ver=4.1.5'></script>\n" +
"<script type='text/javascript' src='http://cogitolearning.co.uk/wp-content/plugins/jquery-vertical-accordion-menu/js/jquery.cookie.js?ver=4.1.5'></script>\n" +
"<script type='text/javascript' src='http://cogitolearning.co.uk/wp-content/plugins/jquery-vertical-accordion-menu/js/jquery.dcjqaccordion.2.9.js?ver=4.1.5'></script>\n" +
"<script type='text/javascript' src='http://cogitolearning.co.uk/wp-content/plugins/cookie-law-info/js/cookielawinfo.js?ver=4.1.5'></script>\n" +
"<script type='text/javascript' src='http://cogitolearning.co.uk/wp-content/themes/foreverwood/js/html5.js?ver=3.6'></script>\n" +
"<script type='text/javascript' src='http://cogitolearning.co.uk/wp-content/plugins/google-analyticator/external-tracking.min.js?ver=6.4.8'></script>\n" +
"<link rel=\"EditURI\" type=\"application/rsd+xml\" title=\"RSD\" href=\"http://cogitolearning.co.uk/xmlrpc.php?rsd\" />\n" +
"<link rel=\"wlwmanifest\" type=\"application/wlwmanifest+xml\" href=\"http://cogitolearning.co.uk/wp-includes/wlwmanifest.xml\" /> \n" +
"<link rel='prev' title='QuantLib: LastFixingQuote' href='http://cogitolearning.co.uk/?p=539' />\n" +
"<link rel='next' title='Writing a Parser in Java: Introduction to the LL(1) Grammar' href='http://cogitolearning.co.uk/?p=558' />\n" +
"<meta name=\"generator\" content=\"WordPress 4.1.5\" />\n" +
"<link rel='canonical' href='http://cogitolearning.co.uk/?p=525' />\n" +
"<link rel='shortlink' href='http://cogitolearning.co.uk/?p=525' />\n" +
"<style type=\"text/css\">\n" +
"#author-bio-box {\n" +
"	width:632px;\n" +
"	background: #;\n" +
"	border: 1px solid #bbb;\n" +
"	box-shadow: 5px 5px 2px #888;\n" +
"	padding: 5px;\n" +
"}\n" +
"#author-bio-box img {\n" +
"	float: left;\n" +
"	margin-right: 10px;\n" +
"	margin-bottom: 2px;\n" +
"}\n" +
"#author-bio-box .author-name {\n" +
"	font-weight: bold;\n" +
"	margin: 0px;\n" +
"	font-size: 14px;\n" +
"}\n" +
"#author-bio-box p {\n" +
"	font-size: 10px;\n" +
"	line-height: 14px;\n" +
"}\n" +
"#author-bio-box thead th {\n" +
"	border: 0;\n" +
"}\n" +
"#author-bio-box tbody {\n" +
"	border: 0;\n" +
"}\n" +
".bio-spacer { min-height:44px; padding: 1px; display: block; clear: both; border:1px;}\n" +
".bio-socials {\n" +
"	border-top:solid 1px;\n" +
"	border-bottom:none;\n" +
"	border-left:none;\n" +
"	border-right:none;\n" +
"	width: 628px;\n" +
"	height: 32px;\n" +
"	clear: both;\n" +
"}\n" +
"</style>\n" +
"	<link rel=\"stylesheet\" href=\"http://cogitolearning.co.uk/wp-content/plugins/jquery-vertical-accordion-menu/skin.php?widget_id=3&amp;skin=clean\" type=\"text/css\" media=\"screen\"  />\n" +
"<!-- Block (1) START-->\n" +
"<style type=\"text/css\">\n" +
".entry_content table.cogitosmallleft td {\n" +
"  text-align: left;\n" +
"  font-size:80%;\n" +
"}\n" +
"\n" +
"div#cookie-compliance-overlay {\n" +
"  color:#000000;\n" +
"}\n" +
"\n" +
".cogitohighlightdiv {\n" +
"  background-color: #FFFBCC;\n" +
"padding: 5px;\n" +
"}\n" +
"\n" +
"pre {\n" +
"  margin: 0 1em 1em 1em;\n" +
"  padding: 1em;\n" +
"  background-color: #ccf;\n" +
"  border: 1px solid #ccc;\n" +
"}\n" +
"\n" +
"ul.menu ul.sub-menu a.dcjq-parent {\n" +
"  padding-left: 30px !important;\n" +
"}\n" +
"\n" +
"ul.menu ul.sub-menu ul.sub-menu a {\n" +
"  padding-left: 40px !important;\n" +
"}\n" +
"\n" +
"</style>\n" +
"<!-- Block (1) END -->\n" +
"<!-- Google Analytics Tracking by Google Analyticator 6.4.8: http://www.videousermanuals.com/google-analyticator/ -->\n" +
"<script type=\"text/javascript\">\n" +
"    var analyticsFileTypes = [''];\n" +
"    var analyticsSnippet = 'disabled';\n" +
"    var analyticsEventTracking = 'enabled';\n" +
"</script>\n" +
"<script type=\"text/javascript\">\n" +
"	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n" +
"	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n" +
"	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n" +
"	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');\n" +
"	ga('create', 'UA-896649-7', 'auto');\n" +
" \n" +
"	ga('send', 'pageview');\n" +
"</script>\n" +
"   \n" +
"</head>\n" +
" \n" +
"<body class=\"single single-post postid-525 single-format-standard\" id=\"wrapper\">\n" +
"<div id=\"container-boxed\"> \n" +
"<div id=\"container-boxed-inner\">\n" +
"<header id=\"wrapper-header\">  \n" +
"  <div class=\"header-content-wrapper\">\n" +
"    <div class=\"header-content\">\n" +
"      <div class=\"title-box\">\n" +
"        <p class=\"site-title\"><a href=\"http://cogitolearning.co.uk/\">Cogito Learning</a></p>\n" +
"      </div>\n" +
"    </div>\n" +
"  </div>\n" +
"  <div class=\"menu-panel-wrapper\">\n" +
"    <div class=\"menu-panel\">\n" +
"<div class=\"menu-content-container\"><ul id=\"main-nav\" class=\"menu\"><li id=\"menu-item-27\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-27\"><a href=\"http://cogitolearning.co.uk/\">Welcome</a></li>\n" +
"<li id=\"menu-item-26\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-item-26\"><a href=\"http://cogitolearning.co.uk/?page_id=21\">Courses</a>\n" +
"<ul class=\"sub-menu\">\n" +
"	<li id=\"menu-item-32\" class=\"menu-item menu-item-type-post_type menu-item-object-page first-menu-item menu-item-32\"><a href=\"http://cogitolearning.co.uk/?page_id=29\">Fundamentals of C++</a></li>\n" +
"	<li id=\"menu-item-38\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-38\"><a href=\"http://cogitolearning.co.uk/?page_id=35\">Advanced C++</a></li>\n" +
"	<li id=\"menu-item-61\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-61\"><a href=\"http://cogitolearning.co.uk/?page_id=57\">Programming with Boost</a></li>\n" +
"	<li id=\"menu-item-93\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-93\"><a href=\"http://cogitolearning.co.uk/?page_id=83\">Using Design Patterns in C++</a></li>\n" +
"	<li id=\"menu-item-62\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-62\"><a href=\"http://cogitolearning.co.uk/?page_id=48\">Fundamentals of Java</a></li>\n" +
"	<li id=\"menu-item-109\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-109\"><a href=\"http://cogitolearning.co.uk/?page_id=103\">Object Oriented Programming in Java</a></li>\n" +
"	<li id=\"menu-item-81\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-81\"><a href=\"http://cogitolearning.co.uk/?page_id=72\">Android Programming</a></li>\n" +
"	<li id=\"menu-item-116\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-116\"><a href=\"http://cogitolearning.co.uk/?page_id=111\">Using Design Patterns in Java</a></li>\n" +
"	<li id=\"menu-item-131\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-131\"><a href=\"http://cogitolearning.co.uk/?page_id=122\">Library and Framework Use in Java</a></li>\n" +
"	<li id=\"menu-item-165\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-165\"><a href=\"http://cogitolearning.co.uk/?page_id=157\">Fundamentals of PHP and HTML</a></li>\n" +
"	<li id=\"menu-item-202\" class=\"menu-item menu-item-type-post_type menu-item-object-page last-menu-item menu-item-202\"><a href=\"http://cogitolearning.co.uk/?page_id=193\">Advanced Library Use in PHP</a></li>\n" +
"</ul>\n" +
"</li>\n" +
"<li id=\"menu-item-761\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-item-761\"><a href=\"http://cogitolearning.co.uk/?page_id=751\">Upcoming</a>\n" +
"<ul class=\"sub-menu\">\n" +
"	<li id=\"menu-item-775\" class=\"menu-item menu-item-type-post_type menu-item-object-page first-menu-item menu-item-775\"><a href=\"http://cogitolearning.co.uk/?page_id=771\">Introduction to Java</a></li>\n" +
"	<li id=\"menu-item-762\" class=\"menu-item menu-item-type-post_type menu-item-object-page last-menu-item menu-item-762\"><a href=\"http://cogitolearning.co.uk/?page_id=745\">Introduction to Android</a></li>\n" +
"</ul>\n" +
"</li>\n" +
"<li id=\"menu-item-97\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-97\"><a href=\"http://cogitolearning.co.uk/?page_id=94\">Fees</a></li>\n" +
"<li id=\"menu-item-70\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-70\"><a href=\"http://cogitolearning.co.uk/?page_id=66\">Contact</a></li>\n" +
"</ul></div>    </div>\n" +
"  </div>\n" +
"\n" +
"</header> <!-- end of wrapper-header --><div id=\"wrapper-content\">\n" +
"  <div class=\"container\">\n" +
"  <div id=\"main-content\">\n" +
"    <article id=\"content\">\n" +
"      <div class=\"content-headline\">\n" +
"        <h1 class=\"entry-headline\">Writing a Parser in Java: The Tokenizer</h1>\n" +
"		      </div>\n" +
"		        <p class=\"post-meta\">\n" +
"          <span class=\"post-info-author\"><i class=\"icon_pencil-edit\" aria-hidden=\"true\"></i> <a href=\"http://cogitolearning.co.uk/?author=3\" title=\"Posts by cogitolearning\" rel=\"author\">cogitolearning</a></span>\n" +
"          <span class=\"post-info-date\"><i class=\"icon_clock_alt\" aria-hidden=\"true\"></i> April 8, 2013</span>\n" +
"          <span class=\"post-info-comments\"><i class=\"icon_comment_alt\" aria-hidden=\"true\"></i> <a href=\"http://cogitolearning.co.uk/?p=525#comments\">7</a></span>\n" +
"          <span class=\"post-info-category\"><i class=\"icon_folder-alt\" aria-hidden=\"true\"></i> <a href=\"http://cogitolearning.co.uk/?cat=42\" rel=\"category\">Java</a>, <a href=\"http://cogitolearning.co.uk/?cat=43\" rel=\"category\">Parser</a></span>\n" +
"<span class=\"post-info-tags\"><i class=\"icon_tag_alt\" aria-hidden=\"true\"></i> <a href=\"http://cogitolearning.co.uk/?tag=java\" rel=\"tag\">java</a>, <a href=\"http://cogitolearning.co.uk/?tag=parser-2\" rel=\"tag\">parser</a>, <a href=\"http://cogitolearning.co.uk/?tag=tokenizer\" rel=\"tag\">tokenizer</a>, <a href=\"http://cogitolearning.co.uk/?tag=tutorial\" rel=\"tag\">tutorial</a></span>        </p>\n" +
"      <div class=\"entry-content\">\n" +
"<p>In <a title=\"Writing a Parser in Java: Introduction\" href=\"http://cogitolearning.co.uk/?p=523\">this short series</a> I am talking about how to write a parser that analyses mathematical expressions and turns them into an object tree that is able to evaluate that expression.</p>\n" +
"<p>The first step in writing a parser is to tokenize the input string. This means to separate the input string into short bits that represent the basic entities in the expression. We could do this by hand, reading one character at a time and assembling the tokens character by character. This would be tedious, difficult to maintain and hard to extend should we decide to add more features to the tokenizer.</p>\n" +
"<p>Instead we decide to use the java.util.regex package to do the work for us. This will allow us to implement quite a general tokenizer that can be used for any kind of grammar.</p>\n" +
"<p>Let&#8217;s start by creating a class called <strong>Tokenizer</strong> and defining an internal class <strong>TokenInfo</strong> that holds information about the individual tokens.</p>\n" +
"<pre>import java.util.regex.Pattern;\n" +
"\n" +
"public class Tokenizer\n" +
"{\n" +
"  private class TokenInfo {\n" +
"    public final Pattern regex;\n" +
"    public final int token;\n" +
"\n" +
"    public TokenInfo(Pattern regex, int token) {\n" +
"      super();\n" +
"      this.regex = regex;\n" +
"      this.token = token;\n" +
"    }\n" +
"  }\n" +
"}</pre>\n" +
"<p>As you can see <strong>TokenInfo</strong> contains two fields. The regular expression that is used to match the input string against the token is stored in the <strong>Pattern regex</strong>. We store <strong>Pattern</strong> objects instead of the regular expression string to improve performance. Regular expressions have to be compiled which takes time. <strong>Pattern</strong> stores the regular expression in compiled form. The code of the token is given by the integer value &#8216;token&#8217;. Each type of token should have its own code.</p>\n" +
"<pre>private LinkedList&lt;TokenInfo&gt; tokenInfos;\n" +
"\n" +
"public Tokenizer() {\n" +
"  tokenInfos = new LinkedList&lt;TokenInfo&gt;();\n" +
"}</pre>\n" +
"<p>We define a linked list, called <strong>tokenInfos</strong>, to hold the information for all the tokens. The linked list is created in the constructor of our <strong>Tokenizer</strong> class. Next we need to add the token information in our list. We do this via the <strong>add()</strong> method.</p>\n" +
"<pre>public void add(String regex, int token) {\n" +
"  tokenInfos.add(\n" +
"  new TokenInfo(\n" +
"  Pattern.compile(\"^(\"+regex+\")\"), token));\n" +
"}</pre>\n" +
"<p>The user can pass a regular expression string and a token code to the method. The method will then the &#8220;^&#8221; character to the user supplied regular expression. It causes the regular expression to match only the beginning of a string. This is needed because we will be removing any token always looking for the next token at the beginning of the input string.</p>\n" +
"<p>We also want to store the information about the tokens we have seen. We need to store the token code and the string that corresponds to the token. The string is needed because the token code does not retain the full information about the input. When we have found a variable we will give the token a special code for variable tokens but we need to also keep the name of the variable so that we can later use it to store or retrieve information. To this end we define another internal class called Token and a linked list of these tokens.</p>\n" +
"<pre>public class Token {\n" +
"  public final int token;\n" +
"  public final String sequence;\n" +
"\n" +
"  public Token(int token, String sequence) {\n" +
"    super();\n" +
"    this.token = token;\n" +
"    this.sequence = sequence;\n" +
"  }\n" +
"}</pre>\n" +
"<pre>private LinkedList&lt;Token&gt; tokens;</pre>\n" +
"<p>In our constructor of <strong>Tokenizer</strong> we now also have to initialize the tokens list.</p>\n" +
"<pre>public Tokenizer() {\n" +
"  tokenInfos = new LinkedList&lt;TokenInfo&gt;();\n" +
"  tokens = new LinkedList&lt;Token&gt;();\n" +
"}</pre>\n" +
"<p>We can now add token information in our main method.</p>\n" +
"<pre>public static void main(String[] args) {\n" +
"  Tokenizer tokenizer = new Tokenizer();\n" +
"  tokenizer.add(\"sin|cos|exp|ln|sqrt\", 1); // function\n" +
"  tokenizer.add(\"\\\\(\", 2); // open bracket\n" +
"  tokenizer.add(\"\\\\)\", 3); // close bracket\n" +
"  tokenizer.add(\"[+-]\", 4); // plus or minus\n" +
"  tokenizer.add(\"[*/]\", 5); // mult or divide\n" +
"  tokenizer.add(\"\\\\^\", 6); // raised\n" +
"  tokenizer.add(\"[0-9]+\",7); // integer number\n" +
"  tokenizer.add(\"[a-zA-Z][a-zA-Z0-9_]*\", 8); // variable\n" +
"}</pre>\n" +
"<p>The code above adds regular expressions that match functions, brackets, mathematical operators, integer numbers and variables. Note how each type of token gets a unique code. Note also how we have to escape special characters in the regular expressions with a double backslash.</p>\n" +
"<p>We are now ready to tokenize an input string. This is done in the tokenize method. In this method we first define a local string that contains the input string but without any leading or trailing spaces. Also we clear the tokens list from any previous data.</p>\n" +
"<pre>public void tokenize(String str) {\n" +
"  String s = new String(str);\n" +
"  tokens.clear();</pre>\n" +
"<p>The main loop is carried out until the local input string is empty. When we find a token we will remove it from the front of the string. If we are successful and the whole string could be tokenized then we will eventually end up with an empty string.</p>\n" +
"<pre>  while (!s.equals(\"\")) {\n" +
"    boolean match = false;</pre>\n" +
"<p>The <strong>match</strong> variable indicates if any of the tokens provided a match with the beginning of the input string. Initially we have no match. We now loop through all the token infos. and create a Matcher for the input string.</p>\n" +
"<pre>    for (TokenInfo info : tokenInfos) {\n" +
"      Matcher m = info.regex.matcher(s);\n" +
"      if (m.find()) {\n" +
"        match = true;\n" +
"\n" +
"        String tok = m.group().trim();\n" +
"        tokens.add(new Token(info.token, tok));\n" +
"\n" +
"        s = m.replaceFirst(\"\");\n" +
"        break;\n" +
"      }\n" +
"    }</pre>\n" +
"<p>If the pattern is found in the input string then match is set to true. The <strong>group()</strong> method of the <strong>Matcher</strong> returns the string of the last match. A new <strong>Token</strong> object is appended to the list of tokens. The token object contains the code of the token that resulted in the match and the matched string. In the next step, the matcher is used to remove the token from the beginning of the input string. We do this with the <strong>replaceFirst()</strong> method which replaces the first (and only) match with an empty string. Finally we break out of the loop over the token infos because we don&#8217;t need to check any other token types in this round.</p>\n" +
"<p>After the loop has finished we check if a match was found. If not we throw an exception. We are using a <strong>ParserException</strong> which is extends <strong>RuntimeException</strong> without adding new functionality to it.</p>\n" +
"<pre>    if (!match) throw new ParserException(\n" +
"        \"Unexpected character in input: \"+s);\n" +
"  }\n" +
"}</pre>\n" +
"<p>This is it! We are done with tokenizing the user input.</p>\n" +
"<p>If you paid close attention you might have realized that the regular expression for variable tokens also matches any function token. This is not a bug. By storing the token information in a linked list, and always ensuring that we look for matches from the beginning of the list, we are giving precedence to patterns added first. If the input sequence starts with alphabetic characters the function pattern will first be tested and will match any one of the specified function. Only if there is no match the variable pattern will be tested.</p>\n" +
"<p>To access the result of tokenizing an input string we define the getter for the tokens list.</p>\n" +
"<pre>public LinkedList&lt;Token&gt; getTokens() {\n" +
"  return tokens;\n" +
"}</pre>\n" +
"<p>Now we are ready to tokenize the input. In our main method we add the following code:</p>\n" +
"<pre>try {\n" +
"  tokenizer.tokenize(\" sin(x) * (1 + var_12) \");\n" +
"\n" +
"  for (Tokenizer.Token tok : tokenizer.getTokens()) {\n" +
"    System.out.println(\"\" + tok.token + \" \" + tok.sequence);\n" +
"  }\n" +
"}\n" +
"catch (ParserException e) {\n" +
"  System.out.println(e.getMessage());\n" +
"}</pre>\n" +
"<p>The output of our program is</p>\n" +
"<pre>1 sin\n" +
"2 (\n" +
"8 x\n" +
"3 )\n" +
"5 *\n" +
"2 (\n" +
"7 1\n" +
"4 +\n" +
"8 var_12\n" +
"3 )</pre>\n" +
"<p>You can download the Java sources for the tokenizer <a href=\"http://cogitolearning.co.uk/wp-content/uploads/2013/04/CogitoLearningTokenizer.zip\">from this link</a>.</p>\n" +
"<p>In the next instalment of this series we will be turning our attention towards designing a grammar for mathematical expressions.</p>\n" +
"      </div>\n" +
"<div id=\"foreverwood-post-nav\" class=\"navigation\" role=\"navigation\">\n" +
"	<div class=\"nav-wrapper\">\n" +
"  <p class=\"nav-previous\"><a href=\"http://cogitolearning.co.uk/?p=539\" title=\"QuantLib: LastFixingQuote\">&larr; Previous post</a></p>\n" +
"	<p class=\"nav-next\"><a href=\"http://cogitolearning.co.uk/?p=558\" title=\"Writing a Parser in Java: Introduction to the LL(1) Grammar\">Next post &rarr;</a></p>\n" +
"   </div>\n" +
"</div>\n" +
" \n" +
"\n" +
"<div id=\"comments\" class=\"comments-area comments-area-post\">\n" +
"\n" +
"	    <h2 class=\"entry-headline\">7 Comments</h2>\n" +
"\n" +
"		<ol class=\"commentlist\">\n" +
"				<li class=\"comment even thread-even depth-1\" id=\"li-comment-7154\">\n" +
"		<div id=\"comment-7154\" class=\"comment\">\n" +
"			<div class=\"comment-meta comment-author vcard\">\n" +
"				<img alt='' src='http://1.gravatar.com/avatar/9d0dff1a49143af5f0a317e9c7746670?s=44&amp;d=http%3A%2F%2F1.gravatar.com%2Favatar%2Fad516503a11cd5ca435acc9bb6523536%3Fs%3D44&amp;r=G' class='avatar avatar-44 photo' height='44' width='44' /><span><b class=\"fn\">roshan</b> </span><time datetime=\"2013-04-09T23:34:36+00:00\">April 9, 2013 at 11:34 pm</time>			</div><!-- .comment-meta -->\n" +
"\n" +
"			\n" +
"			<div class=\"comment-content comment\">\n" +
"				<p>What would be the regex to capture whitespace between words and the newline at end of a line?</p>\n" +
"			 <div class=\"reply\">\n" +
"			   <a class='comment-reply-link' href='/?p=525&#038;replytocom=7154#respond' onclick='return addComment.moveForm( \"comment-7154\", \"7154\", \"respond\", \"525\" )' aria-label='Reply to roshan'>Reply</a> <span>&darr;</span>			</div><!-- .reply -->\n" +
"			   			</div><!-- .comment-content -->\n" +
"		</div><!-- #comment-## -->\n" +
"	<ol class=\"children\">\n" +
"	<li class=\"comment byuser comment-author-cogitolearning bypostauthor odd alt depth-2\" id=\"li-comment-7156\">\n" +
"		<div id=\"comment-7156\" class=\"comment\">\n" +
"			<div class=\"comment-meta comment-author vcard\">\n" +
"				<img alt='' src='http://1.gravatar.com/avatar/faae426ef8baa2c4ad0d063cc60d9d00?s=44&amp;d=http%3A%2F%2F1.gravatar.com%2Favatar%2Fad516503a11cd5ca435acc9bb6523536%3Fs%3D44&amp;r=G' class='avatar avatar-44 photo' height='44' width='44' /><span><b class=\"fn\"><a href='http://www.cogitolearning.co.uk' rel='external nofollow' class='url'>cogitolearning</a></b> <span>(Post author)</span></span><time datetime=\"2013-04-10T04:45:46+00:00\">April 10, 2013 at 4:45 am</time>			</div><!-- .comment-meta -->\n" +
"\n" +
"			\n" +
"			<div class=\"comment-content comment\">\n" +
"				<p>The regular expression to match a whitespace is \\s. If you enclose it in a Java string you have to escape the backslash and it becomes &#8220;\\\\s&#8221;. The expression for newlines is a bit more complicated because Unix and DOS/Windows use different conventions. For DOS it is \\r\\n and for Unix it is \\n. If you want to capture either one you can use \\r\\n|\\n or &#8220;\\\\r\\\\n|\\\\n&#8221; in a Java string.</p>\n" +
"			 <div class=\"reply\">\n" +
"			   <a class='comment-reply-link' href='/?p=525&#038;replytocom=7156#respond' onclick='return addComment.moveForm( \"comment-7156\", \"7156\", \"respond\", \"525\" )' aria-label='Reply to cogitolearning'>Reply</a> <span>&darr;</span>			</div><!-- .reply -->\n" +
"			   			</div><!-- .comment-content -->\n" +
"		</div><!-- #comment-## -->\n" +
"	</li><!-- #comment-## -->\n" +
"</ol><!-- .children -->\n" +
"</li><!-- #comment-## -->\n" +
"	<li class=\"comment even thread-odd thread-alt depth-1\" id=\"li-comment-9236\">\n" +
"		<div id=\"comment-9236\" class=\"comment\">\n" +
"			<div class=\"comment-meta comment-author vcard\">\n" +
"				<img alt='' src='http://0.gravatar.com/avatar/ceb52ea504f2e08c347e90bb50dd22ec?s=44&amp;d=http%3A%2F%2F0.gravatar.com%2Favatar%2Fad516503a11cd5ca435acc9bb6523536%3Fs%3D44&amp;r=G' class='avatar avatar-44 photo' height='44' width='44' /><span><b class=\"fn\">__init__</b> </span><time datetime=\"2013-06-05T10:51:26+00:00\">June 5, 2013 at 10:51 am</time>			</div><!-- .comment-meta -->\n" +
"\n" +
"			\n" +
"			<div class=\"comment-content comment\">\n" +
"				<p>For anyone wondering what the ParserException looks like, this is mine.</p>\n" +
"<p>public class ParserException extends RuntimeException {<br />\n" +
"    public ParserException(String msg) {<br />\n" +
"        super(msg);<br />\n" +
"    }<br />\n" +
"}</p>\n" +
"			 <div class=\"reply\">\n" +
"			   <a class='comment-reply-link' href='/?p=525&#038;replytocom=9236#respond' onclick='return addComment.moveForm( \"comment-9236\", \"9236\", \"respond\", \"525\" )' aria-label='Reply to __init__'>Reply</a> <span>&darr;</span>			</div><!-- .reply -->\n" +
"			   			</div><!-- .comment-content -->\n" +
"		</div><!-- #comment-## -->\n" +
"	</li><!-- #comment-## -->\n" +
"	<li class=\"comment odd alt thread-even depth-1\" id=\"li-comment-9237\">\n" +
"		<div id=\"comment-9237\" class=\"comment\">\n" +
"			<div class=\"comment-meta comment-author vcard\">\n" +
"				<img alt='' src='http://0.gravatar.com/avatar/ceb52ea504f2e08c347e90bb50dd22ec?s=44&amp;d=http%3A%2F%2F0.gravatar.com%2Favatar%2Fad516503a11cd5ca435acc9bb6523536%3Fs%3D44&amp;r=G' class='avatar avatar-44 photo' height='44' width='44' /><span><b class=\"fn\">__init__</b> </span><time datetime=\"2013-06-05T11:03:20+00:00\">June 5, 2013 at 11:03 am</time>			</div><!-- .comment-meta -->\n" +
"\n" +
"			\n" +
"			<div class=\"comment-content comment\">\n" +
"				<p>Also, this post is not the same as the src in the .zip. You need to trim stuff. The code here will not work with spaces as used above. Other than that, great tutorial, helped me immensely.</p>\n" +
"			 <div class=\"reply\">\n" +
"			   <a class='comment-reply-link' href='/?p=525&#038;replytocom=9237#respond' onclick='return addComment.moveForm( \"comment-9237\", \"9237\", \"respond\", \"525\" )' aria-label='Reply to __init__'>Reply</a> <span>&darr;</span>			</div><!-- .reply -->\n" +
"			   			</div><!-- .comment-content -->\n" +
"		</div><!-- #comment-## -->\n" +
"	</li><!-- #comment-## -->\n" +
"	<li class=\"comment even thread-odd thread-alt depth-1\" id=\"li-comment-23061\">\n" +
"		<div id=\"comment-23061\" class=\"comment\">\n" +
"			<div class=\"comment-meta comment-author vcard\">\n" +
"				<img alt='' src='http://1.gravatar.com/avatar/b6ded996c84e6a948185823615c5c7cd?s=44&amp;d=http%3A%2F%2F1.gravatar.com%2Favatar%2Fad516503a11cd5ca435acc9bb6523536%3Fs%3D44&amp;r=G' class='avatar avatar-44 photo' height='44' width='44' /><span><b class=\"fn\">Roberto</b> </span><time datetime=\"2013-11-27T23:24:53+00:00\">November 27, 2013 at 11:24 pm</time>			</div><!-- .comment-meta -->\n" +
"\n" +
"			\n" +
"			<div class=\"comment-content comment\">\n" +
"				<p>Awesome tutorial!! </p>\n" +
"<p>One observation: using &#8220;+|-&#8221; and &#8220;*|/&#8221; actually messes up the order of the original expression. Try an expression like &#8220;3 &#8211; (4 &#8211; 9)&#8221; with the code on this blog and you will get the first &#8220;-&#8221; added before the &#8220;3&#8221;. Same with &#8220;/&#8221;.</p>\n" +
"<p>My work around was to add &#8220;-&#8221; and &#8220;/&#8221; separately but with the same token code:</p>\n" +
"<p>tokenizer.add(4, &#8220;\\\\+&#8221;);<br />\n" +
"tokenizer.add(4, &#8220;\\\\-&#8220;);<br />\n" +
"tokenizer.add(5, &#8220;\\\\*&#8221;);<br />\n" +
"tokenizer.add(5, &#8220;\\\\/&#8221;);</p>\n" +
"			 <div class=\"reply\">\n" +
"			   <a class='comment-reply-link' href='/?p=525&#038;replytocom=23061#respond' onclick='return addComment.moveForm( \"comment-23061\", \"23061\", \"respond\", \"525\" )' aria-label='Reply to Roberto'>Reply</a> <span>&darr;</span>			</div><!-- .reply -->\n" +
"			   			</div><!-- .comment-content -->\n" +
"		</div><!-- #comment-## -->\n" +
"	<ol class=\"children\">\n" +
"	<li class=\"comment byuser comment-author-mikail odd alt depth-2\" id=\"li-comment-28188\">\n" +
"		<div id=\"comment-28188\" class=\"comment\">\n" +
"			<div class=\"comment-meta comment-author vcard\">\n" +
"				<img alt='' src='http://1.gravatar.com/avatar/da8c4a5f60b3858aaa4fd6f662a2483c?s=44&amp;d=http%3A%2F%2F1.gravatar.com%2Favatar%2Fad516503a11cd5ca435acc9bb6523536%3Fs%3D44&amp;r=G' class='avatar avatar-44 photo' height='44' width='44' /><span><b class=\"fn\">Mikail</b> </span><time datetime=\"2014-02-10T15:00:18+00:00\">February 10, 2014 at 3:00 pm</time>			</div><!-- .comment-meta -->\n" +
"\n" +
"			\n" +
"			<div class=\"comment-content comment\">\n" +
"				<p>I have finally found the time to look into the problem. The &#8220;^&#8221; that is added to the regex before compiling into a Pattern only applies to the first option. Thus the regular expression &#8220;^+|-&#8221; matches a &#8220;+&#8221; at the beginning of the line or a minus anywhere. The correct expression should be &#8220;^(+|-)&#8221;. The bug is fixed by surrounding regex with brackets before compiling.</p>\n" +
"<p>I have updated the tutorial and the code file.</p>\n" +
"			 <div class=\"reply\">\n" +
"			   <a class='comment-reply-link' href='/?p=525&#038;replytocom=28188#respond' onclick='return addComment.moveForm( \"comment-28188\", \"28188\", \"respond\", \"525\" )' aria-label='Reply to Mikail'>Reply</a> <span>&darr;</span>			</div><!-- .reply -->\n" +
"			   			</div><!-- .comment-content -->\n" +
"		</div><!-- #comment-## -->\n" +
"	</li><!-- #comment-## -->\n" +
"</ol><!-- .children -->\n" +
"</li><!-- #comment-## -->\n" +
"	<li class=\"comment even thread-even depth-1\" id=\"li-comment-98488\">\n" +
"		<div id=\"comment-98488\" class=\"comment\">\n" +
"			<div class=\"comment-meta comment-author vcard\">\n" +
"				<img alt='' src='http://1.gravatar.com/avatar/70136f8472fcccaa4f8f071c4a10372f?s=44&amp;d=http%3A%2F%2F1.gravatar.com%2Favatar%2Fad516503a11cd5ca435acc9bb6523536%3Fs%3D44&amp;r=G' class='avatar avatar-44 photo' height='44' width='44' /><span><b class=\"fn\">Luricos</b> </span><time datetime=\"2015-03-13T12:31:34+00:00\">March 13, 2015 at 12:31 pm</time>			</div><!-- .comment-meta -->\n" +
"\n" +
"			\n" +
"			<div class=\"comment-content comment\">\n" +
"				<p>Thanks for this tutorial! One thing to note: You should point out that when the pattern is invalid PatternSyntaxException will be thrown during tokenizer.add(..).</p>\n" +
"<p>This should be catched when using tokenizer.add and maybe added to your provided SourceFiles.</p>\n" +
"			 <div class=\"reply\">\n" +
"			   <a class='comment-reply-link' href='/?p=525&#038;replytocom=98488#respond' onclick='return addComment.moveForm( \"comment-98488\", \"98488\", \"respond\", \"525\" )' aria-label='Reply to Luricos'>Reply</a> <span>&darr;</span>			</div><!-- .reply -->\n" +
"			   			</div><!-- .comment-content -->\n" +
"		</div><!-- #comment-## -->\n" +
"	</li><!-- #comment-## -->\n" +
"		</ol><!-- .commentlist -->\n" +
"\n" +
"		\n" +
"		\n" +
"	\n" +
"									<div id=\"respond\" class=\"comment-respond\">\n" +
"				<h3 id=\"reply-title\" class=\"comment-reply-title\">Leave a Comment <small><a rel=\"nofollow\" id=\"cancel-comment-reply-link\" href=\"/?p=525#respond\" style=\"display:none;\">Cancel reply</a></small></h3>\n" +
"									<form action=\"http://cogitolearning.co.uk/wp-comments-post.php\" method=\"post\" id=\"commentform\" class=\"comment-form\">\n" +
"																			<p class=\"comment-notes\"><span id=\"email-notes\">Your email address will not be published.</span> Required fields are marked <span class=\"required\">*</span></p>							<p class=\"comment-form-author\"><label for=\"author\"></label> <input id=\"author\" name=\"author\" type=\"text\" placeholder=\"Your name *\" value=\"\"  size=\"30\" aria-required='true' /></p>\n" +
"<p class=\"comment-form-email\"><label for=\"email\"></label> <input id=\"email\" name=\"email\" type=\"text\" placeholder=\"E-mail *\" value=\"\" size=\"30\" aria-required='true' /></p>\n" +
"<p class=\"comment-form-url\"><label for=\"url\"></label> <input id=\"url\" name=\"url\" type=\"text\" placeholder=\"Website\" value=\"\" size=\"30\" /></p>\n" +
"												<p><label for=\"comment\"></label><textarea id=\"comment\" name=\"comment-54deba4e12afce76\" cols=\"45\" rows=\"8\" aria-required=\"true\" placeholder=\"Comment...\"></textarea><textarea name=\"comment\" rows=\"1\" cols=\"1\" style=\"display:none\"></textarea></p><input type=\"hidden\" name=\"comment-replaced\" value=\"true\" />						<p class=\"form-allowed-tags\" id=\"form-allowed-tags\">You may use these <abbr title=\"HyperText Markup Language\">HTML</abbr> tags and attributes:  <code>&lt;a href=&quot;&quot; title=&quot;&quot;&gt; &lt;abbr title=&quot;&quot;&gt; &lt;acronym title=&quot;&quot;&gt; &lt;b&gt; &lt;blockquote cite=&quot;&quot;&gt; &lt;cite&gt; &lt;code&gt; &lt;del datetime=&quot;&quot;&gt; &lt;em&gt; &lt;i&gt; &lt;q cite=&quot;&quot;&gt; &lt;strike&gt; &lt;strong&gt; </code></p>						<p class=\"form-submit\">\n" +
"							<input name=\"submit\" type=\"submit\" id=\"submit\" class=\"submit\" value=\"Post Comment\" />\n" +
"							<input type='hidden' name='comment_post_ID' value='525' id='comment_post_ID' />\n" +
"<input type='hidden' name='comment_parent' id='comment_parent' value='0' />\n" +
"						</p>\n" +
"						<p style=\"display:none;\"><input type=\"text\" name=\"nxts\" value=\"1432721750\" /><input type=\"text\" name=\"nxts_signed\" value=\"86ef4cf305ba9750a53d65be7e7900b33734bc32\" /><input type=\"text\" name=\"d97c95f219b0296593869de1\" value=\"1fcb70b531854898710557fe06f9f0a0\" /><input type=\"text\" name=\"9f232c106\" value=\"\" /></p>					</form>\n" +
"							</div><!-- #respond -->\n" +
"			\n" +
"</div><!-- #comments .comments-area -->    </article> <!-- end of content -->\n" +
"  </div>\n" +
"<aside id=\"sidebar\">\n" +
"<div id=\"dc_jqaccordion_widget-3\" class=\"sidebar-widget \"> <p class=\"sidebar-headline\">Java Trails</p>		\n" +
"		<div class=\"dcjq-accordion\" id=\"dc_jqaccordion_widget-3-item\">\n" +
"		\n" +
"			<ul id=\"menu-java-trails\" class=\"menu\"><li id=\"menu-item-553\" class=\"menu-item menu-item-type-custom menu-item-object-custom current-menu-ancestor current-menu-parent menu-item-has-children menu-item-553\"><a href=\"http://cogitolearning.co.uk/?p=523\">Writing a Parser</a>\n" +
"<ul class=\"sub-menu\">\n" +
"	<li id=\"menu-item-554\" class=\"menu-item menu-item-type-custom menu-item-object-custom first-menu-item menu-item-554\"><a href=\"http://cogitolearning.co.uk/?p=523\">Introduction</a></li>\n" +
"	<li id=\"menu-item-555\" class=\"menu-item menu-item-type-custom menu-item-object-custom current-menu-item menu-item-555\"><a href=\"http://cogitolearning.co.uk/?p=525\">The tokenizer</a></li>\n" +
"	<li id=\"menu-item-569\" class=\"menu-item menu-item-type-custom menu-item-object-custom menu-item-569\"><a href=\"http://cogitolearning.co.uk/?p=558\">Introduction to the LL(1) grammar</a></li>\n" +
"	<li id=\"menu-item-571\" class=\"menu-item menu-item-type-custom menu-item-object-custom menu-item-571\"><a href=\"http://cogitolearning.co.uk/?p=565\">A grammar for mathematical expressions</a></li>\n" +
"	<li id=\"menu-item-633\" class=\"menu-item menu-item-type-custom menu-item-object-custom menu-item-633\"><a href=\"http://cogitolearning.co.uk/?p=573\">Implementing the parser</a></li>\n" +
"	<li id=\"menu-item-664\" class=\"menu-item menu-item-type-custom menu-item-object-custom menu-item-664\"><a href=\"http://cogitolearning.co.uk/?p=600\">The Expression Tree</a></li>\n" +
"	<li id=\"menu-item-698\" class=\"menu-item menu-item-type-custom menu-item-object-custom menu-item-698\"><a href=\"http://cogitolearning.co.uk/?p=630\">Creating the Expression Tree</a></li>\n" +
"	<li id=\"menu-item-700\" class=\"menu-item menu-item-type-custom menu-item-object-custom last-menu-item menu-item-700\"><a href=\"http://cogitolearning.co.uk/?p=650\">Setting Variables</a></li>\n" +
"</ul>\n" +
"</li>\n" +
"</ul>		\n" +
"		</div>\n" +
"		</div><div id=\"text-2\" class=\"sidebar-widget widget_text\"> <p class=\"sidebar-headline\">Tewter</p>			<div class=\"textwidget\"><p><a href=\"https://play.google.com/store/apps/details?id=uk.co.cogitolearning.tewter\"><img class=\"center\" src=\"http://cogitolearning.co.uk/wp-content/uploads/2013/10/tewter512.png\" width=\"300px\" height=\"300px\" alt=\"Tewter Logo\"/></a></p>\n" +
"<p><a href=\"https://play.google.com/store/apps/details?id=uk.co.cogitolearning.tewter\">Maths revision app for Android  with real questions!</a></p>\n" +
"</div>\n" +
"		</div><div id=\"share-widget-2\" class=\"sidebar-widget share_links\"> <p class=\"sidebar-headline\">Share this Site</p><ul class=\"socialwrap size16 row\"><li class=\"iconOnly\"><a  rel='nofollow'  target='_blank'  title='Digg this post : Writing a Parser in Java: The Tokenizer'  href='http://digg.com/submit?url=http%3A%2F%2Fcogitolearning.co.uk%2F%3Fp%3D525&amp;title=Writing+a+Parser+in+Java%3A+The+Tokenizer&amp;bodytext=In+this+short+series+I+am+talking+about+how+to+write+a+parser+that+analyses+mathematical+expressions+and+turns+them+into+an+object+tree+that+is+able+to+evaluate+that+expression.%0D%0A%0D%0AThe+first+step+in+writing+a+parser+is+to+tokenize+the+input+string.+This+means+to%C2%A0separate%C2%A0the+input+string+into+short+bits+that+represen' style=\"display:block;background: transparent url(http://cogitolearning.co.uk/wp-content/plugins/share-and-follow/default/16/digg.png) no-repeat top left;height:16px;width:16px;\" class=\"digg\" ><span class=\"head\">Digg this post</span></a></li><li class=\"iconOnly\"><a  rel='nofollow'  target='_blank'  title='Recommend this post : Writing a Parser in Java: The Tokenizer on Facebook'  href='http://www.facebook.com/sharer.php?u=http%3A%2F%2Fcogitolearning.co.uk%2F%3Fp%3D525&amp;t=Writing+a+Parser+in+Java%3A+The+Tokenizer' style=\"display:block;background: transparent url(http://cogitolearning.co.uk/wp-content/plugins/share-and-follow/default/16/facebook.png) no-repeat top left;height:16px;width:16px;\" class=\"facebook\" ><span class=\"head\">Recommend on Facebook</span></a></li><li class=\"iconOnly\"><a  rel='nofollow'  target='_blank'  title='Share this post : Writing a Parser in Java: The Tokenizer on google plus'  href='https://plusone.google.com/_/+1/confirm?hl=en&amp;url=http%3A%2F%2Fcogitolearning.co.uk%2F%3Fp%3D525&amp;title=Writing+a+Parser+in+Java%3A+The+Tokenizer' style=\"display:block;background: transparent url(http://cogitolearning.co.uk/wp-content/plugins/share-and-follow/default/16/gplus.png) no-repeat top left;height:16px;width:16px;\" class=\"gplus\" ><span class=\"head\">Share on google plus</span></a></li><li class=\"iconOnly\"><a  rel='nofollow'  target='_blank'  title='Share this post : Writing a Parser in Java: The Tokenizer on Linkedin'  href='http://www.linkedin.com/shareArticle?mini=true&amp;url=http%3A%2F%2Fcogitolearning.co.uk%2F%3Fp%3D525&amp;title=Writing+a+Parser+in+Java%3A+The+Tokenizer&amp;summary=In+this+short+series+I+am+talking+about+how+to+write+a+parser+that+analyses+mathematical+expressions+and+turns+them+into+an+object+tree+that+is+able+to+evaluate+that+expression.%0D%0A%0D%0AThe+first+step+in+writing+a+parser+is+to+tokenize+the+input+string.+This+means+to%C2%A0separate%C2%A0the+input+string+into+short+bits+that+represen' style=\"display:block;background: transparent url(http://cogitolearning.co.uk/wp-content/plugins/share-and-follow/default/16/linkedin.png) no-repeat top left;height:16px;width:16px;\" class=\"linkedin\" ><span class=\"head\">Share on Linkedin</span></a></li><li class=\"iconOnly\"><a rel=\"nofollow\" target=\"_blank\" style=\"display:block;background: transparent url(http://cogitolearning.co.uk/wp-content/plugins/share-and-follow/default/16/twitter.png) no-repeat top left;height:16px;width:16px;\" class=\"twitter\" href=\"http://twitter.com/share?url=http%3A%2F%2Fcogitolearning.co.uk%2F%3Fp%3D525&amp;text=Check+out+new+post%3A+-+ via+%40cogitolearning\" title=\"Tweet this post : Writing a Parser in Java: The Tokenizer on Twitter\"><span class=\"head\">Tweet about it</span></a></li><li class=\"iconOnly\"><a rel=\"_self\" style=\"display:block;background: transparent url(http://cogitolearning.co.uk/wp-content/plugins/share-and-follow/default/16/rss.png) no-repeat top left;height:16px;width:16px;\" class=\"rss\" title=\"Follow this post : Writing a Parser in Java: The Tokenizer comments\"  href=\"http://cogitolearning.co.uk/?p=525&feed=rss2\" ><span class=\"head\">Subscribe to the comments on this post</span></a></li></ul><div class=\"clean\"></div> </div><div id=\"linkcat-20\" class=\"sidebar-widget widget_links\"> <p class=\"sidebar-headline\">Pages</p>\n" +
"	<ul class='xoxo blogroll'>\n" +
"<li><a href=\"http://cogitolearning.co.uk/?page_id=214\">QuantLib Manual</a></li>\n" +
"\n" +
"	</ul>\n" +
"</div>\n" +
"		<div id=\"recent-posts-2\" class=\"sidebar-widget widget_recent_entries\">		 <p class=\"sidebar-headline\">Recent Posts</p>		<ul>\n" +
"					<li>\n" +
"				<a href=\"http://cogitolearning.co.uk/?p=1681\">QuantLib: Discounting Bond Engine</a>\n" +
"						</li>\n" +
"					<li>\n" +
"				<a href=\"http://cogitolearning.co.uk/?p=1669\">Four things you probably didn&#8217;t know about C++</a>\n" +
"						</li>\n" +
"					<li>\n" +
"				<a href=\"http://cogitolearning.co.uk/?p=1663\">Android Custom Views: The onDraw Method</a>\n" +
"						</li>\n" +
"					<li>\n" +
"				<a href=\"http://cogitolearning.co.uk/?p=1449\">QuantLib: Pricing Engines Introduction</a>\n" +
"						</li>\n" +
"					<li>\n" +
"				<a href=\"http://cogitolearning.co.uk/?p=1643\">Android Custom Views: Basics</a>\n" +
"						</li>\n" +
"				</ul>\n" +
"		</div><div id=\"tag_cloud-2\" class=\"sidebar-widget widget_tag_cloud\"> <p class=\"sidebar-headline\">Tags</p><div class=\"tagcloud\"><a href='http://cogitolearning.co.uk/?tag=activity' class='tag-link-58' title='2 topics' style='font-size: 10.065573770492pt;'>activity</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=android-2' class='tag-link-55' title='21 topics' style='font-size: 19.934426229508pt;'>android</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=animation' class='tag-link-64' title='16 topics' style='font-size: 18.672131147541pt;'>animation</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=basics' class='tag-link-9' title='1 topic' style='font-size: 8pt;'>basics</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=boost' class='tag-link-71' title='2 topics' style='font-size: 10.065573770492pt;'>boost</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=c' class='tag-link-6' title='30 topics' style='font-size: 21.655737704918pt;'>c++</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=cashflow' class='tag-link-28' title='5 topics' style='font-size: 13.508196721311pt;'>cashflow</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=control-statements' class='tag-link-11' title='1 topic' style='font-size: 8pt;'>control statements</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=coupons' class='tag-link-31' title='2 topics' style='font-size: 10.065573770492pt;'>coupons</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=currencies' class='tag-link-23' title='4 topics' style='font-size: 12.590163934426pt;'>currencies</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=custom' class='tag-link-80' title='2 topics' style='font-size: 10.065573770492pt;'>custom</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=dates' class='tag-link-25' title='5 topics' style='font-size: 13.508196721311pt;'>dates</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=day-counters' class='tag-link-32' title='1 topic' style='font-size: 8pt;'>day counters</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=design-pattern' class='tag-link-30' title='3 topics' style='font-size: 11.44262295082pt;'>design pattern</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=events' class='tag-link-27' title='1 topic' style='font-size: 8pt;'>events</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=exceptions' class='tag-link-17' title='1 topic' style='font-size: 8pt;'>exceptions</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=exchange-rates' class='tag-link-24' title='2 topics' style='font-size: 10.065573770492pt;'>exchange rates</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=grammar' class='tag-link-48' title='3 topics' style='font-size: 11.44262295082pt;'>grammar</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=if' class='tag-link-10' title='1 topic' style='font-size: 8pt;'>if</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=indices' class='tag-link-36' title='3 topics' style='font-size: 11.44262295082pt;'>indices</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=interpolator' class='tag-link-65' title='2 topics' style='font-size: 10.065573770492pt;'>interpolator</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=introduction' class='tag-link-15' title='2 topics' style='font-size: 10.065573770492pt;'>introduction</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=java' class='tag-link-7' title='12 topics' style='font-size: 17.295081967213pt;'>java</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=macros' class='tag-link-18' title='2 topics' style='font-size: 10.065573770492pt;'>macros</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=manual' class='tag-link-13' title='27 topics' style='font-size: 21.196721311475pt;'>Manual</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=money' class='tag-link-22' title='3 topics' style='font-size: 11.44262295082pt;'>money</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=observable' class='tag-link-39' title='2 topics' style='font-size: 10.065573770492pt;'>observable</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=observer' class='tag-link-38' title='4 topics' style='font-size: 12.590163934426pt;'>observer</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=parser-2' class='tag-link-44' title='8 topics' style='font-size: 15.459016393443pt;'>parser</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=periods' class='tag-link-26' title='1 topic' style='font-size: 8pt;'>periods</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=quantlib-2' class='tag-link-16' title='32 topics' style='font-size: 22pt;'>quantlib</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=quotes' class='tag-link-41' title='2 topics' style='font-size: 10.065573770492pt;'>quotes</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=random-numbers' class='tag-link-72' title='2 topics' style='font-size: 10.065573770492pt;'>random numbers</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=recursive-descent' class='tag-link-49' title='2 topics' style='font-size: 10.065573770492pt;'>recursive descent</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=term-structure' class='tag-link-34' title='2 topics' style='font-size: 10.065573770492pt;'>term structure</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=thread' class='tag-link-57' title='2 topics' style='font-size: 10.065573770492pt;'>thread</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=time-series' class='tag-link-33' title='1 topic' style='font-size: 8pt;'>time series</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=tips' class='tag-link-8' title='1 topic' style='font-size: 8pt;'>tips</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=tracing' class='tag-link-19' title='1 topic' style='font-size: 8pt;'>tracing</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=transition' class='tag-link-67' title='2 topics' style='font-size: 10.065573770492pt;'>transition</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=tree' class='tag-link-50' title='2 topics' style='font-size: 10.065573770492pt;'>tree</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=tutorial' class='tag-link-45' title='26 topics' style='font-size: 20.967213114754pt;'>tutorial</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=typedefs' class='tag-link-21' title='1 topic' style='font-size: 8pt;'>typedefs</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=view' class='tag-link-79' title='2 topics' style='font-size: 10.065573770492pt;'>view</a>\n" +
"<a href='http://cogitolearning.co.uk/?tag=visitor' class='tag-link-29' title='2 topics' style='font-size: 10.065573770492pt;'>visitor</a></div>\n" +
"</div><div id=\"archives-2\" class=\"sidebar-widget widget_archive\"> <p class=\"sidebar-headline\">Archives</p>		<ul>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201503'>March 2015</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201502'>February 2015</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201410'>October 2014</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201403'>March 2014</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201402'>February 2014</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201401'>January 2014</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201312'>December 2013</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201311'>November 2013</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201310'>October 2013</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201309'>September 2013</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201308'>August 2013</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201306'>June 2013</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201305'>May 2013</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201304'>April 2013</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201303'>March 2013</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201302'>February 2013</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201301'>January 2013</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201212'>December 2012</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201211'>November 2012</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201210'>October 2012</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201209'>September 2012</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201208'>August 2012</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201207'>July 2012</a></li>\n" +
"	<li><a href='http://cogitolearning.co.uk/?m=201205'>May 2012</a></li>\n" +
"		</ul>\n" +
"</div></aside> <!-- end of sidebar -->  </div>\n" +
"</div>     <!-- end of wrapper-content -->\n" +
"<footer id=\"wrapper-footer\">\n" +
"  \n" +
"</footer>  <!-- end of wrapper-footer -->\n" +
"</div>\n" +
"</div>\n" +
"		\n" +
"		<script type=\"text/javascript\">\n" +
"			//<![CDATA[\n" +
"			jQuery(document).ready(function() {\n" +
"				\n" +
"				// Edit 09/05: remove globals and package into Object Literal, and removed the debug function\n" +
"				cli_show_cookiebar({\n" +
"					html: '<div id=\"cookie-law-info-bar\"><span>This website uses cookies to improve your experience. We\\'ll assume you\\'re OK with this, but you can opt-out if you wish.<a href=\\\"#\\\" id=\\\"cookie_action_close_header\\\"  class=\\\"medium cli-plugin-button cli-plugin-main-button\\\" >Accept</a></span></div>',\n" +
"					settings: '{\"animate_speed_hide\":\"500\",\"animate_speed_show\":\"500\",\"background\":\"#fff\",\"border\":\"#444\",\"border_on\":true,\"button_1_button_colour\":\"#000\",\"button_1_button_hover\":\"#000000\",\"button_1_link_colour\":\"#fff\",\"button_1_as_button\":true,\"button_2_button_colour\":\"#333\",\"button_2_button_hover\":\"#292929\",\"button_2_link_colour\":\"#444\",\"button_2_as_button\":false,\"font_family\":\"inherit\",\"notify_animate_hide\":true,\"notify_animate_show\":false,\"notify_div_id\":\"#cookie-law-info-bar\",\"notify_position_horizontal\":\"right\",\"notify_position_vertical\":\"bottom\",\"showagain_tab\":false,\"showagain_background\":\"#fff\",\"showagain_border\":\"#000\",\"showagain_div_id\":\"#cookie-law-info-again\",\"showagain_x_position\":\"100px\",\"text\":\"#000\",\"show_once_yn\":false,\"show_once\":\"10000\"}'\n" +
"				});\n" +
"				\n" +
"			});\n" +
"			//]]>\n" +
"		</script>\n" +
"		\n" +
"		<!-- MathJax Latex Plugin installed: Disabled as no shortcodes on this page -->			<script type=\"text/javascript\">\n" +
"				jQuery(document).ready(function($) {\n" +
"					jQuery('#dc_jqaccordion_widget-3-item .menu').dcAccordion({\n" +
"						eventType: 'click',\n" +
"						hoverDelay: 0,\n" +
"						menuClose: false,\n" +
"						autoClose: false,\n" +
"						saveState: true,\n" +
"						autoExpand: true,\n" +
"						classExpand: 'current-menu-item',\n" +
"						classDisable: '',\n" +
"						showCount: false,\n" +
"						disableLink: true,\n" +
"						cookie: 'dc_jqaccordion_widget-3',\n" +
"						speed: 'slow'\n" +
"					});\n" +
"				});\n" +
"			</script>\n" +
"		\n" +
"			<script type='text/javascript' src='http://cogitolearning.co.uk/wp-includes/js/comment-reply.min.js?ver=4.1.5'></script>\n" +
"<script type='text/javascript' src='http://cogitolearning.co.uk/wp-content/themes/foreverwood/js/placeholders.js?ver=2.1.0'></script>\n" +
"<script type='text/javascript' src='http://cogitolearning.co.uk/wp-content/themes/foreverwood/js/scroll-to-top.js?ver=1.0'></script>\n" +
"<script type='text/javascript' src='http://cogitolearning.co.uk/wp-content/themes/foreverwood/js/menubox.js?ver=1.0'></script>\n" +
"<script type='text/javascript' src='http://cogitolearning.co.uk/wp-content/themes/foreverwood/js/selectnav.js?ver=0.1'></script>\n" +
"<script type='text/javascript' src='http://cogitolearning.co.uk/wp-content/themes/foreverwood/js/responsive.js?ver=1.0'></script>\n" +
"\n" +
"<!-- Block (3) START-->\n" +
"<script type=\"text/javascript\">\n" +
"jQuery(\".cogitohighlight\").parent().parent().addClass(\"cogitohighlightdiv\");\n" +
"</script>\n" +
"<!-- Block (3) END -->\n" +
"      \n" +
"</body>\n" +
"</html>";
        tokenizer.tokenize(s, new Tokenizer.TokenHandler() {

            @Override
            public void onToken(Tokenizer.TokenType type, String text) {
                System.out.println("" + type + " " + text);
            }
        });

    }
}
