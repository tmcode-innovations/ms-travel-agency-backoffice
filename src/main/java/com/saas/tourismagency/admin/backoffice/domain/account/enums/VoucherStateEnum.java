package com.saas.tourismagency.admin.backoffice.domain.account.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum VoucherStateEnum implements EnumMappeable<VoucherStateEnum.VoucherStateModel> {

    PENDING("1", "Pending", "Pendiente"),
    CANCELLED("2", "Cancelled", "Cancelado"),
    ANNULLED("3", "Annulled", "Anulado");

    public record VoucherStateModel(
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

    private VoucherStateEnum(String id, String name, String description) {
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
    public VoucherStateModel map() {
        return new VoucherStateModel(id, name, description);
    }

    public static VoucherStateEnum resolve(String id) {
        for (VoucherStateEnum value : VoucherStateEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No voucher state found with id '%s'.".formatted(id));
    }

}
