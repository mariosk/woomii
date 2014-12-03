// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.backend.endusers;

import com.woomii.beta.backend.endusers.EndUsers;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect EndUsers_Roo_Jpa_ActiveRecord {
    
	@PersistenceContext(unitName="persistenceUnitProduction")
    transient EntityManager EndUsers.entityManager;

    @PersistenceContext(unitName="persistenceUnitSandbox")
    transient EntityManager EndUsers.sandBoxEntityManager;
    
    public static final EntityManager EndUsers.entityManager() {
        EntityManager em = new EndUsers().entityManager;
        if (em == null) throw new IllegalStateException("Production Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static final EntityManager EndUsers.sandboxEntityManager() {
        EntityManager em = new EndUsers().sandBoxEntityManager;
        if (em == null) throw new IllegalStateException("Sandbox Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
        
    @Transactional("transactionManagerProduction")
    public EndUsers EndUsers.mergeProduction() {
        if (this.entityManager == null) this.entityManager = entityManager();
        EndUsers merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
           
    @Transactional("transactionManagerSandbox")
    public EndUsers EndUsers.mergeSandbox() {
        if (this.sandBoxEntityManager == null) this.sandBoxEntityManager = sandboxEntityManager();
        EndUsers merged = this.sandBoxEntityManager.merge(this);
        this.sandBoxEntityManager.flush();
        return merged;
    }
        
    public static final List<String> EndUsers.fieldNames4OrderClauseFilter = java.util.Arrays.asList("uuid", "app", "pin", "app_installed");
       
    public static long EndUsers.countEndUserses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM EndUsers o", Long.class).getSingleResult();
    }
    
    public static List<EndUsers> EndUsers.findAllEndUserses() {
        return entityManager().createQuery("SELECT o FROM EndUsers o", EndUsers.class).getResultList();
    }
    
    public static List<EndUsers> EndUsers.findAllEndUserses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM EndUsers o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, EndUsers.class).getResultList();
    }
    
    public static EndUsers EndUsers.findEndUsers(Long id) {
        if (id == null) return null;
        return entityManager().find(EndUsers.class, id);
    }
    
    public static List<EndUsers> EndUsers.findEndUsersEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM EndUsers o", EndUsers.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<EndUsers> EndUsers.findEndUsersEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM EndUsers o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, EndUsers.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void EndUsers.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void EndUsers.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            EndUsers attached = EndUsers.findEndUsers(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void EndUsers.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void EndUsers.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public EndUsers EndUsers.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        EndUsers merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
