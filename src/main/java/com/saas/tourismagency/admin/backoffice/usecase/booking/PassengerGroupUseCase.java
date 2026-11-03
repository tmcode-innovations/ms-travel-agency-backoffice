package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;

import java.util.List;

public interface PassengerGroupUseCase {

    public PassengerGroupResponse create(PassengerGroupCreateRequest request);

    public PassengerGroupResponse create(PassengerGroupCreateRequest request, RegisterGroupDomain registerGroupDomain);

    public PassengerGroupResponse edit(String id, PassengerGroupUpdateRequest request);

    public PassengerGroupResponse edit(String id, PassengerGroupUpdateRequest request, RegisterGroupDomain registerGroupDomain);

    public void deleteById(String id);

    public List<PassengerGroupResponse> findAll();

    public PassengerGroupResponse findById(String id);

    public PassengerGroupSearchResponse searchAll(PassengerGroupSearchRequest passengerGroupSearchRequest);

    public PassengerGroupSearchResponse searchFilterAll(PassengerGroupSearchFilterRequest passengerGroupSearchFilterRequest);

    public List<PassengerGroupResponse> searchAllByRegisterGroup(String idRegisterGroup);

    public PassengerGroupResponse searchByRegisterGroupAndPassengerGroup(String idRegisterGroup, String idPassengerGroup);

    public List<PassengerGroupDomain> searchAllByRegisterGroupDomain(String idRegisterGroup);
}
