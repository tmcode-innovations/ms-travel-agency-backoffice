package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
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
public class SupplierGuideCreateRequest extends SupplierCreateRequest{

    @JsonProperty("idioms")
    private Set<IdiomDomain> idioms;

    public SupplierGuideDomain to() {
        SupplierGuideDomain supplierGuideDomain = new SupplierGuideDomain();
        supplierGuideDomain.setName(name);
        supplierGuideDomain.setDescription(description);
        supplierGuideDomain.setRuc(ruc);
        supplierGuideDomain.setConfirmation(confirmation);
        supplierGuideDomain.setReConfirmation(reConfirmation);
        supplierGuideDomain.setValueConfirmation(valueConfirmation);
        supplierGuideDomain.setValueReConfirmation(valueReConfirmation);
        supplierGuideDomain.setSummary(summary);
        supplierGuideDomain.setPoliticsCancel1(politicsCancel1);
        supplierGuideDomain.setPoliticsCancel2(politicsCancel2);
        supplierGuideDomain.setPoliticsFree(politicsFree);
        supplierGuideDomain.setPoliticsChildren(politicsChildren);
        supplierGuideDomain.setPoliticsShows(getPoliticsShows());
        supplierGuideDomain.setAdministrative(administrative);
        supplierGuideDomain.setCheckIn(checkIn);
        supplierGuideDomain.setCheckOut(checkOut);
        supplierGuideDomain.setServiceGroup(serviceGroup);
        return supplierGuideDomain;
    }

    public SupplierGuideDomain to(PartnerDomain partner, SupplierTypeDomain supplierType, SectorDomain sector, DocumentTypeDomain documentType, TouristDestineDomain touristDestine, Set<IdiomDomain> idioms) {
        SupplierGuideDomain supplierGuideDomain = new SupplierGuideDomain();
        supplierGuideDomain.setName(name);
        supplierGuideDomain.setDescription(description);
        supplierGuideDomain.setRuc(ruc);
        supplierGuideDomain.setConfirmation(confirmation);
        supplierGuideDomain.setReConfirmation(reConfirmation);
        supplierGuideDomain.setValueConfirmation(valueConfirmation);
        supplierGuideDomain.setValueReConfirmation(valueReConfirmation);
        supplierGuideDomain.setSummary(summary);
        supplierGuideDomain.setPoliticsCancel1(politicsCancel1);
        supplierGuideDomain.setPoliticsCancel2(politicsCancel2);
        supplierGuideDomain.setPoliticsFree(politicsFree);
        supplierGuideDomain.setPoliticsChildren(politicsChildren);
        supplierGuideDomain.setPoliticsShows(getPoliticsShows());
        supplierGuideDomain.setAdministrative(administrative);
        supplierGuideDomain.setCheckIn(checkIn);
        supplierGuideDomain.setCheckOut(checkOut);
        supplierGuideDomain.setServiceGroup(serviceGroup);
        if (partner != null)
            supplierGuideDomain.setPartner(partner);
        if (supplierType != null)
            supplierGuideDomain.setSupplierType(supplierType);
        if (sector != null)
            supplierGuideDomain.setSector(sector);
        if (documentType != null)
            supplierGuideDomain.setDocumentType(documentType);
        if (touristDestine != null)
            supplierGuideDomain.setTouristDestine(touristDestine);
        if (idioms != null)
            supplierGuideDomain.setIdioms(idioms);
        return supplierGuideDomain;
    }
}
