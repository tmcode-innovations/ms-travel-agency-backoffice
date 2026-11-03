package com.saas.tourismagency.admin.backoffice.domain.booking.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum GeneratedRegisterBookingOperatorItemEnum implements EnumMappeable<GeneratedRegisterBookingOperatorItemEnum.GeneratedRegisterBookingOperatorItemModel> {

    BOOKING("1", "Booking"),
    OPERATION("2", "Operation");

    public record GeneratedRegisterBookingOperatorItemModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name) {

    }

    private final String id;
    private final String name;

    private GeneratedRegisterBookingOperatorItemEnum(String id, String name) {
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
    public GeneratedRegisterBookingOperatorItemEnum.GeneratedRegisterBookingOperatorItemModel map() {
        return new GeneratedRegisterBookingOperatorItemEnum.GeneratedRegisterBookingOperatorItemModel(id, name);
    }

    public static GeneratedRegisterBookingOperatorItemEnum resolve(String id) {
        for (GeneratedRegisterBookingOperatorItemEnum value : GeneratedRegisterBookingOperatorItemEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No generated booking operator item found with id '%s'.".formatted(id));
    }

}
