package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.accessvoucheregress.AccessVoucherEgressCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.accessvoucheregress.AccessVoucherEgressSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.accessvoucheregress.AccessVoucherEgressSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.accessvoucheregress.AccessVoucherEgressUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.AccessVoucherEgressUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.AccessVoucherEgressController.ACCESS_VOUCHER_EGRESS;

@Slf4j
@Tag(name = "Maintenance - Access Voucher Egress", description = "Maintenance Module")
@RestController
@RequestMapping(ACCESS_VOUCHER_EGRESS)
public class AccessVoucherEgressController {

    public static final String ACCESS_VOUCHER_EGRESS = "/access-voucher-egress";

    public static final String ACCESS_VOUCHER_EGRESS_ID = "access_voucher_egress_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + ACCESS_VOUCHER_EGRESS_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + ACCESS_VOUCHER_EGRESS_ID + "}";
    public static final String DELETE = "/delete/{" + ACCESS_VOUCHER_EGRESS_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String  USER_WITH_ACCESS_VOUCHER_EGRESS = "user-with-access-voucher-egress";

    @Autowired
    private AccessVoucherEgressUseCase accessVoucherEgressUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create access voucher egress.",
            description = "Create access voucher egress."
    )
    public ApiResponse create(
            @RequestBody AccessVoucherEgressCreateRequest request) {
        return ApiResponse.success(
                accessVoucherEgressUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find access voucher egress by id.",
            description = "Find access voucher egress by id."
    )
    public ApiResponse findBy(
            @PathVariable(ACCESS_VOUCHER_EGRESS_ID) String id) {
        return ApiResponse.success(
                accessVoucherEgressUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all access voucher egress.",
            description = "Find all access voucher egress."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                accessVoucherEgressUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit access voucher egress.",
            description = "Edit access voucher egress."
    )
    public ApiResponse edit(
            @PathVariable(ACCESS_VOUCHER_EGRESS_ID) String id,
            @RequestBody AccessVoucherEgressUpdateRequest request) {
        return ApiResponse.success(
                accessVoucherEgressUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete access voucher egress.",
            description = "Delete access voucher egress."
    )
    public ApiResponse deleteById(
           @PathVariable(ACCESS_VOUCHER_EGRESS_ID) String id) {
        accessVoucherEgressUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate access voucher egress.",
            description = "Search all access voucher egress."
    )
    public ApiResponse searchPaginate(
            @RequestBody AccessVoucherEgressSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                accessVoucherEgressUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate access voucher egress.",
            description = "Search filter paginate access voucher egress."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody AccessVoucherEgressSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                accessVoucherEgressUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(USER_WITH_ACCESS_VOUCHER_EGRESS)
    @Operation(
            summary = "Find notifications by user id.",
            description = "Find notifications by user id."
    )
    public ApiResponse findNotificationsByUser(
            @RequestBody BaseSearchRequestById request) {
        return ApiResponse.success(
                accessVoucherEgressUseCase.userWithAccessVoucherEgress(request)
        );
    }

}
