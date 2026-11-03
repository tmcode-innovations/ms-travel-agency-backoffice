package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.ChangeTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencyResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.dateprocess.DateProcessResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.CountryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.costsheettwopercent.CostSheetTwoPercentResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.CostSheetTwoDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.CostSheetTwoStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.VoucherStateEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostSheetTwoResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("dateStr")
    private String dateStr;

    @JsonProperty("dateStrDescription")
    private String dateStrDescription;

    @JsonProperty("dateInit")
    private LocalDate dateInit;

    @JsonProperty("dateInitStr")
    private String dateInitStr;

    @JsonProperty("dateInitStrDescription")
    private String dateInitStrDescription;

    @JsonProperty("dateEnd")
    private LocalDate dateEnd;

    @JsonProperty("dateEndStr")
    private String dateEndStr;

    @JsonProperty("dateEndStrDescription")
    private String dateEndStrDescription;

    @JsonProperty("noPax")
    private Integer noPax;

    @JsonProperty("totalProform")
    private Double totalProform;

    @JsonProperty("totalProfNCred")
    private Double totalProfNCred;

    @JsonProperty("totalDataProform")
    private Double totalDataProform;

    @JsonProperty("totalVoucherIngress")
    private Double totalVoucherIngress;

    @JsonProperty("airValue")
    private Double airValue;

    @JsonProperty("totalAirValue")
    private Double totalAirValue;

    @JsonProperty("dcInvoice")
    private Double dcInvoice;

    @JsonProperty("dcBoleta")
    private Double dcBoleta;

    @JsonProperty("dcCobranza")
    private Double dcCobranza;

    @JsonProperty("dcVtaPqt")
    private Double dcVtaPqt;

    @JsonProperty("duUtility")
    private Double duUtility;

    @JsonProperty("distrUtiliInvoiceGastos")
    private Double distrUtiliInvoiceGastos;

    @JsonProperty("distrUtiliInvoiceUtilidad")
    private Double distrUtiliInvoiceUtilidad;

    @JsonProperty("distrUtiliInvoiceTotalReal")
    private Double distrUtiliInvoiceTotalReal;

    @JsonProperty("distrUtiliInvoicePercentReal")
    private Double distrUtiliInvoicePercentReal;

    @JsonProperty("distrUtiliInvoiceVal")
    private Double distrUtiliInvoiceVal;

    @JsonProperty("distrUtiliInvoiceDiff")
    private Double distrUtiliInvoiceDiff;

    @JsonProperty("distrUtiliBoletaGastos")
    private Double distrUtiliBoletaGastos;

    @JsonProperty("distrUtiliBoletaUtilidad")
    private Double distrUtiliBoletaUtilidad;

    @JsonProperty("distrUtiliBoletaTotalReal")
    private Double distrUtiliBoletaTotalReal;

    @JsonProperty("distrUtiliBoletaPercentReal")
    private Double distrUtiliBoletaPercentReal;

    @JsonProperty("distrUtiliBoletaVal")
    private Double distrUtiliBoletaVal;

    @JsonProperty("distrUtiliBoletaDiff")
    private Double distrUtiliBoletaDiff;

    @JsonProperty("distrUtiliCobranzaGastos")
    private Double distrUtiliCobranzaGastos;

    @JsonProperty("distrUtiliCobranzaTotalReal")
    private Double distrUtiliCobranzaTotalReal;

    @JsonProperty("distrUtiliTotalGastos")
    private Double distrUtiliTotalGastos;

    @JsonProperty("distrUtiliTotalUtilidad")
    private Double distrUtiliTotalUtilidad;

    @JsonProperty("distrUtiliTotalTotalReal")
    private Double distrUtiliTotalTotalReal;

    @JsonProperty("distrUtiliTotalPercentReal")
    private Double distrUtiliTotalPercentReal;

    @JsonProperty("distrUtiliTotalVal")
    private Double distrUtiliTotalVal;

    @JsonProperty("totalValueWithoutIgv")
    private Double totalValueWithoutIgv;

    @JsonProperty("totalValueWithIgv")
    private Double totalValueWithIgv;

    @JsonProperty("totalValueInvoiceExporter")
    private Double totalValueInvoiceExporter;

    @JsonProperty("totalValueBoletaInafect")
    private Double totalValueBoletaInafect;

    @JsonProperty("totalValueDocCobranza")
    private Double totalValueDocCobranza;

    @JsonProperty("costSheetTwoState")
    private String costSheetTwoState;

    @JsonProperty("costSheetTwoStateDescription")
    private String costSheetTwoStateDescription;

    @JsonProperty("dateProcess")
    private DateProcessResponse dateProcess;

    @JsonProperty("country")
    private CountryResponse country;

    @JsonProperty("currency")
    private CurrencyResponse currency;

    @JsonProperty("changeType")
    private ChangeTypeResponse changeType;

    @JsonProperty("customer")
    private CustomerResponse customer;

    @JsonProperty("costSheetTwoPercent")
    private CostSheetTwoPercentResponse costSheetTwoPercent;

    @JsonProperty("registerGroup")
    private RegisterGroupResponse registerGroup;

    @JsonProperty("totalProfNCredFormat")
    private String totalProfNCredFormat;

    @JsonProperty("totalProformFormat")
    private String totalProformFormat;

    @JsonProperty("totalDataProformFormat")
    private String totalDataProformFormat;

    @JsonProperty("totalVoucherIngressFormat")
    private String totalVoucherIngressFormat;

    @JsonProperty("airValueFormat")
    private String airValueFormat;

    @JsonProperty("totalAirValueFormat")
    private String totalAirValueFormat;

    @JsonProperty("dcInvoiceFormat")
    private String dcInvoiceFormat;

    @JsonProperty("dcBoletaFormat")
    private String dcBoletaFormat;

    @JsonProperty("dcCobranzaFormat")
    private String dcCobranzaFormat;

    @JsonProperty("dcVtaPqtFormat")
    private String dcVtaPqtFormat;

    @JsonProperty("duUtilityFormat")
    private String duUtilityFormat;

    @JsonProperty("distrUtiliInvoiceGastosFormat")
    private String distrUtiliInvoiceGastosFormat;

    @JsonProperty("distrUtiliInvoiceUtilidadFormat")
    private String distrUtiliInvoiceUtilidadFormat;

    @JsonProperty("distrUtiliInvoiceTotalRealFormat")
    private String distrUtiliInvoiceTotalRealFormat;

    @JsonProperty("distrUtiliInvoicePercentRealFormat")
    private String distrUtiliInvoicePercentRealFormat;

    @JsonProperty("distrUtiliInvoiceValFormat")
    private String distrUtiliInvoiceValFormat;

    @JsonProperty("distrUtiliInvoiceDiffFormat")
    private String distrUtiliInvoiceDiffFormat;

    @JsonProperty("distrUtiliBoletaGastosFormat")
    private String distrUtiliBoletaGastosFormat;

    @JsonProperty("distrUtiliBoletaUtilidadFormat")
    private String distrUtiliBoletaUtilidadFormat;

    @JsonProperty("distrUtiliBoletaTotalRealFormat")
    private String distrUtiliBoletaTotalRealFormat;

    @JsonProperty("distrUtiliBoletaPercentRealFormat")
    private String distrUtiliBoletaPercentRealFormat;

    @JsonProperty("distrUtiliBoletaValFormat")
    private String distrUtiliBoletaValFormat;

    @JsonProperty("distrUtiliBoletaDiffFormat")
    private String distrUtiliBoletaDiffFormat;

    @JsonProperty("distrUtiliCobranzaGastosFormat")
    private String distrUtiliCobranzaGastosFormat;

    @JsonProperty("distrUtiliCobranzaTotalRealFormat")
    private String distrUtiliCobranzaTotalRealFormat;

    @JsonProperty("distrUtiliTotalGastosFormat")
    private String distrUtiliTotalGastosFormat;

    @JsonProperty("distrUtiliTotalUtilidadFormat")
    private String distrUtiliTotalUtilidadFormat;

    @JsonProperty("distrUtiliTotalTotalRealFormat")
    private String distrUtiliTotalTotalRealFormat;

    @JsonProperty("distrUtiliTotalPercentRealFormat")
    private String distrUtiliTotalPercentRealFormat;

    @JsonProperty("distrUtiliTotalValFormat")
    private String distrUtiliTotalValFormat;

    @JsonProperty("totalValueWithoutIgvFormat")
    private String totalValueWithoutIgvFormat;

    @JsonProperty("totalValueWithIgvFormat")
    private String totalValueWithIgvFormat;

    @JsonProperty("totalValueInvoiceExporterFormat")
    private String totalValueInvoiceExporterFormat;

    @JsonProperty("totalValueBoletaInafectFormat")
    private String totalValueBoletaInafectFormat;

    @JsonProperty("totalValueDocCobranzaFormat")
    private String totalValueDocCobranzaFormat;

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

    public static CostSheetTwoResponse from(CostSheetTwoDomain domain) {
        CostSheetTwoResponse response = new CostSheetTwoResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        response.setDate(domain.getDate());
        response.setDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDate()));
        response.setDateStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDate()));
        response.setDateInit(domain.getDateInit());
        response.setDateInitStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateInit()));
        response.setDateInitStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateInit()));
        response.setDateEnd(domain.getDateEnd());
        response.setDateEndStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateEnd()));
        response.setDateEndStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateEnd()));
        response.setNoPax(domain.getNoPax());

        response.setTotalProform(domain.getTotalProform());
        response.setTotalProfNCred(domain.getTotalProfNCred());
        response.setTotalDataProform(domain.getTotalDataProform());
        response.setTotalVoucherIngress(domain.getTotalVoucherIngress());
        response.setAirValue(domain.getAirValue());
        response.setTotalAirValue(domain.getTotalAirValue());

        response.setDcInvoice(domain.getDcInvoice());
        response.setDcBoleta(domain.getDcBoleta());
        response.setDcCobranza(domain.getDcCobranza());
        response.setDcVtaPqt(domain.getDcVtaPqt());

        response.setDuUtility(domain.getDuUtility());

        response.setDistrUtiliInvoiceGastos(domain.getDistrUtiliInvoiceGastos());
        response.setDistrUtiliInvoiceUtilidad(domain.getDistrUtiliInvoiceUtilidad());
        response.setDistrUtiliInvoiceTotalReal(domain.getDistrUtiliInvoiceTotalReal());
        response.setDistrUtiliInvoicePercentReal(domain.getDistrUtiliInvoicePercentReal());
        response.setDistrUtiliInvoiceVal(domain.getDistrUtiliInvoiceVal());
        response.setDistrUtiliInvoiceDiff(domain.getDistrUtiliInvoiceDiff());

        response.setDistrUtiliBoletaGastos(domain.getDistrUtiliBoletaGastos());
        response.setDistrUtiliBoletaUtilidad(domain.getDistrUtiliBoletaUtilidad());
        response.setDistrUtiliBoletaTotalReal(domain.getDistrUtiliBoletaTotalReal());
        response.setDistrUtiliBoletaPercentReal(domain.getDistrUtiliBoletaPercentReal());
        response.setDistrUtiliBoletaVal(domain.getDistrUtiliBoletaVal());
        response.setDistrUtiliBoletaDiff(domain.getDistrUtiliBoletaDiff());

        response.setDistrUtiliCobranzaGastos(domain.getDistrUtiliCobranzaGastos());
        response.setDistrUtiliCobranzaTotalReal(domain.getDistrUtiliCobranzaTotalReal());

        response.setDistrUtiliTotalGastos(domain.getDistrUtiliTotalGastos());
        response.setDistrUtiliTotalUtilidad(domain.getDistrUtiliTotalUtilidad());
        response.setDistrUtiliTotalTotalReal(domain.getDistrUtiliTotalTotalReal());
        response.setDistrUtiliTotalPercentReal(domain.getDistrUtiliTotalPercentReal());
        response.setDistrUtiliTotalVal(domain.getDistrUtiliTotalVal());

        response.setTotalValueWithoutIgv(domain.getTotalValueWithoutIgv());
        response.setTotalValueWithIgv(domain.getTotalValueWithIgv());
        response.setTotalValueInvoiceExporter(domain.getTotalValueInvoiceExporter());
        response.setTotalValueBoletaInafect(domain.getTotalValueBoletaInafect());
        response.setTotalValueDocCobranza(domain.getTotalValueDocCobranza());

        response.setCostSheetTwoState(domain.getCostSheetTwoState());
        if (domain.getCostSheetTwoState() != null && !domain.getCostSheetTwoState().isEmpty()){
            CostSheetTwoStateEnum.CostSheetTwoStateModel costSheetTwoStateModelData = EnumMappeableService
                    .map(CostSheetTwoStateEnum.class)
                    .stream().filter(
                            costSheetTwoStateModel -> costSheetTwoStateModel.name().equals(domain.getCostSheetTwoState()))
                    .findFirst().get();
            response.setCostSheetTwoStateDescription(costSheetTwoStateModelData.description());
        }

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

        if (domain.getCostSheetTwoPercent() != null &&
                domain.getCostSheetTwoPercent().getId() != null &&
                !domain.getCostSheetTwoPercent().getId().isEmpty()){
            response.setCostSheetTwoPercent(CostSheetTwoPercentResponse.from(domain.getCostSheetTwoPercent()));
        }

        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }

        response.setTotalProfNCredFormat(domain.getTotalProfNCredFormat());
        response.setTotalProformFormat(domain.getTotalProformFormat());
        response.setTotalDataProformFormat(domain.getTotalDataProformFormat());
        response.setTotalVoucherIngressFormat(domain.getTotalVoucherIngressFormat());
        response.setAirValueFormat(domain.getAirValueFormat());
        response.setTotalAirValueFormat(domain.getTotalAirValueFormat());
        response.setDcInvoiceFormat(domain.getDcInvoiceFormat());
        response.setDcBoletaFormat(domain.getDcBoletaFormat());
        response.setDcCobranzaFormat(domain.getDcCobranzaFormat());
        response.setDcVtaPqtFormat(domain.getDcVtaPqtFormat());
        response.setDuUtilityFormat(domain.getDuUtilityFormat());
        response.setDistrUtiliInvoiceGastosFormat(domain.getDistrUtiliInvoiceGastosFormat());
        response.setDistrUtiliInvoiceUtilidadFormat(domain.getDistrUtiliInvoiceUtilidadFormat());
        response.setDistrUtiliInvoiceTotalRealFormat(domain.getDistrUtiliInvoiceTotalRealFormat());
        response.setDistrUtiliInvoicePercentRealFormat(domain.getDistrUtiliInvoicePercentRealFormat());
        response.setDistrUtiliInvoiceValFormat(domain.getDistrUtiliInvoiceValFormat());
        response.setDistrUtiliInvoiceDiffFormat(domain.getDistrUtiliInvoiceDiffFormat());
        response.setDistrUtiliBoletaGastosFormat(domain.getDistrUtiliBoletaGastosFormat());
        response.setDistrUtiliBoletaUtilidadFormat(domain.getDistrUtiliBoletaUtilidadFormat());
        response.setDistrUtiliBoletaTotalRealFormat(domain.getDistrUtiliBoletaTotalRealFormat());
        response.setDistrUtiliBoletaPercentRealFormat(domain.getDistrUtiliBoletaPercentRealFormat());
        response.setDistrUtiliBoletaValFormat(domain.getDistrUtiliBoletaValFormat());
        response.setDistrUtiliBoletaDiffFormat(domain.getDistrUtiliBoletaDiffFormat());
        response.setDistrUtiliCobranzaGastosFormat(domain.getDistrUtiliCobranzaGastosFormat());
        response.setDistrUtiliCobranzaTotalRealFormat(domain.getDistrUtiliCobranzaTotalRealFormat());
        response.setDistrUtiliTotalGastosFormat(domain.getDistrUtiliTotalGastosFormat());
        response.setDistrUtiliTotalUtilidadFormat(domain.getDistrUtiliTotalUtilidadFormat());
        response.setDistrUtiliTotalTotalRealFormat(domain.getDistrUtiliTotalTotalRealFormat());
        response.setDistrUtiliTotalPercentRealFormat(domain.getDistrUtiliTotalPercentRealFormat());
        response.setDistrUtiliTotalValFormat(domain.getDistrUtiliTotalValFormat());
        response.setTotalValueWithoutIgvFormat(domain.getTotalValueWithoutIgvFormat());
        response.setTotalValueWithIgvFormat(domain.getTotalValueWithIgvFormat());
        response.setTotalValueInvoiceExporterFormat(domain.getTotalValueInvoiceExporterFormat());
        response.setTotalValueBoletaInafectFormat(domain.getTotalValueBoletaInafectFormat());
        response.setTotalValueDocCobranzaFormat(domain.getTotalValueDocCobranzaFormat());

        return response;
    }
}
