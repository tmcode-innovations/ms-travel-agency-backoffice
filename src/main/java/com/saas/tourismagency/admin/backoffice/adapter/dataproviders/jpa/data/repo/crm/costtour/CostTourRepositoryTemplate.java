package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtour;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface CostTourRepositoryTemplate {

    public PageDTO<CostTourDomain> query(Pageable pageable, Query query);

    public List<CostTourDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<CostTourDomain> queryAllSimpleDomainCaseShowCostTour(Query query);

    public void updateIdSimpleDomainCaseUpdateSubTotalAmount(Query query, Update update);

    public List<CostTourDomain> queryAllSimpleDomainCaseDeleteByQuotation(Query query);

    public List<CostTourDomain> queryAllSimpleDomainCaseDeleteByQuotationAndItineraryDate(Query query);

    public List<CostTourDomain> queryAllSimpleDomainCaseResendQuotationToBooking(Query query);


    public List<CostTourDomain> queryAllSimpleDomainCaseChangeStateOpened(Query query);
}
