// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.backend.transactions;

import com.woomii.beta.backend.transactions.Transactions;
import com.woomii.beta.backend.transactions.TransactionsDataOnDemand;
import com.woomii.beta.backend.transactions.TransactionsIntegrationTest;
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

privileged aspect TransactionsIntegrationTest_Roo_IntegrationTest {
    
    declare @type: TransactionsIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: TransactionsIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: TransactionsIntegrationTest: @Transactional;
    
    @Autowired
    TransactionsDataOnDemand TransactionsIntegrationTest.dod;
    
    @Test
    public void TransactionsIntegrationTest.testCountTransactionses() {
        Assert.assertNotNull("Data on demand for 'Transactions' failed to initialize correctly", dod.getRandomTransactions());
        long count = Transactions.countTransactionses();
        Assert.assertTrue("Counter for 'Transactions' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void TransactionsIntegrationTest.testFindTransactions() {
        Transactions obj = dod.getRandomTransactions();
        Assert.assertNotNull("Data on demand for 'Transactions' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Transactions' failed to provide an identifier", id);
        obj = Transactions.findTransactions(id);
        Assert.assertNotNull("Find method for 'Transactions' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Transactions' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void TransactionsIntegrationTest.testFindAllTransactionses() {
        Assert.assertNotNull("Data on demand for 'Transactions' failed to initialize correctly", dod.getRandomTransactions());
        long count = Transactions.countTransactionses();
        Assert.assertTrue("Too expensive to perform a find all test for 'Transactions', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Transactions> result = Transactions.findAllTransactionses();
        Assert.assertNotNull("Find all method for 'Transactions' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Transactions' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void TransactionsIntegrationTest.testFindTransactionsEntries() {
        Assert.assertNotNull("Data on demand for 'Transactions' failed to initialize correctly", dod.getRandomTransactions());
        long count = Transactions.countTransactionses();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Transactions> result = Transactions.findTransactionsEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Transactions' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Transactions' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void TransactionsIntegrationTest.testFlush() {
        Transactions obj = dod.getRandomTransactions();
        Assert.assertNotNull("Data on demand for 'Transactions' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Transactions' failed to provide an identifier", id);
        obj = Transactions.findTransactions(id);
        Assert.assertNotNull("Find method for 'Transactions' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyTransactions(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Transactions' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void TransactionsIntegrationTest.testMergeUpdate() {
        Transactions obj = dod.getRandomTransactions();
        Assert.assertNotNull("Data on demand for 'Transactions' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Transactions' failed to provide an identifier", id);
        obj = Transactions.findTransactions(id);
        boolean modified =  dod.modifyTransactions(obj);
        Integer currentVersion = obj.getVersion();
        Transactions merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Transactions' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void TransactionsIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'Transactions' failed to initialize correctly", dod.getRandomTransactions());
        Transactions obj = dod.getNewTransientTransactions(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Transactions' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Transactions' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'Transactions' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void TransactionsIntegrationTest.testRemove() {
        Transactions obj = dod.getRandomTransactions();
        Assert.assertNotNull("Data on demand for 'Transactions' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Transactions' failed to provide an identifier", id);
        obj = Transactions.findTransactions(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Transactions' with identifier '" + id + "'", Transactions.findTransactions(id));
    }
    
}
