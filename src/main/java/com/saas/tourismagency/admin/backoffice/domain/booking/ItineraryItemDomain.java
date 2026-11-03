package com.saas.tourismagency.admin.backoffice.domain.booking;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.TypeInheritanceEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateServiceInvoiceEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.ServiceGroupEnum;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
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

    private RegisterGroupDomain registerGroup;

    private SupplierTypeDomain supplierType;

    private SupplierDomain supplier;

    private ServiceDomain service;

    private DepartmentRegionDomain departmentRegion;

    private RegisterBookingBaseDomain registerBookingBase;

    private GuideDomain guideTransport;

    private GuideDomain guideTransferDrive;

    private OrderServiceItemDomain orderServiceItem;

    private RegisterBookingItemOperatorDomain registerBookingItemOperator;

    private RegisterBookingItemLodgerDomain registerBookingItemLodger;

    private String idItineraryItemQuotation;

    private TourDomain tour;

    private CustomerDomain customer;

    private CostTourDomain costTour;

    private CostTourServiceItemDomain costTourServiceItem;

    // Supplier Type Lodging

    private Integer lodgingCantSwb;
    private Integer lodgingCantMat;
    private Integer lodgingCantTwin;
    private Integer lodgingCantTrp;

    private LodgingCategoryDomain lodgingCategory;

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

    private ItineraryDateDomain lodgingCantBreakfastItineraryDate;
    private ItineraryDateDomain lodgingCantLunchItineraryDate;
    private ItineraryDateDomain lodgingCantDinnerItineraryDate;
    private ItineraryDateDomain lodgingCantBoxLunchItineraryDate;

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

    private ItineraryDateDomain lodgingCantBreakfastTcGuideItineraryDate;
    private ItineraryDateDomain lodgingCantLunchTcGuideItineraryDate;
    private ItineraryDateDomain lodgingCantDinnerTcGuideItineraryDate;
    private ItineraryDateDomain lodgingCantBoxLunchTcGuideItineraryDate;

    private Integer lodgingCantSwbTcGuide;
    private Integer lodgingCantTwinTcGuide;

    private Integer lodgingTcCantNight;

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

    private ItineraryDateDomain lodgingCantBreakfastTcLocalGuideItineraryDate;
    private ItineraryDateDomain lodgingCantLunchTcLocalGuideItineraryDate;
    private ItineraryDateDomain lodgingCantDinnerTcLocalGuideItineraryDate;
    private ItineraryDateDomain lodgingCantBoxLunchTcLocalGuideItineraryDate;

    private Integer lodgingCantSwbTcLocalGuide;
    private Integer lodgingCantTwinTcLocalGuide;

    private Integer lodgingTcLocalCantNight;

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

    private ItineraryDateDomain lodgingCantBreakfastGuideItineraryDate;
    private ItineraryDateDomain lodgingCantLunchGuideItineraryDate;
    private ItineraryDateDomain lodgingCantDinnerGuideItineraryDate;
    private ItineraryDateDomain lodgingCantBoxLunchGuideItineraryDate;

    private Integer lodgingCantSwbGuide;
    private Integer lodgingCantTwinGuide;

    private Integer lodgingGuideCantNight;

    // Supplier Type Lodge

    private Integer lodgeCantSwb;
    private Integer lodgeCantMat;
    private Integer lodgeCantTwin;
    private Integer lodgeCantTrp;

    private LodgingCategoryDomain lodgeCategory;

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

    private ItineraryDateDomain lodgeCantBreakfastItineraryDate;
    private ItineraryDateDomain lodgeCantLunchItineraryDate;
    private ItineraryDateDomain lodgeCantDinnerItineraryDate;
    private ItineraryDateDomain lodgeCantBoxLunchItineraryDate;

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

    private ItineraryDateDomain lodgeCantBreakfastTcGuideItineraryDate;
    private ItineraryDateDomain lodgeCantLunchTcGuideItineraryDate;
    private ItineraryDateDomain lodgeCantDinnerTcGuideItineraryDate;
    private ItineraryDateDomain lodgeCantBoxLunchTcGuideItineraryDate;

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

    private ItineraryDateDomain lodgeCantBreakfastTcLocalGuideItineraryDate;
    private ItineraryDateDomain lodgeCantLunchTcLocalGuideItineraryDate;
    private ItineraryDateDomain lodgeCantDinnerTcLocalGuideItineraryDate;
    private ItineraryDateDomain lodgeCantBoxLunchTcLocalGuideItineraryDate;

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

    private ItineraryDateDomain lodgeCantBreakfastGuideItineraryDate;
    private ItineraryDateDomain lodgeCantLunchGuideItineraryDate;
    private ItineraryDateDomain lodgeCantDinnerGuideItineraryDate;
    private ItineraryDateDomain lodgeCantBoxLunchGuideItineraryDate;

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

    private String stateItineraryService;

    private String note;

    // For Finance Exit Voucher

    private String stateServiceInvoice;

    private String noteInvoice;

    private LocalDate documentDateInvoice;

    // For Operation

    private Boolean serviceForOperation;

    private String generatedBy;

    @Getter(AccessLevel.NONE)
    private Integer cantPax;

    @Getter(AccessLevel.NONE)
    private Integer cantNight;

    @Getter(AccessLevel.NONE)
    private String roomItinerary;

    @Getter(AccessLevel.NONE)
    private String foodItinerary;

    @Getter(AccessLevel.NONE)
    private String strConfirmation;

    @Getter(AccessLevel.NONE)
    private String strReConfirmation;

    @Getter(AccessLevel.NONE)
    private String strItineraryTime;

    @Getter(AccessLevel.NONE)
    private String strTypeService;

    @Getter(AccessLevel.NONE)
    private String strTimeOrderService;

    @Getter(AccessLevel.NONE)
    private String stateItineraryServiceDescription;

    @Getter(AccessLevel.NONE)
    private String stateServiceInvoiceDescription;


    public Integer getCantPax(){
        Integer result = 0;
        /*if (paxTotal != null){
            result = result + paxTotal;
        }*/
        if (paxAdultTotal != null){
            result = result + paxAdultTotal;
        }
        if (paxChildrenTotal != null){
            result = result + paxChildrenTotal;
        }
        if (paxTcTotal != null){
            result = result + paxTcTotal;
        }
        if (paxTcLocalTotal != null){
            result = result + paxTcLocalTotal;
        }
        if (paxGuideTotal != null){
            result = result + paxGuideTotal;
        }
        return result;
    }

    public String getStrTimeOrderService(){
        if (orderServiceItem != null){
            return LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(orderServiceItem.getDateTimeService());
        }
        return "";
    }

    public String getStrTypeService(){
        StringBuffer result = new StringBuffer();
        if (supplier.getTypeInheritance().equals(TypeInheritanceEnum.SUPPLIER_OPERATOR.getName())){
            if ( operatorPvt != null && operatorPvt ){
                result.append("PVT");
            }
            if ( operatorSic != null && operatorSic ){
                result.append("SIC");
            }
        }else if (supplier.getTypeInheritance().equals(TypeInheritanceEnum.SUPPLIER_LODGE.getName())){
            if ( lodgePvt != null && lodgePvt ){
                result.append("PVT");
            }
            if ( lodgeSic != null && lodgeSic ){
                result.append("SIC");
            }
        }
        return result.toString();
    }

    public String getStrItineraryTime(){
        return LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(this.getItineraryDateTime());
    }

    public Integer getCantNight(){
        Integer result = 0;
        if (supplier.getTypeInheritance().equals(TypeInheritanceEnum.SUPPLIER_LODGING.getName())){
            result = lodgingCantNight;
        }else if (supplier.getTypeInheritance().equals(TypeInheritanceEnum.SUPPLIER_LODGE.getName())){
            result = lodgeCantNight;
        }
        return result;
    }

    public String getRoomItinerary(){
		StringBuffer result = new StringBuffer();
        if (supplier.getTypeInheritance().equals(TypeInheritanceEnum.SUPPLIER_LODGING.getName())){
            Integer lodgingCantSwbTotal = (lodgingCantSwb != null && lodgingCantSwb > 0 ? lodgingCantSwb : 0)
                                            + (lodgingCantSwbTcGuide != null && lodgingCantSwbTcGuide > 0 ? lodgingCantSwbTcGuide : 0)
                                            + (lodgingCantSwbTcLocalGuide != null && lodgingCantSwbTcLocalGuide > 0 ? lodgingCantSwbTcLocalGuide : 0)
                                            + (lodgingCantSwbGuide != null && lodgingCantSwbGuide > 0 ? lodgingCantSwbGuide : 0);
            if ( lodgingCantSwbTotal != null && lodgingCantSwbTotal > 0 ){
                result.append(lodgingCantSwbTotal + " SWB, ");
            }
            Integer lodgingCantTwinTotal = (lodgingCantTwin != null && lodgingCantTwin > 0 ? lodgingCantTwin : 0)
                    + (lodgingCantTwinTcGuide != null && lodgingCantTwinTcGuide > 0 ? lodgingCantTwinTcGuide : 0)
                    + (lodgingCantTwinTcLocalGuide != null && lodgingCantTwinTcLocalGuide > 0 ? lodgingCantTwinTcLocalGuide : 0)
                    + (lodgingCantTwinGuide != null && lodgingCantTwinGuide > 0 ? lodgingCantTwinGuide : 0);
            if ( lodgingCantTwinTotal != null && lodgingCantTwinTotal > 0 ){
                result.append(lodgingCantTwinTotal + " TWIN, ");
            }
            if ( lodgingCantMat != null && lodgingCantMat > 0 ){
                result.append(lodgingCantMat + " MAT, ");
            }
            if ( lodgingCantTrp != null && lodgingCantTrp > 0 ){
                result.append(lodgingCantTrp + " TRP, ");
            }
        }else if (supplier.getTypeInheritance().equals(TypeInheritanceEnum.SUPPLIER_LODGE.getName())){
            Integer lodgeCantSwbTotal = (lodgeCantSwb != null && lodgeCantSwb > 0 ? lodgeCantSwb : 0)
                    + (lodgeCantSwbTcGuide != null && lodgeCantSwbTcGuide > 0 ? lodgeCantSwbTcGuide : 0)
                    + (lodgeCantSwbTcLocalGuide != null && lodgeCantSwbTcLocalGuide > 0 ? lodgeCantSwbTcLocalGuide : 0)
                    + (lodgeCantSwbGuide != null && lodgeCantSwbGuide > 0 ? lodgeCantSwbGuide : 0);
            if ( lodgeCantSwbTotal != null && lodgeCantSwbTotal > 0 ){
                result.append(lodgeCantSwbTotal + " SWB, ");
            }
            Integer lodgeCantTwinTotal = (lodgeCantTwin != null && lodgeCantTwin > 0 ? lodgeCantTwin : 0)
                    + (lodgeCantTwinTcGuide != null && lodgeCantTwinTcGuide > 0 ? lodgeCantTwinTcGuide : 0)
                    + (lodgeCantTwinTcLocalGuide != null && lodgeCantTwinTcLocalGuide > 0 ? lodgeCantTwinTcLocalGuide : 0)
                    + (lodgeCantTwinGuide != null && lodgeCantTwinGuide > 0 ? lodgeCantTwinGuide : 0);
            if ( lodgeCantTwinTotal != null && lodgeCantTwinTotal > 0 ){
                result.append(lodgeCantTwinTotal + " TWIN, ");
            }
            if ( lodgeCantMat != null && lodgeCantMat > 0 ){
                result.append(lodgeCantMat + " MAT, ");
            }
            if ( lodgeCantTrp != null && lodgeCantTrp > 0 ){
                result.append(lodgeCantTrp + " TRP, ");
            }
        }
        if ( result.length() > 0 ){
            result.delete(result.length()-2, result.length()-1);
        }
        return result.toString();
    }

    public String getFoodItinerary(){
        StringBuffer result = new StringBuffer();
        if (supplier.getTypeInheritance().equals(TypeInheritanceEnum.SUPPLIER_LODGING.getName())){
            Map<String, Integer> map_list= new HashMap<>();
            Integer lodgingCantBreakfastTotal = (lodgingCantBreakfast != null && lodgingCantBreakfast > 0 ? lodgingCantBreakfast : 0)
                    + (lodgingCantBreakfastTcGuide != null && lodgingCantBreakfastTcGuide > 0 ? lodgingCantBreakfastTcGuide : 0)
                    + (lodgingCantBreakfastTcLocalGuide != null && lodgingCantBreakfastTcLocalGuide > 0 ? lodgingCantBreakfastTcLocalGuide : 0)
                    + (lodgingCantBreakfastGuide != null && lodgingCantBreakfastGuide > 0 ? lodgingCantBreakfastGuide : 0);

            if ( lodgingCantBreakfastTotal != null && lodgingCantBreakfastTotal > 0 ){
                map_list.put("DES. BUFF", lodgingCantBreakfastTotal);
            }else{
                map_list.put("DES. BUFF", 0);
            }

            Integer lodgingCantLunchTotal = (lodgingCantLunch != null && lodgingCantLunch > 0 ? lodgingCantLunch : 0)
                    + (lodgingCantLunchTcGuide != null && lodgingCantLunchTcGuide > 0 ? lodgingCantLunchTcGuide : 0)
                    + (lodgingCantLunchTcLocalGuide != null && lodgingCantLunchTcLocalGuide > 0 ? lodgingCantLunchTcLocalGuide : 0)
                    + (lodgingCantLunchGuide != null && lodgingCantLunchGuide > 0 ? lodgingCantLunchGuide : 0);

            if ( lodgingCantLunchTotal != null && lodgingCantLunchTotal > 0 ){
                map_list.put("ALM", lodgingCantLunchTotal);
            }else{
                map_list.put("ALM", 0);
            }

            Integer lodgingCantDinnerTotal = (lodgingCantDinner != null && lodgingCantDinner > 0 ? lodgingCantDinner : 0)
                    + (lodgingCantDinnerTcGuide != null && lodgingCantDinnerTcGuide > 0 ? lodgingCantDinnerTcGuide : 0)
                    + (lodgingCantDinnerTcLocalGuide != null && lodgingCantDinnerTcLocalGuide > 0 ? lodgingCantDinnerTcLocalGuide : 0)
                    + (lodgingCantDinnerGuide != null && lodgingCantDinnerGuide > 0 ? lodgingCantDinnerGuide : 0);

            if ( lodgingCantDinnerTotal != null && lodgingCantDinnerTotal > 0 ){
                map_list.put("CENA", lodgingCantDinnerTotal);
            }else{
                map_list.put("CENA", 0);
            }

            Integer lodgingCantBoxLunchTotal = (lodgingCantBoxLunch != null && lodgingCantBoxLunch > 0 ? lodgingCantBoxLunch : 0)
                    + (lodgingCantBoxLunchTcGuide != null && lodgingCantBoxLunchTcGuide > 0 ? lodgingCantBoxLunchTcGuide : 0)
                    + (lodgingCantBoxLunchTcLocalGuide != null && lodgingCantBoxLunchTcLocalGuide > 0 ? lodgingCantBoxLunchTcLocalGuide : 0)
                    + (lodgingCantBoxLunchGuide != null && lodgingCantBoxLunchGuide > 0 ? lodgingCantBoxLunchGuide : 0);

            if ( lodgingCantBoxLunchTotal != null && lodgingCantBoxLunchTotal > 0 ){
                map_list.put("BOX LUNCH", lodgingCantBoxLunchTotal);
            }else{
                map_list.put("BOX LUNCH", 0);
            }
            for (String type: map_list.keySet()
            ) {
                if (!map_list.get(type).equals(0)){
                    if ( type.equals("DES. BUFF") ){
                        result.append(map_list.get(type).toString() + " BB , ");
                    }
                    if ( type.equals("ALM") ){
                        result.append(map_list.get(type).toString() + " ALM , ");
                    }
                    if ( type.equals("CENA") ){
                        result.append(map_list.get(type).toString() + " CEN , ");
                    }
                    if ( type.equals("BOX LUNCH") ){
                        result.append(map_list.get(type).toString() + " BOX LUNCH , ");
                    }
                }
            }
        }else if (supplier.getTypeInheritance().equals(TypeInheritanceEnum.SUPPLIER_LODGE.getName()) && serviceGroup != null && serviceGroup.equals(ServiceGroupEnum.LODGING.getName())){
            Map<String, Integer> map_list= new HashMap<>();
            Integer lodgeCantBreakfastTotal = (lodgeCantBreakfast != null && lodgeCantBreakfast > 0 ? lodgeCantBreakfast : 0)
                    + (lodgeCantBreakfastTcGuide != null && lodgeCantBreakfastTcGuide > 0 ? lodgeCantBreakfastTcGuide : 0)
                    + (lodgeCantBreakfastTcLocalGuide != null && lodgeCantBreakfastTcLocalGuide > 0 ? lodgeCantBreakfastTcLocalGuide : 0)
                    + (lodgeCantBreakfastGuide != null && lodgeCantBreakfastGuide > 0 ? lodgeCantBreakfastGuide : 0);

            if ( lodgeCantBreakfastTotal != null && lodgeCantBreakfastTotal > 0 ){
                map_list.put("DES. BUFF", lodgeCantBreakfastTotal);
            }else{
                map_list.put("DES. BUFF", 0);
            }

            Integer lodgeCantLunchTotal = (lodgeCantLunch != null && lodgeCantLunch > 0 ? lodgeCantLunch : 0)
                    + (lodgeCantLunchTcGuide != null && lodgeCantLunchTcGuide > 0 ? lodgeCantLunchTcGuide : 0)
                    + (lodgeCantLunchTcLocalGuide != null && lodgeCantLunchTcLocalGuide > 0 ? lodgeCantLunchTcLocalGuide : 0)
                    + (lodgeCantLunchGuide != null && lodgeCantLunchGuide > 0 ? lodgeCantLunchGuide : 0);

            if ( lodgeCantLunchTotal != null && lodgeCantLunchTotal > 0 ){
                map_list.put("ALM", lodgeCantLunchTotal);
            }else{
                map_list.put("ALM", 0);
            }

            Integer lodgeCantDinnerTotal = (lodgeCantDinner != null && lodgeCantDinner > 0 ? lodgeCantDinner : 0)
                    + (lodgeCantDinnerTcGuide != null && lodgeCantDinnerTcGuide > 0 ? lodgeCantDinnerTcGuide : 0)
                    + (lodgeCantDinnerTcLocalGuide != null && lodgeCantDinnerTcLocalGuide > 0 ? lodgeCantDinnerTcLocalGuide : 0)
                    + (lodgeCantDinnerGuide != null && lodgeCantDinnerGuide > 0 ? lodgeCantDinnerGuide : 0);

            if ( lodgeCantDinnerTotal != null && lodgeCantDinnerTotal > 0 ){
                map_list.put("CENA", lodgeCantDinnerTotal);
            }else{
                map_list.put("CENA", 0);
            }

            Integer lodgeCantBoxLunchTotal = (lodgeCantBoxLunch != null && lodgeCantBoxLunch > 0 ? lodgeCantBoxLunch : 0)
                    + (lodgeCantBoxLunchTcGuide != null && lodgeCantBoxLunchTcGuide > 0 ? lodgeCantBoxLunchTcGuide : 0)
                    + (lodgeCantBoxLunchTcLocalGuide != null && lodgeCantBoxLunchTcLocalGuide > 0 ? lodgeCantBoxLunchTcLocalGuide : 0)
                    + (lodgeCantBoxLunchGuide != null && lodgeCantBoxLunchGuide > 0 ? lodgeCantBoxLunchGuide : 0);

            if ( lodgeCantBoxLunchTotal != null && lodgeCantBoxLunchTotal > 0 ){
                map_list.put("BOX LUNCH", lodgeCantBoxLunchTotal);
            }else{
                map_list.put("BOX LUNCH", 0);
            }
            for (String type: map_list.keySet()
            ) {
                if (!map_list.get(type).equals(0)){
                    if ( type.equals("DES. BUFF") ){
                        result.append(map_list.get(type).toString() + " BB , ");
                    }
                    if ( type.equals("ALM") ){
                        result.append(map_list.get(type).toString() + " ALM , ");
                    }
                    if ( type.equals("CENA") ){
                        result.append(map_list.get(type).toString() + " CEN , ");
                    }
                    if ( type.equals("BOX LUNCH") ){
                        result.append(map_list.get(type).toString() + " BOX LUNCH , ");
                    }
                }
            }
        }else if (supplier.getTypeInheritance().equals(TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName())){
            Map<String, Integer> map_list= new HashMap<>();
            Integer restaurantCantBreakfastTotal = (restaurantCantBreakfast != null && restaurantCantBreakfast > 0 ? restaurantCantBreakfast : 0)
                    + (restaurantCantBreakfastTcGuide != null && restaurantCantBreakfastTcGuide > 0 ? restaurantCantBreakfastTcGuide : 0)
                    + (restaurantCantBreakfastTcLocalGuide != null && restaurantCantBreakfastTcLocalGuide > 0 ? restaurantCantBreakfastTcLocalGuide : 0)
                    + (restaurantCantBreakfastGuide != null && restaurantCantBreakfastGuide > 0 ? restaurantCantBreakfastGuide : 0);

            if ( restaurantCantBreakfastTotal != null && restaurantCantBreakfastTotal > 0 ){
                map_list.put("DES. BUFF", restaurantCantBreakfastTotal);
            }else{
                map_list.put("DES. BUFF", 0);
            }

            Integer restaurantCantLunchTotal = (restaurantCantLunch != null && restaurantCantLunch > 0 ? restaurantCantLunch : 0)
                    + (restaurantCantLunchTcGuide != null && restaurantCantLunchTcGuide > 0 ? restaurantCantLunchTcGuide : 0)
                    + (restaurantCantLunchTcLocalGuide != null && restaurantCantLunchTcLocalGuide > 0 ? restaurantCantLunchTcLocalGuide : 0)
                    + (restaurantCantLunchGuide != null && restaurantCantLunchGuide > 0 ? restaurantCantLunchGuide : 0);

            if ( restaurantCantLunchTotal != null && restaurantCantLunchTotal > 0 ){
                map_list.put("ALM", restaurantCantLunchTotal);
            }else{
                map_list.put("ALM", 0);
            }

            Integer restaurantCantDinnerTotal = (restaurantCantDinner != null && restaurantCantDinner > 0 ? restaurantCantDinner : 0)
                    + (restaurantCantDinnerTcGuide != null && restaurantCantDinnerTcGuide > 0 ? restaurantCantDinnerTcGuide : 0)
                    + (restaurantCantDinnerTcLocalGuide != null && restaurantCantDinnerTcLocalGuide > 0 ? restaurantCantDinnerTcLocalGuide : 0)
                    + (restaurantCantDinnerGuide != null && restaurantCantDinnerGuide > 0 ? restaurantCantDinnerGuide : 0);

            if ( restaurantCantDinnerTotal != null && restaurantCantDinnerTotal > 0 ){
                map_list.put("CENA", restaurantCantDinnerTotal);
            }else{
                map_list.put("CENA", 0);
            }

            Integer restaurantCantBoxLunchTotal = (restaurantCantBoxLunch != null && restaurantCantBoxLunch > 0 ? restaurantCantBoxLunch : 0)
                    + (restaurantCantBoxLunchTcGuide != null && restaurantCantBoxLunchTcGuide > 0 ? restaurantCantBoxLunchTcGuide : 0)
                    + (restaurantCantBoxLunchTcLocalGuide != null && restaurantCantBoxLunchTcLocalGuide > 0 ? restaurantCantBoxLunchTcLocalGuide : 0)
                    + (restaurantCantBoxLunchGuide != null && restaurantCantBoxLunchGuide > 0 ? restaurantCantBoxLunchGuide : 0);

            if ( restaurantCantBoxLunchTotal != null && restaurantCantBoxLunchTotal > 0 ){
                map_list.put("BOX LUNCH", restaurantCantBoxLunchTotal);
            }else{
                map_list.put("BOX LUNCH", 0);
            }
            for (String type: map_list.keySet()
            ) {
                if (!map_list.get(type).equals(0)){
                    if ( type.equals("DES. BUFF") ){
                        result.append(map_list.get(type).toString() + " BB , ");
                    }
                    if ( type.equals("ALM") ){
                        result.append(map_list.get(type).toString() + " ALM , ");
                    }
                    if ( type.equals("CENA") ){
                        result.append(map_list.get(type).toString() + " CEN , ");
                    }
                    if ( type.equals("BOX LUNCH") ){
                        result.append(map_list.get(type).toString() + " BOX LUNCH , ");
                    }
                }
            }
        }
        if ( result.length() > 1 ){
            result.delete(result.length()-2, result.length()-1);
        }
        return result.toString();
    }

    public String getStrConfirmation(){
        StringBuffer result = new StringBuffer();
        if ( registerBookingBase != null ){
            if ( registerBookingBase.getWithConfirmation() != null ){
                if ( registerBookingBase.getWithConfirmation() ){
                    result.append("S");
                }else {
                    result.append("N");
                }
            }else{
                result.append("N");
            }
        }else{
            result.append("N");
        }
        return result.toString();
    }

    public String getStrReConfirmation(){
        StringBuffer result = new StringBuffer();
        if ( registerBookingBase != null ){
            if ( registerBookingBase.getWithReConfirmation() != null ){
                if ( registerBookingBase.getWithReConfirmation() ){
                    result.append("S");
                }else {
                    result.append("N");
                }
            }else{
                result.append("N");
            }
        }else{
            result.append("N");
        }
        return result.toString();
    }

    @Override
    public int compareTo(ItineraryItemDomain other) {
        ItineraryItemDomain to = other;
        if ( itineraryDateTime != null && to.getItineraryDateTime() != null ){
            int value = itineraryDateTime.compareTo(to.getItineraryDateTime());
            return value;
        }else{
            if (index != null && to.getIndex() != null){
                int value = index.compareTo(to.getIndex());
                return value;
            }else{
                return 0;
            }
        }
    }

    public String getStateItineraryServiceDescription(){
        if (stateItineraryService != null && !stateItineraryService.isEmpty()){
            StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                    .map(StateBookingEnum.class)
                    .stream().filter(
                            stateBookingModel -> stateBookingModel.name().equals(stateItineraryService))
                    .findFirst().get();
            return stateBookingModelData.description();
        }else{
            return "";
        }
    }

    public String getStateServiceInvoiceDescription(){
        if (stateServiceInvoice != null && !stateServiceInvoice.isEmpty()){
            StateServiceInvoiceEnum.StateServiceInvoiceModel stateServiceInvoiceModelData = EnumMappeableService
                    .map(StateServiceInvoiceEnum.class)
                    .stream().filter(
                            stateServiceInvoiceModel -> stateServiceInvoiceModel.name().equals(stateServiceInvoice))
                    .findFirst().get();
            return stateServiceInvoiceModelData.description();
        }else{
            return "";
        }
    }

    @Getter(AccessLevel.NONE)
    private String strDate;

    public String getStrDate(){
        return LocalDateTimeUtil.toLocalDateTimeFormatShort(this.getItineraryDateTime());
    }

    @Getter(AccessLevel.NONE)
    private String strTime;

    public String getStrTime(){
        return LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(this.getItineraryDateTime());
    }

    @Getter(AccessLevel.NONE)
    private LocalDateTime truncateDate;

    public LocalDateTime getTruncateDate(){
        LocalDateTime localDateTime = LocalDateTime.of(
                itineraryDateTime.getYear(),
                itineraryDateTime.getMonth(),
                itineraryDateTime.getDayOfMonth(),
                0,
                0,
                0
        );
        return localDateTime;
    }

    @Getter(AccessLevel.NONE)
    private String strServiceComplete;

    public String getStrServiceComplete(){
        StringBuffer result = new StringBuffer();
        if ( service != null ){
            result.append(service.getAbbrev());
        }
        if ( operatorPvt != null && operatorPvt ){
            result.append("/PVT");
        }
        if ( operatorSic != null && operatorSic ){
            result.append("/SIC");
        }
        return result.toString();
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
