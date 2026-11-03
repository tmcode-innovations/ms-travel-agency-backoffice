package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.idiom;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.IdiomEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomConverter implements Converter<IdiomDomain, IdiomEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public IdiomDomain toDomain(IdiomEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdiomDomain idiomDomain = new IdiomDomain();

        idiomDomain.setId( source.getId() );
        idiomDomain.setDeleted( source.getDeleted() );
        idiomDomain.setEnabled( source.getEnabled() );
        idiomDomain.setMigrated( source.getMigrated() );
        idiomDomain.setCreatedAt( source.getCreatedAt() );
        idiomDomain.setUpdatedAt( source.getUpdatedAt() );
        idiomDomain.setDeletedAt( source.getDeletedAt() );
        idiomDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        idiomDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        idiomDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        idiomDomain.setIdentifierName( source.getIdentifierName() );
        idiomDomain.setTenant( source.getTenant() );
        idiomDomain.setName( source.getName() );
        idiomDomain.setDescription( source.getDescription() );

        return idiomDomain;
    }

    @Override
    public IdiomEntity toEntity(IdiomDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdiomEntity idiomEntity = new IdiomEntity();

        idiomEntity.setId( source.getId() );
        idiomEntity.setDeleted( source.getDeleted() );
        idiomEntity.setEnabled( source.getEnabled() );
        idiomEntity.setMigrated( source.getMigrated() );
        idiomEntity.setCreatedAt( source.getCreatedAt() );
        idiomEntity.setUpdatedAt( source.getUpdatedAt() );
        idiomEntity.setDeletedAt( source.getDeletedAt() );
        idiomEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        idiomEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        idiomEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        idiomEntity.setIdentifierName( source.getIdentifierName() );
        idiomEntity.setTenant( source.getTenant() );
        idiomEntity.setName( source.getName() );
        idiomEntity.setDescription( source.getDescription() );

        return idiomEntity;
    }

    public IdBaseDomain toDomainIdBase(IdiomEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(IdiomEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<IdiomEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<IdiomDomain> toDomainAllSimpleToDomainCaseListIdiom(List<IdiomEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            IdiomDomain idiomDomain = new IdiomDomain();
            idiomDomain.setId(entity.getId());
            idiomDomain.setName(entity.getName());
            idiomDomain.setDescription(entity.getDescription());
            return idiomDomain;
        }).toList();
    }

    public PageDTO<IdiomDomain> toDomainPageCaseListIdiom(Page<IdiomEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<IdiomDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListIdiom(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

}
