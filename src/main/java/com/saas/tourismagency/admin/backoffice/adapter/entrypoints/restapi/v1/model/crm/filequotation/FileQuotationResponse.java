package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.filequotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.QuotationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.crm.FileQuotationDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileQuotationResponse {

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

    @JsonProperty("createdAtStr")
    private String createdAtStr;

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

    public static FileQuotationResponse from(FileQuotationDomain domain) {
        FileQuotationResponse fileQuotationResponse = new FileQuotationResponse();
        fileQuotationResponse.setId(domain.getId());
        fileQuotationResponse.setName(domain.getName());
        fileQuotationResponse.setDescription(domain.getDescription());
        fileQuotationResponse.setIdentify(domain.getIdentify());
        fileQuotationResponse.setFileName(domain.getFileName());
        fileQuotationResponse.setExtension(domain.getExtension());
        fileQuotationResponse.setPath(domain.getPath());
        if (domain.getQuotation() != null &&
                domain.getQuotation().getId() != null &&
                !domain.getQuotation().getId().isEmpty()){
            fileQuotationResponse.setQuotation(QuotationResponse.from(domain.getQuotation()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            fileQuotationResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            fileQuotationResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            fileQuotationResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        fileQuotationResponse.setCreatedAt(domain.getCreatedAt());
        fileQuotationResponse.setCreatedAtStr(LocalDateTimeUtil.toLocalDateTimeFormatDateTime(domain.getCreatedAt()));
        fileQuotationResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        fileQuotationResponse.setUpdatedAt(domain.getUpdatedAt());
        fileQuotationResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        fileQuotationResponse.setDeletedAt(domain.getDeletedAt());
        fileQuotationResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return fileQuotationResponse;
    }
}
