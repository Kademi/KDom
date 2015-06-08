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

import java.util.function.Consumer;

/**
 *
 * @author brad
 */
public class ExpressionTokeniser {

    public static final String START = "{{";
    public static final String FINISH = "}}";

    public void tokenise(String text, Consumer<String> expr, Consumer<String> constant) {
        boolean done = false;
        boolean inTag = false;
        int pos = 0;
        int st = 0;
        int fn = 0;
        while (!done) {
            if (inTag) {
                st = pos;
                fn = text.indexOf(FINISH, st);
                if (fn >= 0) {
                    // got an expression
                    String s = text.substring(st + START.length(), fn);
                    expr.accept(s);
                    pos = fn + FINISH.length();
                    inTag = false;
                } else {
                    // did not find a finish, invalid syntax, return all as a constant
                    constant.accept(text.substring(st));
                    return;
                }
            } else {
                st = text.indexOf(START, pos);
                if (st >= 0) {
                    inTag = true;
                    if (st > pos) {
                        // we've found a constant
                        String s = text.substring(pos, st);
                        constant.accept(s);
                        pos = st;
                    }
                } else {
                    // did not a start tag, so just take all remaining as a constant
                    String s = text.substring(pos);
                    constant.accept(s);
                    return;
                }
            }
        }
    }

}
