package com.saas.tourismagency.admin.backoffice.usecase.operation.impl;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.nomenclature.OrderServiceStateResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.nomenclature.RequestOrderServiceStateResponse;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.OrderServiceStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.RequestOrderServiceStateEnum;
import com.saas.tourismagency.admin.backoffice.usecase.operation.OperationNomenclatureUseCase;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class OperationNomenclatureUseCaseImpl implements OperationNomenclatureUseCase {

    @Override
    @Transactional(readOnly = true)
    public List<RequestOrderServiceStateResponse> searchAllRequestOrderServiceState() {
        return EnumMappeableService
                .map(RequestOrderServiceStateEnum.class)
                .stream()
                .map(requestOrderServiceStateModel -> RequestOrderServiceStateResponse.from(requestOrderServiceStateModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderServiceStateResponse> searchAllOrderServiceState() {
        return EnumMappeableService
                .map(OrderServiceStateEnum.class)
                .stream()
                .map(orderServiceStateModel -> OrderServiceStateResponse.from(orderServiceStateModel))
                .toList();
    }

}
