package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.accessvoucheregress;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.AccessVoucherEgressEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.AccessVoucherEgressDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessVoucherEgressConverter implements Converter<AccessVoucherEgressDomain, AccessVoucherEgressEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public AccessVoucherEgressDomain toDomain(AccessVoucherEgressEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        AccessVoucherEgressDomain accessVoucherEgressDomain = new AccessVoucherEgressDomain();

        accessVoucherEgressDomain.setId( source.getId() );
        accessVoucherEgressDomain.setDeleted( source.getDeleted() );
        accessVoucherEgressDomain.setEnabled( source.getEnabled() );
        accessVoucherEgressDomain.setMigrated( source.getMigrated() );
        accessVoucherEgressDomain.setCreatedAt( source.getCreatedAt() );
        accessVoucherEgressDomain.setUpdatedAt( source.getUpdatedAt() );
        accessVoucherEgressDomain.setDeletedAt( source.getDeletedAt() );
        accessVoucherEgressDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        accessVoucherEgressDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        accessVoucherEgressDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        accessVoucherEgressDomain.setIdentifierName( source.getIdentifierName() );
        accessVoucherEgressDomain.setTenant( source.getTenant() );
        accessVoucherEgressDomain.setName( source.getName() );
        accessVoucherEgressDomain.setDescription( source.getDescription() );
        accessVoucherEgressDomain.setIdentify( source.getIdentify() );

        if (source.getUser() != null &&
                source.getUser().getId() != null &&
                !source.getUser().getId().isEmpty()) {
            accessVoucherEgressDomain.setUser( userConverter.toDomain( source.getUser() )  );
        }else{
            accessVoucherEgressDomain.setUser( null );
        }

        return accessVoucherEgressDomain;
    }

    @Override
    public AccessVoucherEgressEntity toEntity(AccessVoucherEgressDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        AccessVoucherEgressEntity accessVoucherEgressEntity = new AccessVoucherEgressEntity();

        accessVoucherEgressEntity.setId( source.getId() );
        accessVoucherEgressEntity.setDeleted( source.getDeleted() );
        accessVoucherEgressEntity.setEnabled( source.getEnabled() );
        accessVoucherEgressEntity.setMigrated( source.getMigrated() );
        accessVoucherEgressEntity.setCreatedAt( source.getCreatedAt() );
        accessVoucherEgressEntity.setUpdatedAt( source.getUpdatedAt() );
        accessVoucherEgressEntity.setDeletedAt( source.getDeletedAt() );
        accessVoucherEgressEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        accessVoucherEgressEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        accessVoucherEgressEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        accessVoucherEgressEntity.setIdentifierName( source.getIdentifierName() );
        accessVoucherEgressEntity.setTenant( source.getTenant() );
        accessVoucherEgressEntity.setName( source.getName() );
        accessVoucherEgressEntity.setDescription( source.getDescription() );
        accessVoucherEgressEntity.setIdentify( source.getIdentify() );

        if (source.getUser() != null &&
                source.getUser().getId() != null &&
                !source.getUser().getId().isEmpty()) {
            accessVoucherEgressEntity.setUser( userConverter.toEntity( source.getUser() )  );
        }else{
            accessVoucherEgressEntity.setUser( null );
        }

        return accessVoucherEgressEntity;
    }

    public IdBaseDomain toDomainIdBase(AccessVoucherEgressEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(AccessVoucherEgressEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setId( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<AccessVoucherEgressEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<AccessVoucherEgressDomain> toDomainAllSimpleToDomain(List<AccessVoucherEgressEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            AccessVoucherEgressDomain accessVoucherEgressDomain = new AccessVoucherEgressDomain();
            accessVoucherEgressDomain.setId(entity.getId());
            accessVoucherEgressDomain.setName(entity.getName());
            return accessVoucherEgressDomain;
        }).toList();
    }

    public List<AccessVoucherEgressDomain> toDomainAllSimpleToDomainCaseListAccessVoucherEgress(List<AccessVoucherEgressEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            AccessVoucherEgressDomain accessVoucherEgressDomain = new AccessVoucherEgressDomain();
            accessVoucherEgressDomain.setId(entity.getId());
            accessVoucherEgressDomain.setName(entity.getName());
            accessVoucherEgressDomain.setDescription(entity.getDescription());
            if (entity.getUser() != null &&
                    entity.getUser().getId() != null &&
                    !entity.getUser().getId().isEmpty()){
                accessVoucherEgressDomain.setUser(new UserDomain());
                accessVoucherEgressDomain.getUser().setId(entity.getUser().getId());
                accessVoucherEgressDomain.getUser().setUserName(entity.getUser().getUserName());
                accessVoucherEgressDomain.getUser().setEmail(entity.getUser().getEmail());
                accessVoucherEgressDomain.getUser().setFullName(entity.getUser().getFullName());
            }
            return accessVoucherEgressDomain;
        }).toList();
    }

    public PageDTO<AccessVoucherEgressDomain> toDomainPageCaseListAccessVoucherEgress(Page<AccessVoucherEgressEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<AccessVoucherEgressDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListAccessVoucherEgress(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

}
