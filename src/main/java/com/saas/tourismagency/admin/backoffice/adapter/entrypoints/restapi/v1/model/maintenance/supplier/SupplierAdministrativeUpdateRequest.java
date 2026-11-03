package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SupplierAdministrativeUpdateRequest extends SupplierUpdateRequest{

    public SupplierAdministrativeDomain to() {
        SupplierAdministrativeDomain supplierAdministrativeDomain = new SupplierAdministrativeDomain();
        supplierAdministrativeDomain.setId(id);
        supplierAdministrativeDomain.setName(name);
        supplierAdministrativeDomain.setDescription(description);
        supplierAdministrativeDomain.setRuc(ruc);
        supplierAdministrativeDomain.setConfirmation(confirmation);
        supplierAdministrativeDomain.setReConfirmation(reConfirmation);
        supplierAdministrativeDomain.setValueConfirmation(valueConfirmation);
        supplierAdministrativeDomain.setValueReConfirmation(valueReConfirmation);
        supplierAdministrativeDomain.setSummary(summary);
        supplierAdministrativeDomain.setPoliticsCancel1(politicsCancel1);
        supplierAdministrativeDomain.setPoliticsCancel2(politicsCancel2);
        supplierAdministrativeDomain.setPoliticsFree(politicsFree);
        supplierAdministrativeDomain.setPoliticsChildren(politicsChildren);
        supplierAdministrativeDomain.setPoliticsShows(politicsShows);
        supplierAdministrativeDomain.setCheckIn(checkIn);
        supplierAdministrativeDomain.setCheckOut(checkOut);
        supplierAdministrativeDomain.setServiceGroup(serviceGroup);
        return supplierAdministrativeDomain;
    }

    public SupplierAdministrativeDomain to(PartnerDomain partner, SupplierTypeDomain supplierType, SectorDomain sector, DocumentTypeDomain documentType, TouristDestineDomain touristDestine) {
        SupplierAdministrativeDomain supplierAdministrativeDomain = new SupplierAdministrativeDomain();
        supplierAdministrativeDomain.setId(id);
        supplierAdministrativeDomain.setName(name);
        supplierAdministrativeDomain.setDescription(description);
        supplierAdministrativeDomain.setRuc(ruc);
        supplierAdministrativeDomain.setConfirmation(confirmation);
        supplierAdministrativeDomain.setReConfirmation(reConfirmation);
        supplierAdministrativeDomain.setValueConfirmation(valueConfirmation);
        supplierAdministrativeDomain.setValueReConfirmation(valueReConfirmation);
        supplierAdministrativeDomain.setSummary(summary);
        supplierAdministrativeDomain.setPoliticsCancel1(politicsCancel1);
        supplierAdministrativeDomain.setPoliticsCancel2(politicsCancel2);
        supplierAdministrativeDomain.setPoliticsFree(politicsFree);
        supplierAdministrativeDomain.setPoliticsChildren(politicsChildren);
        supplierAdministrativeDomain.setPoliticsShows(politicsShows);
        supplierAdministrativeDomain.setCheckIn(checkIn);
        supplierAdministrativeDomain.setCheckOut(checkOut);
        supplierAdministrativeDomain.setServiceGroup(serviceGroup);
        if (partner != null)
            supplierAdministrativeDomain.setPartner(partner);
        if (supplierType != null)
            supplierAdministrativeDomain.setSupplierType(supplierType);
        if (sector != null)
            supplierAdministrativeDomain.setSector(sector);
        if (documentType != null)
            supplierAdministrativeDomain.setDocumentType(documentType);
        if (touristDestine != null)
            supplierAdministrativeDomain.setTouristDestine(touristDestine);
        return supplierAdministrativeDomain;
    }

}
