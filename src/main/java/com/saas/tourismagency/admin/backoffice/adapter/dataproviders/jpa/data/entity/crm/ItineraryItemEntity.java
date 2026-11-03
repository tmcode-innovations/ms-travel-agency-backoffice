package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.DepartmentRegionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.LodgingCategoryDomain;
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
@Document(collection = "crm_itinerary_items")
public class ItineraryItemEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String observation;
    private String serviceGroup;

    private LocalDateTime itineraryDateTime;
    private Integer index;

    private Integer paxTotal;
    private Double price;

    private Integer paxAdultTotal;
    private Double priceAdult;

    private Integer paxChildrenTotal;
    private Double priceChildren;

    private Integer paxTcTotal;
    private Double priceTc;

    private Integer paxTcLocalTotal;
    private Double priceTcLocal;

    private Integer paxGuideTotal;
    private Double priceGuide;

    private @DocumentReference(lazy = true)
    QuotationEntity quotation;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity itineraryDate;

    private @DocumentReference(lazy = true)
    SupplierTypeEntity supplierType;

    private @DocumentReference(lazy = true)
    SupplierEntity supplier;

    private @DocumentReference(lazy = true)
    ServiceEntity service;

    private @DocumentReference(lazy = true)
    DepartmentRegionEntity departmentRegion;

    private @DocumentReference(lazy = true)
    TourEntity tour;

    private @DocumentReference(lazy = true)
    CostTourEntity costTour;

    private @DocumentReference(lazy = true)
    CostTourServiceItemEntity costTourServiceItem;

    // Supplier Type Lodging

    private Integer lodgingCantSwb;
    private Integer lodgingCantMat;
    private Integer lodgingCantTwin;
    private Integer lodgingCantTrp;

    private @DocumentReference(lazy = true)
    LodgingCategoryEntity lodgingCategory;

    private Integer lodgingCantBreakfast;
    private Integer lodgingCantLunch;
    private Integer lodgingCantDinner;
    private Integer lodgingCantBoxLunch;

    private Integer lodgingCantNight;

    private Integer lodgingCantBreakfastTcGuide;
    private Integer lodgingCantLunchTcGuide;
    private Integer lodgingCantDinnerTcGuide;
    private Integer lodgingCantBoxLunchTcGuide;

    private Integer lodgingCantSwbTcGuide;
    private Integer lodgingCantTwinTcGuide;

    private Integer lodgingTcCantNight;

    private Integer lodgingCantBreakfastTcLocalGuide;
    private Integer lodgingCantLunchTcLocalGuide;
    private Integer lodgingCantDinnerTcLocalGuide;
    private Integer lodgingCantBoxLunchTcLocalGuide;

    private Integer lodgingCantSwbTcLocalGuide;
    private Integer lodgingCantTwinTcLocalGuide;

    private Integer lodgingTcLocalCantNight;

    private Integer lodgingCantBreakfastGuide;
    private Integer lodgingCantLunchGuide;
    private Integer lodgingCantDinnerGuide;
    private Integer lodgingCantBoxLunchGuide;

    private Integer lodgingCantSwbGuide;
    private Integer lodgingCantTwinGuide;

    private Integer lodgingGuideCantNight;

    // Supplier Type Lodge

    private Integer lodgeCantSwb;
    private Integer lodgeCantMat;
    private Integer lodgeCantTwin;
    private Integer lodgeCantTrp;

    private @DocumentReference(lazy = true)
    LodgingCategoryEntity lodgeCategory;

    private Integer lodgeCantBreakfast;
    private Integer lodgeCantLunch;
    private Integer lodgeCantDinner;
    private Integer lodgeCantBoxLunch;

    private Integer lodgeCantNight;

    private Integer lodgeCantBreakfastTcGuide;
    private Integer lodgeCantLunchTcGuide;
    private Integer lodgeCantDinnerTcGuide;
    private Integer lodgeCantBoxLunchTcGuide;

    private Integer lodgeCantSwbTcGuide;
    private Integer lodgeCantTwinTcGuide;

    private Integer lodgeTcCantNight;

    private Integer lodgeCantBreakfastTcLocalGuide;
    private Integer lodgeCantLunchTcLocalGuide;
    private Integer lodgeCantDinnerTcLocalGuide;
    private Integer lodgeCantBoxLunchTcLocalGuide;

    private Integer lodgeCantSwbTcLocalGuide;
    private Integer lodgeCantTwinTcLocalGuide;

    private Integer lodgeTcLocalCantNight;

    private Integer lodgeCantBreakfastGuide;
    private Integer lodgeCantLunchGuide;
    private Integer lodgeCantDinnerGuide;
    private Integer lodgeCantBoxLunchGuide;

    private Integer lodgeCantSwbGuide;
    private Integer lodgeCantTwinGuide;

    private Integer lodgeGuideCantNight;

    private Boolean lodgePvt;
    private Boolean lodgeSic;

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

    private Integer restaurantCantBreakfastDrive;
    private Integer restaurantCantLunchDrive;
    private Integer restaurantCantDinnerDrive;
    private Integer restaurantCantBoxLunchDrive;

    // Supplier Type Operator

    private Boolean operatorPvt;
    private Boolean operatorSic;

    private @DocumentReference(lazy = true)
    SupplierLodgingEntity lodgingForOperator;

    private String lodgingForOperatorStr;
}
