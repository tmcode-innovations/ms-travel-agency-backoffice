package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itinerarydate;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IItineraryDateCrmRepositoryTemplate {

    public PageDTO<ItineraryDateDomain> query(Pageable pageable, Query query);

    public List<ItineraryDateDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseShowItinerary(Query query);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseUpdateOrderItinerary(Query query);

    public ItineraryDateDomain queryOneIdSimpleDomainCaseUpdateOrderItinerary(Query query);

    public void updateByIdSimpleDomainCaseUpdateOrderItinerary(Query query, Update update);

    public ItineraryDateDomain queryOneIdSimpleDomainCaseDeleteItineraryDate(Query query);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseDeleteItineraryDate(Query query);

    public ItineraryDateDomain queryOneIdSimpleDomainCaseGeneric(Query query);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseGeneric(Query query);

    public ItineraryDateDomain queryOneIdSimpleDomainCaseDeleteTour(Query query);

    public void updateToursByIdSimpleDomainCaseDeleteTour(Query query, Update update);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseDeleteTour(Query query);

    public void updateIdSimpleDomainCaseUpdateOrderItineraryDate(Query query, Update update);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseDeleteByQuotation(Query query);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseResendQuotationToBooking(Query query);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseChangeStateOpened(Query query);
}
