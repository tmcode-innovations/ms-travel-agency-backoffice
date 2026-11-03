package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtouritem;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.CostTourItemDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface CostTourItemBookingRepositoryTemplate {

    public PageDTO<CostTourItemDomain> query(Pageable pageable, Query query);

    public List<CostTourItemDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<CostTourItemDomain> queryAllSimpleDomainCaseShowCostTourItem(Query query);

    public List<CostTourItemDomain> queryAllSimpleDomainCaseDeleteByRegisterGroup(Query query);

    public List<CostTourItemDomain> queryAllSimpleDomainCaseResendFileToSaleByCostTourAndRegisterGroup(Query query);
}
