package com.saas.tourismagency.admin.backoffice.domain.booking.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum StateRegisterGroupEnum implements EnumMappeable<StateRegisterGroupEnum.StateRegisterGroupModel> {

    GENERATED("1", "Generated", "Generado"),
    OPENED("2", "Opened", "Aperturado"),
    COMPLETED("3", "Completed", "Completado"),
    INITIALIZED("4", "Initialized", "Inicializado"),
    FINISHED("5", "Finished", "Finalizado"),
    CLOSED("6", "Closed", "Cerrado"),
    ANNULLED("7", "Annulled", "Anulado"),
    DRAFT("8", "Draft", "Nuevo"),
    REVIEW("9", "Review", "Revisión");

    public record StateRegisterGroupModel(
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

    private StateRegisterGroupEnum(String id, String name, String description) {
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
    public StateRegisterGroupModel map() {
        return new StateRegisterGroupModel(id, name, description);
    }

    public static StateRegisterGroupEnum resolve(String id) {
        for (StateRegisterGroupEnum value : StateRegisterGroupEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No state register group found with id '%s'.".formatted(id));
    }

}
