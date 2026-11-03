package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "base_entities")
@TypeAlias("SupplierAirLineEntityData")
public class SupplierAirLineEntity extends SupplierEntity implements Serializable {


}
