package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformacreditnote;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.ChangeTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencyResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.dateprocess.DateProcessResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proforma.ProformaResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.CountryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaCreditNoteDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ProformaStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ProformaTypeEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ProformaCreditNoteResponse extends ProformaResponse {

    public static ProformaCreditNoteResponse from(ProformaCreditNoteDomain domain) {
        ProformaCreditNoteResponse response = new ProformaCreditNoteResponse();
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
