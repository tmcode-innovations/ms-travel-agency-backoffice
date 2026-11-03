package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.detraction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DetractionDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetractionResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("available")
    private Boolean available;

    @JsonProperty("valueDefault")
    private Boolean valueDefault;

    @JsonProperty("percent")
    private Double percent;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("availableDescription")
    private String availableDescription;

    @JsonProperty("valueDefaultDescription")
    private String valueDefaultDescription;

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

    public static DetractionResponse from(DetractionDomain domain) {
        DetractionResponse detractionResponse = new DetractionResponse();
        detractionResponse.setId(domain.getId());
        detractionResponse.setName(domain.getName());
        detractionResponse.setDescription(domain.getDescription());
        detractionResponse.setAvailable(domain.getAvailable());
        detractionResponse.setValueDefault(domain.getValueDefault());
        detractionResponse.setPercent(domain.getPercent());
        detractionResponse.setAmount(domain.getAmount());
        detractionResponse.setDate(domain.getDate());
        detractionResponse.setObservation(domain.getObservation());
        detractionResponse.setAvailableDescription( domain.getAvailable() != null && domain.getAvailable() ? "SI" : "NO");
        detractionResponse.setValueDefaultDescription( domain.getValueDefault() != null && domain.getValueDefault() ? "SI" : "NO");

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            detractionResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            detractionResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            detractionResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        detractionResponse.setCreatedAt(domain.getCreatedAt());
        detractionResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        detractionResponse.setUpdatedAt(domain.getUpdatedAt());
        detractionResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        detractionResponse.setDeletedAt(domain.getDeletedAt());
        detractionResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return detractionResponse;
    }
}
