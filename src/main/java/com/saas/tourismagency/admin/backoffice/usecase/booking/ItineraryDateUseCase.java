package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itinerarydate.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;

import java.time.LocalDate;
import java.util.List;

public interface ItineraryDateUseCase {

    public ItineraryDateResponse create(ItineraryDateCreateRequest request);

    public ItineraryDateResponse edit(String id, ItineraryDateUpdateRequest request);

    public void deleteById(String id);

    public List<ItineraryDateResponse> findAll();

    public ItineraryDateResponse findById(String id);

    public ItineraryDateDomain findByIdDomain(String id);

    public ItineraryDateSearchResponse searchAll(ItineraryDateSearchRequest bankSearchRequest);

    public ItineraryDateSearchResponse searchFilterAll(ItineraryDateSearchFilterRequest bankSearchFilterRequest);

    public List<ItineraryDateResponse> searchAllByRegisterGroup(String idRegisterGroup);

    public void generateDateItinerary(RegisterGroupDomain registerGroup, Integer days, LocalDate dateInit);

    public void generateDateItineraryByProgram(RegisterGroupDomain registerGroup, ProgramDomain program, LocalDate dateInit);

    public List<ItineraryDateDomain> searchAllDomainByRegisterGroup(String idRegisterGroup);

    public ItineraryDateResponse searchByRegisterGroupAndItineraryDate(String idRegisterGroup, String idItineraryDate);
}
