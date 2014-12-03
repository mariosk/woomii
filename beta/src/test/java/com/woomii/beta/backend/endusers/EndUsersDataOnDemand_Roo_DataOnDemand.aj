// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.woomii.beta.backend.endusers;

import com.woomii.beta.backend.endusers.EndUsers;
import com.woomii.beta.backend.endusers.EndUsersDataOnDemand;
import com.woomii.beta.frontend.apps.AppsDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect EndUsersDataOnDemand_Roo_DataOnDemand {
    
    declare @type: EndUsersDataOnDemand: @Component;
    
    private Random EndUsersDataOnDemand.rnd = new SecureRandom();
    
    private List<EndUsers> EndUsersDataOnDemand.data;
    
    @Autowired
    AppsDataOnDemand EndUsersDataOnDemand.appsDataOnDemand;
    
    public EndUsers EndUsersDataOnDemand.getNewTransientEndUsers(int index) {
        EndUsers obj = new EndUsers();
        setApp_installed(obj, index);
        setPin(obj, index);
        setUuid(obj, index);
        return obj;
    }
    
    public void EndUsersDataOnDemand.setApp_installed(EndUsers obj, int index) {
        Boolean app_installed = Boolean.TRUE;
        obj.setApp_installed(app_installed);
    }
    
    public void EndUsersDataOnDemand.setPin(EndUsers obj, int index) {
        String pin = "pin_" + index;
        if (pin.length() > 40) {
            pin = new Random().nextInt(10) + pin.substring(1, 40);
        }
        obj.setPin(pin);
    }
    
    public void EndUsersDataOnDemand.setUuid(EndUsers obj, int index) {
        String uuid = "uuid_" + index;
        if (uuid.length() > 40) {
            uuid = new Random().nextInt(10) + uuid.substring(1, 40);
        }
        obj.setUuid(uuid);
    }
    
    public EndUsers EndUsersDataOnDemand.getSpecificEndUsers(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        EndUsers obj = data.get(index);
        Long id = obj.getId();
        return EndUsers.findEndUsers(id);
    }
    
    public EndUsers EndUsersDataOnDemand.getRandomEndUsers() {
        init();
        EndUsers obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return EndUsers.findEndUsers(id);
    }
    
    public boolean EndUsersDataOnDemand.modifyEndUsers(EndUsers obj) {
        return false;
    }
    
    public void EndUsersDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = EndUsers.findEndUsersEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'EndUsers' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<EndUsers>();
        for (int i = 0; i < 10; i++) {
            EndUsers obj = getNewTransientEndUsers(i);
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
