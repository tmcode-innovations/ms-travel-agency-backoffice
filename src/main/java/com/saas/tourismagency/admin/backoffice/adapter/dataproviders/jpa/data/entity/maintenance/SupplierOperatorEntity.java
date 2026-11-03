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
@AllArgsConstructor
@Document(collection = "base_entities")
@TypeAlias("SupplierOperatorEntityData")
public class SupplierOperatorEntity extends SupplierEntity implements Serializable {

    private String supplierTypeOperator;

    private Integer availableOrderService;

}
