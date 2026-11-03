package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "base_partners")
public class PartnerEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;

    private String identify;

    private SocialMediaInformationEmbeddedEntity socialMediaInformation;
    private WebSiteInformationEmbeddedEntity webSiteInformation;
    private ContactInformationEmbeddedEntity contactInformation;
    private LocationInformationEmbeddedEntity locationInformation;
    private AddressEmbeddedEntity address;

}
