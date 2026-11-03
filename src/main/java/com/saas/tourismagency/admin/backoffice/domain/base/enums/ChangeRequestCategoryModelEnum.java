package com.saas.tourismagency.admin.backoffice.domain.base.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum ChangeRequestCategoryModelEnum implements EnumMappeable<ChangeRequestCategoryModelEnum.ChangeRequestCategoryModel> {

    ADD_DAYS_TO_INIT("1", "Add Days to the Init", "Agregar Días al Inicio"),
    ADD_DAYS_TO_END("2", "Add Days to the End", "Agregar Días al Final"),
    ADD_DAYS_MIDDLE("3", "Add Days in the middle", "Agregar Días Intermedio"),

    ADD_TOUR("4", "Add Tour", "Agregar Tour"),
    ADD_PASSENGER_TYPE_TOUR("5", "Add Passenger Type Tour", "Agregar Tipo de Pasajero al Tour"),
    ADD_PASSENGER("6", "Add Passenger", "Agregar Pasajero"),
    ADD_SERVICE_TOUR("7", "Add Service Tour", "Agregar Servicio Adicional al Tour"),

    EDIT_PASSENGER("8", "Edit Passenger", "Editar Pasajero"),
    EDIT_SERVICE("9", "Edit Service", "Editar Servicio"),
    EDIT_TOUR("10", "Edit Tour", "Editar Tour"),
    EDIT_PASSENGER_TYPE_TOUR("11", "Edit Passenger Type Tour", "Editar Tipo de Pasajero al Tour"),
    EDIT_SERVICE_TOUR("12", "Edit Service Tour", "Editar Servicio Adicional al Tour"),

    DELETE_TOUR("13", "Delete Tour", "Eliminar Tour"),
    DELETE_SERVICE("14", "Delete Service", "Eliminar Servicio"),
    DELETE_PASSENGER("15", "Delete Passenger", "Eliminar Pasajero"),
    DELETE_PASSENGER_TYPE_TOUR("16", "Delete Passenger Tour", "Eliminar  Tipo de Pasajero al Tour"),
    DELETE_SERVICE_TOUR("17", "Delete Service Tour", "Eliminar Servicio Adicional al Tour"),

    CHANGE_DATE("18", "Change Date", "Cambiar Fecha"),
    CHANGE_QUOTATION_INFO("19", "Change Quotation Info", "Cambiar Informaciones Generales"),

    APPLY_ALL_CHANGE_TYPE("20", "Apply All Change Type", "Aplicar todo tipo de cambio");


    public record ChangeRequestCategoryModel(
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

    private ChangeRequestCategoryModelEnum(String id, String name, String description) {
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
    public ChangeRequestCategoryModelEnum.ChangeRequestCategoryModel map() {
        return new ChangeRequestCategoryModelEnum.ChangeRequestCategoryModel(id, name, description);
    }

    public static ChangeRequestCategoryModelEnum resolve(String id) {
        for (ChangeRequestCategoryModelEnum value : ChangeRequestCategoryModelEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No change request category found with id '%s'.".formatted(id));
    }

}
