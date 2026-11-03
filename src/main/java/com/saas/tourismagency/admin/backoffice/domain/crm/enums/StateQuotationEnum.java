package com.saas.tourismagency.admin.backoffice.domain.crm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum StateQuotationEnum implements EnumMappeable<StateQuotationEnum.StateQuotationModel> {

    DRAFT("1", "Draft", "Nueva"),
    CANCEL("2", "Cancel", "Cancelada"),
    COMPLETED("3", "Completed", "Completada"),
    ACCEPTED("4", "Accepted", "Aceptada"),
    IN_BOOKING("5", "In Booking", "En Reserva"),
    FINISHED("6", "Finished", "Finalizada"),
    SENT("7", "Sent", "Enviada a Reserva"),
    REJECTED("8", "Rejected", "Rechazada"),
    REVIEW("9", "Review", "Revisión");

    public record StateQuotationModel(
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

    private StateQuotationEnum(String id, String name, String description) {
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
    public StateQuotationEnum.StateQuotationModel map() {
        return new StateQuotationEnum.StateQuotationModel(id, name, description);
    }

    public static StateQuotationEnum resolve(String id) {
        for (StateQuotationEnum value : StateQuotationEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No state quotation found with id '%s'.".formatted(id));
    }

}
