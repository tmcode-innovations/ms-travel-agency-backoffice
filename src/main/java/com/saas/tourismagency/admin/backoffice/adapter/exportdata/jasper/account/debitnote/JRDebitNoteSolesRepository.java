package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.debitnote;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.RptDocumentAccountDetailSoles;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.debitnote.RptDocumentAccountSoles;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRDebitNoteSolesRepository extends JasperSubReportService<RptDocumentAccountSoles, RptDocumentAccountDetailSoles> {

    public byte[] exportSoles(List<RptDocumentAccountSoles> items, List<RptDocumentAccountDetailSoles> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyNoteDebPreImpressSoles", "jrxml/account/invoice/JRFinancyNoteDebPreImpressSoles_subreport_detail", parametersOld);
    }

}
