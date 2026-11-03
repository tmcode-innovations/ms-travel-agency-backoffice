package com.saas.tourismagency.admin.backoffice.domain.operation.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum OrderServiceTypeEnum implements EnumMappeable<OrderServiceTypeEnum.OrderServiceTypeModel> {

    BOOKING("1", "Booking", "Reservas"),
    OPERATION("2", "Operation", "Operaciones");

    public record OrderServiceTypeModel(
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

    private OrderServiceTypeEnum(String id, String name, String description) {
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
    public OrderServiceTypeModel map() {
        return new OrderServiceTypeModel(id, name, description);
    }

    public static OrderServiceTypeEnum resolve(String id) {
        for (OrderServiceTypeEnum value : OrderServiceTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No order service type found with id '%s'.".formatted(id));
    }

}
