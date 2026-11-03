package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerCreateRequest;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierCreateRequest {

    @JsonProperty("name")
    protected String name;

    @JsonProperty("description")
    protected String description;

    @JsonProperty("ruc")
    protected String ruc;

    @JsonProperty("confirmation")
    protected Boolean confirmation;

    @JsonProperty("reConfirmation")
    protected Boolean reConfirmation;

    @JsonProperty("valueConfirmation")
    protected Integer valueConfirmation;

    @JsonProperty("valueReConfirmation")
    protected Integer valueReConfirmation;

    @JsonProperty("summary")
    protected String summary;

    @JsonProperty("politicsCancel1")
    protected String politicsCancel1;

    @JsonProperty("politicsCancel2")
    protected String politicsCancel2;

    @JsonProperty("politicsFree")
    protected String politicsFree;

    @JsonProperty("politicsChildren")
    protected String politicsChildren;

    @JsonProperty("politicsShows")
    protected String politicsShows;

    @JsonProperty("administrative")
    protected Boolean administrative;

    @JsonProperty("checkIn")
    protected String checkIn;

    @JsonProperty("checkOut")
    protected String checkOut;

    @JsonProperty("serviceGroup")
    protected String serviceGroup;

    @JsonProperty("partner")
    private PartnerCreateRequest partner;

    @JsonProperty("sector")
    protected String sector;

    @JsonProperty("documentType")
    protected String documentType;

    @JsonProperty("touristDestine")
    protected String touristDestine;

    public SupplierDomain to() {
        SupplierDomain supplierDomain = new SupplierDomain();
        supplierDomain.setName(name);
        supplierDomain.setDescription(description);
        supplierDomain.setRuc(ruc);
        supplierDomain.setConfirmation(confirmation);
        supplierDomain.setReConfirmation(reConfirmation);
        supplierDomain.setValueConfirmation(valueConfirmation);
        supplierDomain.setValueReConfirmation(valueReConfirmation);
        supplierDomain.setSummary(summary);
        supplierDomain.setPoliticsCancel1(politicsCancel1);
        supplierDomain.setPoliticsCancel2(politicsCancel2);
        supplierDomain.setPoliticsFree(politicsFree);
        supplierDomain.setPoliticsChildren(politicsChildren);
        supplierDomain.setPoliticsShows(politicsShows);
        supplierDomain.setAdministrative(administrative);
        supplierDomain.setCheckIn(checkIn);
        supplierDomain.setCheckOut(checkOut);
        supplierDomain.setServiceGroup(serviceGroup);
        return supplierDomain;
    }

    public SupplierDomain to(PartnerDomain partner, SupplierTypeDomain supplierType, SectorDomain sector, DocumentTypeDomain documentType, TouristDestineDomain touristDestine) {
        SupplierDomain supplierDomain = new SupplierDomain();
        supplierDomain.setName(name);
        supplierDomain.setDescription(description);
        supplierDomain.setRuc(ruc);
        supplierDomain.setConfirmation(confirmation);
        supplierDomain.setReConfirmation(reConfirmation);
        supplierDomain.setValueConfirmation(valueConfirmation);
        supplierDomain.setValueReConfirmation(valueReConfirmation);
        supplierDomain.setSummary(summary);
        supplierDomain.setPoliticsCancel1(politicsCancel1);
        supplierDomain.setPoliticsCancel2(politicsCancel2);
        supplierDomain.setPoliticsFree(politicsFree);
        supplierDomain.setPoliticsChildren(politicsChildren);
        supplierDomain.setPoliticsShows(politicsShows);
        supplierDomain.setAdministrative(administrative);
        supplierDomain.setCheckIn(checkIn);
        supplierDomain.setCheckOut(checkOut);
        supplierDomain.setServiceGroup(serviceGroup);
        if (partner != null)
            supplierDomain.setPartner(partner);
        if (supplierType != null)
            supplierDomain.setSupplierType(supplierType);
        if (sector != null)
            supplierDomain.setSector(sector);
        if (documentType != null)
            supplierDomain.setDocumentType(documentType);
        if (touristDestine != null)
            supplierDomain.setTouristDestine(touristDestine);
        return supplierDomain;
    }
}
