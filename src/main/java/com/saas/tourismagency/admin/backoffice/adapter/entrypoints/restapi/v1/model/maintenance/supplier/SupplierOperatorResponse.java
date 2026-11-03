package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.DocumentTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SupplierOperatorResponse extends SupplierResponse {

    @JsonProperty("availableOrderService")
    private Integer availableOrderService;

    @JsonProperty("supplierTypeOperator")
    private String supplierTypeOperator;

    public static SupplierOperatorResponse from(SupplierOperatorDomain domain) {
        SupplierOperatorResponse supplierOperatorResponse = new SupplierOperatorResponse();
        supplierOperatorResponse.setId(domain.getId());
        supplierOperatorResponse.setName(domain.getName());
        supplierOperatorResponse.setDescription(domain.getDescription());
        supplierOperatorResponse.setIdentify(domain.getIdentify());
        supplierOperatorResponse.setRuc(domain.getRuc());
        supplierOperatorResponse.setConfirmation(domain.getConfirmation());
        supplierOperatorResponse.setReConfirmation(domain.getReConfirmation());
        supplierOperatorResponse.setValueConfirmation(domain.getValueConfirmation());
        supplierOperatorResponse.setValueReConfirmation(domain.getValueReConfirmation());
        supplierOperatorResponse.setSummary(domain.getSummary());
        supplierOperatorResponse.setPoliticsCancel1(domain.getPoliticsCancel1());
        supplierOperatorResponse.setPoliticsCancel2(domain.getPoliticsCancel2());
        supplierOperatorResponse.setPoliticsFree(domain.getPoliticsFree());
        supplierOperatorResponse.setPoliticsChildren(domain.getPoliticsChildren());
        supplierOperatorResponse.setPoliticsShows(domain.getPoliticsShows());
        supplierOperatorResponse.setAdministrative(domain.getAdministrative());
        supplierOperatorResponse.setCheckIn(domain.getCheckIn());
        supplierOperatorResponse.setCheckOut(domain.getCheckOut());
        supplierOperatorResponse.setServiceGroup(domain.getServiceGroup());
        supplierOperatorResponse.setAvailableOrderService(domain.getAvailableOrderService());
        supplierOperatorResponse.setSupplierTypeOperator(domain.getSupplierTypeOperator());
        if (domain.getPartner() != null &&
                domain.getPartner().getId() != null &&
                !domain.getPartner().getId().isEmpty()){
            supplierOperatorResponse.setPartner(PartnerResponse.from(domain.getPartner()));
        }
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            supplierOperatorResponse.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getSector() != null &&
                domain.getSector().getId() != null &&
                !domain.getSector().getId().isEmpty()){
            supplierOperatorResponse.setSector(SectorResponse.from(domain.getSector()));
        }
        if (domain.getDocumentType() != null &&
                domain.getDocumentType().getId() != null &&
                !domain.getDocumentType().getId().isEmpty()){
            supplierOperatorResponse.setDocumentType(DocumentTypeResponse.from(domain.getDocumentType()));
        }
        if (domain.getTouristDestine() != null &&
                domain.getTouristDestine().getId() != null &&
                !domain.getTouristDestine().getId().isEmpty()){
            supplierOperatorResponse.setTouristDestine(TouristDestineResponse.from(domain.getTouristDestine()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            supplierOperatorResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            supplierOperatorResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            supplierOperatorResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        supplierOperatorResponse.setCreatedAt(domain.getCreatedAt());
        supplierOperatorResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        supplierOperatorResponse.setUpdatedAt(domain.getUpdatedAt());
        supplierOperatorResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        supplierOperatorResponse.setDeletedAt(domain.getDeletedAt());
        supplierOperatorResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return supplierOperatorResponse;
    }
}
