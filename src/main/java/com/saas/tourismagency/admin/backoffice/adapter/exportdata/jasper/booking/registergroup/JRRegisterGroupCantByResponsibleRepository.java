package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registergroup;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterGroupCantByResponsibleRepository extends JasperReportService<RegisterGroupDomain> {

    public byte[] export(List<RegisterGroupDomain> items, Map<String, Object> parameters){
        return getExportReport(items, parameters, ExportFormatEnum.PDF.getName(), "jrxml/booking/JRRegisterGroupCantByResponsible");
    }

}