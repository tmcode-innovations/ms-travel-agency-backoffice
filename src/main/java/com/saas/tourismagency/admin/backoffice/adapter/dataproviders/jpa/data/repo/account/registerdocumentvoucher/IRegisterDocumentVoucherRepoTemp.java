package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.registerdocumentvoucher;

import com.saas.tourismagency.admin.backoffice.domain.account.RegisterDocumentVoucherDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

public interface IRegisterDocumentVoucherRepoTemp {

    public PageDTO<RegisterDocumentVoucherDomain> query(Pageable pageable, Query query);

}
