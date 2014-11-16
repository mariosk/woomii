package com.woomii.beta.frontend.areas;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.woomii.beta.frontend.campaigns.Campaigns;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "Areas")
@RooJson
public class Areas {

    /**
     */
    @ManyToOne
    @JoinColumn(name = "CampaignId")
    private Campaigns campaign;

    /**
     */
    private Double lat1;

    /**
     */
    private Double lng1;

    /**
     */
    private Double lat2;

    /**
     */
    private Double lng2;

    /**
     */
    private Double lat3;

    /**
     */
    private Double lng3;

    /**
     */
    private Double lat4;

    /**
     */
    private Double lng4;
}
