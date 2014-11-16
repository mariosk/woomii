package com.woomii.beta.frontend.apps;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.woomii.beta.frontend.customers.Customers;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Lob;

import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "Apps")
@RooJson
public class Apps {

    /**
     */
    @NotNull
    @Column(unique = true)
    @Size(max = 40)
    private String app_id;

    /**
     */
    @ManyToOne
    @JoinColumn(name = "CustomerId")
    private Customers customer;

    /**
     */
    private Boolean sandbox_mode;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date sandbox_mode_changed;

    /**
     */
    @NotNull
    @Size(max = 64)
    private String bundle_id;

    /**
     */
    @Size(max = 16)
    private String sdk_version;

    /**
     */
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date created = new Date();

    /**
     */
    private float rate;

    /**
     */
    @Size(max = 255)
    private String description;

    /**
     */
    @RooUploadedFile(contentType = "image/jpeg")
    //@Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] logo;
}
