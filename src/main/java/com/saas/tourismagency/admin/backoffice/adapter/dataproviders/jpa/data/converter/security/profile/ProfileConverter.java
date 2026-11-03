package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.profile;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.permission.PermissionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.PermissionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.ProfileEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.PermissionDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ProfileDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProfileConverter implements Converter<ProfileDomain, ProfileEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PermissionConverter permissionConverter;

    @Override
    public ProfileDomain toDomain(ProfileEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProfileDomain profileDomain = new ProfileDomain();

        profileDomain.setId( source.getId() );
        profileDomain.setDeleted( source.getDeleted() );
        profileDomain.setEnabled( source.getEnabled() );
        profileDomain.setMigrated( source.getMigrated() );
        profileDomain.setCreatedAt( source.getCreatedAt() );
        profileDomain.setUpdatedAt( source.getUpdatedAt() );
        profileDomain.setDeletedAt( source.getDeletedAt() );
        profileDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        profileDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        profileDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        profileDomain.setIdentifierName( source.getIdentifierName() );
        profileDomain.setTenant( source.getTenant() );
        profileDomain.setName( source.getName() );
        profileDomain.setDescription( source.getDescription() );
        profileDomain.setIdentify( source.getIdentify() );
        profileDomain.setPermissions( permissionEntitySetToPermissionDomainSet( source.getPermissions() ) );
        return profileDomain;
    }

    @Override
    public ProfileEntity toEntity(ProfileDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProfileEntity profileEntity = new ProfileEntity();

        profileEntity.setId( source.getId() );
        profileEntity.setDeleted( source.getDeleted() );
        profileEntity.setEnabled( source.getEnabled() );
        profileEntity.setMigrated( source.getMigrated() );
        profileEntity.setCreatedAt( source.getCreatedAt() );
        profileEntity.setUpdatedAt( source.getUpdatedAt() );
        profileEntity.setDeletedAt( source.getDeletedAt() );
        profileEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        profileEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        profileEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        profileEntity.setIdentifierName( source.getIdentifierName() );
        profileEntity.setTenant( source.getTenant() );
        profileEntity.setName( source.getName() );
        profileEntity.setDescription( source.getDescription() );
        profileEntity.setIdentify( source.getIdentify() );
        profileEntity.setPermissions( permissionDomainSetToPermissionEntitySet( source.getPermissions() ) );
        return profileEntity;
    }

    public Set<PermissionDomain> permissionEntitySetToPermissionDomainSet(Set<PermissionEntity> set) {
        if ( set == null ) {
            return null;
        }
        Set<PermissionDomain> set1 = new LinkedHashSet<>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PermissionEntity permissionEntity : set ) {
            set1.add( permissionConverter.toDomain( permissionEntity ) );
        }
        return set1;
    }

    protected Set<PermissionEntity> permissionDomainSetToPermissionEntitySet(Set<PermissionDomain> set) {
        if ( set == null ) {
            return null;
        }
        Set<PermissionEntity> set1 = new LinkedHashSet<PermissionEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PermissionDomain permissionDomain : set ) {
            set1.add( permissionConverter.toEntity( permissionDomain ) );
        }
        return set1;
    }

    public IdBaseDomain toDomainIdBase(ProfileEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ProfileEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ProfileEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ProfileDomain> toDomainAllSimpleToDomainCaseListProfile(List<ProfileEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ProfileDomain profileDomain = new ProfileDomain();
            profileDomain.setId(entity.getId());
            profileDomain.setName(entity.getName());
            profileDomain.setIdentify(entity.getIdentify());
            return profileDomain;
        }).toList();
    }

    public PageDTO<ProfileDomain> toDomainPageCaseListProfile(Page<ProfileEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ProfileDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListProfile(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
