package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookingoperator;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperTwoSubReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelPassenger;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterBookingOperatorRepository extends JasperTwoSubReportService<RegisterBookingOperatorDomain, RegisterBookingItemOperatorDomain, RptModelPassenger> {

    public byte[] export(List<RegisterBookingOperatorDomain> items, List<RegisterBookingItemOperatorDomain> oneSubReportitems, List<RptModelPassenger> twoSubReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithTwoSubReport(items, oneSubReportitems, twoSubReportitems, format, "jrxml/booking/JRReguestBookingOperator", "jrxml/booking/JRReguestBookingOperator_subreport_detail", "jrxml/booking/JRReguestBookingPassenger_subreport_detail", parametersOld);
    }

}
