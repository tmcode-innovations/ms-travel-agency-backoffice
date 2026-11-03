package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.voucheringress;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRVoucherIngressRepository extends JasperSubReportService<VoucherIngressDomain, VoucherIngressDetailDomain> {

    public byte[] export(List<VoucherIngressDomain> items, List<VoucherIngressDetailDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/account/JRFinancyVoucherIngress", "jrxml/account/JRFinancyVoucherIngress_subreport_detail", parametersOld);
    }

}
