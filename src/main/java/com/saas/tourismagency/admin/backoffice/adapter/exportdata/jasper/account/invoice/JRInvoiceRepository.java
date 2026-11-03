package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.invoice;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.DebitNoteDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.InvoiceDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRInvoiceRepository extends JasperSubReportService<InvoiceDomain, DocumentFinanceDetailDomain> {

    public byte[] exportUsd(List<InvoiceDomain> items, List<DocumentFinanceDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyInvoicePreImpress", "jrxml/account/invoice/JRFinancyInvoicePreImpress_subreport_detail", parametersOld);
    }

    public byte[] exportSoles(List<InvoiceDomain> items, List<DocumentFinanceDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyInvoicePreImpressSoles", "jrxml/account/invoice/JRFinancyInvoicePreImpressSoles_subreport_detail", parametersOld);
    }
}
