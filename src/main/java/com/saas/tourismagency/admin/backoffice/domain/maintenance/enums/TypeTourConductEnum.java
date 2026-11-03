package com.saas.tourismagency.admin.backoffice.domain.maintenance.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum TypeTourConductEnum implements EnumMappeable<TypeTourConductEnum.TypeTourConductModel> {

    EMPLOYEE("1", "Tc"),
    CUSTOMER("2", "Tc Local");

    public record TypeTourConductModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name) {

    }

    private final String id;
    private final String name;

    private TypeTourConductEnum(String id, String name) {
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
    public TypeTourConductEnum.TypeTourConductModel map() {
        return new TypeTourConductEnum.TypeTourConductModel(id, name);
    }

    public static TypeTourConductEnum resolve(String id) {
        for (TypeTourConductEnum value : TypeTourConductEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No type tour conduct found with id '%s'.".formatted(id));
    }

}
