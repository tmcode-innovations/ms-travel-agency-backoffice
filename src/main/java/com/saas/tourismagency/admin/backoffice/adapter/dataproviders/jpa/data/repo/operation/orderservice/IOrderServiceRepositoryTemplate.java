package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderservice;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IOrderServiceRepositoryTemplate {

    public PageDTO<OrderServiceDomain> query(Pageable pageable, Query query);

    public List<OrderServiceDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<OrderServiceDomain> queryAllSimpleDomain(Query query);

    public PageDTO<OrderServiceDomain> queryAllSimpleDomainCaseListOrderService(Pageable pageable, Query query);

    public void updateOrderServiceCantPaxByIdCaseUpdateItem(Query query, Update update);

    public List<OrderServiceDomain> queryAllSimpleDomainCaseShowOrderServiceByDelete(Query query);
}
