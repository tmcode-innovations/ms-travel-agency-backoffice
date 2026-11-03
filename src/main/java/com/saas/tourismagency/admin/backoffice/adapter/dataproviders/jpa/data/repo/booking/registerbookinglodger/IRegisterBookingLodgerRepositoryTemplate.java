package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodger;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgerDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IRegisterBookingLodgerRepositoryTemplate {

    public PageDTO<RegisterBookingLodgerDomain> query(Pageable pageable, Query query);

    public List<RegisterBookingLodgerDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<RegisterBookingLodgerDomain> queryAllSimpleDomainCaseListRegisterBookingLodger(Pageable pageable, Query query);

    public RegisterBookingLodgerDomain queryOneIdSimpleDomainCaseValidationExistBooking(Query query);

    public RegisterBookingLodgerDomain queryOneRegisterBookingLodgerSimpleDomainCaseAllCupon(Query query);

    public void updateChangeDateItineraryDate(Query query, Update update);
}
