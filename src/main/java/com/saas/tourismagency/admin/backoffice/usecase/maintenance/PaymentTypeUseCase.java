package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.paymenttype.*;

import java.util.List;

public interface PaymentTypeUseCase {

    public PaymentTypeResponse create(PaymentTypeCreateRequest request);

    public PaymentTypeResponse edit(String id, PaymentTypeUpdateRequest request);

    public void deleteById(String id);

    public List<PaymentTypeResponse> findAll();

    public PaymentTypeResponse findById(String id);

    public PaymentTypeSearchResponse searchAll(PaymentTypeSearchRequest paymentTypeSearchRequest);

    public PaymentTypeSearchResponse searchFilterAll(PaymentTypeSearchFilterRequest paymentTypeSearchFilterRequest);

    public PaymentTypeResponse findByName(String name);

    public PaymentTypeResponse findByIdentify(String identify);
}
