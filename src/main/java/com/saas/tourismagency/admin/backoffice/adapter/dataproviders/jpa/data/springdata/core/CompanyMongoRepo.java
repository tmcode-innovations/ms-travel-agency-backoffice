package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.CompanyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyMongoRepo extends MongoRepository<CompanyEntity, String> {

    CompanyEntity findByName(String name);

    CompanyEntity findByIdentify(String identify);

    CompanyEntity findByTenant(String tenant);

    CompanyEntity findByNameAndTenant(String name, String tenant);

    CompanyEntity findByIdentifyAndTenant(String identify, String tenant);

    CompanyEntity findFirstByTenant(String tenant);

}
