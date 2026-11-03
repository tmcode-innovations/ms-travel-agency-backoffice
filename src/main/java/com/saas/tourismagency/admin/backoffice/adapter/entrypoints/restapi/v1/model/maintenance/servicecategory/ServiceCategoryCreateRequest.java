package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.servicecategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceCategoryDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCategoryCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("parent")
    private String parent;

    public ServiceCategoryDomain to() {
        ServiceCategoryDomain serviceCategoryDomain = new ServiceCategoryDomain();
        serviceCategoryDomain.setName(name);
        serviceCategoryDomain.setDescription(description);
        serviceCategoryDomain.setIdentify(identify);
        return serviceCategoryDomain;
    }

    public ServiceCategoryDomain to(ServiceCategoryDomain parent) {
        ServiceCategoryDomain serviceCategoryDomain = new ServiceCategoryDomain();
        serviceCategoryDomain.setName(name);
        serviceCategoryDomain.setDescription(description);
        serviceCategoryDomain.setIdentify(identify);
        if (parent != null)
            serviceCategoryDomain.setParent(parent);
        return serviceCategoryDomain;
    }
}
