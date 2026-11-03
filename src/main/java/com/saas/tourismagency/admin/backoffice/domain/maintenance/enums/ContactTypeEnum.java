package com.saas.tourismagency.admin.backoffice.domain.maintenance.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum ContactTypeEnum implements EnumMappeable<ContactTypeEnum.ContactTypeModel> {

    SUPPLIER("1", "Supplier"),
    CUSTOMER("2", "Customer");

    public record ContactTypeModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name) {

    }

    private final String id;
    private final String name;

    private ContactTypeEnum(String id, String name) {
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
    public ContactTypeEnum.ContactTypeModel map() {
        return new ContactTypeEnum.ContactTypeModel(id, name);
    }

    public static ContactTypeEnum resolve(String id) {
        for (ContactTypeEnum value : ContactTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No contact type found with id '%s'.".formatted(id));
    }

}
