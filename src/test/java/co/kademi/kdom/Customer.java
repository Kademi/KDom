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
import java.util.List;

/**
 *
 * @author brad
 */
public class Customer {
    private String firstName;
    private String surName;
    private List<Address> addresses = new ArrayList<>();

    public Customer(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
        addresses.add(new Address("123 Somewhere St", null, null, "Sydney", "Australia"));
        addresses.add(new Address("555 Somewhereelse St", null, null, "Melbourne", "Australia"));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

}
