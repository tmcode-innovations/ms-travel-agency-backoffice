package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base;

import com.saas.tourismagency.admin.backoffice.usecase.base.BaseNomenclatureUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base.BaseNomenclatureController.BASE_NOMENCLATURE;

@Slf4j
@Tag(name = "Base - Nomenclature", description = "Base Module")
@RestController
@RequestMapping(BASE_NOMENCLATURE)
public class BaseNomenclatureController {

    public static final String BASE_NOMENCLATURE = "/base-nomenclatures";

    public static final String CHANGE_REQUEST_TYPE = "change_request_type";

    public static final String SEARCH_PARTNER_CATEGORY = "/search-partner-categories";
    public static final String SEARCH_TITLE_PERSON = "/search-title-people";
    public static final String SEARCH_GENDER = "/search-genders";
    public static final String SEARCH_CHANGE_REQUEST_STATUS = "/search-change-request-status";
    public static final String SEARCH_CHANGE_REQUEST_TYPE = "/search-change-request-type";
    public static final String SEARCH_CHANGE_REQUEST_CATEGORY = "/search-change-request-category/{" + CHANGE_REQUEST_TYPE + "}";

    @Autowired
    private BaseNomenclatureUseCase baseNomenclatureUseCase;

    @PostMapping(SEARCH_PARTNER_CATEGORY)
    @Operation(
            summary = "Search partner categories.",
            description = "Search all partner categories."
    )
    public ApiResponse searchAllPartnerCategory() {
        return ApiResponse.success(
                baseNomenclatureUseCase.searchAllPartnerCategory()
        );
    }

    @PostMapping(SEARCH_TITLE_PERSON)
    @Operation(
            summary = "Search title people.",
            description = "Search all title people."
    )
    public ApiResponse searchAllTitlePerson() {
        return ApiResponse.success(
                baseNomenclatureUseCase.searchAllTitlePerson()
        );
    }

    @PostMapping(SEARCH_GENDER)
    @Operation(
            summary = "Search gender.",
            description = "Search all gender."
    )
    public ApiResponse searchAllGender() {
        return ApiResponse.success(
                baseNomenclatureUseCase.searchAllGender()
        );
    }

    @PostMapping(SEARCH_CHANGE_REQUEST_TYPE)
    @Operation(
            summary = "Search change request type.",
            description = "Search all change request type."
    )
    public ApiResponse searchAllChangeRequestType() {
        return ApiResponse.success(
                baseNomenclatureUseCase.searchAllChangeRequestType()
        );
    }

    @PostMapping(SEARCH_CHANGE_REQUEST_STATUS)
    @Operation(
            summary = "Search change request status.",
            description = "Search all change request status."
    )
    public ApiResponse searchAllChangeRequestStatus() {
        return ApiResponse.success(
                baseNomenclatureUseCase.searchAllChangeRequestStatus()
        );
    }

    @PostMapping(SEARCH_CHANGE_REQUEST_CATEGORY)
    @Operation(
            summary = "Search change request type.",
            description = "Search all change request type."
    )
    public ApiResponse searchAllChangeRequestType(
            @PathVariable(CHANGE_REQUEST_TYPE) String changeRequestType
    ) {
        return ApiResponse.success(
                baseNomenclatureUseCase.searchAllChangeRequestCategory(changeRequestType)
        );
    }

}
