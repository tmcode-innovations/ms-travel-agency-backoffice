package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.analytics.booking;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRReportPassengerRepository extends JasperReportService<PassengerGroupDomain> {

    public byte[] exportPdf(List<PassengerGroupDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/booking/JRBookingReportPassenger");
    }

    public byte[] exportExcel(List<PassengerGroupDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/booking/JRBookingReportPassengerXLS");
    }
}
