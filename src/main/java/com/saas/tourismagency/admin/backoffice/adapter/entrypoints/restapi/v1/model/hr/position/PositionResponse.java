package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.position;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.hr.PositionDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionResponse {

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


    public static PositionResponse from(PositionDomain domain) {
        PositionResponse positionResponse = new PositionResponse();
        positionResponse.setId(domain.getId());
        positionResponse.setName(domain.getName());
        positionResponse.setDescription(domain.getDescription());
        positionResponse.setIdentify(domain.getIdentify());

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            positionResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            positionResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            positionResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        positionResponse.setCreatedAt(domain.getCreatedAt());
        positionResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        positionResponse.setUpdatedAt(domain.getUpdatedAt());
        positionResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        positionResponse.setDeletedAt(domain.getDeletedAt());
        positionResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return positionResponse;
    }
}
