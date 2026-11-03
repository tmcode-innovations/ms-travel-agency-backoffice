package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.notification.NotificationChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.notification.NotificationSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.notification.NotificationSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.notification.NotificationUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.base.NotificationUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base.NotificationController.NOTIFICATION;

@Slf4j
@Tag(name = "Base - Notification", description = "Base Module")
@RestController
@RequestMapping(NOTIFICATION)
public class NotificationController {

    public static final String NOTIFICATION = "/notifications";

    public static final String NOTIFICATION_ID = "notification_id";

    public static final String FIND_BY = "/find-by/{" + NOTIFICATION_ID + "}";
    public static final String EDIT = "/edit/{" + NOTIFICATION_ID + "}";
    public static final String DELETE = "/delete/{" + NOTIFICATION_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String SEARCH_ALL_NOTIFICATIONS = "/search-all-notifications";
    public static final String SEARCH_ALL_NEW_NOTIFICATIONS = "/search-all-new-notifications";

    public static final String CLEAN_NOTIFICATIONS = "/clean-notifications";

    public static final String CHANGE_STATE = "/change-state/{" + NOTIFICATION_ID + "}";

    @Autowired
    private NotificationUseCase notificationUseCase;

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find notification by id.",
            description = "Find notification by id."
    )
    public ApiResponse findBy(
            @PathVariable(NOTIFICATION_ID) String id) {
        return ApiResponse.success(
                notificationUseCase.findById(id)
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit notification.",
            description = "Edit notification."
    )
    public ApiResponse edit(
            @PathVariable(NOTIFICATION_ID) String id,
            @RequestBody NotificationUpdateRequest request) {
        return ApiResponse.success(
                notificationUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete notification.",
            description = "Delete notification."
    )
    public ApiResponse deleteById(
            @PathVariable(NOTIFICATION_ID) String id) {
        notificationUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate notifications.",
            description = "Search all notifications."
    )
    public ApiResponse searchPaginate(
            @RequestBody NotificationSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                notificationUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate notifications.",
            description = "Search filter paginate notifications."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody NotificationSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                notificationUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(SEARCH_ALL_NOTIFICATIONS)
    @Operation(
            summary = "Find notifications by user id.",
            description = "Find notifications by user id."
    )
    public ApiResponse findNotificationsByUser(
            @RequestBody BaseSearchRequestById request) {
        return ApiResponse.success(
                notificationUseCase.searchAllNotificationsByUser(request)
        );
    }

    @PostMapping(SEARCH_ALL_NEW_NOTIFICATIONS)
    @Operation(
            summary = "Find new notifications by user id.",
            description = "Find new notifications by user id."
    )
    public ApiResponse findNewNotificationsByUser(
            @RequestBody BaseSearchRequestById request) {
        return ApiResponse.success(
                notificationUseCase.searchAllNotificationsNewByUser(request)
        );
    }

    @PostMapping(CLEAN_NOTIFICATIONS)
    @Operation(
            summary = "Clean notifications.",
            description = "Clean notificatiosn."
    )
    public ApiResponse cleanNotifications(
            @RequestBody BaseSearchRequestById request) {
        notificationUseCase.cleanNotifications(request);
        return ApiResponse.success();
    }

    @PutMapping(CHANGE_STATE)
    @Operation(
            summary = "Change state notification.",
            description = "Change state notification."
    )
    public ApiResponse changeNotification(
            @PathVariable(NOTIFICATION_ID) String id,
            @RequestBody NotificationChangeStateRequest request) {
        return ApiResponse.success(
                notificationUseCase.changeState(id, request)
        );
    }
}
