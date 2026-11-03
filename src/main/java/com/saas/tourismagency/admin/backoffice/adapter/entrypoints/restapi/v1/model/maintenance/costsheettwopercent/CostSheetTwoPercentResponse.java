package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.costsheettwopercent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CostSheetTwoPercentDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostSheetTwoPercentResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("available")
    private Boolean available;

    @JsonProperty("percentMin")
    private Double percentMin;

    @JsonProperty("percentMax")
    private Double percentMax;

    @JsonProperty("availableDescription")
    private String availableDescription;

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

    public static CostSheetTwoPercentResponse from(CostSheetTwoPercentDomain domain) {
        CostSheetTwoPercentResponse costSheetTwoPercentResponse = new CostSheetTwoPercentResponse();
        costSheetTwoPercentResponse.setId(domain.getId());
        costSheetTwoPercentResponse.setName(domain.getName());
        costSheetTwoPercentResponse.setDescription(domain.getDescription());
        costSheetTwoPercentResponse.setAvailable(domain.getAvailable());
        costSheetTwoPercentResponse.setPercentMin(domain.getPercentMin());
        costSheetTwoPercentResponse.setPercentMax(domain.getPercentMax());
        costSheetTwoPercentResponse.setAvailableDescription( domain.getAvailable() != null && domain.getAvailable() ? "SI" : "NO");

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            costSheetTwoPercentResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            costSheetTwoPercentResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            costSheetTwoPercentResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        costSheetTwoPercentResponse.setCreatedAt(domain.getCreatedAt());
        costSheetTwoPercentResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        costSheetTwoPercentResponse.setUpdatedAt(domain.getUpdatedAt());
        costSheetTwoPercentResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        costSheetTwoPercentResponse.setDeletedAt(domain.getDeletedAt());
        costSheetTwoPercentResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return costSheetTwoPercentResponse;
    }
}
