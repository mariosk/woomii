package com.woomii.beta.frontend.translations;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.woomii.beta.frontend.campaigns.Campaigns;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.woomii.beta.frontend.languages.Languages;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "Translations")
@RooJson
public class Translations {

    /**
     */
    @ManyToOne
    @JoinColumn(name = "CampaignId")
    private Campaigns campaign;

    /**
     */
    @OneToOne
    @JoinColumn(name = "LanguageId")
    private Languages lang;

    /**
     */
    @Size(max = 128)
    private String motto;

    /**
     */
    @Size(max = 255)
    private String terms;

    /**
     */
    @Size(max = 128)
    private String referral_done_msg;

    /**
     */
    @Size(max = 128)
    private String referral_error_msg;

    /**
     */
    @Size(max = 255)
    private String welcome_msg;

    /**
     */
    @Size(max = 128)
    private String error_msg;

    /**
     */
    @Size(max = 255)
    private String donation_msg;

    /**
     */
    @Size(max = 128)
    private String donation_error_msg;

    /**
     */
    @Size(max = 255)
    private String enter_pin_msg;

    /**
     */
    @Size(max = 255)
    private String push_msg_after_installation;
}
