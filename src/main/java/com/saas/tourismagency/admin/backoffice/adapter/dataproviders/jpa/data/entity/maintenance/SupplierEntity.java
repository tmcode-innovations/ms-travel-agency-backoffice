package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.EntityBaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PartnerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "base_entities")
@TypeAlias("SupplierEntityData")
public class SupplierEntity extends EntityBaseEntity implements Serializable {

    protected String ruc;
    protected Boolean confirmation;
    protected Boolean reConfirmation;
    protected Integer valueConfirmation;
    protected Integer valueReConfirmation;
    protected String summary;
    protected String politicsCancel1;
    protected String politicsCancel2;
    protected String politicsFree;
    protected String politicsChildren;
    protected String politicsShows;
    protected Boolean administrative;
    protected String checkIn;
    protected String checkOut;

    protected String serviceGroup;

    protected @DocumentReference(lazy = true)
    PartnerEntity partner;
    protected @DocumentReference(lazy = true)
    SupplierTypeEntity supplierType;
    protected @DocumentReference(lazy = true)
    SectorEntity sector;
    protected @DocumentReference(lazy = true)
    DocumentTypeEntity documentType;
    protected @DocumentReference(lazy = true)
    TouristDestineEntity touristDestine;

    protected @DocumentReference(lazy = true)
    Set<ServiceEntity> services;
}
