package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mm.javaee.core.domain.ISearchPageDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchRequest implements ISearchPageDomain {

    @JsonProperty("page")
    private int page;

    @JsonProperty("size")
    private int size;

    @JsonProperty("sort")
    Map<String, String> sort;

}
