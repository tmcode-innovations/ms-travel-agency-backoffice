package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSearchDataResponse {

    @JsonProperty("notifications")
    private List<NotificationResponse> notifications;

    @JsonProperty("element")
    private String element;

    public static NotificationSearchDataResponse from(List<NotificationResponse> notifications, String element) {
        NotificationSearchDataResponse notificationSearchDataResponse = new NotificationSearchDataResponse();
        notificationSearchDataResponse.setNotifications(notifications);
        notificationSearchDataResponse.setElement(element);
        return notificationSearchDataResponse;
    }
}
