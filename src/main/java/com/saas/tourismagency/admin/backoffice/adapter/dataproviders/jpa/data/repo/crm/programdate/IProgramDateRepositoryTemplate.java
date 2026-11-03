package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programdate;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDateDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IProgramDateRepositoryTemplate {

    public PageDTO<ProgramDateDomain> query(Pageable pageable, Query query);

    public List<ProgramDateDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<ProgramDateDomain> queryAllSimpleDomain(Query query);

    public void updateToursByIdSimpleDomainCaseDeleteTour(Query query, Update update);

    public List<ProgramDateDomain> queryAllSimpleDomainCaseDeleteTour(Query query);
}
