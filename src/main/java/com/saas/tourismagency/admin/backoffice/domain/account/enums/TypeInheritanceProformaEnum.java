package com.saas.tourismagency.admin.backoffice.domain.account.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum TypeInheritanceProformaEnum implements EnumMappeable<TypeInheritanceProformaEnum.TypeInheritanceProformaModel> {

    PROFORMA_INVOICE("1", "Proforma Invoice"),
    PROFORMA_NOTE_CREDIT("2", "Proforma Note Credit");


    public record TypeInheritanceProformaModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name) {

    }

    private final String id;
    private final String name;

    private TypeInheritanceProformaEnum(String id, String name) {
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
    public TypeInheritanceProformaEnum.TypeInheritanceProformaModel map() {
        return new TypeInheritanceProformaEnum.TypeInheritanceProformaModel(id, name);
    }

    public static TypeInheritanceProformaEnum resolve(String id) {
        for (TypeInheritanceProformaEnum value : TypeInheritanceProformaEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No type inheritance proforma found with id '%s'.".formatted(id));
    }

}
