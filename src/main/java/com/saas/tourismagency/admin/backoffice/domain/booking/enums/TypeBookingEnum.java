package com.saas.tourismagency.admin.backoffice.domain.booking.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum TypeBookingEnum implements EnumMappeable<TypeBookingEnum.TypeBookingModel> {

    BOOKING_LODGING("1", "Booking Lodging"),
    BOOKING_LODGE("2", "Booking Lodge"),
    BOOKING_OPERATOR("3", "Booking Operator"),
    BOOKING_GLAMPING("4", "Booking Glamping"),
    BOOKING_RESTAURANT("5", "Booking Restaurant"),
    BOOKING_INSTITUTION("6", "Booking Institution");

    public record TypeBookingModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name) {

    }

    private final String id;
    private final String name;

    private TypeBookingEnum(String id, String name) {
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
    public TypeBookingEnum.TypeBookingModel map() {
        return new TypeBookingEnum.TypeBookingModel(id, name);
    }

    public static TypeBookingEnum resolve(String id) {
        for (TypeBookingEnum value : TypeBookingEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No type booking found with id '%s'.".formatted(id));
    }

}
