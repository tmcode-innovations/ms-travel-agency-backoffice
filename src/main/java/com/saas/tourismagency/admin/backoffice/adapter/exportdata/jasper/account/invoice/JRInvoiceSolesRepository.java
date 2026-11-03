package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.invoice;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.RptDocumentAccountDetailSoles;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.invoice.RptDocumentAccountSoles;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRInvoiceSolesRepository extends JasperSubReportService<RptDocumentAccountSoles, RptDocumentAccountDetailSoles> {

    public byte[] exportSoles(List<RptDocumentAccountSoles> items, List<RptDocumentAccountDetailSoles> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyInvoicePreImpressSoles", "jrxml/account/invoice/JRFinancyInvoicePreImpressSoles_subreport_detail", parametersOld);
    }

}
