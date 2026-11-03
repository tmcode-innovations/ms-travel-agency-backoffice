package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.fileitinerary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.filequotation.FileQuotationResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileItinerarySearchResponse {

    @JsonProperty("entities")
    private List<FileItineraryResponse> fileItineraries;

    @JsonProperty("totalPages")
    private long totalPages;

    @JsonProperty("totalElements")
    private long totalElements;

}
