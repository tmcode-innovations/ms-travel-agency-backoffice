package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.voucheregress;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRVoucherEgressRepository extends JasperSubReportService<VoucherEgressDomain, VoucherEgressDetailDomain> {

    public byte[] exportUsd(List<VoucherEgressDomain> items, List<VoucherEgressDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/JRFinancyVoucherEgress", "jrxml/account/JRFinancyVoucherEgress_subreport_detail", parametersOld);
    }

    public byte[] exportSoles(List<VoucherEgressDomain> items, List<VoucherEgressDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/JRFinancyVoucherEgressSoles", "jrxml/account/JRFinancyVoucherEgressSoles_subreport_detail", parametersOld);
    }

    public byte[] exportUsdGuru(List<VoucherEgressDomain> items, List<VoucherEgressDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/JRFinancyVoucherEgressGuru", "jrxml/account/JRFinancyVoucherEgressGuru_subreport_detail", parametersOld);
    }

    public byte[] exportSolesGuru(List<VoucherEgressDomain> items, List<VoucherEgressDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/JRFinancyVoucherEgressSolesGuru", "jrxml/account/JRFinancyVoucherEgressSolesGuru_subreport_detail", parametersOld);
    }
}
