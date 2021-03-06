// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.frontend.apps;

import com.woomii.beta.frontend.apps.Apps;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Apps_Roo_Json {
    
    public String Apps.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Apps.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Apps Apps.fromJsonToApps(String json) {
        return new JSONDeserializer<Apps>()
        .use(null, Apps.class).deserialize(json);
    }
    
    public static String Apps.toJsonArray(Collection<Apps> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Apps.toJsonArray(Collection<Apps> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Apps> Apps.fromJsonArrayToAppses(String json) {
        return new JSONDeserializer<List<Apps>>()
        .use("values", Apps.class).deserialize(json);
    }
    
}
