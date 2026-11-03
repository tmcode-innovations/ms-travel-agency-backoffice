package com.saas.tourismagency.admin.backoffice.usecase.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.account.AnalyticsFinanceSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.account.AnalyticsFinanceSearchReportFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.account.AnalyticsFinanceSearchResponse;

public interface AnalyticsFinanceUseCase {

    AnalyticsFinanceSearchResponse<?> searchFilterPaginateIssueDateProformInvoice(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateIssueDateProformNoteCred(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateIssueDateInvoice(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateIssueDateBoleta(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateIssueDateNoteDeb(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateIssueDateNoteCred(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateIssueDateDocumentCobranza(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateCancelationDateProformInvoice(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateCancelationDateProformNoteCred(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateCancelationDateInvoice(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateCancelationDateBoleta(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateCancelationDateNoteDeb(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateCancelationDateNoteCred(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateCancelationDateDocumentCobranza(AnalyticsFinanceSearchFilterRequest request);

    AnalyticsFinanceSearchResponse<?> searchFilterPaginateRegisterSale(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateRegisterSaleOperative(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateRegisterSaleOperativePassenger(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateStateAccountCustomer(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateGroupPendingInvoice(AnalyticsFinanceSearchFilterRequest request);
    AnalyticsFinanceSearchResponse<?> searchFilterPaginateProformCtrl(AnalyticsFinanceSearchFilterRequest request);

    // ---- EXPORT: ISSUE DATE ----
    byte[] exportProformInvoiceByIssueDatePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportProformInvoiceByIssueDateExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportProformNoteCredByIssueDatePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportProformNoteCredByIssueDateExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportInvoiceByIssueDatePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportInvoiceByIssueDateExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportBoletaByIssueDatePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportBoletaByIssueDateExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportNoteDebByIssueDatePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportNoteDebByIssueDateExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportNoteCredByIssueDatePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportNoteCredByIssueDateExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportDocumentCobranzaByIssueDatePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportDocumentCobranzaByIssueDateExcel(AnalyticsFinanceSearchReportFilterRequest request);

    // ---- EXPORT: CANCELATION DATE ----
    byte[] exportProformInvoiceByCancelationDatePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportProformInvoiceByCancelationDateExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportProformNoteCredByCancelationDatePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportProformNoteCredByCancelationDateExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportInvoiceByCancelationDatePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportInvoiceByCancelationDateExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportBoletaByCancelationDatePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportBoletaByCancelationDateExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportNoteDebByCancelationDatePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportNoteDebByCancelationDateExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportNoteCredByCancelationDatePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportNoteCredByCancelationDateExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportDocumentCobranzaByCancelationDatePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportDocumentCobranzaByCancelationDateExcel(AnalyticsFinanceSearchReportFilterRequest request);

    // ---- EXPORT: PHASE 3 REPORTS ----
    byte[] exportRegisterSalePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportRegisterSaleExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportRegisterSaleOperativePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportRegisterSaleOperativeExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportRegisterSaleOperativePassengerPdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportRegisterSaleOperativePassengerExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportStateAccountCustomerPdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportStateAccountCustomerExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportGroupPendingInvoicePdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportGroupPendingInvoiceExcel(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportProformCtrlPdf(AnalyticsFinanceSearchReportFilterRequest request);
    byte[] exportProformCtrlExcel(AnalyticsFinanceSearchReportFilterRequest request);

}
