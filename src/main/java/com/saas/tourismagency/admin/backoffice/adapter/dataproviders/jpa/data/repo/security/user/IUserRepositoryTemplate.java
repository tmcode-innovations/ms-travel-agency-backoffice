package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.user;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IUserRepositoryTemplate {

    public PageDTO<UserDomain> query(Pageable pageable, Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<UserDomain> queryAllSimpleDomainCaseListUser(Pageable pageable, Query query);
}
