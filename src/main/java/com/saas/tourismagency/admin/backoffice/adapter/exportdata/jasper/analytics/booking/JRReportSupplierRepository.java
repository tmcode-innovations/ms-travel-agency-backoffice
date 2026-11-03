package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.analytics.booking;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings("rawtypes")
public class JRReportSupplierRepository extends JasperReportService {

    @SuppressWarnings("unchecked")
    public byte[] exportHotelPdf(List items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/booking/JRBookingReportHotel");
    }

    @SuppressWarnings("unchecked")
    public byte[] exportHotelExcel(List items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/booking/JRBookingReportHotelXLS");
    }

    @SuppressWarnings("unchecked")
    public byte[] exportOperatorPdf(List items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/booking/JRBookingReportOperator");
    }

    @SuppressWarnings("unchecked")
    public byte[] exportOperatorExcel(List items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/booking/JRBookingReportOperatorXLS");
    }

    @SuppressWarnings("unchecked")
    public byte[] exportRestaurantPdf(List items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/booking/JRBookingReportRestaurant");
    }

    @SuppressWarnings("unchecked")
    public byte[] exportRestaurantExcel(List items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/booking/JRBookingReportRestaurantXLS");
    }

    @SuppressWarnings("unchecked")
    public byte[] exportTransportPdf(List items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/booking/JRBookingReportTransport");
    }

    @SuppressWarnings("unchecked")
    public byte[] exportTransportExcel(List items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/booking/JRBookingReportTransportXLS");
    }

    @SuppressWarnings("unchecked")
    public byte[] exportAirLinePdf(List items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/booking/JRBookingReportAirLine");
    }

    @SuppressWarnings("unchecked")
    public byte[] exportAirLineExcel(List items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/booking/JRBookingReportAirLineXLS");
    }
}
