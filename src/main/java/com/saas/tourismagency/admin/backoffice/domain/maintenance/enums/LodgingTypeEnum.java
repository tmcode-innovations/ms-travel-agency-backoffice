package com.saas.tourismagency.admin.backoffice.domain.maintenance.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum LodgingTypeEnum implements EnumMappeable<LodgingTypeEnum.LodgingTypeModel> {

    HOTEL("1", "Hotel", "Hotel"),
    APART_HOTEL("2", "Apart Hotel", "Apart Hoteles"),
    LODGING_HOUSE("3", "Lodging House", "Hostales"),
    RESORTS("4", "Resorts", "Resorts"),
    ECOLODGES("5", "Ecolodges", "Ecolodges"),
    HOSTELS("6", "Hostels", "Alberges"),
    BASIC_ACCOMMODATION("7", "Basic Accommodation", "Alojamiento Basico"),
    BOUTIQUE("8", "Boutique", "Boutique");

    public record LodgingTypeModel(
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

    private LodgingTypeEnum(String id, String name, String description) {
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
    public LodgingTypeEnum.LodgingTypeModel map() {
        return new LodgingTypeEnum.LodgingTypeModel(id, name, description);
    }

    public static LodgingTypeEnum resolve(String id) {
        for (LodgingTypeEnum value : LodgingTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No lodging type found with id '%s'.".formatted(id));
    }

}
