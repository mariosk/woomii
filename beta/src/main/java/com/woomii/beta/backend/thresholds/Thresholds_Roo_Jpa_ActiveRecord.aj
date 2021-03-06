// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.backend.thresholds;

import com.woomii.beta.backend.thresholds.Thresholds;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Thresholds_Roo_Jpa_ActiveRecord {
    
	@PersistenceContext(unitName="persistenceUnitProduction")
    transient EntityManager Thresholds.entityManager;
    
    public static final List<String> Thresholds.fieldNames4OrderClauseFilter = java.util.Arrays.asList("max_referrals", "max_credits");
    
    public static final EntityManager Thresholds.entityManager() {
        EntityManager em = new Thresholds().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Thresholds.countThresholdses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Thresholds o", Long.class).getSingleResult();
    }
    
    public static List<Thresholds> Thresholds.findAllThresholdses() {
        return entityManager().createQuery("SELECT o FROM Thresholds o", Thresholds.class).getResultList();
    }
    
    public static List<Thresholds> Thresholds.findAllThresholdses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Thresholds o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Thresholds.class).getResultList();
    }
    
    public static Thresholds Thresholds.findThresholds(Long id) {
        if (id == null) return null;
        return entityManager().find(Thresholds.class, id);
    }
    
    public static List<Thresholds> Thresholds.findThresholdsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Thresholds o", Thresholds.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Thresholds> Thresholds.findThresholdsEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Thresholds o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Thresholds.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Thresholds.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Thresholds.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Thresholds attached = Thresholds.findThresholds(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Thresholds.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Thresholds.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Thresholds Thresholds.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Thresholds merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
