package com.saas.tourismagency.admin.backoffice.domain.maintenance.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum BookingClassificationEnum implements EnumMappeable<BookingClassificationEnum.BookingClassificationModel> {

    SERIES("1", "Series"),
    FITS("2", "Fits"),
    GROUPS("3", "Groups");

    public record BookingClassificationModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name) {

    }

    private final String id;
    private final String name;

    private BookingClassificationEnum(String id, String name) {
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
    public BookingClassificationEnum.BookingClassificationModel map() {
        return new BookingClassificationEnum.BookingClassificationModel(id, name);
    }

    public static BookingClassificationEnum resolve(String id) {
        for (BookingClassificationEnum value : BookingClassificationEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No booking classification found with id '%s'.".formatted(id));
    }

}
