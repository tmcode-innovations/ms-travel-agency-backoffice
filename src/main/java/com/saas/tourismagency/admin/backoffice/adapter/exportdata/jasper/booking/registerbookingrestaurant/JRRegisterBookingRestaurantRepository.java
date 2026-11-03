package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookingrestaurant;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperTwoSubReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelPassenger;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemRestaurantDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingRestaurantDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterBookingRestaurantRepository extends JasperTwoSubReportService<RegisterBookingRestaurantDomain, RegisterBookingItemRestaurantDomain, RptModelPassenger> {

    public byte[] export(List<RegisterBookingRestaurantDomain> items, List<RegisterBookingItemRestaurantDomain> oneSubReportitems, List<RptModelPassenger> twoSubReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithTwoSubReport(items, oneSubReportitems, twoSubReportitems, format, "jrxml/booking/JRReguestBookingRestaurant", "jrxml/booking/JRReguestBookingRestaurant_subreport_detail", "jrxml/booking/JRReguestBookingPassenger_subreport_detail", parametersOld);
    }

}
