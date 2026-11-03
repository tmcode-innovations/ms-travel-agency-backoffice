package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registergroup;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRRegisterGroupItineraryRepository extends JasperReportService<ItineraryItemDomain> {

    public byte[] export(List<ItineraryItemDomain> items, String format, Map<String, Object> parametersOld){
        return getExportReport(items, parametersOld, format, "jrxml/booking/JRRegisterGroupItinerary");
    }

}
