package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.PersistConsecutiveNumberEntity;
import dev.mm.javaee.core.repo.DefaultJPARepository;
import org.springframework.stereotype.Service;

@Service
public class PersistConsecutiveNumberRepo extends DefaultJPARepository<PersistConsecutiveNumberEntity, String, PersistConsecutiveNumberMongoRepo> {

    public PersistConsecutiveNumberRepo(PersistConsecutiveNumberMongoRepo persistConsecutiveNumberMongoRepo) {
        super(persistConsecutiveNumberMongoRepo);
    }

}
