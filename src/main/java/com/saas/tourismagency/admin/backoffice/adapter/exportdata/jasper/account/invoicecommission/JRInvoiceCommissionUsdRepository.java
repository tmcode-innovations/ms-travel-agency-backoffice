package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.invoicecommission;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.RptDocumentAccountDetailUsd;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.invoicecommission.RptDocumentAccountUsd;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRInvoiceCommissionUsdRepository extends JasperSubReportService<RptDocumentAccountUsd, RptDocumentAccountDetailUsd> {

    public byte[] exportUsd(List<RptDocumentAccountUsd> items, List<RptDocumentAccountDetailUsd> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyInvoiceCommissionPreImpress", "jrxml/account/invoice/JRFinancyInvoiceCommissionPreImpress_subreport_detail", parametersOld);
    }

}
