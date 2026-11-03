package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.statistics;

import com.saas.tourismagency.admin.backoffice.usecase.statistics.StatisticsUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.statistics.StatisticsController.STATISTICS;

@Slf4j
@Tag(name = "Statistics", description = "Statistics Module")
@RestController
@RequestMapping(STATISTICS)
public class StatisticsController {

    public static final String STATISTICS = "/statistics";

    public static final String COUNT_QUOTATION = "/count-quotation";
    public static final String COUNT_REGISTER_GROUP = "/count-register-group";
    public static final String COUNT_PROGRAM = "/count-program";
    public static final String COUNT_SERVICE = "/count-service";
    public static final String COUNT_SUPPLIER = "/count-supplier";
    public static final String COUNT_CUSTOMER = "/count-customer";
    public static final String COUNT_TOUR = "/count-tour";
    public static final String GROUP_SUPPLIER_BY_SUPPLIER_TYPE = "/group-supplier-by-supplier-type";

    @Autowired
    private StatisticsUseCase statisticsUseCase;

    @GetMapping(COUNT_QUOTATION)
    @Operation(
            summary = "Count quotation.",
            description = "Count quotation."
    )
    public ApiResponse countQuotation() {
        return ApiResponse.success(
                statisticsUseCase.countQuotation()
        );
    }

    @GetMapping(COUNT_REGISTER_GROUP)
    @Operation(
            summary = "Count register group.",
            description = "Count register group."
    )
    public ApiResponse countRegisterGroup() {
        return ApiResponse.success(
                statisticsUseCase.countRegisterGroup()
        );
    }

    @GetMapping(COUNT_PROGRAM)
    @Operation(
            summary = "Count program.",
            description = "Count program."
    )
    public ApiResponse countProgram() {
        return ApiResponse.success(
                statisticsUseCase.countProgram()
        );
    }

    @GetMapping(COUNT_SERVICE)
    @Operation(
            summary = "Count service.",
            description = "Count service."
    )
    public ApiResponse countService() {
        return ApiResponse.success(
                statisticsUseCase.countService()
        );
    }

    @GetMapping(COUNT_CUSTOMER)
    @Operation(
            summary = "Count customer.",
            description = "Count customer."
    )
    public ApiResponse countCustomer() {
        return ApiResponse.success(
                statisticsUseCase.countCustomer()
        );
    }

    @GetMapping(COUNT_SUPPLIER)
    @Operation(
            summary = "Count supplier.",
            description = "Count supplier."
    )
    public ApiResponse countSupplier() {
        return ApiResponse.success(
                statisticsUseCase.countSupplier()
        );
    }

    @GetMapping(COUNT_TOUR)
    @Operation(
            summary = "Count tour.",
            description = "Count tour."
    )
    public ApiResponse countTour() {
        return ApiResponse.success(
                statisticsUseCase.countTour()
        );
    }

    @GetMapping(GROUP_SUPPLIER_BY_SUPPLIER_TYPE)
    @Operation(
            summary = "Supplier by supplier type.",
            description = "Supplier by supplier type."
    )
    public ApiResponse groupSupplierBySupplierType() {
        return ApiResponse.success(
                statisticsUseCase.supplierByType()
        );
    }
}
