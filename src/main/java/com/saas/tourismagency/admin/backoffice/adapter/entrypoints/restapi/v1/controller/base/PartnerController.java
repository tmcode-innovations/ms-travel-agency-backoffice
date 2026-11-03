package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.base.PartnerUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base.PartnerController.PARTNER;

@Slf4j
@Tag(name = "Base - Partner", description = "Base Module")
@RestController
@RequestMapping(PARTNER)
public class PartnerController {

    public static final String PARTNER = "/partners";

    public static final String PARTNER_ID = "partner_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + PARTNER_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + PARTNER_ID + "}";
    public static final String DELETE = "/delete/{" + PARTNER_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private PartnerUseCase partnerUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create partner.",
            description = "Create partner."
    )
    public ApiResponse create(
            @RequestBody PartnerCreateRequest request) {
        return ApiResponse.success(
                partnerUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find partner by id.",
            description = "Find partner by id."
    )
    public ApiResponse findBy(
            @PathVariable(PARTNER_ID) String id) {
        return ApiResponse.success(
                partnerUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all partners.",
            description = "Find all partners."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                partnerUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit partner.",
            description = "Edit partner."
    )
    public ApiResponse edit(
            @PathVariable(PARTNER_ID) String id,
            @RequestBody PartnerUpdateRequest request) {
        return ApiResponse.success(
                partnerUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete partner.",
            description = "Delete partner."
    )
    public ApiResponse deleteById(
           @PathVariable(PARTNER_ID) String id) {
        partnerUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate partners.",
            description = "Search all partners."
    )
    public ApiResponse searchPaginate(
            @RequestBody PartnerSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                partnerUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate partners.",
            description = "Search filter paginate partners."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody PartnerSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                partnerUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
