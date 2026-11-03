package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.partner;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion.DepartmentRegionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.district.DistrictConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.province.ProvinceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.state.StateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PartnerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.*;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.*;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerConverter implements Converter<PartnerDomain, PartnerEntity> {

    @Autowired
    private UserConverter userConverter;

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
    public PartnerDomain toDomain(PartnerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PartnerDomain partnerDomain = new PartnerDomain();

        partnerDomain.setId( source.getId() );
        partnerDomain.setDeleted( source.getDeleted() );
        partnerDomain.setEnabled( source.getEnabled() );
        partnerDomain.setMigrated( source.getMigrated() );
        partnerDomain.setCreatedAt( source.getCreatedAt() );
        partnerDomain.setUpdatedAt( source.getUpdatedAt() );
        partnerDomain.setDeletedAt( source.getDeletedAt() );
        partnerDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        partnerDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        partnerDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        partnerDomain.setIdentifierName( source.getIdentifierName() );
        partnerDomain.setTenant( source.getTenant() );
        partnerDomain.setName( source.getName() );
        partnerDomain.setDescription( source.getDescription() );
        partnerDomain.setIdentify( source.getIdentify() );
        partnerDomain.setSocialMediaInformation( socialMediaInformationEmbeddedEntityToSocialMediaInformationEmbeddedDomain( source.getSocialMediaInformation() ) );
        partnerDomain.setWebSiteInformation( webSiteInformationEmbeddedEntityToWebSiteInformationEmbeddedDomain( source.getWebSiteInformation() ) );
        partnerDomain.setContactInformation( contactInformationEmbeddedEntityToContactInformationEmbeddedDomain( source.getContactInformation() ) );
        partnerDomain.setLocationInformation( locationInformationEmbeddedEntityToLocationInformationEmbeddedDomain( source.getLocationInformation() ) );
        partnerDomain.setAddress( addressEmbeddedEntityToAddressEmbeddedDomain( source.getAddress() ) );

        return partnerDomain;
    }

    @Override
    public PartnerEntity toEntity(PartnerDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PartnerEntity partnerEntity = new PartnerEntity();

        partnerEntity.setId( source.getId() );
        partnerEntity.setDeleted( source.getDeleted() );
        partnerEntity.setEnabled( source.getEnabled() );
        partnerEntity.setMigrated( source.getMigrated() );
        partnerEntity.setCreatedAt( source.getCreatedAt() );
        partnerEntity.setUpdatedAt( source.getUpdatedAt() );
        partnerEntity.setDeletedAt( source.getDeletedAt() );
        partnerEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        partnerEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        partnerEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        partnerEntity.setIdentifierName( source.getIdentifierName() );
        partnerEntity.setTenant( source.getTenant() );
        partnerEntity.setName( source.getName() );
        partnerEntity.setDescription( source.getDescription() );
        partnerEntity.setIdentify( source.getIdentify() );
        partnerEntity.setSocialMediaInformation( socialMediaInformationEmbeddedDomainToSocialMediaInformationEmbeddedEntity( source.getSocialMediaInformation() ) );
        partnerEntity.setWebSiteInformation( webSiteInformationEmbeddedDomainToWebSiteInformationEmbeddedEntity( source.getWebSiteInformation() ) );
        partnerEntity.setContactInformation( contactInformationEmbeddedDomainToContactInformationEmbeddedEntity( source.getContactInformation() ) );
        partnerEntity.setLocationInformation( locationInformationEmbeddedDomainToLocationInformationEmbeddedEntity( source.getLocationInformation() ) );
        partnerEntity.setAddress( addressEmbeddedDomainToAddressEmbeddedEntity( source.getAddress() ) );

        return partnerEntity;
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
        socialMediaInformationEmbeddedEntity.setWhatsApp( socialMediaInformationEmbeddedDomain.getWhatsApp() );

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

    protected LocationInformationEmbeddedEntity locationInformationEmbeddedDomainToLocationInformationEmbeddedEntity(LocationInformationEmbeddedDomain locationInformationEmbeddedDomain) {
        if ( locationInformationEmbeddedDomain == null ) {
            return null;
        }

        LocationInformationEmbeddedEntity locationInformationEmbeddedEntity = new LocationInformationEmbeddedEntity();

        locationInformationEmbeddedEntity.setId( locationInformationEmbeddedDomain.getId() );
        locationInformationEmbeddedEntity.setName( locationInformationEmbeddedDomain.getName() );
        locationInformationEmbeddedEntity.setDescription( locationInformationEmbeddedDomain.getDescription() );
        locationInformationEmbeddedEntity.setLatitude( locationInformationEmbeddedDomain.getLatitude() );
        locationInformationEmbeddedEntity.setLongitude( locationInformationEmbeddedDomain.getLongitude() );

        return locationInformationEmbeddedEntity;
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
        socialMediaInformationEmbeddedDomain.setWhatsApp( socialMediaInformationEmbeddedEntity.getWhatsApp() );

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

    public LocationInformationEmbeddedDomain locationInformationEmbeddedEntityToLocationInformationEmbeddedDomain(LocationInformationEmbeddedEntity locationInformationEmbeddedEntity) {
        if ( locationInformationEmbeddedEntity == null ) {
            return null;
        }

        LocationInformationEmbeddedDomain locationInformationEmbeddedDomain = new LocationInformationEmbeddedDomain();

        locationInformationEmbeddedDomain.setId( locationInformationEmbeddedEntity.getId() );
        locationInformationEmbeddedDomain.setName( locationInformationEmbeddedEntity.getName() );
        locationInformationEmbeddedDomain.setDescription( locationInformationEmbeddedEntity.getDescription() );
        locationInformationEmbeddedDomain.setLatitude( locationInformationEmbeddedEntity.getLatitude() );
        locationInformationEmbeddedDomain.setLongitude( locationInformationEmbeddedEntity.getLongitude() );

        return locationInformationEmbeddedDomain;
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

    public IdBaseDomain toDomainIdBase(PartnerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(PartnerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<PartnerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
