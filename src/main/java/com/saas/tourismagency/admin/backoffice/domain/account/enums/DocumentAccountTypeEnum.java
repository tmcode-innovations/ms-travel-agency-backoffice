package com.saas.tourismagency.admin.backoffice.domain.account.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum DocumentAccountTypeEnum implements EnumMappeable<DocumentAccountTypeEnum.DocumentAccountTypeModel> {

    BILL("1", "Bill", "Factura", "FAC"),
    BILL_SALE("2", "Bill Sale", "Boleta", "BOL"),
    RECEIPTS_FOR_FEES("3", "Receipts for Fees", "Recibo de Honorarios", "RxH"),
    TICKET("4", "Ticket", "Ticket", "TIC"),
    SEVERAL_RECEIPTS("5", "Several Receipts", "Recibos Varios", "RxV"),
    COLLECTION_DOCUMENT("6", "Collection Document", "Documento de Cobranza", "DOC"),
    CREDIT_NOTE("7", "Credit Note", "Nota de Credito", "CRE"),
    DEBIT_NOTE("8", "Debit Note", "Nota de Debito", "DEB"),
    VACATION_SCHEDULE("9", "Vacation Schedule", "Planilla de Vacaciones", "PdV"),
    SALARY_SHEET("10", "Salary Sheet", "Planilla de Sueldos", "PdS"),
    CTS_WORKSHOP("11", "Cts Workshop", "Planilla de CTS", "CTS"),
    AFP_RETURN("12", "Afp Return", "Planilla de AFP", "AFP"),
    TRANSFERS("13", "Transfers", "Transferencias", "TRF"),
    RETURN("14", "Return", "Devoluciones", "DEV"),
    DETRACTION("15", "Detraction", "Detracción", "DET"),
    SETTLEMENT_EXPENSES("16", "Settlement Expenses", "Liquidación de gastos", "LIQ"),
    OTHER("17", "Other", "Otros", "OTHER");

    public record DocumentAccountTypeModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name,
            @JsonProperty("description")
            String description,
            @JsonProperty("abrev")
            String abbrev) {

    }

    private final String id;
    private final String name;
    private final String description;
    private final String abbrev;

    private DocumentAccountTypeEnum(String id, String name, String description, String abbrev) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.abbrev = abbrev;
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

    public String getAbbrev() {
        return abbrev;
    }

    @Override
    public DocumentAccountTypeModel map() {
        return new DocumentAccountTypeModel(id, name, description, abbrev);
    }

    public static DocumentAccountTypeEnum resolve(String id) {
        for (DocumentAccountTypeEnum value : DocumentAccountTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No document account type found with id '%s'.".formatted(id));
    }

}
