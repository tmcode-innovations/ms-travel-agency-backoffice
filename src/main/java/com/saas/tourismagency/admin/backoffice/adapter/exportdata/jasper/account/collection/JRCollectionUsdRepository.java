package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.collection;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.RptDocumentAccountDetailUsd;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.collection.RptDocumentAccountUsd;
import com.saas.tourismagency.admin.backoffice.domain.account.CollectionDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRCollectionUsdRepository extends JasperSubReportService<RptDocumentAccountUsd, RptDocumentAccountDetailUsd> {

    public byte[] exportUsd(List<RptDocumentAccountUsd> items, List<RptDocumentAccountDetailUsd> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/invoice/JRFinancyCobranzaPreImpress", "jrxml/account/invoice/JRFinancyCobranzaPreImpress_subreport_detail", parametersOld);
    }

}
