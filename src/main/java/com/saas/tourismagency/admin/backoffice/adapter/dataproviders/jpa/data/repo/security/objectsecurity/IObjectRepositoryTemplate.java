package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.objectsecurity;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IObjectRepositoryTemplate {

    public PageDTO<ObjectDomain> query(Pageable pageable, Query query);

    public List<ObjectDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);
}
