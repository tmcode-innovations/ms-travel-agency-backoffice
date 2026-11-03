package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantResponse {

    @JsonProperty("idOrganizationExternal")
    private String idOrganizationExternal;

    @JsonProperty("idCompanyExternal")
    private String idCompanyExternal;

    @JsonProperty("idAdminUserExternal")
    private String idAdminUserExternal;

    public static TenantResponse from(String idOrganizationExternal, String idCompanyExternal, String idAdminUserExternal) {
        TenantResponse tenantResponse = new TenantResponse();
        tenantResponse.setIdOrganizationExternal(idOrganizationExternal);
        tenantResponse.setIdCompanyExternal(idCompanyExternal);
        tenantResponse.setIdAdminUserExternal(idAdminUserExternal);
        return tenantResponse;
    }
}
