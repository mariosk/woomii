package com.woomii.beta.backend.demographics;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.woomii.beta.backend.endusers.EndUsers;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.woomii.beta.types.SexType;
import javax.persistence.Enumerated;
import com.woomii.beta.types.AgeRangeType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "Demographics")
@RooJson
public class Demographics {

    /**
     */
    @OneToOne
    @JoinColumn(name = "EndUserId")
    private EndUsers enduser;

    /**
     */
    @Enumerated
    private SexType sex;

    /**
     */
    @Enumerated
    private AgeRangeType age;

    /**
     */
    @NotNull
    @Size(max = 64)
    private String name;

    /**
     */
    @Size(max = 16)
    private String fb_id;

    /**
     */
    @Size(max = 16)
    private String mobile;

    /**
     */
    @Size(max = 32)
    private String email;
}
