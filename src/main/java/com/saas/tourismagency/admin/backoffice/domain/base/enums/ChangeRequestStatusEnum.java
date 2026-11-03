package com.saas.tourismagency.admin.backoffice.domain.base.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum ChangeRequestStatusEnum implements EnumMappeable<ChangeRequestStatusEnum.ChangeRequestStatusModel> {

    DRAFT("1", "Draft", "Nueva"),
    SENT("2", "Sent", "Enviada"),
    ACCEPTED("3", "Accepted", "Aceptada"),
    ATTENDED("3", "Attended", "Atendida"),
    REFUSED("4", "Refused", "Rechazada"),
    COMPLETED("5", "Completed", "Finalizada"),
    REVIEW("6", "Review", "Revisión");

    public record ChangeRequestStatusModel(
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

    private ChangeRequestStatusEnum(String id, String name, String description) {
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
    public ChangeRequestStatusEnum.ChangeRequestStatusModel map() {
        return new ChangeRequestStatusEnum.ChangeRequestStatusModel(id, name, description);
    }

    public static ChangeRequestStatusEnum resolve(String id) {
        for (ChangeRequestStatusEnum value : ChangeRequestStatusEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No change request status found with id '%s'.".formatted(id));
    }

}
