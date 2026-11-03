package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.DocumentTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.lodgingcategory.LodgingCategoryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierGlampingDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SupplierGlampingResponse extends SupplierResponse {

    @JsonProperty("lodgingType")
    private String lodgingType;

    @JsonProperty("lodgingCategory")
    private LodgingCategoryResponse lodgingCategory;

    public static SupplierGlampingResponse from(SupplierGlampingDomain domain) {
        SupplierGlampingResponse supplierGlampingResponse = new SupplierGlampingResponse();
        supplierGlampingResponse.setId(domain.getId());
        supplierGlampingResponse.setName(domain.getName());
        supplierGlampingResponse.setDescription(domain.getDescription());
        supplierGlampingResponse.setIdentify(domain.getIdentify());
        supplierGlampingResponse.setRuc(domain.getRuc());
        supplierGlampingResponse.setConfirmation(domain.getConfirmation());
        supplierGlampingResponse.setReConfirmation(domain.getReConfirmation());
        supplierGlampingResponse.setValueConfirmation(domain.getValueConfirmation());
        supplierGlampingResponse.setValueReConfirmation(domain.getValueReConfirmation());
        supplierGlampingResponse.setSummary(domain.getSummary());
        supplierGlampingResponse.setPoliticsCancel1(domain.getPoliticsCancel1());
        supplierGlampingResponse.setPoliticsCancel2(domain.getPoliticsCancel2());
        supplierGlampingResponse.setPoliticsFree(domain.getPoliticsFree());
        supplierGlampingResponse.setPoliticsChildren(domain.getPoliticsChildren());
        supplierGlampingResponse.setPoliticsShows(domain.getPoliticsShows());
        supplierGlampingResponse.setAdministrative(domain.getAdministrative());
        supplierGlampingResponse.setCheckIn(domain.getCheckIn());
        supplierGlampingResponse.setCheckOut(domain.getCheckOut());
        supplierGlampingResponse.setServiceGroup(domain.getServiceGroup());
        supplierGlampingResponse.setLodgingType(domain.getLodgingType());
        if (domain.getPartner() != null &&
                domain.getPartner().getId() != null &&
                !domain.getPartner().getId().isEmpty()){
            supplierGlampingResponse.setPartner(PartnerResponse.from(domain.getPartner()));
        }
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            supplierGlampingResponse.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getSector() != null &&
                domain.getSector().getId() != null &&
                !domain.getSector().getId().isEmpty()){
            supplierGlampingResponse.setSector(SectorResponse.from(domain.getSector()));
        }
        if (domain.getDocumentType() != null &&
                domain.getDocumentType().getId() != null &&
                !domain.getDocumentType().getId().isEmpty()){
            supplierGlampingResponse.setDocumentType(DocumentTypeResponse.from(domain.getDocumentType()));
        }
        if (domain.getTouristDestine() != null &&
                domain.getTouristDestine().getId() != null &&
                !domain.getTouristDestine().getId().isEmpty()){
            supplierGlampingResponse.setTouristDestine(TouristDestineResponse.from(domain.getTouristDestine()));
        }
        if (domain.getLodgingCategory() != null){
            supplierGlampingResponse.setLodgingCategory(LodgingCategoryResponse.from(domain.getLodgingCategory()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            supplierGlampingResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            supplierGlampingResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            supplierGlampingResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        supplierGlampingResponse.setCreatedAt(domain.getCreatedAt());
        supplierGlampingResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        supplierGlampingResponse.setUpdatedAt(domain.getUpdatedAt());
        supplierGlampingResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        supplierGlampingResponse.setDeletedAt(domain.getDeletedAt());
        supplierGlampingResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return supplierGlampingResponse;
    }
}
