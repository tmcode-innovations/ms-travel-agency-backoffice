package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.touitem.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourItemDomain;

import java.util.List;

public interface TourItemUseCase {

    public TourItemResponse create(TourItemCreateRequest request);

    public TourItemResponse create(TourItemCreateRequest request, TourDomain tourDomain);

    public List<TourItemResponse>  createMultiple(TourItemCreateRequest request, TourDomain tourDomain);

    public TourItemResponse edit(String id, TourItemUpdateRequest request);

    public TourItemResponse edit(String id, TourItemUpdateRequest request, TourDomain tourDomain);

    public void deleteById(String id);

    public List<TourItemResponse> findAll();

    public TourItemResponse findById(String id);

    public TourItemSearchResponse searchAll(TourItemSearchRequest searchRequest);

    public TourItemSearchResponse searchFilterAll(TourItemSearchFilterRequest searchFilterRequest);

    public List<TourItemResponse> searchAllByTour(String idTour);

    public TourItemResponse searchByTourAndTourItem(String idTour, String idTourItem);

    public List<TourItemDomain> searchAllDomainByTour(String idTour);

    public List<TourItemDomain> searchAllDomainByTourAndTourDate(String idTourDate, String idTour);
}
