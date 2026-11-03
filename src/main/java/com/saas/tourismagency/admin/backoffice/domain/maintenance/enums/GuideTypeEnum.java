package com.saas.tourismagency.admin.backoffice.domain.maintenance.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum GuideTypeEnum implements EnumMappeable<GuideTypeEnum.GuideTypeModel> {

    GUIDE("1", "Guide", "Guia"),
    TRANSFER_DRIVER("2", "Transfer Driver", "Trasladista"),
    DRIVER("3", "Driver", "Chofer");

    public record GuideTypeModel(
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

    private GuideTypeEnum(String id, String name, String description) {
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
    public GuideTypeEnum.GuideTypeModel map() {
        return new GuideTypeEnum.GuideTypeModel(id, name, description);
    }

    public static GuideTypeEnum resolve(String id) {
        for (GuideTypeEnum value : GuideTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No guide type found with id '%s'.".formatted(id));
    }

}
