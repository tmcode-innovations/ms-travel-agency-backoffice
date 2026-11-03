package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.contact.*;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.ContactUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.ContactController.CONTACT;

@Slf4j
@Tag(name = "Maintenance - Contact", description = "Maintenance Module")
@RestController
@RequestMapping(CONTACT)
public class ContactController {

    public static final String CONTACT = "/contacts";

    public static final String CONTACT_ID = "contact_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + CONTACT_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + CONTACT_ID + "}";
    public static final String DELETE = "/delete/{" + CONTACT_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String SEARCH_BY_CONTACT_TYPE = "/search-by-contact-type";

    @Autowired
    private ContactUseCase contactUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create contact.",
            description = "Create contact."
    )
    public ApiResponse create(
            @RequestBody ContactCreateRequest request) {
        return ApiResponse.success(
                contactUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find contact by id.",
            description = "Find contact by id."
    )
    public ApiResponse findBy(
            @PathVariable(CONTACT_ID) String id) {
        return ApiResponse.success(
                contactUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all contacts.",
            description = "Find all contacts."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                contactUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit contact.",
            description = "Edit contact."
    )
    public ApiResponse edit(
            @PathVariable(CONTACT_ID) String id,
            @RequestBody ContactUpdateRequest request) {
        return ApiResponse.success(
                contactUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete contact.",
            description = "Delete contact."
    )
    public ApiResponse deleteById(
           @PathVariable(CONTACT_ID) String id) {
        contactUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate contacts.",
            description = "Search all contacts."
    )
    public ApiResponse searchPaginate(
            @RequestBody ContactSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                contactUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate contacts.",
            description = "Search filter paginate contacts."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody ContactSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                contactUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(SEARCH_BY_CONTACT_TYPE)
    @Operation(
            summary = "Search by contact type.",
            description = "Search by contact type."
    )
    public ApiResponse searchByContact(
            @RequestBody ContactSearchRequestByContactType contactSearchRequestByContactType
    ) {
        return ApiResponse.success(
                contactUseCase.searchAllByContactType(contactSearchRequestByContactType)
        );
    }

}
