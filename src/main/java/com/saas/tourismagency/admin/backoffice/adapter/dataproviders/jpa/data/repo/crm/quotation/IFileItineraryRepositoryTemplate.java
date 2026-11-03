package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.FileItineraryDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDateDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IFileItineraryRepositoryTemplate {

    public PageDTO<FileItineraryDomain> query(Pageable pageable, Query query);

    public List<FileItineraryDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);
}
