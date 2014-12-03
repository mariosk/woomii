package com.woomii.beta.frontend.languages;

public aspect Languages_Roo_Jpa_ActiveRecordExt {

	/* INJECT THIS CODE IN Languages_Roo_Jpa_ActiveRecord IF YOU EXECUTE ROO SHELL COMMANDS...
    @PersistenceContext(unitName="persistenceUnitProduction")
    transient EntityManager Languages.entityManager;

    @PersistenceContext(unitName="persistenceUnitSandbox")
    transient EntityManager Languages.sandBoxEntityManager;

    public static final EntityManager Languages.entityManager() {
        EntityManager em = new Languages().entityManager;
        if (em == null) throw new IllegalStateException("Production Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static final EntityManager Languages.sandboxEntityManager() {
        EntityManager em = new Languages().sandBoxEntityManager;
        if (em == null) throw new IllegalStateException("Sandbox Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
	*/    
}
