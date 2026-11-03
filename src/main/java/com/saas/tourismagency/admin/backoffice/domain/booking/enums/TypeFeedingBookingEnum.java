package com.saas.tourismagency.admin.backoffice.domain.booking.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum TypeFeedingBookingEnum implements EnumMappeable<TypeFeedingBookingEnum.TypeFeedingBookingModel> {

    BREAKFAST_BUFFET("1", "Breakfast Buffet", "Desayuno Buffet"),
    LUNCH("2", "Lunch", "Almuerzo"),
    DINNER("3", "Dinner", "Cena"),
    BOX_LUNCH("4", "Box Lunch", "Box Lunch");

    public record TypeFeedingBookingModel(
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

    private TypeFeedingBookingEnum(String id, String name, String description) {
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
    public TypeFeedingBookingModel map() {
        return new TypeFeedingBookingModel(id, name, description);
    }

    public static TypeFeedingBookingEnum resolve(String id) {
        for (TypeFeedingBookingEnum value : TypeFeedingBookingEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No type feeding booking found with id '%s'.".formatted(id));
    }

}
