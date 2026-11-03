package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.profile;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ProfileDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IProfileRepositoryTemplate {

    public PageDTO<ProfileDomain> query(Pageable pageable, Query query);

    public List<ProfileDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<ProfileDomain> queryAllSimpleDomainCaseListProfile(Pageable pageable, Query query);
}
