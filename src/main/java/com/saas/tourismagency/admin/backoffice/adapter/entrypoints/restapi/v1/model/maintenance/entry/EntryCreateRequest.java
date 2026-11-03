package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.EntryDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("affectIgv")
    private String affectIgv;

    public EntryDomain to() {
        EntryDomain entryDomain = new EntryDomain();
        entryDomain.setName(name);
        entryDomain.setDescription(description);
        entryDomain.setAffectIgv(affectIgv);
        return entryDomain;
    }
}
