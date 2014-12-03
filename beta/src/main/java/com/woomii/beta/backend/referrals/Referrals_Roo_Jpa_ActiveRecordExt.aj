package com.woomii.beta.backend.referrals;

privileged aspect Referrals_Roo_Jpa_ActiveRecordExt {

	/* INJECT THIS CODE IN Referrals_Roo_Jpa_ActiveRecord IF YOU EXECUTE ROO SHELL COMMANDS...

    @PersistenceContext(unitName="persistenceUnitProduction")
    transient EntityManager Referrals.entityManager;

	@PersistenceContext(unitName="persistenceUnitSandbox")
    transient EntityManager Referrals.sandBoxEntityManager;

    public static final EntityManager Referrals.entityManager() {
        EntityManager em = new Referrals().entityManager;
        if (em == null) throw new IllegalStateException("Production Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static final EntityManager Referrals.sandboxEntityManager() {
        EntityManager em = new Referrals().sandBoxEntityManager;
        if (em == null) throw new IllegalStateException("Sandbox Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    @Transactional("transactionManagerProduction")
    public Referrals Referrals.mergeProduction() {
        if (this.sandBoxEntityManager == null) this.entityManager = entityManager();
        Referrals merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
	    
    @Transactional("transactionManagerSandbox")
    public Referrals Referrals.mergeSandbox() {
        if (this.sandBoxEntityManager == null) this.sandBoxEntityManager = sandboxEntityManager();
        Referrals merged = this.sandBoxEntityManager.merge(this);
        this.sandBoxEntityManager.flush();
        return merged;
    }

	*/
}
