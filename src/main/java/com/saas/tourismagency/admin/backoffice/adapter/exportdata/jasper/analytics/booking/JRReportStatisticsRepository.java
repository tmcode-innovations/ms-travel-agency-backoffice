package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.analytics.booking;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRReportStatisticsRepository extends JasperReportService<Map> {

    public byte[] exportForeignOperatorPdf(List<Map> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/booking/JRBookingReportStatisticsForeignOperator");
    }

    public byte[] exportForeignOperatorExcel(List<Map> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/booking/JRBookingReportStatisticsForeignOperatorXLS");
    }

    public byte[] exportCountryPdf(List<Map> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/booking/JRBookingReportStatisticsCountry");
    }

    public byte[] exportCountryExcel(List<Map> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/booking/JRBookingReportStatisticsCountryXLS");
    }

    public byte[] exportResponsiblePdf(List<Map> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/booking/JRBookingReportStatisticsResponsible");
    }

    public byte[] exportResponsibleExcel(List<Map> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/booking/JRBookingReportStatisticsResponsibleXLS");
    }

    public byte[] exportHotelPdf(List<Map> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/booking/JRBookingReportStatisticsHotel");
    }

    public byte[] exportHotelExcel(List<Map> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/booking/JRBookingReportStatisticsHotelXLS");
    }
}