package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "booking_cost_tour_service_items")
public class CostTourServiceItemEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String observation;

    private @DocumentReference(lazy = true)
    CostTourEntity costTour;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity itineraryDate;

    private @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;

    private @DocumentReference(lazy = true)
    TourEntity tour;

    private @DocumentReference(lazy = true)
    SupplierTypeEntity supplierType;

    private @DocumentReference(lazy = true)
    SupplierEntity supplier;

    private @DocumentReference(lazy = true)
    ServiceEntity service;

}
