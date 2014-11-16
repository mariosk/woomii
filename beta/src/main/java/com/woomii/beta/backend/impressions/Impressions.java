package com.woomii.beta.backend.impressions;
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
@RooJpaActiveRecord(table = "Impressions")
@RooJson
public class Impressions {

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
    private Boolean clicked;

    /**
     */
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date created = new Date();
}
