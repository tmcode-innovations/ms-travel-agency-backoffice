package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itineraryitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion.DepartmentRegionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itinerarydate.ItineraryDateResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.QuotationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.TourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.lodgingcategory.LodgingCategoryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.ServiceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.ServiceGroupEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryItemResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("serviceGroup")
    private String serviceGroup;

    @JsonProperty("serviceGroupModel")
    private ServiceGroupEnum.ServiceGroupModel serviceGroupModel;

    @JsonProperty("serviceGroupDescription")
    private String serviceGroupDescription;

    @JsonProperty("itineraryDateTime")
    private LocalDateTime itineraryDateTime;

    @JsonProperty("itineraryDateTimeStr")
    private String itineraryDateTimeStr;

    @JsonProperty("paxTotal")
    private Integer paxTotal;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("paxAdultTotal")
    private Integer paxAdultTotal;

    @JsonProperty("priceAdult")
    private Double priceAdult;

    @JsonProperty("paxChildrenTotal")
    private Integer paxChildrenTotal;

    @JsonProperty("priceChildren")
    private Double priceChildren;

    @JsonProperty("paxTcTotal")
    private Integer paxTcTotal;

    @JsonProperty("priceTc")
    private Double priceTc;

    @JsonProperty("paxTcLocalTotal")
    private Integer paxTcLocalTotal;

    @JsonProperty("priceTcLocal")
    private Double priceTcLocal;

    @JsonProperty("paxGuideTotal")
    private Integer paxGuideTotal;

    @JsonProperty("priceGuide")
    private Double priceGuide;

    @JsonProperty("quotation")
    private QuotationResponse quotation;

    @JsonProperty("itineraryDate")
    ItineraryDateResponse itineraryDate;

    @JsonProperty("supplierType")
    SupplierTypeResponse supplierType;

    @JsonProperty("supplier")
    SupplierResponse supplier;

    @JsonProperty("service")
    ServiceResponse service;

    @JsonProperty("departmentRegion")
    DepartmentRegionResponse departmentRegion;

    @JsonProperty("tour")
    TourResponse tour;

    // Supplier Type Lodging

    @JsonProperty("lodgingCantSwb")
    private Integer lodgingCantSwb;

    @JsonProperty("lodgingCantMat")
    private Integer lodgingCantMat;

    @JsonProperty("lodgingCantTwin")
    private Integer lodgingCantTwin;

    @JsonProperty("lodgingCantTrp")
    private Integer lodgingCantTrp;

    @JsonProperty("lodgingCategory")
    private LodgingCategoryResponse lodgingCategory;

    @JsonProperty("lodgingCantBreakfast")
    private Integer lodgingCantBreakfast;

    @JsonProperty("lodgingCantLunch")
    private Integer lodgingCantLunch;

    @JsonProperty("lodgingCantDinner")
    private Integer lodgingCantDinner;

    @JsonProperty("lodgingCantBoxLunch")
    private Integer lodgingCantBoxLunch;

    @JsonProperty("lodgingCantNight")
    private Integer lodgingCantNight;

    @JsonProperty("lodgingCantBreakfastTcGuide")
    private Integer lodgingCantBreakfastTcGuide;

    @JsonProperty("lodgingCantLunchTcGuide")
    private Integer lodgingCantLunchTcGuide;

    @JsonProperty("lodgingCantDinnerTcGuide")
    private Integer lodgingCantDinnerTcGuide;

    @JsonProperty("lodgingCantBoxLunchTcGuide")
    private Integer lodgingCantBoxLunchTcGuide;

    @JsonProperty("lodgingCantSwbTcGuide")
    private Integer lodgingCantSwbTcGuide;

    @JsonProperty("lodgingCantTwinTcGuide")
    private Integer lodgingCantTwinTcGuide;

    @JsonProperty("lodgingTcCantNight")
    private Integer lodgingTcCantNight;

    @JsonProperty("lodgingCantBreakfastTcLocalGuide")
    private Integer lodgingCantBreakfastTcLocalGuide;

    @JsonProperty("lodgingCantLunchTcLocalGuide")
    private Integer lodgingCantLunchTcLocalGuide;

    @JsonProperty("lodgingCantDinnerTcLocalGuide")
    private Integer lodgingCantDinnerTcLocalGuide;

    @JsonProperty("lodgingCantBoxLunchTcLocalGuide")
    private Integer lodgingCantBoxLunchTcLocalGuide;

    @JsonProperty("lodgingCantSwbTcLocalGuide")
    private Integer lodgingCantSwbTcLocalGuide;

    @JsonProperty("lodgingCantTwinTcLocalGuide")
    private Integer lodgingCantTwinTcLocalGuide;

    @JsonProperty("lodgingTcLocalCantNight")
    private Integer lodgingTcLocalCantNight;

    @JsonProperty("lodgingCantBreakfastGuide")
    private Integer lodgingCantBreakfastGuide;

    @JsonProperty("lodgingCantLunchGuide")
    private Integer lodgingCantLunchGuide;

    @JsonProperty("lodgingCantDinnerGuide")
    private Integer lodgingCantDinnerGuide;

    @JsonProperty("lodgingCantBoxLunchGuide")
    private Integer lodgingCantBoxLunchGuide;

    @JsonProperty("lodgingCantSwbGuide")
    private Integer lodgingCantSwbGuide;

    @JsonProperty("lodgingCantTwinGuide")
    private Integer lodgingCantTwinGuide;

    @JsonProperty("lodgingGuideCantNight")
    private Integer lodgingGuideCantNight;

    // Supplier Type Lodge

    @JsonProperty("lodgeCantSwb")
    private Integer lodgeCantSwb;

    @JsonProperty("lodgeCantMat")
    private Integer lodgeCantMat;

    @JsonProperty("lodgeCantTwin")
    private Integer lodgeCantTwin;

    @JsonProperty("lodgeCantTrp")
    private Integer lodgeCantTrp;

    @JsonProperty("lodgeCategory")
    private LodgingCategoryResponse lodgeCategory;

    @JsonProperty("lodgeCantBreakfast")
    private Integer lodgeCantBreakfast;

    @JsonProperty("lodgeCantLunch")
    private Integer lodgeCantLunch;

    @JsonProperty("lodgeCantDinner")
    private Integer lodgeCantDinner;

    @JsonProperty("lodgeCantBoxLunch")
    private Integer lodgeCantBoxLunch;

    @JsonProperty("lodgeCantNight")
    private Integer lodgeCantNight;

    @JsonProperty("lodgeCantBreakfastTcGuide")
    private Integer lodgeCantBreakfastTcGuide;

    @JsonProperty("lodgeCantLunchTcGuide")
    private Integer lodgeCantLunchTcGuide;

    @JsonProperty("lodgeCantDinnerTcGuide")
    private Integer lodgeCantDinnerTcGuide;

    @JsonProperty("lodgeCantBoxLunchTcGuide")
    private Integer lodgeCantBoxLunchTcGuide;

    @JsonProperty("lodgeCantSwbTcGuide")
    private Integer lodgeCantSwbTcGuide;

    @JsonProperty("lodgeCantTwinTcGuide")
    private Integer lodgeCantTwinTcGuide;

    @JsonProperty("lodgeTcCantNight")
    private Integer lodgeTcCantNight;

    @JsonProperty("lodgeCantBreakfastTcLocalGuide")
    private Integer lodgeCantBreakfastTcLocalGuide;

    @JsonProperty("lodgeCantLunchTcLocalGuide")
    private Integer lodgeCantLunchTcLocalGuide;

    @JsonProperty("lodgeCantDinnerTcLocalGuide")
    private Integer lodgeCantDinnerTcLocalGuide;

    @JsonProperty("lodgeCantBoxLunchTcLocalGuide")
    private Integer lodgeCantBoxLunchTcLocalGuide;

    @JsonProperty("lodgeCantSwbTcLocalGuide")
    private Integer lodgeCantSwbTcLocalGuide;

    @JsonProperty("lodgeCantTwinTcLocalGuide")
    private Integer lodgeCantTwinTcLocalGuide;

    @JsonProperty("lodgeTcLocalCantNight")
    private Integer lodgeTcLocalCantNight;

    @JsonProperty("lodgeCantBreakfastGuide")
    private Integer lodgeCantBreakfastGuide;

    @JsonProperty("lodgeCantLunchGuide")
    private Integer lodgeCantLunchGuide;

    @JsonProperty("lodgeCantDinnerGuide")
    private Integer lodgeCantDinnerGuide;

    @JsonProperty("lodgeCantBoxLunchGuide")
    private Integer lodgeCantBoxLunchGuide;

    @JsonProperty("lodgeCantSwbGuide")
    private Integer lodgeCantSwbGuide;

    @JsonProperty("lodgeCantTwinGuide")
    private Integer lodgeCantTwinGuide;

    @JsonProperty("lodgeGuideCantNight")
    private Integer lodgeGuideCantNight;

    @JsonProperty("lodgePvt")
    private Boolean lodgePvt;

    @JsonProperty("lodgeSic")
    private Boolean lodgeSic;

    // Supplier Type Restaurant

    @JsonProperty("restaurantCantBreakfast")
    private Integer restaurantCantBreakfast;

    @JsonProperty("restaurantCantLunch")
    private Integer restaurantCantLunch;

    @JsonProperty("restaurantCantDinner")
    private Integer restaurantCantDinner;

    @JsonProperty("restaurantCantBoxLunch")
    private Integer restaurantCantBoxLunch;

    @JsonProperty("restaurantCantBreakfastTcGuide")
    private Integer restaurantCantBreakfastTcGuide;

    @JsonProperty("restaurantCantLunchTcGuide")
    private Integer restaurantCantLunchTcGuide;

    @JsonProperty("restaurantCantDinnerTcGuide")
    private Integer restaurantCantDinnerTcGuide;

    @JsonProperty("restaurantCantBoxLunchTcGuide")
    private Integer restaurantCantBoxLunchTcGuide;

    @JsonProperty("restaurantCantBreakfastTcLocalGuide")
    private Integer restaurantCantBreakfastTcLocalGuide;

    @JsonProperty("restaurantCantLunchTcLocalGuide")
    private Integer restaurantCantLunchTcLocalGuide;

    @JsonProperty("restaurantCantDinnerTcLocalGuide")
    private Integer restaurantCantDinnerTcLocalGuide;

    @JsonProperty("restaurantCantBoxLunchTcLocalGuide")
    private Integer restaurantCantBoxLunchTcLocalGuide;

    @JsonProperty("restaurantCantBreakfastGuide")
    private Integer restaurantCantBreakfastGuide;

    @JsonProperty("restaurantCantLunchGuide")
    private Integer restaurantCantLunchGuide;

    @JsonProperty("restaurantCantDinnerGuide")
    private Integer restaurantCantDinnerGuide;

    @JsonProperty("restaurantCantBoxLunchGuide")
    private Integer restaurantCantBoxLunchGuide;

    @JsonProperty("restaurantCantBreakfastDrive")
    private Integer restaurantCantBreakfastDrive;

    @JsonProperty("restaurantCantLunchDrive")
    private Integer restaurantCantLunchDrive;

    @JsonProperty("restaurantCantDinnerDrive")
    private Integer restaurantCantDinnerDrive;

    @JsonProperty("restaurantCantBoxLunchDrive")
    private Integer restaurantCantBoxLunchDrive;

    // Supplier Type Operator

    @JsonProperty("operatorPvt")
    private Boolean operatorPvt;

    @JsonProperty("operatorSic")
    private Boolean operatorSic;

    @JsonProperty("lodgingForOperator")
    private SupplierLodgingResponse lodgingForOperator;

    @JsonProperty("lodgingForOperatorStr")
    private String lodgingForOperatorStr;

    // Data By
    @JsonProperty("createdBy")
    private UserBaseResponse createdBy;

    @JsonProperty("updatedBy")
    private UserBaseResponse updatedBy;

    @JsonProperty("deletedBy")
    private UserBaseResponse deletedBy;

    // Data At

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("createdAtStrLong")
    private String createdAtStrLong;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("updatedAtStrLong")
    private String updatedAtStrLong;

    @JsonProperty("deletedAt")
    private LocalDateTime deletedAt;

    @JsonProperty("deletedAtStrLong")
    private String deletedAtStrLong;

    public static ItineraryItemResponse from(ItineraryItemDomain domain) {
        ItineraryItemResponse itineraryItemResponse = new ItineraryItemResponse();
        itineraryItemResponse.setId(domain.getId());
        itineraryItemResponse.setName(domain.getName());
        itineraryItemResponse.setDescription(domain.getDescription());
        itineraryItemResponse.setIdentify(domain.getIdentify());
        itineraryItemResponse.setObservation(domain.getObservation());
        if (domain.getServiceGroup() != null && !domain.getServiceGroup().isEmpty()){
            itineraryItemResponse.setServiceGroup(domain.getServiceGroup());
            ServiceGroupEnum.ServiceGroupModel serviceGroupModelData = EnumMappeableService
                    .map(ServiceGroupEnum.class)
                    .stream().filter(
                            serviceGroupModel -> serviceGroupModel.name().equals(domain.getServiceGroup()))
                    .findFirst().get();
            itineraryItemResponse.setServiceGroupDescription(serviceGroupModelData.description());
            itineraryItemResponse.setServiceGroupModel(serviceGroupModelData);
        }
        itineraryItemResponse.setItineraryDateTime(domain.getItineraryDateTime());
        itineraryItemResponse.setItineraryDateTimeStr(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(domain.getItineraryDateTime()));
        itineraryItemResponse.setPaxTotal(domain.getPaxTotal());
        itineraryItemResponse.setPrice(domain.getPrice());
        itineraryItemResponse.setPaxAdultTotal(domain.getPaxAdultTotal());
        itineraryItemResponse.setPriceAdult(domain.getPriceAdult());
        itineraryItemResponse.setPaxChildrenTotal(domain.getPaxChildrenTotal());
        itineraryItemResponse.setPriceChildren(domain.getPriceChildren());
        itineraryItemResponse.setPaxTcTotal(domain.getPaxTcTotal());
        itineraryItemResponse.setPriceTc(domain.getPriceTc());
        itineraryItemResponse.setPaxTcLocalTotal(domain.getPaxTcLocalTotal());
        itineraryItemResponse.setPriceTcLocal(domain.getPriceTcLocal());
        itineraryItemResponse.setPaxGuideTotal(domain.getPaxGuideTotal());
        itineraryItemResponse.setPriceGuide(domain.getPriceGuide());

        if (domain.getItineraryDate() != null &&
                domain.getItineraryDate().getId() != null &&
                !domain.getItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setItineraryDate(ItineraryDateResponse.from(domain.getItineraryDate()));
        }
        if (domain.getQuotation() != null &&
                domain.getQuotation().getId() != null &&
                !domain.getQuotation().getId().isEmpty()){
            itineraryItemResponse.setQuotation(QuotationResponse.from(domain.getQuotation()));
        }
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            itineraryItemResponse.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getSupplier() != null &&
                domain.getSupplier().getId() != null &&
                !domain.getSupplier().getId().isEmpty()){
            itineraryItemResponse.setSupplier(SupplierResponse.from(domain.getSupplier()));
        }
        if (domain.getService() != null &&
                domain.getService().getId() != null &&
                !domain.getService().getId().isEmpty()){
            itineraryItemResponse.setService(ServiceResponse.from(domain.getService()));
        }
        if (domain.getDepartmentRegion() != null &&
                domain.getDepartmentRegion().getId() != null &&
                !domain.getDepartmentRegion().getId().isEmpty()){
            itineraryItemResponse.setDepartmentRegion(DepartmentRegionResponse.from(domain.getDepartmentRegion()));
        }
        if (domain.getTour() != null &&
                domain.getTour().getId() != null &&
                !domain.getTour().getId().isEmpty()){
            itineraryItemResponse.setTour(TourResponse.from(domain.getTour()));
        }

        // Supplier Type Lodging

        itineraryItemResponse.setLodgingCantSwb(domain.getLodgingCantSwb());
        itineraryItemResponse.setLodgingCantMat(domain.getLodgingCantMat());
        itineraryItemResponse.setLodgingCantTwin(domain.getLodgingCantTwin());
        itineraryItemResponse.setLodgingCantTrp(domain.getLodgingCantTrp());

        itineraryItemResponse.setLodgingCantBreakfast(domain.getLodgingCantBreakfast());
        itineraryItemResponse.setLodgingCantLunch(domain.getLodgingCantLunch());
        itineraryItemResponse.setLodgingCantDinner(domain.getLodgingCantDinner());
        itineraryItemResponse.setLodgingCantBoxLunch(domain.getLodgingCantBoxLunch());

        if (domain.getLodgingCategory() != null &&
                domain.getLodgingCategory().getId() != null &&
                !domain.getLodgingCategory().getId().isEmpty()){
            itineraryItemResponse.setLodgingCategory(LodgingCategoryResponse.from(domain.getLodgingCategory()));
        }

        itineraryItemResponse.setLodgingCantNight(domain.getLodgingCantNight());

        itineraryItemResponse.setLodgingCantBreakfastTcGuide(domain.getLodgingCantBreakfastTcGuide());
        itineraryItemResponse.setLodgingCantLunchTcGuide(domain.getLodgingCantLunchTcGuide());
        itineraryItemResponse.setLodgingCantDinnerTcGuide(domain.getLodgingCantDinnerTcGuide());
        itineraryItemResponse.setLodgingCantBoxLunchTcGuide(domain.getLodgingCantBoxLunchTcGuide());

        itineraryItemResponse.setLodgingCantSwbTcGuide(domain.getLodgingCantSwbTcGuide());
        itineraryItemResponse.setLodgingCantTwinTcGuide(domain.getLodgingCantTwinTcGuide());

        itineraryItemResponse.setLodgingTcCantNight(domain.getLodgingTcCantNight());

        itineraryItemResponse.setLodgingCantBreakfastTcLocalGuide(domain.getLodgingCantBreakfastTcLocalGuide());
        itineraryItemResponse.setLodgingCantLunchTcLocalGuide(domain.getLodgingCantLunchTcLocalGuide());
        itineraryItemResponse.setLodgingCantDinnerTcLocalGuide(domain.getLodgingCantDinnerTcLocalGuide());
        itineraryItemResponse.setLodgingCantBoxLunchTcLocalGuide(domain.getLodgingCantBoxLunchTcLocalGuide());

        itineraryItemResponse.setLodgingCantSwbTcLocalGuide(domain.getLodgingCantSwbTcLocalGuide());
        itineraryItemResponse.setLodgingCantTwinTcLocalGuide(domain.getLodgingCantTwinTcLocalGuide());

        itineraryItemResponse.setLodgingTcLocalCantNight(domain.getLodgingTcLocalCantNight());

        itineraryItemResponse.setLodgingCantBreakfastGuide(domain.getLodgingCantBreakfastGuide());
        itineraryItemResponse.setLodgingCantLunchGuide(domain.getLodgingCantLunchGuide());
        itineraryItemResponse.setLodgingCantDinnerGuide(domain.getLodgingCantDinnerGuide());
        itineraryItemResponse.setLodgingCantBoxLunchGuide(domain.getLodgingCantBoxLunchGuide());

        itineraryItemResponse.setLodgingCantSwbGuide(domain.getLodgingCantSwbGuide());
        itineraryItemResponse.setLodgingCantTwinGuide(domain.getLodgingCantTwinGuide());

        itineraryItemResponse.setLodgingGuideCantNight(domain.getLodgingGuideCantNight());

        // Supplier Type Lodge

        itineraryItemResponse.setLodgeCantSwb(domain.getLodgeCantSwb());
        itineraryItemResponse.setLodgeCantMat(domain.getLodgeCantMat());
        itineraryItemResponse.setLodgeCantTwin(domain.getLodgeCantTwin());
        itineraryItemResponse.setLodgeCantTrp(domain.getLodgeCantTrp());

        itineraryItemResponse.setLodgeCantBreakfast(domain.getLodgeCantBreakfast());
        itineraryItemResponse.setLodgeCantLunch(domain.getLodgeCantLunch());
        itineraryItemResponse.setLodgeCantDinner(domain.getLodgeCantDinner());
        itineraryItemResponse.setLodgeCantBoxLunch(domain.getLodgeCantBoxLunch());

        if (domain.getLodgeCategory() != null &&
                domain.getLodgeCategory().getId() != null &&
                !domain.getLodgeCategory().getId().isEmpty()){
            itineraryItemResponse.setLodgeCategory(LodgingCategoryResponse.from(domain.getLodgeCategory()));
        }

        itineraryItemResponse.setLodgeCantNight(domain.getLodgeCantNight());

        itineraryItemResponse.setLodgeCantBreakfastTcGuide(domain.getLodgeCantBreakfastTcGuide());
        itineraryItemResponse.setLodgeCantLunchTcGuide(domain.getLodgeCantLunchTcGuide());
        itineraryItemResponse.setLodgeCantDinnerTcGuide(domain.getLodgeCantDinnerTcGuide());
        itineraryItemResponse.setLodgeCantBoxLunchTcGuide(domain.getLodgeCantBoxLunchTcGuide());

        itineraryItemResponse.setLodgeCantSwbTcGuide(domain.getLodgeCantSwbTcGuide());
        itineraryItemResponse.setLodgeCantTwinTcGuide(domain.getLodgeCantTwinTcGuide());

        itineraryItemResponse.setLodgeTcCantNight(domain.getLodgeTcCantNight());

        itineraryItemResponse.setLodgeCantBreakfastTcLocalGuide(domain.getLodgeCantBreakfastTcLocalGuide());
        itineraryItemResponse.setLodgeCantLunchTcLocalGuide(domain.getLodgeCantLunchTcLocalGuide());
        itineraryItemResponse.setLodgeCantDinnerTcLocalGuide(domain.getLodgeCantDinnerTcLocalGuide());
        itineraryItemResponse.setLodgeCantBoxLunchTcLocalGuide(domain.getLodgeCantBoxLunchTcLocalGuide());

        itineraryItemResponse.setLodgeCantSwbTcLocalGuide(domain.getLodgeCantSwbTcLocalGuide());
        itineraryItemResponse.setLodgeCantTwinTcLocalGuide(domain.getLodgeCantTwinTcLocalGuide());

        itineraryItemResponse.setLodgeTcLocalCantNight(domain.getLodgeTcLocalCantNight());

        itineraryItemResponse.setLodgeCantBreakfastGuide(domain.getLodgeCantBreakfastGuide());
        itineraryItemResponse.setLodgeCantLunchGuide(domain.getLodgeCantLunchGuide());
        itineraryItemResponse.setLodgeCantDinnerGuide(domain.getLodgeCantDinnerGuide());
        itineraryItemResponse.setLodgeCantBoxLunchGuide(domain.getLodgeCantBoxLunchGuide());

        itineraryItemResponse.setLodgeCantSwbGuide(domain.getLodgeCantSwbGuide());
        itineraryItemResponse.setLodgeCantTwinGuide(domain.getLodgeCantTwinGuide());

        itineraryItemResponse.setLodgeGuideCantNight(domain.getLodgeGuideCantNight());

        itineraryItemResponse.setLodgePvt(domain.getLodgePvt());
        itineraryItemResponse.setLodgeSic(domain.getLodgeSic());

        // Supplier Type Restaurant

        itineraryItemResponse.setRestaurantCantBreakfast(domain.getRestaurantCantBreakfast());
        itineraryItemResponse.setRestaurantCantLunch(domain.getRestaurantCantLunch());
        itineraryItemResponse.setRestaurantCantDinner(domain.getRestaurantCantDinner());
        itineraryItemResponse.setRestaurantCantBoxLunch(domain.getRestaurantCantBoxLunch());

        itineraryItemResponse.setRestaurantCantBreakfastTcGuide(domain.getRestaurantCantBreakfastTcGuide());
        itineraryItemResponse.setRestaurantCantLunchTcGuide(domain.getRestaurantCantLunchTcGuide());
        itineraryItemResponse.setRestaurantCantDinnerTcGuide(domain.getRestaurantCantDinnerTcGuide());
        itineraryItemResponse.setRestaurantCantBoxLunchTcGuide(domain.getRestaurantCantBoxLunchTcGuide());

        itineraryItemResponse.setRestaurantCantBreakfastTcLocalGuide(domain.getRestaurantCantBreakfastTcLocalGuide());
        itineraryItemResponse.setRestaurantCantLunchTcLocalGuide(domain.getRestaurantCantLunchTcLocalGuide());
        itineraryItemResponse.setRestaurantCantDinnerTcLocalGuide(domain.getRestaurantCantDinnerTcLocalGuide());
        itineraryItemResponse.setRestaurantCantBoxLunchTcLocalGuide(domain.getRestaurantCantBoxLunchTcLocalGuide());

        itineraryItemResponse.setRestaurantCantBreakfastGuide(domain.getRestaurantCantBreakfastGuide());
        itineraryItemResponse.setRestaurantCantLunchGuide(domain.getRestaurantCantLunchGuide());
        itineraryItemResponse.setRestaurantCantDinnerGuide(domain.getRestaurantCantDinnerGuide());
        itineraryItemResponse.setRestaurantCantBoxLunchGuide(domain.getRestaurantCantBoxLunchGuide());

        itineraryItemResponse.setRestaurantCantBreakfastDrive(domain.getRestaurantCantBreakfastDrive());
        itineraryItemResponse.setRestaurantCantLunchDrive(domain.getRestaurantCantLunchDrive());
        itineraryItemResponse.setRestaurantCantDinnerDrive(domain.getRestaurantCantDinnerDrive());
        itineraryItemResponse.setRestaurantCantBoxLunchDrive(domain.getRestaurantCantBoxLunchDrive());

        // Supplier Type Operator

        itineraryItemResponse.setOperatorPvt(domain.getOperatorPvt());
        itineraryItemResponse.setOperatorSic(domain.getOperatorSic());

        itineraryItemResponse.setLodgingForOperatorStr(domain.getLodgingForOperatorStr());

        if (domain.getLodgingForOperator() != null &&
                domain.getLodgingForOperator().getId() != null &&
                !domain.getLodgingForOperator().getId().isEmpty()){
            itineraryItemResponse.setLodgingForOperator(SupplierLodgingResponse.from(domain.getLodgingForOperator()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            itineraryItemResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            itineraryItemResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            itineraryItemResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        itineraryItemResponse.setCreatedAt(domain.getCreatedAt());
        itineraryItemResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        itineraryItemResponse.setUpdatedAt(domain.getUpdatedAt());
        itineraryItemResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        itineraryItemResponse.setDeletedAt(domain.getDeletedAt());
        itineraryItemResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));

        return itineraryItemResponse;
    }
}
