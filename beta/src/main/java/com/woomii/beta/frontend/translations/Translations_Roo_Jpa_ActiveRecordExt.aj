package com.woomii.beta.frontend.translations;

public aspect Translations_Roo_Jpa_ActiveRecordExt {
    
	/* INJECT THIS CODE IN Translations_Roo_Jpa_ActiveRecord IF YOU EXECUTE ROO SHELL COMMANDS...
    @PersistenceContext(unitName="persistenceUnitProduction")
    transient EntityManager Translations.entityManager;

	@PersistenceContext(unitName="persistenceUnitSandbox")
	transient EntityManager Translations.sandBoxEntityManager;
    
    public static final EntityManager Translations.entityManager() {
        EntityManager em = new Translations().entityManager;
        if (em == null) throw new IllegalStateException("Production Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
	
    public static final EntityManager Translations.sandboxEntityManager() {
        EntityManager em = new Translations().sandBoxEntityManager;
        if (em == null) throw new IllegalStateException("Sandbox Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    */
    
}
