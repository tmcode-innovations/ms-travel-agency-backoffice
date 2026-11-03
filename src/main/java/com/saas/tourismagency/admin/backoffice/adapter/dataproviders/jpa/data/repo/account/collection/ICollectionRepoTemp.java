package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.collection;

import com.saas.tourismagency.admin.backoffice.domain.account.CollectionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface ICollectionRepoTemp {

    public PageDTO<CollectionDomain> query(Pageable pageable, Query query);

    public List<CollectionDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public void updateIdSimpleDomainCaseUpdateDocumentFinanceBasic(Query query, Update update);

    public CollectionDomain queryOneIdSimpleDomainCaseGetModificationData(Query query);

    public PageDTO<CollectionDomain> queryAllSimpleDomainCaseListCollection(Pageable pageable, Query query);
}
