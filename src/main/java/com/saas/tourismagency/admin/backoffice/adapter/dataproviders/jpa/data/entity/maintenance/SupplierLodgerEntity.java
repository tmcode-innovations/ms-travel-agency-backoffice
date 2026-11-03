package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "base_entities")
@TypeAlias("SupplierLodgerEntityData")
public class SupplierLodgerEntity extends SupplierEntity implements Serializable {

    private String lodgingType;
    private String supplierTypeOperator;

    private @DocumentReference(lazy = true)
    LodgingCategoryEntity lodgingCategory;

}
