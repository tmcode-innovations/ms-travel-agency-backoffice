package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookinglodging;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgingDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterBookingLodgingRepository extends JasperSubReportService<RegisterBookingLodgingDomain, RegisterBookingItemLodgingDomain> {

    public byte[] export(List<RegisterBookingLodgingDomain> items, List<RegisterBookingItemLodgingDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/booking/JRReguestBookingLodging", "jrxml/booking/JRReguestBookingLodging_subreport_detail", parametersOld);
    }

}
