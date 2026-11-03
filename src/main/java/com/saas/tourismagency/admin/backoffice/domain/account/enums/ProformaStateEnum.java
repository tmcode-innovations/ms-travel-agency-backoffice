package com.saas.tourismagency.admin.backoffice.domain.account.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum ProformaStateEnum implements EnumMappeable<ProformaStateEnum.ProformaStateModel> {

    ANNULLED("1", "Annulled", "Anulada"),
    PENDING("2", "Pending", "Pendiente"),
    CANCELLED("3", "Cancelled", "Cancelada");


    public record ProformaStateModel(
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

    private ProformaStateEnum(String id, String name, String description) {
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
    public ProformaStateModel map() {
        return new ProformaStateModel(id, name, description);
    }

    public static ProformaStateEnum resolve(String id) {
        for (ProformaStateEnum value : ProformaStateEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No proforma state found with id '%s'.".formatted(id));
    }

}
