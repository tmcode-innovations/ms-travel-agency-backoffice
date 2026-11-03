package com.saas.tourismagency.admin.backoffice.domain.account.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum VoucherTypeEnum implements EnumMappeable<VoucherTypeEnum.VoucherTypeModel> {

    CASH_CHECK("1", "Cash Check", "Efectivo / Cheque"),
    PREPAID("2", "Prepaid", "Prepago"),
    TRANSFER("3", "Transfer", "Transferencia"),
    CREDIT_CARD("4", "Credit Card", "Tarjeta de credito");

    public record VoucherTypeModel(
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

    private VoucherTypeEnum(String id, String name, String description) {
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
    public VoucherTypeModel map() {
        return new VoucherTypeModel(id, name, description);
    }

    public static VoucherTypeEnum resolve(String id) {
        for (VoucherTypeEnum value : VoucherTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No voucher type found with id '%s'.".formatted(id));
    }

}
