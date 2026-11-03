package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookinglodgerlodging;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgerLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterBookingLodLodgingCuponRepository extends JasperReportService<RegisterBookingItemLodgerLodgingDomain> {

    public byte[] export(List<RegisterBookingItemLodgerLodgingDomain> items, String format, Map<String, Object> parametersOld){
        return getExportReport(items, parametersOld, format, "jrxml/booking/JRBookingCuponLodgerLodging");
    }

    public byte[] exportWithUnion(List<RegisterBookingItemLodgerLodgingDomain> items, List<RegisterBookingItemLodgerLodgingDomain> itemsOther,String format, Map<String, Object> parametersOld, Map<String, Object> parametersOldOther){
        return getExportReportWithUnionOtherReport(items, itemsOther, parametersOld, parametersOldOther, format, "jrxml/booking/JRBookingCuponLodgerLodging", "jrxml/booking/JRBookingCuponLodgerLodgingTcLocal");
    }

}
