package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingglamping;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nationality.NationalityResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingGlampingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateBookingEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class RegisterBookingGlampingResponse extends RegisterBookingBaseResponse {

    @JsonProperty("initTour")
    private LocalDate initTour;

    @JsonProperty("initTourStr")
    private String initTourStr;

    @JsonProperty("endTour")
    private LocalDate endTour;

    @JsonProperty("endTourStr")
    private String endTourStr;

    @JsonProperty("initHourTour")
    private LocalDateTime initHourTour;

    @JsonProperty("initHourTourStr")
    private String initHourTourStr;

    @JsonProperty("endHourTour")
    private LocalDateTime endHourTour;

    @JsonProperty("endHourTourStr")
    private String endHourTourStr;

    @JsonProperty("cantSwb")
    private Integer cantSwb;

    @JsonProperty("haveCantSwb")
    protected Boolean haveCantSwb;

    @JsonProperty("cantSwbTcGuide")
    private Integer cantSwbTcGuide;

    @JsonProperty("haveCantSwbTcGuide")
    protected Boolean haveCantSwbTcGuide;

    @JsonProperty("cantSwbTcLocalGuide")
    private Integer cantSwbTcLocalGuide;

    @JsonProperty("haveCantSwbTcLocalGuide")
    protected Boolean haveCantSwbTcLocalGuide;

    @JsonProperty("cantSwbGuide")
    private Integer cantSwbGuide;

    @JsonProperty("haveCantSwbGuide")
    protected Boolean haveCantSwbGuide;

    @JsonProperty("cantTrp")
    private Integer cantTrp;

    @JsonProperty("haveCantTrp")
    protected Boolean haveCantTrp;

    @JsonProperty("cantCdp")
    private Integer cantCdp;

    @JsonProperty("haveCantCdp")
    protected Boolean haveCantCdp;

    @JsonProperty("cantQtp")
    private Integer cantQtp;

    @JsonProperty("haveCantQtp")
    protected Boolean haveCantQtp;

    @JsonProperty("cantSxtp")
    private Integer cantSxtp;

    @JsonProperty("haveCantSxtp")
    protected Boolean haveCantSxtp;

    @JsonProperty("cantTwin")
    private Integer cantTwin;

    @JsonProperty("haveCantTwin")
    protected Boolean haveCantTwin;

    @JsonProperty("cantTwinTcGuide")
    private Integer cantTwinTcGuide;

    @JsonProperty("haveCantTwinTcGuide")
    protected Boolean haveCantTwinTcGuide;

    @JsonProperty("cantTwinTcLocalGuide")
    private Integer cantTwinTcLocalGuide;

    @JsonProperty("haveCantTwinTcLocalGuide")
    protected Boolean haveCantTwinTcLocalGuide;

    @JsonProperty("cantTwinGuide")
    private Integer cantTwinGuide;

    @JsonProperty("haveCantTwinGuide")
    protected Boolean haveCantTwinGuide;

    @JsonProperty("cantMat")
    private Integer cantMat;

    @JsonProperty("haveCantMat")
    protected Boolean haveCantMat;

    @JsonProperty("numberIngress")
    private Integer numberIngress;

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

    public static RegisterBookingGlampingResponse from(RegisterBookingGlampingDomain domain) {
        RegisterBookingGlampingResponse response = new RegisterBookingGlampingResponse();
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

        // Lodging

        response.setInitTour(domain.getInitTour());
        response.setInitTourStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getInitTour()));
        response.setEndTour(domain.getEndTour());
        response.setEndTourStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getEndTour()));
        response.setInitHourTour(domain.getInitHourTour());
        response.setInitHourTourStr(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(domain.getInitHourTour()));
        response.setEndHourTour(domain.getEndHourTour());
        response.setEndHourTourStr(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(domain.getEndHourTour()));
        response.setCantSwb(domain.getCantSwb());
        response.setCantSwbTcGuide(domain.getCantSwbTcGuide());
        response.setCantSwbTcLocalGuide(domain.getCantSwbTcLocalGuide());
        response.setCantSwbGuide(domain.getCantSwbGuide());
        response.setCantTrp(domain.getCantTrp());
        response.setCantCdp(domain.getCantCdp());
        response.setCantQtp(domain.getCantQtp());
        response.setCantSxtp(domain.getCantSxtp());
        response.setCantTwin(domain.getCantTwin());
        response.setCantTwinTcGuide(domain.getCantTwinTcGuide());
        response.setCantTwinTcLocalGuide(domain.getCantTwinTcLocalGuide());
        response.setCantTwinGuide(domain.getCantTwinGuide());
        response.setCantMat(domain.getCantMat());
        response.setNumberIngress(domain.getNumberIngress());

        response.setHaveCantSwb(response.getCantSwb() != null && response.getCantSwb() > 0 ? true : false);
        response.setHaveCantSwbTcGuide(response.getCantSwbTcGuide() != null && response.getCantSwbTcGuide() > 0 ? true : false);
        response.setHaveCantSwbTcLocalGuide(response.getCantSwbTcLocalGuide() != null && response.getCantSwbTcLocalGuide() > 0 ? true : false);
        response.setHaveCantSwbGuide(response.getCantSwbGuide() != null && response.getCantSwbGuide() > 0 ? true : false);

        response.setHaveCantTwin(response.getCantTwin() != null && response.getCantTwin() > 0 ? true : false);
        response.setHaveCantTwinTcGuide(response.getCantTwinTcGuide() != null && response.getCantTwinTcGuide() > 0 ? true : false);
        response.setHaveCantTwinTcLocalGuide(response.getCantTwinTcLocalGuide() != null && response.getCantTwinTcLocalGuide() > 0 ? true : false);
        response.setHaveCantTwinGuide(response.getCantTwinGuide() != null && response.getCantTwinGuide() > 0 ? true : false);

        response.setHaveCantMat(response.getCantMat() != null && response.getCantMat() > 0 ? true : false);
        response.setHaveCantTrp(response.getCantTrp() != null && response.getCantTrp() > 0 ? true : false);
        response.setHaveCantCdp(response.getCantCdp() != null && response.getCantCdp() > 0 ? true : false);
        response.setHaveCantQtp(response.getCantQtp() != null && response.getCantQtp() > 0 ? true : false);
        response.setHaveCantSxtp(response.getCantSxtp() != null && response.getCantSxtp() > 0 ? true : false);

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
