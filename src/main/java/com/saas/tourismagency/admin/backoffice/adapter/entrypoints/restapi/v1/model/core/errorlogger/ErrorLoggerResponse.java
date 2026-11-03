package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.errorlogger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.core.ErrorLoggerDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorLoggerResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("clazz")
    private String clazz;

    @JsonProperty("stackTrace")
    private String stackTrace;

    @JsonProperty("importance")
    private String importance;

    @JsonProperty("message")
    private String message;

    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("extraData")
    private String extraData;

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

    public static ErrorLoggerResponse from(ErrorLoggerDomain domain) {
        ErrorLoggerResponse errorLoggerResponse = new ErrorLoggerResponse();
        errorLoggerResponse.setId(domain.getId());
        errorLoggerResponse.setName(domain.getName());
        errorLoggerResponse.setDescription(domain.getDescription());

        errorLoggerResponse.setClazz( domain.getClazz() );
        errorLoggerResponse.setStackTrace( domain.getStackTrace() );
        errorLoggerResponse.setImportance( domain.getImportance() );
        errorLoggerResponse.setMessage( domain.getMessage() );
        errorLoggerResponse.setStatusCode( domain.getStatusCode() );
        errorLoggerResponse.setExtraData( domain.getExtraData() );

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            errorLoggerResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            errorLoggerResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            errorLoggerResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        errorLoggerResponse.setCreatedAt(domain.getCreatedAt());
        errorLoggerResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        errorLoggerResponse.setUpdatedAt(domain.getUpdatedAt());
        errorLoggerResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        errorLoggerResponse.setDeletedAt(domain.getDeletedAt());
        errorLoggerResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return errorLoggerResponse;
    }
}
