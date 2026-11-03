package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodging;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IRegisterBookingLodgingRepositoryTemplate {

    public PageDTO<RegisterBookingLodgingDomain> query(Pageable pageable, Query query);

    public List<RegisterBookingLodgingDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<RegisterBookingLodgingDomain> queryAllSimpleDomainCaseListRegisterBookingLodging(Pageable pageable, Query query);

    public RegisterBookingLodgingDomain queryOneIdSimpleDomainCaseValidationExistBooking(Query query);

    public RegisterBookingLodgingDomain queryOneRegisterBookingLodgingSimpleDomainCaseAllCupon(Query query);

    public void updateChangeDateItineraryDate(Query query, Update update);
}
