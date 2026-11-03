package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.changerequest;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.quotation.QuotationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.object.ObjectConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.ChangeRequestEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ChangeRequestConverter implements Converter<ChangeRequestDomain, ChangeRequestEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private ObjectConverter objectConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private QuotationConverter quotationConverter;

    @Override
    public ChangeRequestDomain toDomain(ChangeRequestEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ChangeRequestDomain changeRequestDomain = new ChangeRequestDomain();

        changeRequestDomain.setId( source.getId() );
        changeRequestDomain.setDeleted( source.getDeleted() );
        changeRequestDomain.setEnabled( source.getEnabled() );
        changeRequestDomain.setMigrated( source.getMigrated() );
        changeRequestDomain.setCreatedAt( source.getCreatedAt() );
        changeRequestDomain.setUpdatedAt( source.getUpdatedAt() );
        changeRequestDomain.setDeletedAt( source.getDeletedAt() );
        changeRequestDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        changeRequestDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        changeRequestDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        changeRequestDomain.setIdentifierName( source.getIdentifierName() );
        changeRequestDomain.setTenant( source.getTenant() );
        changeRequestDomain.setName( source.getName() );
        changeRequestDomain.setDescription( source.getDescription() );
        changeRequestDomain.setIdentify( source.getIdentify() );

        changeRequestDomain.setType( source.getType() );
        changeRequestDomain.setStatus( source.getStatus() );
        changeRequestDomain.setActiveChangeRequest( source.getActiveChangeRequest() );
        Set<String> set1 = source.getCategories();
        if ( set1 != null ) {
            changeRequestDomain.setCategories( new LinkedHashSet<String>( set1 ) );
        }

        changeRequestDomain.setDateTime( source.getDateTime() );

        changeRequestDomain.setEmployeeEmit( employeeConverter.toDomain( source.getEmployeeEmit() ) );
        changeRequestDomain.setUserEmit( userConverter.toDomain( source.getUserEmit() ) );
        changeRequestDomain.setEmployeeReceive( employeeConverter.toDomain( source.getEmployeeReceive() ) );
        changeRequestDomain.setUserReceive( userConverter.toDomain( source.getUserReceive() ) );

        changeRequestDomain.setIdentifyObjectEmit( source.getIdentifyObjectEmit() );
        changeRequestDomain.setIdentifyObjectReceive( source.getIdentifyObjectReceive() );

        changeRequestDomain.setNameObjectEmit( source.getNameObjectEmit() );
        changeRequestDomain.setNameObjectReceive( source.getNameObjectReceive() );

        changeRequestDomain.setIdObjectEmit( source.getIdObjectEmit() );
        changeRequestDomain.setIdObjectReceive( source.getIdObjectReceive() );

        changeRequestDomain.setObjectEntityEmit( objectConverter.toDomain( source.getObjectEntityEmit() ) );
        changeRequestDomain.setObjectEntityReceive( objectConverter.toDomain( source.getObjectEntityReceive() ) );

        changeRequestDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );
        changeRequestDomain.setQuotation( quotationConverter.toDomain( source.getQuotation() ) );

        changeRequestDomain.setEmployeeAttendedBy( employeeConverter.toDomain( source.getEmployeeAttendedBy() ) );
        changeRequestDomain.setUserAttendedBy( userConverter.toDomain( source.getUserAttendedBy() ) );

        changeRequestDomain.setEmployeeSentBy( employeeConverter.toDomain( source.getEmployeeSentBy() ) );
        changeRequestDomain.setUserSentBy( userConverter.toDomain( source.getUserSentBy() ) );

        changeRequestDomain.setEmployeeRefusedBy( employeeConverter.toDomain( source.getEmployeeRefusedBy() ) );
        changeRequestDomain.setUserRefusedBy( userConverter.toDomain( source.getUserRefusedBy() ) );

        changeRequestDomain.setEmployeeCompletedBy( employeeConverter.toDomain( source.getEmployeeCompletedBy() ) );
        changeRequestDomain.setUserCompletedBy( userConverter.toDomain( source.getUserCompletedBy() ) );

        changeRequestDomain.setEmployeeAcceptedBy( employeeConverter.toDomain( source.getEmployeeAcceptedBy() ) );
        changeRequestDomain.setUserAcceptedBy( userConverter.toDomain( source.getUserAcceptedBy() ) );

        return changeRequestDomain;
    }

    @Override
    public ChangeRequestEntity toEntity(ChangeRequestDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ChangeRequestEntity changeRequestEntity = new ChangeRequestEntity();

        changeRequestEntity.setId( source.getId() );
        changeRequestEntity.setDeleted( source.getDeleted() );
        changeRequestEntity.setEnabled( source.getEnabled() );
        changeRequestEntity.setMigrated( source.getMigrated() );
        changeRequestEntity.setCreatedAt( source.getCreatedAt() );
        changeRequestEntity.setUpdatedAt( source.getUpdatedAt() );
        changeRequestEntity.setDeletedAt( source.getDeletedAt() );
        changeRequestEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        changeRequestEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        changeRequestEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        changeRequestEntity.setIdentifierName( source.getIdentifierName() );
        changeRequestEntity.setTenant( source.getTenant() );
        changeRequestEntity.setName( source.getName() );
        changeRequestEntity.setDescription( source.getDescription() );
        changeRequestEntity.setIdentify( source.getIdentify() );

        changeRequestEntity.setType( source.getType() );
        changeRequestEntity.setStatus( source.getStatus() );
        changeRequestEntity.setActiveChangeRequest( source.getActiveChangeRequest() );

        Set<String> set1 = source.getCategories();
        if ( set1 != null ) {
            changeRequestEntity.setCategories( new LinkedHashSet<String>( set1 ) );
        }

        changeRequestEntity.setDateTime( source.getDateTime() );

        changeRequestEntity.setEmployeeEmit( employeeConverter.toEntity( source.getEmployeeEmit() ) );
        changeRequestEntity.setUserEmit( userConverter.toEntity( source.getUserEmit() ) );

        changeRequestEntity.setEmployeeReceive( employeeConverter.toEntity( source.getEmployeeReceive() ) );
        changeRequestEntity.setUserReceive( userConverter.toEntity( source.getUserReceive() ) );

        changeRequestEntity.setIdentifyObjectEmit( source.getIdentifyObjectEmit() );
        changeRequestEntity.setIdentifyObjectReceive( source.getIdentifyObjectReceive() );

        changeRequestEntity.setNameObjectEmit( source.getNameObjectEmit() );
        changeRequestEntity.setNameObjectReceive( source.getNameObjectReceive() );

        changeRequestEntity.setIdObjectEmit( source.getIdObjectEmit() );
        changeRequestEntity.setIdObjectReceive( source.getIdObjectReceive() );

        changeRequestEntity.setObjectEntityEmit( objectConverter.toEntity( source.getObjectEntityEmit() ) );
        changeRequestEntity.setObjectEntityReceive( objectConverter.toEntity( source.getObjectEntityReceive() ) );

        changeRequestEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );
        changeRequestEntity.setQuotation( quotationConverter.toEntity( source.getQuotation() ) );

        changeRequestEntity.setEmployeeAttendedBy( employeeConverter.toEntity( source.getEmployeeAttendedBy() ) );
        changeRequestEntity.setUserAttendedBy( userConverter.toEntity( source.getUserAttendedBy() ) );

        changeRequestEntity.setEmployeeSentBy( employeeConverter.toEntity( source.getEmployeeSentBy() ) );
        changeRequestEntity.setUserSentBy( userConverter.toEntity( source.getUserSentBy() ) );

        changeRequestEntity.setEmployeeRefusedBy( employeeConverter.toEntity( source.getEmployeeRefusedBy() ) );
        changeRequestEntity.setUserRefusedBy( userConverter.toEntity( source.getUserRefusedBy() ) );

        changeRequestEntity.setEmployeeCompletedBy( employeeConverter.toEntity( source.getEmployeeCompletedBy() ) );
        changeRequestEntity.setUserCompletedBy( userConverter.toEntity( source.getUserCompletedBy() ) );

        changeRequestEntity.setEmployeeAcceptedBy( employeeConverter.toEntity( source.getEmployeeAcceptedBy() ) );
        changeRequestEntity.setUserAcceptedBy( userConverter.toEntity( source.getUserAcceptedBy() ) );

        return changeRequestEntity;
    }

    public IdBaseDomain toDomainIdBase(ChangeRequestEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ChangeRequestEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ChangeRequestEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ChangeRequestDomain> toDomainAllSimpleToDomainCaseShowChangeRequest(List<ChangeRequestEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ChangeRequestDomain changeRequestDomain = new ChangeRequestDomain();
            changeRequestDomain.setId(entity.getId());
            changeRequestDomain.setStatus(entity.getStatus());
            changeRequestDomain.setDateTime(entity.getDateTime());
            changeRequestDomain.setDescription(entity.getDescription());
            changeRequestDomain.setType(entity.getType());
            if (entity.getEmployeeEmit() != null &&
                    entity.getEmployeeEmit().getId() != null &&
                    !entity.getEmployeeEmit().getId().isEmpty()){
                changeRequestDomain.setEmployeeEmit(new EmployeeDomain());
                changeRequestDomain.getEmployeeEmit().setId(entity.getEmployeeEmit().getId());
                if (entity.getEmployeeEmit().getPerson() != null &&
                        entity.getEmployeeEmit().getPerson().getId() != null &&
                        !entity.getEmployeeEmit().getPerson().getId().isEmpty()){
                    changeRequestDomain.getEmployeeEmit().setPerson(new PersonDomain());
                    changeRequestDomain.getEmployeeEmit().getPerson().setId(entity.getEmployeeEmit().getPerson().getId());
                    changeRequestDomain.getEmployeeEmit().getPerson().setFirstName(entity.getEmployeeEmit().getPerson().getFirstName());
                    changeRequestDomain.getEmployeeEmit().getPerson().setLastName(entity.getEmployeeEmit().getPerson().getLastName());
                }
            }
            if (entity.getEmployeeReceive() != null &&
                    entity.getEmployeeReceive().getId() != null &&
                    !entity.getEmployeeReceive().getId().isEmpty()){
                changeRequestDomain.setEmployeeReceive(new EmployeeDomain());
                changeRequestDomain.getEmployeeReceive().setId(entity.getEmployeeReceive().getId());
                if (entity.getEmployeeReceive().getPerson() != null &&
                        entity.getEmployeeReceive().getPerson().getId() != null &&
                        !entity.getEmployeeReceive().getPerson().getId().isEmpty()){
                    changeRequestDomain.getEmployeeReceive().setPerson(new PersonDomain());
                    changeRequestDomain.getEmployeeReceive().getPerson().setId(entity.getEmployeeReceive().getPerson().getId());
                    changeRequestDomain.getEmployeeReceive().getPerson().setFirstName(entity.getEmployeeReceive().getPerson().getFirstName());
                    changeRequestDomain.getEmployeeReceive().getPerson().setLastName(entity.getEmployeeReceive().getPerson().getLastName());
                }
            }
            if (entity.getEmployeeAttendedBy() != null &&
                    entity.getEmployeeAttendedBy().getId() != null &&
                    !entity.getEmployeeAttendedBy().getId().isEmpty()){
                changeRequestDomain.setEmployeeAttendedBy(new EmployeeDomain());
                changeRequestDomain.getEmployeeAttendedBy().setId(entity.getEmployeeAttendedBy().getId());
                if (entity.getEmployeeAttendedBy().getPerson() != null &&
                        entity.getEmployeeAttendedBy().getPerson().getId() != null &&
                        !entity.getEmployeeAttendedBy().getPerson().getId().isEmpty()){
                    changeRequestDomain.getEmployeeAttendedBy().setPerson(new PersonDomain());
                    changeRequestDomain.getEmployeeAttendedBy().getPerson().setId(entity.getEmployeeAttendedBy().getPerson().getId());
                    changeRequestDomain.getEmployeeAttendedBy().getPerson().setFirstName(entity.getEmployeeAttendedBy().getPerson().getFirstName());
                    changeRequestDomain.getEmployeeAttendedBy().getPerson().setLastName(entity.getEmployeeAttendedBy().getPerson().getLastName());
                }
            }
            if (entity.getEmployeeSentBy() != null &&
                    entity.getEmployeeSentBy().getId() != null &&
                    !entity.getEmployeeSentBy().getId().isEmpty()){
                changeRequestDomain.setEmployeeSentBy(new EmployeeDomain());
                changeRequestDomain.getEmployeeSentBy().setId(entity.getEmployeeSentBy().getId());
                if (entity.getEmployeeSentBy().getPerson() != null &&
                        entity.getEmployeeSentBy().getPerson().getId() != null &&
                        !entity.getEmployeeSentBy().getPerson().getId().isEmpty()){
                    changeRequestDomain.getEmployeeSentBy().setPerson(new PersonDomain());
                    changeRequestDomain.getEmployeeSentBy().getPerson().setId(entity.getEmployeeSentBy().getPerson().getId());
                    changeRequestDomain.getEmployeeSentBy().getPerson().setFirstName(entity.getEmployeeSentBy().getPerson().getFirstName());
                    changeRequestDomain.getEmployeeSentBy().getPerson().setLastName(entity.getEmployeeSentBy().getPerson().getLastName());
                }
            }
            if (entity.getEmployeeRefusedBy() != null &&
                    entity.getEmployeeRefusedBy().getId() != null &&
                    !entity.getEmployeeRefusedBy().getId().isEmpty()){
                changeRequestDomain.setEmployeeRefusedBy(new EmployeeDomain());
                changeRequestDomain.getEmployeeRefusedBy().setId(entity.getEmployeeRefusedBy().getId());
                if (entity.getEmployeeRefusedBy().getPerson() != null &&
                        entity.getEmployeeRefusedBy().getPerson().getId() != null &&
                        !entity.getEmployeeRefusedBy().getPerson().getId().isEmpty()){
                    changeRequestDomain.getEmployeeRefusedBy().setPerson(new PersonDomain());
                    changeRequestDomain.getEmployeeRefusedBy().getPerson().setId(entity.getEmployeeRefusedBy().getPerson().getId());
                    changeRequestDomain.getEmployeeRefusedBy().getPerson().setFirstName(entity.getEmployeeRefusedBy().getPerson().getFirstName());
                    changeRequestDomain.getEmployeeRefusedBy().getPerson().setLastName(entity.getEmployeeRefusedBy().getPerson().getLastName());
                }
            }
            if (entity.getEmployeeCompletedBy() != null &&
                    entity.getEmployeeCompletedBy().getId() != null &&
                    !entity.getEmployeeCompletedBy().getId().isEmpty()){
                changeRequestDomain.setEmployeeCompletedBy(new EmployeeDomain());
                changeRequestDomain.getEmployeeCompletedBy().setId(entity.getEmployeeCompletedBy().getId());
                if (entity.getEmployeeCompletedBy().getPerson() != null &&
                        entity.getEmployeeCompletedBy().getPerson().getId() != null &&
                        !entity.getEmployeeCompletedBy().getPerson().getId().isEmpty()){
                    changeRequestDomain.getEmployeeCompletedBy().setPerson(new PersonDomain());
                    changeRequestDomain.getEmployeeCompletedBy().getPerson().setId(entity.getEmployeeCompletedBy().getPerson().getId());
                    changeRequestDomain.getEmployeeCompletedBy().getPerson().setFirstName(entity.getEmployeeCompletedBy().getPerson().getFirstName());
                    changeRequestDomain.getEmployeeCompletedBy().getPerson().setLastName(entity.getEmployeeCompletedBy().getPerson().getLastName());
                }
            }
            if (entity.getEmployeeAcceptedBy() != null &&
                    entity.getEmployeeAcceptedBy().getId() != null &&
                    !entity.getEmployeeAcceptedBy().getId().isEmpty()){
                changeRequestDomain.setEmployeeAcceptedBy(new EmployeeDomain());
                changeRequestDomain.getEmployeeAcceptedBy().setId(entity.getEmployeeAcceptedBy().getId());
                if (entity.getEmployeeAcceptedBy().getPerson() != null &&
                        entity.getEmployeeAcceptedBy().getPerson().getId() != null &&
                        !entity.getEmployeeAcceptedBy().getPerson().getId().isEmpty()){
                    changeRequestDomain.getEmployeeAcceptedBy().setPerson(new PersonDomain());
                    changeRequestDomain.getEmployeeAcceptedBy().getPerson().setId(entity.getEmployeeAcceptedBy().getPerson().getId());
                    changeRequestDomain.getEmployeeAcceptedBy().getPerson().setFirstName(entity.getEmployeeAcceptedBy().getPerson().getFirstName());
                    changeRequestDomain.getEmployeeAcceptedBy().getPerson().setLastName(entity.getEmployeeAcceptedBy().getPerson().getLastName());
                }
            }
            return changeRequestDomain;
        }).toList();
    }
}
