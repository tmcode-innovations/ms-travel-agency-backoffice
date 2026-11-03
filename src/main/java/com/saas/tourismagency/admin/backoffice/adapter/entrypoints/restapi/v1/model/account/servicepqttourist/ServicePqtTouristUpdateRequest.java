package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.servicepqttourist;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.GroupPqtTouristDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ServicePqtTouristDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentAccountTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicePqtTouristUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("affectServiceIgv")
    private Boolean affectServiceIgv;

    @JsonProperty("costServiceIgv")
    private Boolean costServiceIgv;

    @JsonProperty("affectIgv")
    private String affectIgv;

    @JsonProperty("documentAccountType")
    private String documentAccountType;

    @JsonProperty("groupPqtTourist")
    private String groupPqtTourist;

    public ServicePqtTouristDomain to() {
        ServicePqtTouristDomain domain = new ServicePqtTouristDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setAffectIgv(affectIgv);
        domain.setAffectServiceIgv(affectServiceIgv);
        domain.setCostServiceIgv(costServiceIgv);
        return domain;
    }

    public ServicePqtTouristDomain to(DocumentAccountTypeDomain documentAccountTypeDomain, GroupPqtTouristDomain groupPqtTouristDomain) {
        ServicePqtTouristDomain domain = new ServicePqtTouristDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setAffectIgv(affectIgv);
        domain.setAffectServiceIgv(affectServiceIgv);
        domain.setCostServiceIgv(costServiceIgv);
        if (documentAccountTypeDomain != null)
            domain.setDocumentAccountType(documentAccountTypeDomain);
        if (groupPqtTouristDomain != null)
            domain.setGroupPqtTourist(groupPqtTouristDomain);
        return domain;
    }

}
