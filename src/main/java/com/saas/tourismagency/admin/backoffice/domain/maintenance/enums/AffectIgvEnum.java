package com.saas.tourismagency.admin.backoffice.domain.maintenance.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum AffectIgvEnum implements EnumMappeable<AffectIgvEnum.AffectIgvModel> {

    AFFECT_IGV("1", "Affect Igv", "Afecto al Igv"),
    INAFFECT_IGV("2", "Inaffect Igv", "Inafecto al Igv");

    public record AffectIgvModel(
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

    private AffectIgvEnum(String id, String name, String description) {
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
    public AffectIgvEnum.AffectIgvModel map() {
        return new AffectIgvEnum.AffectIgvModel(id, name, description);
    }

    public static AffectIgvEnum resolve(String id) {
        for (AffectIgvEnum value : AffectIgvEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No affect igv found with id '%s'.".formatted(id));
    }

}
