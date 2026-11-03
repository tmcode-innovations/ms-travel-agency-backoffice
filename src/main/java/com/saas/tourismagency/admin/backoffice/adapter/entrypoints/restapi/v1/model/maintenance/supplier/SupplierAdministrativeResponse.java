package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.DocumentTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierAdministrativeDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SupplierAdministrativeResponse extends SupplierResponse {

    public static SupplierAdministrativeResponse from(SupplierAdministrativeDomain domain) {
        SupplierAdministrativeResponse supplierAdminstrativeResponse = new SupplierAdministrativeResponse();
        supplierAdminstrativeResponse.setId(domain.getId());
        supplierAdminstrativeResponse.setName(domain.getName());
        supplierAdminstrativeResponse.setDescription(domain.getDescription());
        supplierAdminstrativeResponse.setIdentify(domain.getIdentify());
        supplierAdminstrativeResponse.setRuc(domain.getRuc());
        supplierAdminstrativeResponse.setConfirmation(domain.getConfirmation());
        supplierAdminstrativeResponse.setReConfirmation(domain.getReConfirmation());
        supplierAdminstrativeResponse.setValueConfirmation(domain.getValueConfirmation());
        supplierAdminstrativeResponse.setValueReConfirmation(domain.getValueReConfirmation());
        supplierAdminstrativeResponse.setSummary(domain.getSummary());
        supplierAdminstrativeResponse.setPoliticsCancel1(domain.getPoliticsCancel1());
        supplierAdminstrativeResponse.setPoliticsCancel2(domain.getPoliticsCancel2());
        supplierAdminstrativeResponse.setPoliticsFree(domain.getPoliticsFree());
        supplierAdminstrativeResponse.setPoliticsChildren(domain.getPoliticsChildren());
        supplierAdminstrativeResponse.setPoliticsShows(domain.getPoliticsShows());
        supplierAdminstrativeResponse.setAdministrative(domain.getAdministrative());
        supplierAdminstrativeResponse.setCheckIn(domain.getCheckIn());
        supplierAdminstrativeResponse.setCheckOut(domain.getCheckOut());
        supplierAdminstrativeResponse.setServiceGroup(domain.getServiceGroup());
        if (domain.getPartner() != null &&
                domain.getPartner().getId() != null &&
                !domain.getPartner().getId().isEmpty()){
            supplierAdminstrativeResponse.setPartner(PartnerResponse.from(domain.getPartner()));
        }
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            supplierAdminstrativeResponse.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getSector() != null &&
                domain.getSector().getId() != null &&
                !domain.getSector().getId().isEmpty()){
            supplierAdminstrativeResponse.setSector(SectorResponse.from(domain.getSector()));
        }
        if (domain.getDocumentType() != null &&
                domain.getDocumentType().getId() != null &&
                !domain.getDocumentType().getId().isEmpty()){
            supplierAdminstrativeResponse.setDocumentType(DocumentTypeResponse.from(domain.getDocumentType()));
        }
        if (domain.getTouristDestine() != null &&
                domain.getTouristDestine().getId() != null &&
                !domain.getTouristDestine().getId().isEmpty()){
            supplierAdminstrativeResponse.setTouristDestine(TouristDestineResponse.from(domain.getTouristDestine()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            supplierAdminstrativeResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            supplierAdminstrativeResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            supplierAdminstrativeResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        supplierAdminstrativeResponse.setCreatedAt(domain.getCreatedAt());
        supplierAdminstrativeResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        supplierAdminstrativeResponse.setUpdatedAt(domain.getUpdatedAt());
        supplierAdminstrativeResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        supplierAdminstrativeResponse.setDeletedAt(domain.getDeletedAt());
        supplierAdminstrativeResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return supplierAdminstrativeResponse;
    }
}
