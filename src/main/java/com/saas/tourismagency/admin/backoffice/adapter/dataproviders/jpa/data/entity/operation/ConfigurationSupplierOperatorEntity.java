package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierOperatorEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "operation_configuration_supplier_operators")
public class ConfigurationSupplierOperatorEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private @DocumentReference(lazy = true)
    SupplierOperatorEntity supplierOperator;

    private Boolean active;
}
