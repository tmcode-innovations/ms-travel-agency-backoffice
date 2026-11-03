package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.costsheettwopercent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CostSheetTwoPercentDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostSheetTwoPercentUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("percentMin")
    private Double percentMin;

    @JsonProperty("percentMax")
    private Double percentMax;

    public CostSheetTwoPercentDomain to() {
        CostSheetTwoPercentDomain costSheetTwoPercentDomain = new CostSheetTwoPercentDomain();
        costSheetTwoPercentDomain.setId(id);
        costSheetTwoPercentDomain.setName(name);
        costSheetTwoPercentDomain.setDescription(description);
        costSheetTwoPercentDomain.setPercentMin(percentMin);
        costSheetTwoPercentDomain.setPercentMax(percentMax);
        return costSheetTwoPercentDomain;
    }

}
