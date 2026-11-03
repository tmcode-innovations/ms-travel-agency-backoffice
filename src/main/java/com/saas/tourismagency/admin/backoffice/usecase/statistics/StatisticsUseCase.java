package com.saas.tourismagency.admin.backoffice.usecase.statistics;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.statistics.CountBaseResponse;
import com.saas.tourismagency.admin.backoffice.domain.statistics.GroupedDataDomain;

import java.util.List;

public interface StatisticsUseCase {

    public CountBaseResponse countQuotation();

    public CountBaseResponse countRegisterGroup();

    public CountBaseResponse countProgram();

    public CountBaseResponse countCustomer();

    public CountBaseResponse countSupplier();

    public CountBaseResponse countService();

    public CountBaseResponse countTour();

    public List<Integer> supplierByType();
}
