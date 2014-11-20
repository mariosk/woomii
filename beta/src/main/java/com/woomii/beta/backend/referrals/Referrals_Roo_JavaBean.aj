// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.backend.referrals;

import com.woomii.beta.backend.referrals.Referrals;
import com.woomii.beta.frontend.apps.Apps;
import com.woomii.beta.frontend.campaigns.Campaigns;
import java.util.Date;

privileged aspect Referrals_Roo_JavaBean {
    
    public Campaigns Referrals.getCampaign() {
        return this.campaign;
    }
    
    public void Referrals.setCampaign(Campaigns campaign) {
        this.campaign = campaign;
    }
    
    public Apps Referrals.getApp() {
        return this.app;
    }
    
    public void Referrals.setApp(Apps app) {
        this.app = app;
    }
    
    public String Referrals.getAff_id() {
        return this.aff_id;
    }
    
    public void Referrals.setAff_id(String aff_id) {
        this.aff_id = aff_id;
    }
    
    public String Referrals.getUuid_a() {
        return this.uuid_a;
    }
    
    public void Referrals.setUuid_a(String uuid_a) {
        this.uuid_a = uuid_a;
    }
    
    public String Referrals.getUuid_b() {
        return this.uuid_b;
    }
    
    public void Referrals.setUuid_b(String uuid_b) {
        this.uuid_b = uuid_b;
    }
    
    public String Referrals.getUa_b() {
        return this.ua_b;
    }
    
    public void Referrals.setUa_b(String ua_b) {
        this.ua_b = ua_b;
    }
    
    public short Referrals.getSuggested_friends() {
        return this.suggested_friends;
    }
    
    public void Referrals.setSuggested_friends(short suggested_friends) {
        this.suggested_friends = suggested_friends;
    }
    
    public Date Referrals.getCreated() {
        return this.created;
    }
    
    public void Referrals.setCreated(Date created) {
        this.created = created;
    }
    
    public Boolean Referrals.getSandbox_mode() {
        return this.sandbox_mode;
    }
    
    public void Referrals.setSandbox_mode(Boolean sandbox_mode) {
        this.sandbox_mode = sandbox_mode;
    }
    
}
