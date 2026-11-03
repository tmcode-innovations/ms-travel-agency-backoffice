package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.partner;

import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

public interface IPartnerRepositoryTemplate {

    public PageDTO<PartnerDomain> query(Pageable pageable, Query query);

}
