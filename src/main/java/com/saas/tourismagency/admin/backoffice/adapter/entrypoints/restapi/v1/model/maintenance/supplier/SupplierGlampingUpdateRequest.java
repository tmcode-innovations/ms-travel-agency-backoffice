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
public class SupplierGlampingUpdateRequest extends SupplierUpdateRequest{

    @JsonProperty("lodgingType")
    private String lodgingType;

    @JsonProperty("lodgingCategory")
    private String lodgingCategory;

    public SupplierGlampingDomain to() {
        SupplierGlampingDomain supplierGlampingDomain = new SupplierGlampingDomain();
        supplierGlampingDomain.setId(id);
        supplierGlampingDomain.setName(name);
        supplierGlampingDomain.setDescription(description);
        supplierGlampingDomain.setRuc(ruc);
        supplierGlampingDomain.setConfirmation(confirmation);
        supplierGlampingDomain.setReConfirmation(reConfirmation);
        supplierGlampingDomain.setValueConfirmation(valueConfirmation);
        supplierGlampingDomain.setValueReConfirmation(valueReConfirmation);
        supplierGlampingDomain.setSummary(summary);
        supplierGlampingDomain.setPoliticsCancel1(politicsCancel1);
        supplierGlampingDomain.setPoliticsCancel2(politicsCancel2);
        supplierGlampingDomain.setPoliticsFree(politicsFree);
        supplierGlampingDomain.setPoliticsChildren(politicsChildren);
        supplierGlampingDomain.setPoliticsShows(politicsShows);
        supplierGlampingDomain.setAdministrative(administrative);
        supplierGlampingDomain.setCheckIn(checkIn);
        supplierGlampingDomain.setCheckOut(checkOut);
        supplierGlampingDomain.setServiceGroup(serviceGroup);
        supplierGlampingDomain.setLodgingType(lodgingType);
        return supplierGlampingDomain;
    }

    public SupplierGlampingDomain to(PartnerDomain partner, SupplierTypeDomain supplierType, SectorDomain sector, DocumentTypeDomain documentType, TouristDestineDomain touristDestine, LodgingCategoryDomain lodgingCategory) {
        SupplierGlampingDomain supplierGlampingDomain = new SupplierGlampingDomain();
        supplierGlampingDomain.setId(id);
        supplierGlampingDomain.setName(name);
        supplierGlampingDomain.setDescription(description);
        supplierGlampingDomain.setRuc(ruc);
        supplierGlampingDomain.setConfirmation(confirmation);
        supplierGlampingDomain.setReConfirmation(reConfirmation);
        supplierGlampingDomain.setValueConfirmation(valueConfirmation);
        supplierGlampingDomain.setValueReConfirmation(valueReConfirmation);
        supplierGlampingDomain.setSummary(summary);
        supplierGlampingDomain.setPoliticsCancel1(politicsCancel1);
        supplierGlampingDomain.setPoliticsCancel2(politicsCancel2);
        supplierGlampingDomain.setPoliticsFree(politicsFree);
        supplierGlampingDomain.setPoliticsChildren(politicsChildren);
        supplierGlampingDomain.setPoliticsShows(politicsShows);
        supplierGlampingDomain.setAdministrative(administrative);
        supplierGlampingDomain.setCheckIn(checkIn);
        supplierGlampingDomain.setCheckOut(checkOut);
        supplierGlampingDomain.setServiceGroup(serviceGroup);
        supplierGlampingDomain.setLodgingType(lodgingType);
        if (partner != null)
            supplierGlampingDomain.setPartner(partner);
        if (supplierType != null)
            supplierGlampingDomain.setSupplierType(supplierType);
        if (sector != null)
            supplierGlampingDomain.setSector(sector);
        if (documentType != null)
            supplierGlampingDomain.setDocumentType(documentType);
        if (touristDestine != null)
            supplierGlampingDomain.setTouristDestine(touristDestine);
        if (lodgingCategory != null)
            supplierGlampingDomain.setLodgingCategory(lodgingCategory);
        return supplierGlampingDomain;
    }

}
