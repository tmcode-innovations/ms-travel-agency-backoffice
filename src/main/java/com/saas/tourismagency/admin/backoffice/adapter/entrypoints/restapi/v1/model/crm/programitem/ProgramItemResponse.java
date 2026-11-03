package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.program.ProgramResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programdate.ProgramDateResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.ServiceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.ServiceGroupEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramItemResponse {

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

    @JsonProperty("program")
    private ProgramResponse program;

    @JsonProperty("programDate")
    ProgramDateResponse programDate;

    @JsonProperty("supplierType")
    SupplierTypeResponse supplierType;

    @JsonProperty("supplier")
    SupplierResponse supplier;

    @JsonProperty("service")
    ServiceResponse service;

    @JsonProperty("programDateTime")
    private LocalDateTime programDateTime;

    @JsonProperty("programDateTimeStr")
    private String programDateTimeStr;

    @JsonProperty("pvt")
    private Boolean pvt;

    @JsonProperty("sic")
    private Boolean sic;

    @JsonProperty("serviceGroup")
    private String serviceGroup;

    @JsonProperty("serviceGroupModel")
    private ServiceGroupEnum.ServiceGroupModel serviceGroupModel;

    @JsonProperty("serviceGroupDescription")
    private String serviceGroupDescription;



    // Supplier Type Lodging

    @JsonProperty("lodgingCantSwb")
    private Integer lodgingCantSwb;

    @JsonProperty("lodgingCantMat")
    private Integer lodgingCantMat;

    @JsonProperty("lodgingCantTwin")
    private Integer lodgingCantTwin;

    @JsonProperty("lodgingCantTrp")
    private Integer lodgingCantTrp;

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

    @JsonProperty("lodgingCantSwbTcGuide")
    private Integer lodgingCantSwbTcGuide;

    @JsonProperty("lodgingCantTwinTcGuide")
    private Integer lodgingCantTwinTcGuide;

    @JsonProperty("lodgingCantBreakfastTcGuide")
    private Integer lodgingCantBreakfastTcGuide;

    @JsonProperty("lodgingCantLunchTcGuide")
    private Integer lodgingCantLunchTcGuide;

    @JsonProperty("lodgingCantDinnerTcGuide")
    private Integer lodgingCantDinnerTcGuide;

    @JsonProperty("lodgingCantBoxLunchTcGuide")
    private Integer lodgingCantBoxLunchTcGuide;

    @JsonProperty("lodgingCantSwbTcLocalGuide")
    private Integer lodgingCantSwbTcLocalGuide;

    @JsonProperty("lodgingCantTwinTcLocalGuide")
    private Integer lodgingCantTwinTcLocalGuide;

    @JsonProperty("lodgingCantBreakfastTcLocalGuide")
    private Integer lodgingCantBreakfastTcLocalGuide;

    @JsonProperty("lodgingCantLunchTcLocalGuide")
    private Integer lodgingCantLunchTcLocalGuide;

    @JsonProperty("lodgingCantDinnerTcLocalGuide")
    private Integer lodgingCantDinnerTcLocalGuide;

    @JsonProperty("lodgingCantBoxLunchTcLocalGuide")
    private Integer lodgingCantBoxLunchTcLocalGuide;

    @JsonProperty("lodgingCantSwbGuide")
    private Integer lodgingCantSwbGuide;

    @JsonProperty("lodgingCantTwinGuide")
    private Integer lodgingCantTwinGuide;

    @JsonProperty("lodgingCantBreakfastGuide")
    private Integer lodgingCantBreakfastGuide;

    @JsonProperty("lodgingCantLunchGuide")
    private Integer lodgingCantLunchGuide;

    @JsonProperty("lodgingCantDinnerGuide")
    private Integer lodgingCantDinnerGuide;

    @JsonProperty("lodgingCantBoxLunchGuide")
    private Integer lodgingCantBoxLunchGuide;

    // Supplier Type Lodge

    @JsonProperty("lodgeCantSwb")
    private Integer lodgeCantSwb;

    @JsonProperty("lodgeCantMat")
    private Integer lodgeCantMat;

    @JsonProperty("lodgeCantTwin")
    private Integer lodgeCantTwin;

    @JsonProperty("lodgeCantTrp")
    private Integer lodgeCantTrp;

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

    @JsonProperty("lodgeCantSwbTcGuide")
    private Integer lodgeCantSwbTcGuide;

    @JsonProperty("lodgeCantTwinTcGuide")
    private Integer lodgeCantTwinTcGuide;

    @JsonProperty("lodgeCantBreakfastTcGuide")
    private Integer lodgeCantBreakfastTcGuide;

    @JsonProperty("lodgeCantLunchTcGuide")
    private Integer lodgeCantLunchTcGuide;

    @JsonProperty("lodgeCantDinnerTcGuide")
    private Integer lodgeCantDinnerTcGuide;

    @JsonProperty("lodgeCantBoxLunchTcGuide")
    private Integer lodgeCantBoxLunchTcGuide;

    @JsonProperty("lodgeCantSwbTcLocalGuide")
    private Integer lodgeCantSwbTcLocalGuide;

    @JsonProperty("lodgeCantTwinTcLocalGuide")
    private Integer lodgeCantTwinTcLocalGuide;

    @JsonProperty("lodgeCantBreakfastTcLocalGuide")
    private Integer lodgeCantBreakfastTcLocalGuide;

    @JsonProperty("lodgeCantLunchTcLocalGuide")
    private Integer lodgeCantLunchTcLocalGuide;

    @JsonProperty("lodgeCantDinnerTcLocalGuide")
    private Integer lodgeCantDinnerTcLocalGuide;

    @JsonProperty("lodgeCantBoxLunchTcLocalGuide")
    private Integer lodgeCantBoxLunchTcLocalGuide;

    @JsonProperty("lodgeCantSwbGuide")
    private Integer lodgeCantSwbGuide;

    @JsonProperty("lodgeCantTwinGuide")
    private Integer lodgeCantTwinGuide;

    @JsonProperty("lodgeCantBreakfastGuide")
    private Integer lodgeCantBreakfastGuide;

    @JsonProperty("lodgeCantLunchGuide")
    private Integer lodgeCantLunchGuide;

    @JsonProperty("lodgeCantDinnerGuide")
    private Integer lodgeCantDinnerGuide;

    @JsonProperty("lodgeCantBoxLunchGuide")
    private Integer lodgeCantBoxLunchGuide;


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

    public static ProgramItemResponse from(ProgramItemDomain domain) {
        ProgramItemResponse programItemResponse = new ProgramItemResponse();
        programItemResponse.setId(domain.getId());
        programItemResponse.setName(domain.getName());
        programItemResponse.setDescription(domain.getDescription());
        programItemResponse.setIdentify(domain.getIdentify());
        programItemResponse.setObservation(domain.getObservation());
        programItemResponse.setProgramDateTime(domain.getProgramDateTime());
        programItemResponse.setProgramDateTimeStr(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(domain.getProgramDateTime()));
        programItemResponse.setPvt(domain.getPvt());
        programItemResponse.setSic(domain.getSic());

        if (domain.getServiceGroup() != null && !domain.getServiceGroup().isEmpty()){
            programItemResponse.setServiceGroup(domain.getServiceGroup());
            ServiceGroupEnum.ServiceGroupModel serviceGroupModelData = EnumMappeableService
                    .map(ServiceGroupEnum.class)
                    .stream().filter(
                            serviceGroupModel -> serviceGroupModel.name().equals(domain.getServiceGroup()))
                    .findFirst().get();
            programItemResponse.setServiceGroupDescription(serviceGroupModelData.description());
            programItemResponse.setServiceGroupModel(serviceGroupModelData);
        }

        if (domain.getProgramDate() != null &&
                domain.getProgramDate().getId() != null &&
                !domain.getProgramDate().getId().isEmpty()){
            programItemResponse.setProgramDate(ProgramDateResponse.from(domain.getProgramDate()));
        }
        if (domain.getProgram() != null &&
                domain.getProgram().getId() != null &&
                !domain.getProgram().getId().isEmpty()){
            programItemResponse.setProgram(ProgramResponse.from(domain.getProgram()));
        }
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            programItemResponse.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getSupplier() != null &&
                domain.getSupplier().getId() != null &&
                !domain.getSupplier().getId().isEmpty()){
            programItemResponse.setSupplier(SupplierResponse.from(domain.getSupplier()));
        }
        if (domain.getService() != null &&
                domain.getService().getId() != null &&
                !domain.getService().getId().isEmpty()){
            programItemResponse.setService(ServiceResponse.from(domain.getService()));
        }


        // Supplier Type Lodging

        programItemResponse.setLodgingCantSwb(domain.getLodgingCantSwb());
        programItemResponse.setLodgingCantMat(domain.getLodgingCantMat());
        programItemResponse.setLodgingCantTwin(domain.getLodgingCantTwin());
        programItemResponse.setLodgingCantTrp(domain.getLodgingCantTrp());

        programItemResponse.setLodgingCategoryStr(domain.getLodgingCategoryStr());

        programItemResponse.setLodgingCantBreakfast(domain.getLodgingCantBreakfast());
        programItemResponse.setLodgingCantLunch(domain.getLodgingCantLunch());
        programItemResponse.setLodgingCantDinner(domain.getLodgingCantDinner());
        programItemResponse.setLodgingCantBoxLunch(domain.getLodgingCantBoxLunch());

        programItemResponse.setLodgingCantSwbTcGuide(domain.getLodgingCantSwbTcGuide());
        programItemResponse.setLodgingCantTwinTcGuide(domain.getLodgingCantTwinTcGuide());

        programItemResponse.setLodgingCantBreakfastTcGuide(domain.getLodgingCantBreakfastTcGuide());
        programItemResponse.setLodgingCantLunchTcGuide(domain.getLodgingCantLunchTcGuide());
        programItemResponse.setLodgingCantDinnerTcGuide(domain.getLodgingCantDinnerTcGuide());
        programItemResponse.setLodgingCantBoxLunchTcGuide(domain.getLodgingCantBoxLunchTcGuide());

        programItemResponse.setLodgingCantSwbTcLocalGuide(domain.getLodgingCantSwbTcLocalGuide());
        programItemResponse.setLodgingCantTwinTcLocalGuide(domain.getLodgingCantTwinTcLocalGuide());

        programItemResponse.setLodgingCantBreakfastTcLocalGuide(domain.getLodgingCantBreakfastTcLocalGuide());
        programItemResponse.setLodgingCantLunchTcLocalGuide(domain.getLodgingCantLunchTcLocalGuide());
        programItemResponse.setLodgingCantDinnerTcLocalGuide(domain.getLodgingCantDinnerTcLocalGuide());
        programItemResponse.setLodgingCantBoxLunchTcLocalGuide(domain.getLodgingCantBoxLunchTcLocalGuide());

        programItemResponse.setLodgingCantSwbGuide(domain.getLodgingCantSwbGuide());
        programItemResponse.setLodgingCantTwinGuide(domain.getLodgingCantTwinGuide());

        programItemResponse.setLodgingCantBreakfastGuide(domain.getLodgingCantBreakfastGuide());
        programItemResponse.setLodgingCantLunchGuide(domain.getLodgingCantLunchGuide());
        programItemResponse.setLodgingCantDinnerGuide(domain.getLodgingCantDinnerGuide());
        programItemResponse.setLodgingCantBoxLunchGuide(domain.getLodgingCantBoxLunchGuide());

        // Supplier Type Lodge

        programItemResponse.setLodgeCantSwb(domain.getLodgeCantSwb());
        programItemResponse.setLodgeCantMat(domain.getLodgeCantMat());
        programItemResponse.setLodgeCantTwin(domain.getLodgeCantTwin());
        programItemResponse.setLodgeCantTrp(domain.getLodgeCantTrp());

        programItemResponse.setLodgeCategoryStr(domain.getLodgeCategoryStr());

        programItemResponse.setLodgeCantBreakfast(domain.getLodgeCantBreakfast());
        programItemResponse.setLodgeCantLunch(domain.getLodgeCantLunch());
        programItemResponse.setLodgeCantDinner(domain.getLodgeCantDinner());
        programItemResponse.setLodgeCantBoxLunch(domain.getLodgeCantBoxLunch());

        programItemResponse.setLodgeCantSwbTcGuide(domain.getLodgeCantSwbTcGuide());
        programItemResponse.setLodgeCantTwinTcGuide(domain.getLodgeCantTwinTcGuide());

        programItemResponse.setLodgeCantBreakfastTcGuide(domain.getLodgeCantBreakfastTcGuide());
        programItemResponse.setLodgeCantLunchTcGuide(domain.getLodgeCantLunchTcGuide());
        programItemResponse.setLodgeCantDinnerTcGuide(domain.getLodgeCantDinnerTcGuide());
        programItemResponse.setLodgeCantBoxLunchTcGuide(domain.getLodgeCantBoxLunchTcGuide());

        programItemResponse.setLodgeCantSwbTcLocalGuide(domain.getLodgeCantSwbTcLocalGuide());
        programItemResponse.setLodgeCantTwinTcLocalGuide(domain.getLodgeCantTwinTcLocalGuide());

        programItemResponse.setLodgeCantBreakfastTcLocalGuide(domain.getLodgeCantBreakfastTcLocalGuide());
        programItemResponse.setLodgeCantLunchTcLocalGuide(domain.getLodgeCantLunchTcLocalGuide());
        programItemResponse.setLodgeCantDinnerTcLocalGuide(domain.getLodgeCantDinnerTcLocalGuide());
        programItemResponse.setLodgeCantBoxLunchTcLocalGuide(domain.getLodgeCantBoxLunchTcLocalGuide());

        programItemResponse.setLodgeCantSwbGuide(domain.getLodgeCantSwbGuide());
        programItemResponse.setLodgeCantTwinGuide(domain.getLodgeCantTwinGuide());

        programItemResponse.setLodgeCantBreakfastGuide(domain.getLodgeCantBreakfastGuide());
        programItemResponse.setLodgeCantLunchGuide(domain.getLodgeCantLunchGuide());
        programItemResponse.setLodgeCantDinnerGuide(domain.getLodgeCantDinnerGuide());
        programItemResponse.setLodgeCantBoxLunchGuide(domain.getLodgeCantBoxLunchGuide());

        // Supplier Type Restaurant

        programItemResponse.setRestaurantCantBreakfast(domain.getRestaurantCantBreakfast());
        programItemResponse.setRestaurantCantLunch(domain.getRestaurantCantLunch());
        programItemResponse.setRestaurantCantDinner(domain.getRestaurantCantDinner());
        programItemResponse.setRestaurantCantBoxLunch(domain.getRestaurantCantBoxLunch());

        programItemResponse.setRestaurantCantBreakfastTcGuide(domain.getRestaurantCantBreakfastTcGuide());
        programItemResponse.setRestaurantCantLunchTcGuide(domain.getRestaurantCantLunchTcGuide());
        programItemResponse.setRestaurantCantDinnerTcGuide(domain.getRestaurantCantDinnerTcGuide());
        programItemResponse.setRestaurantCantBoxLunchTcGuide(domain.getRestaurantCantBoxLunchTcGuide());

        programItemResponse.setRestaurantCantBreakfastTcLocalGuide(domain.getRestaurantCantBreakfastTcLocalGuide());
        programItemResponse.setRestaurantCantLunchTcLocalGuide(domain.getRestaurantCantLunchTcLocalGuide());
        programItemResponse.setRestaurantCantDinnerTcLocalGuide(domain.getRestaurantCantDinnerTcLocalGuide());
        programItemResponse.setRestaurantCantBoxLunchTcLocalGuide(domain.getRestaurantCantBoxLunchTcLocalGuide());

        programItemResponse.setRestaurantCantBreakfastGuide(domain.getRestaurantCantBreakfastGuide());
        programItemResponse.setRestaurantCantLunchGuide(domain.getRestaurantCantLunchGuide());
        programItemResponse.setRestaurantCantDinnerGuide(domain.getRestaurantCantDinnerGuide());
        programItemResponse.setRestaurantCantBoxLunchGuide(domain.getRestaurantCantBoxLunchGuide());


        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            programItemResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            programItemResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            programItemResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        programItemResponse.setCreatedAt(domain.getCreatedAt());
        programItemResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        programItemResponse.setUpdatedAt(domain.getUpdatedAt());
        programItemResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        programItemResponse.setDeletedAt(domain.getDeletedAt());
        programItemResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return programItemResponse;
    }
}
