package com.saas.tourismagency.admin.backoffice.domain.account.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum ExpenseGuideStateEnum implements EnumMappeable<ExpenseGuideStateEnum.ExpenseGuideStateModel> {

    PENDING("1", "Pending"),
    CANCELLED("2", "Cancelled"),
    ANNULLED("3", "Annulled");

    public record ExpenseGuideStateModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name) {

    }

    private final String id;
    private final String name;

    private ExpenseGuideStateEnum(String id, String name) {
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
    public ExpenseGuideStateModel map() {
        return new ExpenseGuideStateModel(id, name);
    }

    public static ExpenseGuideStateEnum resolve(String id) {
        for (ExpenseGuideStateEnum value : ExpenseGuideStateEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No expense guide state found with id '%s'.".formatted(id));
    }

}
