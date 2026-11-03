package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itineraryitem.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;

import java.util.List;

public interface ItineraryItemCrmUseCase {

    public ItineraryItemResponse create(ItineraryItemCreateRequest request);

    public ItineraryItemResponse create(ItineraryItemCreateRequest request, QuotationDomain quotationDomain);

    public void  createMultiple(ItineraryItemCreateRequest request, QuotationDomain quotationDomain);

    public void createTour(ItineraryTourItemCreateRequest request, QuotationDomain quotationDomain);

    public void  createAdvanced(ItineraryServiceAdvancedCreateRequest request, QuotationDomain quotationDomain);

    public void createTourServiceAdditional(ItineraryCostTourServiceItemCreateRequest request, QuotationDomain quotationDomain);

    public ItineraryItemResponse edit(String id, ItineraryItemUpdateRequest request);

    public ItineraryItemResponse edit(String id, ItineraryItemUpdateRequest request, QuotationDomain quotationDomain);

    public void deleteById(String id);

    public List<ItineraryItemResponse> findAll();

    public ItineraryItemResponse findById(String id);

    public ItineraryItemSearchResponse searchAll(ItineraryItemSearchRequest searchRequest);

    public ItineraryItemSearchResponse searchFilterAll(ItineraryItemSearchFilterRequest searchFilterRequest);

    public List<ItineraryItemResponse> searchAllByQuotation(String idQuotation);

    public ItineraryItemResponse searchByQuotationAndItineraryItem(String idQuotation, String idItineraryItem);

    public List<ItineraryItemDomain> searchAllDomainByQuotation(String idQuotation);

    public List<ItineraryItemDomain> searchAllDomainByQuotationAndItineraryDate(String idItineraryDate, String idQuotation);
}
