package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programitem;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramItemDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IProgramItemRepositoryTemplate {

    public PageDTO<ProgramItemDomain> query(Pageable pageable, Query query);

    public List<ProgramItemDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<ProgramItemDomain> queryAllSimpleDomain(Query query);

    public List<ProgramItemDomain> queryAllSimpleDomainCaseCreateItinerary(Query query);

    public List<ProgramItemDomain> queryAllSimpleDomainCaseDeleteTours(Query query);
}
