package com.saas.tourismagency.admin.backoffice.domain.maintenance.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum CustomerTypeEnum implements EnumMappeable<CustomerTypeEnum.CustomerTypeModel> {

    OPERATOR_INTERNATIONAL("1", "Operator International"),
    OTHERS("2", "Others");

    public record CustomerTypeModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name) {

    }

    private final String id;
    private final String name;

    private CustomerTypeEnum(String id, String name) {
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
    public CustomerTypeEnum.CustomerTypeModel map() {
        return new CustomerTypeEnum.CustomerTypeModel(id, name);
    }

    public static CustomerTypeEnum resolve(String id) {
        for (CustomerTypeEnum value : CustomerTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No customer type found with id '%s'.".formatted(id));
    }

}
