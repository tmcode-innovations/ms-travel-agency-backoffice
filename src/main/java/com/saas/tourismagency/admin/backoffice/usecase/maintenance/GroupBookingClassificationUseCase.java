package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.groupbookingclassification.*;

import java.util.List;

public interface GroupBookingClassificationUseCase {

    public GroupBookingClassificationResponse create(GroupBookingClassificationCreateRequest request);

    public GroupBookingClassificationResponse edit(String id, GroupBookingClassificationUpdateRequest request);

    public void deleteById(String id);

    public List<GroupBookingClassificationResponse> findAll();

    public GroupBookingClassificationResponse findById(String id);

    public GroupBookingClassificationSearchResponse searchAll(GroupBookingClassificationSearchRequest bankSearchRequest);

    public GroupBookingClassificationSearchResponse searchFilterAll(GroupBookingClassificationSearchFilterRequest bankSearchFilterRequest);

}
