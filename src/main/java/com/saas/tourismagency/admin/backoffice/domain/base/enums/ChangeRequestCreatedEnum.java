package com.saas.tourismagency.admin.backoffice.domain.base.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum ChangeRequestCreatedEnum implements EnumMappeable<ChangeRequestCreatedEnum.ChangeRequestCreatedModel> {

    QUOTATION("1", "Quotation", "Cotización"),
    REGISTER_GROUP("2", "Register Group", "Registro de Grupo");

    public record ChangeRequestCreatedModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name,
            @JsonProperty("description")
            String description
            ) {

    }

    private final String id;
    private final String name;
    private final String description;

    private ChangeRequestCreatedEnum(String id, String name, String description) {
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
    public ChangeRequestCreatedEnum.ChangeRequestCreatedModel map() {
        return new ChangeRequestCreatedEnum.ChangeRequestCreatedModel(id, name, description);
    }

    public static ChangeRequestCreatedEnum resolve(String id) {
        for (ChangeRequestCreatedEnum value : ChangeRequestCreatedEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No change request created found with id '%s'.".formatted(id));
    }

}
