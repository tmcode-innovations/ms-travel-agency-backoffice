package com.saas.tourismagency.admin.backoffice.usecase.operation;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.nomenclature.OrderServiceStateResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.nomenclature.RequestOrderServiceStateResponse;

import java.util.List;

public interface OperationNomenclatureUseCase {

    public List<RequestOrderServiceStateResponse> searchAllRequestOrderServiceState();

    public List<OrderServiceStateResponse> searchAllOrderServiceState();

}
