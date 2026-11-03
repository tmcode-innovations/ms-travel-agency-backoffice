package com.saas.tourismagency.admin.backoffice.usecase.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.notification.*;

import java.util.List;

public interface NotificationUseCase {

    public NotificationResponse create(NotificationCreateRequest request);

    public NotificationResponse edit(String id, NotificationUpdateRequest request);

    public void deleteById(String id);

    public List<NotificationResponse> findAll();

    public NotificationResponse findById(String id);

    public NotificationSearchResponse searchAll(NotificationSearchRequest notificationSearchRequest);

    public NotificationSearchResponse searchFilterAll(NotificationSearchFilterRequest notificationSearchFilterRequest);

    public NotificationResponse findByName(String name);

    public NotificationResponse findByIdentify(String identify);

    public NotificationSearchDataResponse searchAllNotificationsByUser(BaseSearchRequestById request);

    public NotificationSearchDataResponse searchAllNotificationsNewByUser(BaseSearchRequestById request);

    public void cleanNotifications(BaseSearchRequestById request);

    public NotificationResponse changeState(String id, NotificationChangeStateRequest request);
}
