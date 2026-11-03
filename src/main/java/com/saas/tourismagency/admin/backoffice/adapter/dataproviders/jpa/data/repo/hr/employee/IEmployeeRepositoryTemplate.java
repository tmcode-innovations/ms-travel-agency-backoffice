package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.SimpleEmployeeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IEmployeeRepositoryTemplate {

    public PageDTO<EmployeeDomain> query(Pageable pageable, Query query);

    public List<EmployeeDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<SimpleEmployeeDomain> queryAllSimpleWithConditionalArea(Query query);

    public PageDTO<EmployeeDomain> queryAllSimpleDomainCaseListEmployee(Pageable pageable, Query query);
}
