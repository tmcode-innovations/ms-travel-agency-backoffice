package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.idiom.IdiomResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.DocumentTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierGuideDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SupplierGuideResponse extends SupplierResponse {

    @JsonProperty("idioms")
    private Set<IdiomResponse> idioms;

    public static SupplierGuideResponse from(SupplierGuideDomain domain) {
        SupplierGuideResponse supplierGuideResponse = new SupplierGuideResponse();
        supplierGuideResponse.setId(domain.getId());
        supplierGuideResponse.setName(domain.getName());
        supplierGuideResponse.setDescription(domain.getDescription());
        supplierGuideResponse.setIdentify(domain.getIdentify());
        supplierGuideResponse.setRuc(domain.getRuc());
        supplierGuideResponse.setConfirmation(domain.getConfirmation());
        supplierGuideResponse.setReConfirmation(domain.getReConfirmation());
        supplierGuideResponse.setValueConfirmation(domain.getValueConfirmation());
        supplierGuideResponse.setValueReConfirmation(domain.getValueReConfirmation());
        supplierGuideResponse.setSummary(domain.getSummary());
        supplierGuideResponse.setPoliticsCancel1(domain.getPoliticsCancel1());
        supplierGuideResponse.setPoliticsCancel2(domain.getPoliticsCancel2());
        supplierGuideResponse.setPoliticsFree(domain.getPoliticsFree());
        supplierGuideResponse.setPoliticsChildren(domain.getPoliticsChildren());
        supplierGuideResponse.setPoliticsShows(domain.getPoliticsShows());
        supplierGuideResponse.setAdministrative(domain.getAdministrative());
        supplierGuideResponse.setCheckIn(domain.getCheckIn());
        supplierGuideResponse.setCheckOut(domain.getCheckOut());
        supplierGuideResponse.setServiceGroup(domain.getServiceGroup());
        if (domain.getPartner() != null &&
                domain.getPartner().getId() != null &&
                !domain.getPartner().getId().isEmpty()){
            supplierGuideResponse.setPartner(PartnerResponse.from(domain.getPartner()));
        }
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            supplierGuideResponse.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getSector() != null &&
                domain.getSector().getId() != null &&
                !domain.getSector().getId().isEmpty()){
            supplierGuideResponse.setSector(SectorResponse.from(domain.getSector()));
        }
        if (domain.getDocumentType() != null &&
                domain.getDocumentType().getId() != null &&
                !domain.getDocumentType().getId().isEmpty()){
            supplierGuideResponse.setDocumentType(DocumentTypeResponse.from(domain.getDocumentType()));
        }
        if (domain.getTouristDestine() != null &&
                domain.getTouristDestine().getId() != null &&
                !domain.getTouristDestine().getId().isEmpty()){
            supplierGuideResponse.setTouristDestine(TouristDestineResponse.from(domain.getTouristDestine()));
        }
        if (domain.getIdioms() != null && !domain.getIdioms().isEmpty()){
            supplierGuideResponse.setIdioms(domain.getIdioms().stream().map(idiomDomain -> IdiomResponse.from(idiomDomain)).collect(Collectors.toSet()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            supplierGuideResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            supplierGuideResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            supplierGuideResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        supplierGuideResponse.setCreatedAt(domain.getCreatedAt());
        supplierGuideResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        supplierGuideResponse.setUpdatedAt(domain.getUpdatedAt());
        supplierGuideResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        supplierGuideResponse.setDeletedAt(domain.getDeletedAt());
        supplierGuideResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return supplierGuideResponse;
    }
}
