package com.saas.tourismagency.admin.backoffice.domain.account.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum PaymentTypeEnum implements EnumMappeable<PaymentTypeEnum.PaymentTypeModel> {

    TRANSFER_SWITZERLAND("1", "Transfer Switzerland"),
    TRANSFER_LIMA("2", "Transfer Lima"),
    INTERNET_VISA("3", "Internet Visa"),
    DIRECT_PAYMENT("4", "Direct Payment");

    public record PaymentTypeModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name) {

    }

    private final String id;
    private final String name;

    private PaymentTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public PaymentTypeModel map() {
        return new PaymentTypeModel(id, name);
    }

    public static PaymentTypeEnum resolve(String id) {
        for (PaymentTypeEnum value : PaymentTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No payment type found with id '%s'.".formatted(id));
    }

}
