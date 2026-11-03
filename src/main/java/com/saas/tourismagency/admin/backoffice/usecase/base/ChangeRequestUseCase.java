package com.saas.tourismagency.admin.backoffice.usecase.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequest.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequestcomment.ChangeRequestCommentCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequestcomment.ChangeRequestCommentResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequestcomment.ChangeRequestCommentUpdateRequest;

import java.util.List;

public interface ChangeRequestUseCase {

    public ChangeRequestResponse create(ChangeRequestCreateRequest request);

    public ChangeRequestResponse createAndSent(ChangeRequestCreateRequest request);

    public ChangeRequestResponse edit(String id, ChangeRequestUpdateRequest request);

    public void deleteById(String id);

    public List<ChangeRequestResponse> findAll();

    public ChangeRequestResponse findById(String id);

    public ChangeRequestSearchResponse searchAll(ChangeRequestSearchRequest changeRequestSearchRequest);

    public ChangeRequestSearchResponse searchFilterAll(ChangeRequestSearchFilterRequest changeRequestSearchFilterRequest);

    public ChangeRequestResponse findByName(String name);

    public ChangeRequestResponse findByIdentify(String identify);

    public ChangeRequestResponse changeState(String id, ChangeRequestChangeStateRequest request);

    // Change Request Comment

    public List<ChangeRequestCommentResponse> findAllChangeRequestCommentByChangeRequest(String idChangeRequest);

    public ChangeRequestCommentResponse addChangeRequestComment(ChangeRequestCommentCreateRequest request, String idQuotation);

    public ChangeRequestCommentResponse editChangeRequestComment(String id, ChangeRequestCommentUpdateRequest request, String idChangeRequeest);

    public void deleteChangeRequestComment(String id, String idChangeRequest);

    public ChangeRequestCommentResponse findByChangeRequestCommentAndChangeRequest(String id, String idChangeRequest);

    public List<ChangeRequestResponse> findAllByRegisterGroup(String idRegisterGroup);

    public List<ChangeRequestResponse> findAllByQuotation(String idQuotation);

    public ChangeRequestResponse findChangeRequestActiveByQuotationAndRegisterGroup(String idQuotation, String idRegisterGroup);

}
