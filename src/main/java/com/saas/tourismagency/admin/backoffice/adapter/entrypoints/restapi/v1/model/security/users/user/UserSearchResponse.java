package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchResponse {

    @JsonProperty("entities")
    private List<UserResponse> users;

    @JsonProperty("totalPages")
    private long totalPages;

    @JsonProperty("totalElements")
    private long totalElements;

}
