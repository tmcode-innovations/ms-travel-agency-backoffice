package com.saas.tourismagency.admin.backoffice.domain.account.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum DocumentAccountStateEnum implements EnumMappeable<DocumentAccountStateEnum.DocumentAccountStateModel> {

    ANNULLED("1", "Annulled", "Anulado"),
    PENDING("2", "Pending", "Pendiente"),
    CANCELLED("3", "Cancelled", "Cancelado");


    public record DocumentAccountStateModel(
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

    private DocumentAccountStateEnum(String id, String name, String description) {
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
    public DocumentAccountStateModel map() {
        return new DocumentAccountStateModel(id, name, description);
    }

    public static DocumentAccountStateEnum resolve(String id) {
        for (DocumentAccountStateEnum value : DocumentAccountStateEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No document account state found with id '%s'.".formatted(id));
    }

}
