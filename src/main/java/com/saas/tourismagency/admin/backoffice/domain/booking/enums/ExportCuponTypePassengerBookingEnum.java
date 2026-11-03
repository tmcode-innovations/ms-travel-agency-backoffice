package com.saas.tourismagency.admin.backoffice.domain.booking.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum ExportCuponTypePassengerBookingEnum implements EnumMappeable<ExportCuponTypePassengerBookingEnum.ExportCuponTypePassengerBookingModel> {

    PASSENGER_ONLY_TC_LOCAL("1", "Only TC Local", "Solo TC Local"),
    PASSENGER_ALL_WITHOUT_TC_LOCAL("2", "Without TC Local", "Sin TC Local");

    public record ExportCuponTypePassengerBookingModel(
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

    private ExportCuponTypePassengerBookingEnum(String id, String name, String description) {
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
    public ExportCuponTypePassengerBookingModel map() {
        return new ExportCuponTypePassengerBookingModel(id, name, description);
    }

    public static ExportCuponTypePassengerBookingEnum resolve(String id) {
        for (ExportCuponTypePassengerBookingEnum value : ExportCuponTypePassengerBookingEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No export cupon type passenger found with id '%s'.".formatted(id));
    }

}
