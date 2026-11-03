package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.touitem.TourItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.touitem.TourItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.touitem.TourItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tourdate.TourDateAllItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tourdate.TourDateResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tourdate.TourDateUpdateOnlyNameRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tourdate.TourModelViewResponse;

import java.util.List;

public interface TourUseCase {

    public TourResponse create(TourCreateRequest request);

    public TourResponse edit(String id, TourUpdateRequest request);

    public void deleteById(String id);

    public List<TourResponse> findAll();

    public TourResponse findById(String id);

    public TourSearchResponse searchAll(TourSearchRequest tourSearchRequest);

    public TourSearchResponse searchFilterAll(TourSearchFilterRequest tourSearchFilterRequest);

    public TourResponse findByName(String name);

    public TourResponse findByIdentify(String identify);


    public List<TourItemResponse> findAllTourItemByTour(String idTour);

    public List<TourDateResponse> findAllTourDateByTour(String idTour);

    public TourItemResponse addTour(TourItemCreateRequest request, String idTour);

    public List<TourItemResponse> addTourMultiple(TourItemCreateRequest request, String idTour);

    public TourItemResponse editTour(String id, TourItemUpdateRequest request, String idTour);

    public void deleteTour(String id, String idTour);

    public TourItemResponse findByTourItemIdAndTour(String id, String idTour);

    public TourDateResponse findByTourDateIdAndTour(String id, String idTour);

    public List<TourDateAllItemResponse> findAllTourDateWithTourItemByTour(String idTour);

    public List<TourModelViewResponse> findAllTourDateWithTourItemByTourModelView(String idTour);

    public TourDateResponse editTourDate(String id, TourDateUpdateOnlyNameRequest request, String idTour);

    public void changeDateTourDay(String idTour, ChangeTourDayCrmRequest changeTourDayRequest);
}
