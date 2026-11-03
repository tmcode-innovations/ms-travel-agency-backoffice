package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.lodgingcategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.LodgingCategoryDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LodgingCategoryCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public LodgingCategoryDomain to() {
        LodgingCategoryDomain lodgingCategoryDomain = new LodgingCategoryDomain();
        lodgingCategoryDomain.setName(name);
        lodgingCategoryDomain.setDescription(description);
        return lodgingCategoryDomain;
    }
}
