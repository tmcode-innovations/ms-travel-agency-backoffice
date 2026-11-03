package com.saas.tourismagency.admin.backoffice.usecase.operation;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.configurationsupplieroperator.*;
import com.saas.tourismagency.admin.backoffice.domain.operation.ConfigurationSupplierOperatorDomain;

import java.util.List;

public interface ConfigurationSupplierOperatorUseCase {

    public ConfigurationSupplierOperatorResponse create(ConfigurationSupplierOperatorCreateRequest request);

    public ConfigurationSupplierOperatorResponse edit(String id, ConfigurationSupplierOperatorUpdateRequest request);

    public void deleteById(String id);

    public List<ConfigurationSupplierOperatorResponse> findAll();

    public ConfigurationSupplierOperatorResponse findById(String id);

    public ConfigurationSupplierOperatorDomain findByIdDomain(String id);

    public ConfigurationSupplierOperatorSearchResponse searchAll(ConfigurationSupplierOperatorSearchRequest bankSearchRequest);

    public ConfigurationSupplierOperatorSearchResponse searchFilterAll(ConfigurationSupplierOperatorSearchFilterRequest bankSearchFilterRequest);

}
