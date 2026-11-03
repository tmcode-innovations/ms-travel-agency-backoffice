package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ChangeTypeEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDate;
import java.util.List;

public interface IChangeTypeRepoTemp {

    public PageDTO<ChangeTypeDomain> query(Pageable pageable, Query query);

    public List<ChangeTypeDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<ChangeTypeDomain> queryAllSimpleDomainCaseShowChangeType(Query query);

    public PageDTO<ChangeTypeDomain> queryAllSimpleDomainCaseListChangeType(Pageable pageable, Query query);

    public List<ChangeTypeEntity> aggregateChangeTypeByCurrentDate(LocalDate localDate);

    public List<ChangeTypeDomain> queryAllSimpleDomainCaseToolUpdateChangeType(Query query);

    public void updateIdSimpleDomainCaseUpdateToolChangeType(Query query, Update update);
}
