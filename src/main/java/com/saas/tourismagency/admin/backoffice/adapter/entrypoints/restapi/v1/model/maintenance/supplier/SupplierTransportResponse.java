package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.DocumentTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.transporttype.TransportTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTransportDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SupplierTransportResponse extends SupplierResponse {

    @JsonProperty("transportType")
    private TransportTypeResponse transportType;

    public static SupplierTransportResponse from(SupplierTransportDomain domain) {
        SupplierTransportResponse supplierTransportResponse = new SupplierTransportResponse();
        supplierTransportResponse.setId(domain.getId());
        supplierTransportResponse.setName(domain.getName());
        supplierTransportResponse.setDescription(domain.getDescription());
        supplierTransportResponse.setIdentify(domain.getIdentify());
        supplierTransportResponse.setRuc(domain.getRuc());
        supplierTransportResponse.setConfirmation(domain.getConfirmation());
        supplierTransportResponse.setReConfirmation(domain.getReConfirmation());
        supplierTransportResponse.setValueConfirmation(domain.getValueConfirmation());
        supplierTransportResponse.setValueReConfirmation(domain.getValueReConfirmation());
        supplierTransportResponse.setSummary(domain.getSummary());
        supplierTransportResponse.setPoliticsCancel1(domain.getPoliticsCancel1());
        supplierTransportResponse.setPoliticsCancel2(domain.getPoliticsCancel2());
        supplierTransportResponse.setPoliticsFree(domain.getPoliticsFree());
        supplierTransportResponse.setPoliticsChildren(domain.getPoliticsChildren());
        supplierTransportResponse.setPoliticsShows(domain.getPoliticsShows());
        supplierTransportResponse.setAdministrative(domain.getAdministrative());
        supplierTransportResponse.setCheckIn(domain.getCheckIn());
        supplierTransportResponse.setCheckOut(domain.getCheckOut());
        supplierTransportResponse.setServiceGroup(domain.getServiceGroup());
        if (domain.getPartner() != null &&
                domain.getPartner().getId() != null &&
                !domain.getPartner().getId().isEmpty()){
            supplierTransportResponse.setPartner(PartnerResponse.from(domain.getPartner()));
        }
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            supplierTransportResponse.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getSector() != null &&
                domain.getSector().getId() != null &&
                !domain.getSector().getId().isEmpty()){
            supplierTransportResponse.setSector(SectorResponse.from(domain.getSector()));
        }
        if (domain.getDocumentType() != null &&
                domain.getDocumentType().getId() != null &&
                !domain.getDocumentType().getId().isEmpty()){
            supplierTransportResponse.setDocumentType(DocumentTypeResponse.from(domain.getDocumentType()));
        }
        if (domain.getTouristDestine() != null &&
                domain.getTouristDestine().getId() != null &&
                !domain.getTouristDestine().getId().isEmpty()){
            supplierTransportResponse.setTouristDestine(TouristDestineResponse.from(domain.getTouristDestine()));
        }
        if (domain.getTransportType() != null){
            supplierTransportResponse.setTransportType(TransportTypeResponse.from(domain.getTransportType()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            supplierTransportResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            supplierTransportResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            supplierTransportResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        supplierTransportResponse.setCreatedAt(domain.getCreatedAt());
        supplierTransportResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        supplierTransportResponse.setUpdatedAt(domain.getUpdatedAt());
        supplierTransportResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        supplierTransportResponse.setDeletedAt(domain.getDeletedAt());
        supplierTransportResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return supplierTransportResponse;
    }
}
