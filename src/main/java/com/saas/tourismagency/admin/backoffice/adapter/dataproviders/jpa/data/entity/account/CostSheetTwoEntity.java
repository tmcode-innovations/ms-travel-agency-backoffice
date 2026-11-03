package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.CountryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.CostSheetTwoPercentEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "account_cost_sheet_two")
public class CostSheetTwoEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private LocalDate date;
    private LocalDate dateInit;
    private LocalDate dateEnd;
    private Integer noPax;

    private Double totalProform;
    private Double totalProfNCred;
    private Double totalDataProform;
    private Double totalVoucherIngress;
    private Double airValue;
    private Double totalAirValue;

    private Double dcInvoice;
    private Double dcBoleta;
    private Double dcCobranza;
    private Double dcVtaPqt;

    private Double duUtility;

    private Double distrUtiliInvoiceGastos;
    private Double distrUtiliInvoiceUtilidad;
    private Double distrUtiliInvoiceTotalReal;
    private Double distrUtiliInvoicePercentReal;
    private Double distrUtiliInvoiceVal;
    private Double distrUtiliInvoiceDiff;

    private Double distrUtiliBoletaGastos;
    private Double distrUtiliBoletaUtilidad;
    private Double distrUtiliBoletaTotalReal;
    private Double distrUtiliBoletaPercentReal;
    private Double distrUtiliBoletaVal;
    private Double distrUtiliBoletaDiff;

    private Double distrUtiliCobranzaGastos;
    private Double distrUtiliCobranzaTotalReal;

    private Double distrUtiliTotalGastos;
    private Double distrUtiliTotalUtilidad;
    private Double distrUtiliTotalTotalReal;
    private Double distrUtiliTotalPercentReal;
    private Double distrUtiliTotalVal;

    private Double totalValueWithoutIgv;
    private Double totalValueWithIgv;
    private Double totalValueInvoiceExporter;
    private Double totalValueBoletaInafect;
    private Double totalValueDocCobranza;

    private @DocumentReference(lazy = true)
    DateProcessEntity dateProcess;

    private @DocumentReference(lazy = true)
    CountryEntity country;

    private String costSheetTwoState;

    private @DocumentReference(lazy = true)
    CurrencyEntity currency;

    private @DocumentReference(lazy = true)
    ChangeTypeEntity changeType;

    private @DocumentReference(lazy = true)
    CustomerEntity customer;

    private @DocumentReference(lazy = true)
    CostSheetTwoPercentEntity costSheetTwoPercent;

    private @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;
}
