package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetailitem;

import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IVoucherEgressDetailItemRepoTemp {

    public PageDTO<VoucherEgressDetailItemDomain> query(Pageable pageable, Query query);

    public List<VoucherEgressDetailItemDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<VoucherEgressDetailItemDomain> queryAllSimpleDomainCaseShowVoucherEgressDetailItem(Query query);

    public PageDTO<VoucherEgressDetailItemDomain> queryAllSimpleDomainCaseListVoucherEgressDetailItem(Pageable pageable, Query query);

    public List<VoucherEgressDetailItemDomain> queryAllSimpleDomainCaseNoItemVoucherEgressDetailItem(Query query);

    public List<VoucherEgressDetailItemDomain> queryAllSimpleDomainCaseShowVoucherEgressDetailItemByDelete(Query query);

    public List<VoucherEgressDetailItemDomain> queryAllSimpleDomainCaseUpdateCalculateByRegisterGroup(Query query);
}
