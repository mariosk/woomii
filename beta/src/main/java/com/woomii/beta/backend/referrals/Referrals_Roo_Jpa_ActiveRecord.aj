// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.backend.referrals;

import com.woomii.beta.backend.referrals.Referrals;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Referrals_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Referrals.entityManager;
    
    public static final List<String> Referrals.fieldNames4OrderClauseFilter = java.util.Arrays.asList("campaign", "app", "aff_id", "uuid_a", "uuid_b", "ua_b", "suggested_friends", "created");
    
    public static final EntityManager Referrals.entityManager() {
        EntityManager em = new Referrals().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Referrals.countReferralses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Referrals o", Long.class).getSingleResult();
    }
    
    public static List<Referrals> Referrals.findAllReferralses() {
        return entityManager().createQuery("SELECT o FROM Referrals o", Referrals.class).getResultList();
    }
    
    public static List<Referrals> Referrals.findAllReferralses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Referrals o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Referrals.class).getResultList();
    }
    
    public static Referrals Referrals.findReferrals(Long id) {
        if (id == null) return null;
        return entityManager().find(Referrals.class, id);
    }
    
    public static List<Referrals> Referrals.findReferralsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Referrals o", Referrals.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Referrals> Referrals.findReferralsEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Referrals o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Referrals.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Referrals.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Referrals.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Referrals attached = Referrals.findReferrals(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Referrals.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Referrals.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Referrals Referrals.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Referrals merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
