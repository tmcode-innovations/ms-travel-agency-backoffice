package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itinerarydate.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;

import java.time.LocalDate;
import java.util.List;

public interface ItineraryDateCrmUseCase {

    public ItineraryDateResponse create(ItineraryDateCreateRequest request);

    public ItineraryDateResponse edit(String id, ItineraryDateUpdateRequest request);

    public void deleteById(String id);

    public List<ItineraryDateResponse> findAll();

    public ItineraryDateResponse findById(String id);

    public ItineraryDateDomain findByIdDomain(String id);

    public ItineraryDateSearchResponse searchAll(ItineraryDateSearchRequest searchRequest);

    public ItineraryDateSearchResponse searchFilterAll(ItineraryDateSearchFilterRequest searchFilterRequest);

    public List<ItineraryDateResponse> searchAllByQuotation(String idQuotation);

    public void generateDateItinerary(QuotationDomain quotation, Integer days, LocalDate dateInit);

    public void generateDateItineraryByProgram(QuotationDomain quotation, ProgramDomain program, LocalDate dateInit);

    public List<ItineraryDateDomain> searchAllDomainByQuotation(String idQuotation);

    public ItineraryDateResponse searchByQuotationAndItineraryDate(String idQuotation, String idItineraryDate);
}
