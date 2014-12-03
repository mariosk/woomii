package com.woomii.beta.frontend.campaigns;

public aspect Campaigns_Roo_Jpa_ActiveRecordExt {

	/* INJECT THIS CODE IN Campaigns_Roo_Jpa_ActiveRecord IF YOU EXECUTE ROO SHELL COMMANDS...
	
    @PersistenceContext(unitName="persistenceUnitProduction")
    transient EntityManager Campaigns.entityManager;

    @PersistenceContext(unitName="persistenceUnitSandbox")
    transient EntityManager Campaigns.sandboxEntityManager;

    public static final EntityManager Campaigns.entityManager() {
        EntityManager em = new Campaigns().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static final EntityManager Campaigns.sandboxEntityManager() {
        EntityManager em = new Campaigns().sandboxEntityManager;
        if (em == null) throw new IllegalStateException("Sandbox entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
	*/    
}
