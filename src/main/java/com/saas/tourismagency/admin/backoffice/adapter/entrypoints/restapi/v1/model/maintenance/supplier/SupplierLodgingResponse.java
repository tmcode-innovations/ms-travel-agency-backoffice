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
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgingDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SupplierLodgingResponse extends SupplierResponse {

    @JsonProperty("lodgingType")
    private String lodgingType;

    @JsonProperty("lodgingCategory")
    private LodgingCategoryResponse lodgingCategory;

    public static SupplierLodgingResponse from(SupplierLodgingDomain domain) {
        SupplierLodgingResponse supplierLodgingResponse = new SupplierLodgingResponse();
        supplierLodgingResponse.setId(domain.getId());
        supplierLodgingResponse.setName(domain.getName());
        supplierLodgingResponse.setDescription(domain.getDescription());
        supplierLodgingResponse.setIdentify(domain.getIdentify());
        supplierLodgingResponse.setRuc(domain.getRuc());
        supplierLodgingResponse.setConfirmation(domain.getConfirmation());
        supplierLodgingResponse.setReConfirmation(domain.getReConfirmation());
        supplierLodgingResponse.setValueConfirmation(domain.getValueConfirmation());
        supplierLodgingResponse.setValueReConfirmation(domain.getValueReConfirmation());
        supplierLodgingResponse.setSummary(domain.getSummary());
        supplierLodgingResponse.setPoliticsCancel1(domain.getPoliticsCancel1());
        supplierLodgingResponse.setPoliticsCancel2(domain.getPoliticsCancel2());
        supplierLodgingResponse.setPoliticsFree(domain.getPoliticsFree());
        supplierLodgingResponse.setPoliticsChildren(domain.getPoliticsChildren());
        supplierLodgingResponse.setPoliticsShows(domain.getPoliticsShows());
        supplierLodgingResponse.setAdministrative(domain.getAdministrative());
        supplierLodgingResponse.setCheckIn(domain.getCheckIn());
        supplierLodgingResponse.setCheckOut(domain.getCheckOut());
        supplierLodgingResponse.setServiceGroup(domain.getServiceGroup());
        supplierLodgingResponse.setLodgingType(domain.getLodgingType());
        if (domain.getPartner() != null &&
                domain.getPartner().getId() != null &&
                !domain.getPartner().getId().isEmpty()){
            supplierLodgingResponse.setPartner(PartnerResponse.from(domain.getPartner()));
        }
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            supplierLodgingResponse.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getSector() != null &&
                domain.getSector().getId() != null &&
                !domain.getSector().getId().isEmpty()){
            supplierLodgingResponse.setSector(SectorResponse.from(domain.getSector()));
        }
        if (domain.getDocumentType() != null &&
                domain.getDocumentType().getId() != null &&
                !domain.getDocumentType().getId().isEmpty()){
            supplierLodgingResponse.setDocumentType(DocumentTypeResponse.from(domain.getDocumentType()));
        }
        if (domain.getTouristDestine() != null &&
                domain.getTouristDestine().getId() != null &&
                !domain.getTouristDestine().getId().isEmpty()){
            supplierLodgingResponse.setTouristDestine(TouristDestineResponse.from(domain.getTouristDestine()));
        }
        if (domain.getLodgingCategory() != null &&
                domain.getLodgingCategory().getId() != null &&
                !domain.getLodgingCategory().getId().isEmpty()){
            supplierLodgingResponse.setLodgingCategory(LodgingCategoryResponse.from(domain.getLodgingCategory()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            supplierLodgingResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            supplierLodgingResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            supplierLodgingResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        supplierLodgingResponse.setCreatedAt(domain.getCreatedAt());
        supplierLodgingResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        supplierLodgingResponse.setUpdatedAt(domain.getUpdatedAt());
        supplierLodgingResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        supplierLodgingResponse.setDeletedAt(domain.getDeletedAt());
        supplierLodgingResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return supplierLodgingResponse;
    }
}
