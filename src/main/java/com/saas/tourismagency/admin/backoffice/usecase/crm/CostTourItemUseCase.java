package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtouritem.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;

import java.util.List;

public interface CostTourItemUseCase {

    public CostTourItemResponse create(CostTourItemCreateRequest request);

    public CostTourItemResponse create(CostTourItemCreateRequest request, QuotationDomain quotationDomain, CostTourDomain costTourDomain);

    public CostTourItemResponse edit(String id, CostTourItemUpdateRequest request);

    public CostTourItemResponse edit(String id, CostTourItemUpdateRequest request, QuotationDomain quotationDomain, CostTourDomain costTourDomain);

    public void deleteById(String id);

    public List<CostTourItemResponse> findAll();

    public CostTourItemResponse findById(String id);

    public CostTourItemSearchResponse searchAll(CostTourItemSearchRequest searchRequest);

    public CostTourItemSearchResponse searchFilterAll(CostTourItemSearchFilterRequest searchFilterRequest);

    public List<CostTourItemResponse> searchAllByQuotation(String idQuotation);

    public CostTourItemResponse searchByQuotationAndCostTourItem(String idQuotation, String idCostTourItem);

    public CostTourItemResponse searchByQuotationAndCostTourAndCostTourItem(String idQuotation, String idCostTour, String idCostTourItem);

    public List<CostTourItemDomain> searchAllDomainByQuotation(String idQuotation);

    public List<CostTourItemDomain> searchAllDomainByQuotationAndCostTour(String idCostTour, String idQuotation);
}
