package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonUpdateRequest;
import com.saas.tourismagency.admin.backoffice.domain.base.AreaDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.PositionDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("person")
    private PersonUpdateRequest person;

    @JsonProperty("partner")
    private PartnerUpdateRequest partner;

    @JsonProperty("area")
    private String area;

    @JsonProperty("position")
    private String position;

    @JsonProperty("user")
    private String user;

    public EmployeeDomain to() {
        EmployeeDomain employeeDomain = new EmployeeDomain();
        employeeDomain.setId(id);
        employeeDomain.setName(name);
        employeeDomain.setDescription(description);
        employeeDomain.setIdentify(identify);
        return employeeDomain;
    }

    public EmployeeDomain to(PersonDomain person, AreaDomain area, PositionDomain position, PartnerDomain partner) {
        EmployeeDomain employeeDomain = new EmployeeDomain();
        employeeDomain.setId(id);
        employeeDomain.setName(name);
        employeeDomain.setDescription(description);
        employeeDomain.setIdentify(identify);
        if (person != null)
            employeeDomain.setPerson(person);
        if (area != null)
            employeeDomain.setArea(area);
        if (position != null)
            employeeDomain.setPosition(position);
        if (partner != null)
            employeeDomain.setPartner(partner);
        return employeeDomain;
    }

}
