package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringress;

import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IVoucherIngressRepoTemp {

    public PageDTO<VoucherIngressDomain> query(Pageable pageable, Query query);

    public List<VoucherIngressDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<VoucherIngressDomain> queryAllSimpleDomainCaseShowVoucherIngress(Query query);

    public PageDTO<VoucherIngressDomain> queryAllSimpleDomainCaseListVoucherIngress(Pageable pageable, Query query);

    public VoucherIngressDomain queryOneIdSimpleDomainCaseGetModificationData(Query query);

    public void updateIdSimpleDomainCaseUpdateVoucherIngressBasic(Query query, Update update);
}
