package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookinglodging;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterBookingLodgingCuponRepository extends JasperReportService<RegisterBookingItemLodgingDomain> {

    public byte[] export(List<RegisterBookingItemLodgingDomain> items, String format, Map<String, Object> parametersOld){
        return getExportReport(items, parametersOld, format, "jrxml/booking/JRBookingCuponLodging");
    }

    public byte[] exportWithUnion(List<RegisterBookingItemLodgingDomain> items, List<RegisterBookingItemLodgingDomain> itemsOther, String format, Map<String, Object> parametersOld, Map<String, Object> parametersOldOther){
        return getExportReportWithUnionOtherReport(items, itemsOther, parametersOld,parametersOldOther, format, "jrxml/booking/JRBookingCuponLodging", "jrxml/booking/JRBookingCuponLodgingTcLocal");
    }

}
