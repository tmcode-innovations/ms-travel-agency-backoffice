package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetail;

import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IVoucherEgressDetailRepoTemp {

    public PageDTO<VoucherEgressDetailDomain> query(Pageable pageable, Query query);

    public List<VoucherEgressDetailDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<VoucherEgressDetailDomain> queryAllSimpleDomainCaseShowVoucherEgressDetail(Query query);

    public PageDTO<VoucherEgressDetailDomain> queryAllSimpleDomainCaseListVoucherEgressDetail(Pageable pageable, Query query);

    public VoucherEgressDetailDomain queryOneIdSimpleDomainCaseGetModificationData(Query query);

    public void updateIdSimpleDomainCaseUpdateVoucherEgressDetailBasic(Query query, Update update);

    public List<VoucherEgressDetailDomain> queryAllSimpleDomainCaseNoItemVoucherEgressDetail(Query query);

    public List<VoucherEgressDetailDomain> queryAllSimpleDomainCaseShowVoucherEgressDetailByDelete(Query query);

    public List<VoucherEgressDetailDomain> queryAllSimpleDomainCaseExportPdfByVoucherEgress(Query query);
}
