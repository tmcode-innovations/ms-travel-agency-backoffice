package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CreditNoteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditNoteMongoRepo extends MongoRepository<CreditNoteEntity, String> {


}
