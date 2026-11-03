package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.ticket;

import com.saas.tourismagency.admin.backoffice.domain.account.TicketDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface ITicketRepoTemp {

    public PageDTO<TicketDomain> query(Pageable pageable, Query query);

    public List<TicketDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public void updateIdSimpleDomainCaseUpdateDocumentFinanceBasic(Query query, Update update);

    public TicketDomain queryOneIdSimpleDomainCaseGetModificationData(Query query);

    public PageDTO<TicketDomain> queryAllSimpleDomainCaseListTicket(Pageable pageable, Query query);
}
