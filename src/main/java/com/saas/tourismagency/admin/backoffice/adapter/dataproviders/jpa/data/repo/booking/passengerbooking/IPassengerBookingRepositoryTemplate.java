package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengerbooking;

import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerBookingDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IPassengerBookingRepositoryTemplate {

    public PageDTO<PassengerBookingDomain> query(Pageable pageable, Query query);

    public List<PassengerBookingDomain> query(Query query);
}
