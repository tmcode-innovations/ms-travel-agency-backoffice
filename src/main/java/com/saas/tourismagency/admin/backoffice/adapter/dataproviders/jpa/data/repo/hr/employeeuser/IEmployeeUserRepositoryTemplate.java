package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser;

import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IEmployeeUserRepositoryTemplate {

    public PageDTO<EmployeeUserDomain> query(Pageable pageable, Query query);

    public List<EmployeeUserDomain> query(Query query);
}
