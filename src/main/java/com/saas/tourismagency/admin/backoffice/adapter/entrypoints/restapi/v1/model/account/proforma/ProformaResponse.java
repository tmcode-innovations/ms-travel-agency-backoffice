package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proforma;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.ChangeTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencyResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.dateprocess.DateProcessResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.CountryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.DocumentAccountStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ProformaStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ProformaTypeEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProformaResponse {

    @JsonProperty("id")
    protected String id;

    @JsonProperty("name")
    protected String name;

    @JsonProperty("description")
    protected String description;

    @JsonProperty("identify")
    protected String identify;

    @JsonProperty("typeInheritanceProforma")
    protected String typeInheritanceProforma;

    @JsonProperty("title")
    protected String title;

    @JsonProperty("date")
    protected LocalDate date;

    @JsonProperty("dateStr")
    protected String dateStr;

    @JsonProperty("dateStrDescription")
    protected String dateStrDescription;

    @JsonProperty("address")
    protected String address;

    @JsonProperty("reference")
    protected String reference;

    @JsonProperty("dateInit")
    protected LocalDate dateInit;

    @JsonProperty("dateInitStr")
    protected String dateInitStr;

    @JsonProperty("dateInitStrDescription")
    protected String dateInitStrDescription;

    @JsonProperty("dateEnd")
    protected LocalDate dateEnd;

    @JsonProperty("dateEndStr")
    protected String dateEndStr;

    @JsonProperty("dateEndStrDescription")
    protected String dateEndStrDescription;

    @JsonProperty("noPax")
    protected Integer noPax;

    @JsonProperty("importTotalUsd")
    protected Double importTotalUsd;

    @JsonProperty("importTotalSoles")
    protected Double importTotalSoles;

    @JsonProperty("importAmortizationUsd")
    protected Double importAmortizationUsd;

    @JsonProperty("importAmortizationSoles")
    protected Double importAmortizationSoles;

    @JsonProperty("importSaldxAmortizarUsd")
    protected Double importSaldxAmortizarUsd;

    @JsonProperty("importSaldxAmortizarSoles")
    protected Double importSaldxAmortizarSoles;

    @JsonProperty("priceUnit")
    protected Double priceUnit;

    @JsonProperty("cantRegister")
    protected Integer cantRegister;

    @JsonProperty("dateCancel")
    protected LocalDate dateCancel;

    @JsonProperty("dateCancelStr")
    protected String dateCancelStr;

    @JsonProperty("dateCancelStrDescription")
    protected String dateCancelStrDescription;

    @JsonProperty("proformaType")
    protected String proformaType;

    @JsonProperty("proformaTypeDescription")
    protected String proformaTypeDescription;

    @JsonProperty("proformaState")
    protected String proformaState;

    @JsonProperty("proformaStateDescription")
    protected String proformaStateDescription;

    @JsonProperty("importTotalUsdFormat")
    protected String importTotalUsdFormat;

    @JsonProperty("importTotalSolesFormat")
    protected String importTotalSolesFormat;

    @JsonProperty("priceUnitFormat")
    protected String priceUnitFormat;

    @JsonProperty("importTotalUsdAccountFormat")
    protected String importTotalUsdAccountFormat;

    @JsonProperty("importTotalUsdAccount")
    protected Double importTotalUsdAccount;

    @JsonProperty("importAmortizationUsdFormat")
    protected String importAmortizationUsdFormat;

    @JsonProperty("importAmortizationSolesFormat")
    protected String importAmortizationSolesFormat;

    @JsonProperty("importSaldxAmortizarUsdFormat")
    protected String importSaldxAmortizarUsdFormat;

    @JsonProperty("importSaldxAmortizarSolesFormat")
    protected String importSaldxAmortizarSolesFormat;

    @JsonProperty("dateProcess")
    protected DateProcessResponse dateProcess;

    @JsonProperty("country")
    protected CountryResponse country;

    @JsonProperty("currency")
    protected CurrencyResponse currency;

    @JsonProperty("changeType")
    protected ChangeTypeResponse changeType;

    @JsonProperty("customer")
    protected CustomerResponse customer;

    @JsonProperty("registerGroup")
    protected RegisterGroupResponse registerGroup;

    @JsonProperty("employee")
    protected EmployeeResponse employee;

    public static ProformaResponse from(ProformaDomain domain) {
        ProformaResponse response = new ProformaResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        response.setTypeInheritanceProforma(domain.getTypeInheritanceProforma());

        response.setTitle(domain.getTitle());
        response.setDate(domain.getDate());
        response.setDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDate()));
        response.setDateStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDate()));
        response.setAddress(domain.getAddress());
        response.setReference(domain.getReference());
        response.setDateInit(domain.getDateInit());
        response.setDateInitStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateInit()));
        response.setDateInitStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateInit()));
        response.setDateEnd(domain.getDateEnd());
        response.setDateEndStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateEnd()));
        response.setDateEndStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateEnd()));
        response.setNoPax(domain.getNoPax());
        response.setImportTotalUsd(domain.getImportTotalUsd());
        response.setImportTotalSoles(domain.getImportTotalSoles());
        response.setImportAmortizationUsd(domain.getImportAmortizationUsd());
        response.setImportAmortizationSoles(domain.getImportAmortizationSoles());
        response.setImportSaldxAmortizarUsd(domain.getImportSaldxAmortizarUsd());
        response.setImportSaldxAmortizarSoles(domain.getImportSaldxAmortizarSoles());
        response.setPriceUnit(domain.getPriceUnit());
        response.setCantRegister(domain.getCantRegister());

        response.setDateCancel(domain.getDateCancel());
        response.setDateCancelStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateCancel()));
        response.setDateCancelStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateCancel()));

        response.setProformaType(domain.getProformaType());

        if (domain.getProformaType() != null && !domain.getProformaType().isEmpty()){
            ProformaTypeEnum.ProformaTypeModel proformaTypeModelData = EnumMappeableService
                    .map(ProformaTypeEnum.class)
                    .stream().filter(
                            proformaTypeModel -> proformaTypeModel.name().equals(domain.getProformaType()))
                    .findFirst().get();
            response.setProformaTypeDescription(proformaTypeModelData.description());
        }

        response.setProformaState(domain.getProformaState());

        if (domain.getProformaState() != null && !domain.getProformaState().isEmpty()){
            ProformaStateEnum.ProformaStateModel proformaStateModelData = EnumMappeableService
                    .map(ProformaStateEnum.class)
                    .stream().filter(
                            proformaStateModel -> proformaStateModel.name().equals(domain.getProformaState()))
                    .findFirst().get();
            response.setProformaStateDescription(proformaStateModelData.description());
        }

        response.setImportTotalUsdFormat(domain.getImportTotalUsdFormat());
        response.setImportTotalSolesFormat(domain.getImportTotalSolesFormat());
        response.setPriceUnitFormat(domain.getPriceUnitFormat());
        response.setImportTotalUsdAccountFormat(domain.getImportTotalUsdAccountFormat());
        response.setImportTotalUsdAccount(domain.getImportTotalUsdAccount());
        response.setImportAmortizationUsdFormat(domain.getImportAmortizationUsdFormat());
        response.setImportAmortizationSolesFormat(domain.getImportAmortizationSolesFormat());
        response.setImportSaldxAmortizarUsdFormat(domain.getImportSaldxAmortizarUsdFormat());
        response.setImportSaldxAmortizarSolesFormat(domain.getImportSaldxAmortizarSolesFormat());

        if (domain.getDateProcess() != null &&
                domain.getDateProcess().getId() != null &&
                !domain.getDateProcess().getId().isEmpty()){
            response.setDateProcess(DateProcessResponse.from(domain.getDateProcess()));
        }

        if (domain.getCountry() != null &&
                domain.getCountry().getId() != null &&
                !domain.getCountry().getId().isEmpty()){
            response.setCountry(CountryResponse.from(domain.getCountry()));
        }

        if (domain.getCurrency() != null &&
                domain.getCurrency().getId() != null &&
                !domain.getCurrency().getId().isEmpty()){
            response.setCurrency(CurrencyResponse.from(domain.getCurrency()));
        }

        if (domain.getChangeType() != null &&
                domain.getChangeType().getId() != null &&
                !domain.getChangeType().getId().isEmpty()){
            response.setChangeType(ChangeTypeResponse.from(domain.getChangeType()));
        }

        if (domain.getCustomer() != null &&
                domain.getCustomer().getId() != null &&
                !domain.getCustomer().getId().isEmpty()){
            response.setCustomer(CustomerResponse.from(domain.getCustomer()));
        }

        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }

        if (domain.getEmployee() != null &&
                domain.getEmployee().getId() != null &&
                !domain.getEmployee().getId().isEmpty()){
            response.setEmployee(EmployeeResponse.from(domain.getEmployee()));
        }

        return response;
    }
}
