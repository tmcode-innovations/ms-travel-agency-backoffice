package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.collection.CollectionCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.collection.CollectionSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.collection.CollectionSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.collection.CollectionUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.CollectionUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.CollectionController.COLLECTION;

@Slf4j
@Tag(name = "Finance - Collection (Cobranza)", description = "Finance Module")
@RestController
@RequestMapping(COLLECTION)
public class CollectionController {

    public static final String COLLECTION = "/collections";

    public static final String COLLECTION_ID = "collection_id";
    public static final String DETAIL_ID = "detail_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + COLLECTION_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + COLLECTION_ID + "}";
    public static final String DELETE = "/delete/{" + COLLECTION_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String ADD_DETAIL = "/{" + COLLECTION_ID + "}/detail/add";
    public static final String EDIT_DETAIL = "/{" + COLLECTION_ID + "}/detail/edit/{" + DETAIL_ID + "}";
    public static final String DELETE_DETAIL = "/{" + COLLECTION_ID + "}/detail/delete/{" + DETAIL_ID + "}";
    public static final String FIND_DETAIL = "/{" + COLLECTION_ID + "}/detail/find/{" + DETAIL_ID + "}";
    public static final String FIND_ALL_DETAIL = "/{" + COLLECTION_ID + "}/detail/find-all";

    public static final String FIND_DATA_UPDATED = "/{" + COLLECTION_ID + "}/find-data-updated";

    public static final String EXPORT_DOCUMENT = "/{" + COLLECTION_ID + "}/export-collection/pdf";

    @Autowired
    private CollectionUseCase collectionUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create collection.",
            description = "Create collection."
    )
    public ApiResponse create(
            @RequestBody CollectionCreateRequest request) {
        return ApiResponse.success(
                collectionUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find collection by id.",
            description = "Find collection by id."
    )
    public ApiResponse findBy(
            @PathVariable(COLLECTION_ID) String id) {
        return ApiResponse.success(
                collectionUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all collections.",
            description = "Find all collections."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                collectionUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit collection.",
            description = "Edit collection."
    )
    public ApiResponse edit(
            @PathVariable(COLLECTION_ID) String id,
            @RequestBody CollectionUpdateRequest request) {
        return ApiResponse.success(
                collectionUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete collection.",
            description = "Delete collection."
    )
    public ApiResponse deleteById(
           @PathVariable(COLLECTION_ID) String id) {
        collectionUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate collections.",
            description = "Search all collections."
    )
    public ApiResponse searchPaginate(
            @RequestBody CollectionSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                collectionUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate collections.",
            description = "Search filter paginate collections."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody CollectionSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                collectionUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(ADD_DETAIL)
    @Operation(
            summary = "add detail.",
            description = "Add detail."
    )
    public ApiResponse addDetail(
            @PathVariable(COLLECTION_ID) String idCollection,
            @RequestBody DocumentFinanceDetailCreateRequest request) {
        return ApiResponse.success(
                collectionUseCase.addDocumentFinanceDetail(request, idCollection)
        );
    }

    @PutMapping(EDIT_DETAIL)
    @Operation(
            summary = "Edit detail .",
            description = "Edit detail."
    )
    public ApiResponse editDetail(
            @PathVariable(COLLECTION_ID) String idCollection,
            @PathVariable(DETAIL_ID) String idDetail,
            @RequestBody DocumentFinanceDetailUpdateRequest request) {
        return ApiResponse.success(
                collectionUseCase.editDocumentFinanceDetail(idDetail, request, idCollection)
        );
    }

    @DeleteMapping(DELETE_DETAIL)
    @Operation(
            summary = "Delete detail.",
            description = "Delete detail."
    )
    public ApiResponse deleteDetail(
            @PathVariable(COLLECTION_ID) String idCollection,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        collectionUseCase.deleteDocumentFinanceDetail(idDetail, idCollection);
        return ApiResponse.success();
    }

    @GetMapping(FIND_DETAIL)
    @Operation(
            summary = "Find detail by id and collection.",
            description = "Find detail by id and collection."
    )
    public ApiResponse findDetailByIdAndCollection(
            @PathVariable(COLLECTION_ID) String idCollection,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        return ApiResponse.success(
                collectionUseCase.findByIdAndDocumentFinance(idDetail, idCollection)
        );
    }

    @GetMapping(FIND_ALL_DETAIL)
    @Operation(
            summary = "Find all detail by collection.",
            description = "Find all detail by collection."
    )
    public ApiResponse findAllDetailByCollection(
            @PathVariable(COLLECTION_ID) String idCollection
    ) {
        return ApiResponse.success(
                collectionUseCase.findAllDocumentFinanceDetailByDocumentFinance(idCollection)
        );
    }

    @GetMapping(FIND_DATA_UPDATED)
    @Operation(
            summary = "Find data updated by collection.",
            description = "Find data updated by collection."
    )
    public ApiResponse findDataUpdated(
            @PathVariable(COLLECTION_ID) String idCollection) {
        return ApiResponse.success(
                collectionUseCase.getCollectionModificationData(idCollection)
        );
    }

    @GetMapping(EXPORT_DOCUMENT)
    @Operation(
            summary = "Export collection.",
            description = "Export collection."
    )
    public ResponseEntity<Resource> exportCollectionPdf(
            @PathVariable(COLLECTION_ID) String idCollection
    ) {
        byte[] reportContent = collectionUseCase.exportCollectionPdf(idCollection);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("collection_report.pdf").build().toString())
                .body(resource);
    }

}
