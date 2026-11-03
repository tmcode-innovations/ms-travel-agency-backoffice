package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proforma;

import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IProformaRepoTemp {

    public PageDTO<ProformaDomain> query(Pageable pageable, Query query);

    public List<ProformaDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public void updateAmortizationById(Query query, Update update);
}
