package com.woomii.beta.backend.impressions;

privileged aspect Impressions_Roo_Jpa_ActiveRecordExt {

	/* INJECT THIS CODE IN Impressions_Roo_Jpa_ActiveRecord IF YOU EXECUTE ROO SHELL COMMANDS...
	
    @PersistenceContext(unitName="persistenceUnitProduction")
    transient EntityManager Impressions.entityManager;

    @PersistenceContext(unitName="persistenceUnitSandbox")
    transient EntityManager Impressions.sandboxEntityManager;

    public static final EntityManager Impressions.entityManager() {
        EntityManager em = new Impressions().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static final EntityManager Impressions.sandboxEntityManager() {
        EntityManager em = new Impressions().sandboxEntityManager;
        if (em == null) throw new IllegalStateException("Sandbox entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    @Transactional("transactionManagerProduction")
    public Impressions Impressions.mergeProduction() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Impressions merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    @Transactional("transactionManagerSandbox")
    public Impressions Impressions.mergeSandbox() {
        if (this.sandboxEntityManager == null) this.sandboxEntityManager = sandboxEntityManager();
        Impressions merged = this.sandboxEntityManager.merge(this);
        this.sandboxEntityManager.flush();
        return merged;
    }
    
	*/   
    
    
}
