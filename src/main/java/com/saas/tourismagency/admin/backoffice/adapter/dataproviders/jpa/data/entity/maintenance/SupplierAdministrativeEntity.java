package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.EntityBaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PartnerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "base_entities")
@TypeAlias("SupplierAdministrativeEntityData")
public class SupplierAdministrativeEntity extends SupplierEntity implements Serializable {


}
