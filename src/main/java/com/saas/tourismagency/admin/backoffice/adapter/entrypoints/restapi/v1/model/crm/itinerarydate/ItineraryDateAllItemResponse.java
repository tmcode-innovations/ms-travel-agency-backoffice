package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itinerarydate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itineraryitem.ItineraryItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.QuotationResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourItemDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDateAllItemResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("itemItinerary")
    private String itemItinerary;

    @JsonProperty("index")
    private Integer index;

    @JsonProperty("itineraryDate")
    private LocalDate itineraryDate;

    @JsonProperty("itineraryDateStr")
    private String itineraryDateStr;

    @JsonProperty("quotation")
    private QuotationResponse quotation;

    @JsonProperty("hasTours")
    private Boolean hasTours;

    @JsonProperty("itineraryItems")
    private List<ItineraryItemResponse> itineraryItems;

    public static ItineraryDateAllItemResponse from(ItineraryDateDomain domain, List<ItineraryItemDomain> itineraryItemsDomain) {
        ItineraryDateAllItemResponse itineraryDateAllItemResponse = new ItineraryDateAllItemResponse();
        itineraryDateAllItemResponse.setId(domain.getId());
        itineraryDateAllItemResponse.setName(domain.getName());
        itineraryDateAllItemResponse.setDescription(domain.getDescription());
        itineraryDateAllItemResponse.setIdentify(domain.getIdentify());
        itineraryDateAllItemResponse.setIndex(domain.getIndex());
        itineraryDateAllItemResponse.setItemItinerary(domain.getItemItinerary());
        itineraryDateAllItemResponse.setItineraryDate(domain.getItineraryDate());
        itineraryDateAllItemResponse.setItineraryDateStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getItineraryDate()));

        if (domain.getQuotation() != null &&
                domain.getQuotation().getId() != null &&
                !domain.getQuotation().getId().isEmpty()){
            itineraryDateAllItemResponse.setQuotation(QuotationResponse.from(domain.getQuotation()));
        }

        if (domain.getTours() != null && !domain.getTours().isEmpty()){
            itineraryDateAllItemResponse.setHasTours(true);
            List<TourDomain> tourDomainListResult = domain.getTours().stream().collect(Collectors.toList());
            StringBuffer result = new StringBuffer();
            for(TourDomain item: tourDomainListResult){
                result.append(item.getName() + " / ");
            }
            if ( result.length() > 1 ){
                result.delete(result.length()-2, result.length()-1);
            }
            itineraryDateAllItemResponse.setName(itineraryDateAllItemResponse.getItemItinerary() + (itineraryDateAllItemResponse.getName() != null && !itineraryDateAllItemResponse.getName().isEmpty() ? " - " : "") + itineraryDateAllItemResponse.getName() + " - " + result);
        }else{
            itineraryDateAllItemResponse.setHasTours(false);
            itineraryDateAllItemResponse.setName(itineraryDateAllItemResponse.getItemItinerary() + (itineraryDateAllItemResponse.getName() != null && !itineraryDateAllItemResponse.getName().isEmpty() ? " - " : "") + itineraryDateAllItemResponse.getName());
        }
        itineraryDateAllItemResponse.setItineraryItems(
                itineraryItemsDomain.
                        stream().
                        map(itineraryItemDomain -> ItineraryItemResponse.from(itineraryItemDomain)).
                        collect(Collectors.toList())
        );
        return itineraryDateAllItemResponse;
    }
}
