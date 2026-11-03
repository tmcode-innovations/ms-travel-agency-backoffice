package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherIngressEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherIngressMongoRepo extends MongoRepository<VoucherIngressEntity, String> {


}
