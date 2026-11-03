package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.grouppqttourist;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.GroupPqtTouristEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.GroupPqtTouristDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupPqtTouristConverter implements Converter<GroupPqtTouristDomain, GroupPqtTouristEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public GroupPqtTouristDomain toDomain(GroupPqtTouristEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        GroupPqtTouristDomain groupPqtTouristDomain = new GroupPqtTouristDomain();

        groupPqtTouristDomain.setId( source.getId() );
        groupPqtTouristDomain.setDeleted( source.getDeleted() );
        groupPqtTouristDomain.setEnabled( source.getEnabled() );
        groupPqtTouristDomain.setMigrated( source.getMigrated() );
        groupPqtTouristDomain.setCreatedAt( source.getCreatedAt() );
        groupPqtTouristDomain.setUpdatedAt( source.getUpdatedAt() );
        groupPqtTouristDomain.setDeletedAt( source.getDeletedAt() );
        groupPqtTouristDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        groupPqtTouristDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        groupPqtTouristDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        groupPqtTouristDomain.setIdentifierName( source.getIdentifierName() );
        groupPqtTouristDomain.setTenant( source.getTenant() );
        groupPqtTouristDomain.setName( source.getName() );
        groupPqtTouristDomain.setDescription( source.getDescription() );
        groupPqtTouristDomain.setIdentify( source.getIdentify() );

        return groupPqtTouristDomain;
    }

    @Override
    public GroupPqtTouristEntity toEntity(GroupPqtTouristDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        GroupPqtTouristEntity groupPqtTouristEntity = new GroupPqtTouristEntity();

        groupPqtTouristEntity.setId( source.getId() );
        groupPqtTouristEntity.setDeleted( source.getDeleted() );
        groupPqtTouristEntity.setEnabled( source.getEnabled() );
        groupPqtTouristEntity.setMigrated( source.getMigrated() );
        groupPqtTouristEntity.setCreatedAt( source.getCreatedAt() );
        groupPqtTouristEntity.setUpdatedAt( source.getUpdatedAt() );
        groupPqtTouristEntity.setDeletedAt( source.getDeletedAt() );
        groupPqtTouristEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        groupPqtTouristEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        groupPqtTouristEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        groupPqtTouristEntity.setIdentifierName( source.getIdentifierName() );
        groupPqtTouristEntity.setTenant( source.getTenant() );
        groupPqtTouristEntity.setName( source.getName() );
        groupPqtTouristEntity.setDescription( source.getDescription() );
        groupPqtTouristEntity.setIdentify( source.getIdentify() );

        return groupPqtTouristEntity;
    }

    public List<GroupPqtTouristDomain> toDomainAllSimpleToDomainCaseListGroupPqtTourist(List<GroupPqtTouristEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            GroupPqtTouristDomain groupPqtTouristDomain = new GroupPqtTouristDomain();
            groupPqtTouristDomain.setId(entity.getId());
            groupPqtTouristDomain.setIdentify(entity.getIdentify());
            groupPqtTouristDomain.setName(entity.getName());
            return groupPqtTouristDomain;
        }).toList();
    }

    public PageDTO<GroupPqtTouristDomain> toDomainPageCaseListGroupPqtTourist(Page<GroupPqtTouristEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<GroupPqtTouristDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListGroupPqtTourist(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

}
