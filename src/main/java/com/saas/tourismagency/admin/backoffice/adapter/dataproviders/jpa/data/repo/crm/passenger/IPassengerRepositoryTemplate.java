package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.passenger;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.PassengerDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IPassengerRepositoryTemplate {

    public PageDTO<PassengerDomain> query(Pageable pageable, Query query);

    public List<PassengerDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<PassengerDomain> queryAllSimpleDomainCaseDeleteByQuotation(Query query);

    public List<PassengerDomain> queryAllSimpleDomainCaseGetCollectionForLastIndex(Query query);

    public List<PassengerDomain> queryAllSimpleDomainCaseResendQuotationToBooking(Query query);

    public List<PassengerDomain> queryAllSimpleDomainCaseChangeStateOpened(Query query);
}
