package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.operation.orderservice;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JROrderServiceOperationRepository extends JasperSubReportService<OrderServiceDomain, OrderServiceItemDomain> {

    public byte[] export(List<OrderServiceDomain> items, List<OrderServiceItemDomain> subReportitems, String format, Map<String, Object> parametersOld){
        return getExportReportWithSubReport(items, subReportitems, format, "jrxml/operation/JROrderServiceOperation", "jrxml/operation/JROrderServiceOperation_subreport_detail", parametersOld);
    }

}
