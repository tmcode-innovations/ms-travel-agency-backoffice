package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.collection;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.RptDocumentAccountDetailSoles;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.collection.RptDocumentAccountSoles;
import com.saas.tourismagency.admin.backoffice.domain.account.CollectionDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRCollectionSolesRepository extends JasperSubReportService<RptDocumentAccountSoles, RptDocumentAccountDetailSoles> {

    public byte[] exportSoles(List<RptDocumentAccountSoles> items, List<RptDocumentAccountDetailSoles> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyCobranzaPreImpressSoles", "jrxml/account/invoice/JRFinancyCobranzaPreImpressSoles_subreport_detail", parametersOld);
    }
}
