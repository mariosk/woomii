// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.backend.referrals;

import com.woomii.beta.backend.referrals.Referrals;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Referrals_Roo_Json {
    
    public String Referrals.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Referrals.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Referrals Referrals.fromJsonToReferrals(String json) {
        return new JSONDeserializer<Referrals>()
        .use(null, Referrals.class).deserialize(json);
    }
    
    public static String Referrals.toJsonArray(Collection<Referrals> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Referrals.toJsonArray(Collection<Referrals> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Referrals> Referrals.fromJsonArrayToReferralses(String json) {
        return new JSONDeserializer<List<Referrals>>()
        .use("values", Referrals.class).deserialize(json);
    }
    
}
