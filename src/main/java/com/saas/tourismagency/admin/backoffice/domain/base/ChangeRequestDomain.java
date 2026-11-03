package com.saas.tourismagency.admin.backoffice.domain.base;

import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ChangeRequestDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String status;
    private String type;
    private Boolean activeChangeRequest;
    private Set<String> categories;

    private LocalDateTime dateTime;

    private String nameObjectEmit;
    private String identifyObjectEmit;
    private String idObjectEmit;
    private EmployeeDomain employeeEmit;
    private UserDomain userEmit;
    private ObjectDomain objectEntityEmit;

    private String nameObjectReceive;
    private String identifyObjectReceive;
    private String idObjectReceive;
    private EmployeeDomain employeeReceive;
    private UserDomain userReceive;
    private ObjectDomain objectEntityReceive;

    private RegisterGroupDomain registerGroup;
    private QuotationDomain quotation;

    private EmployeeDomain employeeAttendedBy;
    private UserDomain userAttendedBy;

    private EmployeeDomain employeeSentBy;
    private UserDomain userSentBy;

    private EmployeeDomain employeeRefusedBy;
    private UserDomain userRefusedBy;

    private EmployeeDomain employeeCompletedBy;
    private UserDomain userCompletedBy;

    private EmployeeDomain employeeAcceptedBy;
    private UserDomain userAcceptedBy;
}
