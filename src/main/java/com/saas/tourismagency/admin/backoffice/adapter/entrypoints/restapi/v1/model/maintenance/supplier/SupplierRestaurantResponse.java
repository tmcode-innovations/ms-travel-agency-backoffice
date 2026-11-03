package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.DocumentTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.restauranttype.RestaurantTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierRestaurantDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SupplierRestaurantResponse extends SupplierResponse {

    @JsonProperty("restaurantType")
    private RestaurantTypeResponse restaurantType;

    public static SupplierRestaurantResponse from(SupplierRestaurantDomain domain) {
        SupplierRestaurantResponse supplierRestaurantResponse = new SupplierRestaurantResponse();
        supplierRestaurantResponse.setId(domain.getId());
        supplierRestaurantResponse.setName(domain.getName());
        supplierRestaurantResponse.setDescription(domain.getDescription());
        supplierRestaurantResponse.setIdentify(domain.getIdentify());
        supplierRestaurantResponse.setRuc(domain.getRuc());
        supplierRestaurantResponse.setConfirmation(domain.getConfirmation());
        supplierRestaurantResponse.setReConfirmation(domain.getReConfirmation());
        supplierRestaurantResponse.setValueConfirmation(domain.getValueConfirmation());
        supplierRestaurantResponse.setValueReConfirmation(domain.getValueReConfirmation());
        supplierRestaurantResponse.setSummary(domain.getSummary());
        supplierRestaurantResponse.setPoliticsCancel1(domain.getPoliticsCancel1());
        supplierRestaurantResponse.setPoliticsCancel2(domain.getPoliticsCancel2());
        supplierRestaurantResponse.setPoliticsFree(domain.getPoliticsFree());
        supplierRestaurantResponse.setPoliticsChildren(domain.getPoliticsChildren());
        supplierRestaurantResponse.setPoliticsShows(domain.getPoliticsShows());
        supplierRestaurantResponse.setAdministrative(domain.getAdministrative());
        supplierRestaurantResponse.setCheckIn(domain.getCheckIn());
        supplierRestaurantResponse.setCheckOut(domain.getCheckOut());
        supplierRestaurantResponse.setServiceGroup(domain.getServiceGroup());
        if (domain.getPartner() != null &&
                domain.getPartner().getId() != null &&
                !domain.getPartner().getId().isEmpty()){
            supplierRestaurantResponse.setPartner(PartnerResponse.from(domain.getPartner()));
        }
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            supplierRestaurantResponse.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getSector() != null &&
                domain.getSector().getId() != null &&
                !domain.getSector().getId().isEmpty()){
            supplierRestaurantResponse.setSector(SectorResponse.from(domain.getSector()));
        }
        if (domain.getDocumentType() != null &&
                domain.getDocumentType().getId() != null &&
                !domain.getDocumentType().getId().isEmpty()){
            supplierRestaurantResponse.setDocumentType(DocumentTypeResponse.from(domain.getDocumentType()));
        }
        if (domain.getTouristDestine() != null &&
                domain.getTouristDestine().getId() != null &&
                !domain.getTouristDestine().getId().isEmpty()){
            supplierRestaurantResponse.setTouristDestine(TouristDestineResponse.from(domain.getTouristDestine()));
        }
        if (domain.getRestaurantType() != null){
            supplierRestaurantResponse.setRestaurantType(RestaurantTypeResponse.from(domain.getRestaurantType()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            supplierRestaurantResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            supplierRestaurantResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            supplierRestaurantResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        supplierRestaurantResponse.setCreatedAt(domain.getCreatedAt());
        supplierRestaurantResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        supplierRestaurantResponse.setUpdatedAt(domain.getUpdatedAt());
        supplierRestaurantResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        supplierRestaurantResponse.setDeletedAt(domain.getDeletedAt());
        supplierRestaurantResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return supplierRestaurantResponse;
    }
}
