package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tourdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.touitem.TourItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.TourResponse;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourItemDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourDateAllItemResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("day")
    private Integer day;

    @JsonProperty("tour")
    private TourResponse tour;

    @JsonProperty("tourItems")
    private List<TourItemResponse> tourItems;

    public static TourDateAllItemResponse from(TourDateDomain domain, List<TourItemDomain> tourItemsDomain) {
        TourDateAllItemResponse tourDateAllItemResponse = new TourDateAllItemResponse();
        tourDateAllItemResponse.setId(domain.getId());
        tourDateAllItemResponse.setName(domain.getName());
        tourDateAllItemResponse.setDescription(domain.getDescription());
        tourDateAllItemResponse.setIdentify(domain.getIdentify());
        tourDateAllItemResponse.setDay(domain.getDay());

        if (domain.getTour() != null &&
                domain.getTour().getId() != null &&
                !domain.getTour().getId().isEmpty()){
            tourDateAllItemResponse.setTour(TourResponse.from(domain.getTour()));
        }

        tourDateAllItemResponse.setTourItems(
                tourItemsDomain.
                        stream().
                        map(tourItemDomain -> TourItemResponse.from(tourItemDomain)).
                        collect(Collectors.toList())
        );
        return tourDateAllItemResponse;
    }
}
