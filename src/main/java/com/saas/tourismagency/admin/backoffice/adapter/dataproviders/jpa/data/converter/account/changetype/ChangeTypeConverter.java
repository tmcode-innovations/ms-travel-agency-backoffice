package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ChangeTypeEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DateProcessDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangeTypeConverter implements Converter<ChangeTypeDomain, ChangeTypeEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private DateProcessConverter dateProcessConverter;

    @Override
    public ChangeTypeDomain toDomain(ChangeTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ChangeTypeDomain changeTypeDomain = new ChangeTypeDomain();

        changeTypeDomain.setId( source.getId() );
        changeTypeDomain.setDeleted( source.getDeleted() );
        changeTypeDomain.setEnabled( source.getEnabled() );
        changeTypeDomain.setMigrated( source.getMigrated() );
        changeTypeDomain.setCreatedAt( source.getCreatedAt() );
        changeTypeDomain.setUpdatedAt( source.getUpdatedAt() );
        changeTypeDomain.setDeletedAt( source.getDeletedAt() );
        changeTypeDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        changeTypeDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        changeTypeDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        changeTypeDomain.setIdentifierName( source.getIdentifierName() );
        changeTypeDomain.setTenant( source.getTenant() );
        changeTypeDomain.setName( source.getName() );
        changeTypeDomain.setDescription( source.getDescription() );
        changeTypeDomain.setIdentify( source.getIdentify() );
        changeTypeDomain.setPriceSale( source.getPriceSale() );
        changeTypeDomain.setDate( source.getDate() );
        changeTypeDomain.setDateProcess( dateProcessConverter.toDomain( source.getDateProcess() ) );
        return changeTypeDomain;
    }

    @Override
    public ChangeTypeEntity toEntity(ChangeTypeDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ChangeTypeEntity changeTypeEntity = new ChangeTypeEntity();

        changeTypeEntity.setId( source.getId() );
        changeTypeEntity.setDeleted( source.getDeleted() );
        changeTypeEntity.setEnabled( source.getEnabled() );
        changeTypeEntity.setMigrated( source.getMigrated() );
        changeTypeEntity.setCreatedAt( source.getCreatedAt() );
        changeTypeEntity.setUpdatedAt( source.getUpdatedAt() );
        changeTypeEntity.setDeletedAt( source.getDeletedAt() );
        changeTypeEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        changeTypeEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        changeTypeEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        changeTypeEntity.setIdentifierName( source.getIdentifierName() );
        changeTypeEntity.setTenant( source.getTenant() );
        changeTypeEntity.setName( source.getName() );
        changeTypeEntity.setDescription( source.getDescription() );
        changeTypeEntity.setIdentify( source.getIdentify() );
        changeTypeEntity.setPriceSale( source.getPriceSale() );
        changeTypeEntity.setDate( source.getDate() );
        changeTypeEntity.setDateProcess( dateProcessConverter.toEntity( source.getDateProcess() ) );
        return changeTypeEntity;
    }

    public IdBaseDomain toDomainIdBase(ChangeTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ChangeTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ChangeTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ChangeTypeDomain> toDomainAllSimpleToDomainCaseListEntity(List<ChangeTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ChangeTypeDomain changeTypeDomain = new ChangeTypeDomain();

            return changeTypeDomain;
        }).toList();
    }

    public List<ChangeTypeDomain> toDomainAllSimpleToDomainCaseShowChangeType(List<ChangeTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ChangeTypeDomain changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(entity.getId());
            changeTypeDomain.setName(entity.getName());
            changeTypeDomain.setDescription(entity.getDescription());
            return changeTypeDomain;
        }).toList();
    }

    public List<ChangeTypeDomain> toDomainAllSimpleToDomainCaseListChangeType(List<ChangeTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ChangeTypeDomain changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(entity.getId());
            changeTypeDomain.setName(entity.getName());
            changeTypeDomain.setDescription(entity.getDescription());
            changeTypeDomain.setDate(entity.getDate());
            changeTypeDomain.setPriceSale(entity.getPriceSale());
            if (entity.getDateProcess() != null &&
                    entity.getDateProcess().getId() != null &&
                    !entity.getDateProcess().getId().isEmpty()){
                changeTypeDomain.setDateProcess(new DateProcessDomain());
                changeTypeDomain.getDateProcess().setId(entity.getDateProcess().getId());
                changeTypeDomain.getDateProcess().setName(entity.getDateProcess().getName());
                changeTypeDomain.getDateProcess().setDescription(entity.getDateProcess().getDescription());
                changeTypeDomain.getDateProcess().setIdentify(entity.getDateProcess().getIdentify());
                changeTypeDomain.getDateProcess().setDate(entity.getDateProcess().getDate());
            }
            return changeTypeDomain;
        }).toList();
    }

    public PageDTO<ChangeTypeDomain> toDomainPageCaseListChangeType(Page<ChangeTypeEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ChangeTypeDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListChangeType(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public List<ChangeTypeDomain> toDomainAllSimpleToDomainCaseToolUpdateChangeType(List<ChangeTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ChangeTypeDomain changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId( entity.getId() );
            if (entity.getDateProcess() != null) {
                changeTypeDomain.setDateProcess(new DateProcessDomain());
                changeTypeDomain.getDateProcess().setId(entity.getDateProcess().getId());
                changeTypeDomain.getDateProcess().setDate(entity.getDateProcess().getDate());
            }
            return changeTypeDomain;
        }).toList();
    }
}
