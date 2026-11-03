package com.saas.tourismagency.admin.backoffice.domain.maintenance.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum ServiceGroupEnum implements EnumMappeable<ServiceGroupEnum.ServiceGroupModel> {

    TRADITIONAL_GROUP("1", "Traditional Group", "Servicios Tradicionales"),
    LODGING("2", "Lodging", "Servicios de Alojamientos"),
    TRADITIONAL_GROUP_AND_LODGING("3", "Traditional Group and Lodging", "Servicios Tradicionales y Alojamientos");

    public record ServiceGroupModel(
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

    private ServiceGroupEnum(String id, String name, String description) {
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
    public ServiceGroupEnum.ServiceGroupModel map() {
        return new ServiceGroupEnum.ServiceGroupModel(id, name, description);
    }

    public static ServiceGroupEnum resolve(String id) {
        for (ServiceGroupEnum value : ServiceGroupEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No service group found with id '%s'.".formatted(id));
    }

}
