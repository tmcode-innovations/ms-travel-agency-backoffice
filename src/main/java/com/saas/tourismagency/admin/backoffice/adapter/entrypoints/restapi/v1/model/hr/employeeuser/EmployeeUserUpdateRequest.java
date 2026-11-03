package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employeeuser;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUserUpdateRequest {

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

    public EmployeeUserDomain to() {
        EmployeeUserDomain employeeUserDomain = new EmployeeUserDomain();
        employeeUserDomain.setId(id);
        employeeUserDomain.setName(name);
        employeeUserDomain.setDescription(description);
        employeeUserDomain.setIdentify(identify);
        return employeeUserDomain;
    }

    public EmployeeUserDomain to(EmployeeDomain employee, UserDomain user) {
        EmployeeUserDomain employeeUserDomain = new EmployeeUserDomain();
        employeeUserDomain.setId(id);
        employeeUserDomain.setName(name);
        employeeUserDomain.setDescription(description);
        employeeUserDomain.setIdentify(identify);
        if (employee != null)
            employeeUserDomain.setEmployee(employee);
        if (user != null)
            employeeUserDomain.setUser(user);
        return employeeUserDomain;
    }

}
