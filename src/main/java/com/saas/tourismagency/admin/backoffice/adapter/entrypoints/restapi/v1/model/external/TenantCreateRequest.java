package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.AreaDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantCreateRequest {

    @JsonProperty("tenantId")
    private String tenantId;

    @JsonProperty("nameOrganization")
    private String nameOrganization;

    @JsonProperty("nameCompany")
    private String nameCompany;

    @JsonProperty("emailAdministratorTenant")
    private String emailAdministratorTenant;

    @JsonProperty("userNameAdministratorTenant")
    private String userNameAdministratorTenant;

    @JsonProperty("fullNameAdministratorTenant")
    private String fullNameAdministratorTenant;

    @JsonProperty("quotationType")
    private String quotationType;

    @JsonProperty("registerGroupType")
    private String registerGroupType;

    @JsonProperty("generalConfigurationType")
    private String generalConfigurationType;
}
