package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregress.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetailitem.VoucherEgressDetailItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetailitem.VoucherEgressDetailItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetailitem.VoucherEgressDetailItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringress.VoucherIngressChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringress.VoucherIngressResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;

import java.util.List;

public interface VoucherEgressUseCase {

    public VoucherEgressResponse create(VoucherEgressCreateRequest request);

    public VoucherEgressResponse edit(String id, VoucherEgressUpdateRequest request);

    public void deleteById(String id);

    public List<VoucherEgressResponse> findAll();

    public VoucherEgressResponse findById(String id);

    public VoucherEgressSearchResponse searchAll(VoucherEgressSearchRequest bankSearchRequest);

    public VoucherEgressSearchResponse searchFilterAll(VoucherEgressSearchFilterRequest bankSearchFilterRequest);

    public VoucherEgressResponse changeState(String id, VoucherEgressChangeStateRequest request);



    public VoucherEgressDetailResponse addVoucherEgressDetail(VoucherEgressDetailCreateRequest request, String idVoucherEgress);

    public VoucherEgressDetailResponse editVoucherEgressDetail(String id, VoucherEgressDetailUpdateRequest request, String idVoucherEgress);

    public void deleteVoucherEgressDetail(String id, String idVoucherEgress);

    public List<VoucherEgressDetailResponse> findAllVoucherEgressDetailByVoucherEgress(String idVoucherEgress);

    public VoucherEgressDetailResponse findByIdAndVoucherEgress(String id, String idVoucherEgress);




    public VoucherEgressDetailItemResponse addVoucherEgressDetailItem(VoucherEgressDetailItemCreateRequest request, String idVoucherEgress, String idVoucherEgressDetail);

    public VoucherEgressDetailItemResponse editVoucherEgressDetailItem(String id, VoucherEgressDetailItemUpdateRequest request, String idVoucherEgress, String idVoucherIngressDetail);

    public void deleteVoucherEgressDetailItem(String id, String idVoucherEgress, String idVoucherEgressDetail);

    public List<VoucherEgressDetailItemResponse> findAllVoucherEgressDetailItemByVoucherEgressAndVoucherEgressDetail(String idVoucherEgress, String idVoucherEgressDetail);

    public VoucherEgressDetailItemResponse findByIdAndVoucherEgressAndVoucherEgressDetail(String id, String idVoucherEgress, String idVoucherEgressDetail);


    public VoucherEgressResponse getVoucherEgressModificationData(String idVoucherEgress);

    public VoucherEgressDetailResponse getVoucherEgressDetailModificationData(String id, String idVoucherEgress);


    public byte[] exportVoucherEgressPdf(String idVoucherEgress);

    public byte[] exportVoucherEgressExcel(String idVoucherEgress);

    public List<SimpleBaseResponse> findAllSimple();

    public List<VoucherEgressResponse> findAllSimplePending();

    public List<VoucherEgressResponse> findAllSimplePendingBooking();

    public void duplicateVoucherEgressDetail(String idVoucherEgress, String idVoucherEgressDetail);
}
