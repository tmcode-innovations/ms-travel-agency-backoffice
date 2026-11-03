package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itineraryitem;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IItineraryItemCrmRepositoryTemplate {

    public PageDTO<ItineraryItemDomain> query(Pageable pageable, Query query);

    public List<ItineraryItemDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomain(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseShowItinerary(Query query);

    public ItineraryItemDomain queryOneIdSimpleDomainCaseUpdateOrderItinerary(Query query);

    public void updateIdSimpleDomainCaseUpdateOrderItinerary(Query query, Update update);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseUpdateOrderItinerary(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseGetCalculateCost(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseGetCollectionForLastIndex(Query query);

    public ItineraryItemDomain queryOneIdSimpleDomainCaseGeneric(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseDeleteTours(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseDeleteByQuotation(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseResendQuotationToBooking(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseChangeStateOpened(Query query);
}
