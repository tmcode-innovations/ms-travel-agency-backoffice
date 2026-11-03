package com.saas.tourismagency.admin.backoffice.domain.base.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum GenderEnum implements EnumMappeable<GenderEnum.GenderModel> {

    FEMALE("1", "Female", "Femenino"),
    MALE("2", "Male", "Masculino");

    public record GenderModel(
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

    private GenderEnum(String id, String name, String description) {
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
    public GenderEnum.GenderModel map() {
        return new GenderEnum.GenderModel(id, name, description);
    }

    public static GenderEnum resolve(String id) {
        for (GenderEnum value : GenderEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No gender found with id '%s'.".formatted(id));
    }

}
