package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetailitem.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailResponse;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDomain;

import java.util.List;

public interface VoucherEgressDetailItemUseCase {

    public VoucherEgressDetailItemResponse create(VoucherEgressDetailItemCreateRequest request);

    public VoucherEgressDetailItemResponse create(VoucherEgressDetailItemCreateRequest request, VoucherEgressDomain voucherEgressDomain, VoucherEgressDetailDomain voucherEgressDetailDomain);

    public VoucherEgressDetailItemResponse edit(String id, VoucherEgressDetailItemUpdateRequest request);

    public VoucherEgressDetailItemResponse edit(String id, VoucherEgressDetailItemUpdateRequest request, VoucherEgressDomain voucherEgressDomain, VoucherEgressDetailDomain voucherEgressDetailDomain);

    public void deleteById(String id);

    public List<VoucherEgressDetailItemResponse> findAll();

    public VoucherEgressDetailItemResponse findById(String id);

    public VoucherEgressDetailItemSearchResponse searchAll(VoucherEgressDetailItemSearchRequest bankSearchRequest);

    public VoucherEgressDetailItemSearchResponse searchFilterAll(VoucherEgressDetailItemSearchFilterRequest bankSearchFilterRequest);

    public List<VoucherEgressDetailItemResponse> searchAllByVoucherEgressAndVoucherEgressDetail(String idVoucherEgress, String idVoucherEgressDetail);

    public VoucherEgressDetailItemResponse searchByVoucherEgressAndVoucherEgressDetailAndVoucherEgressDetailItem(String idVoucherEgress, String idVoucherEgressDetail, String idVoucherEgressDetailItem);

}
