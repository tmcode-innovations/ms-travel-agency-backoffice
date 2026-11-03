package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingoperator;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IRegisterBookingOperatorRepTemp {

    public PageDTO<RegisterBookingOperatorDomain> query(Pageable pageable, Query query);

    public List<RegisterBookingOperatorDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<RegisterBookingOperatorDomain> queryAllSimpleDomainCaseListRegisterBookingOperator(Pageable pageable, Query query);

    public RegisterBookingOperatorDomain queryOneIdSimpleDomainCaseValidationExistBooking(Query query);

    public RegisterBookingOperatorDomain queryOneRegisterBookingOperatorSimpleDomainCaseAllCupon(Query query);

    public void updateChangeDateItineraryDate(Query query, Update update);
}
