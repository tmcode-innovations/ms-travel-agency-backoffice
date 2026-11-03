package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregress;

import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IVoucherEgressRepoTemp {

    public PageDTO<VoucherEgressDomain> query(Pageable pageable, Query query);

    public List<VoucherEgressDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<VoucherEgressDomain> queryAllSimpleDomainCaseShowVoucherEgress(Query query);

    public PageDTO<VoucherEgressDomain> queryAllSimpleDomainCaseListVoucherEgress(Pageable pageable, Query query);

    public VoucherEgressDomain queryOneIdSimpleDomainCaseGetModificationData(Query query);

    public void updateIdSimpleDomainCaseUpdateVoucherEgressBasic(Query query, Update update);

    public List<VoucherEgressDomain> queryAllSimpleDomainCaseVoucherEgressByParent(Query query);

    public List<VoucherEgressDomain> queryAllPending(Query query);

}
