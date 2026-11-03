package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingroom;

import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingRoomDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IRegisterBookingRoomRepTemp {

    public PageDTO<RegisterBookingRoomDomain> query(Pageable pageable, Query query);

    public List<RegisterBookingRoomDomain> query(Query query);
}
