package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employeeuser;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeUserEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeUserConverter implements Converter<EmployeeUserDomain, EmployeeUserEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Override
    public EmployeeUserDomain toDomain(EmployeeUserEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        EmployeeUserDomain employeeUserDomain = new EmployeeUserDomain();

        employeeUserDomain.setId( source.getId() );
        employeeUserDomain.setDeleted( source.getDeleted() );
        employeeUserDomain.setEnabled( source.getEnabled() );
        employeeUserDomain.setMigrated( source.getMigrated() );
        employeeUserDomain.setCreatedAt( source.getCreatedAt() );
        employeeUserDomain.setUpdatedAt( source.getUpdatedAt() );
        employeeUserDomain.setDeletedAt( source.getDeletedAt() );
        employeeUserDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        employeeUserDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        employeeUserDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        employeeUserDomain.setIdentifierName( source.getIdentifierName() );
        employeeUserDomain.setTenant( source.getTenant() );
        employeeUserDomain.setName( source.getName() );
        employeeUserDomain.setDescription( source.getDescription() );
        employeeUserDomain.setIdentify( source.getIdentify() );
        employeeUserDomain.setEmployee( employeeConverter.toDomain( source.getEmployee() ) );
        employeeUserDomain.setUser( userConverter.toDomain( source.getUser() ) );

        return employeeUserDomain;
    }

    @Override
    public EmployeeUserEntity toEntity(EmployeeUserDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        EmployeeUserEntity employeeUserEntity = new EmployeeUserEntity();

        employeeUserEntity.setId( source.getId() );
        employeeUserEntity.setDeleted( source.getDeleted() );
        employeeUserEntity.setEnabled( source.getEnabled() );
        employeeUserEntity.setMigrated( source.getMigrated() );
        employeeUserEntity.setCreatedAt( source.getCreatedAt() );
        employeeUserEntity.setUpdatedAt( source.getUpdatedAt() );
        employeeUserEntity.setDeletedAt( source.getDeletedAt() );
        employeeUserEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        employeeUserEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        employeeUserEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        employeeUserEntity.setIdentifierName( source.getIdentifierName() );
        employeeUserEntity.setTenant( source.getTenant() );
        employeeUserEntity.setName( source.getName() );
        employeeUserEntity.setDescription( source.getDescription() );
        employeeUserEntity.setIdentify( source.getIdentify() );
        employeeUserEntity.setEmployee( employeeConverter.toEntity( source.getEmployee() ) );
        employeeUserEntity.setUser( userConverter.toEntity( source.getUser() ) );

        return employeeUserEntity;
    }

    public IdBaseDomain toDomainIdBase(EmployeeUserEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(EmployeeUserEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<EmployeeUserEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
