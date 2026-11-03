package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.ChangeRequestCommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeRequestCommentMongoRepo extends MongoRepository<ChangeRequestCommentEntity, String> {

    ChangeRequestCommentEntity findByNameAndTenant(String name, String tenant);

    ChangeRequestCommentEntity findByIdentifyAndTenant(String identify, String tenant);

    ChangeRequestCommentEntity findByNameAndTenantAndDeleted(String name, String tenant, Boolean deleted);

    ChangeRequestCommentEntity findByIdentifyAndTenantAndDeleted(String identify, String tenant, Boolean deleted);
}
