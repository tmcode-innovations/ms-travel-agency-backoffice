package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SupplierAirLineCreateRequest extends SupplierCreateRequest{

    public SupplierAirLineDomain to() {
        SupplierAirLineDomain supplierAirLineDomain = new SupplierAirLineDomain();
        supplierAirLineDomain.setName(name);
        supplierAirLineDomain.setDescription(description);
        supplierAirLineDomain.setRuc(ruc);
        supplierAirLineDomain.setConfirmation(confirmation);
        supplierAirLineDomain.setReConfirmation(reConfirmation);
        supplierAirLineDomain.setValueConfirmation(valueConfirmation);
        supplierAirLineDomain.setValueReConfirmation(valueReConfirmation);
        supplierAirLineDomain.setSummary(summary);
        supplierAirLineDomain.setPoliticsCancel1(politicsCancel1);
        supplierAirLineDomain.setPoliticsCancel2(politicsCancel2);
        supplierAirLineDomain.setPoliticsFree(politicsFree);
        supplierAirLineDomain.setPoliticsChildren(politicsChildren);
        supplierAirLineDomain.setPoliticsShows(getPoliticsShows());
        supplierAirLineDomain.setAdministrative(administrative);
        supplierAirLineDomain.setCheckIn(checkIn);
        supplierAirLineDomain.setCheckOut(checkOut);
        supplierAirLineDomain.setServiceGroup(serviceGroup);
        return supplierAirLineDomain;
    }

    public SupplierAirLineDomain to(PartnerDomain partner, SupplierTypeDomain supplierType, SectorDomain sector, DocumentTypeDomain documentType, TouristDestineDomain touristDestine) {
        SupplierAirLineDomain supplierAirLineDomain = new SupplierAirLineDomain();
        supplierAirLineDomain.setName(name);
        supplierAirLineDomain.setDescription(description);
        supplierAirLineDomain.setRuc(ruc);
        supplierAirLineDomain.setConfirmation(confirmation);
        supplierAirLineDomain.setReConfirmation(reConfirmation);
        supplierAirLineDomain.setValueConfirmation(valueConfirmation);
        supplierAirLineDomain.setValueReConfirmation(valueReConfirmation);
        supplierAirLineDomain.setSummary(summary);
        supplierAirLineDomain.setPoliticsCancel1(politicsCancel1);
        supplierAirLineDomain.setPoliticsCancel2(politicsCancel2);
        supplierAirLineDomain.setPoliticsFree(politicsFree);
        supplierAirLineDomain.setPoliticsChildren(politicsChildren);
        supplierAirLineDomain.setPoliticsShows(getPoliticsShows());
        supplierAirLineDomain.setAdministrative(administrative);
        supplierAirLineDomain.setCheckIn(checkIn);
        supplierAirLineDomain.setCheckOut(checkOut);
        supplierAirLineDomain.setServiceGroup(serviceGroup);
        if (partner != null)
            supplierAirLineDomain.setPartner(partner);
        if (supplierType != null)
            supplierAirLineDomain.setSupplierType(supplierType);
        if (sector != null)
            supplierAirLineDomain.setSector(sector);
        if (documentType != null)
            supplierAirLineDomain.setDocumentType(documentType);
        if (touristDestine != null)
            supplierAirLineDomain.setTouristDestine(touristDestine);
        return supplierAirLineDomain;
    }
}
