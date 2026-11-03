package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.servicepqttourist;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.grouppqttourist.GroupPqtTouristResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documentaccounttype.DocAccountTypeResponse;
import com.saas.tourismagency.admin.backoffice.domain.account.ServicePqtTouristDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.AffectIgvEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicePqtTouristResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("affectServiceIgv")
    private Boolean affectServiceIgv;

    @JsonProperty("affectServiceIgvDescription")
    private String affectServiceIgvDescription;

    @JsonProperty("costServiceIgv")
    private Boolean costServiceIgv;

    @JsonProperty("costServiceIgvDescription")
    private String costServiceIgvDescription;

    @JsonProperty("affectIgv")
    private String affectIgv;

    @JsonProperty("affectIgvDescription")
    private String affectIgvDescription;

    @JsonProperty("documentAccountType")
    private DocAccountTypeResponse documentAccountType;

    @JsonProperty("groupPqtTourist")
    private GroupPqtTouristResponse groupPqtTourist;

    public static ServicePqtTouristResponse from(ServicePqtTouristDomain domain) {
        ServicePqtTouristResponse response = new ServicePqtTouristResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());
        response.setAffectIgv(domain.getAffectIgv());
        response.setAffectIgvDescription(domain.getAffectIgv() != null && domain.getAffectIgv() != "" ? EnumMappeableService
                .map(AffectIgvEnum.class)
                .stream().filter(
                        affectIgvModel -> affectIgvModel.name().equals(domain.getAffectIgv()))
                .findFirst().get().description() : "");
        response.setDocumentAccountType(domain.getDocumentAccountType() != null ? DocAccountTypeResponse.from(domain.getDocumentAccountType()) : null);
        response.setGroupPqtTourist(domain.getGroupPqtTourist() != null ? GroupPqtTouristResponse.from(domain.getGroupPqtTourist()) : null);
        response.setAffectServiceIgv(domain.getAffectServiceIgv());
        response.setCostServiceIgv(domain.getCostServiceIgv());
        response.setAffectServiceIgvDescription(domain.getAffectServiceIgv() != null ? domain.getAffectServiceIgv() ? "SI" : "NO" : "");
        response.setCostServiceIgvDescription(domain.getCostServiceIgv() != null ? domain.getCostServiceIgv() ? "SI" : "NO" : "");
        return response;
    }
}
