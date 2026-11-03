package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.analytics.booking;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgingDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRReportLodgingRepository extends JasperReportService<RegisterBookingLodgingDomain> {

    public byte[] exportHotelExpirationPdf(List<RegisterBookingLodgingDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "pdf", "jrxml/analytics/booking/JRBookingReportHotelExpiration");
    }

    public byte[] exportHotelExpirationExcel(List<RegisterBookingLodgingDomain> items, Map<String, Object> parameters) {
        return getExportReport(items, parameters, "xlsx", "jrxml/analytics/booking/JRBookingReportHotelExpirationXLS");
    }
}
