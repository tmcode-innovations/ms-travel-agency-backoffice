package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.notification;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.object.ObjectConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.NotificationEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.NotificationDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationConverter implements Converter<NotificationDomain, NotificationEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private ObjectConverter objectConverter;

    @Override
    public NotificationDomain toDomain(NotificationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        NotificationDomain notificationDomain = new NotificationDomain();

        notificationDomain.setId( source.getId() );
        notificationDomain.setDeleted( source.getDeleted() );
        notificationDomain.setEnabled( source.getEnabled() );
        notificationDomain.setMigrated( source.getMigrated() );
        notificationDomain.setCreatedAt( source.getCreatedAt() );
        notificationDomain.setUpdatedAt( source.getUpdatedAt() );
        notificationDomain.setDeletedAt( source.getDeletedAt() );
        notificationDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        notificationDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        notificationDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        notificationDomain.setIdentifierName( source.getIdentifierName() );
        notificationDomain.setTenant( source.getTenant() );
        notificationDomain.setName( source.getName() );
        notificationDomain.setDescription( source.getDescription() );
        notificationDomain.setIdentify( source.getIdentify() );

        notificationDomain.setSubject( source.getSubject() );
        notificationDomain.setRead( source.getRead() );
        notificationDomain.setType( source.getType() );
        notificationDomain.setStatus( source.getStatus() );
        notificationDomain.setDateTime( source.getDateTime() );

        notificationDomain.setEmployeeSource( employeeConverter.toDomain( source.getEmployeeSource() ) );
        notificationDomain.setUserSource( userConverter.toDomain( source.getUserSource() ) );
        notificationDomain.setEmployeeDestine( employeeConverter.toDomain( source.getEmployeeDestine() ) );
        notificationDomain.setUserDestine( userConverter.toDomain( source.getUserDestine() ) );

        notificationDomain.setIdentifyObjectSource( source.getIdentifyObjectSource() );
        notificationDomain.setIdentifyObjectDestine( source.getIdentifyObjectDestine() );

        notificationDomain.setNameObjectSource( source.getNameObjectSource() );
        notificationDomain.setNameObjectDestine( source.getNameObjectDestine() );

        notificationDomain.setIdObjectSource( source.getIdObjectSource() );
        notificationDomain.setIdObjectDestine( source.getIdObjectDestine() );

        notificationDomain.setObjectEntitySource( objectConverter.toDomain( source.getObjectEntitySource() ) );
        notificationDomain.setObjectEntityDestine( objectConverter.toDomain( source.getObjectEntityDestine() ) );

        return notificationDomain;
    }

    @Override
    public NotificationEntity toEntity(NotificationDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        NotificationEntity notificationEntity = new NotificationEntity();

        notificationEntity.setId( source.getId() );
        notificationEntity.setDeleted( source.getDeleted() );
        notificationEntity.setEnabled( source.getEnabled() );
        notificationEntity.setMigrated( source.getMigrated() );
        notificationEntity.setCreatedAt( source.getCreatedAt() );
        notificationEntity.setUpdatedAt( source.getUpdatedAt() );
        notificationEntity.setDeletedAt( source.getDeletedAt() );
        notificationEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        notificationEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        notificationEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        notificationEntity.setIdentifierName( source.getIdentifierName() );
        notificationEntity.setTenant( source.getTenant() );
        notificationEntity.setName( source.getName() );
        notificationEntity.setDescription( source.getDescription() );
        notificationEntity.setIdentify( source.getIdentify() );

        notificationEntity.setSubject( source.getSubject() );
        notificationEntity.setRead( source.getRead() );
        notificationEntity.setType( source.getType() );
        notificationEntity.setStatus( source.getStatus() );
        notificationEntity.setDateTime( source.getDateTime() );

        notificationEntity.setEmployeeSource( employeeConverter.toEntity( source.getEmployeeSource() ) );
        notificationEntity.setUserSource( userConverter.toEntity( source.getUserSource() ) );

        notificationEntity.setEmployeeDestine( employeeConverter.toEntity( source.getEmployeeDestine() ) );
        notificationEntity.setUserDestine( userConverter.toEntity( source.getUserDestine() ) );

        notificationEntity.setIdentifyObjectSource( source.getIdentifyObjectSource() );
        notificationEntity.setIdentifyObjectDestine( source.getIdentifyObjectDestine() );

        notificationEntity.setNameObjectSource( source.getNameObjectSource() );
        notificationEntity.setNameObjectDestine( source.getNameObjectDestine() );

        notificationEntity.setIdObjectSource( source.getIdObjectSource() );
        notificationEntity.setIdObjectDestine( source.getIdObjectDestine() );

        notificationEntity.setObjectEntitySource( objectConverter.toEntity( source.getObjectEntitySource() ) );
        notificationEntity.setObjectEntityDestine( objectConverter.toEntity( source.getObjectEntityDestine() ) );

        return notificationEntity;
    }

    public IdBaseDomain toDomainIdBase(NotificationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(NotificationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<NotificationEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<NotificationDomain> toDomainAllSimpleToDomainCaseGeneric(List<NotificationEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            NotificationDomain notificationDomain = new NotificationDomain();
            notificationDomain.setId(entity.getId());
            notificationDomain.setName(entity.getName());
            notificationDomain.setSubject( entity.getSubject() );
            notificationDomain.setRead( entity.getRead() );
            notificationDomain.setDateTime( entity.getDateTime() );
            notificationDomain.setType( entity.getType() );
            notificationDomain.setStatus( entity.getStatus() );
            notificationDomain.setNameObjectDestine( entity.getNameObjectDestine() );
            notificationDomain.setIdentifyObjectDestine( entity.getIdentifyObjectDestine() );
            notificationDomain.setIdObjectDestine( entity.getIdObjectDestine() );
            notificationDomain.setNameObjectSource( entity.getNameObjectSource() );
            notificationDomain.setIdentifyObjectSource( entity.getIdentifyObjectSource() );
            notificationDomain.setIdObjectSource( entity.getIdObjectSource() );

            if (entity.getObjectEntityDestine() != null &&
                    entity.getObjectEntityDestine().getId() != null &&
                    !entity.getObjectEntityDestine().getId().isEmpty()){
                notificationDomain.setObjectEntityDestine( new ObjectDomain() );
                notificationDomain.getObjectEntityDestine().setId( entity.getObjectEntityDestine().getId() );
                notificationDomain.getObjectEntityDestine().setName( entity.getObjectEntityDestine().getName() );
                notificationDomain.getObjectEntityDestine().setIdentify( entity.getObjectEntityDestine().getIdentify() );
            }
            if (entity.getObjectEntitySource() != null &&
                    entity.getObjectEntitySource().getId() != null &&
                    !entity.getObjectEntitySource().getId().isEmpty()){
                notificationDomain.setObjectEntitySource( new ObjectDomain() );
                notificationDomain.getObjectEntitySource().setId( entity.getObjectEntitySource().getId() );
                notificationDomain.getObjectEntitySource().setName( entity.getObjectEntitySource().getName() );
                notificationDomain.getObjectEntitySource().setIdentify( entity.getObjectEntitySource().getIdentify() );
            }
            if (entity.getUserDestine() != null &&
                    entity.getUserDestine().getId() != null &&
                    !entity.getUserDestine().getId().isEmpty()){
                notificationDomain.setUserDestine( new UserDomain() );
                notificationDomain.getUserDestine().setId( entity.getUserDestine().getId() );
                notificationDomain.getUserDestine().setEmail( entity.getUserDestine().getEmail() );
                notificationDomain.getUserDestine().setUserName( entity.getUserDestine().getUserName() );
                notificationDomain.getUserDestine().setFullName( entity.getUserDestine().getFullName() );
            }
            if (entity.getUserSource() != null &&
                    entity.getUserSource().getId() != null &&
                    !entity.getUserSource().getId().isEmpty()){
                notificationDomain.setUserSource( new UserDomain() );
                notificationDomain.getUserSource().setId( entity.getUserSource().getId() );
                notificationDomain.getUserSource().setEmail( entity.getUserSource().getEmail() );
                notificationDomain.getUserSource().setUserName( entity.getUserSource().getUserName() );
                notificationDomain.getUserSource().setFullName( entity.getUserSource().getFullName() );
            }
            if (entity.getEmployeeDestine() != null &&
                    entity.getEmployeeDestine().getId() != null &&
                    !entity.getEmployeeDestine().getId().isEmpty()){
                notificationDomain.setEmployeeDestine(new EmployeeDomain());
                notificationDomain.getEmployeeDestine().setId(entity.getEmployeeDestine().getId());
                if (entity.getEmployeeDestine().getPerson() != null &&
                        entity.getEmployeeDestine().getPerson().getId() != null &&
                        !entity.getEmployeeDestine().getPerson().getId().isEmpty()){
                    notificationDomain.getEmployeeDestine().setPerson(new PersonDomain());
                    notificationDomain.getEmployeeDestine().getPerson().setId(entity.getEmployeeDestine().getPerson().getId());
                    notificationDomain.getEmployeeDestine().getPerson().setFirstName(entity.getEmployeeDestine().getPerson().getFirstName());
                    notificationDomain.getEmployeeDestine().getPerson().setLastName(entity.getEmployeeDestine().getPerson().getLastName());
                }
            }
            if (entity.getEmployeeSource() != null &&
                    entity.getEmployeeSource().getId() != null &&
                    !entity.getEmployeeSource().getId().isEmpty()){
                notificationDomain.setEmployeeSource(new EmployeeDomain());
                notificationDomain.getEmployeeSource().setId(entity.getEmployeeSource().getId());
                if (entity.getEmployeeSource().getPerson() != null &&
                        entity.getEmployeeSource().getPerson().getId() != null &&
                        !entity.getEmployeeSource().getPerson().getId().isEmpty()){
                    notificationDomain.getEmployeeSource().setPerson(new PersonDomain());
                    notificationDomain.getEmployeeSource().getPerson().setId(entity.getEmployeeSource().getPerson().getId());
                    notificationDomain.getEmployeeSource().getPerson().setFirstName(entity.getEmployeeSource().getPerson().getFirstName());
                    notificationDomain.getEmployeeSource().getPerson().setLastName(entity.getEmployeeSource().getPerson().getLastName());
                }
            }
            return notificationDomain;
        }).toList();
    }

    public List<NotificationDomain> toDomainAllSimpleToDomainCaseListNotification(List<NotificationEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            NotificationDomain notificationDomain = new NotificationDomain();
            notificationDomain.setId(entity.getId());
            notificationDomain.setName(entity.getName());
            notificationDomain.setSubject( entity.getSubject() );
            notificationDomain.setDateTime( entity.getDateTime() );
            notificationDomain.setType( entity.getType() );
            if (entity.getUserSource() != null &&
                    entity.getUserSource().getId() != null &&
                    !entity.getUserSource().getId().isEmpty()){
                notificationDomain.setUserSource( new UserDomain() );
                notificationDomain.getUserSource().setId( entity.getUserSource().getId() );
                notificationDomain.getUserSource().setEmail( entity.getUserSource().getEmail() );
                notificationDomain.getUserSource().setUserName( entity.getUserSource().getUserName() );
                notificationDomain.getUserSource().setFullName( entity.getUserSource().getFullName() );
            }
            return notificationDomain;
        }).toList();
    }

    public PageDTO<NotificationDomain> toDomainPageCaseListNotification(Page<NotificationEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<NotificationDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListNotification(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
