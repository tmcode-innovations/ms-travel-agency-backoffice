package com.saas.tourismagency.admin.backoffice.usecase.operation;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingrestaurant.RegisterBookingRestaurantResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.requestorderservice.*;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;

import java.util.List;

public interface RequestOrderServiceUseCase {

    public RequestOrderServiceResponse create(RequestOrderServiceCreateRequest request);

    public RequestOrderServiceResponse edit(String id, RequestOrderServiceUpdateRequest request);

    public void deleteById(String id);

    public List<RequestOrderServiceResponse> findAll();

    public RequestOrderServiceResponse findById(String id);

    public RequestOrderServiceDomain findByIdDomain(String id);

    public RequestOrderServiceSearchResponse searchAll(RequestOrderServiceSearchRequest searchRequest);

    public RequestOrderServiceSearchResponse searchFilterAll(RequestOrderServiceSearchFilterRequest searchFilterRequest);

    public RequestOrderServiceResponse changeState(String id, RequestOrderServiceChangeStateRequest request);

    public RequestOrderServiceResponse generate(String idRegisterGroup, String idRegisterBookingOperator, String idSupplierOperator);

    public void reSendRequestOrderService(String id);
}
