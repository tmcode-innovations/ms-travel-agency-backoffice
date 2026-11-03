package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion.DepartmentRegionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itinerarydate.ItineraryDateResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.QuotationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.TourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.lodgingcategory.LodgingCategoryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.ServiceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.GenderEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateRegisterGroupEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateServiceInvoiceEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.ServiceGroupEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
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

    @JsonProperty("pax")
    private Integer pax;

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

    @JsonProperty("stateBookingDescription")
    private String stateBookingDescription;

    @JsonProperty("registerGroup")
    private RegisterGroupResponse registerGroup;

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

    @JsonProperty("customer")
    CustomerResponse customer;

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

    @JsonProperty("lodgingCategoryStr")
    private String lodgingCategoryStr;

    @JsonProperty("lodgingCantBreakfast")
    private Integer lodgingCantBreakfast;

    @JsonProperty("lodgingCantLunch")
    private Integer lodgingCantLunch;

    @JsonProperty("lodgingCantDinner")
    private Integer lodgingCantDinner;

    @JsonProperty("lodgingCantBoxLunch")
    private Integer lodgingCantBoxLunch;

    @JsonProperty("lodgingCantBreakfastDateTime")
    private LocalDateTime lodgingCantBreakfastDateTime;

    @JsonProperty("lodgingCantLunchDateTime")
    private LocalDateTime lodgingCantLunchDateTime;

    @JsonProperty("lodgingCantDinnerDateTime")
    private LocalDateTime lodgingCantDinnerDateTime;

    @JsonProperty("lodgingCantBoxLunchDateTime")
    private LocalDateTime lodgingCantBoxLunchDateTime;

    @JsonProperty("lodgingCantBreakfastDate")
    private LocalDate lodgingCantBreakfastDate;

    @JsonProperty("lodgingCantLunchDate")
    private LocalDate lodgingCantLunchDate;

    @JsonProperty("lodgingCantDinnerDate")
    private LocalDate lodgingCantDinnerDate;

    @JsonProperty("lodgingCantBoxLunchDate")
    private LocalDate lodgingCantBoxLunchDate;

    @JsonProperty("lodgingCantBreakfastDateStr")
    private String lodgingCantBreakfastDateStr;

    @JsonProperty("lodgingCantLunchDateStr")
    private String lodgingCantLunchDateStr;

    @JsonProperty("lodgingCantDinnerDateStr")
    private String lodgingCantDinnerDateStr;

    @JsonProperty("lodgingCantBoxLunchDateStr")
    private String lodgingCantBoxLunchDateStr;

    @JsonProperty("lodgingCantBreakfastItineraryDate")
    private ItineraryDateResponse lodgingCantBreakfastItineraryDate;

    @JsonProperty("lodgingCantLunchItineraryDate")
    private ItineraryDateResponse lodgingCantLunchItineraryDate;

    @JsonProperty("lodgingCantDinnerItineraryDate")
    private ItineraryDateResponse lodgingCantDinnerItineraryDate;

    @JsonProperty("lodgingCantBoxLunchItineraryDate")
    private ItineraryDateResponse lodgingCantBoxLunchItineraryDate;

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

    @JsonProperty("lodgingCantBreakfastTcGuideDateTime")
    private LocalDateTime lodgingCantBreakfastTcGuideDateTime;

    @JsonProperty("lodgingCantLunchTcGuideDateTime")
    private LocalDateTime lodgingCantLunchTcGuideDateTime;

    @JsonProperty("lodgingCantDinnerTcGuideDateTime")
    private LocalDateTime lodgingCantDinnerTcGuideDateTime;

    @JsonProperty("lodgingCantBoxLunchTcGuideDateTime")
    private LocalDateTime lodgingCantBoxLunchTcGuideDateTime;

    @JsonProperty("lodgingCantBreakfastTcGuideDate")
    private LocalDate lodgingCantBreakfastTcGuideDate;

    @JsonProperty("lodgingCantLunchTcGuideDate")
    private LocalDate lodgingCantLunchTcGuideDate;

    @JsonProperty("lodgingCantDinnerTcGuideDate")
    private LocalDate lodgingCantDinnerTcGuideDate;

    @JsonProperty("lodgingCantBoxLunchTcGuideDate")
    private LocalDate lodgingCantBoxLunchTcGuideDate;

    @JsonProperty("lodgingCantBreakfastTcGuideDateStr")
    private String lodgingCantBreakfastTcGuideDateStr;

    @JsonProperty("lodgingCantLunchTcGuideDateStr")
    private String lodgingCantLunchTcGuideDateStr;

    @JsonProperty("lodgingCantDinnerTcGuideDateStr")
    private String lodgingCantDinnerTcGuideDateStr;

    @JsonProperty("lodgingCantBoxLunchTcGuideDateStr")
    private String lodgingCantBoxLunchTcGuideDateStr;

    @JsonProperty("lodgingCantBreakfastTcGuideItineraryDate")
    private ItineraryDateResponse lodgingCantBreakfastTcGuideItineraryDate;

    @JsonProperty("lodgingCantLunchTcGuideItineraryDate")
    private ItineraryDateResponse lodgingCantLunchTcGuideItineraryDate;

    @JsonProperty("lodgingCantDinnerTcGuideItineraryDate")
    private ItineraryDateResponse lodgingCantDinnerTcGuideItineraryDate;

    @JsonProperty("lodgingCantBoxLunchTcGuideItineraryDate")
    private ItineraryDateResponse lodgingCantBoxLunchTcGuideItineraryDate;

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

    @JsonProperty("lodgingCantBreakfastTcLocalGuideDateTime")
    private LocalDateTime lodgingCantBreakfastTcLocalGuideDateTime;

    @JsonProperty("lodgingCantLunchTcLocalGuideDateTime")
    private LocalDateTime lodgingCantLunchTcLocalGuideDateTime;

    @JsonProperty("lodgingCantDinnerTcLocalGuideDateTime")
    private LocalDateTime lodgingCantDinnerTcLocalGuideDateTime;

    @JsonProperty("lodgingCantBoxLunchTcLocalGuideDateTime")
    private LocalDateTime lodgingCantBoxLunchTcLocalGuideDateTime;

    @JsonProperty("lodgingCantBreakfastTcLocalGuideDate")
    private LocalDate lodgingCantBreakfastTcLocalGuideDate;

    @JsonProperty("lodgingCantLunchTcLocalGuideDate")
    private LocalDate lodgingCantLunchTcLocalGuideDate;

    @JsonProperty("lodgingCantDinnerTcLocalGuideDate")
    private LocalDate lodgingCantDinnerTcLocalGuideDate;

    @JsonProperty("lodgingCantBoxLunchTcLocalGuideDate")
    private LocalDate lodgingCantBoxLunchTcLocalGuideDate;

    @JsonProperty("lodgingCantBreakfastTcLocalGuideItineraryDate")
    private ItineraryDateResponse lodgingCantBreakfastTcLocalGuideItineraryDate;

    @JsonProperty("lodgingCantLunchTcLocalGuideItineraryDate")
    private ItineraryDateResponse lodgingCantLunchTcLocalGuideItineraryDate;

    @JsonProperty("lodgingCantDinnerTcLocalGuideItineraryDate")
    private ItineraryDateResponse lodgingCantDinnerTcLocalGuideItineraryDate;

    @JsonProperty("lodgingCantBoxLunchTcLocalGuideItineraryDate")
    private ItineraryDateResponse lodgingCantBoxLunchTcLocalGuideItineraryDate;

    @JsonProperty("lodgingCantBreakfastTcLocalGuideDateStr")
    private String lodgingCantBreakfastTcLocalGuideDateStr;

    @JsonProperty("lodgingCantLunchTcLocalGuideDateStr")
    private String lodgingCantLunchTcLocalGuideDateStr;

    @JsonProperty("lodgingCantDinnerTcLocalGuideDateStr")
    private String lodgingCantDinnerTcLocalGuideDateStr;

    @JsonProperty("lodgingCantBoxLunchTcLocalGuideDateStr")
    private String lodgingCantBoxLunchTcLocalGuideDateStr;

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

    @JsonProperty("lodgingCantBreakfastGuideDateTime")
    private LocalDateTime lodgingCantBreakfastGuideDateTime;

    @JsonProperty("lodgingCantLunchGuideDateTime")
    private LocalDateTime lodgingCantLunchGuideDateTime;

    @JsonProperty("lodgingCantDinnerGuideDateTime")
    private LocalDateTime lodgingCantDinnerGuideDateTime;

    @JsonProperty("lodgingCantBoxLunchGuideDateTime")
    private LocalDateTime lodgingCantBoxLunchGuideDateTime;

    @JsonProperty("lodgingCantBreakfastGuideDate")
    private LocalDate lodgingCantBreakfastGuideDate;

    @JsonProperty("lodgingCantLunchGuideDate")
    private LocalDate lodgingCantLunchGuideDate;

    @JsonProperty("lodgingCantDinnerGuideDate")
    private LocalDate lodgingCantDinnerGuideDate;

    @JsonProperty("lodgingCantBoxLunchGuideDate")
    private LocalDate lodgingCantBoxLunchGuideDate;

    @JsonProperty("lodgingCantBreakfastGuideDateStr")
    private String lodgingCantBreakfastGuideDateStr;

    @JsonProperty("lodgingCantLunchGuideDateStr")
    private String lodgingCantLunchGuideDateStr;

    @JsonProperty("lodgingCantDinnerGuideDateStr")
    private String lodgingCantDinnerGuideDateStr;

    @JsonProperty("lodgingCantBoxLunchGuideDateStr")
    private String lodgingCantBoxLunchGuideDateStr;

    @JsonProperty("lodgingCantBreakfastGuideItineraryDate")
    private ItineraryDateResponse lodgingCantBreakfastGuideItineraryDate;

    @JsonProperty("lodgingCantLunchGuideItineraryDate")
    private ItineraryDateResponse lodgingCantLunchGuideItineraryDate;

    @JsonProperty("lodgingCantDinnerGuideItineraryDate")
    private ItineraryDateResponse lodgingCantDinnerGuideItineraryDate;

    @JsonProperty("lodgingCantBoxLunchGuideItineraryDate")
    private ItineraryDateResponse lodgingCantBoxLunchGuideItineraryDate;

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

    @JsonProperty("lodgeCategoryStr")
    private String lodgeCategoryStr;

    @JsonProperty("lodgeCantBreakfast")
    private Integer lodgeCantBreakfast;

    @JsonProperty("lodgeCantLunch")
    private Integer lodgeCantLunch;

    @JsonProperty("lodgeCantDinner")
    private Integer lodgeCantDinner;

    @JsonProperty("lodgeCantBoxLunch")
    private Integer lodgeCantBoxLunch;

    @JsonProperty("lodgeCantBreakfastDateTime")
    private LocalDateTime lodgeCantBreakfastDateTime;

    @JsonProperty("lodgeCantLunchDateTime")
    private LocalDateTime lodgeCantLunchDateTime;

    @JsonProperty("lodgeCantDinnerDateTime")
    private LocalDateTime lodgeCantDinnerDateTime;

    @JsonProperty("lodgeCantBoxLunchDateTime")
    private LocalDateTime lodgeCantBoxLunchDateTime;

    @JsonProperty("lodgeCantBreakfastDate")
    private LocalDate lodgeCantBreakfastDate;

    @JsonProperty("lodgeCantLunchDate")
    private LocalDate lodgeCantLunchDate;

    @JsonProperty("lodgeCantDinnerDate")
    private LocalDate lodgeCantDinnerDate;

    @JsonProperty("lodgeCantBoxLunchDate")
    private LocalDate lodgeCantBoxLunchDate;

    @JsonProperty("lodgeCantBreakfastDateStr")
    private String lodgeCantBreakfastDateStr;

    @JsonProperty("lodgeCantLunchDateStr")
    private String lodgeCantLunchDateStr;

    @JsonProperty("lodgeCantDinnerDateStr")
    private String lodgeCantDinnerDateStr;

    @JsonProperty("lodgeCantBoxLunchDateStr")
    private String lodgeCantBoxLunchDateStr;

    @JsonProperty("lodgeCantBreakfastItineraryDate")
    private ItineraryDateResponse lodgeCantBreakfastItineraryDate;

    @JsonProperty("lodgeCantLunchItineraryDate")
    private ItineraryDateResponse lodgeCantLunchItineraryDate;

    @JsonProperty("lodgeCantDinnerItineraryDate")
    private ItineraryDateResponse lodgeCantDinnerItineraryDate;

    @JsonProperty("lodgeCantBoxLunchItineraryDate")
    private ItineraryDateResponse lodgeCantBoxLunchItineraryDate;

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

    @JsonProperty("lodgeCantBreakfastTcGuideDateTime")
    private LocalDateTime lodgeCantBreakfastTcGuideDateTime;

    @JsonProperty("lodgeCantLunchTcGuideDateTime")
    private LocalDateTime lodgeCantLunchTcGuideDateTime;

    @JsonProperty("lodgeCantDinnerTcGuideDateTime")
    private LocalDateTime lodgeCantDinnerTcGuideDateTime;

    @JsonProperty("lodgeCantBoxLunchTcGuideDateTime")
    private LocalDateTime lodgeCantBoxLunchTcGuideDateTime;

    @JsonProperty("lodgeCantBreakfastTcGuideDate")
    private LocalDate lodgeCantBreakfastTcGuideDate;

    @JsonProperty("lodgeCantLunchTcGuideDate")
    private LocalDate lodgeCantLunchTcGuideDate;

    @JsonProperty("lodgeCantDinnerTcGuideDate")
    private LocalDate lodgeCantDinnerTcGuideDate;

    @JsonProperty("lodgeCantBoxLunchTcGuideDate")
    private LocalDate lodgeCantBoxLunchTcGuideDate;

    @JsonProperty("lodgeCantBreakfastTcGuideDateStr")
    private String lodgeCantBreakfastTcGuideDateStr;

    @JsonProperty("lodgeCantLunchTcGuideDateStr")
    private String lodgeCantLunchTcGuideDateStr;

    @JsonProperty("lodgeCantDinnerTcGuideDateStr")
    private String lodgeCantDinnerTcGuideDateStr;

    @JsonProperty("lodgeCantBoxLunchTcGuideDateStr")
    private String lodgeCantBoxLunchTcGuideDateStr;

    @JsonProperty("lodgeCantBreakfastTcGuideItineraryDate")
    private ItineraryDateResponse lodgeCantBreakfastTcGuideItineraryDate;

    @JsonProperty("lodgeCantLunchTcGuideItineraryDate")
    private ItineraryDateResponse lodgeCantLunchTcGuideItineraryDate;

    @JsonProperty("lodgeCantDinnerTcGuideItineraryDate")
    private ItineraryDateResponse lodgeCantDinnerTcGuideItineraryDate;

    @JsonProperty("lodgeCantBoxLunchTcGuideItineraryDate")
    private ItineraryDateResponse lodgeCantBoxLunchTcGuideItineraryDate;


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

    @JsonProperty("lodgeCantBreakfastTcLocalGuideDateTime")
    private LocalDateTime lodgeCantBreakfastTcLocalGuideDateTime;

    @JsonProperty("lodgeCantLunchTcLocalGuideDateTime")
    private LocalDateTime lodgeCantLunchTcLocalGuideDateTime;

    @JsonProperty("lodgeCantDinnerTcLocalGuideDateTime")
    private LocalDateTime lodgeCantDinnerTcLocalGuideDateTime;

    @JsonProperty("lodgeCantBoxLunchTcLocalGuideDateTime")
    private LocalDateTime lodgeCantBoxLunchTcLocalGuideDateTime;

    @JsonProperty("lodgeCantBreakfastTcLocalGuideDate")
    private LocalDate lodgeCantBreakfastTcLocalGuideDate;

    @JsonProperty("lodgeCantLunchTcLocalGuideDate")
    private LocalDate lodgeCantLunchTcLocalGuideDate;

    @JsonProperty("lodgeCantDinnerTcLocalGuideDate")
    private LocalDate lodgeCantDinnerTcLocalGuideDate;

    @JsonProperty("lodgeCantBoxLunchTcLocalGuideDate")
    private LocalDate lodgeCantBoxLunchTcLocalGuideDate;

    @JsonProperty("lodgeCantBreakfastTcLocalGuideDateStr")
    private String lodgeCantBreakfastTcLocalGuideDateStr;

    @JsonProperty("lodgeCantLunchTcLocalGuideDateStr")
    private String lodgeCantLunchTcLocalGuideDateStr;

    @JsonProperty("lodgeCantDinnerTcLocalGuideDateStr")
    private String lodgeCantDinnerTcLocalGuideDateStr;

    @JsonProperty("lodgeCantBoxLunchTcLocalGuideDateStr")
    private String lodgeCantBoxLunchTcLocalGuideDateStr;

    @JsonProperty("lodgeCantBreakfastTcLocalGuideItineraryDate")
    private ItineraryDateResponse lodgeCantBreakfastTcLocalGuideItineraryDate;

    @JsonProperty("lodgeCantLunchTcLocalGuideItineraryDate")
    private ItineraryDateResponse lodgeCantLunchTcLocalGuideItineraryDate;

    @JsonProperty("lodgeCantDinnerTcLocalGuideItineraryDate")
    private ItineraryDateResponse lodgeCantDinnerTcLocalGuideItineraryDate;

    @JsonProperty("lodgeCantBoxLunchTcLocalGuideItineraryDate")
    private ItineraryDateResponse lodgeCantBoxLunchTcLocalGuideItineraryDate;

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

    @JsonProperty("lodgeCantBreakfastGuideDateTime")
    private LocalDateTime lodgeCantBreakfastGuideDateTime;

    @JsonProperty("lodgeCantLunchGuideDateTime")
    private LocalDateTime lodgeCantLunchGuideDateTime;

    @JsonProperty("lodgeCantDinnerGuideDateTime")
    private LocalDateTime lodgeCantDinnerGuideDateTime;

    @JsonProperty("lodgeCantBoxLunchGuideDateTime")
    private LocalDateTime lodgeCantBoxLunchGuideDateTime;

    @JsonProperty("lodgeCantBreakfastGuideDate")
    private LocalDate lodgeCantBreakfastGuideDate;

    @JsonProperty("lodgeCantLunchGuideDate")
    private LocalDate lodgeCantLunchGuideDate;

    @JsonProperty("lodgeCantDinnerGuideDate")
    private LocalDate lodgeCantDinnerGuideDate;

    @JsonProperty("lodgeCantBoxLunchGuideDate")
    private LocalDate lodgeCantBoxLunchGuideDate;

    @JsonProperty("lodgeCantBreakfastGuideDateStr")
    private String lodgeCantBreakfastGuideDateStr;

    @JsonProperty("lodgeCantLunchGuideDateStr")
    private String lodgeCantLunchGuideDateStr;

    @JsonProperty("lodgeCantDinnerGuideDateStr")
    private String lodgeCantDinnerGuideDateStr;

    @JsonProperty("lodgeCantBoxLunchGuideDateStr")
    private String lodgeCantBoxLunchGuideDateStr;

    @JsonProperty("lodgeCantBreakfastGuideItineraryDate")
    private ItineraryDateResponse lodgeCantBreakfastGuideItineraryDate;

    @JsonProperty("lodgeCantLunchGuideItineraryDate")
    private ItineraryDateResponse lodgeCantLunchGuideItineraryDate;

    @JsonProperty("lodgeCantDinnerGuideItineraryDate")
    private ItineraryDateResponse lodgeCantDinnerGuideItineraryDate;

    @JsonProperty("lodgeCantBoxLunchGuideItineraryDate")
    private ItineraryDateResponse lodgeCantBoxLunchGuideItineraryDate;

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

    @JsonProperty("stateItineraryService")
    private String stateItineraryService;

    @JsonProperty("stateItineraryServiceDescription")
    private String stateItineraryServiceDescription;

    @JsonProperty("note")
    private String note;

    @JsonProperty("stateServiceInvoice")
    private String stateServiceInvoice;

    @JsonProperty("stateServiceInvoiceDescription")
    private String stateServiceInvoiceDescription;

    @JsonProperty("noteInvoice")
    private String noteInvoice;

    @JsonProperty("documentDateInvoice")
    private LocalDate documentDateInvoice;

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

        if (domain.getPaxTotal() != null){
            itineraryItemResponse.setPax(domain.getPaxTotal());
        }else if (domain.getPaxAdultTotal() != null){
            itineraryItemResponse.setPax(domain.getPaxAdultTotal());
        }

        if (domain.getItineraryDate() != null &&
                domain.getItineraryDate().getId() != null &&
                !domain.getItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setItineraryDate(ItineraryDateResponse.from(domain.getItineraryDate()));
        }
        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            itineraryItemResponse.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
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

        if (itineraryItemResponse.getItineraryDateTime() != null){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            itineraryItemResponse.setItineraryDateTimeStr(itineraryItemResponse.getItineraryDateTime().format(dateTimeFormatter));
        }else{
            itineraryItemResponse.setItineraryDateTimeStr("");
        }

        if (domain.getTour() != null &&
                domain.getTour().getId() != null &&
                !domain.getTour().getId().isEmpty()){
            itineraryItemResponse.setTour(TourResponse.from(domain.getTour()));
        }

        if (domain.getCustomer() != null &&
                domain.getCustomer().getId() != null &&
                !domain.getCustomer().getId().isEmpty()){
            itineraryItemResponse.setCustomer(CustomerResponse.from(domain.getCustomer()));
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
        itineraryItemResponse.setLodgingCantBreakfastDateTime(domain.getLodgingCantBreakfastDateTime());
        itineraryItemResponse.setLodgingCantLunchDateTime(domain.getLodgingCantLunchDateTime());
        itineraryItemResponse.setLodgingCantDinnerDateTime(domain.getLodgingCantDinnerDateTime());
        itineraryItemResponse.setLodgingCantBoxLunchDateTime(domain.getLodgingCantBoxLunchDateTime());
        itineraryItemResponse.setLodgingCantBreakfastDate(domain.getLodgingCantBreakfastDate());
        itineraryItemResponse.setLodgingCantLunchDate(domain.getLodgingCantLunchDate());
        itineraryItemResponse.setLodgingCantDinnerDate(domain.getLodgingCantDinnerDate());
        itineraryItemResponse.setLodgingCantBoxLunchDate(domain.getLodgingCantBoxLunchDate());

        if (domain.getLodgingCantBreakfastItineraryDate() != null &&
                domain.getLodgingCantBreakfastItineraryDate().getId() != null &&
                !domain.getLodgingCantBreakfastItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantBreakfastItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantBreakfastItineraryDate()));
        }

        if (domain.getLodgingCantLunchItineraryDate() != null &&
                domain.getLodgingCantLunchItineraryDate().getId() != null &&
                !domain.getLodgingCantLunchItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantLunchItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantLunchItineraryDate()));
        }

        if (domain.getLodgingCantDinnerItineraryDate() != null &&
                domain.getLodgingCantDinnerItineraryDate().getId() != null &&
                !domain.getLodgingCantDinnerItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantDinnerItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantDinnerItineraryDate()));
        }

        if (domain.getLodgingCantBoxLunchItineraryDate() != null &&
                domain.getLodgingCantBoxLunchItineraryDate().getId() != null &&
                !domain.getLodgingCantBoxLunchItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantBoxLunchItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantBoxLunchItineraryDate()));
        }

        itineraryItemResponse.setLodgingCantBreakfastDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantBreakfastDate()));
        itineraryItemResponse.setLodgingCantLunchDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantLunchDate()));
        itineraryItemResponse.setLodgingCantDinnerDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantDinnerDate()));
        itineraryItemResponse.setLodgingCantBoxLunchDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantBoxLunchDate()));

        if (domain.getLodgingCategory() != null &&
                domain.getLodgingCategory().getId() != null &&
                !domain.getLodgingCategory().getId().isEmpty()){
            itineraryItemResponse.setLodgingCategory(LodgingCategoryResponse.from(domain.getLodgingCategory()));
        }
        itineraryItemResponse.setLodgingCategoryStr(domain.getLodgingCategoryStr());
        itineraryItemResponse.setLodgingCantNight(domain.getLodgingCantNight());

        itineraryItemResponse.setLodgingCantBreakfastTcGuide(domain.getLodgingCantBreakfastTcGuide());
        itineraryItemResponse.setLodgingCantLunchTcGuide(domain.getLodgingCantLunchTcGuide());
        itineraryItemResponse.setLodgingCantDinnerTcGuide(domain.getLodgingCantDinnerTcGuide());
        itineraryItemResponse.setLodgingCantBoxLunchTcGuide(domain.getLodgingCantBoxLunchTcGuide());
        itineraryItemResponse.setLodgingCantBreakfastTcGuideDateTime(domain.getLodgingCantBreakfastTcGuideDateTime());
        itineraryItemResponse.setLodgingCantLunchTcGuideDateTime(domain.getLodgingCantLunchTcGuideDateTime());
        itineraryItemResponse.setLodgingCantDinnerTcGuideDateTime(domain.getLodgingCantDinnerTcGuideDateTime());
        itineraryItemResponse.setLodgingCantBoxLunchTcGuideDateTime(domain.getLodgingCantBoxLunchTcGuideDateTime());
        itineraryItemResponse.setLodgingCantBreakfastTcGuideDate(domain.getLodgingCantBreakfastTcGuideDate());
        itineraryItemResponse.setLodgingCantLunchTcGuideDate(domain.getLodgingCantLunchTcGuideDate());
        itineraryItemResponse.setLodgingCantDinnerTcGuideDate(domain.getLodgingCantDinnerTcGuideDate());
        itineraryItemResponse.setLodgingCantBoxLunchTcGuideDate(domain.getLodgingCantBoxLunchTcGuideDate());

        itineraryItemResponse.setLodgingCantBreakfastTcGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantBreakfastTcGuideDate()));
        itineraryItemResponse.setLodgingCantLunchTcGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantLunchTcGuideDate()));
        itineraryItemResponse.setLodgingCantDinnerTcGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantDinnerTcGuideDate()));
        itineraryItemResponse.setLodgingCantBoxLunchTcGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantBoxLunchTcGuideDate()));

        if (domain.getLodgingCantBreakfastTcGuideItineraryDate() != null &&
                domain.getLodgingCantBreakfastTcGuideItineraryDate().getId() != null &&
                !domain.getLodgingCantBreakfastTcGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantBreakfastTcGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantBreakfastTcGuideItineraryDate()));
        }

        if (domain.getLodgingCantLunchTcGuideItineraryDate() != null &&
                domain.getLodgingCantLunchTcGuideItineraryDate().getId() != null &&
                !domain.getLodgingCantLunchTcGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantLunchTcGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantLunchTcGuideItineraryDate()));
        }

        if (domain.getLodgingCantDinnerTcGuideItineraryDate() != null &&
                domain.getLodgingCantDinnerTcGuideItineraryDate().getId() != null &&
                !domain.getLodgingCantDinnerTcGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantDinnerTcGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantDinnerTcGuideItineraryDate()));
        }

        if (domain.getLodgingCantBoxLunchTcGuideItineraryDate() != null &&
                domain.getLodgingCantBoxLunchTcGuideItineraryDate().getId() != null &&
                !domain.getLodgingCantBoxLunchTcGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantBoxLunchTcGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantBoxLunchTcGuideItineraryDate()));
        }

        itineraryItemResponse.setLodgingCantSwbTcGuide(domain.getLodgingCantSwbTcGuide());
        itineraryItemResponse.setLodgingCantTwinTcGuide(domain.getLodgingCantTwinTcGuide());

        itineraryItemResponse.setLodgingTcCantNight(domain.getLodgingTcCantNight());

        itineraryItemResponse.setLodgingCantBreakfastTcLocalGuide(domain.getLodgingCantBreakfastTcLocalGuide());
        itineraryItemResponse.setLodgingCantLunchTcLocalGuide(domain.getLodgingCantLunchTcLocalGuide());
        itineraryItemResponse.setLodgingCantDinnerTcLocalGuide(domain.getLodgingCantDinnerTcLocalGuide());
        itineraryItemResponse.setLodgingCantBoxLunchTcLocalGuide(domain.getLodgingCantBoxLunchTcLocalGuide());
        itineraryItemResponse.setLodgingCantBreakfastTcLocalGuideDateTime(domain.getLodgingCantBreakfastTcLocalGuideDateTime());
        itineraryItemResponse.setLodgingCantLunchTcLocalGuideDateTime(domain.getLodgingCantLunchTcLocalGuideDateTime());
        itineraryItemResponse.setLodgingCantDinnerTcLocalGuideDateTime(domain.getLodgingCantDinnerTcLocalGuideDateTime());
        itineraryItemResponse.setLodgingCantBoxLunchTcLocalGuideDateTime(domain.getLodgingCantBoxLunchTcLocalGuideDateTime());
        itineraryItemResponse.setLodgingCantBreakfastTcLocalGuideDate(domain.getLodgingCantBreakfastTcLocalGuideDate());
        itineraryItemResponse.setLodgingCantLunchTcLocalGuideDate(domain.getLodgingCantLunchTcLocalGuideDate());
        itineraryItemResponse.setLodgingCantDinnerTcLocalGuideDate(domain.getLodgingCantDinnerTcLocalGuideDate());
        itineraryItemResponse.setLodgingCantBoxLunchTcLocalGuideDate(domain.getLodgingCantBoxLunchTcLocalGuideDate());

        itineraryItemResponse.setLodgingCantBreakfastTcLocalGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantBreakfastTcLocalGuideDate()));
        itineraryItemResponse.setLodgingCantLunchTcLocalGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantLunchTcLocalGuideDate()));
        itineraryItemResponse.setLodgingCantDinnerTcLocalGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantDinnerTcLocalGuideDate()));
        itineraryItemResponse.setLodgingCantBoxLunchTcLocalGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantBoxLunchTcLocalGuideDate()));

        if (domain.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null &&
                domain.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId() != null &&
                !domain.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantBreakfastTcLocalGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantBreakfastTcLocalGuideItineraryDate()));
        }

        if (domain.getLodgingCantLunchTcLocalGuideItineraryDate() != null &&
                domain.getLodgingCantLunchTcLocalGuideItineraryDate().getId() != null &&
                !domain.getLodgingCantLunchTcLocalGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantLunchTcLocalGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantLunchTcLocalGuideItineraryDate()));
        }

        if (domain.getLodgingCantDinnerTcLocalGuideItineraryDate() != null &&
                domain.getLodgingCantDinnerTcLocalGuideItineraryDate().getId() != null &&
                !domain.getLodgingCantDinnerTcLocalGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantDinnerTcLocalGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantDinnerTcLocalGuideItineraryDate()));
        }

        if (domain.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null &&
                domain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getId() != null &&
                !domain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantBoxLunchTcLocalGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantBoxLunchTcLocalGuideItineraryDate()));
        }

        itineraryItemResponse.setLodgingCantSwbTcLocalGuide(domain.getLodgingCantSwbTcLocalGuide());
        itineraryItemResponse.setLodgingCantTwinTcLocalGuide(domain.getLodgingCantTwinTcLocalGuide());

        itineraryItemResponse.setLodgingTcLocalCantNight(domain.getLodgingTcLocalCantNight());

        itineraryItemResponse.setLodgingCantBreakfastGuide(domain.getLodgingCantBreakfastGuide());
        itineraryItemResponse.setLodgingCantLunchGuide(domain.getLodgingCantLunchGuide());
        itineraryItemResponse.setLodgingCantDinnerGuide(domain.getLodgingCantDinnerGuide());
        itineraryItemResponse.setLodgingCantBoxLunchGuide(domain.getLodgingCantBoxLunchGuide());
        itineraryItemResponse.setLodgingCantBreakfastGuideDateTime(domain.getLodgingCantBreakfastGuideDateTime());
        itineraryItemResponse.setLodgingCantLunchGuideDateTime(domain.getLodgingCantLunchGuideDateTime());
        itineraryItemResponse.setLodgingCantDinnerGuideDateTime(domain.getLodgingCantDinnerGuideDateTime());
        itineraryItemResponse.setLodgingCantBoxLunchGuideDateTime(domain.getLodgingCantBoxLunchGuideDateTime());
        itineraryItemResponse.setLodgingCantBreakfastGuideDate(domain.getLodgingCantBreakfastGuideDate());
        itineraryItemResponse.setLodgingCantLunchGuideDate(domain.getLodgingCantLunchGuideDate());
        itineraryItemResponse.setLodgingCantDinnerGuideDate(domain.getLodgingCantDinnerGuideDate());
        itineraryItemResponse.setLodgingCantBoxLunchGuideDate(domain.getLodgingCantBoxLunchGuideDate());

        itineraryItemResponse.setLodgingCantBreakfastGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantBreakfastGuideDate()));
        itineraryItemResponse.setLodgingCantLunchGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantLunchGuideDate()));
        itineraryItemResponse.setLodgingCantDinnerGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantDinnerGuideDate()));
        itineraryItemResponse.setLodgingCantBoxLunchGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgingCantBoxLunchGuideDate()));

        if (domain.getLodgingCantBreakfastGuideItineraryDate() != null &&
                domain.getLodgingCantBreakfastGuideItineraryDate().getId() != null &&
                !domain.getLodgingCantBreakfastGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantBreakfastGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantBreakfastGuideItineraryDate()));
        }

        if (domain.getLodgingCantLunchGuideItineraryDate() != null &&
                domain.getLodgingCantLunchGuideItineraryDate().getId() != null &&
                !domain.getLodgingCantLunchGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantLunchGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantLunchGuideItineraryDate()));
        }

        if (domain.getLodgingCantDinnerGuideItineraryDate() != null &&
                domain.getLodgingCantDinnerGuideItineraryDate().getId() != null &&
                !domain.getLodgingCantDinnerGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantDinnerGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantDinnerGuideItineraryDate()));
        }

        if (domain.getLodgingCantBoxLunchGuideItineraryDate() != null &&
                domain.getLodgingCantBoxLunchGuideItineraryDate().getId() != null &&
                !domain.getLodgingCantBoxLunchGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgingCantBoxLunchGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgingCantBoxLunchGuideItineraryDate()));
        }

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
        itineraryItemResponse.setLodgeCantBreakfastDateTime(domain.getLodgeCantBreakfastDateTime());
        itineraryItemResponse.setLodgeCantLunchDateTime(domain.getLodgeCantLunchDateTime());
        itineraryItemResponse.setLodgeCantDinnerDateTime(domain.getLodgeCantDinnerDateTime());
        itineraryItemResponse.setLodgeCantBoxLunchDateTime(domain.getLodgeCantBoxLunchDateTime());
        itineraryItemResponse.setLodgeCantBreakfastDate(domain.getLodgeCantBreakfastDate());
        itineraryItemResponse.setLodgeCantLunchDate(domain.getLodgeCantLunchDate());
        itineraryItemResponse.setLodgeCantDinnerDate(domain.getLodgeCantDinnerDate());
        itineraryItemResponse.setLodgeCantBoxLunchDate(domain.getLodgeCantBoxLunchDate());
        itineraryItemResponse.setLodgeCantBreakfastDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantBreakfastDate()));
        itineraryItemResponse.setLodgeCantLunchDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantLunchDate()));
        itineraryItemResponse.setLodgeCantDinnerDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantDinnerDate()));
        itineraryItemResponse.setLodgeCantBoxLunchDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantBoxLunchDate()));

        if (domain.getLodgeCantBreakfastItineraryDate() != null &&
                domain.getLodgeCantBreakfastItineraryDate().getId() != null &&
                !domain.getLodgeCantBreakfastItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantBreakfastItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantBreakfastItineraryDate()));
        }

        if (domain.getLodgeCantLunchItineraryDate() != null &&
                domain.getLodgeCantLunchItineraryDate().getId() != null &&
                !domain.getLodgeCantLunchItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantLunchItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantLunchItineraryDate()));
        }

        if (domain.getLodgeCantDinnerItineraryDate() != null &&
                domain.getLodgeCantDinnerItineraryDate().getId() != null &&
                !domain.getLodgeCantDinnerItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantDinnerItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantDinnerItineraryDate()));
        }

        if (domain.getLodgeCantBoxLunchItineraryDate() != null &&
                domain.getLodgeCantBoxLunchItineraryDate().getId() != null &&
                !domain.getLodgeCantBoxLunchItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantBoxLunchItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantBoxLunchItineraryDate()));
        }

        if (domain.getLodgeCategory() != null &&
                domain.getLodgeCategory().getId() != null &&
                !domain.getLodgeCategory().getId().isEmpty()){
            itineraryItemResponse.setLodgeCategory(LodgingCategoryResponse.from(domain.getLodgeCategory()));
        }
        itineraryItemResponse.setLodgeCategoryStr(domain.getLodgeCategoryStr());
        itineraryItemResponse.setLodgeCantNight(domain.getLodgeCantNight());

        itineraryItemResponse.setLodgeCantBreakfastTcGuide(domain.getLodgeCantBreakfastTcGuide());
        itineraryItemResponse.setLodgeCantLunchTcGuide(domain.getLodgeCantLunchTcGuide());
        itineraryItemResponse.setLodgeCantDinnerTcGuide(domain.getLodgeCantDinnerTcGuide());
        itineraryItemResponse.setLodgeCantBoxLunchTcGuide(domain.getLodgeCantBoxLunchTcGuide());
        itineraryItemResponse.setLodgeCantBreakfastTcGuideDateTime(domain.getLodgeCantBreakfastTcGuideDateTime());
        itineraryItemResponse.setLodgeCantLunchTcGuideDateTime(domain.getLodgeCantLunchTcGuideDateTime());
        itineraryItemResponse.setLodgeCantDinnerTcGuideDateTime(domain.getLodgeCantDinnerTcGuideDateTime());
        itineraryItemResponse.setLodgeCantBoxLunchTcGuideDateTime(domain.getLodgeCantBoxLunchTcGuideDateTime());
        itineraryItemResponse.setLodgeCantBreakfastTcGuideDate(domain.getLodgeCantBreakfastTcGuideDate());
        itineraryItemResponse.setLodgeCantLunchTcGuideDate(domain.getLodgeCantLunchTcGuideDate());
        itineraryItemResponse.setLodgeCantDinnerTcGuideDate(domain.getLodgeCantDinnerTcGuideDate());
        itineraryItemResponse.setLodgeCantBoxLunchTcGuideDate(domain.getLodgeCantBoxLunchTcGuideDate());
        itineraryItemResponse.setLodgeCantBreakfastTcGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantBreakfastTcGuideDate()));
        itineraryItemResponse.setLodgeCantLunchTcGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantLunchTcGuideDate()));
        itineraryItemResponse.setLodgeCantDinnerTcGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantDinnerTcGuideDate()));
        itineraryItemResponse.setLodgeCantBoxLunchTcGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantBoxLunchTcGuideDate()));

        if (domain.getLodgeCantBreakfastTcGuideItineraryDate() != null &&
                domain.getLodgeCantBreakfastTcGuideItineraryDate().getId() != null &&
                !domain.getLodgeCantBreakfastTcGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantBreakfastTcGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantBreakfastTcGuideItineraryDate()));
        }

        if (domain.getLodgeCantLunchTcGuideItineraryDate() != null &&
                domain.getLodgeCantLunchTcGuideItineraryDate().getId() != null &&
                !domain.getLodgeCantLunchTcGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantLunchTcGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantLunchTcGuideItineraryDate()));
        }

        if (domain.getLodgeCantDinnerTcGuideItineraryDate() != null &&
                domain.getLodgeCantDinnerTcGuideItineraryDate().getId() != null &&
                !domain.getLodgeCantDinnerTcGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantDinnerTcGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantDinnerTcGuideItineraryDate()));
        }

        if (domain.getLodgeCantBoxLunchTcGuideItineraryDate() != null &&
                domain.getLodgeCantBoxLunchTcGuideItineraryDate().getId() != null &&
                !domain.getLodgeCantBoxLunchTcGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantBoxLunchTcGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantBoxLunchTcGuideItineraryDate()));
        }

        itineraryItemResponse.setLodgeCantSwbTcGuide(domain.getLodgeCantSwbTcGuide());
        itineraryItemResponse.setLodgeCantTwinTcGuide(domain.getLodgeCantTwinTcGuide());

        itineraryItemResponse.setLodgeTcCantNight(domain.getLodgeTcCantNight());

        itineraryItemResponse.setLodgeCantBreakfastTcLocalGuide(domain.getLodgeCantBreakfastTcLocalGuide());
        itineraryItemResponse.setLodgeCantLunchTcLocalGuide(domain.getLodgeCantLunchTcLocalGuide());
        itineraryItemResponse.setLodgeCantDinnerTcLocalGuide(domain.getLodgeCantDinnerTcLocalGuide());
        itineraryItemResponse.setLodgeCantBoxLunchTcLocalGuide(domain.getLodgeCantBoxLunchTcLocalGuide());
        itineraryItemResponse.setLodgeCantBreakfastTcLocalGuideDateTime(domain.getLodgeCantBreakfastTcLocalGuideDateTime());
        itineraryItemResponse.setLodgeCantLunchTcLocalGuideDateTime(domain.getLodgeCantLunchTcLocalGuideDateTime());
        itineraryItemResponse.setLodgeCantDinnerTcLocalGuideDateTime(domain.getLodgeCantDinnerTcLocalGuideDateTime());
        itineraryItemResponse.setLodgeCantBoxLunchTcLocalGuideDateTime(domain.getLodgeCantBoxLunchTcLocalGuideDateTime());
        itineraryItemResponse.setLodgeCantBreakfastTcLocalGuideDate(domain.getLodgeCantBreakfastTcLocalGuideDate());
        itineraryItemResponse.setLodgeCantLunchTcLocalGuideDate(domain.getLodgeCantLunchTcLocalGuideDate());
        itineraryItemResponse.setLodgeCantDinnerTcLocalGuideDate(domain.getLodgeCantDinnerTcLocalGuideDate());
        itineraryItemResponse.setLodgeCantBoxLunchTcLocalGuideDate(domain.getLodgeCantBoxLunchTcLocalGuideDate());
        itineraryItemResponse.setLodgeCantBreakfastTcLocalGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantBreakfastTcLocalGuideDate()));
        itineraryItemResponse.setLodgeCantLunchTcLocalGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantLunchTcLocalGuideDate()));
        itineraryItemResponse.setLodgeCantDinnerTcLocalGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantDinnerTcLocalGuideDate()));
        itineraryItemResponse.setLodgeCantBoxLunchTcLocalGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantBoxLunchTcLocalGuideDate()));

        if (domain.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null &&
                domain.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId() != null &&
                !domain.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantBreakfastTcLocalGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantBreakfastTcLocalGuideItineraryDate()));
        }

        if (domain.getLodgeCantLunchTcLocalGuideItineraryDate() != null &&
                domain.getLodgeCantLunchTcLocalGuideItineraryDate().getId() != null &&
                !domain.getLodgeCantLunchTcLocalGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantLunchTcLocalGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantLunchTcLocalGuideItineraryDate()));
        }

        if (domain.getLodgeCantDinnerTcLocalGuideItineraryDate() != null &&
                domain.getLodgeCantDinnerTcLocalGuideItineraryDate().getId() != null &&
                !domain.getLodgeCantDinnerTcLocalGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantDinnerTcLocalGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantDinnerTcLocalGuideItineraryDate()));
        }

        if (domain.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null &&
                domain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getId() != null &&
                !domain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantBoxLunchTcLocalGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantBoxLunchTcLocalGuideItineraryDate()));
        }

        itineraryItemResponse.setLodgeCantSwbTcLocalGuide(domain.getLodgeCantSwbTcLocalGuide());
        itineraryItemResponse.setLodgeCantTwinTcLocalGuide(domain.getLodgeCantTwinTcLocalGuide());

        itineraryItemResponse.setLodgeTcLocalCantNight(domain.getLodgeTcLocalCantNight());

        itineraryItemResponse.setLodgeCantBreakfastGuide(domain.getLodgeCantBreakfastGuide());
        itineraryItemResponse.setLodgeCantLunchGuide(domain.getLodgeCantLunchGuide());
        itineraryItemResponse.setLodgeCantDinnerGuide(domain.getLodgeCantDinnerGuide());
        itineraryItemResponse.setLodgeCantBoxLunchGuide(domain.getLodgeCantBoxLunchGuide());
        itineraryItemResponse.setLodgeCantBreakfastGuideDateTime(domain.getLodgeCantBreakfastGuideDateTime());
        itineraryItemResponse.setLodgeCantLunchGuideDateTime(domain.getLodgeCantLunchGuideDateTime());
        itineraryItemResponse.setLodgeCantDinnerGuideDateTime(domain.getLodgeCantDinnerGuideDateTime());
        itineraryItemResponse.setLodgeCantBoxLunchGuideDateTime(domain.getLodgeCantBoxLunchGuideDateTime());
        itineraryItemResponse.setLodgeCantBreakfastGuideDate(domain.getLodgeCantBreakfastGuideDate());
        itineraryItemResponse.setLodgeCantLunchGuideDate(domain.getLodgeCantLunchGuideDate());
        itineraryItemResponse.setLodgeCantDinnerGuideDate(domain.getLodgeCantDinnerGuideDate());
        itineraryItemResponse.setLodgeCantBoxLunchGuideDate(domain.getLodgeCantBoxLunchGuideDate());
        itineraryItemResponse.setLodgeCantBreakfastGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantBreakfastGuideDate()));
        itineraryItemResponse.setLodgeCantLunchGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantLunchGuideDate()));
        itineraryItemResponse.setLodgeCantDinnerGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantDinnerGuideDate()));
        itineraryItemResponse.setLodgeCantBoxLunchGuideDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getLodgeCantBoxLunchGuideDate()));

        if (domain.getLodgeCantBreakfastGuideItineraryDate() != null &&
                domain.getLodgeCantBreakfastGuideItineraryDate().getId() != null &&
                !domain.getLodgeCantBreakfastGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantBreakfastGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantBreakfastGuideItineraryDate()));
        }

        if (domain.getLodgeCantLunchGuideItineraryDate() != null &&
                domain.getLodgeCantLunchGuideItineraryDate().getId() != null &&
                !domain.getLodgeCantLunchGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantLunchGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantLunchGuideItineraryDate()));
        }

        if (domain.getLodgeCantDinnerGuideItineraryDate() != null &&
                domain.getLodgeCantDinnerGuideItineraryDate().getId() != null &&
                !domain.getLodgeCantDinnerGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantDinnerGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantDinnerGuideItineraryDate()));
        }

        if (domain.getLodgeCantBoxLunchGuideItineraryDate() != null &&
                domain.getLodgeCantBoxLunchGuideItineraryDate().getId() != null &&
                !domain.getLodgeCantBoxLunchGuideItineraryDate().getId().isEmpty()){
            itineraryItemResponse.setLodgeCantBoxLunchGuideItineraryDate(ItineraryDateResponse.from(domain.getLodgeCantBoxLunchGuideItineraryDate()));
        }

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

        if (domain.getStateItineraryService() != null && !domain.getStateItineraryService().isEmpty()){
            itineraryItemResponse.setStateItineraryService(domain.getStateItineraryService());
            StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                    .map(StateBookingEnum.class)
                    .stream().filter(
                            stateBookingModel -> stateBookingModel.name().equals(domain.getStateItineraryService()))
                    .findFirst().get();
            itineraryItemResponse.setStateItineraryServiceDescription(stateBookingModelData.description());
        }

        itineraryItemResponse.setNote(domain.getNote());

        if (domain.getStateServiceInvoice() != null && !domain.getStateServiceInvoice().isEmpty()){
            itineraryItemResponse.setStateServiceInvoice(domain.getStateServiceInvoice());
            StateServiceInvoiceEnum.StateServiceInvoiceModel stateServiceInvoiceModelData = EnumMappeableService
                    .map(StateServiceInvoiceEnum.class)
                    .stream().filter(
                            stateServiceInvoiceModel -> stateServiceInvoiceModel.name().equals(domain.getStateServiceInvoice()))
                    .findFirst().get();
            itineraryItemResponse.setStateServiceInvoiceDescription(stateServiceInvoiceModelData.description());
        }

        itineraryItemResponse.setNoteInvoice(domain.getNoteInvoice());
        itineraryItemResponse.setDocumentDateInvoice(domain.getDocumentDateInvoice());
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
