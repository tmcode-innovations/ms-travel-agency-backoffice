package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;

import java.util.List;

public interface CostTourUseCase {

    public CostTourResponse create(CostTourCreateRequest request);

    public CostTourResponse create(CostTourCreateRequest request, QuotationDomain quotationDomain);

    public CostTourResponse edit(String id, CostTourUpdateRequest request);

    public CostTourResponse edit(String id, CostTourUpdateRequest request, QuotationDomain quotationDomain);

    public void deleteById(String id);

    public List<CostTourResponse> findAll();

    public CostTourResponse findById(String id);

    public CostTourDomain findByIdDomain(String id);

    public CostTourSearchResponse searchAll(CostTourSearchRequest searchRequest);

    public CostTourSearchResponse searchFilterAll(CostTourSearchFilterRequest searchFilterRequest);

    public List<CostTourResponse> searchAllByQuotation(String idQuotation);

    public List<CostTourDomain> searchAllDomainByQuotation(String idQuotation);

    public CostTourResponse searchByQuotationAndCostTour(String idQuotation, String idCostTour);
}
