// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.backend.referrals;

import com.woomii.beta.backend.referrals.Referrals;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

privileged aspect Referrals_Roo_Jpa_Entity {
    
    declare @type: Referrals: @Entity;
    
    declare @type: Referrals: @Table(name = "Referrals");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Referrals.id;
    
    @Version
    @Column(name = "version")
    private Integer Referrals.version;
    
    public Long Referrals.getId() {
        return this.id;
    }
    
    public void Referrals.setId(Long id) {
        this.id = id;
    }
    
    public Integer Referrals.getVersion() {
        return this.version;
    }
    
    public void Referrals.setVersion(Integer version) {
        this.version = version;
    }
    
}