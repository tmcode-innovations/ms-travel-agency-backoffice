package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.costsheettwo;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.CostSheetTwoDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CostSheetTwoDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRCostSheetTwoRepository extends JasperSubReportService<CostSheetTwoDomain, CostSheetTwoDetailDomain> {

    public byte[] exportGuru(List<CostSheetTwoDomain> items, List<CostSheetTwoDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/JRFinancyCostSheetTwoGuru", "jrxml/account/JRFinancyCostSheetTwoGuru_subreport_detail", parametersOld);
    }

    public byte[] exportLegendary(List<CostSheetTwoDomain> items, List<CostSheetTwoDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/JRFinancyCostSheetTwo", "jrxml/account/JRFinancyCostSheetTwo_subreport_detail", parametersOld);
    }

}
