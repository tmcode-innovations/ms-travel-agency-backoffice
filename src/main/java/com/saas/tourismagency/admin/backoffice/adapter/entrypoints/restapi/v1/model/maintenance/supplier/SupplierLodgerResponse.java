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
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgerDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SupplierLodgerResponse extends SupplierResponse {

    @JsonProperty("lodgingType")
    private String lodgingType;

    @JsonProperty("supplierTypeOperator")
    private String supplierTypeOperator;

    @JsonProperty("lodgingCategory")
    private LodgingCategoryResponse lodgingCategory;

    public static SupplierLodgerResponse from(SupplierLodgerDomain domain) {
        SupplierLodgerResponse supplierLodgerResponse = new SupplierLodgerResponse();
        supplierLodgerResponse.setId(domain.getId());
        supplierLodgerResponse.setName(domain.getName());
        supplierLodgerResponse.setDescription(domain.getDescription());
        supplierLodgerResponse.setIdentify(domain.getIdentify());
        supplierLodgerResponse.setRuc(domain.getRuc());
        supplierLodgerResponse.setConfirmation(domain.getConfirmation());
        supplierLodgerResponse.setReConfirmation(domain.getReConfirmation());
        supplierLodgerResponse.setValueConfirmation(domain.getValueConfirmation());
        supplierLodgerResponse.setValueReConfirmation(domain.getValueReConfirmation());
        supplierLodgerResponse.setSummary(domain.getSummary());
        supplierLodgerResponse.setPoliticsCancel1(domain.getPoliticsCancel1());
        supplierLodgerResponse.setPoliticsCancel2(domain.getPoliticsCancel2());
        supplierLodgerResponse.setPoliticsFree(domain.getPoliticsFree());
        supplierLodgerResponse.setPoliticsChildren(domain.getPoliticsChildren());
        supplierLodgerResponse.setPoliticsShows(domain.getPoliticsShows());
        supplierLodgerResponse.setAdministrative(domain.getAdministrative());
        supplierLodgerResponse.setCheckIn(domain.getCheckIn());
        supplierLodgerResponse.setCheckOut(domain.getCheckOut());
        supplierLodgerResponse.setServiceGroup(domain.getServiceGroup());
        supplierLodgerResponse.setLodgingType(domain.getLodgingType());
        supplierLodgerResponse.setSupplierTypeOperator(domain.getSupplierTypeOperator());
        if (domain.getPartner() != null &&
                domain.getPartner().getId() != null &&
                !domain.getPartner().getId().isEmpty()){
            supplierLodgerResponse.setPartner(PartnerResponse.from(domain.getPartner()));
        }
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            supplierLodgerResponse.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getSector() != null &&
                domain.getSector().getId() != null &&
                !domain.getSector().getId().isEmpty()){
            supplierLodgerResponse.setSector(SectorResponse.from(domain.getSector()));
        }
        if (domain.getDocumentType() != null &&
                domain.getDocumentType().getId() != null &&
                !domain.getDocumentType().getId().isEmpty()){
            supplierLodgerResponse.setDocumentType(DocumentTypeResponse.from(domain.getDocumentType()));
        }
        if (domain.getTouristDestine() != null &&
                domain.getTouristDestine().getId() != null &&
                !domain.getTouristDestine().getId().isEmpty()){
            supplierLodgerResponse.setTouristDestine(TouristDestineResponse.from(domain.getTouristDestine()));
        }
        if (domain.getLodgingCategory() != null &&
                domain.getLodgingCategory().getId() != null &&
                !domain.getLodgingCategory().getId().isEmpty()){
            supplierLodgerResponse.setLodgingCategory(LodgingCategoryResponse.from(domain.getLodgingCategory()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            supplierLodgerResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            supplierLodgerResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            supplierLodgerResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        supplierLodgerResponse.setCreatedAt(domain.getCreatedAt());
        supplierLodgerResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        supplierLodgerResponse.setUpdatedAt(domain.getUpdatedAt());
        supplierLodgerResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        supplierLodgerResponse.setDeletedAt(domain.getDeletedAt());
        supplierLodgerResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return supplierLodgerResponse;
    }
}
