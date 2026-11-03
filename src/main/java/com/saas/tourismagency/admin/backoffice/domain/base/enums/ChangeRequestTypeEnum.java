package com.saas.tourismagency.admin.backoffice.domain.base.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum ChangeRequestTypeEnum implements EnumMappeable<ChangeRequestTypeEnum.ChangeRequestTypeModel> {

    ADD("1", "Add", "Adición"),
    CHANGE("2", "Change", "Cambio"),
    ADD_CHANGE("3", "Add_Change", "Adición / Cambio");

    public record ChangeRequestTypeModel(
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

    private ChangeRequestTypeEnum(String id, String name, String description) {
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
    public ChangeRequestTypeEnum.ChangeRequestTypeModel map() {
        return new ChangeRequestTypeEnum.ChangeRequestTypeModel(id, name, description);
    }

    public static ChangeRequestTypeEnum resolve(String id) {
        for (ChangeRequestTypeEnum value : ChangeRequestTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No change request type found with id '%s'.".formatted(id));
    }

}
