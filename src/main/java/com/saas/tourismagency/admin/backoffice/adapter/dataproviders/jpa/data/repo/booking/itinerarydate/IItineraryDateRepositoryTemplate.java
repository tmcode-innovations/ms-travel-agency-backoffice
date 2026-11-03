package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IItineraryDateRepositoryTemplate {

    public PageDTO<ItineraryDateDomain> query(Pageable pageable, Query query);

    public List<ItineraryDateDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseShowItinerary(Query query);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseUpdateOrderItinerary(Query query);

    public ItineraryDateDomain queryOneIdSimpleDomainCaseUpdateOrderItinerary(Query query);

    public void updateByIdSimpleDomainCaseUpdateOrderItinerary(Query query, Update update);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseGenerateBooking(Query query);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseGenerateBookingAction(Query query);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseDeleteItineraryDate(Query query);

    public ItineraryDateDomain queryOneIdSimpleDomainCaseDeleteItineraryDate(Query query);

    public ItineraryDateDomain queryOneItineraryItemSimpleDomainCaseGeneric(Query query);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseGeneric(Query query);

    public ItineraryDateDomain queryOneIdSimpleDomainCaseDeleteTour(Query query);

    public void updateToursByIdSimpleDomainCaseDeleteTour(Query query, Update update);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseDeleteTour(Query query);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseDeleteByRegisterGroup(Query query);

    public void updateIdSimpleDomainCaseUpdateOrderItineraryDate(Query query, Update update);

    public List<ItineraryDateDomain> queryAllSimpleDomainCaseProgramByRegisterGroup(Query query);

    public ItineraryDateDomain queryOneIdSimpleDomainCaseCommonBasicData(Query query);

    public void updateIdSimpleDomainCaseUpdateIdItineraryDateQuotation(Query query, Update update);
}
