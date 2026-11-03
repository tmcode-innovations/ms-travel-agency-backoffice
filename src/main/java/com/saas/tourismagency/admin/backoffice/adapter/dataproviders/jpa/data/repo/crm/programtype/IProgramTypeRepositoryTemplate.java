package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programtype;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IProgramTypeRepositoryTemplate {

    public PageDTO<ProgramTypeDomain> query(Pageable pageable, Query query);

    public List<ProgramTypeDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<ProgramTypeDomain> queryAllSimpleDomainCaseListProgramType(Pageable pageable, Query query);
}
