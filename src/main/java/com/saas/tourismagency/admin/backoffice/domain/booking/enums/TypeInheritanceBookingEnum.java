package com.saas.tourismagency.admin.backoffice.domain.booking.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum TypeInheritanceBookingEnum implements EnumMappeable<TypeInheritanceBookingEnum.TypeInheritanceBookingModel> {

    REGISTER_BOOKING_LODGING("1", "Booking Lodging"),
    REGISTER_BOOKING_LODGE("2", "Booking Lodge"),
    REGISTER_BOOKING_OPERATOR("3", "Booking Operator"),
    REGISTER_BOOKING_GLAMPING("4", "Booking Glamping"),
    REGISTER_BOOKING_RESTAURANT("5", "Booking Restaurant"),
    REGISTER_BOOKING_INSTITUTION("6", "Booking Institution");

    public record TypeInheritanceBookingModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name) {

    }

    private final String id;
    private final String name;

    private TypeInheritanceBookingEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public TypeInheritanceBookingEnum.TypeInheritanceBookingModel map() {
        return new TypeInheritanceBookingEnum.TypeInheritanceBookingModel(id, name);
    }

    public static TypeInheritanceBookingEnum resolve(String id) {
        for (TypeInheritanceBookingEnum value : TypeInheritanceBookingEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No type inheritance booking found with id '%s'.".formatted(id));
    }

}
