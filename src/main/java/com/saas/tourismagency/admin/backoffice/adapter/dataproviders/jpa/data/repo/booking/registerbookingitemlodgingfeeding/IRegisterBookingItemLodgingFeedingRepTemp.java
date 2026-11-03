package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgingfeeding;

import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingFeedingDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IRegisterBookingItemLodgingFeedingRepTemp {

    public PageDTO<RegisterBookingItemLodgingFeedingDomain> query(Pageable pageable, Query query);

    public List<RegisterBookingItemLodgingFeedingDomain> query(Query query);

    public List<RegisterBookingItemLodgingFeedingDomain> queryAllRegisterBookingItemLodgingSimpleDomainCaseAllCupon(Query query);

    public List<RegisterBookingItemLodgingFeedingDomain> queryAllRegisterBookingItemLodgerLodgingSimpleDomainCaseAllCupon(Query query);

    public void updateChangeDateItineraryDate(Query query, Update update);
}
