package com.saas.tourismagency.admin.backoffice.domain.booking.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum TypePassengerTcBookingEnum implements EnumMappeable<TypePassengerTcBookingEnum.TypePassengerTcBookingModel> {

    ADULT("1", "Adult", "Adulto"),
    CHILD("2", "Child", "Niño"),
    FREE("3", "Free", "Free"),
    TC("4", "Tc", "Tc"),
    TC_LOCAL("5", "Tc Local", "Tc Local"),
    GUIDE("6", "Guide", "Guia"),
    STUDENT("7", "Student", "Estudiante");

    public record TypePassengerTcBookingModel(
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

    private TypePassengerTcBookingEnum(String id, String name, String description) {
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
    public TypePassengerTcBookingModel map() {
        return new TypePassengerTcBookingModel(id, name, description);
    }

    public static TypePassengerTcBookingEnum resolve(String id) {
        for (TypePassengerTcBookingEnum value : TypePassengerTcBookingEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No type passenger booking found with id '%s'.".formatted(id));
    }

}
