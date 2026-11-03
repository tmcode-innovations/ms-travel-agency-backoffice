package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.usecase.maintenance.MaintenanceNomenclatureUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.MaintenanceNomenclatureController.MAINTENANCE_NOMENCLATURE;

@Slf4j
@Tag(name = "Maintenance - Nomenclature", description = "Maintenance Module")
@RestController
@RequestMapping(MAINTENANCE_NOMENCLATURE)
public class MaintenanceNomenclatureController {

    public static final String MAINTENANCE_NOMENCLATURE = "/maintenance-nomenclatures";

    public static final String SEARCH_AFFECT_IGV = "/search-affect-igvs";
    public static final String SEARCH_BOOKING_CLASSIFICATION = "/search-booking-classifications";
    public static final String SEARCH_CONTACT_TYPE = "/search-contact-types";
    public static final String SEARCH_CUSTOMER_TYPE = "/search-customer-types";
    public static final String SEARCH_GUIDE_TYPE = "/search-guide-types";
    public static final String SEARCH_LODGING_TYPE = "/search-lodging-types";
    public static final String SEARCH_SERVICE_GROUP = "/search-service-groups";
    public static final String SEARCH_SERVICE_TYPE = "/search-service-types";
    public static final String SEARCH_SUPPLIER_TYPE_OPERATOR = "/search-supplier_type_operators";
    public static final String SEARCH_TYPE_TOUR_CONDUCT = "/search-type-tour-conducts";

    @Autowired
    private MaintenanceNomenclatureUseCase maintenanceNomenclatureUseCase;

    @PostMapping(SEARCH_AFFECT_IGV)
    @Operation(
            summary = "Search affect igvs.",
            description = "Search all affect igvs."
    )
    public ApiResponse searchAllAffectIgv() {
        return ApiResponse.success(
                maintenanceNomenclatureUseCase.searchAllAffectIgv()
        );
    }

    @PostMapping(SEARCH_BOOKING_CLASSIFICATION)
    @Operation(
            summary = "Search booking classifications.",
            description = "Search all booking classifications."
    )
    public ApiResponse searchAllBookingClassification() {
        return ApiResponse.success(
                maintenanceNomenclatureUseCase.searchAllBookingClassification()
        );
    }

    @PostMapping(SEARCH_CONTACT_TYPE)
    @Operation(
            summary = "Search contact types.",
            description = "Search all contact types."
    )
    public ApiResponse searchAllContactType() {
        return ApiResponse.success(
                maintenanceNomenclatureUseCase.searchAllContactType()
        );
    }

    @PostMapping(SEARCH_CUSTOMER_TYPE)
    @Operation(
            summary = "Search customer types.",
            description = "Search all customer types."
    )
    public ApiResponse searchAllCustomerType() {
        return ApiResponse.success(
                maintenanceNomenclatureUseCase.searchAllCustomerType()
        );
    }

    @PostMapping(SEARCH_GUIDE_TYPE)
    @Operation(
            summary = "Search guide types.",
            description = "Search all guide types."
    )
    public ApiResponse searchAllGuideType() {
        return ApiResponse.success(
                maintenanceNomenclatureUseCase.searchAllGuideType()
        );
    }

    @PostMapping(SEARCH_LODGING_TYPE)
    @Operation(
            summary = "Search lodging types.",
            description = "Search all lodging types."
    )
    public ApiResponse searchAllLodgingType() {
        return ApiResponse.success(
                maintenanceNomenclatureUseCase.searchAllLodgingType()
        );
    }

    @PostMapping(SEARCH_SERVICE_GROUP)
    @Operation(
            summary = "Search service groups.",
            description = "Search all service groups."
    )
    public ApiResponse searchAllServiceGroup() {
        return ApiResponse.success(
                maintenanceNomenclatureUseCase.searchAllServiceGroup()
        );
    }

    @PostMapping(SEARCH_SERVICE_TYPE)
    @Operation(
            summary = "Search service types.",
            description = "Search all service types."
    )
    public ApiResponse searchAllServiceType() {
        return ApiResponse.success(
                maintenanceNomenclatureUseCase.searchAllServiceType()
        );
    }

    @PostMapping(SEARCH_SUPPLIER_TYPE_OPERATOR)
    @Operation(
            summary = "Search supplier type operators.",
            description = "Search all supplier type operators."
    )
    public ApiResponse searchAllSupplierTypeOperator() {
        return ApiResponse.success(
                maintenanceNomenclatureUseCase.searchAllSupplierTypeOperator()
        );
    }

    @PostMapping(SEARCH_TYPE_TOUR_CONDUCT)
    @Operation(
            summary = "Search type tour conducts.",
            description = "Search all type tour conducts."
    )
    public ApiResponse searchAllTypeTourConduct() {
        return ApiResponse.success(
                maintenanceNomenclatureUseCase.searchAllTypeTourConduct()
        );
    }

}
