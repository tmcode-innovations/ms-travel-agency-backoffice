package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.invoicecommission;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.InvoiceCommissionDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRInvoiceCommissionRepository extends JasperSubReportService<InvoiceCommissionDomain, DocumentFinanceDetailDomain> {

    public byte[] exportUsd(List<InvoiceCommissionDomain> items, List<DocumentFinanceDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyInvoiceCommissionPreImpress", "jrxml/account/invoice/JRFinancyInvoiceCommissionPreImpress_subreport_detail", parametersOld);
    }

    public byte[] exportSoles(List<InvoiceCommissionDomain> items, List<DocumentFinanceDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyInvoiceCommissionPreImpressSoles", "jrxml/account/invoice/JRFinancyInvoiceCommissionPreImpressSoles_subreport_detail", parametersOld);
    }
}
