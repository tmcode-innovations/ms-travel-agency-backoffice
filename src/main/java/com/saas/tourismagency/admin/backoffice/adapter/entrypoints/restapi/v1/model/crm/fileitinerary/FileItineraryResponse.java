package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.fileitinerary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.QuotationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.crm.FileItineraryDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileItineraryResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("fileName")
    private String fileName;

    @JsonProperty("extension")
    private String extension;

    @JsonProperty("path")
    private String path;

    @JsonProperty("quotation")
    private QuotationResponse quotation;

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

    @JsonProperty("createdAtStr")
    private String createdAtStr;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("updatedAtStrLong")
    private String updatedAtStrLong;

    @JsonProperty("deletedAt")
    private LocalDateTime deletedAt;

    @JsonProperty("deletedAtStrLong")
    private String deletedAtStrLong;


    public static FileItineraryResponse from(FileItineraryDomain domain) {
        FileItineraryResponse fileItineraryResponse = new FileItineraryResponse();
        fileItineraryResponse.setId(domain.getId());
        fileItineraryResponse.setName(domain.getName());
        fileItineraryResponse.setDescription(domain.getDescription());
        fileItineraryResponse.setIdentify(domain.getIdentify());
        fileItineraryResponse.setFileName(domain.getFileName());
        fileItineraryResponse.setExtension(domain.getExtension());
        fileItineraryResponse.setPath(domain.getPath());
        if (domain.getQuotation() != null &&
                domain.getQuotation().getId() != null &&
                !domain.getQuotation().getId().isEmpty()){
            fileItineraryResponse.setQuotation(QuotationResponse.from(domain.getQuotation()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            fileItineraryResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            fileItineraryResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            fileItineraryResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        fileItineraryResponse.setCreatedAt(domain.getCreatedAt());
        fileItineraryResponse.setCreatedAtStr(LocalDateTimeUtil.toLocalDateTimeFormatDateTime(domain.getCreatedAt()));
        fileItineraryResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        fileItineraryResponse.setUpdatedAt(domain.getUpdatedAt());
        fileItineraryResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        fileItineraryResponse.setDeletedAt(domain.getDeletedAt());
        fileItineraryResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return fileItineraryResponse;
    }
}
