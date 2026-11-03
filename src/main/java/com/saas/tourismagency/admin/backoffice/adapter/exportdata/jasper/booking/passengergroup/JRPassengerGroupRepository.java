package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.passengergroup;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRPassengerGroupRepository extends JasperSubReportService<RegisterGroupDomain, PassengerGroupDomain> {

    public byte[] export(List<RegisterGroupDomain> items, List<PassengerGroupDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/booking/JRPassengerStandard", "jrxml/booking/JRPassengerStandard_subreport_item", parametersOld);
    }

}
