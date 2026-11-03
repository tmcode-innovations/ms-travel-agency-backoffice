package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.grouppqttourist;

import com.saas.tourismagency.admin.backoffice.domain.account.GroupPqtTouristDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

public interface IGroupPqtTouristRepoTemp {

    public PageDTO<GroupPqtTouristDomain> query(Pageable pageable, Query query);

    public PageDTO<GroupPqtTouristDomain> queryAllSimpleDomainCaseListGroupPqtTourist(Pageable pageable, Query query);

}
