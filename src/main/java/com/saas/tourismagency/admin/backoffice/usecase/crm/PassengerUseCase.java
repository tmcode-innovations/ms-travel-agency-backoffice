package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.passenger.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;

import java.util.List;

public interface PassengerUseCase {

    public PassengerResponse create(PassengerCreateRequest request);

    public PassengerResponse create(PassengerCreateRequest request, QuotationDomain quotationDomain);

    public PassengerResponse edit(String id, PassengerUpdateRequest request);

    public PassengerResponse edit(String id, PassengerUpdateRequest request, QuotationDomain quotationDomain);

    public void deleteById(String id);

    public List<PassengerResponse> findAll();

    public PassengerResponse findById(String id);

    public PassengerSearchResponse searchAll(PassengerSearchRequest passengerSearchRequest);

    public PassengerSearchResponse searchFilterAll(PassengerSearchFilterRequest passengerSearchFilterRequest);

    public List<PassengerResponse> searchAllByQuotation(String idQuotation);

    public PassengerResponse searchByQuotationAndPassenger(String idQuotation, String idPassenger);
}
