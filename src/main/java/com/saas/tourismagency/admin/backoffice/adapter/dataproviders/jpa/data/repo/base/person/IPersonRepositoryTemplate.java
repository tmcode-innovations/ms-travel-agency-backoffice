package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.person;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IPersonRepositoryTemplate {

    public PageDTO<PersonDomain> query(Pageable pageable, Query query);

    public List<IdBaseDomain> queryIds(Query query);

}
