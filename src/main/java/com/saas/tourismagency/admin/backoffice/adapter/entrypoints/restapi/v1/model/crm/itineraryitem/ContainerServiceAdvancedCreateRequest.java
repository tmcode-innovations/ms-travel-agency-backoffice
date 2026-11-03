package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itineraryitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseCreateRequestById;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerServiceAdvancedCreateRequest {

    @JsonProperty("service")
    private BaseCreateRequestById service;

    @JsonProperty("itineraryDate")
    private BaseCreateRequestById itineraryDate;

    @JsonProperty("paxAdultTotal")
    private Integer paxAdultTotal;

    @JsonProperty("paxChildrenTotal")
    private Integer paxChildrenTotal;

    @JsonProperty("paxTcTotal")
    private Integer paxTcTotal;

    @JsonProperty("paxTcLocalTotal")
    private Integer paxTcLocalTotal;

    @JsonProperty("paxGuideTotal")
    private Integer paxGuideTotal;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("itineraryDateTime")
    private LocalDateTime itineraryDateTime;
}
