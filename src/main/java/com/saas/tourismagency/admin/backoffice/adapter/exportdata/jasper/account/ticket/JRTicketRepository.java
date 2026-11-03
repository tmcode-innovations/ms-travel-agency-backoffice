package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.ticket;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.InvoiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.TicketDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRTicketRepository extends JasperSubReportService<TicketDomain, DocumentFinanceDetailDomain> {

    public byte[] exportUsd(List<TicketDomain> items, List<DocumentFinanceDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyBoletaPreImpress", "jrxml/account/invoice/JRFinancyBoletaPreImpress_subreport_detail", parametersOld);
    }

    public byte[] exportSoles(List<TicketDomain> items, List<DocumentFinanceDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyBoletaPreImpressSoles", "jrxml/account/invoice/JRFinancyBoletaPreImpressSoles_subreport_detail", parametersOld);
    }
}
