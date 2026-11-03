package com.saas.tourismagency.admin.backoffice.domain.base.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocialMediaInformationEmbeddedDomain implements Serializable {

    private String id;
    private String name;
    private String description;

    private String facebook;
    private String youtube;
    private String instagram;
    private String linkedIn;
    private String twitterX;
    private String whatsApp;

}
