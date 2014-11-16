// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.backend.referrals;

import com.woomii.beta.backend.referrals.Referrals;
import com.woomii.beta.backend.referrals.ReferralsDataOnDemand;
import com.woomii.beta.backend.referrals.ReferralsIntegrationTest;
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

privileged aspect ReferralsIntegrationTest_Roo_IntegrationTest {
    
    declare @type: ReferralsIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: ReferralsIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: ReferralsIntegrationTest: @Transactional;
    
    @Autowired
    ReferralsDataOnDemand ReferralsIntegrationTest.dod;
    
    @Test
    public void ReferralsIntegrationTest.testCountReferralses() {
        Assert.assertNotNull("Data on demand for 'Referrals' failed to initialize correctly", dod.getRandomReferrals());
        long count = Referrals.countReferralses();
        Assert.assertTrue("Counter for 'Referrals' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void ReferralsIntegrationTest.testFindReferrals() {
        Referrals obj = dod.getRandomReferrals();
        Assert.assertNotNull("Data on demand for 'Referrals' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Referrals' failed to provide an identifier", id);
        obj = Referrals.findReferrals(id);
        Assert.assertNotNull("Find method for 'Referrals' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Referrals' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void ReferralsIntegrationTest.testFindAllReferralses() {
        Assert.assertNotNull("Data on demand for 'Referrals' failed to initialize correctly", dod.getRandomReferrals());
        long count = Referrals.countReferralses();
        Assert.assertTrue("Too expensive to perform a find all test for 'Referrals', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Referrals> result = Referrals.findAllReferralses();
        Assert.assertNotNull("Find all method for 'Referrals' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Referrals' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void ReferralsIntegrationTest.testFindReferralsEntries() {
        Assert.assertNotNull("Data on demand for 'Referrals' failed to initialize correctly", dod.getRandomReferrals());
        long count = Referrals.countReferralses();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Referrals> result = Referrals.findReferralsEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Referrals' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Referrals' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void ReferralsIntegrationTest.testFlush() {
        Referrals obj = dod.getRandomReferrals();
        Assert.assertNotNull("Data on demand for 'Referrals' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Referrals' failed to provide an identifier", id);
        obj = Referrals.findReferrals(id);
        Assert.assertNotNull("Find method for 'Referrals' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyReferrals(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Referrals' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void ReferralsIntegrationTest.testMergeUpdate() {
        Referrals obj = dod.getRandomReferrals();
        Assert.assertNotNull("Data on demand for 'Referrals' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Referrals' failed to provide an identifier", id);
        obj = Referrals.findReferrals(id);
        boolean modified =  dod.modifyReferrals(obj);
        Integer currentVersion = obj.getVersion();
        Referrals merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Referrals' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void ReferralsIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'Referrals' failed to initialize correctly", dod.getRandomReferrals());
        Referrals obj = dod.getNewTransientReferrals(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Referrals' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Referrals' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'Referrals' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void ReferralsIntegrationTest.testRemove() {
        Referrals obj = dod.getRandomReferrals();
        Assert.assertNotNull("Data on demand for 'Referrals' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Referrals' failed to provide an identifier", id);
        obj = Referrals.findReferrals(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Referrals' with identifier '" + id + "'", Referrals.findReferrals(id));
    }
    
}
