package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.entry;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.EntryDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IEntryRepositoryTemplate {

    public PageDTO<EntryDomain> query(Pageable pageable, Query query);

    public List<EntryDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<EntryDomain> queryAllSimpleDocumentFinance(Query query);

    public PageDTO<EntryDomain> queryAllSimpleDomainCaseListEntry(Pageable pageable, Query query);
}
