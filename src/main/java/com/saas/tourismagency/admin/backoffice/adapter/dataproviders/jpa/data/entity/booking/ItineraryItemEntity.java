package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.DepartmentRegionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.*;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.OrderServiceItemEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "booking_itinerary_items")
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
    RegisterGroupEntity registerGroup;

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
    RegisterBookingBaseEntity registerBookingBase;

    private @DocumentReference(lazy = true)
    GuideEntity guideTransport;

    private @DocumentReference(lazy = true)
    GuideEntity guideTransferDrive;

    private @DocumentReference(lazy = true)
    OrderServiceItemEntity orderServiceItem;

    private @DocumentReference(lazy = true)
    RegisterBookingItemOperatorEntity registerBookingItemOperator;

    private @DocumentReference(lazy = true)
    RegisterBookingItemLodgerEntity registerBookingItemLodger;

    private String idItineraryItemQuotation;

    private @DocumentReference(lazy = true)
    TourEntity tour;

    private @DocumentReference(lazy = true)
    CustomerEntity customer;

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

    private String lodgingCategoryStr;

    private Integer lodgingCantBreakfast;
    private Integer lodgingCantLunch;
    private Integer lodgingCantDinner;
    private Integer lodgingCantBoxLunch;

    private LocalDateTime lodgingCantBreakfastDateTime;
    private LocalDateTime lodgingCantLunchDateTime;
    private LocalDateTime lodgingCantDinnerDateTime;
    private LocalDateTime lodgingCantBoxLunchDateTime;

    private LocalDate lodgingCantBreakfastDate;
    private LocalDate lodgingCantLunchDate;
    private LocalDate lodgingCantDinnerDate;
    private LocalDate lodgingCantBoxLunchDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantBreakfastItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantLunchItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantDinnerItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantBoxLunchItineraryDate;

    private Integer lodgingCantNight;

    private Integer lodgingCantBreakfastTcGuide;
    private Integer lodgingCantLunchTcGuide;
    private Integer lodgingCantDinnerTcGuide;
    private Integer lodgingCantBoxLunchTcGuide;

    private LocalDateTime lodgingCantBreakfastTcGuideDateTime;
    private LocalDateTime lodgingCantLunchTcGuideDateTime;
    private LocalDateTime lodgingCantDinnerTcGuideDateTime;
    private LocalDateTime lodgingCantBoxLunchTcGuideDateTime;

    private LocalDate lodgingCantBreakfastTcGuideDate;
    private LocalDate lodgingCantLunchTcGuideDate;
    private LocalDate lodgingCantDinnerTcGuideDate;
    private LocalDate lodgingCantBoxLunchTcGuideDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantBreakfastTcGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantLunchTcGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantDinnerTcGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantBoxLunchTcGuideItineraryDate;

    private Integer lodgingCantSwbTcGuide;
    private Integer lodgingCantTwinTcGuide;

    private Integer lodgingTcGuideCantNight;

    private Integer lodgingCantBreakfastTcLocalGuide;
    private Integer lodgingCantLunchTcLocalGuide;
    private Integer lodgingCantDinnerTcLocalGuide;
    private Integer lodgingCantBoxLunchTcLocalGuide;

    private LocalDateTime lodgingCantBreakfastTcLocalGuideDateTime;
    private LocalDateTime lodgingCantLunchTcLocalGuideDateTime;
    private LocalDateTime lodgingCantDinnerTcLocalGuideDateTime;
    private LocalDateTime lodgingCantBoxLunchTcLocalGuideDateTime;

    private LocalDate lodgingCantBreakfastTcLocalGuideDate;
    private LocalDate lodgingCantLunchTcLocalGuideDate;
    private LocalDate lodgingCantDinnerTcLocalGuideDate;
    private LocalDate lodgingCantBoxLunchTcLocalGuideDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantBreakfastTcLocalGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantLunchTcLocalGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantDinnerTcLocalGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantBoxLunchTcLocalGuideItineraryDate;

    private Integer lodgingCantSwbTcLocalGuide;
    private Integer lodgingCantTwinTcLocalGuide;

    private Integer lodgingTcLocalGuideCantNight;

    private Integer lodgingCantBreakfastGuide;
    private Integer lodgingCantLunchGuide;
    private Integer lodgingCantDinnerGuide;
    private Integer lodgingCantBoxLunchGuide;

    private LocalDateTime lodgingCantBreakfastGuideDateTime;
    private LocalDateTime lodgingCantLunchGuideDateTime;
    private LocalDateTime lodgingCantDinnerGuideDateTime;
    private LocalDateTime lodgingCantBoxLunchGuideDateTime;

    private LocalDate lodgingCantBreakfastGuideDate;
    private LocalDate lodgingCantLunchGuideDate;
    private LocalDate lodgingCantDinnerGuideDate;
    private LocalDate lodgingCantBoxLunchGuideDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantBreakfastGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantLunchGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantDinnerGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgingCantBoxLunchGuideItineraryDate;

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

    private String lodgeCategoryStr;

    private Integer lodgeCantBreakfast;
    private Integer lodgeCantLunch;
    private Integer lodgeCantDinner;
    private Integer lodgeCantBoxLunch;

    private LocalDateTime lodgeCantBreakfastDateTime;
    private LocalDateTime lodgeCantLunchDateTime;
    private LocalDateTime lodgeCantDinnerDateTime;
    private LocalDateTime lodgeCantBoxLunchDateTime;

    private LocalDate lodgeCantBreakfastDate;
    private LocalDate lodgeCantLunchDate;
    private LocalDate lodgeCantDinnerDate;
    private LocalDate lodgeCantBoxLunchDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantBreakfastItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantLunchItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantDinnerItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantBoxLunchItineraryDate;

    private Integer lodgeCantNight;

    private Integer lodgeCantBreakfastTcGuide;
    private Integer lodgeCantLunchTcGuide;
    private Integer lodgeCantDinnerTcGuide;
    private Integer lodgeCantBoxLunchTcGuide;

    private LocalDateTime lodgeCantBreakfastTcGuideDateTime;
    private LocalDateTime lodgeCantLunchTcGuideDateTime;
    private LocalDateTime lodgeCantDinnerTcGuideDateTime;
    private LocalDateTime lodgeCantBoxLunchTcGuideDateTime;

    private LocalDate lodgeCantBreakfastTcGuideDate;
    private LocalDate lodgeCantLunchTcGuideDate;
    private LocalDate lodgeCantDinnerTcGuideDate;
    private LocalDate lodgeCantBoxLunchTcGuideDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantBreakfastTcGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantLunchTcGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantDinnerTcGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantBoxLunchTcGuideItineraryDate;

    private Integer lodgeCantSwbTcGuide;
    private Integer lodgeCantTwinTcGuide;

    private Integer lodgeTcCantNight;

    private Integer lodgeCantBreakfastTcLocalGuide;
    private Integer lodgeCantLunchTcLocalGuide;
    private Integer lodgeCantDinnerTcLocalGuide;
    private Integer lodgeCantBoxLunchTcLocalGuide;

    private LocalDateTime lodgeCantBreakfastTcLocalGuideDateTime;
    private LocalDateTime lodgeCantLunchTcLocalGuideDateTime;
    private LocalDateTime lodgeCantDinnerTcLocalGuideDateTime;
    private LocalDateTime lodgeCantBoxLunchTcLocalGuideDateTime;

    private LocalDate lodgeCantBreakfastTcLocalGuideDate;
    private LocalDate lodgeCantLunchTcLocalGuideDate;
    private LocalDate lodgeCantDinnerTcLocalGuideDate;
    private LocalDate lodgeCantBoxLunchTcLocalGuideDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantBreakfastTcLocalGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantLunchTcLocalGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantDinnerTcLocalGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantBoxLunchTcLocalGuideItineraryDate;

    private Integer lodgeCantSwbTcLocalGuide;
    private Integer lodgeCantTwinTcLocalGuide;

    private Integer lodgeTcLocalCantNight;

    private Integer lodgeCantBreakfastGuide;
    private Integer lodgeCantLunchGuide;
    private Integer lodgeCantDinnerGuide;
    private Integer lodgeCantBoxLunchGuide;

    private LocalDateTime lodgeCantBreakfastGuideDateTime;
    private LocalDateTime lodgeCantLunchGuideDateTime;
    private LocalDateTime lodgeCantDinnerGuideDateTime;
    private LocalDateTime lodgeCantBoxLunchGuideDateTime;

    private LocalDate lodgeCantBreakfastGuideDate;
    private LocalDate lodgeCantLunchGuideDate;
    private LocalDate lodgeCantDinnerGuideDate;
    private LocalDate lodgeCantBoxLunchGuideDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantBreakfastGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantLunchGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantDinnerGuideItineraryDate;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity lodgeCantBoxLunchGuideItineraryDate;

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

    private String stateItineraryService;

    private String note;

    // For Finance Exit Voucher

    private String stateServiceInvoice;

    private String noteInvoice;

    private LocalDate documentDateInvoice;

    // For Operation

    private Boolean serviceForOperation;

    private String generatedBy;
}
