package com.saas.tourismagency.admin.backoffice.domain.booking.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum StateBookingEnum implements EnumMappeable<StateBookingEnum.StateBookingModel> {

    DRAFT("1", "Draft", "Generada"),
    REQUEST("2", "Request", "Solicitada"),
    CONFIRMED("3", "Confirmed", "Confirmada"),
    RECONFIRMED("4", "Reconfirmed", "Reconfirmada"),
    MODIFIED("5", "Modified", "Modificada"),
    ANNULLED("6", "Annulled", "Liberada"),
    CANCELLED("7", "Cancelled", "Cancelada"),
    SENT_TO_OPERATIONS("16", "Sent To Operations", "Enviada a Operaciones"),

    BOOKING("8", "Booking", "Reservado"),
    TO_RESCHEDULE("9", "To Reschedule", "A Reprogramar"),
    RESCHEDULED("10", "Rescheduled", "Reprogramado"),
    FINISHED("11", "Finished", "Concluido"),
    GENERATED_PENDING_MP("12", "Generated Pending MP", "Generado - Pendiente MP"),
    GENERATED_PENDING_ABC("13", "Generated Pending ABC", "Generado - Pendiente ABC"),
    GENERATED_PENDING_BCD("14", "Generated Pending BCD", "Generado - Pendiente BCD"),
    GENERATED_TO_CONFIRM_PERU_RAIL("15", "Generated Confirm Peru Rail", "Generado - Por confirmar PeruRail"),
    SCHEDULED("16", "Scheduled", "Programado"),
    NO_SHOW("17", "No Show", "No Show");


    public record StateBookingModel(
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

    private StateBookingEnum(String id, String name, String description) {
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
    public StateBookingModel map() {
        return new StateBookingModel(id, name, description);
    }

    public static StateBookingEnum resolve(String id) {
        for (StateBookingEnum value : StateBookingEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No state booking found with id '%s'.".formatted(id));
    }

}
