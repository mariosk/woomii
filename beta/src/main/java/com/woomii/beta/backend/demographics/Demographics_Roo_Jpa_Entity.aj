// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.backend.demographics;

import com.woomii.beta.backend.demographics.Demographics;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

privileged aspect Demographics_Roo_Jpa_Entity {
    
    declare @type: Demographics: @Entity;
    
    declare @type: Demographics: @Table(name = "Demographics");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Demographics.id;
    
    @Version
    @Column(name = "version")
    private Integer Demographics.version;
    
    public Long Demographics.getId() {
        return this.id;
    }
    
    public void Demographics.setId(Long id) {
        this.id = id;
    }
    
    public Integer Demographics.getVersion() {
        return this.version;
    }
    
    public void Demographics.setVersion(Integer version) {
        this.version = version;
    }
    
}
