package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core;

import com.saas.tourismagency.admin.backoffice.domain.core.OrganizationDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

public interface IOrganizationRepositoryTemplate {

    public PageDTO<OrganizationDomain> query(Pageable pageable, Query query);

}
