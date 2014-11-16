package com.woomii.beta.frontend.languages;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "Languages")
@RooJson
public class Languages {

    /**
     */
    @NotNull
    @Column(unique = true)
    @Size(min = 2, max = 2)
    private String code;

    /**
     */
    @NotNull
    private String name;
}
