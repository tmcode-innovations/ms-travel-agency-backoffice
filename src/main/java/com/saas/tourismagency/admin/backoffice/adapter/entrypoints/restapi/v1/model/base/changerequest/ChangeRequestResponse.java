package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nomenclature.ChangeRequestCategoryModelResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.QuotationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.nomenclature.GuideTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.objectsecurity.ObjectResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.ChangeRequestDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.ChangeRequestCategoryModelEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.ChangeRequestStatusEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.ChangeRequestTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.GuideTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("type")
    private String type;

    @JsonProperty("typeModel")
    private ChangeRequestTypeEnum.ChangeRequestTypeModel typeModel;

    @JsonProperty("typeDescription")
    private String typeDescription;

    @JsonProperty("categories")
    private Set<ChangeRequestCategoryModelResponse> categories;

    @JsonProperty("categoryDescription")
    private String categoryDescription;

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusModel")
    private ChangeRequestStatusEnum.ChangeRequestStatusModel statusModel;

    @JsonProperty("statusDescription")
    private String statusDescription;

    @JsonProperty("employeeEmit")
    private EmployeeResponse employeeEmit;

    @JsonProperty("userEmit")
    private UserResponse userEmit;

    @JsonProperty("employeeReceive")
    private EmployeeResponse employeeReceive;

    @JsonProperty("userReceive")
    private UserResponse userReceive;

    @JsonProperty("employeeAttendedBy")
    private EmployeeResponse employeeAttendedBy;

    @JsonProperty("userAttendedBy")
    private UserResponse userAttendedBy;

    @JsonProperty("employeeSentBy")
    private EmployeeResponse employeeSentBy;

    @JsonProperty("userSentBy")
    private UserResponse userSentBy;

    @JsonProperty("employeeRefusedBy")
    private EmployeeResponse employeeRefusedBy;

    @JsonProperty("userRefusedBy")
    private UserResponse userRefusedBy;

    @JsonProperty("employeeCompletedBy")
    private EmployeeResponse employeeCompletedBy;

    @JsonProperty("userCompletedBy")
    private UserResponse userCompletedBy;

    @JsonProperty("employeeAcceptedBy")
    private EmployeeResponse employeeAcceptedBy;

    @JsonProperty("userAcceptedBy")
    private UserResponse userAcceptedBy;

    @JsonProperty("objectEntityEmit")
    private ObjectResponse objectEntityEmit;

    @JsonProperty("nameObjectEmit")
    private String nameObjectEmit;

    @JsonProperty("identifyObjectEmit")
    private String identifyObjectEmit;

    @JsonProperty("idObjectEmit")
    private String idObjectEmit;

    @JsonProperty("objectEntityReceive")
    private ObjectResponse objectEntityReceive;

    @JsonProperty("nameObjectReceive")
    private String nameObjectReceive;

    @JsonProperty("identifyObjectReceive")
    private String identifyObjectReceive;

    @JsonProperty("idObjectReceive")
    private String idObjectReceive;

    @JsonProperty("registerGroup")
    private RegisterGroupResponse registetrGroup;

    @JsonProperty("quotation")
    private QuotationResponse quotation;

    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    @JsonProperty("dateTimeStr")
    private String dateTimeStr;

    @JsonProperty("categoryAddDateToInit")
    private Boolean categoryAddDateToInit;

    @JsonProperty("categoryAddDateToEnd")
    private Boolean categoryAddDateToEnd;

    @JsonProperty("categoryAddDateInMiddle")
    private Boolean categoryAddDateInMiddle;

    @JsonProperty("categoryAddTour")
    private Boolean categoryAddTour;

    @JsonProperty("categoryAddPassengerTypeTour")
    private Boolean categoryAddPassengerTypeTour;

    @JsonProperty("categoryAddPassenger")
    private Boolean categoryAddPassenger;

    @JsonProperty("categoryAddServiceTour")
    private Boolean categoryAddServiceTour;

    @JsonProperty("categoryEditPassenger")
    private Boolean categoryEditPassenger;

    @JsonProperty("categoryEditService")
    private Boolean categoryEditService;

    @JsonProperty("categoryEditTour")
    private Boolean categoryEditTour;

    @JsonProperty("categoryEditPassengerTypeTour")
    private Boolean categoryEditPassengerTypeTour;

    @JsonProperty("categoryEditServiceTour")
    private Boolean categoryEditServiceTour;

    @JsonProperty("categoryDeleteTour")
    private Boolean categoryDeleteTour;

    @JsonProperty("categoryDeleteService")
    private Boolean categoryDeleteService;

    @JsonProperty("categoryDeletePassenger")
    private Boolean categoryDeletePassenger;

    @JsonProperty("categoryDeletePassengerTour")
    private Boolean categoryDeletePassengerTour;

    @JsonProperty("categoryDeleteServiceTour")
    private Boolean categoryDeleteServiceTour;

    @JsonProperty("categoryChangeDate")
    private Boolean categoryChangeDate;

    @JsonProperty("categoryChangeQuotationInfo")
    private Boolean categoryChangeQuotationInfo;

    @JsonProperty("categoryApplyAllChange")
    private Boolean categoryApplyAllChange;

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

    public static ChangeRequestResponse from(ChangeRequestDomain domain) {
        ChangeRequestResponse changeRequestResponse = new ChangeRequestResponse();
        changeRequestResponse.setId(domain.getId());
        changeRequestResponse.setName(domain.getName());
        changeRequestResponse.setDescription(domain.getDescription());
        changeRequestResponse.setIdentify(domain.getIdentify());

        if (domain.getType() != null && !domain.getType().isEmpty()){
            changeRequestResponse.setType(domain.getType());
            ChangeRequestTypeEnum.ChangeRequestTypeModel typeModelData = EnumMappeableService
                    .map(ChangeRequestTypeEnum.class)
                    .stream().filter(
                            typeModel -> typeModel.name().equals(domain.getType()))
                    .findFirst().get();
            changeRequestResponse.setTypeDescription(typeModelData.description());
            changeRequestResponse.setTypeModel(typeModelData);
        }

        if (domain.getCategories() != null && !domain.getCategories().isEmpty()){
            changeRequestResponse.setCategories(domain.getCategories().stream().map(s -> {
                ChangeRequestCategoryModelEnum.ChangeRequestCategoryModel changeRequestCategoryModelData = EnumMappeableService
                        .map(ChangeRequestCategoryModelEnum.class)
                        .stream().filter(
                                changeRequestCategoryModel -> changeRequestCategoryModel.name().equals(s))
                        .findFirst().get();
                return ChangeRequestCategoryModelResponse.from(changeRequestCategoryModelData);
            }).collect(Collectors.toSet()));
            StringBuffer result = new StringBuffer();
            for (String category: domain.getCategories()){
                ChangeRequestCategoryModelEnum.ChangeRequestCategoryModel changeRequestCategoryModelData = EnumMappeableService
                        .map(ChangeRequestCategoryModelEnum.class)
                        .stream().filter(
                                changeRequestCategoryModel -> changeRequestCategoryModel.name().equals(category))
                        .findFirst().get();
                if (changeRequestCategoryModelData != null){
                    result.append(changeRequestCategoryModelData.description());
                    result.append(" , ");
                }
            }
            if (result.length() > 1){
                result.delete(result.length()-2, result.length()-1);
            }
            changeRequestResponse.setCategoryDescription(result.toString());

            changeRequestResponse.setCategoryAddDateToInit(domain.getCategories().contains(ChangeRequestCategoryModelEnum.ADD_DAYS_TO_INIT.getName()));
            changeRequestResponse.setCategoryAddDateToEnd(domain.getCategories().contains(ChangeRequestCategoryModelEnum.ADD_DAYS_TO_END.getName()));
            changeRequestResponse.setCategoryAddDateInMiddle(domain.getCategories().contains(ChangeRequestCategoryModelEnum.ADD_DAYS_MIDDLE.getName()));
            changeRequestResponse.setCategoryAddTour(domain.getCategories().contains(ChangeRequestCategoryModelEnum.ADD_TOUR.getName()));
            changeRequestResponse.setCategoryAddPassengerTypeTour(domain.getCategories().contains(ChangeRequestCategoryModelEnum.ADD_PASSENGER_TYPE_TOUR.getName()));
            changeRequestResponse.setCategoryAddPassenger(domain.getCategories().contains(ChangeRequestCategoryModelEnum.ADD_PASSENGER.getName()));
            changeRequestResponse.setCategoryAddServiceTour(domain.getCategories().contains(ChangeRequestCategoryModelEnum.ADD_SERVICE_TOUR.getName()));

            changeRequestResponse.setCategoryEditPassenger(domain.getCategories().contains(ChangeRequestCategoryModelEnum.EDIT_PASSENGER.getName()));
            changeRequestResponse.setCategoryEditService(domain.getCategories().contains(ChangeRequestCategoryModelEnum.EDIT_SERVICE.getName()));
            changeRequestResponse.setCategoryEditTour(domain.getCategories().contains(ChangeRequestCategoryModelEnum.EDIT_TOUR.getName()));
            changeRequestResponse.setCategoryEditPassengerTypeTour(domain.getCategories().contains(ChangeRequestCategoryModelEnum.EDIT_PASSENGER_TYPE_TOUR.getName()));
            changeRequestResponse.setCategoryEditServiceTour(domain.getCategories().contains(ChangeRequestCategoryModelEnum.EDIT_SERVICE_TOUR.getName()));

            changeRequestResponse.setCategoryDeleteTour(domain.getCategories().contains(ChangeRequestCategoryModelEnum.DELETE_TOUR.getName()));
            changeRequestResponse.setCategoryDeleteService(domain.getCategories().contains(ChangeRequestCategoryModelEnum.DELETE_SERVICE.getName()));
            changeRequestResponse.setCategoryDeletePassenger(domain.getCategories().contains(ChangeRequestCategoryModelEnum.DELETE_PASSENGER.getName()));
            changeRequestResponse.setCategoryDeletePassengerTour(domain.getCategories().contains(ChangeRequestCategoryModelEnum.DELETE_PASSENGER_TYPE_TOUR.getName()));
            changeRequestResponse.setCategoryDeleteServiceTour(domain.getCategories().contains(ChangeRequestCategoryModelEnum.DELETE_SERVICE_TOUR.getName()));

            changeRequestResponse.setCategoryChangeDate(domain.getCategories().contains(ChangeRequestCategoryModelEnum.CHANGE_DATE.getName()));
            changeRequestResponse.setCategoryChangeQuotationInfo(domain.getCategories().contains(ChangeRequestCategoryModelEnum.CHANGE_QUOTATION_INFO.getName()));
            changeRequestResponse.setCategoryApplyAllChange(domain.getCategories().contains(ChangeRequestCategoryModelEnum.APPLY_ALL_CHANGE_TYPE.getName()));
        }else{
            changeRequestResponse.setCategoryAddDateToInit(false);
            changeRequestResponse.setCategoryAddDateToEnd(false);
            changeRequestResponse.setCategoryAddDateInMiddle(false);
            changeRequestResponse.setCategoryAddTour(false);
            changeRequestResponse.setCategoryAddPassengerTypeTour(false);
            changeRequestResponse.setCategoryAddPassenger(false);
            changeRequestResponse.setCategoryAddServiceTour(false);

            changeRequestResponse.setCategoryEditPassenger(false);
            changeRequestResponse.setCategoryEditService(false);
            changeRequestResponse.setCategoryEditTour(false);
            changeRequestResponse.setCategoryEditPassengerTypeTour(false);
            changeRequestResponse.setCategoryEditServiceTour(false);

            changeRequestResponse.setCategoryDeleteTour(false);
            changeRequestResponse.setCategoryDeleteService(false);
            changeRequestResponse.setCategoryDeletePassenger(false);
            changeRequestResponse.setCategoryDeletePassengerTour(false);
            changeRequestResponse.setCategoryDeleteServiceTour(false);

            changeRequestResponse.setCategoryChangeDate(false);
            changeRequestResponse.setCategoryChangeQuotationInfo(false);
            changeRequestResponse.setCategoryApplyAllChange(false);
        }

        if (domain.getStatus() != null && !domain.getStatus().isEmpty()){
            changeRequestResponse.setStatus(domain.getStatus());
            ChangeRequestStatusEnum.ChangeRequestStatusModel statusModelData = EnumMappeableService
                    .map(ChangeRequestStatusEnum.class)
                    .stream().filter(
                            statusModel -> statusModel.name().equals(domain.getStatus()))
                    .findFirst().get();
            changeRequestResponse.setStatusDescription(statusModelData.description());
            changeRequestResponse.setStatusModel(statusModelData);
        }

        changeRequestResponse.setDateTime(domain.getDateTime());
        changeRequestResponse.setDateTimeStr(LocalDateTimeUtil.toLocalDateTimeFormatDateTime(domain.getDateTime()));

        if (domain.getUserEmit() != null &&
                domain.getUserEmit().getId() != null &&
                !domain.getUserEmit().getId().isEmpty()){
            changeRequestResponse.setUserEmit(UserResponse.from(domain.getUserEmit()));
        }
        if (domain.getEmployeeEmit() != null &&
                domain.getEmployeeEmit().getId() != null &&
                !domain.getEmployeeEmit().getId().isEmpty()){
            changeRequestResponse.setEmployeeEmit(EmployeeResponse.from(domain.getEmployeeEmit()));
        }
        if (domain.getUserReceive() != null &&
                domain.getUserReceive().getId() != null &&
                !domain.getUserReceive().getId().isEmpty()){
            changeRequestResponse.setUserReceive(UserResponse.from(domain.getUserReceive()));
        }
        if (domain.getEmployeeReceive() != null &&
                domain.getEmployeeReceive().getId() != null &&
                !domain.getEmployeeReceive().getId().isEmpty()){
            changeRequestResponse.setEmployeeReceive(EmployeeResponse.from(domain.getEmployeeReceive()));
        }

        if (domain.getUserAttendedBy() != null &&
                domain.getUserAttendedBy().getId() != null &&
                !domain.getUserAttendedBy().getId().isEmpty()){
            changeRequestResponse.setUserAttendedBy(UserResponse.from(domain.getUserAttendedBy()));
        }
        if (domain.getEmployeeAttendedBy() != null &&
                domain.getEmployeeAttendedBy().getId() != null &&
                !domain.getEmployeeAttendedBy().getId().isEmpty()){
            changeRequestResponse.setEmployeeAttendedBy(EmployeeResponse.from(domain.getEmployeeAttendedBy()));
        }

        if (domain.getUserSentBy() != null &&
                domain.getUserSentBy().getId() != null &&
                !domain.getUserSentBy().getId().isEmpty()){
            changeRequestResponse.setUserSentBy(UserResponse.from(domain.getUserSentBy()));
        }
        if (domain.getEmployeeSentBy() != null &&
                domain.getEmployeeSentBy().getId() != null &&
                !domain.getEmployeeSentBy().getId().isEmpty()){
            changeRequestResponse.setEmployeeSentBy(EmployeeResponse.from(domain.getEmployeeSentBy()));
        }

        if (domain.getUserRefusedBy() != null &&
                domain.getUserRefusedBy().getId() != null &&
                !domain.getUserRefusedBy().getId().isEmpty()){
            changeRequestResponse.setUserRefusedBy(UserResponse.from(domain.getUserRefusedBy()));
        }
        if (domain.getEmployeeRefusedBy() != null &&
                domain.getEmployeeRefusedBy().getId() != null &&
                !domain.getEmployeeRefusedBy().getId().isEmpty()){
            changeRequestResponse.setEmployeeRefusedBy(EmployeeResponse.from(domain.getEmployeeRefusedBy()));
        }

        if (domain.getUserCompletedBy() != null &&
                domain.getUserCompletedBy().getId() != null &&
                !domain.getUserCompletedBy().getId().isEmpty()){
            changeRequestResponse.setUserCompletedBy(UserResponse.from(domain.getUserCompletedBy()));
        }
        if (domain.getEmployeeCompletedBy() != null &&
                domain.getEmployeeCompletedBy().getId() != null &&
                !domain.getEmployeeCompletedBy().getId().isEmpty()){
            changeRequestResponse.setEmployeeCompletedBy(EmployeeResponse.from(domain.getEmployeeCompletedBy()));
        }

        if (domain.getUserAcceptedBy() != null &&
                domain.getUserAcceptedBy().getId() != null &&
                !domain.getUserAcceptedBy().getId().isEmpty()){
            changeRequestResponse.setUserAcceptedBy(UserResponse.from(domain.getUserAcceptedBy()));
        }
        if (domain.getEmployeeAcceptedBy() != null &&
                domain.getEmployeeAcceptedBy().getId() != null &&
                !domain.getEmployeeAcceptedBy().getId().isEmpty()){
            changeRequestResponse.setEmployeeAcceptedBy(EmployeeResponse.from(domain.getEmployeeAcceptedBy()));
        }

        changeRequestResponse.setNameObjectEmit(domain.getNameObjectEmit());
        changeRequestResponse.setIdObjectEmit(domain.getIdObjectEmit());
        changeRequestResponse.setIdentifyObjectEmit(domain.getIdentifyObjectEmit());

        changeRequestResponse.setNameObjectReceive(domain.getNameObjectReceive());
        changeRequestResponse.setIdObjectReceive(domain.getIdObjectReceive());
        changeRequestResponse.setIdentifyObjectReceive(domain.getIdentifyObjectReceive());

        if (domain.getObjectEntityEmit() != null &&
                domain.getObjectEntityEmit().getId() != null &&
                !domain.getObjectEntityEmit().getId().isEmpty()){
            changeRequestResponse.setObjectEntityEmit(ObjectResponse.from(domain.getObjectEntityEmit()));
        }
        if (domain.getObjectEntityReceive() != null &&
                domain.getObjectEntityReceive().getId() != null &&
                !domain.getObjectEntityReceive().getId().isEmpty()){
            changeRequestResponse.setObjectEntityReceive(ObjectResponse.from(domain.getObjectEntityReceive()));
        }

        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            changeRequestResponse.setRegistetrGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }
        if (domain.getQuotation() != null &&
                domain.getQuotation().getId() != null &&
                !domain.getQuotation().getId().isEmpty()){
            changeRequestResponse.setQuotation(QuotationResponse.from(domain.getQuotation()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            changeRequestResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            changeRequestResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            changeRequestResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        changeRequestResponse.setCreatedAt(domain.getCreatedAt());
        changeRequestResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        changeRequestResponse.setUpdatedAt(domain.getUpdatedAt());
        changeRequestResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        changeRequestResponse.setDeletedAt(domain.getDeletedAt());
        changeRequestResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return changeRequestResponse;
    }
}
