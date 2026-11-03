package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformacreditnote.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailUpdateRequest;

import java.util.List;

public interface ProformaCreditNoteUseCase {

    public ProformaCreditNoteResponse create(ProformaCreditNoteCreateRequest request);

    public ProformaCreditNoteResponse edit(String id, ProformaCreditNoteUpdateRequest request);

    public void deleteById(String id);

    public List<ProformaCreditNoteResponse> findAll();

    public ProformaCreditNoteResponse findById(String id);

    public ProformaCreditNoteSearchResponse searchAll(ProformaCreditNoteSearchRequest bankSearchRequest);

    public ProformaCreditNoteSearchResponse searchFilterAll(ProformaCreditNoteSearchFilterRequest bankSearchFilterRequest);

    public ProformaDetailResponse addProformaDetail(ProformaDetailCreateRequest request, String idProforma);

    public ProformaDetailResponse editProformaDetail(String id, ProformaDetailUpdateRequest request, String idProforma);

    public void deleteProformaDetail(String id, String idProforma);

    public List<ProformaDetailResponse> findAllProformaDetailByProforma(String idProforma);

    public ProformaDetailResponse findByIdAndProforma(String id, String idProforma);

    public byte[] exportProformaCreditNotePdf(String idProformaCreditNote);

    public ProformaCreditNoteResponse getProformaCreditNoteModificationData(String idProformaCreditNote);
}
