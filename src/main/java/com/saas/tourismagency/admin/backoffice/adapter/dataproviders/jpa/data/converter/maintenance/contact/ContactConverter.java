package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.contact;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion.DepartmentRegionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.district.DistrictConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.person.PersonConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.province.ProvinceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.state.StateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.AddressEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.ContactInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.SocialMediaInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.WebSiteInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.BankEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ContactEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ContactDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactConverter implements Converter<ContactDomain, ContactEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private SupplierConverter supplierConverter;

    @Autowired
    private SupplierTypeConverter supplierTypeConverter;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private CountryConverter countryConverter;

    @Autowired
    private DepartmentRegionConverter departmentRegionConverter;

    @Autowired
    private ProvinceConverter provinceConverter;

    @Autowired
    private DistrictConverter districtConverter;

    @Autowired
    private StateConverter stateConverter;

    @Override
    public ContactDomain toDomain(ContactEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ContactDomain contactDomain = new ContactDomain();

        contactDomain.setId( source.getId() );
        contactDomain.setDeleted( source.getDeleted() );
        contactDomain.setEnabled( source.getEnabled() );
        contactDomain.setMigrated( source.getMigrated() );
        contactDomain.setCreatedAt( source.getCreatedAt() );
        contactDomain.setUpdatedAt( source.getUpdatedAt() );
        contactDomain.setDeletedAt( source.getDeletedAt() );
        contactDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        contactDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        contactDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        contactDomain.setIdentifierName( source.getIdentifierName() );
        contactDomain.setTenant( source.getTenant() );
        contactDomain.setName( source.getName() );
        contactDomain.setDescription( source.getDescription() );
        contactDomain.setIdentify( source.getIdentify() );
        contactDomain.setAppointment( source.getAppointment() );
        contactDomain.setContactType( source.getContactType() );
        contactDomain.setPerson( personConverter.toDomain( source.getPerson() ) );
        contactDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        contactDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        contactDomain.setCustomer( customerConverter.toDomain( source.getCustomer() ) );
        contactDomain.setAddress( addressEmbeddedEntityToAddressEmbeddedDomain( source.getAddress() ) );
        contactDomain.setSocialMediaInformation( socialMediaInformationEmbeddedEntityToSocialMediaInformationEmbeddedDomain( source.getSocialMediaInformation() ) );
        contactDomain.setWebSiteInformation( webSiteInformationEmbeddedEntityToWebSiteInformationEmbeddedDomain( source.getWebSiteInformation() ) );
        contactDomain.setContactInformation( contactInformationEmbeddedEntityToContactInformationEmbeddedDomain( source.getContactInformation() ) );

        return contactDomain;
    }

    @Override
    public ContactEntity toEntity(ContactDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ContactEntity contactEntity = new ContactEntity();

        contactEntity.setId( source.getId() );
        contactEntity.setDeleted( source.getDeleted() );
        contactEntity.setEnabled( source.getEnabled() );
        contactEntity.setMigrated( source.getMigrated() );
        contactEntity.setCreatedAt( source.getCreatedAt() );
        contactEntity.setUpdatedAt( source.getUpdatedAt() );
        contactEntity.setDeletedAt( source.getDeletedAt() );
        contactEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        contactEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        contactEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        contactEntity.setIdentifierName( source.getIdentifierName() );
        contactEntity.setTenant( source.getTenant() );
        contactEntity.setName( source.getName() );
        contactEntity.setDescription( source.getDescription() );
        contactEntity.setIdentify( source.getIdentify() );
        contactEntity.setAppointment( source.getAppointment() );
        contactEntity.setContactType( source.getContactType() );
        contactEntity.setPerson( personConverter.toEntity( source.getPerson() ) );
        contactEntity.setSupplier( supplierConverter.toEntity( source.getSupplier() ) );
        contactEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        contactEntity.setCustomer( customerConverter.toEntity( source.getCustomer() ) );
        contactEntity.setAddress( addressEmbeddedDomainToAddressEmbeddedEntity( source.getAddress() ) );
        contactEntity.setSocialMediaInformation( socialMediaInformationEmbeddedDomainToSocialMediaInformationEmbeddedEntity( source.getSocialMediaInformation() ) );
        contactEntity.setWebSiteInformation( webSiteInformationEmbeddedDomainToWebSiteInformationEmbeddedEntity( source.getWebSiteInformation() ) );
        contactEntity.setContactInformation( contactInformationEmbeddedDomainToContactInformationEmbeddedEntity( source.getContactInformation() ) );

        return contactEntity;
    }

    public SocialMediaInformationEmbeddedEntity socialMediaInformationEmbeddedDomainToSocialMediaInformationEmbeddedEntity(SocialMediaInformationEmbeddedDomain socialMediaInformationEmbeddedDomain) {
        if ( socialMediaInformationEmbeddedDomain == null ) {
            return null;
        }

        SocialMediaInformationEmbeddedEntity socialMediaInformationEmbeddedEntity = new SocialMediaInformationEmbeddedEntity();

        socialMediaInformationEmbeddedEntity.setId( socialMediaInformationEmbeddedDomain.getId() );
        socialMediaInformationEmbeddedEntity.setName( socialMediaInformationEmbeddedDomain.getName() );
        socialMediaInformationEmbeddedEntity.setDescription( socialMediaInformationEmbeddedDomain.getDescription() );
        socialMediaInformationEmbeddedEntity.setFacebook( socialMediaInformationEmbeddedDomain.getFacebook() );
        socialMediaInformationEmbeddedEntity.setYoutube( socialMediaInformationEmbeddedDomain.getYoutube() );
        socialMediaInformationEmbeddedEntity.setInstagram( socialMediaInformationEmbeddedDomain.getInstagram() );
        socialMediaInformationEmbeddedEntity.setLinkedIn( socialMediaInformationEmbeddedDomain.getLinkedIn() );
        socialMediaInformationEmbeddedEntity.setTwitterX( socialMediaInformationEmbeddedDomain.getTwitterX() );

        return socialMediaInformationEmbeddedEntity;
    }

    public WebSiteInformationEmbeddedEntity webSiteInformationEmbeddedDomainToWebSiteInformationEmbeddedEntity(WebSiteInformationEmbeddedDomain webSiteInformationEmbeddedDomain) {
        if ( webSiteInformationEmbeddedDomain == null ) {
            return null;
        }

        WebSiteInformationEmbeddedEntity webSiteInformationEmbeddedEntity = new WebSiteInformationEmbeddedEntity();

        webSiteInformationEmbeddedEntity.setId( webSiteInformationEmbeddedDomain.getId() );
        webSiteInformationEmbeddedEntity.setName( webSiteInformationEmbeddedDomain.getName() );
        webSiteInformationEmbeddedEntity.setDescription( webSiteInformationEmbeddedDomain.getDescription() );
        webSiteInformationEmbeddedEntity.setUrl( webSiteInformationEmbeddedDomain.getUrl() );

        return webSiteInformationEmbeddedEntity;
    }

    public ContactInformationEmbeddedEntity contactInformationEmbeddedDomainToContactInformationEmbeddedEntity(ContactInformationEmbeddedDomain contactInformationEmbeddedDomain) {
        if ( contactInformationEmbeddedDomain == null ) {
            return null;
        }

        ContactInformationEmbeddedEntity contactInformationEmbeddedEntity = new ContactInformationEmbeddedEntity();

        contactInformationEmbeddedEntity.setId( contactInformationEmbeddedDomain.getId() );
        contactInformationEmbeddedEntity.setName( contactInformationEmbeddedDomain.getName() );
        contactInformationEmbeddedEntity.setDescription( contactInformationEmbeddedDomain.getDescription() );
        contactInformationEmbeddedEntity.setEmail( contactInformationEmbeddedDomain.getEmail() );
        contactInformationEmbeddedEntity.setPhone( contactInformationEmbeddedDomain.getPhone() );
        contactInformationEmbeddedEntity.setMobile( contactInformationEmbeddedDomain.getMobile() );
        contactInformationEmbeddedEntity.setFax( contactInformationEmbeddedDomain.getFax() );

        return contactInformationEmbeddedEntity;
    }

    public AddressEmbeddedEntity addressEmbeddedDomainToAddressEmbeddedEntity(AddressEmbeddedDomain addressEmbeddedDomain) {
        if ( addressEmbeddedDomain == null ) {
            return null;
        }

        AddressEmbeddedEntity addressEmbeddedEntity = new AddressEmbeddedEntity();

        addressEmbeddedEntity.setId( addressEmbeddedDomain.getId() );
        addressEmbeddedEntity.setName( addressEmbeddedDomain.getName() );
        addressEmbeddedEntity.setDescription( addressEmbeddedDomain.getDescription() );
        addressEmbeddedEntity.setCity( addressEmbeddedDomain.getCity() );
        addressEmbeddedEntity.setZip( addressEmbeddedDomain.getZip() );
        addressEmbeddedEntity.setStreet( addressEmbeddedDomain.getStreet() );
        addressEmbeddedEntity.setStreet2( addressEmbeddedDomain.getStreet2() );
        addressEmbeddedEntity.setAddress1( addressEmbeddedDomain.getAddress1() );
        addressEmbeddedEntity.setAddress2( addressEmbeddedDomain.getAddress2() );
        addressEmbeddedEntity.setAddress3( addressEmbeddedDomain.getAddress3() );
        addressEmbeddedEntity.setCounty( addressEmbeddedDomain.getCounty() );
        addressEmbeddedEntity.setTown( addressEmbeddedDomain.getTown() );
        addressEmbeddedEntity.setCountry( countryConverter.toEntity( addressEmbeddedDomain.getCountry() ) );
        addressEmbeddedEntity.setDepartmentRegion( departmentRegionConverter.toEntity( addressEmbeddedDomain.getDepartmentRegion() ) );
        addressEmbeddedEntity.setProvince( provinceConverter.toEntity( addressEmbeddedDomain.getProvince() ) );
        addressEmbeddedEntity.setDistrict( districtConverter.toEntity( addressEmbeddedDomain.getDistrict() ) );
        addressEmbeddedEntity.setState( stateConverter.toEntity( addressEmbeddedDomain.getState() ) );

        return addressEmbeddedEntity;
    }

    public SocialMediaInformationEmbeddedDomain socialMediaInformationEmbeddedEntityToSocialMediaInformationEmbeddedDomain(SocialMediaInformationEmbeddedEntity socialMediaInformationEmbeddedEntity) {
        if ( socialMediaInformationEmbeddedEntity == null ) {
            return null;
        }

        SocialMediaInformationEmbeddedDomain socialMediaInformationEmbeddedDomain = new SocialMediaInformationEmbeddedDomain();

        socialMediaInformationEmbeddedDomain.setId( socialMediaInformationEmbeddedEntity.getId() );
        socialMediaInformationEmbeddedDomain.setName( socialMediaInformationEmbeddedEntity.getName() );
        socialMediaInformationEmbeddedDomain.setDescription( socialMediaInformationEmbeddedEntity.getDescription() );
        socialMediaInformationEmbeddedDomain.setFacebook( socialMediaInformationEmbeddedEntity.getFacebook() );
        socialMediaInformationEmbeddedDomain.setYoutube( socialMediaInformationEmbeddedEntity.getYoutube() );
        socialMediaInformationEmbeddedDomain.setInstagram( socialMediaInformationEmbeddedEntity.getInstagram() );
        socialMediaInformationEmbeddedDomain.setLinkedIn( socialMediaInformationEmbeddedEntity.getLinkedIn() );
        socialMediaInformationEmbeddedDomain.setTwitterX( socialMediaInformationEmbeddedEntity.getTwitterX() );

        return socialMediaInformationEmbeddedDomain;
    }

    public WebSiteInformationEmbeddedDomain webSiteInformationEmbeddedEntityToWebSiteInformationEmbeddedDomain(WebSiteInformationEmbeddedEntity webSiteInformationEmbeddedEntity) {
        if ( webSiteInformationEmbeddedEntity == null ) {
            return null;
        }

        WebSiteInformationEmbeddedDomain webSiteInformationEmbeddedDomain = new WebSiteInformationEmbeddedDomain();

        webSiteInformationEmbeddedDomain.setId( webSiteInformationEmbeddedEntity.getId() );
        webSiteInformationEmbeddedDomain.setName( webSiteInformationEmbeddedEntity.getName() );
        webSiteInformationEmbeddedDomain.setDescription( webSiteInformationEmbeddedEntity.getDescription() );
        webSiteInformationEmbeddedDomain.setUrl( webSiteInformationEmbeddedEntity.getUrl() );

        return webSiteInformationEmbeddedDomain;
    }

    public ContactInformationEmbeddedDomain contactInformationEmbeddedEntityToContactInformationEmbeddedDomain(ContactInformationEmbeddedEntity contactInformationEmbeddedEntity) {
        if ( contactInformationEmbeddedEntity == null ) {
            return null;
        }

        ContactInformationEmbeddedDomain contactInformationEmbeddedDomain = new ContactInformationEmbeddedDomain();

        contactInformationEmbeddedDomain.setId( contactInformationEmbeddedEntity.getId() );
        contactInformationEmbeddedDomain.setName( contactInformationEmbeddedEntity.getName() );
        contactInformationEmbeddedDomain.setDescription( contactInformationEmbeddedEntity.getDescription() );
        contactInformationEmbeddedDomain.setEmail( contactInformationEmbeddedEntity.getEmail() );
        contactInformationEmbeddedDomain.setPhone( contactInformationEmbeddedEntity.getPhone() );
        contactInformationEmbeddedDomain.setMobile( contactInformationEmbeddedEntity.getMobile() );
        contactInformationEmbeddedDomain.setFax( contactInformationEmbeddedEntity.getFax() );

        return contactInformationEmbeddedDomain;
    }

    public AddressEmbeddedDomain addressEmbeddedEntityToAddressEmbeddedDomain(AddressEmbeddedEntity addressEmbeddedEntity) {
        if ( addressEmbeddedEntity == null ) {
            return null;
        }

        AddressEmbeddedDomain addressEmbeddedDomain = new AddressEmbeddedDomain();

        addressEmbeddedDomain.setId( addressEmbeddedEntity.getId() );
        addressEmbeddedDomain.setName( addressEmbeddedEntity.getName() );
        addressEmbeddedDomain.setDescription( addressEmbeddedEntity.getDescription() );
        addressEmbeddedDomain.setCity( addressEmbeddedEntity.getCity() );
        addressEmbeddedDomain.setZip( addressEmbeddedEntity.getZip() );
        addressEmbeddedDomain.setStreet( addressEmbeddedEntity.getStreet() );
        addressEmbeddedDomain.setStreet2( addressEmbeddedEntity.getStreet2() );
        addressEmbeddedDomain.setAddress1( addressEmbeddedEntity.getAddress1() );
        addressEmbeddedDomain.setAddress2( addressEmbeddedEntity.getAddress2() );
        addressEmbeddedDomain.setAddress3( addressEmbeddedEntity.getAddress3() );
        addressEmbeddedDomain.setCounty( addressEmbeddedEntity.getCounty() );
        addressEmbeddedDomain.setTown( addressEmbeddedEntity.getTown() );
        addressEmbeddedDomain.setCountry( countryConverter.toDomain( addressEmbeddedEntity.getCountry() ) );
        addressEmbeddedDomain.setDepartmentRegion( departmentRegionConverter.toDomain( addressEmbeddedEntity.getDepartmentRegion() ) );
        addressEmbeddedDomain.setProvince( provinceConverter.toDomain( addressEmbeddedEntity.getProvince() ) );
        addressEmbeddedDomain.setDistrict( districtConverter.toDomain( addressEmbeddedEntity.getDistrict() ) );
        addressEmbeddedDomain.setState( stateConverter.toDomain( addressEmbeddedEntity.getState() ) );

        return addressEmbeddedDomain;
    }

    public IdBaseDomain toDomainIdBase(ContactEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ContactEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ContactEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ContactDomain> toDomainAllSimpleToDomainCaseListContact(List<ContactEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ContactDomain contactDomain = new ContactDomain();
            contactDomain.setId(entity.getId());
            if (entity.getPerson() != null && entity.getPerson().getId() != null && !entity.getPerson().getId().isEmpty()){
                contactDomain.setPerson(new PersonDomain());
                contactDomain.getPerson().setId(entity.getPerson().getId());
                contactDomain.getPerson().setFirstName(entity.getPerson().getFirstName());
                contactDomain.getPerson().setLastName(entity.getPerson().getLastName());
            }
            if (entity.getContactInformation() != null){
                contactDomain.setContactInformation(new ContactInformationEmbeddedDomain());
                contactDomain.getContactInformation().setEmail(entity.getContactInformation().getEmail());
                contactDomain.getContactInformation().setMobile(entity.getContactInformation().getMobile());
            }
            if (entity.getSupplier() != null && entity.getSupplier().getId() != null && !entity.getSupplier().getId().isEmpty()){
                contactDomain.setSupplier(new SupplierDomain());
                contactDomain.getSupplier().setId(entity.getSupplier().getId());
                contactDomain.getSupplier().setName(entity.getSupplier().getName());
            }
            if (entity.getSupplierType() != null && entity.getSupplierType().getId() != null && !entity.getSupplierType().getId().isEmpty()){
                contactDomain.setSupplierType(new SupplierTypeDomain());
                contactDomain.getSupplierType().setId(entity.getSupplierType().getId());
                contactDomain.getSupplierType().setName(entity.getSupplierType().getName());
            }
            if (entity.getCustomer() != null && entity.getCustomer().getId() != null && !entity.getCustomer().getId().isEmpty()){
                contactDomain.setCustomer(new CustomerDomain());
                contactDomain.getCustomer().setId(entity.getCustomer().getId());
                contactDomain.getCustomer().setName(entity.getCustomer().getName());
            }
            return contactDomain;
        }).toList();
    }

    public PageDTO<ContactDomain> toDomainPageCaseListContact(Page<ContactEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ContactDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListContact(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
