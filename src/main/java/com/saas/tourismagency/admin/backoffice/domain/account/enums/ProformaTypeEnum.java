package com.saas.tourismagency.admin.backoffice.domain.account.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum ProformaTypeEnum implements EnumMappeable<ProformaTypeEnum.ProformaTypeModel> {

    INVOICE("1", "Invoice", "Factura"),
    CREDIT_NOTE("2", "Credit Note", "Nota de Credito");

    public record ProformaTypeModel(
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

    private ProformaTypeEnum(String id, String name, String description) {
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
    public ProformaTypeModel map() {
        return new ProformaTypeModel(id, name, description);
    }

    public static ProformaTypeEnum resolve(String id) {
        for (ProformaTypeEnum value : ProformaTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No proforma type found with id '%s'.".formatted(id));
    }

}
