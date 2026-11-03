package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.CountryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.idiom.IdiomResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.program.ProgramResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.QuotationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateRegisterGroupEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.enums.StateQuotationEnum;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterGroupResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("identifyNameStr")
    private String identifyNameStr;

    @JsonProperty("days")
    private Integer days;

    @JsonProperty("cantPax")
    private Integer cantPax;

    @JsonProperty("cantFree")
    private Integer cantFree;

    @JsonProperty("cantPaxAdult")
    private Integer cantPaxAdult;

    @JsonProperty("cantPaxChildren")
    private Integer cantPaxChildren;

    @JsonProperty("tC")
    private Integer tC;

    @JsonProperty("tCLocal")
    private Integer tCLocal;

    @JsonProperty("guide")
    private Integer guide;

    @JsonProperty("totalPax")
    private Integer totalPax;

    @JsonProperty("dateAperture")
    private LocalDate dateAperture;

    @JsonProperty("dateApertureStr")
    private String dateApertureStr;

    @JsonProperty("dateApertureStrDescription")
    private String dateApertureStrDescription;

    @JsonProperty("initTour")
    private LocalDate initTour;

    @JsonProperty("initTourStr")
    private String initTourStr;

    @JsonProperty("initTourStrDescription")
    private String initTourStrDescription;

    @JsonProperty("endTour")
    private LocalDate endTour;

    @JsonProperty("endTourStr")
    private String endTourStr;

    @JsonProperty("endTourStrDescription")
    private String endTourStrDescription;

    @JsonProperty("arriveAir")
    private String arriveAir;

    @JsonProperty("departureAir")
    private String departureAir;

    @JsonProperty("cantSWB")
    private Integer cantSWB;

    @JsonProperty("cantTRP")
    private Integer cantTRP;

    @JsonProperty("cantCDP")
    private Integer cantCDP;

    @JsonProperty("cantQTP")
    private Integer cantQTP;

    @JsonProperty("cantSXTP")
    private Integer cantSXTP;

    @JsonProperty("cantTWIN")
    private Integer cantTWIN;

    @JsonProperty("cantMAT")
    private Integer cantMAT;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("link")
    private String link;

    @JsonProperty("linkBitrix")
    private String linkBitrix;

    @JsonProperty("linkWetravel")
    private String linkWetravel;

    @JsonProperty("program")
    private ProgramResponse program;

    @JsonProperty("quotation")
    private QuotationResponse quotation;

    @JsonProperty("customer")
    private CustomerResponse customer;

    @JsonProperty("idiom")
    private IdiomResponse idiom;

    @JsonProperty("country")
    private CountryResponse country;

    @JsonProperty("stateRegisterGroup")
    private String stateRegisterGroup;

    @JsonProperty("stateRegisterGroupDescription")
    private String stateRegisterGroupDescription;

    @JsonProperty("responsible")
    private EmployeeResponse responsible;

    @JsonProperty("versionQuotation")
    private Integer versionQuotation;

    @JsonProperty("apertureWrite")
    private Boolean apertureWrite;

    @JsonProperty("apertureWriteDescription")
    private String apertureWriteDescription;

    @JsonProperty("emailPassengerPrincipal")
    private String emailPassengerPrincipal;

    @JsonProperty("mobilePassengerPrincipal")
    private String mobilePassengerPrincipal;

    @JsonProperty("whatsappPassengerPrincipal")
    private String whatsappPassengerPrincipal;

    @JsonProperty("editedAdvanced")
    private Boolean editedAdvanced;

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

    public static RegisterGroupResponse from(RegisterGroupDomain domain) {
        RegisterGroupResponse response = new RegisterGroupResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());
        response.setIdentifyNameStr(domain.getIdentify() + " " + domain.getName());
        response.setDays(domain.getDays());
        response.setCantPax(domain.getCantPax());
        response.setCantFree(domain.getCantFree());
        response.setCantPaxAdult(domain.getCantPaxAdult());
        response.setCantPaxChildren(domain.getCantPaxChildren());
        response.setTC(domain.getTC());
        response.setTCLocal(domain.getTCLocal());
        response.setGuide(domain.getGuide());
        response.setTotalPax(domain.getTotalPax());
        response.setDateAperture(domain.getDateAperture());
        response.setDateApertureStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateAperture()));
        response.setDateApertureStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateAperture()));
        response.setInitTour(domain.getInitTour());
        response.setInitTourStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getInitTour()));
        response.setInitTourStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getInitTour()));
        response.setEndTour(domain.getEndTour());
        response.setEndTourStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getEndTour()));
        response.setEndTourStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getEndTour()));
        response.setArriveAir(domain.getArriveAir());
        response.setDepartureAir(domain.getDepartureAir());
        response.setCantSWB(domain.getCantSWB());
        response.setCantTRP(domain.getCantTRP());
        response.setCantCDP(domain.getCantCDP());
        response.setCantQTP(domain.getCantQTP());
        response.setCantSXTP(domain.getCantSXTP());
        response.setCantTWIN(domain.getCantTWIN());
        response.setCantMAT(domain.getCantMAT());
        response.setObservation(domain.getObservation());
        response.setLink(domain.getLink());
        response.setLinkBitrix(domain.getLinkBitrix());
        response.setLinkWetravel(domain.getLinkWetravel());
        if (domain.getProgram() != null &&
                domain.getProgram().getId() != null &&
                !domain.getProgram().getId().isEmpty()){
            response.setProgram(ProgramResponse.from(domain.getProgram()));
        }
        if (domain.getQuotation() != null &&
                domain.getQuotation().getId() != null &&
                !domain.getQuotation().getId().isEmpty()){
            response.setQuotation(QuotationResponse.from(domain.getQuotation()));
        }
        if (domain.getCustomer() != null &&
                domain.getCustomer().getId() != null &&
                !domain.getCustomer().getId().isEmpty()){
            response.setCustomer(CustomerResponse.from(domain.getCustomer()));
        }
        if (domain.getIdiom() != null &&
                domain.getIdiom().getId() != null &&
                !domain.getIdiom().getId().isEmpty()){
            response.setIdiom(IdiomResponse.from(domain.getIdiom()));
        }
        if (domain.getCountry() != null &&
                domain.getCountry().getId() != null &&
                !domain.getCountry().getId().isEmpty()){
            response.setCountry(CountryResponse.from(domain.getCountry()));
        }
        response.setStateRegisterGroup(domain.getStateRegisterGroup());
        if (domain.getStateRegisterGroup() != null && !domain.getStateRegisterGroup().isEmpty()){
            StateRegisterGroupEnum.StateRegisterGroupModel stateRegisterGroupModelData = EnumMappeableService
                    .map(StateRegisterGroupEnum.class)
                    .stream().filter(
                            stateRegisterGroupModel -> stateRegisterGroupModel.name().equals(domain.getStateRegisterGroup()))
                    .findFirst().get();
            response.setStateRegisterGroupDescription(stateRegisterGroupModelData.description());
        }
        if (domain.getResponsible() != null &&
                domain.getResponsible().getId() != null &&
                !domain.getResponsible().getId().isEmpty()){
            response.setResponsible(EmployeeResponse.from(domain.getResponsible()));
        }
        response.setVersionQuotation(domain.getVersionQuotation());
        response.setApertureWrite(domain.getApertureWrite());
        response.setMobilePassengerPrincipal(domain.getMobilePassengerPrincipal());
        response.setEmailPassengerPrincipal(domain.getEmailPassengerPrincipal());
        response.setWhatsappPassengerPrincipal(domain.getWhatsappPassengerPrincipal());

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
