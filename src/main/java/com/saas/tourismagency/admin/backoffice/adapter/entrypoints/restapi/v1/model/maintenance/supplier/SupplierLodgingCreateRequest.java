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
public class SupplierLodgingCreateRequest extends SupplierCreateRequest{

    @JsonProperty("lodgingType")
    private String lodgingType;

    @JsonProperty("lodgingCategory")
    private String lodgingCategory;

    public SupplierLodgingDomain to() {
        SupplierLodgingDomain supplierLodgingDomain = new SupplierLodgingDomain();
        supplierLodgingDomain.setName(name);
        supplierLodgingDomain.setDescription(description);
        supplierLodgingDomain.setRuc(ruc);
        supplierLodgingDomain.setConfirmation(confirmation);
        supplierLodgingDomain.setReConfirmation(reConfirmation);
        supplierLodgingDomain.setValueConfirmation(valueConfirmation);
        supplierLodgingDomain.setValueReConfirmation(valueReConfirmation);
        supplierLodgingDomain.setSummary(summary);
        supplierLodgingDomain.setPoliticsCancel1(politicsCancel1);
        supplierLodgingDomain.setPoliticsCancel2(politicsCancel2);
        supplierLodgingDomain.setPoliticsFree(politicsFree);
        supplierLodgingDomain.setPoliticsChildren(politicsChildren);
        supplierLodgingDomain.setPoliticsShows(getPoliticsShows());
        supplierLodgingDomain.setAdministrative(administrative);
        supplierLodgingDomain.setCheckIn(checkIn);
        supplierLodgingDomain.setCheckOut(checkOut);
        supplierLodgingDomain.setServiceGroup(serviceGroup);
        supplierLodgingDomain.setLodgingType(lodgingType);
        return supplierLodgingDomain;
    }

    public SupplierLodgingDomain to(PartnerDomain partner, SupplierTypeDomain supplierType, SectorDomain sector, DocumentTypeDomain documentType, TouristDestineDomain touristDestine, LodgingCategoryDomain lodgingCategory) {
        SupplierLodgingDomain supplierLodgingDomain = new SupplierLodgingDomain();
        supplierLodgingDomain.setName(name);
        supplierLodgingDomain.setDescription(description);
        supplierLodgingDomain.setRuc(ruc);
        supplierLodgingDomain.setConfirmation(confirmation);
        supplierLodgingDomain.setReConfirmation(reConfirmation);
        supplierLodgingDomain.setValueConfirmation(valueConfirmation);
        supplierLodgingDomain.setValueReConfirmation(valueReConfirmation);
        supplierLodgingDomain.setSummary(summary);
        supplierLodgingDomain.setPoliticsCancel1(politicsCancel1);
        supplierLodgingDomain.setPoliticsCancel2(politicsCancel2);
        supplierLodgingDomain.setPoliticsFree(politicsFree);
        supplierLodgingDomain.setPoliticsChildren(politicsChildren);
        supplierLodgingDomain.setPoliticsShows(getPoliticsShows());
        supplierLodgingDomain.setAdministrative(administrative);
        supplierLodgingDomain.setCheckIn(checkIn);
        supplierLodgingDomain.setCheckOut(checkOut);
        supplierLodgingDomain.setServiceGroup(serviceGroup);
        supplierLodgingDomain.setLodgingType(lodgingType);
        if (partner != null)
            supplierLodgingDomain.setPartner(partner);
        if (supplierType != null)
            supplierLodgingDomain.setSupplierType(supplierType);
        if (sector != null)
            supplierLodgingDomain.setSector(sector);
        if (documentType != null)
            supplierLodgingDomain.setDocumentType(documentType);
        if (touristDestine != null)
            supplierLodgingDomain.setTouristDestine(touristDestine);
        if (lodgingCategory != null)
            supplierLodgingDomain.setLodgingCategory(lodgingCategory);
        return supplierLodgingDomain;
    }
}
