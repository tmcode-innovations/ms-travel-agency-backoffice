package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.ChangeRequestDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.NotificationDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("type")
    private String type;

    @JsonProperty("categories")
    private Set<String> categories;

    @JsonProperty("idObjectEmit")
    private String idObjectEmit;

    @JsonProperty("employeeEmit")
    private String employeeEmit;

    @JsonProperty("userEmit")
    private String userEmit;

    @JsonProperty("objectEntityEmit")
    private String objectEntityEmit;

    @JsonProperty("idObjectReceive")
    private String idObjectReceive;

    @JsonProperty("employeeReceive")
    private String employeeReceive;

    @JsonProperty("userReceive")
    private String userReceive;

    @JsonProperty("objectEntityReceive")
    private String objectEntityReceive;

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("quotation")
    private String quotation;

    public ChangeRequestDomain to() {
        ChangeRequestDomain changeRequestDomain = new ChangeRequestDomain();
        changeRequestDomain.setId(id);
        changeRequestDomain.setName(name);
        changeRequestDomain.setDescription(description);
        changeRequestDomain.setIdentify(identify);

        changeRequestDomain.setType(type);
        changeRequestDomain.setIdObjectEmit(idObjectEmit);
        changeRequestDomain.setIdObjectReceive(idObjectReceive);
        return changeRequestDomain;
    }

    public ChangeRequestDomain to(
            UserDomain userEmitDomain,
            EmployeeDomain employeeEmitDomain,
            ObjectDomain objectEntityEmitDomain,
            UserDomain userReceiveDomain,
            EmployeeDomain employeeReceiveDomain,
            ObjectDomain objectEntityReceiveDomain,
            RegisterGroupDomain registerGroupDomain,
            QuotationDomain quotationDomain,
            Set<String> categories
    ) {
        ChangeRequestDomain changeRequestDomain = new ChangeRequestDomain();
        changeRequestDomain.setId(id);
        changeRequestDomain.setName(name);
        changeRequestDomain.setDescription(description);
        changeRequestDomain.setIdentify(identify);

        changeRequestDomain.setType(type);
        changeRequestDomain.setIdObjectEmit(idObjectEmit);
        changeRequestDomain.setIdObjectReceive(idObjectReceive);

        if (employeeEmitDomain != null)
            changeRequestDomain.setEmployeeEmit(employeeEmitDomain);

        if (userEmitDomain != null)
            changeRequestDomain.setUserEmit(userEmitDomain);

        if (objectEntityEmitDomain != null)
            changeRequestDomain.setObjectEntityEmit(objectEntityEmitDomain);

        if (employeeReceiveDomain != null)
            changeRequestDomain.setEmployeeReceive(employeeReceiveDomain);

        if (userReceiveDomain != null)
            changeRequestDomain.setUserReceive(userReceiveDomain);

        if (objectEntityReceiveDomain != null)
            changeRequestDomain.setObjectEntityReceive(objectEntityReceiveDomain);

        if (registerGroupDomain != null)
            changeRequestDomain.setRegisterGroup(registerGroupDomain);

        if (quotationDomain != null)
            changeRequestDomain.setQuotation(quotationDomain);

        if (categories != null && !categories.isEmpty())
            changeRequestDomain.setCategories(categories);

        return changeRequestDomain;
    }

}
