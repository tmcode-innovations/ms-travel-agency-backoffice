package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequest.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequestcomment.ChangeRequestCommentCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequestcomment.ChangeRequestCommentUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.base.ChangeRequestUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base.ChangeRequestController.CHANGE_REQUEST;

@Slf4j
@Tag(name = "Base - Change Request", description = "Base Module")
@RestController
@RequestMapping(CHANGE_REQUEST)
public class ChangeRequestController {

    public static final String CHANGE_REQUEST = "/change-requests";

    public static final String CHANGE_REQUEST_ID = "change_request_id";
    public static final String CHANGE_REQUEST_COMMENT_ID = "change_request_comment_id";
    public static final String REGISTER_GROUP_ID = "register_group_id";
    public static final String QUOTATION_ID = "quotation_id";

    public static final String CREATE = "/create";
    public static final String CREATE_AND_SENT = "/create-sent";
    public static final String FIND_BY = "/find-by/{" + CHANGE_REQUEST_ID + "}";
    public static final String EDIT = "/edit/{" + CHANGE_REQUEST_ID + "}";
    public static final String DELETE = "/delete/{" + CHANGE_REQUEST_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String CHANGE_STATE = "/change-state/{" + CHANGE_REQUEST_ID + "}";
    public static final String CHANGE_REQUEST_ACTIVE = "/change-request-active/{" + QUOTATION_ID + "}/{" + REGISTER_GROUP_ID + "}";

    public static final String ADD_CHANGE_REQUEST_COMMENT = "/{" + CHANGE_REQUEST_ID + "}/change-request/add";
    public static final String EDIT_CHANGE_REQUEST_COMMENT = "/{" + CHANGE_REQUEST_ID + "}/change-request/edit/{" + CHANGE_REQUEST_COMMENT_ID + "}";
    public static final String DELETE_CHANGE_REQUEST_COMMENT = "/{" + CHANGE_REQUEST_ID + "}/change-request/delete/{" + CHANGE_REQUEST_COMMENT_ID + "}";
    public static final String FIND_CHANGE_REQUEST_COMMENT = "/{" + CHANGE_REQUEST_ID + "}/change-request/find/{" + CHANGE_REQUEST_COMMENT_ID + "}";
    public static final String FIND_ALL_CHANGE_REQUEST_COMMENT = "/{" + CHANGE_REQUEST_ID + "}/change-request/find-all";

    public static final String FIND_ALL_CHANGE_REQUEST_BY_REGISTER_GROUP = "/register-group/{" + REGISTER_GROUP_ID + "}";
    public static final String FIND_ALL_CHANGE_REQUEST_BY_QUOTATION = "/quotation/{" + QUOTATION_ID + "}";

    @Autowired
    private ChangeRequestUseCase changeRequestUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create change request.",
            description = "Create change request."
    )
    public ApiResponse create(
            @RequestBody ChangeRequestCreateRequest request) {
        return ApiResponse.success(
                changeRequestUseCase.create(request)
        );
    }

    @PostMapping(CREATE_AND_SENT)
    @Operation(
            summary = "Create and sent change request.",
            description = "Create and sent change request."
    )
    public ApiResponse createAndSent(
            @RequestBody ChangeRequestCreateRequest request) {
        return ApiResponse.success(
                changeRequestUseCase.createAndSent(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find change request by id.",
            description = "Find change request by id."
    )
    public ApiResponse findBy(
            @PathVariable(CHANGE_REQUEST_ID) String id) {
        return ApiResponse.success(
                changeRequestUseCase.findById(id)
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit change request.",
            description = "Edit change request."
    )
    public ApiResponse edit(
            @PathVariable(CHANGE_REQUEST_ID) String id,
            @RequestBody ChangeRequestUpdateRequest request) {
        return ApiResponse.success(
                changeRequestUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete change request.",
            description = "Delete change request."
    )
    public ApiResponse deleteById(
            @PathVariable(CHANGE_REQUEST_ID) String id) {
        changeRequestUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate change requests.",
            description = "Search all change requests."
    )
    public ApiResponse searchPaginate(
            @RequestBody ChangeRequestSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                changeRequestUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate change requets.",
            description = "Search filter paginate change requets."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody ChangeRequestSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                changeRequestUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PutMapping(CHANGE_STATE)
    @Operation(
            summary = "Change state change request.",
            description = "Change state change request."
    )
    public ApiResponse changeChangeRequest(
            @PathVariable(CHANGE_REQUEST_ID) String id,
            @RequestBody ChangeRequestChangeStateRequest request) {
        return ApiResponse.success(
                changeRequestUseCase.changeState(id, request)
        );
    }

    @GetMapping(CHANGE_REQUEST_ACTIVE)
    @Operation(
            summary = "Change request active.",
            description = "Change request active."
    )
    public ApiResponse findRequestActive(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable (REGISTER_GROUP_ID) String idRegisterGroup) {
        return ApiResponse.success(
                changeRequestUseCase.findChangeRequestActiveByQuotationAndRegisterGroup(idQuotation, idRegisterGroup)
        );
    }

    @PostMapping(ADD_CHANGE_REQUEST_COMMENT)
    @Operation(
            summary = "add change request comment.",
            description = "Add change request comment."
    )
    public ApiResponse addChangeRequestComment(
            @PathVariable(CHANGE_REQUEST_ID) String idChangeRequest,
            @RequestBody ChangeRequestCommentCreateRequest request) {
        return ApiResponse.success(
                changeRequestUseCase.addChangeRequestComment(request, idChangeRequest)
        );
    }

    @PutMapping(EDIT_CHANGE_REQUEST_COMMENT)
    @Operation(
            summary = "Edit change request comment.",
            description = "Edit change request comment."
    )
    public ApiResponse editChangeRequestComment(
            @PathVariable(CHANGE_REQUEST_ID) String idChangeRequest,
            @PathVariable(CHANGE_REQUEST_COMMENT_ID) String idChangeRequestComment,
            @RequestBody ChangeRequestCommentUpdateRequest request) {
        return ApiResponse.success(
                changeRequestUseCase.editChangeRequestComment(idChangeRequestComment, request, idChangeRequest)
        );
    }

    @DeleteMapping(DELETE_CHANGE_REQUEST_COMMENT)
    @Operation(
            summary = "Delete change request comment.",
            description = "Delete change request comment."
    )
    public ApiResponse deleteCostTour(
            @PathVariable(CHANGE_REQUEST_ID) String idChangeRequest,
            @PathVariable(CHANGE_REQUEST_COMMENT_ID) String idChangeRequestComment
    ) {
        changeRequestUseCase.deleteChangeRequestComment(idChangeRequestComment, idChangeRequest);
        return ApiResponse.success();
    }

    @GetMapping(FIND_CHANGE_REQUEST_COMMENT)
    @Operation(
            summary = "Find change requet comment by id and change request.",
            description = "Find change requet comment by id and change request."
    )
    public ApiResponse findChangeRequestCommentByIdAndChangeRequest(
            @PathVariable(CHANGE_REQUEST_ID) String idChangeRequest,
            @PathVariable(CHANGE_REQUEST_COMMENT_ID) String idChangeRequestComment
    ) {
        return ApiResponse.success(
                changeRequestUseCase.findByChangeRequestCommentAndChangeRequest(idChangeRequestComment, idChangeRequest)
        );
    }

    @GetMapping(FIND_ALL_CHANGE_REQUEST_COMMENT)
    @Operation(
            summary = "Find all change requet comment by change requet.",
            description = "Find all change requet comment by change requet."
    )
    public ApiResponse findAllChangeRequestCommentByChangeRequest(
            @PathVariable(CHANGE_REQUEST_ID) String idChangeRequest
    ) {
        return ApiResponse.success(
                changeRequestUseCase.findAllChangeRequestCommentByChangeRequest(idChangeRequest)
        );
    }

    @GetMapping(FIND_ALL_CHANGE_REQUEST_BY_REGISTER_GROUP)
    @Operation(
            summary = "Find all change requet by register group.",
            description = "Find all change requet by register group."
    )
    public ApiResponse findAllChangeRequestByRegisterGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup
    ) {
        return ApiResponse.success(
                changeRequestUseCase.findAllByRegisterGroup(idRegisterGroup)
        );
    }

    @GetMapping(FIND_ALL_CHANGE_REQUEST_BY_QUOTATION)
    @Operation(
            summary = "Find all change requet by quotation.",
            description = "Find all change requet by quotation."
    )
    public ApiResponse findAllChangeRequestByQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation
    ) {
        return ApiResponse.success(
                changeRequestUseCase.findAllByQuotation(idQuotation)
        );
    }
}
