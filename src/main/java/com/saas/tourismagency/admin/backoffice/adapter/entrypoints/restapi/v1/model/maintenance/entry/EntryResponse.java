package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.nomenclature.AffectIgvResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.EntryDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.AffectIgvEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("affectIgv")
    private String affectIgv;

    @JsonProperty("affectIgvDescription")
    private String affectIgvDescription;

    @JsonProperty("affectIgvModel")
    private AffectIgvResponse affectIgvModel;

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

    public static EntryResponse from(EntryDomain domain) {
        EntryResponse entryResponse = new EntryResponse();
        entryResponse.setId(domain.getId());
        entryResponse.setName(domain.getName());
        entryResponse.setDescription(domain.getDescription());
        entryResponse.setIdentify(domain.getIdentify());
        entryResponse.setAffectIgv(domain.getAffectIgv());
        if (domain.getAffectIgv() != null && !domain.getAffectIgv().isEmpty() && !domain.getAffectIgv().isBlank()){
            AffectIgvEnum.AffectIgvModel affectIgvModelData = EnumMappeableService
                    .map(AffectIgvEnum.class)
                    .stream().filter(
                            affectIgvModel -> affectIgvModel.name().equals(domain.getAffectIgv()))
                    .findFirst().get();
            entryResponse.setAffectIgvDescription(affectIgvModelData.description());
            entryResponse.setAffectIgvModel(AffectIgvResponse.from(affectIgvModelData));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            entryResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            entryResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            entryResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        entryResponse.setCreatedAt(domain.getCreatedAt());
        entryResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        entryResponse.setUpdatedAt(domain.getUpdatedAt());
        entryResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        entryResponse.setDeletedAt(domain.getDeletedAt());
        entryResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return entryResponse;
    }
}
