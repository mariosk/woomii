// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.backend.impressions;

import com.woomii.beta.backend.impressions.Impressions;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Impressions_Roo_Json {
    
    public String Impressions.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Impressions.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Impressions Impressions.fromJsonToImpressions(String json) {
        return new JSONDeserializer<Impressions>()
        .use(null, Impressions.class).deserialize(json);
    }
    
    public static String Impressions.toJsonArray(Collection<Impressions> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Impressions.toJsonArray(Collection<Impressions> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Impressions> Impressions.fromJsonArrayToImpressionses(String json) {
        return new JSONDeserializer<List<Impressions>>()
        .use("values", Impressions.class).deserialize(json);
    }
    
}
