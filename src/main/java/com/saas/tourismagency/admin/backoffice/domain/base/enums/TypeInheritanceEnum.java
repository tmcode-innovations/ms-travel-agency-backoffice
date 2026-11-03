package com.saas.tourismagency.admin.backoffice.domain.base.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum TypeInheritanceEnum implements EnumMappeable<TypeInheritanceEnum.TypeInheritanceModel> {

    CUSTOMER("1", "Customer"),
    SUPPLIER_LODGING("2", "Supplier Lodging"),
    SUPPLIER_RESTAURANT("3", "Supplier Restaurant"),
    SUPPLIER_GUIDE("4", "Supplier Guide"),
    SUPPLIER_TRANSPORT("5", "Supplier Transport"),
    SUPPLIER_OPERATOR("6", "Supplier Operator"),
    SUPPLIER_AIR_LINE("7", "Supplier Air Line"),
    SUPPLIER_LODGE("8", "Supplier Lodge"),
    SUPPLIER_ADMINISTRATIVE("9", "Supplier Administrative"),
    SUPPLIER_GLAMPING("10", "Supplier Glamping"),
    SUPPLIER_INSTITUTION("11", "Supplier Institution");

    public record TypeInheritanceModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name) {

    }

    private final String id;
    private final String name;

    private TypeInheritanceEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public TypeInheritanceEnum.TypeInheritanceModel map() {
        return new TypeInheritanceEnum.TypeInheritanceModel(id, name);
    }

    public static TypeInheritanceEnum resolve(String id) {
        for (TypeInheritanceEnum value : TypeInheritanceEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No type inheritance found with id '%s'.".formatted(id));
    }

}
