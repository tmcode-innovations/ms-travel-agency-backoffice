package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.invoice;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.RptDocumentAccountDetailUsd;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.invoice.RptDocumentAccountUsd;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRInvoiceUsdRepository extends JasperSubReportService<RptDocumentAccountUsd, RptDocumentAccountDetailUsd> {

    public byte[] exportUsd(List<RptDocumentAccountUsd> items, List<RptDocumentAccountDetailUsd> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyInvoicePreImpress", "jrxml/account/invoice/JRFinancyInvoicePreImpress_subreport_detail", parametersOld);
    }

}
