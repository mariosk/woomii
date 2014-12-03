package com.woomii.beta.backend.endusers;

privileged aspect EndUsers_Roo_Jpa_ActiveRecordExt {

	/* INJECT THIS CODE IN EndUsers_Roo_Jpa_ActiveRecord IF YOU EXECUTE ROO SHELL COMMANDS...

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
    
    */
    
}
