package com.woomii.beta.frontend.customers;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "Customers")
@RooJson
public class Customers {

    /**
     */
    @NotNull
    @Column(unique = true)
    @Size(max = 40)
    private String cust_id;

    /**
     */
    @Size(max = 64)
    private String name;

    /**
     */
    @Size(max = 32)
    private String email;

    /**
     */
    @Size(max = 32)
    private String password;

    /**
     */
    @Size(max = 16)
    private String fb_id;

    /**
     */
    @Size(max = 16)
    private String google_id;

    /**
     */
    @RooUploadedFile(contentType = "image/jpeg")
    @Basic(fetch = FetchType.LAZY)
    private byte[] logo;
}
