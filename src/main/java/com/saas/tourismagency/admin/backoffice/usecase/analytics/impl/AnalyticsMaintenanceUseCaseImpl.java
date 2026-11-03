package com.saas.tourismagency.admin.backoffice.usecase.analytics.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.AnalyticsMaintenanceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.maintenance.AnalyticsMaintenanceSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.maintenance.AnalyticsMaintenanceSearchResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.usecase.analytics.AnalyticsMaintenanceUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AnalyticsMaintenanceUseCaseImpl implements AnalyticsMaintenanceUseCase {

    @Autowired
    private AnalyticsMaintenanceRepo analyticsMaintenanceRepo;

    @Override
    @Transactional(readOnly = true)
    public AnalyticsMaintenanceSearchResponse<?> searchFilterPaginateSupplier(AnalyticsMaintenanceSearchFilterRequest request) {
        PageDTO<SupplierDomain> pageDTO = analyticsMaintenanceRepo.searchPageableSupplier(request);
        AnalyticsMaintenanceSearchResponse<SupplierResponse> response = new AnalyticsMaintenanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(SupplierResponse::from).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }
}
