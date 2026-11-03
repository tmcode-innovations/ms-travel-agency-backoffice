package com.saas.tourismagency.admin.backoffice.domain.base.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum NotificationTypeEnum implements EnumMappeable<NotificationTypeEnum.NotificationTypeModel> {

    QUOTATION_SENDING_TO_BOOKING("1", "Quotation Sending To Booking", "Cotización enviada a reserva"),
    REQUEST_SENT("2", "Request To Sent", "Solicitud enviada"),
    REQUEST_ACCEPTED("3", "Request Accepted", "Solicitud aceptada"),
    CHANGE_ACCEPTED("4", "Change Accepted", "Cambio aceptado"),
    CHANGE_REFUSED("5", "Change Refused", "Cambio rechazado"),
    REQUEST_COMPLETED("6", "Request Completed", "Solicitud completada"),
    CHANGE_REVIEW("7", "Change Review", "Revisando Cambio"),
    SEND_COMMENT("8", "Send Comment", "Comentario Enviado"),
    QUOTATION_DELETED_BY_REGISTER_GROUP("9", "Quotation Deleted Register Group", "Cotización eliminada por Registro de Grupo");

    public record NotificationTypeModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name,
            @JsonProperty("description")
            String description
            ) {

    }

    private final String id;
    private final String name;
    private final String description;

    private NotificationTypeEnum(String id, String name, String description) {
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
    public NotificationTypeEnum.NotificationTypeModel map() {
        return new NotificationTypeEnum.NotificationTypeModel(id, name, description);
    }

    public static NotificationTypeEnum resolve(String id) {
        for (NotificationTypeEnum value : NotificationTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No notification type found with id '%s'.".formatted(id));
    }

}
