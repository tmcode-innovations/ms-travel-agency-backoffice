package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.crm.quotation;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRQuotationTypeGuruRepository extends JasperSubReportService<QuotationDomain, ItineraryItemDomain> {

    public byte[] export(List<QuotationDomain> items, List<ItineraryItemDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/crm/JRQuotationGuruExplorers", "jrxml/crm/JRQuotationGuruExplorers_subreport_item", parametersOld);
    }

}
