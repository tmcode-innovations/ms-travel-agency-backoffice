package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinginstitution;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingInstitutionDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IRegisterBookingInstitutionRepTemp {

    public PageDTO<RegisterBookingInstitutionDomain> query(Pageable pageable, Query query);

    public List<RegisterBookingInstitutionDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<RegisterBookingInstitutionDomain> queryAllSimpleDomainCaseListRegisterBookingInstitution(Pageable pageable, Query query);

    public RegisterBookingInstitutionDomain queryOneIdSimpleDomainCaseValidationExistBooking(Query query);

    public void updateChangeDateItineraryDate(Query query, Update update);
}
