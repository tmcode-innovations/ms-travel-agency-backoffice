package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.DocumentTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponse {

    @JsonProperty("id")
    protected String id;

    @JsonProperty("name")
    protected String name;

    @JsonProperty("description")
    protected String description;

    @JsonProperty("identify")
    protected String identify;

    @JsonProperty("ruc")
    protected String ruc;

    @JsonProperty("confirmation")
    protected Boolean confirmation;

    @JsonProperty("reConfirmation")
    protected Boolean reConfirmation;

    @JsonProperty("valueConfirmation")
    protected Integer valueConfirmation;

    @JsonProperty("valueReConfirmation")
    protected Integer valueReConfirmation;

    @JsonProperty("summary")
    protected String summary;

    @JsonProperty("politicsCancel1")
    protected String politicsCancel1;

    @JsonProperty("politicsCancel2")
    protected String politicsCancel2;

    @JsonProperty("politicsFree")
    protected String politicsFree;

    @JsonProperty("politicsChildren")
    protected String politicsChildren;

    @JsonProperty("politicsShows")
    protected String politicsShows;

    @JsonProperty("administrative")
    protected Boolean administrative;

    @JsonProperty("checkIn")
    protected String checkIn;

    @JsonProperty("checkOut")
    protected String checkOut;

    @JsonProperty("serviceGroup")
    protected String serviceGroup;

    @JsonProperty("partner")
    protected PartnerResponse partner;

    @JsonProperty("supplierType")
    protected SupplierTypeResponse supplierType;

    @JsonProperty("sector")
    protected SectorResponse sector;

    @JsonProperty("documentType")
    protected DocumentTypeResponse documentType;

    @JsonProperty("touristDestine")
    protected TouristDestineResponse touristDestine;

    @JsonProperty("strAbbrevTypeSupplier")
    protected String strAbbrevTypeSupplier;

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

    public static SupplierResponse from(SupplierDomain domain) {
        SupplierResponse supplierResponse = new SupplierResponse();
        supplierResponse.setId(domain.getId());
        supplierResponse.setName(domain.getName());
        supplierResponse.setDescription(domain.getDescription());
        supplierResponse.setIdentify(domain.getIdentify());
        supplierResponse.setRuc(domain.getRuc());
        supplierResponse.setConfirmation(domain.getConfirmation());
        supplierResponse.setReConfirmation(domain.getReConfirmation());
        supplierResponse.setValueConfirmation(domain.getValueConfirmation());
        supplierResponse.setValueReConfirmation(domain.getValueReConfirmation());
        supplierResponse.setSummary(domain.getSummary());
        supplierResponse.setPoliticsCancel1(domain.getPoliticsCancel1());
        supplierResponse.setPoliticsCancel2(domain.getPoliticsCancel2());
        supplierResponse.setPoliticsFree(domain.getPoliticsFree());
        supplierResponse.setPoliticsChildren(domain.getPoliticsChildren());
        supplierResponse.setPoliticsShows(domain.getPoliticsShows());
        supplierResponse.setAdministrative(domain.getAdministrative());
        supplierResponse.setCheckIn(domain.getCheckIn());
        supplierResponse.setCheckOut(domain.getCheckOut());
        supplierResponse.setServiceGroup(domain.getServiceGroup());

        if (domain.getPartner() != null &&
                domain.getPartner().getId() != null &&
                !domain.getPartner().getId().isEmpty()){
            supplierResponse.setPartner(PartnerResponse.from(domain.getPartner()));
        }
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            supplierResponse.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getSector() != null &&
                domain.getSector().getId() != null &&
                !domain.getSector().getId().isEmpty()){
            supplierResponse.setSector(SectorResponse.from(domain.getSector()));
        }
        if (domain.getDocumentType() != null &&
                domain.getDocumentType().getId() != null &&
                !domain.getDocumentType().getId().isEmpty()){
            supplierResponse.setDocumentType(DocumentTypeResponse.from(domain.getDocumentType()));
        }
        if (domain.getTouristDestine() != null &&
                domain.getTouristDestine().getId() != null &&
                !domain.getTouristDestine().getId().isEmpty()){
            supplierResponse.setTouristDestine(TouristDestineResponse.from(domain.getTouristDestine()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            supplierResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            supplierResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            supplierResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }

        supplierResponse.setStrAbbrevTypeSupplier(domain.getStrAbbrevTypeSupplier());

        // Data At
        supplierResponse.setCreatedAt(domain.getCreatedAt());
        supplierResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        supplierResponse.setUpdatedAt(domain.getUpdatedAt());
        supplierResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        supplierResponse.setDeletedAt(domain.getDeletedAt());
        supplierResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return supplierResponse;
    }
}
