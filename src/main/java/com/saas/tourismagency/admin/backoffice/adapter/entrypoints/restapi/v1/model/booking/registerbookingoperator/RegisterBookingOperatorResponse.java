package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingoperator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nationality.NationalityResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierOperatorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerBookingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateBookingEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class RegisterBookingOperatorResponse extends RegisterBookingBaseResponse {

    @JsonProperty("numberService")
    private Integer numberService;

    @JsonProperty("dateInitActivity")
    private LocalDate dateInitActivity;

    @JsonProperty("dateInitActivityStr")
    private String dateInitActivityStr;

    @JsonProperty("dateEndActivity")
    private LocalDate dateEndActivity;

    @JsonProperty("dateEndActivityStr")
    private String dateEndActivityStr;

    @JsonProperty("noCupon")
    private String noCupon;

    @JsonProperty("noCuponPreImpress")
    private String noCuponPreImpress;

    @JsonProperty("cuponTcLocal")
    private Integer cuponTcLocal;

    @JsonProperty("cuponTc")
    private Integer cuponTc;

    @JsonProperty("cuponCantPaxChildren")
    private Integer cuponCantPaxChildren;

    @JsonProperty("cuponCantPaxAdult")
    private Integer cuponCantPaxAdult;

    @JsonProperty("operatorArrive")
    private SupplierOperatorResponse operatorArrive;

    @JsonProperty("operatorDeparture")
    private SupplierOperatorResponse operatorDeparture;

    @JsonProperty("supplierLodging")
    private SupplierLodgingResponse supplierLodging;

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

    @JsonProperty("createdAtStr")
    private String createdAtStr;

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

    public static RegisterBookingOperatorResponse from(RegisterBookingOperatorDomain domain) {
        RegisterBookingOperatorResponse response = new RegisterBookingOperatorResponse();
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
        response.setConfirmation(domain.getConfirmation());
        response.setDateReConfirmation(domain.getDateReConfirmation());
        response.setDateConfirmationStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateConfirmation()));
        response.setReConfirmation(domain.getReConfirmation());
        response.setDateReConfirmationStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateReConfirmation()));
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

        // Operator
        response.setNumberService(domain.getNumberService());
        response.setDateInitActivity(domain.getDateInitActivity());
        response.setDateInitActivityStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateInitActivity()));
        response.setDateEndActivity(domain.getDateEndActivity());
        response.setDateEndActivityStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateEndActivity()));
        response.setNoCupon(domain.getNoCupon());
        response.setNoCuponPreImpress(domain.getNoCuponPreImpress());
        response.setCuponTcLocal(domain.getCuponTcLocal());
        response.setCuponTc(domain.getCuponTc());
        response.setCuponCantPaxChildren(domain.getCuponCantPaxChildren());
        response.setCuponCantPaxAdult(domain.getCuponCantPaxAdult());

        if (domain.getSupplierLodging() != null &&
                domain.getSupplierLodging().getId() != null &&
                !domain.getSupplierLodging().getId().isEmpty()){
            response.setSupplierLodging(SupplierLodgingResponse.from(domain.getSupplierLodging()));
        }
        if (domain.getOperatorArrive() != null &&
                domain.getOperatorArrive().getId() != null &&
                !domain.getOperatorArrive().getId().isEmpty()){
            response.setOperatorArrive(SupplierOperatorResponse.from(domain.getOperatorArrive()));
        }
        if (domain.getOperatorDeparture() != null &&
                domain.getOperatorDeparture().getId() != null &&
                !domain.getOperatorDeparture().getId().isEmpty()){
            response.setOperatorDeparture(SupplierOperatorResponse.from(domain.getOperatorDeparture()));
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
        response.setCreatedAtStr(LocalDateTimeUtil.toLocalDateTimeFormatShort(domain.getCreatedAt()));
        response.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        response.setUpdatedAt(domain.getUpdatedAt());
        response.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        response.setDeletedAt(domain.getDeletedAt());
        response.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return response;
    }
}
