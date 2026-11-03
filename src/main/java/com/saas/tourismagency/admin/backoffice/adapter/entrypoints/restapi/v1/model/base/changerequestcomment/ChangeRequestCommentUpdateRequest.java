package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequestcomment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.ChangeRequestCommentDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.ChangeRequestDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestCommentUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("employee")
    private String employee;

    @JsonProperty("user")
    private String user;

    @JsonProperty("changeRequest")
    private String changeRequest;

    public ChangeRequestCommentDomain to() {
        ChangeRequestCommentDomain changeRequestCommentDomain = new ChangeRequestCommentDomain();
        changeRequestCommentDomain.setId(id);
        changeRequestCommentDomain.setName(name);
        changeRequestCommentDomain.setDescription(description);
        changeRequestCommentDomain.setIdentify(identify);
        return changeRequestCommentDomain;
    }

    public ChangeRequestCommentDomain to(
            UserDomain userDomain,
            EmployeeDomain employeeDomain,
            ChangeRequestDomain changeRequestDomain
    ) {
        ChangeRequestCommentDomain changeRequestCommentDomain = new ChangeRequestCommentDomain();
        changeRequestCommentDomain.setId(id);
        changeRequestCommentDomain.setName(name);
        changeRequestCommentDomain.setDescription(description);
        changeRequestCommentDomain.setIdentify(identify);

        if (employeeDomain != null)
            changeRequestCommentDomain.setEmployee(employeeDomain);

        if (userDomain != null)
            changeRequestCommentDomain.setUser(userDomain);

        if (changeRequestDomain != null)
            changeRequestCommentDomain.setChangeRequest(changeRequestDomain);

        return changeRequestCommentDomain;
    }
}
