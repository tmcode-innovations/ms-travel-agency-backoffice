package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nationality.NationalityResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateBookingEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookingBaseResponse {

    @JsonProperty("id")
    protected String id;

    @JsonProperty("name")
    protected String name;

    @JsonProperty("description")
    protected String description;

    @JsonProperty("identify")
    protected String identify;

    @JsonProperty("identifyConfirmation")
    protected String identifyConfirmation;

    @JsonProperty("identifyReConfirmation")
    protected String identifyReConfirmation;

    @JsonProperty("arriveAir")
    protected String arriveAir;

    @JsonProperty("departureAir")
    protected String departureAir;

    @JsonProperty("arriveAirDate")
    protected LocalDate arriveAirDate;

    @JsonProperty("arriveAirDateStr")
    protected String arriveAirDateStr;

    @JsonProperty("dateAperture")
    protected LocalDate dateAperture;

    @JsonProperty("dateApertureStr")
    protected String dateApertureStr;

    @JsonProperty("departureAirDate")
    protected LocalDate departureAirDate;

    @JsonProperty("departureAirDateStr")
    protected String departureAirDateStr;

    @JsonProperty("dateConfirmation")
    protected LocalDate dateConfirmation;

    @JsonProperty("dateConfirmationStr")
    protected String dateConfirmationStr;

    @JsonProperty("confirmation")
    protected Boolean confirmation;

    @JsonProperty("dateReConfirmation")
    protected LocalDate dateReConfirmation;

    @JsonProperty("dateReConfirmationStr")
    protected String dateReConfirmationStr;

    @JsonProperty("reConfirmation")
    protected Boolean reConfirmation;

    @JsonProperty("prePago")
    protected Boolean prePago;

    @JsonProperty("gastosExtrasNoIncluidos")
    protected Boolean gastosExtrasNoIncluidos;

    @JsonProperty("withConfirmation")
    protected Boolean withConfirmation;

    @JsonProperty("withReConfirmation")
    protected Boolean withReConfirmation;

    @JsonProperty("observation")
    protected String observation;

    @JsonProperty("observationCupon")
    protected String observationCupon;

    @JsonProperty("cantPax")
    protected Integer cantPax;

    @JsonProperty("haveCantPax")
    protected Boolean haveCantPax;

    @JsonProperty("cantFree")
    protected Integer cantFree;

    @JsonProperty("haveCantFree")
    protected Boolean haveCantFree;

    @JsonProperty("cantPaxAdult")
    protected Integer cantPaxAdult;

    @JsonProperty("haveCantPaxAdult")
    protected Boolean haveCantPaxAdult;

    @JsonProperty("cantPaxChildren")
    protected Integer cantPaxChildren;

    @JsonProperty("haveCantPaxChildren")
    protected Boolean haveCantPaxChildren;

    @JsonProperty("tc")
    protected Integer tc;

    @JsonProperty("haveTc")
    protected Boolean haveTc;

    @JsonProperty("tcLocal")
    protected Integer tcLocal;

    @JsonProperty("haveTcLocal")
    protected Boolean haveTcLocal;

    @JsonProperty("guide")
    protected Integer guide;

    @JsonProperty("haveGuide")
    protected Boolean haveGuide;

    @JsonProperty("totalPax")
    protected Integer totalPax;

    @JsonProperty("valueConfirmation")
    protected Integer valueConfirmation;

    @JsonProperty("valueReConfirmation")
    protected Integer valueReConfirmation;

    @JsonProperty("stateBooking")
    protected String stateBooking;

    @JsonProperty("stateBookingDescription")
    protected String stateBookingDescription;

    @JsonProperty("supplierType")
    protected SupplierTypeResponse supplierType;

    @JsonProperty("supplier")
    protected SupplierResponse supplier;

    @JsonProperty("registerGroup")
    protected RegisterGroupResponse registerGroup;

    @JsonProperty("customer")
    protected CustomerResponse customer;

    @JsonProperty("nationality")
    protected NationalityResponse nationality;

    @JsonProperty("employee")
    protected EmployeeResponse employee;

    @JsonProperty("activeRegisterBooking")
    protected Boolean activeRegisterBooking;

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


    public static RegisterBookingBaseResponse from(RegisterBookingBaseDomain domain) {
        RegisterBookingBaseResponse response = new RegisterBookingBaseResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());
        response.setIdentifyConfirmation(domain.getIdentifyConfirmation());
        response.setIdentifyReConfirmation(domain.getIdentifyReConfirmation());
        response.setArriveAir(domain.getArriveAir());
        response.setDepartureAir(domain.getDepartureAir());
        response.setArriveAirDate(domain.getArriveAirDate());
        response.setArriveAirDateStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getArriveAirDate()));
        response.setDateAperture(domain.getDateAperture());
        response.setDateApertureStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateAperture()));
        response.setDepartureAirDate(domain.getDepartureAirDate());
        response.setDepartureAirDateStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDepartureAirDate()));
        response.setDateConfirmation(domain.getDateConfirmation());
        response.setDateConfirmationStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateConfirmation()));
        response.setConfirmation(domain.getConfirmation());
        response.setDateReConfirmation(domain.getDateReConfirmation());
        response.setDateReConfirmationStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateReConfirmation()));
        response.setReConfirmation(domain.getReConfirmation());
        response.setPrePago(domain.getPrePago());
        response.setGastosExtrasNoIncluidos(domain.getGastosExtrasNoIncluidos());
        response.setWithConfirmation(domain.getWithConfirmation());
        response.setWithReConfirmation(domain.getWithReConfirmation());
        response.setObservation(domain.getObservation());
        response.setObservationCupon(domain.getObservationCupon());
        response.setCantPax(domain.getCantPax());
        response.setCantFree(domain.getCantFree());
        response.setCantPaxAdult(domain.getCantPaxAdult());
        response.setCantPaxChildren(domain.getCantPaxChildren());
        response.setTc(domain.getTc());
        response.setTcLocal(domain.getTcLocal());
        response.setGuide(domain.getGuide());
        response.setTotalPax(domain.getTotalPax());
        response.setValueConfirmation(domain.getValueConfirmation());
        response.setValueReConfirmation(domain.getValueReConfirmation());

        response.setHaveCantPax(response.getCantPax() != null && response.getCantPax() > 0 ? true : false);
        response.setHaveCantPaxAdult(response.getCantPaxAdult() != null && response.getCantPaxAdult() > 0 ? true : false);
        response.setHaveCantPaxChildren(response.getCantPaxChildren() != null && response.getCantPaxChildren() > 0 ? true : false);
        response.setHaveCantFree(response.getCantFree() != null && response.getCantFree() > 0 ? true : false);
        response.setHaveTc(response.getTc() != null && response.getTc() > 0 ? true : false);
        response.setHaveTcLocal(response.getTcLocal() != null && response.getTcLocal() > 0 ? true : false);
        response.setHaveGuide(response.getGuide() != null && response.getGuide() > 0 ? true : false);

        if (domain.getStateBooking() != null && !domain.getStateBooking().isEmpty()){
            response.setStateBooking(domain.getStateBooking());
            StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                    .map(StateBookingEnum.class)
                    .stream().filter(
                            stateBookingModel -> stateBookingModel.name().equals(domain.getStateBooking()))
                    .findFirst().get();
            response.setStateBookingDescription(stateBookingModelData.description());
        }
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            response.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getSupplier() != null &&
                domain.getSupplier().getId() != null &&
                !domain.getSupplier().getId().isEmpty()){
            response.setSupplier(SupplierResponse.from(domain.getSupplier()));
        }
        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }
        if (domain.getCustomer() != null &&
                domain.getCustomer().getId() != null &&
                !domain.getCustomer().getId().isEmpty()){
            response.setCustomer(CustomerResponse.from(domain.getCustomer()));
        }
        if (domain.getNationality() != null &&
                domain.getNationality().getId() != null &&
                !domain.getNationality().getId().isEmpty()){
            response.setNationality(NationalityResponse.from(domain.getNationality()));
        }
        if (domain.getEmployee() != null &&
                domain.getEmployee().getId() != null &&
                !domain.getEmployee().getId().isEmpty()){
            response.setEmployee(EmployeeResponse.from(domain.getEmployee()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            response.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            response.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            response.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        response.setCreatedAt(domain.getCreatedAt());
        response.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        response.setUpdatedAt(domain.getUpdatedAt());
        response.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        response.setDeletedAt(domain.getDeletedAt());
        response.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return response;
    }
}
