package com.saas.tourismagency.admin.backoffice.domain.core.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum GeneralConfigurationTypeEnum implements EnumMappeable<GeneralConfigurationTypeEnum.GeneralConfigurationTypeModel> {

    TYPE_LEGENDARY("1", "Legendary", "Formato Legendary (Referencia)"),
    TYPE_GURU_EXPLORER("2", "Guru Explorers", "Formato Guru Explorers (Referencia)");

    public record GeneralConfigurationTypeModel(
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

    private GeneralConfigurationTypeEnum(String id, String name, String description) {
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
    public GeneralConfigurationTypeEnum.GeneralConfigurationTypeModel map() {
        return new GeneralConfigurationTypeEnum.GeneralConfigurationTypeModel(id, name, description);
    }

    public static GeneralConfigurationTypeEnum resolve(String id) {
        for (GeneralConfigurationTypeEnum value : GeneralConfigurationTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No quotation type found with id '%s'.".formatted(id));
    }

}
