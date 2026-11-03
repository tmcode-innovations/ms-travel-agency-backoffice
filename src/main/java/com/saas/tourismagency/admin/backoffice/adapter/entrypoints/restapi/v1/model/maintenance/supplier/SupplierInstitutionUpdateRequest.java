package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SupplierInstitutionUpdateRequest extends SupplierUpdateRequest{

    public SupplierInstitutionDomain to() {
        SupplierInstitutionDomain supplierInstitutionDomain = new SupplierInstitutionDomain();
        supplierInstitutionDomain.setId(id);
        supplierInstitutionDomain.setName(name);
        supplierInstitutionDomain.setDescription(description);
        supplierInstitutionDomain.setRuc(ruc);
        supplierInstitutionDomain.setConfirmation(confirmation);
        supplierInstitutionDomain.setReConfirmation(reConfirmation);
        supplierInstitutionDomain.setValueConfirmation(valueConfirmation);
        supplierInstitutionDomain.setValueReConfirmation(valueReConfirmation);
        supplierInstitutionDomain.setSummary(summary);
        supplierInstitutionDomain.setPoliticsCancel1(politicsCancel1);
        supplierInstitutionDomain.setPoliticsCancel2(politicsCancel2);
        supplierInstitutionDomain.setPoliticsFree(politicsFree);
        supplierInstitutionDomain.setPoliticsChildren(politicsChildren);
        supplierInstitutionDomain.setPoliticsShows(politicsShows);
        supplierInstitutionDomain.setAdministrative(administrative);
        supplierInstitutionDomain.setCheckIn(checkIn);
        supplierInstitutionDomain.setCheckOut(checkOut);
        supplierInstitutionDomain.setServiceGroup(serviceGroup);
        return supplierInstitutionDomain;
    }

    public SupplierInstitutionDomain to(PartnerDomain partner, SupplierTypeDomain supplierType, SectorDomain sector, DocumentTypeDomain documentType, TouristDestineDomain touristDestine) {
        SupplierInstitutionDomain supplierInstitutionDomain = new SupplierInstitutionDomain();
        supplierInstitutionDomain.setId(id);
        supplierInstitutionDomain.setName(name);
        supplierInstitutionDomain.setDescription(description);
        supplierInstitutionDomain.setRuc(ruc);
        supplierInstitutionDomain.setConfirmation(confirmation);
        supplierInstitutionDomain.setReConfirmation(reConfirmation);
        supplierInstitutionDomain.setValueConfirmation(valueConfirmation);
        supplierInstitutionDomain.setValueReConfirmation(valueReConfirmation);
        supplierInstitutionDomain.setSummary(summary);
        supplierInstitutionDomain.setPoliticsCancel1(politicsCancel1);
        supplierInstitutionDomain.setPoliticsCancel2(politicsCancel2);
        supplierInstitutionDomain.setPoliticsFree(politicsFree);
        supplierInstitutionDomain.setPoliticsChildren(politicsChildren);
        supplierInstitutionDomain.setPoliticsShows(politicsShows);
        supplierInstitutionDomain.setAdministrative(administrative);
        supplierInstitutionDomain.setCheckIn(checkIn);
        supplierInstitutionDomain.setCheckOut(checkOut);
        supplierInstitutionDomain.setServiceGroup(serviceGroup);
        if (partner != null)
            supplierInstitutionDomain.setPartner(partner);
        if (supplierType != null)
            supplierInstitutionDomain.setSupplierType(supplierType);
        if (sector != null)
            supplierInstitutionDomain.setSector(sector);
        if (documentType != null)
            supplierInstitutionDomain.setDocumentType(documentType);
        if (touristDestine != null)
            supplierInstitutionDomain.setTouristDestine(touristDestine);
        return supplierInstitutionDomain;
    }

}
