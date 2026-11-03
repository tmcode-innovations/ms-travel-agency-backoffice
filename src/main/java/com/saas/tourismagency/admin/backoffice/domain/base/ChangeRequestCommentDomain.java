package com.saas.tourismagency.admin.backoffice.domain.base;

import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ChangeRequestCommentDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private LocalDateTime dateTime;

    private EmployeeDomain employee;
    private UserDomain user;
    private ChangeRequestDomain changeRequest;

}
