package com.saas.tourismagency.admin.backoffice.domain.account.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum TypeInheritanceDocumentFinanceEnum implements EnumMappeable<TypeInheritanceDocumentFinanceEnum.TypeInheritanceDocumentFinanceModel> {

    DOCUMENT_FINANCE_TICKET("1", "Document Finance Ticket"),
    DOCUMENT_FINANCE_COLLECTION("2", "Document Finance Collection"),
    DOCUMENT_FINANCE_INVOICE("3", "Document Finance Invoice"),
    DOCUMENT_FINANCE_DEBIT_NOTE("4", "Document Finance Debit Note"),
    DOCUMENT_FINANCE_CREDIT_NOTE("5", "Document Finance Credit Note"),
    DOCUMENT_FINANCE_CREDIT_NOTE_COMMISSION("6", "Document Finance Credit Note Commission"),
    DOCUMENT_FINANCE_INVOICE_COMMISSION("7", "Document Finance Invoice Commission");


    public record TypeInheritanceDocumentFinanceModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name) {

    }

    private final String id;
    private final String name;

    private TypeInheritanceDocumentFinanceEnum(String id, String name) {
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
    public TypeInheritanceDocumentFinanceEnum.TypeInheritanceDocumentFinanceModel map() {
        return new TypeInheritanceDocumentFinanceEnum.TypeInheritanceDocumentFinanceModel(id, name);
    }

    public static TypeInheritanceDocumentFinanceEnum resolve(String id) {
        for (TypeInheritanceDocumentFinanceEnum value : TypeInheritanceDocumentFinanceEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No type inheritance document finance found with id '%s'.".formatted(id));
    }

}
