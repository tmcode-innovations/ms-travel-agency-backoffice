package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookingglamping;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingGlampingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemGlampingDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterBookingGlampingRepository extends JasperSubReportService<RegisterBookingGlampingDomain, RegisterBookingItemGlampingDomain> {

    public byte[] export(List<RegisterBookingGlampingDomain> items, List<RegisterBookingItemGlampingDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/booking/JRReguestBookingGlamping", "jrxml/booking/JRReguestBookingGlamping_subreport_detail", parametersOld);
    }

}
