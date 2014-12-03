package com.woomii.beta.backend.endusers;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.woomii.beta.frontend.apps.Apps;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "EndUsers")
@RooJson
public class EndUsers {

    /**
     */
    @NotNull
    @Column(unique = true)
    @Size(max = 40)
    private String uuid;

    /**
     */
    @ManyToOne
    @JoinColumn(name = "AppId")
    private Apps app;

    /**
     */
    @NotNull
    @Column(unique = true)
    @Size(max = 40)
    private String pin;

    /**
     */
    private Boolean app_installed;
}
