package com.saas.tourismagency.admin.backoffice.domain.booking.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum StateServiceInvoiceEnum implements EnumMappeable<StateServiceInvoiceEnum.StateServiceInvoiceModel> {

    PENDING_PAYMENT("1", "Pending Payment", "Pendiente de Pago"),
    DOCUMENT_RECEIVED("2", "Document Received", "Documento Recibido"),
    GENERATED("3", "Generated", "Generado");


    public record StateServiceInvoiceModel(
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

    private StateServiceInvoiceEnum(String id, String name, String description) {
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
    public StateServiceInvoiceModel map() {
        return new StateServiceInvoiceModel(id, name, description);
    }

    public static StateServiceInvoiceEnum resolve(String id) {
        for (StateServiceInvoiceEnum value : StateServiceInvoiceEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No state booking found with id '%s'.".formatted(id));
    }

}
