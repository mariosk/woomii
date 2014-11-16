package com.woomii.beta.backend.referrals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.woomii.beta.frontend.campaigns.Campaigns;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.woomii.beta.frontend.apps.Apps;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "Referrals")
@RooJson
public class Referrals {

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
    @Size(max = 40)
    private String aff_id;

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
    @NotNull
    @Size(max = 255)
    private String ua_b;

    /**
     */
    private short suggested_friends;

    /**
     */
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date created = new Date();
}
