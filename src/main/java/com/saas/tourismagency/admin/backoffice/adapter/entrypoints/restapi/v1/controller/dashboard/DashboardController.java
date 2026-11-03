package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.dashboard;

import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.dashboard.DashboardController.DASHBOARD;

@Slf4j
@Tag(name = "Dashboard", description = "Dashboard")
@RestController
@RequestMapping(DASHBOARD)
public class DashboardController {

    public static final String DASHBOARD = "/dashboards";

    public static final String GET_DATA_MAINTENANCE = "/get-data-maintenance";

    @GetMapping(GET_DATA_MAINTENANCE)
    @Operation(
            summary = "Get data maintenance.",
            description = "Get data maintenance."
    )
    public ApiResponse getDataMaintenance() {
        return ApiResponse.success(List.of());
    }

}
