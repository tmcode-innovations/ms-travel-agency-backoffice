package com.saas.tourismagency.admin.backoffice.domain.operation.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum RequestOrderServiceStateEnum implements EnumMappeable<RequestOrderServiceStateEnum.RequestOrderServiceStateModel> {

    DRAFT("1", "Draft", "Nueva"),
    IN_PROGRESS("2", "In Progress", "En Proceso"),
    FINISHED("3", "Finished", "Finalizada"),
    CANCELLED("4", "Cancelled", "Cancelada");

    public record RequestOrderServiceStateModel(
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

    private RequestOrderServiceStateEnum(String id, String name, String description) {
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
    public RequestOrderServiceStateModel map() {
        return new RequestOrderServiceStateModel(id, name, description);
    }

    public static RequestOrderServiceStateEnum resolve(String id) {
        for (RequestOrderServiceStateEnum value : RequestOrderServiceStateEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No request order service state found with id '%s'.".formatted(id));
    }

}
