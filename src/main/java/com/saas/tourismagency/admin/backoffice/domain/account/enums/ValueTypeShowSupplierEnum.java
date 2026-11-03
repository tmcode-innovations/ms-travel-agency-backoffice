package com.saas.tourismagency.admin.backoffice.domain.account.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum ValueTypeShowSupplierEnum implements EnumMappeable<ValueTypeShowSupplierEnum.ValueTypeShowSupplierModel> {

    BOOKIG("1", "Booking", "Reserva"),
    ADMINISTRATIVE("2", "Administrative", "Administrativo"),
    FREE("3", "Free", "Libre");

    public record ValueTypeShowSupplierModel(
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

    private ValueTypeShowSupplierEnum(String id, String name, String description) {
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
    public ValueTypeShowSupplierModel map() {
        return new ValueTypeShowSupplierModel(id, name, description);
    }

    public static ValueTypeShowSupplierEnum resolve(String id) {
        for (ValueTypeShowSupplierEnum value : ValueTypeShowSupplierEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No value type show supplier found with id '%s'.".formatted(id));
    }

}
