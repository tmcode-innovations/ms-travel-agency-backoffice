package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.analytics.account;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRReportVoucherPurchaseBySupplierRepository extends JasperReportService<VoucherEgressDetailDomain> {

    public byte[] exportPdf(List<VoucherEgressDetailDomain> items, String format, Map<String, Object> parametersOld){
        return getExportReport(items, parametersOld, format, "jrxml/analytics/JRFinancyReportPurchBySupp");
    }

    public byte[] exportExcel(List<VoucherEgressDetailDomain> items, String format, Map<String, Object> parametersOld){
        return getExportReport(items, parametersOld, format, "jrxml/analytics/JRFinancyReportPurchBySuppXLS");
    }
}
