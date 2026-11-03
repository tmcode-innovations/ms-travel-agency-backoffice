package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.person;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PersonEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonConverter implements Converter<PersonDomain, PersonEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public PersonDomain toDomain(PersonEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PersonDomain personDomain = new PersonDomain();

        personDomain.setId( source.getId() );
        personDomain.setDeleted( source.getDeleted() );
        personDomain.setEnabled( source.getEnabled() );
        personDomain.setMigrated( source.getMigrated() );
        personDomain.setCreatedAt( source.getCreatedAt() );
        personDomain.setUpdatedAt( source.getUpdatedAt() );
        personDomain.setDeletedAt( source.getDeletedAt() );
        personDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        personDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        personDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        personDomain.setIdentifierName( source.getIdentifierName() );
        personDomain.setTenant( source.getTenant() );
        personDomain.setName( source.getName() );
        personDomain.setDescription( source.getDescription() );
        personDomain.setIdentify( source.getIdentify() );
        personDomain.setFirstName( source.getFirstName() );
        personDomain.setLastName( source.getLastName() );
        personDomain.setAge( source.getAge() );
        personDomain.setDateBirthday( source.getDateBirthday() );
        personDomain.setIdentifyPerson( source.getIdentifyPerson() );
        personDomain.setGender( source.getGender() );

        return personDomain;
    }

    @Override
    public PersonEntity toEntity(PersonDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setId( source.getId() );
        personEntity.setDeleted( source.getDeleted() );
        personEntity.setEnabled( source.getEnabled() );
        personEntity.setMigrated( source.getMigrated() );
        personEntity.setCreatedAt( source.getCreatedAt() );
        personEntity.setUpdatedAt( source.getUpdatedAt() );
        personEntity.setDeletedAt( source.getDeletedAt() );
        personEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        personEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        personEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        personEntity.setIdentifierName( source.getIdentifierName() );
        personEntity.setTenant( source.getTenant() );
        personEntity.setName( source.getName() );
        personEntity.setDescription( source.getDescription() );
        personEntity.setIdentify( source.getIdentify() );
        personEntity.setFirstName( source.getFirstName() );
        personEntity.setLastName( source.getLastName() );
        personEntity.setAge( source.getAge() );
        personEntity.setDateBirthday( source.getDateBirthday() );
        personEntity.setIdentifyPerson( source.getIdentifyPerson() );
        personEntity.setGender( source.getGender() );

        return personEntity;
    }

    public IdBaseDomain toDomainIdBase(PersonEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(PersonEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<PersonEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
