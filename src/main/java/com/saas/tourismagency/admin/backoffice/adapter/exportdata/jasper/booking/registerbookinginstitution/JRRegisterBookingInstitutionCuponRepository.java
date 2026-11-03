package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookinginstitution;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelItemCuponInstitution;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemInstitutionDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterBookingInstitutionCuponRepository extends JasperSubReportService<RptModelItemCuponInstitution, RegisterBookingItemInstitutionDomain> {

    public byte[] export(List<RptModelItemCuponInstitution> items, List<RegisterBookingItemInstitutionDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/booking/JRBookingCuponInstitution", "jrxml/booking/JRBookingCuponInstitution_subreport_detail", parametersOld);
    }

}
