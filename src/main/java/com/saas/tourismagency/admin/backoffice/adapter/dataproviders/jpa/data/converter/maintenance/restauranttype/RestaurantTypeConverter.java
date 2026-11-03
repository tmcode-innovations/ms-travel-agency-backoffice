package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.restauranttype;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.RestaurantTypeEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.RestaurantTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantTypeConverter implements Converter<RestaurantTypeDomain, RestaurantTypeEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public RestaurantTypeDomain toDomain(RestaurantTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RestaurantTypeDomain restaurantTypeDomain = new RestaurantTypeDomain();

        restaurantTypeDomain.setId( source.getId() );
        restaurantTypeDomain.setDeleted( source.getDeleted() );
        restaurantTypeDomain.setEnabled( source.getEnabled() );
        restaurantTypeDomain.setMigrated( source.getMigrated() );
        restaurantTypeDomain.setCreatedAt( source.getCreatedAt() );
        restaurantTypeDomain.setUpdatedAt( source.getUpdatedAt() );
        restaurantTypeDomain.setDeletedAt( source.getDeletedAt() );
        restaurantTypeDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        restaurantTypeDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        restaurantTypeDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        restaurantTypeDomain.setIdentifierName( source.getIdentifierName() );
        restaurantTypeDomain.setTenant( source.getTenant() );
        restaurantTypeDomain.setName( source.getName() );
        restaurantTypeDomain.setDescription( source.getDescription() );
        restaurantTypeDomain.setIdentify( source.getIdentify() );

        return restaurantTypeDomain;
    }

    @Override
    public RestaurantTypeEntity toEntity(RestaurantTypeDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RestaurantTypeEntity restaurantTypeEntity = new RestaurantTypeEntity();

        restaurantTypeEntity.setId( source.getId() );
        restaurantTypeEntity.setDeleted( source.getDeleted() );
        restaurantTypeEntity.setEnabled( source.getEnabled() );
        restaurantTypeEntity.setMigrated( source.getMigrated() );
        restaurantTypeEntity.setCreatedAt( source.getCreatedAt() );
        restaurantTypeEntity.setUpdatedAt( source.getUpdatedAt() );
        restaurantTypeEntity.setDeletedAt( source.getDeletedAt() );
        restaurantTypeEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        restaurantTypeEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        restaurantTypeEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        restaurantTypeEntity.setIdentifierName( source.getIdentifierName() );
        restaurantTypeEntity.setTenant( source.getTenant() );
        restaurantTypeEntity.setName( source.getName() );
        restaurantTypeEntity.setDescription( source.getDescription() );
        restaurantTypeEntity.setIdentify( source.getIdentify() );

        return restaurantTypeEntity;
    }

    public IdBaseDomain toDomainIdBase(RestaurantTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RestaurantTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RestaurantTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RestaurantTypeDomain> toDomainAllSimpleToDomainCaseListRestaurantType(List<RestaurantTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RestaurantTypeDomain restaurantTypeDomain = new RestaurantTypeDomain();
            restaurantTypeDomain.setId(entity.getId());
            restaurantTypeDomain.setIdentify(entity.getIdentify());
            restaurantTypeDomain.setName(entity.getName());
            return restaurantTypeDomain;
        }).toList();
    }

    public PageDTO<RestaurantTypeDomain> toDomainPageCaseListRestaurantType(Page<RestaurantTypeEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<RestaurantTypeDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListRestaurantType(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

}
