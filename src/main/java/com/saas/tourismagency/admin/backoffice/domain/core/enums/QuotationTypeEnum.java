package com.saas.tourismagency.admin.backoffice.domain.core.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum QuotationTypeEnum implements EnumMappeable<QuotationTypeEnum.QuotationTypeModel> {

    TYPE_LEGENDARY("1", "Legendary", "Formato Legendary (Referencia)"),
    TYPE_GURU_EXPLORER("2", "Guru Explorers", "Formato Guru Explorers (Referencia)");

    public record QuotationTypeModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name,
            @JsonProperty("description")
            String description
            ) {

    }

    private final String id;
    private final String name;
    private final String description;

    private QuotationTypeEnum(String id, String name, String description) {
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
    public QuotationTypeEnum.QuotationTypeModel map() {
        return new QuotationTypeEnum.QuotationTypeModel(id, name, description);
    }

    public static QuotationTypeEnum resolve(String id) {
        for (QuotationTypeEnum value : QuotationTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No quotation type found with id '%s'.".formatted(id));
    }

}
