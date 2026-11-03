package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgerlodging;

import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgerLodgingDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IRegisterBookingItemLodgerLodgingRepTemp {

    public PageDTO<RegisterBookingItemLodgerLodgingDomain> query(Pageable pageable, Query query);

    public List<RegisterBookingItemLodgerLodgingDomain> query(Query query);

    public List<RegisterBookingItemLodgerLodgingDomain> queryAllRegisterBookingItemLodgerLodgingSimpleDomainCaseOnluId(Query query);

    public List<RegisterBookingItemLodgerLodgingDomain> queryAllRegisterBookingItemLodgerLodgingSimpleDomainCaseAllCupon(Query query);

    public void updateChangeDateItineraryDate(Query query, Update update);
}
