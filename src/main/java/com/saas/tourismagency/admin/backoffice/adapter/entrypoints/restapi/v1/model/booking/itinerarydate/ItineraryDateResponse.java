package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itinerarydate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.TourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDateResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("index")
    private Integer index;

    @JsonProperty("itemItinerary")
    private String itemItinerary;

    @JsonProperty("showNameComplete")
    private String showNameComplete;

    @JsonProperty("itineraryDate")
    private LocalDate itineraryDate;

    @JsonProperty("itineraryDateStr")
    private String itineraryDateStr;

    @JsonProperty("registerGroup")
    private RegisterGroupResponse registerGroup;

    @JsonProperty("tours")
    private Set<TourResponse> tours;

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


    public static ItineraryDateResponse from(ItineraryDateDomain domain) {
        ItineraryDateResponse response = new ItineraryDateResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setItemItinerary(domain.getItemItinerary());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());
        response.setIndex(domain.getIndex());
        response.setItineraryDate(domain.getItineraryDate());
        response.setItineraryDateStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getItineraryDate()));
        response.setShowNameComplete(domain.getItemItinerary() + " - " + LocalDateTimeUtil.toLocalDateFormatShort(domain.getItineraryDate()) + (domain.getName() != null && !domain.getName().isEmpty() ? " - " : "") + domain.getName() );

        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }

        if (domain.getTours() != null && !domain.getTours().isEmpty()){
            response.setTours(domain.getTours().stream().map(tourDomain -> TourResponse.from(tourDomain)).collect(Collectors.toSet()));
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

    public static ItineraryDateResponse from(ItineraryDateDomain domain, String generalConfiguration) {
        ItineraryDateResponse response = new ItineraryDateResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setItemItinerary(domain.getItemItinerary());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());
        response.setIndex(domain.getIndex());
        response.setItineraryDate(domain.getItineraryDate());
        response.setItineraryDateStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getItineraryDate()));
        if (generalConfiguration.equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            response.setShowNameComplete(domain.getItemItinerary() + (domain.getName() != null && !domain.getName().isEmpty() ? " - " : "") + domain.getName() );
        }else if (generalConfiguration.equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            response.setShowNameComplete(domain.getItemItinerary() + " - " + LocalDateTimeUtil.toLocalDateFormatShort(domain.getItineraryDate()) + (domain.getName() != null && !domain.getName().isEmpty() ? " - " : "") + domain.getName() );
        }

        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }

        if (domain.getTours() != null && !domain.getTours().isEmpty()){
            response.setTours(domain.getTours().stream().map(tourDomain -> TourResponse.from(tourDomain)).collect(Collectors.toSet()));
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
