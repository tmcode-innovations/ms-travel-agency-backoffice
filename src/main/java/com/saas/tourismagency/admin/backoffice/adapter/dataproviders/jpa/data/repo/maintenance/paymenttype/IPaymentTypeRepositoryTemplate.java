package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.paymenttype;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PaymentTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IPaymentTypeRepositoryTemplate {

    public PageDTO<PaymentTypeDomain> query(Pageable pageable, Query query);

    public List<PaymentTypeDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<PaymentTypeDomain> queryAllSimpleDomainCaseListPaymentType(Pageable pageable, Query query);
}
