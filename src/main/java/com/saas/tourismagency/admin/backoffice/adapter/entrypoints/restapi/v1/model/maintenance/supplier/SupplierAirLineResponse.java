package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.DocumentTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierAirLineDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SupplierAirLineResponse extends SupplierResponse {

    public static SupplierAirLineResponse from(SupplierAirLineDomain domain) {
        SupplierAirLineResponse supplierAirLineResponse = new SupplierAirLineResponse();
        supplierAirLineResponse.setId(domain.getId());
        supplierAirLineResponse.setName(domain.getName());
        supplierAirLineResponse.setDescription(domain.getDescription());
        supplierAirLineResponse.setIdentify(domain.getIdentify());
        supplierAirLineResponse.setRuc(domain.getRuc());
        supplierAirLineResponse.setConfirmation(domain.getConfirmation());
        supplierAirLineResponse.setReConfirmation(domain.getReConfirmation());
        supplierAirLineResponse.setValueConfirmation(domain.getValueConfirmation());
        supplierAirLineResponse.setValueReConfirmation(domain.getValueReConfirmation());
        supplierAirLineResponse.setSummary(domain.getSummary());
        supplierAirLineResponse.setPoliticsCancel1(domain.getPoliticsCancel1());
        supplierAirLineResponse.setPoliticsCancel2(domain.getPoliticsCancel2());
        supplierAirLineResponse.setPoliticsFree(domain.getPoliticsFree());
        supplierAirLineResponse.setPoliticsChildren(domain.getPoliticsChildren());
        supplierAirLineResponse.setPoliticsShows(domain.getPoliticsShows());
        supplierAirLineResponse.setAdministrative(domain.getAdministrative());
        supplierAirLineResponse.setCheckIn(domain.getCheckIn());
        supplierAirLineResponse.setCheckOut(domain.getCheckOut());
        supplierAirLineResponse.setServiceGroup(domain.getServiceGroup());
        if (domain.getPartner() != null &&
                domain.getPartner().getId() != null &&
                !domain.getPartner().getId().isEmpty()){
            supplierAirLineResponse.setPartner(PartnerResponse.from(domain.getPartner()));
        }
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            supplierAirLineResponse.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getSector() != null &&
                domain.getSector().getId() != null &&
                !domain.getSector().getId().isEmpty()){
            supplierAirLineResponse.setSector(SectorResponse.from(domain.getSector()));
        }
        if (domain.getDocumentType() != null &&
                domain.getDocumentType().getId() != null &&
                !domain.getDocumentType().getId().isEmpty()){
            supplierAirLineResponse.setDocumentType(DocumentTypeResponse.from(domain.getDocumentType()));
        }
        if (domain.getTouristDestine() != null &&
                domain.getTouristDestine().getId() != null &&
                !domain.getTouristDestine().getId().isEmpty()){
            supplierAirLineResponse.setTouristDestine(TouristDestineResponse.from(domain.getTouristDestine()));
        }
        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            supplierAirLineResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            supplierAirLineResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            supplierAirLineResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        supplierAirLineResponse.setCreatedAt(domain.getCreatedAt());
        supplierAirLineResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        supplierAirLineResponse.setUpdatedAt(domain.getUpdatedAt());
        supplierAirLineResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        supplierAirLineResponse.setDeletedAt(domain.getDeletedAt());
        supplierAirLineResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return supplierAirLineResponse;
    }
}
