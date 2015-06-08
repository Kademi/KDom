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

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jdk.nashorn.internal.runtime.ParserException;

/**
 *
 * @author brad
 */
public class Tokenizer {

    public enum TokenType {

        DECLARATION,
        OPEN_TAG,
        CLOSE_TAG,
        SELF_CLOSE_TAG,
        MUSTACHE,
        TEXT, MUSTACHE_OPEN, MUSTACHE_CLOSE
    }

    private LinkedList<TokenInfo> tokenInfos;

    public Tokenizer() {
        tokenInfos = new LinkedList<>();
        add("<![-/[/]a-zA-Z0-9 \\._]*>", TokenType.DECLARATION);
        add("<[a-zA-Z][a-zA-Z0-9_ ='/\"-\\.]*/>", TokenType.SELF_CLOSE_TAG); //warning, must be higher then OPEN_TAG
        add("<[a-zA-Z][a-zA-Z0-9_ ='/\"-\\.]*>", TokenType.OPEN_TAG);
        add("</[a-zA-Z][a-zA-Z0-9_]*>", TokenType.CLOSE_TAG);
        add("\\{\\{[a-zA-Z][a-zA-Z0-9_()'\"-/ \\.]*\\}\\}", TokenType.MUSTACHE);
        add("\\{\\{#[a-zA-Z][ a-zA-Z0-9_()'\"-/ \\.]*\\}\\}", TokenType.MUSTACHE_OPEN);
        add("\\{\\{/[a-zA-Z][ a-zA-Z0-9_()'\"-/ \\.]*\\}\\}", TokenType.MUSTACHE_CLOSE);
        add("[a-zA-Z][a-zA-Z0-9_]*", TokenType.TEXT);

    }

    public final void add(String regex, TokenType token) {
        tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), token));
    }

    public void tokenize(String str, TokenHandler handler) {
        String s = str.trim();
        LinkedList<Token> tokens = new LinkedList<>();
        while (!s.equals("")) {
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;
                    String tok = m.group().trim();
                    s = m.replaceFirst("").trim();
                    TokenType type = info.type;
                    String text = tok;
                    handler.onToken(type, text);
                    break;
                }
            }
            if (!match) {
                throw new ParserException("Unexpected character in input: " + s);
            }
        }
    }

    public interface TokenHandler {
        void onToken(TokenType type, String text);
    }

    private class TokenInfo {

        public final Pattern regex;
        public final TokenType type;

        public TokenInfo(Pattern regex, TokenType type) {
            super();
            this.regex = regex;
            this.type = type;
        }
    }

    public class Token {

        public final TokenType type;
        public final String sequence;

        public Token(TokenType token, String sequence) {
            super();
            this.type = token;
            this.sequence = sequence;
        }

    }
}
