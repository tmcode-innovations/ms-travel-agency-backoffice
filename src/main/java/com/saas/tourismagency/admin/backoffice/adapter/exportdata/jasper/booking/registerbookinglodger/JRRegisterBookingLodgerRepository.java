package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookinglodger;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperThreeSubReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelPassenger;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterBookingLodgerRepository extends JasperThreeSubReportService<RegisterBookingLodgerDomain, RegisterBookingItemLodgerDomain, RegisterBookingItemLodgerLodgingDomain, RptModelPassenger> {

    public byte[] export(List<RegisterBookingLodgerDomain> items, List<RegisterBookingItemLodgerDomain> oneSubReportitems, List<RegisterBookingItemLodgerLodgingDomain> twoSubReportitems, List<RptModelPassenger> threeSubReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithThreeSubReport(items, oneSubReportitems, twoSubReportitems, threeSubReportitems, format, "jrxml/booking/JRReguestBookingLodger", "jrxml/booking/JRReguestBookingLodger_subreport_detail", "jrxml/booking/JRReguestBookingLodgerLodging_subreport_detail", "jrxml/booking/JRReguestBookingPassenger_subreport_detail", parametersOld);
    }

}
