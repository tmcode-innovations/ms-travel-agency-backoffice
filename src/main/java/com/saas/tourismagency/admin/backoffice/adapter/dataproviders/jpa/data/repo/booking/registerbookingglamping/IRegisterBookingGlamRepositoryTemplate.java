package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingglamping;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingGlampingDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IRegisterBookingGlamRepositoryTemplate {

    public PageDTO<RegisterBookingGlampingDomain> query(Pageable pageable, Query query);

    public List<RegisterBookingGlampingDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<RegisterBookingGlampingDomain> queryAllSimpleDomainCaseListRegisterBookingGlamping(Pageable pageable, Query query);

    public RegisterBookingGlampingDomain queryOneIdSimpleDomainCaseValidationExistBooking(Query query);

    public void updateChangeDateItineraryDate(Query query, Update update);
}
