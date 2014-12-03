// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.backend.demographics;

import com.woomii.beta.backend.demographics.Demographics;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Demographics_Roo_Jpa_ActiveRecord {
    
	@PersistenceContext(unitName="persistenceUnitProduction")
    transient EntityManager Demographics.entityManager;
    
    public static final List<String> Demographics.fieldNames4OrderClauseFilter = java.util.Arrays.asList("enduser", "sex", "age", "name", "fb_id", "mobile", "email");
    
    public static final EntityManager Demographics.entityManager() {
        EntityManager em = new Demographics().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Demographics.countDemographicses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Demographics o", Long.class).getSingleResult();
    }
    
    public static List<Demographics> Demographics.findAllDemographicses() {
        return entityManager().createQuery("SELECT o FROM Demographics o", Demographics.class).getResultList();
    }
    
    public static List<Demographics> Demographics.findAllDemographicses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Demographics o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Demographics.class).getResultList();
    }
    
    public static Demographics Demographics.findDemographics(Long id) {
        if (id == null) return null;
        return entityManager().find(Demographics.class, id);
    }
    
    public static List<Demographics> Demographics.findDemographicsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Demographics o", Demographics.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Demographics> Demographics.findDemographicsEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Demographics o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Demographics.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Demographics.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Demographics.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Demographics attached = Demographics.findDemographics(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Demographics.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Demographics.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Demographics Demographics.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Demographics merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
