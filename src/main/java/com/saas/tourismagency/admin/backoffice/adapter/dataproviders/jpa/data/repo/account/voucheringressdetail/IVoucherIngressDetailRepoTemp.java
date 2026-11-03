package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringressdetail;

import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IVoucherIngressDetailRepoTemp {

    public PageDTO<VoucherIngressDetailDomain> query(Pageable pageable, Query query);

    public List<VoucherIngressDetailDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<VoucherIngressDetailDomain> queryAllSimpleDomainCaseShowVoucherIngressDetail(Query query);

    public PageDTO<VoucherIngressDetailDomain> queryAllSimpleDomainCaseListVoucherIngressDetail(Pageable pageable, Query query);

    public List<VoucherIngressDetailDomain> queryAllSimpleDomainCaseShowVoucherIngressDetailByRegisterGroup(Query query);

    public List<VoucherIngressDetailDomain> queryAllSimpleDomainCaseShowVoucherIngressDetailByDelete(Query query);

}
