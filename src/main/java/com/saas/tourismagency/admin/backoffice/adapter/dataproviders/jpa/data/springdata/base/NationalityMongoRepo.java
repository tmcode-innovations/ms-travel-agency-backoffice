package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.NationalityEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalityMongoRepo extends MongoRepository<NationalityEntity, String> {

    NationalityEntity findByNameAndTenant(String name, String tenant);

    NationalityEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

}
