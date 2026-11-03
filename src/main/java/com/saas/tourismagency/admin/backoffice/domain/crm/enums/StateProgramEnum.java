package com.saas.tourismagency.admin.backoffice.domain.crm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum StateProgramEnum implements EnumMappeable<StateProgramEnum.StateProgramModel> {

    DRAFT("1", "Draft", "Borrador"),
    CANCEL("2", "Cancel", "Cancelado"),
    COMPLETED("3", "Completed", "Completado");

    public record StateProgramModel(
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

    private StateProgramEnum(String id, String name, String description) {
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
    public StateProgramEnum.StateProgramModel map() {
        return new StateProgramEnum.StateProgramModel(id, name, description);
    }

    public static StateProgramEnum resolve(String id) {
        for (StateProgramEnum value : StateProgramEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No state program found with id '%s'.".formatted(id));
    }

}
