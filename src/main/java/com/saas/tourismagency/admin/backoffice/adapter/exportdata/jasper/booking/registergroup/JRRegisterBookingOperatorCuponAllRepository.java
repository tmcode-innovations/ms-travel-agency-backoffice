package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registergroup;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptCuponGenericDetail;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.stereotype.Service;

@Service
public class JRRegisterBookingOperatorCuponAllRepository extends JasperReportService<RptCuponGenericDetail> {

    public JasperReport compiler(){
        return getJasperReportCompiler("jrxml/booking/JRBookingCuponAllOperator_subreport_detail");
    }

}
