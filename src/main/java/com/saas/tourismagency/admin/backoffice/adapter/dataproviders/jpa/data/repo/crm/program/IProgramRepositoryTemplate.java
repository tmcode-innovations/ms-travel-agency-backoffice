package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.program;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IProgramRepositoryTemplate {

    public PageDTO<ProgramDomain> query(Pageable pageable, Query query);

    public List<ProgramDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<ProgramDomain> queryAllSimpleDomainCaseListProgram(Pageable pageable, Query query);

    // Statistics
    public Long countProgram(Query query);

    public List<ProgramDomain> queryAllSimpleWithDay(Query query);
}
