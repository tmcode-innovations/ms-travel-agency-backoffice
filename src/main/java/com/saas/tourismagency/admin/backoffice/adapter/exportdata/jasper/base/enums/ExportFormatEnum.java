package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.utils.EnumMappeable;

public enum ExportFormatEnum implements EnumMappeable<ExportFormatEnum.ExportFormatModel> {

    PDF("PDF", "pdf"),
    XML("XML", "xml"),
    XLSX("XLSX", "xlsx");

    public record ExportFormatModel(
            @JsonProperty("id")
            String id,
            @JsonProperty("name")
            String name) {

    }

    private final String id;
    private final String name;

    private ExportFormatEnum(String id, String name) {
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
    public ExportFormatModel map() {
        return new ExportFormatModel(id, name);
    }

    public static ExportFormatEnum resolve(String id) {
        for (ExportFormatEnum value : ExportFormatEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new NotFoundException("No export format found with id '%s'.".formatted(id));
    }

}
