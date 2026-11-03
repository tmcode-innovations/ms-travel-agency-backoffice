package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.guide;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IGuideRepositoryTemplate {

    public PageDTO<GuideDomain> query(Pageable pageable, Query query);

    public List<GuideDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<GuideDomain> queryAllSimple(Query query);

    public PageDTO<GuideDomain> queryAllSimpleDomainCaseListGuide(Pageable pageable, Query query);
}
