package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.analytics.booking;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRReportGuideRepository extends JasperReportService<ItineraryItemDomain> {

    public byte[] exportPdf(List<ItineraryItemDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/booking/JRBookingReportGuide");
    }

    public byte[] exportExcel(List<ItineraryItemDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/booking/JRBookingReportGuideXLS");
    }
}
