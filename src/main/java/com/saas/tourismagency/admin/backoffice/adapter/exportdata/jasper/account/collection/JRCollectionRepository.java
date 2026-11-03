package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.collection;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.CollectionDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaCreditNoteDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDetailDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRCollectionRepository extends JasperSubReportService<CollectionDomain, DocumentFinanceDetailDomain> {

    public byte[] exportUsd(List<CollectionDomain> items, List<DocumentFinanceDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyCobranzaPreImpress", "jrxml/account/invoice/JRFinancyCobranzaPreImpress_subreport_detail", parametersOld);
    }

    public byte[] exportSoles(List<CollectionDomain> items, List<DocumentFinanceDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyCobranzaPreImpressSoles", "jrxml/account/invoice/JRFinancyCobranzaPreImpressSoles_subreport_detail", parametersOld);
    }
}
