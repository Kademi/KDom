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

/**
 *
 * @author brad
 */
public interface Tokenizer {

    public enum TokenType {

        DECLARATION,
        OPEN_TAG,
        CLOSE_TAG,
        SELF_CLOSE_TAG,
        MUSTACHE,
        TEXT, MUSTACHE_OPEN, MUSTACHE_CLOSE
    }

    void tokenize(String str, TokenHandler handler);

    public interface TokenHandler {
        /**
         *
         * @param type - the type of the token
         * @param text - for a tag, this should be the whole tag, eg <body class='abc'>
         */
        void onToken(TokenType type, String text);
    }
}
