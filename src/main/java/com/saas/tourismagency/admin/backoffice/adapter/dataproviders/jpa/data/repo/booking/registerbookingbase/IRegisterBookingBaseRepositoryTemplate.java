package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase;

import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IRegisterBookingBaseRepositoryTemplate {

    public PageDTO<RegisterBookingBaseDomain> query(Pageable pageable, Query query);

    public List<RegisterBookingBaseDomain> query(Query query);

    public List<RegisterBookingBaseDomain> queryAllRegisterBookingBaseAndActiveSimpleDomainCaseAllCupon(Query query);

    public List<RegisterBookingBaseDomain> queryAllByRegisterGroupAndActiveSimpleDomainCaseAnnulled(Query query);

    public void updateStateBookingAndActiveRegisterBookingIdSimpleDomainCaseAnnulled(Query query, Update update);

    public List<RegisterBookingBaseDomain> queryAllRegisterBookingBaseAndActiveSimpleDomainCaseAllGenerateBooking(Query query);
}
