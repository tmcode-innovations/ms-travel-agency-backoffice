package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.permission;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.PermissionDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IPermissionRepositoryTemplate {

    public PageDTO<PermissionDomain> query(Pageable pageable, Query query);

    public List<PermissionDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);
}
