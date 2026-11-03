package com.saas.tourismagency.admin.backoffice.domain.operation.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum OrderServiceStateEnum implements EnumMappeable<OrderServiceStateEnum.OrderServiceStateModel> {

    DRAFT("1", "Draft", "Nueva"),
    CONFIRMED("2", "Confirmed", "Confirmado"),
    CANCELLED("3", "Cancelled", "Cancelada");

    public record OrderServiceStateModel(
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

    private OrderServiceStateEnum(String id, String name, String description) {
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
    public OrderServiceStateModel map() {
        return new OrderServiceStateModel(id, name, description);
    }

    public static OrderServiceStateEnum resolve(String id) {
        for (OrderServiceStateEnum value : OrderServiceStateEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No order service state found with id '%s'.".formatted(id));
    }

}
