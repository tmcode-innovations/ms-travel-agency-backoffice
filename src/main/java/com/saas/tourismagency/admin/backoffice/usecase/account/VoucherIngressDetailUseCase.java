package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.*;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDomain;

import java.util.List;

public interface VoucherIngressDetailUseCase {

    public VoucherIngressDetailResponse create(VoucherIngressDetailCreateRequest request);

    public VoucherIngressDetailResponse create(VoucherIngressDetailCreateRequest request, VoucherIngressDomain voucherIngressDomain);

    public VoucherIngressDetailResponse edit(String id, VoucherIngressDetailUpdateRequest request);

    public VoucherIngressDetailResponse edit(String id, VoucherIngressDetailUpdateRequest request, VoucherIngressDomain voucherIngressDomain);

    public void deleteById(String id);

    public List<VoucherIngressDetailResponse> findAll();

    public VoucherIngressDetailResponse findById(String id);

    public VoucherIngressDetailSearchResponse searchAll(VoucherIngressDetailSearchRequest voucherIngressDetailSearchRequest);

    public VoucherIngressDetailSearchResponse searchFilterAll(VoucherIngressDetailSearchFilterRequest voucherIngressDetailSearchFilterRequest);

    public List<VoucherIngressDetailResponse> searchAllByVoucherIngress(String idVoucherIngress);

    public VoucherIngressDetailResponse searchByVoucherIngressAndVoucherIngressDetail(String idVoucherIngress, String idVoucherIngressDetail);


}
