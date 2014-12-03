package com.woomii.beta.frontend.apps;

privileged aspect Apps_Roo_Jpa_ActiveRecordExt {
	
	/* INJECT THIS CODE IN Apps_Roo_Jpa_ActiveRecord IF YOU EXECUTE ROO SHELL COMMANDS...
	
    @PersistenceContext(unitName="persistenceUnitProduction")
    transient EntityManager Apps.entityManager;

    @PersistenceContext(unitName="persistenceUnitSandbox")
    transient EntityManager Apps.sandboxEntityManager;

    public static final EntityManager Apps.entityManager() {
        EntityManager em = new Apps().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static final EntityManager Apps.sandboxEntityManager() {
        EntityManager em = new Apps().sandboxEntityManager;
        if (em == null) throw new IllegalStateException("Sandbox entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
	*/
    
}
