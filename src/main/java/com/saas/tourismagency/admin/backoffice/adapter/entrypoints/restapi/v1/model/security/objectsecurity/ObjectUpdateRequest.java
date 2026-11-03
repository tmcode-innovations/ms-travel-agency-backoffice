package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.objectsecurity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.security.AppDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("app")
    private String app;

    public ObjectDomain to() {
        ObjectDomain objectDomain = new ObjectDomain();
        objectDomain.setId(id);
        objectDomain.setName(name);
        objectDomain.setDescription(description);
        objectDomain.setIdentify(identify);
        return objectDomain;
    }

    public ObjectDomain to(AppDomain app) {
        ObjectDomain objectDomain = new ObjectDomain();
        objectDomain.setId(id);
        objectDomain.setName(name);
        objectDomain.setDescription(description);
        objectDomain.setIdentify(identify);
        if (app != null)
            objectDomain.setApp(app);
        return objectDomain;
    }

}
