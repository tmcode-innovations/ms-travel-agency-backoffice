package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.dateprocess;

import com.saas.tourismagency.admin.backoffice.domain.account.DateProcessDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IDateProcessRepoTemp {

    public PageDTO<DateProcessDomain> query(Pageable pageable, Query query);

    public List<DateProcessDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<DateProcessDomain> queryAllSimpleDomainCaseShowDateProcess(Query query);

    public PageDTO<DateProcessDomain> queryAllSimpleDomainCaseListDateProcess(Pageable pageable, Query query);

}
