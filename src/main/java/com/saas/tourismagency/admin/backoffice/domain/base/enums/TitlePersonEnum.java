package com.saas.tourismagency.admin.backoffice.domain.base.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum TitlePersonEnum implements EnumMappeable<TitlePersonEnum.TitlePersonModel> {

    MRS("1", "Mrs.", "Mrs"),
    MR("2", "Mr.", "Mr"),
    MS("3", "Ms.", "Ms"),
    CHD("4", "Chd.", "Chd");

    public record TitlePersonModel(
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

    private TitlePersonEnum(String id, String name, String description) {
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
    public TitlePersonEnum.TitlePersonModel map() {
        return new TitlePersonEnum.TitlePersonModel(id, name, description);
    }

    public static TitlePersonEnum resolve(String id) {
        for (TitlePersonEnum value : TitlePersonEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No title person found with id '%s'.".formatted(id));
    }

}
