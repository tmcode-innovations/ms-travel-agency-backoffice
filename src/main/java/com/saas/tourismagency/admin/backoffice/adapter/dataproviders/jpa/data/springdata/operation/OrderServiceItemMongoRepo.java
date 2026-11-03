package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.operation;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.OrderServiceItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceItemMongoRepo extends MongoRepository<OrderServiceItemEntity, String> {


}
