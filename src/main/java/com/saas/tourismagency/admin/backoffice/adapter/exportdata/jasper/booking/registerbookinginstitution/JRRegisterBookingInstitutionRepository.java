package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookinginstitution;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperTwoSubReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelPassenger;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingInstitutionDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemInstitutionDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterBookingInstitutionRepository extends JasperTwoSubReportService<RegisterBookingInstitutionDomain, RegisterBookingItemInstitutionDomain, RptModelPassenger> {

    public byte[] export(List<RegisterBookingInstitutionDomain> items, List<RegisterBookingItemInstitutionDomain> oneSubReportitems, List<RptModelPassenger> twoSubReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithTwoSubReport(items, oneSubReportitems, twoSubReportitems, format, "jrxml/booking/JRReguestBookingInstitution", "jrxml/booking/JRReguestBookingInstitution_subreport_detail", "jrxml/booking/JRReguestBookingPassenger_subreport_detail", parametersOld);
    }

}
