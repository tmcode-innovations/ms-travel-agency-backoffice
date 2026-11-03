package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.analytics.booking;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.RegisterGroupReportItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRReportRegisterGroupRepository extends JasperReportService<RegisterGroupReportItem> {

    public byte[] exportPdf(List<RegisterGroupReportItem> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/booking/JRBookingReportGroup");
    }

    public byte[] exportExcel(List<RegisterGroupReportItem> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/booking/JRBookingReportGroupXLS");
    }

    public byte[] exportGroupByProgramPdf(List<RegisterGroupReportItem> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/booking/JRBookingReportGroupByProgram");
    }

    public byte[] exportGroupByProgramExcel(List<RegisterGroupReportItem> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/booking/JRBookingReportGroupByProgramXLS");
    }
}
