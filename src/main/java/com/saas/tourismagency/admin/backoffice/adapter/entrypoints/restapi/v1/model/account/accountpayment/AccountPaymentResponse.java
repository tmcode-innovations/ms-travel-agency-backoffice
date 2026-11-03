package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.accountpayment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.ChangeTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proforma.ProformaResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.bank.BankResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.AccountPaymentDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.PaymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountPaymentResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("noItem")
    private Integer noItem;

    @JsonProperty("paymentDate")
    private LocalDate paymentDate;

    @JsonProperty("paymentDateStr")
    private String paymentDateStr;

    @JsonProperty("paymentDateStrDescription")
    private String paymentDateStrDescription;

    @JsonProperty("nroDocument")
    private String nroDocument;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("importMontSoles")
    private Double importMontSoles;

    @JsonProperty("importMontUsd")
    private Double importMontUsd;

    @JsonProperty("paymentType")
    private String paymentType;

    @JsonProperty("paymentTypeName")
    private String paymentTypeName;

    @JsonProperty("proforma")
    private ProformaResponse proforma;

    @JsonProperty("bank")
    private BankResponse bank;

    @JsonProperty("changeType")
    private ChangeTypeResponse changeType;

    @JsonProperty("importMontSolesFormat")
    private String importMontSolesFormat;

    @JsonProperty("importMontUsdFormat")
    private String importMontUsdFormat;

    public static AccountPaymentResponse from(AccountPaymentDomain domain) {
        AccountPaymentResponse response = new AccountPaymentResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());
        response.setNoItem(domain.getNoItem());
        response.setPaymentDate(domain.getPaymentDate());

        response.setPaymentDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getPaymentDate()));
        response.setPaymentDateStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getPaymentDate()));

        response.setNroDocument(domain.getNroDocument());
        response.setObservation(domain.getObservation());
        response.setImportMontSoles(domain.getImportMontSoles());
        response.setImportMontUsd(domain.getImportMontUsd());
        response.setPaymentType(domain.getPaymentType());

        if (domain.getPaymentType() != null && !domain.getPaymentType().isEmpty()) {
            try {
                response.setPaymentTypeName(PaymentTypeEnum.resolve(domain.getPaymentType()).getName());
            } catch (Exception e) {
                response.setPaymentTypeName(domain.getPaymentType());
            }
        }

        if (domain.getProforma() != null &&
                domain.getProforma().getId() != null &&
                !domain.getProforma().getId().isEmpty()){
            response.setProforma(ProformaResponse.from(domain.getProforma()));
        }

        if (domain.getBank() != null &&
                domain.getBank().getId() != null &&
                !domain.getBank().getId().isEmpty()){
            response.setBank(BankResponse.from(domain.getBank()));
        }

        if (domain.getChangeType() != null &&
                domain.getChangeType().getId() != null &&
                !domain.getChangeType().getId().isEmpty()){
            response.setChangeType(ChangeTypeResponse.from(domain.getChangeType()));
        }

        response.setImportMontSolesFormat(domain.getImportMontSolesFormat());
        response.setImportMontUsdFormat(domain.getImportMontUsdFormat());
        return response;
    }
}
