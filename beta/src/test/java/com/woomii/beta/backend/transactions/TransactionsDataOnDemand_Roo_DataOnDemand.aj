// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.backend.transactions;

import com.woomii.beta.backend.transactions.Transactions;
import com.woomii.beta.backend.transactions.TransactionsDataOnDemand;
import com.woomii.beta.frontend.apps.AppsDataOnDemand;
import com.woomii.beta.frontend.campaigns.CampaignsDataOnDemand;
import com.woomii.beta.types.TransactionType;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect TransactionsDataOnDemand_Roo_DataOnDemand {
    
    declare @type: TransactionsDataOnDemand: @Component;
    
    private Random TransactionsDataOnDemand.rnd = new SecureRandom();
    
    private List<Transactions> TransactionsDataOnDemand.data;
    
    @Autowired
    AppsDataOnDemand TransactionsDataOnDemand.appsDataOnDemand;
    
    @Autowired
    CampaignsDataOnDemand TransactionsDataOnDemand.campaignsDataOnDemand;
    
    public Transactions TransactionsDataOnDemand.getNewTransientTransactions(int index) {
        Transactions obj = new Transactions();
        setCreated(obj, index);
        setCredits_earned(obj, index);
        setCredits_redeemed(obj, index);
        setType(obj, index);
        setUuid_a(obj, index);
        setUuid_b(obj, index);
        return obj;
    }
    
    public void TransactionsDataOnDemand.setCreated(Transactions obj, int index) {
        Date created = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreated(created);
    }
    
    public void TransactionsDataOnDemand.setCredits_earned(Transactions obj, int index) {
        int credits_earned = index;
        obj.setCredits_earned(credits_earned);
    }
    
    public void TransactionsDataOnDemand.setCredits_redeemed(Transactions obj, int index) {
        int credits_redeemed = index;
        obj.setCredits_redeemed(credits_redeemed);
    }
    
    public void TransactionsDataOnDemand.setType(Transactions obj, int index) {
        TransactionType type = TransactionType.class.getEnumConstants()[0];
        obj.setType(type);
    }
    
    public void TransactionsDataOnDemand.setUuid_a(Transactions obj, int index) {
        String uuid_a = "uuid_a_" + index;
        if (uuid_a.length() > 40) {
            uuid_a = uuid_a.substring(0, 40);
        }
        obj.setUuid_a(uuid_a);
    }
    
    public void TransactionsDataOnDemand.setUuid_b(Transactions obj, int index) {
        String uuid_b = "uuid_b_" + index;
        if (uuid_b.length() > 40) {
            uuid_b = uuid_b.substring(0, 40);
        }
        obj.setUuid_b(uuid_b);
    }
    
    public Transactions TransactionsDataOnDemand.getSpecificTransactions(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Transactions obj = data.get(index);
        Long id = obj.getId();
        return Transactions.findTransactions(id);
    }
    
    public Transactions TransactionsDataOnDemand.getRandomTransactions() {
        init();
        Transactions obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Transactions.findTransactions(id);
    }
    
    public boolean TransactionsDataOnDemand.modifyTransactions(Transactions obj) {
        return false;
    }
    
    public void TransactionsDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Transactions.findTransactionsEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Transactions' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Transactions>();
        for (int i = 0; i < 10; i++) {
            Transactions obj = getNewTransientTransactions(i);
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
            data.add(obj);
        }
    }
    
}
