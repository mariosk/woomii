// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.frontend.translations;

import com.woomii.beta.frontend.translations.Translations;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

privileged aspect Translations_Roo_Jpa_Entity {
    
    declare @type: Translations: @Entity;
    
    declare @type: Translations: @Table(name = "Translations");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Translations.id;
    
    @Version
    @Column(name = "version")
    private Integer Translations.version;
    
    public Long Translations.getId() {
        return this.id;
    }
    
    public void Translations.setId(Long id) {
        this.id = id;
    }
    
    public Integer Translations.getVersion() {
        return this.version;
    }
    
    public void Translations.setVersion(Integer version) {
        this.version = version;
    }
    
}
