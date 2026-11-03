package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodging;

import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IRegisterBookingItemLodgingRepositoryTemplate {

    public PageDTO<RegisterBookingItemLodgingDomain> query(Pageable pageable, Query query);

    public List<RegisterBookingItemLodgingDomain> query(Query query);

    public List<RegisterBookingItemLodgingDomain> queryAllRegisterBookingItemLodgingSimpleDomainCaseOnluId(Query query);

    public List<RegisterBookingItemLodgingDomain> queryAllRegisterBookingItemLodgingSimpleDomainCaseAllCupon(Query query);

    public void updateChangeDateItineraryDate(Query query, Update update);
}
