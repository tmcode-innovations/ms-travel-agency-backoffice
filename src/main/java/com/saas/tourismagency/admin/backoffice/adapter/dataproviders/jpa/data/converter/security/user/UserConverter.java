package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.app.AppConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.profile.ProfileConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.AppEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.ProfileEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.AppDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ProfileDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import dev.mm.javaee.core.domain.UserBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserConverter implements Converter<UserDomain, UserEntity> {

    private ProfileConverter profileConverter;

    private AppConverter appConverter;

    @Autowired
    public UserConverter(
            @Lazy ProfileConverter profileConverter,
            @Lazy AppConverter appConverter
    ) {
        this.profileConverter = profileConverter;
        this.appConverter = appConverter;
    }

    @Override
    public UserDomain toDomain(UserEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        UserDomain userDomain = new UserDomain();

        userDomain.setId( source.getId() );
        userDomain.setDeleted( source.getDeleted() );
        userDomain.setEnabled( source.getEnabled() );
        userDomain.setMigrated( source.getMigrated() );
        userDomain.setCreatedAt( source.getCreatedAt() );
        userDomain.setUpdatedAt( source.getUpdatedAt() );
        userDomain.setDeletedAt( source.getDeletedAt() );
        userDomain.setCreatedBy( userEntityToUserBaseDomain( source.getCreatedBy() ) );
        userDomain.setUpdatedBy( userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        userDomain.setDeletedBy( userEntityToUserBaseDomain( source.getDeletedBy() ) );
        userDomain.setIdentifierName( source.getIdentifierName() );
        userDomain.setTenant( source.getTenant() );
        userDomain.setUserName( source.getUserName() );
        userDomain.setEmail( source.getEmail() );
        userDomain.setFullName( source.getFullName() );
        userDomain.setDescription( source.getDescription() );
        userDomain.setProfiles( profileEntitySetToProfileDomainSet( source.getProfiles() ) );
        userDomain.setApps( appEntitySetToAppDomainSet( source.getApps() ) );
        return userDomain;
    }

    @Override
    public UserEntity toEntity(UserDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( source.getId() );
        userEntity.setDeleted( source.getDeleted() );
        userEntity.setEnabled( source.getEnabled() );
        userEntity.setMigrated( source.getMigrated() );
        userEntity.setCreatedAt( source.getCreatedAt() );
        userEntity.setUpdatedAt( source.getUpdatedAt() );
        userEntity.setDeletedAt( source.getDeletedAt() );
        userEntity.setCreatedBy( userBaseDomainToUserEntity( source.getCreatedBy() ) );
        userEntity.setUpdatedBy( userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        userEntity.setDeletedBy( userBaseDomainToUserEntity( source.getDeletedBy() ) );
        userEntity.setIdentifierName( source.getIdentifierName() );
        userEntity.setTenant( source.getTenant() );
        userEntity.setUserName( source.getUserName() );
        userEntity.setEmail( source.getEmail() );
        userEntity.setFullName( source.getFullName() );
        userEntity.setDescription( source.getDescription() );
        userEntity.setProfiles( profileDomainSetToProfileEntitySet( source.getProfiles() ) );
        userEntity.setApps( appDomainSetToAppEntitySet( source.getApps() ) );
        return userEntity;
    }

    public UserBaseDomain userEntityToUserBaseDomain(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserBaseDomain userBaseDomain = new UserBaseDomain();

        userBaseDomain.setId( userEntity.getId() );
        userBaseDomain.setDeleted( userEntity.getDeleted() );
        userBaseDomain.setEnabled( userEntity.getEnabled() );
        userBaseDomain.setMigrated( userEntity.getMigrated() );
        userBaseDomain.setCreatedAt( userEntity.getCreatedAt() );
        userBaseDomain.setUpdatedAt( userEntity.getUpdatedAt() );
        userBaseDomain.setDeletedAt( userEntity.getDeletedAt() );
        userBaseDomain.setIdentifierName( userEntity.getIdentifierName() );
        userBaseDomain.setTenant( userEntity.getTenant() );
        userBaseDomain.setUserName( userEntity.getUserName() );
        userBaseDomain.setEmail( userEntity.getEmail() );
        userBaseDomain.setFullName( userEntity.getFullName() );

        return userBaseDomain;
    }

    public UserEntity userBaseDomainToUserEntity(UserBaseDomain userBaseDomain) {
        if ( userBaseDomain == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( userBaseDomain.getId() );
        userEntity.setDeleted( userBaseDomain.getDeleted() );
        userEntity.setEnabled( userBaseDomain.getEnabled() );
        userEntity.setMigrated( userBaseDomain.getMigrated() );
        userEntity.setCreatedAt( userBaseDomain.getCreatedAt() );
        userEntity.setUpdatedAt( userBaseDomain.getUpdatedAt() );
        userEntity.setDeletedAt( userBaseDomain.getDeletedAt() );
        userEntity.setIdentifierName( userBaseDomain.getIdentifierName() );
        userEntity.setTenant( userBaseDomain.getTenant() );
        userEntity.setUserName( userBaseDomain.getUserName() );
        userEntity.setEmail( userBaseDomain.getEmail() );
        userEntity.setFullName( userBaseDomain.getFullName() );

        return userEntity;
    }

    public Set<ProfileDomain> profileEntitySetToProfileDomainSet(Set<ProfileEntity> set) {
        if ( set == null ) {
            return null;
        }
        Set<ProfileDomain> set1 = new LinkedHashSet<>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProfileEntity profileEntity : set ) {
            set1.add( profileConverter.toDomain( profileEntity ) );
        }
        return set1;
    }

    public Set<ProfileEntity> profileDomainSetToProfileEntitySet(Set<ProfileDomain> set) {
        if ( set == null ) {
            return null;
        }
        Set<ProfileEntity> set1 = new LinkedHashSet<ProfileEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProfileDomain profileDomain : set ) {
            set1.add( profileConverter.toEntity( profileDomain ) );
        }
        return set1;
    }

    public Set<AppDomain> appEntitySetToAppDomainSet(Set<AppEntity> set) {
        if ( set == null ) {
            return null;
        }
        Set<AppDomain> set1 = new LinkedHashSet<>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AppEntity appEntity : set ) {
            set1.add( appConverter.toDomain( appEntity ) );
        }
        return set1;
    }

    public Set<AppEntity> appDomainSetToAppEntitySet(Set<AppDomain> set) {
        if ( set == null ) {
            return null;
        }
        Set<AppEntity> set1 = new LinkedHashSet<AppEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AppDomain appDomain : set ) {
            set1.add( appConverter.toEntity( appDomain ) );
        }
        return set1;
    }

    public IdBaseDomain toDomainIdBase(UserEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(UserEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getFullName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<UserEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<UserDomain> toDomainAllSimpleToDomainCaseListUser(List<UserEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            UserDomain userDomain = new UserDomain();
            userDomain.setId(entity.getId());
            userDomain.setUserName(entity.getUserName());
            userDomain.setEmail(entity.getEmail());
            userDomain.setFullName(entity.getFullName());
            userDomain.setDescription(entity.getDescription());
            return userDomain;
        }).toList();
    }

    public PageDTO<UserDomain> toDomainPageCaseListUser(Page<UserEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<UserDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListUser(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
