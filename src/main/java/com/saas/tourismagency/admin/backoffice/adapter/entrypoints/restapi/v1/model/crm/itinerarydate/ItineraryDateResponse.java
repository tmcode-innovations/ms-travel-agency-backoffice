package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itinerarydate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.idiom.IdiomResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.QuotationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.TourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @JsonProperty("quotation")
    private QuotationResponse quotation;

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
        ItineraryDateResponse itineraryDateResponse = new ItineraryDateResponse();
        itineraryDateResponse.setId(domain.getId());
        itineraryDateResponse.setName(domain.getName());
        itineraryDateResponse.setItemItinerary(domain.getItemItinerary());
        itineraryDateResponse.setDescription(domain.getDescription());
        itineraryDateResponse.setIdentify(domain.getIdentify());
        itineraryDateResponse.setIndex(domain.getIndex());
        itineraryDateResponse.setItineraryDate(domain.getItineraryDate());
        itineraryDateResponse.setItineraryDateStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getItineraryDate()));
        itineraryDateResponse.setShowNameComplete(domain.getItemItinerary() + " - " + LocalDateTimeUtil.toLocalDateFormatShort(domain.getItineraryDate()) + (domain.getName() != null && !domain.getName().isEmpty() ? " - " : "") + domain.getName() );

        if (domain.getQuotation() != null &&
                domain.getQuotation().getId() != null &&
                !domain.getQuotation().getId().isEmpty()){
            itineraryDateResponse.setQuotation(QuotationResponse.from(domain.getQuotation()));
        }

        if (domain.getTours() != null && !domain.getTours().isEmpty()){
            itineraryDateResponse.setTours(domain.getTours().stream().map(tourDomain -> TourResponse.from(tourDomain)).collect(Collectors.toSet()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            itineraryDateResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            itineraryDateResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            itineraryDateResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        itineraryDateResponse.setCreatedAt(domain.getCreatedAt());
        itineraryDateResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        itineraryDateResponse.setUpdatedAt(domain.getUpdatedAt());
        itineraryDateResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        itineraryDateResponse.setDeletedAt(domain.getDeletedAt());
        itineraryDateResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return itineraryDateResponse;
    }

    public static ItineraryDateResponse from(ItineraryDateDomain domain, String generalConfiguration) {
        ItineraryDateResponse itineraryDateResponse = new ItineraryDateResponse();
        itineraryDateResponse.setId(domain.getId());
        itineraryDateResponse.setName(domain.getName());
        itineraryDateResponse.setItemItinerary(domain.getItemItinerary());
        itineraryDateResponse.setDescription(domain.getDescription());
        itineraryDateResponse.setIdentify(domain.getIdentify());
        itineraryDateResponse.setIndex(domain.getIndex());
        itineraryDateResponse.setItineraryDate(domain.getItineraryDate());
        itineraryDateResponse.setItineraryDateStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getItineraryDate()));
        if (generalConfiguration.equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            itineraryDateResponse.setShowNameComplete(domain.getItemItinerary() + (domain.getName() != null && !domain.getName().isEmpty() ? " - " : "") + domain.getName() );
        }else if (generalConfiguration.equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            itineraryDateResponse.setShowNameComplete(domain.getItemItinerary() + " - " + LocalDateTimeUtil.toLocalDateFormatShort(domain.getItineraryDate()) + (domain.getName() != null && !domain.getName().isEmpty() ? " - " : "") + domain.getName() );
        }

        if (domain.getQuotation() != null &&
                domain.getQuotation().getId() != null &&
                !domain.getQuotation().getId().isEmpty()){
            itineraryDateResponse.setQuotation(QuotationResponse.from(domain.getQuotation()));
        }

        if (domain.getTours() != null && !domain.getTours().isEmpty()){
            itineraryDateResponse.setTours(domain.getTours().stream().map(tourDomain -> TourResponse.from(tourDomain)).collect(Collectors.toSet()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            itineraryDateResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            itineraryDateResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            itineraryDateResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        itineraryDateResponse.setCreatedAt(domain.getCreatedAt());
        itineraryDateResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        itineraryDateResponse.setUpdatedAt(domain.getUpdatedAt());
        itineraryDateResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        itineraryDateResponse.setDeletedAt(domain.getDeletedAt());
        itineraryDateResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return itineraryDateResponse;
    }
}
