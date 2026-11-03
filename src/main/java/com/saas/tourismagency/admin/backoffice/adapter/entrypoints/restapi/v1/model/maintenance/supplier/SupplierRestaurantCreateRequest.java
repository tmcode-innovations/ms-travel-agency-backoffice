package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SupplierRestaurantCreateRequest extends SupplierCreateRequest{

    @JsonProperty("restaurantType")
    private String restaurantType;

    public SupplierRestaurantDomain to() {
        SupplierRestaurantDomain supplierRestaurantDomain = new SupplierRestaurantDomain();
        supplierRestaurantDomain.setName(name);
        supplierRestaurantDomain.setDescription(description);
        supplierRestaurantDomain.setRuc(ruc);
        supplierRestaurantDomain.setConfirmation(confirmation);
        supplierRestaurantDomain.setReConfirmation(reConfirmation);
        supplierRestaurantDomain.setValueConfirmation(valueConfirmation);
        supplierRestaurantDomain.setValueReConfirmation(valueReConfirmation);
        supplierRestaurantDomain.setSummary(summary);
        supplierRestaurantDomain.setPoliticsCancel1(politicsCancel1);
        supplierRestaurantDomain.setPoliticsCancel2(politicsCancel2);
        supplierRestaurantDomain.setPoliticsFree(politicsFree);
        supplierRestaurantDomain.setPoliticsChildren(politicsChildren);
        supplierRestaurantDomain.setPoliticsShows(getPoliticsShows());
        supplierRestaurantDomain.setAdministrative(administrative);
        supplierRestaurantDomain.setCheckIn(checkIn);
        supplierRestaurantDomain.setCheckOut(checkOut);
        supplierRestaurantDomain.setServiceGroup(serviceGroup);
        return supplierRestaurantDomain;
    }

    public SupplierRestaurantDomain to(PartnerDomain partner, SupplierTypeDomain supplierType, SectorDomain sector, DocumentTypeDomain documentType, TouristDestineDomain touristDestine, RestaurantTypeDomain restaurantType) {
        SupplierRestaurantDomain supplierRestaurantDomain = new SupplierRestaurantDomain();
        supplierRestaurantDomain.setName(name);
        supplierRestaurantDomain.setDescription(description);
        supplierRestaurantDomain.setRuc(ruc);
        supplierRestaurantDomain.setConfirmation(confirmation);
        supplierRestaurantDomain.setReConfirmation(reConfirmation);
        supplierRestaurantDomain.setValueConfirmation(valueConfirmation);
        supplierRestaurantDomain.setValueReConfirmation(valueReConfirmation);
        supplierRestaurantDomain.setSummary(summary);
        supplierRestaurantDomain.setPoliticsCancel1(politicsCancel1);
        supplierRestaurantDomain.setPoliticsCancel2(politicsCancel2);
        supplierRestaurantDomain.setPoliticsFree(politicsFree);
        supplierRestaurantDomain.setPoliticsChildren(politicsChildren);
        supplierRestaurantDomain.setPoliticsShows(getPoliticsShows());
        supplierRestaurantDomain.setAdministrative(administrative);
        supplierRestaurantDomain.setCheckIn(checkIn);
        supplierRestaurantDomain.setCheckOut(checkOut);
        supplierRestaurantDomain.setServiceGroup(serviceGroup);
        if (partner != null)
            supplierRestaurantDomain.setPartner(partner);
        if (supplierType != null)
            supplierRestaurantDomain.setSupplierType(supplierType);
        if (sector != null)
            supplierRestaurantDomain.setSector(sector);
        if (documentType != null)
            supplierRestaurantDomain.setDocumentType(documentType);
        if (touristDestine != null)
            supplierRestaurantDomain.setTouristDestine(touristDestine);
        if (restaurantType != null)
            supplierRestaurantDomain.setRestaurantType(restaurantType);
        return supplierRestaurantDomain;
    }
}
