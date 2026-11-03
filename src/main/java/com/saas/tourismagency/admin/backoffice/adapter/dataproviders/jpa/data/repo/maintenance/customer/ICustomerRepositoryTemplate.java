package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ICustomerRepositoryTemplate {

    public PageDTO<CustomerDomain> query(Pageable pageable, Query query);

    public List<CustomerDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public CustomerDomain queryOneName(Query query);

    // Statistics
    public Long countCustomer(Query query);

    public PageDTO<CustomerDomain> queryAllSimpleDomainCaseListCustomer(Pageable pageable, Query query);
}
