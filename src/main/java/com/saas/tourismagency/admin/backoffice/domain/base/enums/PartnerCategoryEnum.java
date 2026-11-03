package com.saas.tourismagency.admin.backoffice.domain.base.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum PartnerCategoryEnum implements EnumMappeable<PartnerCategoryEnum.PartnerCategoryModel> {

    EMPLOYEE("1", "Employee"),
    CUSTOMER("2", "Customer"),
    SUPPLIER("3", "Supplier"),
    COMPANY("4", "Company");

    public record PartnerCategoryModel(
            @JsonProperty("id")
                    String id,
            @JsonProperty("name")
                    String name) {

    }

    private final String id;
    private final String name;

    private PartnerCategoryEnum(String id, String name) {
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
    public PartnerCategoryEnum.PartnerCategoryModel map() {
        return new PartnerCategoryEnum.PartnerCategoryModel(id, name);
    }

    public static PartnerCategoryEnum resolve(String id) {
        for (PartnerCategoryEnum value : PartnerCategoryEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No partner category found with id '%s'.".formatted(id));
    }
}
