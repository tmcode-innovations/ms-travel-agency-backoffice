package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.accountpayment;

import com.saas.tourismagency.admin.backoffice.domain.account.AccountPaymentDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IAccountPaymentRepoTemp {

    public PageDTO<AccountPaymentDomain> query(Pageable pageable, Query query);

    public List<AccountPaymentDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<AccountPaymentDomain> queryAllSimpleDomainCaseShowAccountPayment(Query query);

    public PageDTO<AccountPaymentDomain> queryAllSimpleDomainCaseListAccountPayment(Pageable pageable, Query query);

    public List<AccountPaymentDomain> queryByProformaIdFullData(Query query);
}
