package com.woomii.beta.backend.transactions;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.woomii.beta.frontend.campaigns.Campaigns;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.woomii.beta.frontend.apps.Apps;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.woomii.beta.types.TransactionType;

import javax.persistence.Enumerated;

import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "Transactions")
@RooJson
public class Transactions {

    /**
     */
    @ManyToOne
    @JoinColumn(name = "CampaignId")
    private Campaigns campaign;

    /**
     */
    @ManyToOne
    @JoinColumn(name = "AppId")
    private Apps app;

    /**
     */
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date created = new Date();

    /**
     */
    @NotNull
    @Size(max = 40)
    private String uuid_a;

    /**
     */
    @Size(max = 40)
    private String uuid_b;

    /**
     */
    @Enumerated
    private TransactionType type;

    /**
     */
    private int credits_earned;

    /**
     */
    private int credits_redeemed;
}
