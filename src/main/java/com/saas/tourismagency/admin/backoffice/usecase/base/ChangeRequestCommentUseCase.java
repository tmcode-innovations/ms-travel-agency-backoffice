package com.saas.tourismagency.admin.backoffice.usecase.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequestcomment.*;
import com.saas.tourismagency.admin.backoffice.domain.base.ChangeRequestDomain;

import java.util.List;

public interface ChangeRequestCommentUseCase {

    public ChangeRequestCommentResponse create(ChangeRequestCommentCreateRequest request);

    public ChangeRequestCommentResponse edit(String id, ChangeRequestCommentUpdateRequest request);

    public ChangeRequestCommentResponse create(ChangeRequestCommentCreateRequest request, ChangeRequestDomain changeRequestDomain);

    public ChangeRequestCommentResponse edit(String id, ChangeRequestCommentUpdateRequest request, ChangeRequestDomain changeRequestDomain);

    public void deleteById(String id);

    public List<ChangeRequestCommentResponse> findAll();

    public ChangeRequestCommentResponse findById(String id);

    public ChangeRequestCommentSearchResponse searchAll(ChangeRequestCommentSearchRequest changeRequestCommentSearchRequest);

    public ChangeRequestCommentSearchResponse searchFilterAll(ChangeRequestCommentSearchFilterRequest changeRequestCommentSearchFilterRequest);

    public ChangeRequestCommentResponse findByName(String name);

    public ChangeRequestCommentResponse findByIdentify(String identify);

    public ChangeRequestCommentResponse searchByChangeRequestAndChangeRequestComment(String idChangeRequest, String idChangeRequestComment);

}
