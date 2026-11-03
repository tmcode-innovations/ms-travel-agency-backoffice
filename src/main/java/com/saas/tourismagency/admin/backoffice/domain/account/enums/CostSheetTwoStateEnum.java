package com.saas.tourismagency.admin.backoffice.domain.account.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum CostSheetTwoStateEnum implements EnumMappeable<CostSheetTwoStateEnum.CostSheetTwoStateModel> {

    PENDING("1", "Pending", "Pendiente"),
    CANCELLED("2", "Cancelled", "Cancelado"),
    ANNULLED("3", "Annulled", "Anulado");

    public record CostSheetTwoStateModel(
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

    private CostSheetTwoStateEnum(String id, String name, String description) {
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
    public CostSheetTwoStateModel map() {
        return new CostSheetTwoStateModel(id, name, description);
    }

    public static CostSheetTwoStateEnum resolve(String id) {
        for (CostSheetTwoStateEnum value : CostSheetTwoStateEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No cost sheet two state found with id '%s'.".formatted(id));
    }

}
