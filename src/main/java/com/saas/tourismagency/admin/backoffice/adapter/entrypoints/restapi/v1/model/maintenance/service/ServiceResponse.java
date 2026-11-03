package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.servicecategory.ServiceCategoryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("abbrev")
    private String abbrev;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("noApplyRate")
    private Boolean noApplyRate;

    @JsonProperty("day")
    private Integer day;

    @JsonProperty("withoutCost")
    private Boolean withoutCost;

    @JsonProperty("typeTour")
    private Boolean typeTour;

    @JsonProperty("cost")
    private Double cost;

    @JsonProperty("serviceType")
    private String serviceType;

    @JsonProperty("touristDestine")
    private TouristDestineResponse touristDestine;

    @JsonProperty("serviceCategory")
    private ServiceCategoryResponse serviceCategory;

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

    public static ServiceResponse from(ServiceDomain domain) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setId(domain.getId());
        serviceResponse.setName(domain.getName());
        serviceResponse.setDescription(domain.getDescription());
        serviceResponse.setIdentify(domain.getIdentify());
        serviceResponse.setAbbrev(domain.getAbbrev());
        serviceResponse.setObservation(domain.getObservation());
        serviceResponse.setNoApplyRate(domain.getNoApplyRate());
        serviceResponse.setDay(domain.getDay());
        serviceResponse.setWithoutCost(domain.getWithoutCost());
        serviceResponse.setTypeTour(domain.getTypeTour());
        serviceResponse.setCost(domain.getCost());
        serviceResponse.setServiceType(domain.getServiceType());
        if (domain.getTouristDestine() != null &&
                domain.getTouristDestine().getId() != null &&
                !domain.getTouristDestine().getId().isEmpty()){
            serviceResponse.setTouristDestine(TouristDestineResponse.from(domain.getTouristDestine()));
        }
        if (domain.getServiceCategory() != null &&
                domain.getServiceCategory().getId() != null &&
                !domain.getServiceCategory().getId().isEmpty()){
            serviceResponse.setServiceCategory(ServiceCategoryResponse.from(domain.getServiceCategory()));
        }
        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            serviceResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            serviceResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            serviceResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        serviceResponse.setCreatedAt(domain.getCreatedAt());
        serviceResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        serviceResponse.setUpdatedAt(domain.getUpdatedAt());
        serviceResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        serviceResponse.setDeletedAt(domain.getDeletedAt());
        serviceResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return serviceResponse;
    }
}
