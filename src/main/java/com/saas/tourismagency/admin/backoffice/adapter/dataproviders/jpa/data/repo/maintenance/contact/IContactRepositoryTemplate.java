package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.contact;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ContactDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IContactRepositoryTemplate {

    public PageDTO<ContactDomain> query(Pageable pageable, Query query);

    public List<ContactDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<ContactDomain> queryAllSimpleDomainCaseListContact(Pageable pageable, Query query);
}
