// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.frontend.apps;

import com.woomii.beta.frontend.apps.Apps;
import com.woomii.beta.frontend.customers.Customers;
import java.util.Date;

privileged aspect Apps_Roo_JavaBean {
    
    public String Apps.getApp_id() {
        return this.app_id;
    }
    
    public void Apps.setApp_id(String app_id) {
        this.app_id = app_id;
    }
    
    public Customers Apps.getCustomer() {
        return this.customer;
    }
    
    public void Apps.setCustomer(Customers customer) {
        this.customer = customer;
    }
    
    public String Apps.getBundle_id() {
        return this.bundle_id;
    }
    
    public void Apps.setBundle_id(String bundle_id) {
        this.bundle_id = bundle_id;
    }
    
    public String Apps.getSdk_version() {
        return this.sdk_version;
    }
    
    public void Apps.setSdk_version(String sdk_version) {
        this.sdk_version = sdk_version;
    }
    
    public Date Apps.getCreated() {
        return this.created;
    }
    
    public void Apps.setCreated(Date created) {
        this.created = created;
    }
    
    public float Apps.getRate() {
        return this.rate;
    }
    
    public void Apps.setRate(float rate) {
        this.rate = rate;
    }
    
    public String Apps.getDescription() {
        return this.description;
    }
    
    public void Apps.setDescription(String description) {
        this.description = description;
    }
    
    public byte[] Apps.getLogo() {
        return this.logo;
    }
    
    public void Apps.setLogo(byte[] logo) {
        this.logo = logo;
    }
    
}
