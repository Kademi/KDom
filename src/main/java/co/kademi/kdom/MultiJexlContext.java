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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.jexl2.JexlContext;

/**
 *
 * @author brad
 */
public class MultiJexlContext implements JexlContext {

    public static MultiJexlContext build(JexlContext ... contexts) {
        List<JexlContext> list = new ArrayList<>();
        list.addAll(Arrays.asList(contexts));
        return new MultiJexlContext(list);
    }

    private List<JexlContext> list;

    public MultiJexlContext(List<JexlContext> list) {
        this.list = list;
    }


    @Override
    public Object get(String name) {
        System.out.println("multi get: " + name);
        for( JexlContext c : list ) {
            Object o = c.get(name);
            if( o != null ) {
                return o;
            }
        }
        return null;
    }

    @Override
    public void set(String name, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean has(String name) {
        System.out.println("multi has: " + name + " ?");
        for( JexlContext c : list ) {
            if( c.has(name)) {
                System.out.println("yes");
                return true;
            }
        }
        System.out.println("  no");
        return false;
    }

}
