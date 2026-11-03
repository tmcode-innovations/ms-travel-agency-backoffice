package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tourdate.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;

import java.time.LocalDate;
import java.util.List;

public interface TourDateUseCase {

    public TourDateResponse create(TourDateCreateRequest request);

    public TourDateResponse edit(String id, TourDateUpdateRequest request);

    public void deleteById(String id);

    public List<TourDateResponse> findAll();

    public TourDateResponse findById(String id);

    public TourDateDomain findByIdDomain(String id);

    public TourDateSearchResponse searchAll(TourDateSearchRequest searchRequest);

    public TourDateSearchResponse searchFilterAll(TourDateSearchFilterRequest searchFilterRequest);

    public List<TourDateResponse> searchAllByTour(String idTour);

    public void generateDateTour(TourDomain tour, Integer days);

    public List<TourDateDomain> searchAllDomainByTour(String idTour);

    public TourDateResponse searchByTourAndTourDate(String idTour, String idTourDate);
}
