package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.costsheettwopercent;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.CostSheetTwoPercentEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CostSheetTwoPercentDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostSheetTwoPercentConverter implements Converter<CostSheetTwoPercentDomain, CostSheetTwoPercentEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public CostSheetTwoPercentDomain toDomain(CostSheetTwoPercentEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CostSheetTwoPercentDomain costSheetTwoPercentDomain = new CostSheetTwoPercentDomain();

        costSheetTwoPercentDomain.setId( source.getId() );
        costSheetTwoPercentDomain.setDeleted( source.getDeleted() );
        costSheetTwoPercentDomain.setEnabled( source.getEnabled() );
        costSheetTwoPercentDomain.setMigrated( source.getMigrated() );
        costSheetTwoPercentDomain.setCreatedAt( source.getCreatedAt() );
        costSheetTwoPercentDomain.setUpdatedAt( source.getUpdatedAt() );
        costSheetTwoPercentDomain.setDeletedAt( source.getDeletedAt() );
        costSheetTwoPercentDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        costSheetTwoPercentDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        costSheetTwoPercentDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        costSheetTwoPercentDomain.setIdentifierName( source.getIdentifierName() );
        costSheetTwoPercentDomain.setTenant( source.getTenant() );
        costSheetTwoPercentDomain.setName( source.getName() );
        costSheetTwoPercentDomain.setDescription( source.getDescription() );
        costSheetTwoPercentDomain.setAvailable( source.getAvailable() );
        costSheetTwoPercentDomain.setPercentMin( source.getPercentMin() );
        costSheetTwoPercentDomain.setPercentMax( source.getPercentMax() );

        return costSheetTwoPercentDomain;
    }

    @Override
    public CostSheetTwoPercentEntity toEntity(CostSheetTwoPercentDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CostSheetTwoPercentEntity costSheetTwoPercentEntity = new CostSheetTwoPercentEntity();

        costSheetTwoPercentEntity.setId( source.getId() );
        costSheetTwoPercentEntity.setDeleted( source.getDeleted() );
        costSheetTwoPercentEntity.setEnabled( source.getEnabled() );
        costSheetTwoPercentEntity.setMigrated( source.getMigrated() );
        costSheetTwoPercentEntity.setCreatedAt( source.getCreatedAt() );
        costSheetTwoPercentEntity.setUpdatedAt( source.getUpdatedAt() );
        costSheetTwoPercentEntity.setDeletedAt( source.getDeletedAt() );
        costSheetTwoPercentEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        costSheetTwoPercentEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        costSheetTwoPercentEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        costSheetTwoPercentEntity.setIdentifierName( source.getIdentifierName() );
        costSheetTwoPercentEntity.setTenant( source.getTenant() );
        costSheetTwoPercentEntity.setName( source.getName() );
        costSheetTwoPercentEntity.setDescription( source.getDescription() );
        costSheetTwoPercentEntity.setAvailable( source.getAvailable() );
        costSheetTwoPercentEntity.setPercentMin( source.getPercentMin() );
        costSheetTwoPercentEntity.setPercentMax( source.getPercentMax() );

        return costSheetTwoPercentEntity;
    }

    public IdBaseDomain toDomainIdBase(CostSheetTwoPercentEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(CostSheetTwoPercentEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<CostSheetTwoPercentEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<CostSheetTwoPercentDomain> toDomainAllSimpleToDomainCaseListCostSheetTwoPercent(List<CostSheetTwoPercentEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostSheetTwoPercentDomain costSheetTwoPercentDomain = new CostSheetTwoPercentDomain();
            costSheetTwoPercentDomain.setId(entity.getId());
            costSheetTwoPercentDomain.setName(entity.getName());
            costSheetTwoPercentDomain.setPercentMin(entity.getPercentMin());
            costSheetTwoPercentDomain.setPercentMax(entity.getPercentMax());
            costSheetTwoPercentDomain.setAvailable(entity.getAvailable());
            return costSheetTwoPercentDomain;
        }).toList();
    }

    public PageDTO<CostSheetTwoPercentDomain> toDomainPageCaseListCostSheetTwoPercent(Page<CostSheetTwoPercentEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<CostSheetTwoPercentDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListCostSheetTwoPercent(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
