package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.proformcreditnote;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaCreditNoteDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDetailDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRProformCreditNoteRepository extends JasperSubReportService<ProformaCreditNoteDomain, ProformaDetailDomain> {

    public byte[] exportUsd(List<ProformaCreditNoteDomain> items, List<ProformaDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/proform/JRFinancyProformNoteCred", "jrxml/account/proform/JRFinancyProformNoteCred_subreport_detail", parametersOld);
    }

    public byte[] exportSoles(List<ProformaCreditNoteDomain> items, List<ProformaDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/proform/JRFinancyProformNoteCredSoles", "jrxml/account/proform/JRFinancyProformNoteCredSoles_subreport_detail", parametersOld);
    }
}
