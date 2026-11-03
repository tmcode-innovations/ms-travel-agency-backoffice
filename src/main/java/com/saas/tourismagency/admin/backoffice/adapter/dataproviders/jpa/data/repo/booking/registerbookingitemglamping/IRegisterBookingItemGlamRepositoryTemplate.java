package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemglamping;

import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemGlampingDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IRegisterBookingItemGlamRepositoryTemplate {

    public PageDTO<RegisterBookingItemGlampingDomain> query(Pageable pageable, Query query);

    public List<RegisterBookingItemGlampingDomain> query(Query query);

    public void updateChangeDateItineraryDate(Query query, Update update);
}
