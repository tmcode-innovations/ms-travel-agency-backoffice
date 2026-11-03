package com.saas.tourismagency.admin.backoffice.domain.booking.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum TypeRoomBookingEnum implements EnumMappeable<TypeRoomBookingEnum.TypeRoomBookingModel> {

    SWB("1", "Swb", "Swb"),
    TWIN("2", "Twin", "Twin"),
    MAT("3", "Mat", "Mat"),
    TRP("4", "Trp", "Trp"),
    CDP("5", "Cdp", "Cdp"),
    QTP("6", "Qtp", "Qtp"),
    SXTP("7", "Sxtp", "Sxtp"),
    SWB_TC("8", "Swb TC", "Swb TC"),
    TWIN_TC("9", "Twin TC", "Twin TC"),
    SWB_TC_LOCAL("10", "Swb TC Local", "Swb TC"),
    TWIN_TC_LOCAL("11", "Twin TC Local", "Twin TC Local"),
    SWB_GUIDE("12", "Swb Guide", "Swb Guide"),
    TWIN_GUIDE("13", "Twin Guide", "Twin Guide");

    public record TypeRoomBookingModel(
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

    private TypeRoomBookingEnum(String id, String name, String description) {
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
    public TypeRoomBookingModel map() {
        return new TypeRoomBookingModel(id, name, description);
    }

    public static TypeRoomBookingEnum resolve(String id) {
        for (TypeRoomBookingEnum value : TypeRoomBookingEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No type room booking found with id '%s'.".formatted(id));
    }

    public static TypeRoomBookingEnum resolveByName(String name) {
        for (TypeRoomBookingEnum value : TypeRoomBookingEnum.values()) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        throw new NotFoundException("No type room booking found with name '%s'.".formatted(name));
    }

}
