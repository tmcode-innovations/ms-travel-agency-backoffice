package com.saas.tourismagency.admin.backoffice.usecase.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.maintenance.AnalyticsMaintenanceSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.maintenance.AnalyticsMaintenanceSearchResponse;

public interface AnalyticsMaintenanceUseCase {

    AnalyticsMaintenanceSearchResponse<?> searchFilterPaginateSupplier(AnalyticsMaintenanceSearchFilterRequest request);

}
