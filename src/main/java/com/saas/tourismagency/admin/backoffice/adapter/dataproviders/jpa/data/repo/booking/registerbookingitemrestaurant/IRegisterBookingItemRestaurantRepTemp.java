package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemrestaurant;

import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemRestaurantDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IRegisterBookingItemRestaurantRepTemp {

    public PageDTO<RegisterBookingItemRestaurantDomain> query(Pageable pageable, Query query);

    public List<RegisterBookingItemRestaurantDomain> query(Query query);

    public List<RegisterBookingItemRestaurantDomain> queryAllRegisterBookingItemRestaurantSimpleDomainCaseAllCupon(Query query);

    public void updateChangeDateItineraryDate(Query query, Update update);
}
