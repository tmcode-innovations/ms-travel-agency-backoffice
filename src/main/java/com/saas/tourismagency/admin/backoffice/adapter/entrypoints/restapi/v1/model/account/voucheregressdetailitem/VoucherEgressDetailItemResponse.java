package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetailitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencyResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.servicepqttourist.ServicePqtTouristResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregress.VoucherEgressResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.detraction.DetractionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.igv.IgvResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailItemDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherEgressDetailItemResponse {

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

    @JsonProperty("strNoItemFormat")
    private String strNoItemFormat;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("netImportUsd")
    private Double netImportUsd;

    @JsonProperty("netImportUsdFormat")
    private String netImportUsdFormat;

    @JsonProperty("netImportSoles")
    private Double netImportSoles;

    @JsonProperty("netImportSolesFormat")
    private String netImportSolesFormat;

    @JsonProperty("totalImportUsd")
    private Double totalImportUsd;

    @JsonProperty("totalImportUsdFormat")
    private String totalImportUsdFormat;

    @JsonProperty("totalImportSoles")
    private Double totalImportSoles;

    @JsonProperty("totalImportSolesFormat")
    private String totalImportSolesFormat;

    @JsonProperty("netGeneralUsd")
    private Double netGeneralUsd;

    @JsonProperty("netGeneralUsdFormat")
    private String netGeneralUsdFormat;

    @JsonProperty("igvSfGeneral")
    private Double igvSfGeneral;

    @JsonProperty("igvSfGeneralFormat")
    private String igvSfGeneralFormat;

    @JsonProperty("igvCostGeneral")
    private Double igvCostGeneral;

    @JsonProperty("igvCostGeneralFormat")
    private String igvCostGeneralFormat;

    @JsonProperty("igvTotalGeneral")
    private Double igvTotalGeneral;

    @JsonProperty("igvTotalGeneralFormat")
    private String igvTotalGeneralFormat;

    @JsonProperty("invoiceSaleGeneral")
    private Double invoiceSaleGeneral;

    @JsonProperty("invoiceSaleGeneralFormat")
    private String invoiceSaleGeneralFormat;

    @JsonProperty("ticketSaleGeneral")
    private Double ticketSaleGeneral;

    @JsonProperty("ticketSaleGeneralFormat")
    private String ticketSaleGeneralFormat;

    @JsonProperty("documentCollectionGeneral")
    private Double documentCollectionGeneral;

    @JsonProperty("documentCollectionGeneralFormat")
    private String documentCollectionGeneralFormat;

    @JsonProperty("voucherEgress")
    private VoucherEgressResponse voucherEgress;

    @JsonProperty("voucherEgressDetail")
    private VoucherEgressDetailResponse voucherEgressDetail;

    @JsonProperty("currency")
    private CurrencyResponse currency;

    @JsonProperty("servicePqtTourist")
    private ServicePqtTouristResponse servicePqtTourist;

    @JsonProperty("igv")
    private IgvResponse igv;

    @JsonProperty("registerGroup")
    private RegisterGroupResponse registerGroup;

    @JsonProperty("detraction")
    private DetractionResponse detraction;

    @JsonProperty("paxTotal")
    private Integer paxTotal;

    @JsonProperty("netImportUsdTotal")
    private Double netImportUsdTotal;

    @JsonProperty("netImportUsdTotalFormat")
    private String netImportUsdTotalFormat;

    @JsonProperty("netImportSolesTotal")
    private Double netImportSolesTotal;

    @JsonProperty("netImportSolesTotalFormat")
    private String netImportSolesTotalFormat;

    @JsonProperty("totalImportUsdTotal")
    private Double totalImportUsdTotal;

    @JsonProperty("totalImportUsdTotalFormat")
    private String totalImportUsdTotalFormat;

    @JsonProperty("totalImportSolesTotal")
    private Double totalImportSolesTotal;

    @JsonProperty("totalImportSolesTotalFormat")
    private String totalImportSolesTotalFormat;

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

    public static VoucherEgressDetailItemResponse from(VoucherEgressDetailItemDomain domain) {
        VoucherEgressDetailItemResponse response = new VoucherEgressDetailItemResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        response.setNoItem(domain.getNoItem());
        response.setStrNoItemFormat(domain.getStrNoItemFormat());

        response.setObservation(domain.getObservation());
        response.setNetImportUsd(domain.getNetImportUsd());
        response.setNetImportUsdFormat(domain.getNetImportUsdFormat());
        response.setNetImportSoles(domain.getNetImportSoles());
        response.setNetImportSolesFormat(domain.getNetImportSolesFormat());
        response.setTotalImportUsd(domain.getTotalImportUsd());
        response.setTotalImportUsdFormat(domain.getTotalImportUsdFormat());
        response.setTotalImportSoles(domain.getTotalImportSoles());
        response.setTotalImportSolesFormat(domain.getTotalImportSolesFormat());
        response.setNetGeneralUsd(domain.getNetGeneralUsd());
        response.setNetGeneralUsdFormat(domain.getNetGeneralUsdFormat());
        response.setIgvSfGeneral(domain.getIgvSfGeneral());
        response.setIgvSfGeneralFormat(domain.getIgvSfGeneralFormat());
        response.setIgvCostGeneral(domain.getIgvCostGeneral());
        response.setIgvCostGeneralFormat(domain.getIgvCostGeneralFormat());
        response.setIgvTotalGeneral(domain.getIgvTotalGeneral());
        response.setIgvTotalGeneralFormat(domain.getIgvTotalGeneralFormat());
        response.setInvoiceSaleGeneral(domain.getInvoiceSaleGeneral());
        response.setInvoiceSaleGeneralFormat(domain.getInvoiceSaleGeneralFormat());
        response.setTicketSaleGeneral(domain.getTicketSaleGeneral());
        response.setTicketSaleGeneralFormat(domain.getTicketSaleGeneralFormat());
        response.setDocumentCollectionGeneral(domain.getDocumentCollectionGeneral());
        response.setDocumentCollectionGeneralFormat(domain.getDocumentCollectionGeneralFormat());

        response.setPaxTotal(domain.getPaxTotal());

        response.setNetImportUsdTotal(domain.getNetImportUsdTotal());
        response.setNetImportUsdTotalFormat(domain.getNetImportUsdTotalFormat());
        response.setNetImportSolesTotal(domain.getNetImportSolesTotal());
        response.setNetImportSolesTotalFormat(domain.getNetImportSolesTotalFormat());
        response.setTotalImportUsdTotal(domain.getTotalImportUsdTotal());
        response.setTotalImportUsdTotalFormat(domain.getTotalImportUsdTotalFormat());
        response.setTotalImportSolesTotal(domain.getTotalImportSolesTotal());
        response.setTotalImportSolesTotalFormat(domain.getTotalImportSolesTotalFormat());

        if (domain.getVoucherEgress() != null &&
                domain.getVoucherEgress().getId() != null &&
                !domain.getVoucherEgress().getId().isEmpty()){
            response.setVoucherEgress(VoucherEgressResponse.from(domain.getVoucherEgress()));
        }
        if (domain.getVoucherEgressDetail() != null &&
                domain.getVoucherEgressDetail().getId() != null &&
                !domain.getVoucherEgressDetail().getId().isEmpty()){
            response.setVoucherEgressDetail(VoucherEgressDetailResponse.from(domain.getVoucherEgressDetail()));
        }
        if (domain.getCurrency() != null &&
                domain.getCurrency().getId() != null &&
                !domain.getCurrency().getId().isEmpty()){
            response.setCurrency(CurrencyResponse.from(domain.getCurrency()));
        }
        if (domain.getServicePqtTourist() != null &&
                domain.getServicePqtTourist().getId() != null &&
                !domain.getServicePqtTourist().getId().isEmpty()){
            response.setServicePqtTourist(ServicePqtTouristResponse.from(domain.getServicePqtTourist()));
        }
        if (domain.getIgv() != null &&
                domain.getIgv().getId() != null &&
                !domain.getIgv().getId().isEmpty()){
            response.setIgv(IgvResponse.from(domain.getIgv()));
        }
        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }
        if (domain.getDetraction() != null &&
                domain.getDetraction().getId() != null &&
                !domain.getDetraction().getId().isEmpty()){
            response.setDetraction(DetractionResponse.from(domain.getDetraction()));
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
