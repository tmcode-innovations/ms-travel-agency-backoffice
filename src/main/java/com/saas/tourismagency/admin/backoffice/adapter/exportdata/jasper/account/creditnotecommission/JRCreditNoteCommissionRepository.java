package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.creditnotecommission;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.CreditNoteCommissionDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRCreditNoteCommissionRepository extends JasperSubReportService<CreditNoteCommissionDomain, DocumentFinanceDetailDomain> {

    public byte[] exportUsd(List<CreditNoteCommissionDomain> items, List<DocumentFinanceDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyNoteCredCommissionPreImpress", "jrxml/account/invoice/JRFinancyNoteCredCommissionPreImpress_subreport_detail", parametersOld);
    }

    public byte[] exportSoles(List<CreditNoteCommissionDomain> items, List<DocumentFinanceDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyNoteCredCommissionPreImpressSoles", "jrxml/account/invoice/JRFinancyNoteCredCommissionPreImpressSoles_subreport_detail", parametersOld);
    }
}
