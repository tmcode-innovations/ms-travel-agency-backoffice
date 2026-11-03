package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtour;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.CostTourDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface CostTourBookingRepositoryTemplate {

    public PageDTO<CostTourDomain> query(Pageable pageable, Query query);

    public List<CostTourDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<CostTourDomain> queryAllSimpleDomainCaseShowCostTour(Query query);

    public void updateIdSimpleDomainCaseUpdateSubTotalAmount(Query query, Update update);

    public List<CostTourDomain> queryAllSimpleDomainCaseDeleteByRegisterGroup(Query query);

    public List<CostTourDomain> queryAllSimpleDomainCaseDeleteByRegisterGroupAndItineraryDate(Query query);

    public List<CostTourDomain> queryAllSimpleDomainCaseResendFileToSale(Query query);
}
