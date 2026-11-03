package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformacreditnote;

import com.saas.tourismagency.admin.backoffice.domain.account.ProformaCreditNoteDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IProformaCreditNoteRepoTemp {

    public PageDTO<ProformaCreditNoteDomain> query(Pageable pageable, Query query);

    public List<ProformaCreditNoteDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<ProformaCreditNoteDomain> queryAllSimpleDomainCaseListProformaCreditNote(Pageable pageable, Query query);
}
