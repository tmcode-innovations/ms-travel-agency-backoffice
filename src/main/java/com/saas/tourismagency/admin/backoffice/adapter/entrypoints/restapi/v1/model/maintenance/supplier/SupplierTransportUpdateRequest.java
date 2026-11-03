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
public class SupplierTransportUpdateRequest extends SupplierUpdateRequest{

    @JsonProperty("transportType")
    private String transportType;

    public SupplierTransportDomain to() {
        SupplierTransportDomain supplierTransportDomain = new SupplierTransportDomain();
        supplierTransportDomain.setId(id);
        supplierTransportDomain.setName(name);
        supplierTransportDomain.setDescription(description);
        supplierTransportDomain.setRuc(ruc);
        supplierTransportDomain.setConfirmation(confirmation);
        supplierTransportDomain.setReConfirmation(reConfirmation);
        supplierTransportDomain.setValueConfirmation(valueConfirmation);
        supplierTransportDomain.setValueReConfirmation(valueReConfirmation);
        supplierTransportDomain.setSummary(summary);
        supplierTransportDomain.setPoliticsCancel1(politicsCancel1);
        supplierTransportDomain.setPoliticsCancel2(politicsCancel2);
        supplierTransportDomain.setPoliticsFree(politicsFree);
        supplierTransportDomain.setPoliticsChildren(politicsChildren);
        supplierTransportDomain.setPoliticsShows(politicsShows);
        supplierTransportDomain.setAdministrative(administrative);
        supplierTransportDomain.setCheckIn(checkIn);
        supplierTransportDomain.setCheckOut(checkOut);
        supplierTransportDomain.setServiceGroup(serviceGroup);
        return supplierTransportDomain;
    }

    public SupplierTransportDomain to(PartnerDomain partner, SupplierTypeDomain supplierType, SectorDomain sector, DocumentTypeDomain documentType, TouristDestineDomain touristDestine, TransportTypeDomain transportType) {
        SupplierTransportDomain supplierTransportDomain = new SupplierTransportDomain();
        supplierTransportDomain.setId(id);
        supplierTransportDomain.setName(name);
        supplierTransportDomain.setDescription(description);
        supplierTransportDomain.setRuc(ruc);
        supplierTransportDomain.setConfirmation(confirmation);
        supplierTransportDomain.setReConfirmation(reConfirmation);
        supplierTransportDomain.setValueConfirmation(valueConfirmation);
        supplierTransportDomain.setValueReConfirmation(valueReConfirmation);
        supplierTransportDomain.setSummary(summary);
        supplierTransportDomain.setPoliticsCancel1(politicsCancel1);
        supplierTransportDomain.setPoliticsCancel2(politicsCancel2);
        supplierTransportDomain.setPoliticsFree(politicsFree);
        supplierTransportDomain.setPoliticsChildren(politicsChildren);
        supplierTransportDomain.setPoliticsShows(politicsShows);
        supplierTransportDomain.setAdministrative(administrative);
        supplierTransportDomain.setCheckIn(checkIn);
        supplierTransportDomain.setCheckOut(checkOut);
        supplierTransportDomain.setServiceGroup(serviceGroup);
        if (partner != null)
            supplierTransportDomain.setPartner(partner);
        if (supplierType != null)
            supplierTransportDomain.setSupplierType(supplierType);
        if (sector != null)
            supplierTransportDomain.setSector(sector);
        if (documentType != null)
            supplierTransportDomain.setDocumentType(documentType);
        if (touristDestine != null)
            supplierTransportDomain.setTouristDestine(touristDestine);
        if (transportType != null)
            supplierTransportDomain.setTransportType(transportType);
        return supplierTransportDomain;
    }

}
