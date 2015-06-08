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

import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.ObjectContext;

/**
 *
 * @author brad
 */
public class NestableJexlContext implements JexlContext {

    public static NestableJexlContext wrap(Object newRoot, JexlContext parent, JexlEngine engine) {
        return new NestableJexlContext(parent, new ObjectContext(engine, newRoot));
    }

    private final JexlContext parent;
    private final JexlContext child;

    public NestableJexlContext(JexlContext parent, JexlContext child) {
        this.parent = parent;
        this.child = child;
    }



    @Override
    public Object get(String name) {
        if( name.equals("parent")) {
            return parent;
        } else {
            return child.get(name);
        }
    }

    @Override
    public void set(String name, Object value) {
        throw new RuntimeException("unsupported operation");
    }

    @Override
    public boolean has(String name) {
        if( name.equals("parent")) {
            return parent != null;
        } else {
            return child.has(name);
        }
    }

    public JexlContext getChild() {
        return child;
    }

    public JexlContext getParent() {
        return parent;
    }

}
