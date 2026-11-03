package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum ModeUpdateServiceAdvancedEnum implements EnumMappeable<ModeUpdateServiceAdvancedEnum.ModeUpdateServiceAdvancedModel> {

    NEW_SUPPLIER("1", "New Supplier"),
    SUPPLIER_WITH_CHANGE("2", "Supplier With Change"),
    SUPPLIER_WITHOUT_CHANGE("3", "Supplier Without Change");

    public record ModeUpdateServiceAdvancedModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name) {

    }

    private final String id;
    private final String name;

    private ModeUpdateServiceAdvancedEnum(String id, String name) {
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
    public ModeUpdateServiceAdvancedModel map() {
        return new ModeUpdateServiceAdvancedModel(id, name);
    }

    public static ModeUpdateServiceAdvancedEnum resolve(String id) {
        for (ModeUpdateServiceAdvancedEnum value : ModeUpdateServiceAdvancedEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No mode update service advanced found with id '%s'.".formatted(id));
    }

}
