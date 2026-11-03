package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SupplierLodgerUpdateRequest extends SupplierUpdateRequest{

    @JsonProperty("lodgingType")
    private String lodgingType;

    @JsonProperty("supplierTypeOperator")
    private String supplierTypeOperator;

    @JsonProperty("lodgingCategory")
    private String lodgingCategory;

    public SupplierLodgerDomain to() {
        SupplierLodgerDomain supplierLodgerDomain = new SupplierLodgerDomain();
        supplierLodgerDomain.setId(id);
        supplierLodgerDomain.setName(name);
        supplierLodgerDomain.setDescription(description);
        supplierLodgerDomain.setRuc(ruc);
        supplierLodgerDomain.setConfirmation(confirmation);
        supplierLodgerDomain.setReConfirmation(reConfirmation);
        supplierLodgerDomain.setValueConfirmation(valueConfirmation);
        supplierLodgerDomain.setValueReConfirmation(valueReConfirmation);
        supplierLodgerDomain.setSummary(summary);
        supplierLodgerDomain.setPoliticsCancel1(politicsCancel1);
        supplierLodgerDomain.setPoliticsCancel2(politicsCancel2);
        supplierLodgerDomain.setPoliticsFree(politicsFree);
        supplierLodgerDomain.setPoliticsChildren(politicsChildren);
        supplierLodgerDomain.setPoliticsShows(politicsShows);
        supplierLodgerDomain.setAdministrative(administrative);
        supplierLodgerDomain.setCheckIn(checkIn);
        supplierLodgerDomain.setCheckOut(checkOut);
        supplierLodgerDomain.setServiceGroup(serviceGroup);
        supplierLodgerDomain.setLodgingType(lodgingType);
        supplierLodgerDomain.setSupplierTypeOperator(supplierTypeOperator);
        return supplierLodgerDomain;
    }

    public SupplierLodgerDomain to(PartnerDomain partner, SupplierTypeDomain supplierType, SectorDomain sector, DocumentTypeDomain documentType, TouristDestineDomain touristDestine, LodgingCategoryDomain lodgingCategory) {
        SupplierLodgerDomain supplierLodgerDomain = new SupplierLodgerDomain();
        supplierLodgerDomain.setId(id);
        supplierLodgerDomain.setName(name);
        supplierLodgerDomain.setDescription(description);
        supplierLodgerDomain.setRuc(ruc);
        supplierLodgerDomain.setConfirmation(confirmation);
        supplierLodgerDomain.setReConfirmation(reConfirmation);
        supplierLodgerDomain.setValueConfirmation(valueConfirmation);
        supplierLodgerDomain.setValueReConfirmation(valueReConfirmation);
        supplierLodgerDomain.setSummary(summary);
        supplierLodgerDomain.setPoliticsCancel1(politicsCancel1);
        supplierLodgerDomain.setPoliticsCancel2(politicsCancel2);
        supplierLodgerDomain.setPoliticsFree(politicsFree);
        supplierLodgerDomain.setPoliticsChildren(politicsChildren);
        supplierLodgerDomain.setPoliticsShows(politicsShows);
        supplierLodgerDomain.setAdministrative(administrative);
        supplierLodgerDomain.setCheckIn(checkIn);
        supplierLodgerDomain.setCheckOut(checkOut);
        supplierLodgerDomain.setServiceGroup(serviceGroup);
        supplierLodgerDomain.setLodgingType(lodgingType);
        supplierLodgerDomain.setSupplierTypeOperator(supplierTypeOperator);
        if (partner != null)
            supplierLodgerDomain.setPartner(partner);
        if (supplierType != null)
            supplierLodgerDomain.setSupplierType(supplierType);
        if (sector != null)
            supplierLodgerDomain.setSector(sector);
        if (documentType != null)
            supplierLodgerDomain.setDocumentType(documentType);
        if (touristDestine != null)
            supplierLodgerDomain.setTouristDestine(touristDestine);
        if (lodgingCategory != null)
            supplierLodgerDomain.setLodgingCategory(lodgingCategory);
        return supplierLodgerDomain;
    }

}
