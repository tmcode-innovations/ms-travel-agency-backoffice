package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.crm.quotation;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.CurrencyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour.CostTourItemModelView;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour.CostTourModelView;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRQuotationCostTypeGuruRepository extends JasperSubReportService<QuotationDomain, CostTourDomain> {

    @Autowired
    private JasperSubReportQuotationService jasperSubReportQuotationService;

    public byte[] export(List<QuotationDomain> items, List<CostTourModelView> subReportitems, List<CostTourModelView> subReportitemsLodging, List<CostTourModelView> subReportitemsOther, String format, Map<String, Object> parametersOld){
        return jasperSubReportQuotationService.getExportReportWithSubReportOtherV1(items, subReportitems, subReportitemsLodging, subReportitemsOther, format, "jrxml/crm/JRQuotationCostTourGuruExplorers", "jrxml/crm/JRQuotationCostTourGuruExplorers_subreport_item", "jrxml/crm/JRQuotationCostTourGuruExplorers_subreport_item_lodging", "jrxml/crm/JRQuotationCostTourAndCostTourItemGuruExplorers_subreport_item", "jrxml/crm/JRQuotationCostTourAndCostTourItemOtherGuruExplorers_subreport_item", "jrxml/crm/JRQuotationCostTourAndCostTourItemOtherGuruExplorers_subreport_item_1", parametersOld);
    }

    public byte[] exportV2(List<QuotationDomain> items, List<CostTourModelView> subReportitems, List<CostTourModelView> subReportitemsLodging, List<CostTourItemModelView> subReportitemsGroupPassengerType, List<CostTourModelView> subReportitemsOther, String format, Map<String, Object> parametersOld){
        return jasperSubReportQuotationService.getExportReportWithSubReportOtherV2(items, subReportitems, subReportitemsLodging, subReportitemsGroupPassengerType, subReportitemsOther, format, "jrxml/crm/JRQuotationCostTourGuruExplorers", "jrxml/crm/JRQuotationCostTourGuruExplorers_subreport_item", "jrxml/crm/JRQuotationCostTourGuruExplorers_subreport_item_lodging", "jrxml/crm/JRQuotationCostTourGuruExplorers_subreport_item_group_passenger_type", "jrxml/crm/JRQuotationCostTourAndCostTourItemGuruExplorers_subreport_item", "jrxml/crm/JRQuotationCostTourAndCostTourItemOtherGuruExplorers_subreport_item", "jrxml/crm/JRQuotationCostTourAndCostTourItemOtherGuruExplorers_subreport_item_1", parametersOld);
    }

}
