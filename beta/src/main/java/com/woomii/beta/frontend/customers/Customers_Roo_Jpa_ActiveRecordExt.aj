package com.woomii.beta.frontend.customers;

public aspect Customers_Roo_Jpa_ActiveRecordExt {

	/* INJECT THIS CODE IN Customers_Roo_Jpa_ActiveRecord IF YOU EXECUTE ROO SHELL COMMANDS...
	
    @PersistenceContext(unitName="persistenceUnitProduction")
    transient EntityManager Customers.entityManager;

    @PersistenceContext(unitName="persistenceUnitSandbox")
    transient EntityManager Customers.sandboxEntityManager;

    public static final EntityManager Customers.entityManager() {
        EntityManager em = new Customers().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static final EntityManager Customers.sandboxEntityManager() {
        EntityManager em = new Customers().sandboxEntityManager;
        if (em == null) throw new IllegalStateException("Sandbox entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
	*/
}
