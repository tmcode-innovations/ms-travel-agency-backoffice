package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.DocumentTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierInstitutionDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SupplierInstitutionResponse extends SupplierResponse {

    public static SupplierInstitutionResponse from(SupplierInstitutionDomain domain) {
        SupplierInstitutionResponse supplierInstitutionResponse = new SupplierInstitutionResponse();
        supplierInstitutionResponse.setId(domain.getId());
        supplierInstitutionResponse.setName(domain.getName());
        supplierInstitutionResponse.setDescription(domain.getDescription());
        supplierInstitutionResponse.setIdentify(domain.getIdentify());
        supplierInstitutionResponse.setRuc(domain.getRuc());
        supplierInstitutionResponse.setConfirmation(domain.getConfirmation());
        supplierInstitutionResponse.setReConfirmation(domain.getReConfirmation());
        supplierInstitutionResponse.setValueConfirmation(domain.getValueConfirmation());
        supplierInstitutionResponse.setValueReConfirmation(domain.getValueReConfirmation());
        supplierInstitutionResponse.setSummary(domain.getSummary());
        supplierInstitutionResponse.setPoliticsCancel1(domain.getPoliticsCancel1());
        supplierInstitutionResponse.setPoliticsCancel2(domain.getPoliticsCancel2());
        supplierInstitutionResponse.setPoliticsFree(domain.getPoliticsFree());
        supplierInstitutionResponse.setPoliticsChildren(domain.getPoliticsChildren());
        supplierInstitutionResponse.setPoliticsShows(domain.getPoliticsShows());
        supplierInstitutionResponse.setAdministrative(domain.getAdministrative());
        supplierInstitutionResponse.setCheckIn(domain.getCheckIn());
        supplierInstitutionResponse.setCheckOut(domain.getCheckOut());
        supplierInstitutionResponse.setServiceGroup(domain.getServiceGroup());
        if (domain.getPartner() != null &&
                domain.getPartner().getId() != null &&
                !domain.getPartner().getId().isEmpty()){
            supplierInstitutionResponse.setPartner(PartnerResponse.from(domain.getPartner()));
        }
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            supplierInstitutionResponse.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getSector() != null &&
                domain.getSector().getId() != null &&
                !domain.getSector().getId().isEmpty()){
            supplierInstitutionResponse.setSector(SectorResponse.from(domain.getSector()));
        }
        if (domain.getDocumentType() != null &&
                domain.getDocumentType().getId() != null &&
                !domain.getDocumentType().getId().isEmpty()){
            supplierInstitutionResponse.setDocumentType(DocumentTypeResponse.from(domain.getDocumentType()));
        }
        if (domain.getTouristDestine() != null &&
                domain.getTouristDestine().getId() != null &&
                !domain.getTouristDestine().getId().isEmpty()){
            supplierInstitutionResponse.setTouristDestine(TouristDestineResponse.from(domain.getTouristDestine()));
        }
        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            supplierInstitutionResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            supplierInstitutionResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            supplierInstitutionResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        supplierInstitutionResponse.setCreatedAt(domain.getCreatedAt());
        supplierInstitutionResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        supplierInstitutionResponse.setUpdatedAt(domain.getUpdatedAt());
        supplierInstitutionResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        supplierInstitutionResponse.setDeletedAt(domain.getDeletedAt());
        supplierInstitutionResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return supplierInstitutionResponse;
    }
}
