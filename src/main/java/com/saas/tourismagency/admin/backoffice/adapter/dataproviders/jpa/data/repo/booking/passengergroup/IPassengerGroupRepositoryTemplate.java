package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IPassengerGroupRepositoryTemplate {

    public PageDTO<PassengerGroupDomain> query(Pageable pageable, Query query);

    public List<PassengerGroupDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<PassengerGroupDomain> queryAllSimpleDomainCaseListPassengerGroup(Pageable pageable, Query query);

    public List<PassengerGroupDomain> queryAllSimpleDomainCaseDeleteByRegisterGroup(Query query);

    public List<PassengerGroupDomain> queryAllSimpleDomainCaseGetCollectionForLastIndex(Query query);
}
