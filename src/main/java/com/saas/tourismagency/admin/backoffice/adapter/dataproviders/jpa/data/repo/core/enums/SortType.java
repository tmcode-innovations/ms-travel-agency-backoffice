package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums;

import dev.mm.javaee.core.validation.annotations.EnumValidatorComparator;
import java.util.List;

public enum SortType implements EnumValidatorComparator<String> {
    ASC("ASC"),
    DESC("DESC"),
    NONE("0");

    private final String name;

    private SortType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean test(String statusName) {
        return this.toString().equalsIgnoreCase(statusName);
    }

    public static boolean contain(String type) {
        return List.of(SortType.values()
        ).stream().anyMatch((SortType t) -> {
            return t.test(type);
        });
    }

    public static List<String> all() {
        return List.of(SortType.values()).stream().map((SortType t) -> {
            return t.toString();
        }
        ).toList();
    }
}
