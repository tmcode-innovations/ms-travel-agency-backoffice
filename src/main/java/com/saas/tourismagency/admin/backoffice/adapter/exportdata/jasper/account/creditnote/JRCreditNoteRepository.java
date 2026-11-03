package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.creditnote;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.CollectionDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CreditNoteDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRCreditNoteRepository extends JasperSubReportService<CreditNoteDomain, DocumentFinanceDetailDomain> {

    public byte[] exportUsd(List<CreditNoteDomain> items, List<DocumentFinanceDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyNoteCredPreImpress", "jrxml/account/invoice/JRFinancyNoteCredPreImpress_subreport_detail", parametersOld);
    }

    public byte[] exportSoles(List<CreditNoteDomain> items, List<DocumentFinanceDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyNoteCredPreImpressSoles", "jrxml/account/invoice/JRFinancyNoteCredPreImpressSoles_subreport_detail", parametersOld);
    }
}
