package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookingglamping;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemGlampingDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterBookingGlampingCuponRepository extends JasperReportService<RegisterBookingItemGlampingDomain> {

    public byte[] export(List<RegisterBookingItemGlampingDomain> items, String format, Map<String, Object> parametersOld){
        return getExportReport(items, parametersOld, format, "jrxml/booking/JRBookingCuponGlamping");
    }

}
