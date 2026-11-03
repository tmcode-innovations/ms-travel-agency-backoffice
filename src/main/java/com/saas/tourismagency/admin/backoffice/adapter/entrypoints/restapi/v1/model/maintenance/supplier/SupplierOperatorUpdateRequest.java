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
public class SupplierOperatorUpdateRequest extends SupplierUpdateRequest{

    @JsonProperty("availableOrderService")
    private Integer availableOrderService;

    @JsonProperty("supplierTypeOperator")
    private String supplierTypeOperator;

    public SupplierOperatorDomain to() {
        SupplierOperatorDomain supplierOperatorDomain = new SupplierOperatorDomain();
        supplierOperatorDomain.setId(id);
        supplierOperatorDomain.setName(name);
        supplierOperatorDomain.setDescription(description);
        supplierOperatorDomain.setRuc(ruc);
        supplierOperatorDomain.setConfirmation(confirmation);
        supplierOperatorDomain.setReConfirmation(reConfirmation);
        supplierOperatorDomain.setValueConfirmation(valueConfirmation);
        supplierOperatorDomain.setValueReConfirmation(valueReConfirmation);
        supplierOperatorDomain.setSummary(summary);
        supplierOperatorDomain.setPoliticsCancel1(politicsCancel1);
        supplierOperatorDomain.setPoliticsCancel2(politicsCancel2);
        supplierOperatorDomain.setPoliticsFree(politicsFree);
        supplierOperatorDomain.setPoliticsChildren(politicsChildren);
        supplierOperatorDomain.setPoliticsShows(politicsShows);
        supplierOperatorDomain.setAdministrative(administrative);
        supplierOperatorDomain.setCheckIn(checkIn);
        supplierOperatorDomain.setCheckOut(checkOut);
        supplierOperatorDomain.setServiceGroup(serviceGroup);
        supplierOperatorDomain.setAvailableOrderService(availableOrderService);
        supplierOperatorDomain.setSupplierTypeOperator(supplierTypeOperator);
        return supplierOperatorDomain;
    }

    public SupplierOperatorDomain to(PartnerDomain partner, SupplierTypeDomain supplierType, SectorDomain sector, DocumentTypeDomain documentType, TouristDestineDomain touristDestine) {
        SupplierOperatorDomain supplierOperatorDomain = new SupplierOperatorDomain();
        supplierOperatorDomain.setId(id);
        supplierOperatorDomain.setName(name);
        supplierOperatorDomain.setDescription(description);
        supplierOperatorDomain.setRuc(ruc);
        supplierOperatorDomain.setConfirmation(confirmation);
        supplierOperatorDomain.setReConfirmation(reConfirmation);
        supplierOperatorDomain.setValueConfirmation(valueConfirmation);
        supplierOperatorDomain.setValueReConfirmation(valueReConfirmation);
        supplierOperatorDomain.setSummary(summary);
        supplierOperatorDomain.setPoliticsCancel1(politicsCancel1);
        supplierOperatorDomain.setPoliticsCancel2(politicsCancel2);
        supplierOperatorDomain.setPoliticsFree(politicsFree);
        supplierOperatorDomain.setPoliticsChildren(politicsChildren);
        supplierOperatorDomain.setPoliticsShows(politicsShows);
        supplierOperatorDomain.setAdministrative(administrative);
        supplierOperatorDomain.setCheckIn(checkIn);
        supplierOperatorDomain.setCheckOut(checkOut);
        supplierOperatorDomain.setServiceGroup(serviceGroup);
        supplierOperatorDomain.setAvailableOrderService(availableOrderService);
        supplierOperatorDomain.setSupplierTypeOperator(supplierTypeOperator);
        if (partner != null)
            supplierOperatorDomain.setPartner(partner);
        if (supplierType != null)
            supplierOperatorDomain.setSupplierType(supplierType);
        if (sector != null)
            supplierOperatorDomain.setSector(sector);
        if (documentType != null)
            supplierOperatorDomain.setDocumentType(documentType);
        if (touristDestine != null)
            supplierOperatorDomain.setTouristDestine(touristDestine);
        return supplierOperatorDomain;
    }

}
