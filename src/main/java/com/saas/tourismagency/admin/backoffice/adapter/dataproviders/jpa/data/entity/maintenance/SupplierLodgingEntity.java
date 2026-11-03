package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "base_entities")
@TypeAlias("SupplierLodgingEntityData")
public class SupplierLodgingEntity extends SupplierEntity implements Serializable {

    private String lodgingType;

    private @DocumentReference(lazy = true)
    LodgingCategoryEntity lodgingCategory;

}
