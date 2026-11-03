package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderserviceitem;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IOrderServiceItemRepositoryTemplate {

    public PageDTO<OrderServiceItemDomain> query(Pageable pageable, Query query);

    public List<OrderServiceItemDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<OrderServiceItemDomain> queryAllSimpleDomain(Query query);

    public List<OrderServiceItemDomain> queryAllSimpleDomainCaseListOrderServiceItems(Query query);

    public List<OrderServiceItemDomain> queryAllSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItems(Query query);

    public List<OrderServiceItemDomain> queryAllSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItemsButRequestOrder(Query query);

    public List<OrderServiceItemDomain> queryAllSimpleDomainCaseShowOrderServiceItemByDelete(Query query);

    public List<OrderServiceItemDomain> queryAllSimpleDomainCaseListOrderServiceItemsExpenseGuide(Query query);

    public void updateChangeDateItineraryDate(Query query, Update update);
}
