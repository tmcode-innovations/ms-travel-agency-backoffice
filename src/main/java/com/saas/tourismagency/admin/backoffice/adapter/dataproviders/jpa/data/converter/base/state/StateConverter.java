package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.state;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.StateEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.StateDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateConverter implements Converter<StateDomain, StateEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private CountryConverter countryConverter;

    @Override
    public StateDomain toDomain(StateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        StateDomain stateDomain = new StateDomain();

        stateDomain.setId( source.getId() );
        stateDomain.setDeleted( source.getDeleted() );
        stateDomain.setEnabled( source.getEnabled() );
        stateDomain.setMigrated( source.getMigrated() );
        stateDomain.setCreatedAt( source.getCreatedAt() );
        stateDomain.setUpdatedAt( source.getUpdatedAt() );
        stateDomain.setDeletedAt( source.getDeletedAt() );
        stateDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        stateDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        stateDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        stateDomain.setIdentifierName( source.getIdentifierName() );
        stateDomain.setTenant( source.getTenant() );
        stateDomain.setName( source.getName() );
        stateDomain.setDescription( source.getDescription() );
        stateDomain.setIdentify( source.getIdentify() );
        stateDomain.setCountry( countryConverter.toDomain( source.getCountry() ) );

        return stateDomain;
    }

    @Override
    public StateEntity toEntity(StateDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        StateEntity stateEntity = new StateEntity();

        stateEntity.setId( source.getId() );
        stateEntity.setDeleted( source.getDeleted() );
        stateEntity.setEnabled( source.getEnabled() );
        stateEntity.setMigrated( source.getMigrated() );
        stateEntity.setCreatedAt( source.getCreatedAt() );
        stateEntity.setUpdatedAt( source.getUpdatedAt() );
        stateEntity.setDeletedAt( source.getDeletedAt() );
        stateEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        stateEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        stateEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        stateEntity.setIdentifierName( source.getIdentifierName() );
        stateEntity.setTenant( source.getTenant() );
        stateEntity.setName( source.getName() );
        stateEntity.setDescription( source.getDescription() );
        stateEntity.setIdentify( source.getIdentify() );
        stateEntity.setCountry( countryConverter.toEntity( source.getCountry() ) );

        return stateEntity;
    }

    public IdBaseDomain toDomainIdBase(StateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(StateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<StateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<StateDomain> toDomainAllSimpleToDomainCaseListState(List<StateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            StateDomain stateDomain = new StateDomain();
            stateDomain.setId(entity.getId());
            stateDomain.setIdentify(entity.getIdentify());
            stateDomain.setName(entity.getName());
            if (entity.getCountry() != null) {
                CountryDomain countryDomain = new CountryDomain();
                countryDomain.setId(entity.getCountry().getId());
                countryDomain.setName(entity.getCountry().getName());
                stateDomain.setCountry(countryDomain);
            }
            return stateDomain;
        }).toList();
    }

    public PageDTO<StateDomain> toDomainPageCaseListState(Page<StateEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<StateDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListState(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
