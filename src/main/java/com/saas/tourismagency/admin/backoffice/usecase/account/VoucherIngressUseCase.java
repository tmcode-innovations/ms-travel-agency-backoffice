package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringress.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.PassengerGroupResponse;

import java.util.List;

public interface VoucherIngressUseCase {

    public VoucherIngressResponse create(VoucherIngressCreateRequest request);

    public VoucherIngressResponse edit(String id, VoucherIngressUpdateRequest request);

    public void deleteById(String id);

    public List<VoucherIngressResponse> findAll();

    public VoucherIngressResponse findById(String id);

    public VoucherIngressSearchResponse searchAll(VoucherIngressSearchRequest voucherIngressSearchRequest);

    public VoucherIngressSearchResponse searchFilterAll(VoucherIngressSearchFilterRequest voucherIngressSearchFilterRequest);

    public VoucherIngressDetailResponse addVoucherIngressDetail(VoucherIngressDetailCreateRequest request, String idVoucherIngress);

    public VoucherIngressDetailResponse editVoucherIngressDetail(String id, VoucherIngressDetailUpdateRequest request, String idVoucherIngress);

    public void deleteVoucherIngressDetail(String id, String idVoucherIngress);

    public List<VoucherIngressDetailResponse> findAllVoucherIngressDetailByVoucherIngress(String idVoucherIngress);

    public VoucherIngressDetailResponse findByIdAndVoucherIngress(String id, String idVoucherIngress);

    public VoucherIngressResponse changeState(String id, VoucherIngressChangeStateRequest request);

    public VoucherIngressResponse getVoucherIngressModificationData(String idVoucherIngress);

    public VoucherIngressVerificationImportResponse verificationImport(String idRegisterGroup);

    public byte[] exportVoucherIngressPdf(String idVoucherIngress);

    public byte[] exportVoucherIngressExcel(String idVoucherIngress);
}
