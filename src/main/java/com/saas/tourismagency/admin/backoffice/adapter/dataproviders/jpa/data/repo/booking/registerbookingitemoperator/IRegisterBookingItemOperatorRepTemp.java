package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemoperator;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemOperatorDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IRegisterBookingItemOperatorRepTemp {

    public PageDTO<RegisterBookingItemOperatorDomain> query(Pageable pageable, Query query);

    public List<RegisterBookingItemOperatorDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<RegisterBookingItemOperatorDomain> queryAllSimpleDomain(Query query);

    public List<RegisterBookingItemOperatorDomain> queryAllSimpleDomainCaseOperationAvailable(Query query);

    public List<RegisterBookingItemOperatorDomain> queryAllRegisterBookingItemOperatorSimpleDomainCaseAllCupon(Query query);

    public void updateChangeDateItineraryDate(Query query, Update update);
}
