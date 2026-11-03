package com.saas.tourismagency.admin.backoffice.domain.maintenance.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum SupplierTypeOperatorEnum implements EnumMappeable<SupplierTypeOperatorEnum.SupplierTypeOperatorModel> {

    OPERATOR_NATIONAL("1", "Operator National", "Operador Nacional"),
    OPERATOR_INTERNATIONAL("2", "Operator International", "Operador Internacional");

    public record SupplierTypeOperatorModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name,
            @JsonProperty("description")
            String description) {

    }

    private final String id;
    private final String name;
    private final String description;


    private SupplierTypeOperatorEnum(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public SupplierTypeOperatorEnum.SupplierTypeOperatorModel map() {
        return new SupplierTypeOperatorEnum.SupplierTypeOperatorModel(id, name, description);
    }

    public static SupplierTypeOperatorEnum resolve(String id) {
        for (SupplierTypeOperatorEnum value : SupplierTypeOperatorEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No supplier type operator found with id '%s'.".formatted(id));
    }

}
