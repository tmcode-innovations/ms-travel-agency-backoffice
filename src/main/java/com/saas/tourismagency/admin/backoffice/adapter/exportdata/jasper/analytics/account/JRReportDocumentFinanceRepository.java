package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.analytics.account;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRReportDocumentFinanceRepository extends JasperReportService<DocumentFinanceDomain> {

    public byte[] exportInvoiceByIssueDatePdf(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRInvoiceEmitDatePDF");
    }

    public byte[] exportInvoiceByIssueDateExcel(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRInvoiceEmitDateXLS");
    }

    public byte[] exportInvoiceByCancelationDatePdf(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRInvoiceCancelDatePDF");
    }

    public byte[] exportInvoiceByCancelationDateExcel(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRInvoiceCancelDateXLS");
    }

    public byte[] exportBoletaByIssueDatePdf(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRBoletaEmitDatePDF");
    }

    public byte[] exportBoletaByIssueDateExcel(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRBoletaEmitDateXLS");
    }

    public byte[] exportBoletaByCancelationDatePdf(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRBoletaCancelDatePDF");
    }

    public byte[] exportBoletaByCancelationDateExcel(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRBoletaCancelDateXLS");
    }

    public byte[] exportNoteDebByIssueDatePdf(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRNoteDebEmitDatePDF");
    }

    public byte[] exportNoteDebByIssueDateExcel(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRNoteDebEmitDateXLS");
    }

    public byte[] exportNoteDebByCancelationDatePdf(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRNoteDebCancelDatePDF");
    }

    public byte[] exportNoteDebByCancelationDateExcel(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRNoteDebCancelDateXLS");
    }

    public byte[] exportNoteCredByIssueDatePdf(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRNoteCredEmitDatePDF");
    }

    public byte[] exportNoteCredByIssueDateExcel(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRNoteCredEmitDateXLS");
    }

    public byte[] exportNoteCredByCancelationDatePdf(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRNoteCredCancelDatePDF");
    }

    public byte[] exportNoteCredByCancelationDateExcel(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRNoteCredCancelDateXLS");
    }

    public byte[] exportDocumentCobranzaByIssueDatePdf(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRDocumentCobranzaEmitDatePDF");
    }

    public byte[] exportDocumentCobranzaByIssueDateExcel(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRDocumentCobranzaEmitDateXLS");
    }

    public byte[] exportDocumentCobranzaByCancelationDatePdf(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRDocumentCobranzaCancelDatePDF");
    }

    public byte[] exportDocumentCobranzaByCancelationDateExcel(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRDocumentCobranzaCancelDateXLS");
    }

    public byte[] exportRegisterSalePdf(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRRegisterSalePDF");
    }

    public byte[] exportRegisterSaleExcel(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRRegisterSaleXLS");
    }

    public byte[] exportRegisterSaleOperativePdf(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRRegisterSaleOperativePDF");
    }

    public byte[] exportRegisterSaleOperativeExcel(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRRegisterSaleOperativeXLS");
    }

    public byte[] exportRegisterSaleOperativePassengerPdf(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRRegisterSaleOperativePassengerPDF");
    }

    public byte[] exportRegisterSaleOperativePassengerExcel(List<DocumentFinanceDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRRegisterSaleOperativePassengerXLS");
    }
}
