// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.frontend.campaigns;

import com.woomii.beta.frontend.campaigns.Campaigns;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Campaigns_Roo_Json {
    
    public String Campaigns.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Campaigns.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Campaigns Campaigns.fromJsonToCampaigns(String json) {
        return new JSONDeserializer<Campaigns>()
        .use(null, Campaigns.class).deserialize(json);
    }
    
    public static String Campaigns.toJsonArray(Collection<Campaigns> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Campaigns.toJsonArray(Collection<Campaigns> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Campaigns> Campaigns.fromJsonArrayToCampaignses(String json) {
        return new JSONDeserializer<List<Campaigns>>()
        .use("values", Campaigns.class).deserialize(json);
    }
    
}
