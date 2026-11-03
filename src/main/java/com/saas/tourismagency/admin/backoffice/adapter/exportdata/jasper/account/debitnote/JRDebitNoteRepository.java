package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.debitnote;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.CreditNoteDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DebitNoteDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRDebitNoteRepository extends JasperSubReportService<DebitNoteDomain, DocumentFinanceDetailDomain> {

    public byte[] exportUsd(List<DebitNoteDomain> items, List<DocumentFinanceDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyNoteDebPreImpress", "jrxml/account/invoice/JRFinancyNoteDebPreImpress_subreport_detail", parametersOld);
    }

    public byte[] exportSoles(List<DebitNoteDomain> items, List<DocumentFinanceDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyNoteDebPreImpressSoles", "jrxml/account/invoice/JRFinancyNoteDebPreImpressSoles_subreport_detail", parametersOld);
    }
}
