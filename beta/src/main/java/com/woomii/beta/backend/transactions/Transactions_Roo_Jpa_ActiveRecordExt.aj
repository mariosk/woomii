package com.woomii.beta.backend.transactions;

privileged aspect Transactions_Roo_Jpa_ActiveRecordExt {

	/* INJECT THIS CODE IN Transactions_Roo_Jpa_ActiveRecord IF YOU EXECUTE ROO SHELL COMMANDS...
    @PersistenceContext(unitName="persistenceUnitProduction")
    transient EntityManager Transactions.entityManager;

    @PersistenceContext(unitName="persistenceUnitSandbox")
    transient EntityManager Transactions.sandBoxEntityManager;
    
    public static final EntityManager Transactions.entityManager() {
        EntityManager em = new Transactions().entityManager;
        if (em == null) throw new IllegalStateException("Production Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
     
    public static final EntityManager Transactions.sandboxEntityManager() {
        EntityManager em = new Transactions().sandBoxEntityManager;
        if (em == null) throw new IllegalStateException("Sandbox Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    @Transactional("transactionManagerProduction")
    public Transactions Transactions.mergeProduction() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Transactions merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }       
    
    @Transactional("transactionManagerSandbox")
    public Transactions Transactions.mergeSandbox() {
        if (this.sandBoxEntityManager == null) this.sandBoxEntityManager = sandboxEntityManager();
        Transactions merged = this.sandBoxEntityManager.merge(this);
        this.sandBoxEntityManager.flush();
        return merged;
    }
    */
    
}
