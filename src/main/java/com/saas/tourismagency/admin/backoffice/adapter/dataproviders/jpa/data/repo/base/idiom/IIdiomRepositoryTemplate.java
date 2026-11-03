package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.idiom;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IIdiomRepositoryTemplate {

    public PageDTO<IdiomDomain> query(Pageable pageable, Query query);

    public List<IdiomDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<IdiomDomain> queryAllSimpleDomainCaseListIdiom(Pageable pageable, Query query);
}
