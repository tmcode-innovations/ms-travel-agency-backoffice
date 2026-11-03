package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.analytics.account;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRReportProformaFinanceRepository extends JasperReportService<ProformaDomain> {

    public byte[] exportProformInvoiceByIssueDatePdf(List<ProformaDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRProformInvoiceEmitDatePDF");
    }

    public byte[] exportProformInvoiceByIssueDateExcel(List<ProformaDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRProformInvoiceEmitDateXLS");
    }

    public byte[] exportProformInvoiceByCancelationDatePdf(List<ProformaDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRProformInvoiceCancelDatePDF");
    }

    public byte[] exportProformInvoiceByCancelationDateExcel(List<ProformaDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRProformInvoiceCancelDateXLS");
    }

    public byte[] exportProformNoteCredByIssueDatePdf(List<ProformaDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRProformNoteCredEmitDatePDF");
    }

    public byte[] exportProformNoteCredByIssueDateExcel(List<ProformaDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRProformNoteCredEmitDateXLS");
    }

    public byte[] exportProformNoteCredByCancelationDatePdf(List<ProformaDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRProformNoteCredCancelDatePDF");
    }

    public byte[] exportProformNoteCredByCancelationDateExcel(List<ProformaDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRProformNoteCredCancelDateXLS");
    }

    public byte[] exportStateAccountCustomerPdf(List<ProformaDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRStateAccountCustomerPDF");
    }

    public byte[] exportStateAccountCustomerExcel(List<ProformaDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRStateAccountCustomerXLS");
    }

    public byte[] exportGroupPendingInvoicePdf(List<ProformaDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRGroupPendingInvoicePDF");
    }

    public byte[] exportGroupPendingInvoiceExcel(List<ProformaDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRGroupPendingInvoiceXLS");
    }

    public byte[] exportProformCtrlPdf(List<ProformaDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/account/JRProformCtrlPDF");
    }

    public byte[] exportProformCtrlExcel(List<ProformaDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/account/JRProformCtrlXLS");
    }
}
