package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DateProcessEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DateProcessMongoRepo extends MongoRepository<DateProcessEntity, String> {

    DateProcessEntity findByDateAndTenantAndDeleted(LocalDate date, String tenant, Boolean deleted);

}
