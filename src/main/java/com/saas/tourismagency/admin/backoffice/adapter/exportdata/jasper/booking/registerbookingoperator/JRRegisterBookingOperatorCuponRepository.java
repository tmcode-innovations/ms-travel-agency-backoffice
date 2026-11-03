package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookingoperator;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelItemCuponOperator;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemOperatorDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterBookingOperatorCuponRepository extends JasperSubReportService<RptModelItemCuponOperator, RegisterBookingItemOperatorDomain> {

    public byte[] export(List<RptModelItemCuponOperator> items, List<RegisterBookingItemOperatorDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/booking/JRBookingCuponOperator", "jrxml/booking/JRBookingCuponOperator_subreport_detail", parametersOld);
    }

    public byte[] exportWithUnion(List<RptModelItemCuponOperator> items, List<RegisterBookingItemOperatorDomain> subReportitems, List<RptModelItemCuponOperator> itemsOther, List<RegisterBookingItemOperatorDomain> subReportitemsOther, String format, Map<String, Object> parametersOld, Map<String, Object> parametersOldOther){
        return getExportReportWithSubReportWithUnionOtherReport(items, subReportitems, itemsOther, subReportitemsOther, format, "jrxml/booking/JRBookingCuponOperator", "jrxml/booking/JRBookingCuponOperatorTcLocal", "jrxml/booking/JRBookingCuponOperator_subreport_detail", parametersOld, parametersOldOther);
    }

}
