package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.restauranttype.*;

import java.util.List;

public interface RestaurantTypeUseCase {

    public RestaurantTypeResponse create(RestaurantTypeCreateRequest request);

    public RestaurantTypeResponse edit(String id, RestaurantTypeUpdateRequest request);

    public void deleteById(String id);

    public List<RestaurantTypeResponse> findAll();

    public RestaurantTypeResponse findById(String id);

    public RestaurantTypeSearchResponse searchAll(RestaurantTypeSearchRequest restaurantTypeSearchRequest);

    public RestaurantTypeSearchResponse searchFilterAll(RestaurantTypeSearchFilterRequest restaurantTypeSearchFilterRequest);

    public RestaurantTypeResponse findByName(String name);

    public RestaurantTypeResponse findByIdentify(String identify);
}
