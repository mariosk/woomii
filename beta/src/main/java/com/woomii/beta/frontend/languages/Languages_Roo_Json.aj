// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.frontend.languages;

import com.woomii.beta.frontend.languages.Languages;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Languages_Roo_Json {
    
    public String Languages.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Languages.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Languages Languages.fromJsonToLanguages(String json) {
        return new JSONDeserializer<Languages>()
        .use(null, Languages.class).deserialize(json);
    }
    
    public static String Languages.toJsonArray(Collection<Languages> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Languages.toJsonArray(Collection<Languages> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Languages> Languages.fromJsonArrayToLanguageses(String json) {
        return new JSONDeserializer<List<Languages>>()
        .use("values", Languages.class).deserialize(json);
    }
    
}