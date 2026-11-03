package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.maintenance.AnalyticsMaintenanceSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.usecase.analytics.AnalyticsMaintenanceUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Analytics Maintenance", description = "Analytics Maintenance Reports")
@RestController
@RequestMapping("/analytics/maintenance")
public class AnalyticsMaintenanceController {

    @Autowired
    private AnalyticsMaintenanceUseCase analyticsMaintenanceUseCase;

    @PostMapping("/search-filter-paginate-supplier")
    @Operation(summary = "Search filter paginate supplier report")
    public ApiResponse searchFilterPaginateSupplier(
            @RequestBody AnalyticsMaintenanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsMaintenanceUseCase.searchFilterPaginateSupplier(searchFilterRequest));
    }
}
