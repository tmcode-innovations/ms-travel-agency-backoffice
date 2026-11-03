package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.*;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDomain;

import java.util.List;

public interface VoucherEgressDetailUseCase {

    public VoucherEgressDetailResponse create(VoucherEgressDetailCreateRequest request);

    public VoucherEgressDetailResponse create(VoucherEgressDetailCreateRequest request, VoucherEgressDomain voucherEgressDomain);

    public VoucherEgressDetailResponse edit(String id, VoucherEgressDetailUpdateRequest request);

    public VoucherEgressDetailResponse edit(String id, VoucherEgressDetailUpdateRequest request, VoucherEgressDomain voucherEgressDomain);

    public void deleteById(String id);

    public List<VoucherEgressDetailResponse> findAll();

    public VoucherEgressDetailResponse findById(String id);

    public VoucherEgressDetailSearchResponse searchAll(VoucherEgressDetailSearchRequest bankSearchRequest);

    public VoucherEgressDetailSearchResponse searchFilterAll(VoucherEgressDetailSearchFilterRequest bankSearchFilterRequest);

    public List<VoucherEgressDetailResponse> searchAllByVoucherEgress(String idVoucherEgress);

    public VoucherEgressDetailResponse searchByVoucherEgressAndVoucherEgressDetail(String idVoucherEgress, String idVoucherEgressDetail);

    public VoucherEgressDetailResponse getVoucherEgressDetailModificationData(String idVoucherEgress, String idVoucherEgressDetail);

    public VoucherEgressDetailResponse changeCantPax(String idItineraryItem, VoucherEgressDetailChangeCantPaxRequest request);

    public void duplicate(String id);
}
