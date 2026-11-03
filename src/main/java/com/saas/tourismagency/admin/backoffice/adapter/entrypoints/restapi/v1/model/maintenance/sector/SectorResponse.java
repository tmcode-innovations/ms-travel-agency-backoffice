package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SectorDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

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

    public static SectorResponse from(SectorDomain domain) {
        SectorResponse sectorResponse = new SectorResponse();
        sectorResponse.setId(domain.getId());
        sectorResponse.setName(domain.getName());
        sectorResponse.setDescription(domain.getDescription());
        sectorResponse.setIdentify(domain.getIdentify());

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            sectorResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            sectorResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            sectorResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        sectorResponse.setCreatedAt(domain.getCreatedAt());
        sectorResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        sectorResponse.setUpdatedAt(domain.getUpdatedAt());
        sectorResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        sectorResponse.setDeletedAt(domain.getDeletedAt());
        sectorResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return sectorResponse;
    }
}
