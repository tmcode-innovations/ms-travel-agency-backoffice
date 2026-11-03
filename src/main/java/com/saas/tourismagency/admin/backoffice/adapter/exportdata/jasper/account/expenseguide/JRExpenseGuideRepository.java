package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.expenseguide;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRExpenseGuideRepository extends JasperSubReportService<ExpenseGuideDomain, ExpenseGuideDetailDomain> {

    public byte[] export(List<ExpenseGuideDomain> items, List<ExpenseGuideDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/JRFinancyExpenseGuide", "jrxml/account/JRFinancyExpenseGuide_subreport_detail", parametersOld);
    }

}
