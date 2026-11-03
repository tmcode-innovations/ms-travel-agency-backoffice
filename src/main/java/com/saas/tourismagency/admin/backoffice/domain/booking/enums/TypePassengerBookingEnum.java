package com.saas.tourismagency.admin.backoffice.domain.booking.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum TypePassengerBookingEnum implements EnumMappeable<TypePassengerBookingEnum.TypePassengerBookingModel> {

    PAX("1", "Pax", "PAX"),
    TC("2", "TC", "TC"),
    TC_LOCAL("3", "TC LOCAL", "TC LOCAL"),
    GUIDE("4", "Guide", "GUIA");

    public record TypePassengerBookingModel(
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

    private TypePassengerBookingEnum(String id, String name, String description) {
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
    public TypePassengerBookingModel map() {
        return new TypePassengerBookingModel(id, name, description);
    }

    public static TypePassengerBookingEnum resolve(String id) {
        for (TypePassengerBookingEnum value : TypePassengerBookingEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No type passenger booking found with id '%s'.".formatted(id));
    }

}
