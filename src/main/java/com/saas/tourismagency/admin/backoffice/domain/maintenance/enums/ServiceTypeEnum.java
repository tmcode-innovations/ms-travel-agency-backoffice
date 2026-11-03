package com.saas.tourismagency.admin.backoffice.domain.maintenance.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum ServiceTypeEnum implements EnumMappeable<ServiceTypeEnum.ServiceTypeModel> {

    TRADITIONAL("1", "Traditional", "Tradicional"),
    ROOM("2", "Room", "Habitación"),
    FEEDING("3", "Feeding", "Alimentación"),
    EXTRA("4", "Extra", "Extra");

    public record ServiceTypeModel(
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

    private ServiceTypeEnum(String id, String name, String description) {
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
    public ServiceTypeEnum.ServiceTypeModel map() {
        return new ServiceTypeEnum.ServiceTypeModel(id, name, description);
    }

    public static ServiceTypeEnum resolve(String id) {
        for (ServiceTypeEnum value : ServiceTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No service type found with id '%s'.".formatted(id));
    }

}
