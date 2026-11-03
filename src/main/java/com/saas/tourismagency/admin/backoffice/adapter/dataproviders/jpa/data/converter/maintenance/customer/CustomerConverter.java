package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.partner.PartnerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.CustomerEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerConverter implements Converter<CustomerDomain, CustomerEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PartnerConverter partnerConverter;

    @Override
    public CustomerDomain toDomain(CustomerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CustomerDomain customerDomain = new CustomerDomain();

        customerDomain.setId( source.getId() );
        customerDomain.setDeleted( source.getDeleted() );
        customerDomain.setEnabled( source.getEnabled() );
        customerDomain.setMigrated( source.getMigrated() );
        customerDomain.setCreatedAt( source.getCreatedAt() );
        customerDomain.setUpdatedAt( source.getUpdatedAt() );
        customerDomain.setDeletedAt( source.getDeletedAt() );
        customerDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        customerDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        customerDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        customerDomain.setIdentifierName( source.getIdentifierName() );
        customerDomain.setTenant( source.getTenant() );
        customerDomain.setName( source.getName() );
        customerDomain.setDescription( source.getDescription() );
        customerDomain.setIdentify( source.getIdentify() );
        customerDomain.setTypeInheritance( source.getTypeInheritance() );
        customerDomain.setRuc( source.getRuc() );
        customerDomain.setCustomerType( source.getCustomerType() );
        customerDomain.setPartner( partnerConverter.toDomain( source.getPartner() ) );

        return customerDomain;
    }

    @Override
    public CustomerEntity toEntity(CustomerDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId( source.getId() );
        customerEntity.setDeleted( source.getDeleted() );
        customerEntity.setEnabled( source.getEnabled() );
        customerEntity.setMigrated( source.getMigrated() );
        customerEntity.setCreatedAt( source.getCreatedAt() );
        customerEntity.setUpdatedAt( source.getUpdatedAt() );
        customerEntity.setDeletedAt( source.getDeletedAt() );
        customerEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        customerEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        customerEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        customerEntity.setIdentifierName( source.getIdentifierName() );
        customerEntity.setTenant( source.getTenant() );
        customerEntity.setName( source.getName() );
        customerEntity.setDescription( source.getDescription() );
        customerEntity.setIdentify( source.getIdentify() );
        customerEntity.setTypeInheritance( source.getTypeInheritance() );
        customerEntity.setRuc( source.getRuc() );
        customerEntity.setCustomerType( source.getCustomerType() );
        customerEntity.setPartner( partnerConverter.toEntity( source.getPartner() ) );

        return customerEntity;
    }

    public IdBaseDomain toDomainIdBase(CustomerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(CustomerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<CustomerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public CustomerDomain toDomainNameBase(CustomerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CustomerDomain customerDomain = new CustomerDomain();

        customerDomain.setId( source.getId() );
        return customerDomain;
    }

    public List<CustomerDomain> toDomainAllSimpleToDomainCaseListCustomer(List<CustomerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CustomerDomain customerDomain = new CustomerDomain();
            customerDomain.setId(entity.getId());
            customerDomain.setIdentify(entity.getIdentify());
            customerDomain.setName(entity.getName());
            return customerDomain;
        }).toList();
    }

    public PageDTO<CustomerDomain> toDomainPageCaseListCustomer(Page<CustomerEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<CustomerDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListCustomer(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
