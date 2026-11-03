package com.saas.tourismagency.admin.backoffice.domain.crm;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryItemDomain extends BaseDomain implements Serializable, Comparable<ItineraryItemDomain> {

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

    private ItineraryDateDomain itineraryDate;

    private QuotationDomain quotation;

    private SupplierTypeDomain supplierType;

    private SupplierDomain supplier;

    private ServiceDomain service;

    private DepartmentRegionDomain departmentRegion;

    private TourDomain tour;

    private CostTourDomain costTour;

    private CostTourServiceItemDomain costTourServiceItem;

    // Supplier Type Lodging

    private Integer lodgingCantSwb;
    private Integer lodgingCantMat;
    private Integer lodgingCantTwin;
    private Integer lodgingCantTrp;

    private LodgingCategoryDomain lodgingCategory;

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

    private LodgingCategoryDomain lodgeCategory;

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

    private SupplierLodgingDomain lodgingForOperator;

    private String lodgingForOperatorStr;


    @Getter(AccessLevel.NONE)
    private String itineraryDateTimeString;

    public String getItineraryDateTimeString(){
        return LocalDateTimeUtil.toLocalDateTimeFormatShort(this.getItineraryDateTime());
    }

    @Override
    public int compareTo(ItineraryItemDomain other) {
        ItineraryItemDomain to = other;
        if ( itineraryDateTime != null && to.getItineraryDateTime() != null ){
            int value = itineraryDateTime.compareTo(to.getItineraryDateTime());
            return value;
        }else{
            int value = index.compareTo(to.getIndex());
            return value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof ItineraryItemDomain)) return false;
        ItineraryItemDomain that = (ItineraryItemDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
