package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.proforminvoice;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaInvoiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRProformInvoiceRepository extends JasperSubReportService<ProformaInvoiceDomain, ProformaDetailDomain> {

    public byte[] exportUsd(List<ProformaInvoiceDomain> items, List<ProformaDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/proform/JRFinancyProformInvoice", "jrxml/account/proform/JRFinancyProformInvoice_subreport_detail", parametersOld);
    }

    public byte[] exportSoles(List<ProformaInvoiceDomain> items, List<ProformaDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/proform/JRFinancyProformInvoiceSoles", "jrxml/account/proform/JRFinancyProformInvoiceSoles_subreport_detail", parametersOld);
    }
}
