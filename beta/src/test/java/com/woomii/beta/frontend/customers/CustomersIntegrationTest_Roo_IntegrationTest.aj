// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.frontend.customers;

import com.woomii.beta.frontend.customers.Customers;
import com.woomii.beta.frontend.customers.CustomersDataOnDemand;
import com.woomii.beta.frontend.customers.CustomersIntegrationTest;
import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect CustomersIntegrationTest_Roo_IntegrationTest {
    
    declare @type: CustomersIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: CustomersIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: CustomersIntegrationTest: @Transactional;
    
    @Autowired
    CustomersDataOnDemand CustomersIntegrationTest.dod;
    
    @Test
    public void CustomersIntegrationTest.testCountCustomerses() {
        Assert.assertNotNull("Data on demand for 'Customers' failed to initialize correctly", dod.getRandomCustomers());
        long count = Customers.countCustomerses();
        Assert.assertTrue("Counter for 'Customers' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void CustomersIntegrationTest.testFindCustomers() {
        Customers obj = dod.getRandomCustomers();
        Assert.assertNotNull("Data on demand for 'Customers' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Customers' failed to provide an identifier", id);
        obj = Customers.findCustomers(id);
        Assert.assertNotNull("Find method for 'Customers' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Customers' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void CustomersIntegrationTest.testFindAllCustomerses() {
        Assert.assertNotNull("Data on demand for 'Customers' failed to initialize correctly", dod.getRandomCustomers());
        long count = Customers.countCustomerses();
        Assert.assertTrue("Too expensive to perform a find all test for 'Customers', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Customers> result = Customers.findAllCustomerses();
        Assert.assertNotNull("Find all method for 'Customers' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Customers' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void CustomersIntegrationTest.testFindCustomersEntries() {
        Assert.assertNotNull("Data on demand for 'Customers' failed to initialize correctly", dod.getRandomCustomers());
        long count = Customers.countCustomerses();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Customers> result = Customers.findCustomersEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Customers' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Customers' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void CustomersIntegrationTest.testFlush() {
        Customers obj = dod.getRandomCustomers();
        Assert.assertNotNull("Data on demand for 'Customers' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Customers' failed to provide an identifier", id);
        obj = Customers.findCustomers(id);
        Assert.assertNotNull("Find method for 'Customers' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyCustomers(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Customers' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void CustomersIntegrationTest.testMergeUpdate() {
        Customers obj = dod.getRandomCustomers();
        Assert.assertNotNull("Data on demand for 'Customers' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Customers' failed to provide an identifier", id);
        obj = Customers.findCustomers(id);
        boolean modified =  dod.modifyCustomers(obj);
        Integer currentVersion = obj.getVersion();
        Customers merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Customers' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void CustomersIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'Customers' failed to initialize correctly", dod.getRandomCustomers());
        Customers obj = dod.getNewTransientCustomers(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Customers' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Customers' identifier to be null", obj.getId());
        try {
            obj.persist();
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        obj.flush();
        Assert.assertNotNull("Expected 'Customers' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void CustomersIntegrationTest.testRemove() {
        Customers obj = dod.getRandomCustomers();
        Assert.assertNotNull("Data on demand for 'Customers' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Customers' failed to provide an identifier", id);
        obj = Customers.findCustomers(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Customers' with identifier '" + id + "'", Customers.findCustomers(id));
    }
    
}