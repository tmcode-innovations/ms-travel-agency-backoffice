package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingrestaurant;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingRestaurantDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IRegisterBookingRestaurantRepTemp {

    public PageDTO<RegisterBookingRestaurantDomain> query(Pageable pageable, Query query);

    public List<RegisterBookingRestaurantDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<RegisterBookingRestaurantDomain> queryAllSimpleDomainCaseListRegisterBookingRestaurant(Pageable pageable, Query query);

    public RegisterBookingRestaurantDomain queryOneIdSimpleDomainCaseValidationExistBooking(Query query);

    public RegisterBookingRestaurantDomain queryOneRegisterBookingRestaurantSimpleDomainCaseAllCupon(Query query);

    public void updateChangeDateItineraryDate(Query query, Update update);
}
