package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CurrencyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyMongoRepo extends MongoRepository<CurrencyEntity, String> {

    CurrencyEntity findByNameAndTenant(String name, String tenant);

    CurrencyEntity findByIdentifyAndTenant(String identify, String tenant);

}
