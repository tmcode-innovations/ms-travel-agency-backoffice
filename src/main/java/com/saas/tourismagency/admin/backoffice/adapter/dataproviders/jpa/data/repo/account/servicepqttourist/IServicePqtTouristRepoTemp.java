package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.servicepqttourist;

import com.saas.tourismagency.admin.backoffice.domain.account.ServicePqtTouristDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IServicePqtTouristRepoTemp {

    public PageDTO<ServicePqtTouristDomain> query(Pageable pageable, Query query);

    public List<ServicePqtTouristDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<ServicePqtTouristDomain> queryAllSimpleDomainCaseShowServicePqtTourist(Query query);

    public PageDTO<ServicePqtTouristDomain> queryAllSimpleDomainCaseListServicePqtTourist(Pageable pageable, Query query);
}
