package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookinglodger;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelItemCuponLodger;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgerDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterBookingLodgerCuponRepository extends JasperSubReportService<RptModelItemCuponLodger, RegisterBookingItemLodgerDomain> {

    public byte[] export(List<RptModelItemCuponLodger> items, List<RegisterBookingItemLodgerDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/booking/JRBookingCuponLodger", "jrxml/booking/JRBookingCuponLodger_subreport_detail", parametersOld);
    }

}
