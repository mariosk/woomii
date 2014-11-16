// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.frontend.customers;

import com.woomii.beta.frontend.customers.Customers;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Customers_Roo_Json {
    
    public String Customers.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Customers.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Customers Customers.fromJsonToCustomers(String json) {
        return new JSONDeserializer<Customers>()
        .use(null, Customers.class).deserialize(json);
    }
    
    public static String Customers.toJsonArray(Collection<Customers> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Customers.toJsonArray(Collection<Customers> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Customers> Customers.fromJsonArrayToCustomerses(String json) {
        return new JSONDeserializer<List<Customers>>()
        .use("values", Customers.class).deserialize(json);
    }
    
}