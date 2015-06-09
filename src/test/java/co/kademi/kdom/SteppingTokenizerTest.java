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

import co.kademi.kdom.Tokenizer.TokenHandler;
import org.junit.Test;

/**
 *
 * @author brad
 */
public class SteppingTokenizerTest {

    public SteppingTokenizerTest() {
    }

    @Test
    public void testTokeniseMath() {
        SteppingTokenizer tokenizer = new SteppingTokenizer();

        tokenizer.tokenize("<html><body class='mybody'><p>abc 123 ,&;!-</p><p>!a</p><br/></body></html>", new TokenHandler() {

            @Override
            public void onToken(RegexTokenizer.TokenType type, String text) {
                System.out.println("token: " + type + ": " + text);
            }
        });


    }

    @Test
    public void testTokeniseMustacheHtml() {
        SteppingTokenizer tokenizer = new SteppingTokenizer();
        TokenHandler handler = new TokenHandler() {

            @Override
            public void onToken(RegexTokenizer.TokenType type, String text) {
                System.out.println("" + type + " " + text);
            }
        };

        tokenizer.tokenize("<!DOCTYPE html>\n<html><title>XXX</title><body class='main'>{{p.name}}BBB{{#person}}XX{{/person}}<br/>AAA\n</body></html>", handler);

    }
}
