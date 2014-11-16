package com.woomii.beta.frontend.campaigns;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.woomii.beta.frontend.apps.Apps;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.woomii.beta.types.CurrencyType;
import javax.persistence.Enumerated;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(identifierColumn = "Id", identifierField = "Id", table = "Campaigns")
@RooJson
public class Campaigns {

    /**
     */
    @ManyToOne
    @JoinColumn(name = "AppId")
    private Apps app;

    /**
     */
    private Boolean status;

    /**
     */
    private Boolean area_enabled;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date launch_date;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date expiration_date;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date credits_expiration_date;

    /**
     */
    private short when_to_change_demographics;

    /**
     */
    @NotNull
    @Size(max = 32)
    private String num_of_credits_threshold;

    /**
     */
    private int num_of_credits;

    /**
     */
    @NotNull
    @Size(max = 32)
    private String num_of_referrals_threshold;

    /**
     */
    private int num_of_referrals;

    /**
     */
    @NotNull
    @Size(max = 64)
    private String name;

    /**
     */
    @Enumerated
    private CurrencyType currency;

    /**
     */
    private int credits_earn_at_installation_usera;

    /**
     */
    private int credits_earn_at_installation_userb;

    /**
     */
    private int credits_earn_at_transaction;

    /**
     */
    @Size(max = 6)
    private String rgbcolor;
}
