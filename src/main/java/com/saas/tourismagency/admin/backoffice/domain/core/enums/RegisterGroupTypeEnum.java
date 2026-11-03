package com.saas.tourismagency.admin.backoffice.domain.core.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum RegisterGroupTypeEnum implements EnumMappeable<RegisterGroupTypeEnum.RegisterGroupTypeModel> {

    TYPE_LEGENDARY("1", "Legendary", "Formato Legendary (Referencia)"),
    TYPE_GURU_EXPLORER("2", "Guru Explorers", "Formato Guru Explorers (Referencia)");

    public record RegisterGroupTypeModel(
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

    private RegisterGroupTypeEnum(String id, String name, String description) {
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
    public RegisterGroupTypeEnum.RegisterGroupTypeModel map() {
        return new RegisterGroupTypeEnum.RegisterGroupTypeModel(id, name, description);
    }

    public static RegisterGroupTypeEnum resolve(String id) {
        for (RegisterGroupTypeEnum value : RegisterGroupTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No quotation type found with id '%s'.".formatted(id));
    }

}
