package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.requestorderservice;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IRequestOrderServiceRepositoryTemplate {

    public PageDTO<RequestOrderServiceDomain> query(Pageable pageable, Query query);

    public List<RequestOrderServiceDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<RequestOrderServiceDomain> queryAllSimpleDomain(Query query);

    public RequestOrderServiceDomain queryOneIdSimpleDomainCaseGenerateRequestOrderService(Query query);

    public PageDTO<RequestOrderServiceDomain> queryAllSimpleDomainCaseListRequestOrderService(Pageable pageable, Query query);

    public void updateRequestOrderServiceStateByIdSimpleDomainCaseAddItem(Query query, Update update);
}
