package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.changerequest;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.ChangeRequestCommentEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangeRequestCommentConverter implements Converter<ChangeRequestCommentDomain, ChangeRequestCommentEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private ChangeRequestConverter changeRequestConverter;

    @Override
    public ChangeRequestCommentDomain toDomain(ChangeRequestCommentEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ChangeRequestCommentDomain changeRequestCommentDomain = new ChangeRequestCommentDomain();

        changeRequestCommentDomain.setId( source.getId() );
        changeRequestCommentDomain.setDeleted( source.getDeleted() );
        changeRequestCommentDomain.setEnabled( source.getEnabled() );
        changeRequestCommentDomain.setMigrated( source.getMigrated() );
        changeRequestCommentDomain.setCreatedAt( source.getCreatedAt() );
        changeRequestCommentDomain.setUpdatedAt( source.getUpdatedAt() );
        changeRequestCommentDomain.setDeletedAt( source.getDeletedAt() );
        changeRequestCommentDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        changeRequestCommentDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        changeRequestCommentDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        changeRequestCommentDomain.setIdentifierName( source.getIdentifierName() );
        changeRequestCommentDomain.setTenant( source.getTenant() );
        changeRequestCommentDomain.setName( source.getName() );
        changeRequestCommentDomain.setDescription( source.getDescription() );
        changeRequestCommentDomain.setIdentify( source.getIdentify() );

        changeRequestCommentDomain.setDateTime( source.getDateTime() );

        changeRequestCommentDomain.setEmployee( employeeConverter.toDomain( source.getEmployee() ) );
        changeRequestCommentDomain.setUser( userConverter.toDomain( source.getUser() ) );
        changeRequestCommentDomain.setChangeRequest( changeRequestConverter.toDomain( source.getChangeRequest() ) );

        return changeRequestCommentDomain;
    }

    @Override
    public ChangeRequestCommentEntity toEntity(ChangeRequestCommentDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ChangeRequestCommentEntity changeRequestCommentEntity = new ChangeRequestCommentEntity();

        changeRequestCommentEntity.setId( source.getId() );
        changeRequestCommentEntity.setDeleted( source.getDeleted() );
        changeRequestCommentEntity.setEnabled( source.getEnabled() );
        changeRequestCommentEntity.setMigrated( source.getMigrated() );
        changeRequestCommentEntity.setCreatedAt( source.getCreatedAt() );
        changeRequestCommentEntity.setUpdatedAt( source.getUpdatedAt() );
        changeRequestCommentEntity.setDeletedAt( source.getDeletedAt() );
        changeRequestCommentEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        changeRequestCommentEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        changeRequestCommentEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        changeRequestCommentEntity.setIdentifierName( source.getIdentifierName() );
        changeRequestCommentEntity.setTenant( source.getTenant() );
        changeRequestCommentEntity.setName( source.getName() );
        changeRequestCommentEntity.setDescription( source.getDescription() );
        changeRequestCommentEntity.setIdentify( source.getIdentify() );

        changeRequestCommentEntity.setDateTime( source.getDateTime() );

        changeRequestCommentEntity.setEmployee( employeeConverter.toEntity( source.getEmployee() ) );
        changeRequestCommentEntity.setUser( userConverter.toEntity( source.getUser() ) );
        changeRequestCommentEntity.setChangeRequest( changeRequestConverter.toEntity( source.getChangeRequest() ) );

        return changeRequestCommentEntity;
    }

    public IdBaseDomain toDomainIdBase(ChangeRequestCommentEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ChangeRequestCommentEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ChangeRequestCommentEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ChangeRequestCommentDomain> toDomainAllSimpleToDomainCaseShowChangeRequestComment(List<ChangeRequestCommentEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ChangeRequestCommentDomain changeRequestCommentDomain = new ChangeRequestCommentDomain();
            changeRequestCommentDomain.setId(entity.getId());
            changeRequestCommentDomain.setDateTime(entity.getDateTime());
            changeRequestCommentDomain.setDescription(entity.getDescription());
            if (entity.getEmployee() != null &&
                    entity.getEmployee().getId() != null &&
                    !entity.getEmployee().getId().isEmpty()){
                changeRequestCommentDomain.setEmployee(new EmployeeDomain());
                changeRequestCommentDomain.getEmployee().setId(entity.getEmployee().getId());
                if (entity.getEmployee().getPerson() != null &&
                        entity.getEmployee().getPerson().getId() != null &&
                        !entity.getEmployee().getPerson().getId().isEmpty()){
                    changeRequestCommentDomain.getEmployee().setPerson(new PersonDomain());
                    changeRequestCommentDomain.getEmployee().getPerson().setId(entity.getEmployee().getPerson().getId());
                    changeRequestCommentDomain.getEmployee().getPerson().setFirstName(entity.getEmployee().getPerson().getFirstName());
                    changeRequestCommentDomain.getEmployee().getPerson().setLastName(entity.getEmployee().getPerson().getLastName());
                }
            }
            return changeRequestCommentDomain;
        }).toList();
    }
}
