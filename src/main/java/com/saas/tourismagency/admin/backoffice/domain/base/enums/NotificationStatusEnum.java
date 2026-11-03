package com.saas.tourismagency.admin.backoffice.domain.base.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum NotificationStatusEnum implements EnumMappeable<NotificationStatusEnum.NotificationStatusModel> {

    DRAFT("1", "Draft", "Nueva"),
    WITHOUT_VIEW("2", "Without View", "Sin Ver"),
    VIEW("3", "View", "Vista"),
    DELETED("4", "Deleted", "Eliminada");

    public record NotificationStatusModel(
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

    private NotificationStatusEnum(String id, String name, String description) {
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
    public NotificationStatusEnum.NotificationStatusModel map() {
        return new NotificationStatusEnum.NotificationStatusModel(id, name, description);
    }

    public static NotificationStatusEnum resolve(String id) {
        for (NotificationStatusEnum value : NotificationStatusEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No notification status found with id '%s'.".formatted(id));
    }

}
