// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.backend.transactions;

import com.woomii.beta.backend.transactions.Transactions;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

privileged aspect Transactions_Roo_Jpa_Entity {
    
    declare @type: Transactions: @Entity;
    
    declare @type: Transactions: @Table(name = "Transactions");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Transactions.id;
    
    @Version
    @Column(name = "version")
    private Integer Transactions.version;
    
    public Long Transactions.getId() {
        return this.id;
    }
    
    public void Transactions.setId(Long id) {
        this.id = id;
    }
    
    public Integer Transactions.getVersion() {
        return this.version;
    }
    
    public void Transactions.setVersion(Integer version) {
        this.version = version;
    }
    
}
