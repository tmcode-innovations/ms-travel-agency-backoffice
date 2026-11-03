package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IServiceRepositoryTemplate {

    public PageDTO<ServiceDomain> query(Pageable pageable, Query query);

    public List<ServiceDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    // Statistics
    public Long countService(Query query);

    public List<ServiceDomain> queryAllSimpleDomainCaseToolUpdateAbbrev(Query query);

    public void updateIdSimpleDomainCaseUpdateToolAbbrev(Query query, Update update);

    public PageDTO<ServiceDomain> queryAllSimpleDomainCaseListService(Pageable pageable, Query query);
}
