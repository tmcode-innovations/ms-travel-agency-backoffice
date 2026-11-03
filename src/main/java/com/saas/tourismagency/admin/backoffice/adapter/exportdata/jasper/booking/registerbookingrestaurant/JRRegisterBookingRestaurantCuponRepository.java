package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookingrestaurant;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelItemCuponRestaurant;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemRestaurantDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterBookingRestaurantCuponRepository extends JasperSubReportService<RptModelItemCuponRestaurant, RegisterBookingItemRestaurantDomain> {

    public byte[] export(List<RptModelItemCuponRestaurant> items, List<RegisterBookingItemRestaurantDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/booking/JRBookingCuponRestaurant", "jrxml/booking/JRBookingCuponRestaurant_subreport_detail", parametersOld);
    }

    public byte[] exportWithUnion(List<RptModelItemCuponRestaurant> items, List<RegisterBookingItemRestaurantDomain> subReportitems, List<RptModelItemCuponRestaurant> itemsOther, List<RegisterBookingItemRestaurantDomain> subReportitemsOther, String format, Map<String, Object> parametersOld, Map<String, Object> parametersOldOther){
        return getExportReportWithSubReportWithUnionOtherReport(items, subReportitems,itemsOther, subReportitemsOther, format, "jrxml/booking/JRBookingCuponRestaurant", "jrxml/booking/JRBookingCuponRestaurantTcLocal", "jrxml/booking/JRBookingCuponRestaurant_subreport_detail", parametersOld, parametersOldOther);
    }

}
