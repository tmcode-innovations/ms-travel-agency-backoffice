package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.PersistConsecutiveNumberEntity;
import org.springframework.data.mongodb.core.query.Query;

public interface PersistConsecutiveMongoTemplate {

    public PersistConsecutiveNumberEntity query(Query query);

}
