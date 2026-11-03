package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtourserviceitem.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;

import java.util.List;

public interface CostTourServiceItemUseCase {

    public CostTourServiceItemResponse create(CostTourServiceItemCreateRequest request);

    public CostTourServiceItemResponse create(CostTourServiceItemCreateRequest request, QuotationDomain quotationDomain, CostTourDomain costTourDomain);

    public CostTourServiceItemResponse edit(String id, CostTourServiceItemUpdateRequest request);

    public CostTourServiceItemResponse edit(String id, CostTourServiceItemUpdateRequest request, QuotationDomain quotationDomain, CostTourDomain costTourDomain);

    public void deleteById(String id);

    public List<CostTourServiceItemResponse> findAll();

    public CostTourServiceItemResponse findById(String id);

    public CostTourServiceItemSearchResponse searchAll(CostTourServiceItemSearchRequest searchRequest);

    public CostTourServiceItemSearchResponse searchFilterAll(CostTourServiceItemSearchFilterRequest searchFilterRequest);

    public List<CostTourServiceItemResponse> searchAllByQuotation(String idQuotation);

    public CostTourServiceItemResponse searchByQuotationAndCostTourServiceItem(String idQuotation, String idCostTourServiceItem);

    public CostTourServiceItemResponse searchByQuotationAndCostTourAndCostTourServiceItem(String idQuotation, String idCostTour, String idCostTourServiceItem);

    public List<CostTourServiceItemDomain> searchAllDomainByQuotation(String idQuotation);

    public List<CostTourServiceItemDomain> searchAllDomainByQuotationAndCostTour(String idCostTour, String idQuotation);
}
