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

import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author brad
 */
public class SteppingTokenizer implements Tokenizer {

    @Override
    public void tokenize(String str, TokenHandler handler) {
        try {
            HTMLTokenizer t = new HTMLTokenizer(new StringReader(str));
            while (true) {
                int next = t.nextToken();
                switch (next) {
                    case HTMLTokenizer.TT_EOF:
                        return ;
                    case HTMLTokenizer.TT_ENTITY:
                        handler.onToken(RegexTokenizer.TokenType.TEXT, t.getToken());
                        break;
                    case HTMLTokenizer.TT_TAG:
                        String tag = t.getToken();
                        System.out.println("tag: " + tag);
                        if( tag.startsWith("/")) {
                            // close tag
                            String s = "<" + tag + ">";
                            handler.onToken(TokenType.CLOSE_TAG, s);
                        } else if( tag.endsWith("/")) {
                            // self closing
                            String s = "<" + tag + ">";
                            handler.onToken(TokenType.SELF_CLOSE_TAG, s);
                        } else {
                            // open tag
                            String s = "<" + tag + ">";
                            handler.onToken(TokenType.OPEN_TAG, s);
                        }
                        break;
                    case HTMLTokenizer.TT_TEXT:
                        String s = t.getToken();
                        if( s.startsWith("{{#") && s.endsWith("}}") ) {
                            // mustache section start
                            handler.onToken(TokenType.MUSTACHE_OPEN, s);
                        } else if( s.startsWith("{{/") && s.endsWith("}}")) {
                            // mustache section end
                            handler.onToken(TokenType.MUSTACHE_CLOSE, s);
                        } else if( s.startsWith("{{") && s.endsWith("}}") ) {
                            // mustache expression
                            handler.onToken(TokenType.MUSTACHE, s);
                        } else {
                            handler.onToken(RegexTokenizer.TokenType.TEXT, t.getToken());
                        }
                        break;
                }
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
