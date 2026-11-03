package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtouritem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtour.CostTourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtourserviceitem.CostTourServiceItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itinerarydate.ItineraryDateResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.TourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.passengertype.PassengerTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.CostTourItemDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostTourItemResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("cantPax")
    private Integer cantPax;

    @JsonProperty("pricePax")
    private Double pricePax;

    @JsonProperty("pricePaxFormat")
    private String pricePaxFormat;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("amountFormat")
    private String amountFormat;

    @JsonProperty("itineraryDate")
    private ItineraryDateResponse itineraryDate;

    @JsonProperty("registerGroup")
    private RegisterGroupResponse registerGroup;

    @JsonProperty("tour")
    private TourResponse tour;

    @JsonProperty("costTour")
    private CostTourResponse costTour;

    @JsonProperty("passengerType")
    private PassengerTypeResponse passengerType;

    @JsonProperty("costTourServiceItem")
    private CostTourServiceItemResponse costTourServiceItem;

    // Data By
    @JsonProperty("createdBy")
    private UserBaseResponse createdBy;

    @JsonProperty("updatedBy")
    private UserBaseResponse updatedBy;

    @JsonProperty("deletedBy")
    private UserBaseResponse deletedBy;

    // Data At

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("createdAtStrLong")
    private String createdAtStrLong;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("updatedAtStrLong")
    private String updatedAtStrLong;

    @JsonProperty("deletedAt")
    private LocalDateTime deletedAt;

    @JsonProperty("deletedAtStrLong")
    private String deletedAtStrLong;

    public static CostTourItemResponse from(CostTourItemDomain domain) {
        CostTourItemResponse response = new CostTourItemResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        response.setCantPax(domain.getCantPax());
        response.setPricePax(domain.getPricePax());
        response.setPricePaxFormat(domain.getPricePaxFormat());
        response.setAmount(domain.getAmount());
        response.setAmountFormat(domain.getAmountFormat());

        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }

        if (domain.getTour() != null &&
                domain.getTour().getId() != null &&
                !domain.getTour().getId().isEmpty()){
            response.setTour(TourResponse.from(domain.getTour()));
        }

        if (domain.getItineraryDate() != null &&
                domain.getItineraryDate().getId() != null &&
                !domain.getItineraryDate().getId().isEmpty()){
            response.setItineraryDate(ItineraryDateResponse.from(domain.getItineraryDate()));
        }

        if (domain.getCostTour() != null &&
                domain.getCostTour().getId() != null &&
                !domain.getCostTour().getId().isEmpty()){
            response.setCostTour(CostTourResponse.from(domain.getCostTour()));
        }

        if (domain.getPassengerType() != null &&
                domain.getPassengerType().getId() != null &&
                !domain.getPassengerType().getId().isEmpty()){
            response.setPassengerType(PassengerTypeResponse.from(domain.getPassengerType()));
        }

        if (domain.getCostTourServiceItem() != null &&
                domain.getCostTourServiceItem().getId() != null &&
                !domain.getCostTourServiceItem().getId().isEmpty()){
            response.setCostTourServiceItem(CostTourServiceItemResponse.from(domain.getCostTourServiceItem()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            response.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            response.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            response.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        response.setCreatedAt(domain.getCreatedAt());
        response.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        response.setUpdatedAt(domain.getUpdatedAt());
        response.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        response.setDeletedAt(domain.getDeletedAt());
        response.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return response;
    }

}
