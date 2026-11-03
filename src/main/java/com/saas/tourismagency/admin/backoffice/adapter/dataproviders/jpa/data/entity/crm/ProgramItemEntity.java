package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
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
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "crm_program_items")
public class ProgramItemEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String observation;

    private Integer index;

    private @DocumentReference(lazy = true)
    ProgramEntity program;

    private @DocumentReference(lazy = true)
    ProgramDateEntity programDate;

    private @DocumentReference(lazy = true)
    SupplierTypeEntity supplierType;

    private @DocumentReference(lazy = true)
    SupplierEntity supplier;

    private @DocumentReference(lazy = true)
    ServiceEntity service;

    private @DocumentReference(lazy = true)
    TourEntity tour;

    private @DocumentReference(lazy = true)
    ProgramCostTourEntity programCostTour;

    private LocalDateTime programDateTime;

    private Boolean pvt;
    private Boolean sic;

    private String serviceGroup;

    // Supplier Type Lodging

    private Integer lodgingCantSwb;
    private Integer lodgingCantMat;
    private Integer lodgingCantTwin;
    private Integer lodgingCantTrp;

    private String lodgingCategoryStr;

    private Integer lodgingCantBreakfast;
    private Integer lodgingCantLunch;
    private Integer lodgingCantDinner;
    private Integer lodgingCantBoxLunch;

    private Integer lodgingCantSwbTcGuide;
    private Integer lodgingCantTwinTcGuide;

    private Integer lodgingCantBreakfastTcGuide;
    private Integer lodgingCantLunchTcGuide;
    private Integer lodgingCantDinnerTcGuide;
    private Integer lodgingCantBoxLunchTcGuide;

    private Integer lodgingCantSwbTcLocalGuide;
    private Integer lodgingCantTwinTcLocalGuide;

    private Integer lodgingCantBreakfastTcLocalGuide;
    private Integer lodgingCantLunchTcLocalGuide;
    private Integer lodgingCantDinnerTcLocalGuide;
    private Integer lodgingCantBoxLunchTcLocalGuide;

    private Integer lodgingCantSwbGuide;
    private Integer lodgingCantTwinGuide;

    private Integer lodgingCantBreakfastGuide;
    private Integer lodgingCantLunchGuide;
    private Integer lodgingCantDinnerGuide;
    private Integer lodgingCantBoxLunchGuide;

    // Supplier Type Lodge

    private Integer lodgeCantSwb;
    private Integer lodgeCantMat;
    private Integer lodgeCantTwin;
    private Integer lodgeCantTrp;

    private String lodgeCategoryStr;

    private Integer lodgeCantBreakfast;
    private Integer lodgeCantLunch;
    private Integer lodgeCantDinner;
    private Integer lodgeCantBoxLunch;

    private Integer lodgeCantSwbTcGuide;
    private Integer lodgeCantTwinTcGuide;

    private Integer lodgeCantBreakfastTcGuide;
    private Integer lodgeCantLunchTcGuide;
    private Integer lodgeCantDinnerTcGuide;
    private Integer lodgeCantBoxLunchTcGuide;

    private Integer lodgeCantSwbTcLocalGuide;
    private Integer lodgeCantTwinTcLocalGuide;

    private Integer lodgeCantBreakfastTcLocalGuide;
    private Integer lodgeCantLunchTcLocalGuide;
    private Integer lodgeCantDinnerTcLocalGuide;
    private Integer lodgeCantBoxLunchTcLocalGuide;

    private Integer lodgeCantSwbGuide;
    private Integer lodgeCantTwinGuide;

    private Integer lodgeCantBreakfastGuide;
    private Integer lodgeCantLunchGuide;
    private Integer lodgeCantDinnerGuide;
    private Integer lodgeCantBoxLunchGuide;

    // Supplier Type Restaurant

    private Integer restaurantCantBreakfast;
    private Integer restaurantCantLunch;
    private Integer restaurantCantDinner;
    private Integer restaurantCantBoxLunch;

    private Integer restaurantCantBreakfastTcGuide;
    private Integer restaurantCantLunchTcGuide;
    private Integer restaurantCantDinnerTcGuide;
    private Integer restaurantCantBoxLunchTcGuide;

    private Integer restaurantCantBreakfastTcLocalGuide;
    private Integer restaurantCantLunchTcLocalGuide;
    private Integer restaurantCantDinnerTcLocalGuide;
    private Integer restaurantCantBoxLunchTcLocalGuide;

    private Integer restaurantCantBreakfastGuide;
    private Integer restaurantCantLunchGuide;
    private Integer restaurantCantDinnerGuide;
    private Integer restaurantCantBoxLunchGuide;

}
