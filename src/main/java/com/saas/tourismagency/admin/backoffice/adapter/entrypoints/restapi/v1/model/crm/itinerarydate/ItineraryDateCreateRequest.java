package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itinerarydate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDateCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("itineraryDate")
    private LocalDate itineraryDate;

    @JsonProperty("quotation")
    private String quotation;

    public ItineraryDateDomain to(QuotationDomain quotation) {
        ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
        itineraryDateDomain.setName(name);
        itineraryDateDomain.setDescription(description);
        itineraryDateDomain.setIdentify(identify);
        itineraryDateDomain.setItineraryDate(itineraryDate);
        if (quotation != null)
            itineraryDateDomain.setQuotation(quotation);
        return itineraryDateDomain;
    }

}
