package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.configurationsupplieroperator;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.ConfigurationSupplierOperatorDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IConfigurationSupplierOperatorRepTemp {

    public PageDTO<ConfigurationSupplierOperatorDomain> query(Pageable pageable, Query query);

    public List<ConfigurationSupplierOperatorDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<ConfigurationSupplierOperatorDomain> queryAllSimpleDomain(Query query);

    public ConfigurationSupplierOperatorDomain queryOneIdSimpleDomainCaseGenerateRequestOrderService(Query query);

    public PageDTO<ConfigurationSupplierOperatorDomain> queryAllSimpleDomainCaseListConfigurationSupplierOperator(Pageable pageable, Query query);
}
