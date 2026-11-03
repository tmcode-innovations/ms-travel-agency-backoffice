package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registergroup;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptExportCuponAllGeneric;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterBookingCuponAllRepository extends JasperReportService<RptExportCuponAllGeneric> {

    public byte[] export(List<RptExportCuponAllGeneric> items, String format, Map<String, Object> parametersOld){
        return getExportReport(items, parametersOld, format, "jrxml/booking/JRBookingCuponAll");
    }

}
