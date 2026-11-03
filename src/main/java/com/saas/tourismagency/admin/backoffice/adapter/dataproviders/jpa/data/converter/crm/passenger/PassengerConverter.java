package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.passenger;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion.DepartmentRegionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.district.DistrictConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.nationality.NationalityConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.person.PersonConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.province.ProvinceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.state.StateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.quotation.QuotationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documenttype.DocumentTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.passengertype.PassengerTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.AddressEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.ContactInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.SocialMediaInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.WebSiteInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.PassengerEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.PassengerDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PassengerConverter implements Converter<PassengerDomain, PassengerEntity> {

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

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private NationalityConverter nationalityConverter;

    @Autowired
    private DocumentTypeConverter documentTypeConverter;

    @Autowired
    private QuotationConverter quotationConverter;

    @Autowired
    private PassengerTypeConverter passengerTypeConverter;

    @Override
    public PassengerDomain toDomain(PassengerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PassengerDomain passengerDomain = new PassengerDomain();

        passengerDomain.setId( source.getId() );
        passengerDomain.setDeleted( source.getDeleted() );
        passengerDomain.setEnabled( source.getEnabled() );
        passengerDomain.setMigrated( source.getMigrated() );
        passengerDomain.setCreatedAt( source.getCreatedAt() );
        passengerDomain.setUpdatedAt( source.getUpdatedAt() );
        passengerDomain.setDeletedAt( source.getDeletedAt() );
        passengerDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        passengerDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        passengerDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        passengerDomain.setIdentifierName( source.getIdentifierName() );
        passengerDomain.setTenant( source.getTenant() );
        passengerDomain.setName( source.getName() );
        passengerDomain.setDescription( source.getDescription() );
        passengerDomain.setIdentify( source.getIdentify() );
        passengerDomain.setSocialMediaInformation( socialMediaInformationEmbeddedEntityToSocialMediaInformationEmbeddedDomain( source.getSocialMediaInformation() ) );
        passengerDomain.setWebSiteInformation( webSiteInformationEmbeddedEntityToWebSiteInformationEmbeddedDomain( source.getWebSiteInformation() ) );
        passengerDomain.setContactInformation( contactInformationEmbeddedEntityToContactInformationEmbeddedDomain( source.getContactInformation() ) );
        passengerDomain.setAddress( addressEmbeddedEntityToAddressEmbeddedDomain( source.getAddress() ) );
        passengerDomain.setDateInit( source.getDateInit() );
        passengerDomain.setDateEnd( source.getDateEnd() );
        passengerDomain.setDocument( source.getDocument() );
        passengerDomain.setObservation( source.getObservation() );
        passengerDomain.setImageUrl( source.getImageUrl() );
        passengerDomain.setPrincipal( source.getPrincipal() );
        passengerDomain.setTitlePerson( source.getTitlePerson() );
        passengerDomain.setTypePassengerTcBooking( source.getTypePassengerTcBooking() );
        passengerDomain.setPerson( personConverter.toDomain( source.getPerson() ) );
        passengerDomain.setNationality( nationalityConverter.toDomain( source.getNationality() ) );
        passengerDomain.setDocumentType( documentTypeConverter.toDomain( source.getDocumentType() ) );
        passengerDomain.setQuotation( quotationConverter.toDomain( source.getQuotation() ) );
        passengerDomain.setPassengerType( passengerTypeConverter.toDomain( source.getPassengerType() ) );
        passengerDomain.setIndex( source.getIndex() );
        return passengerDomain;
    }

    @Override
    public PassengerEntity toEntity(PassengerDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PassengerEntity passengerEntity = new PassengerEntity();

        passengerEntity.setId( source.getId() );
        passengerEntity.setDeleted( source.getDeleted() );
        passengerEntity.setEnabled( source.getEnabled() );
        passengerEntity.setMigrated( source.getMigrated() );
        passengerEntity.setCreatedAt( source.getCreatedAt() );
        passengerEntity.setUpdatedAt( source.getUpdatedAt() );
        passengerEntity.setDeletedAt( source.getDeletedAt() );
        passengerEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        passengerEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        passengerEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        passengerEntity.setIdentifierName( source.getIdentifierName() );
        passengerEntity.setTenant( source.getTenant() );
        passengerEntity.setName( source.getName() );
        passengerEntity.setDescription( source.getDescription() );
        passengerEntity.setIdentify( source.getIdentify() );
        passengerEntity.setSocialMediaInformation( socialMediaInformationEmbeddedDomainToSocialMediaInformationEmbeddedEntity( source.getSocialMediaInformation() ) );
        passengerEntity.setWebSiteInformation( webSiteInformationEmbeddedDomainToWebSiteInformationEmbeddedEntity( source.getWebSiteInformation() ) );
        passengerEntity.setContactInformation( contactInformationEmbeddedDomainToContactInformationEmbeddedEntity( source.getContactInformation() ) );
        passengerEntity.setAddress( addressEmbeddedDomainToAddressEmbeddedEntity( source.getAddress() ) );
        passengerEntity.setDateInit( source.getDateInit() );
        passengerEntity.setDateEnd( source.getDateEnd() );
        passengerEntity.setDocument( source.getDocument() );
        passengerEntity.setObservation( source.getObservation() );
        passengerEntity.setImageUrl( source.getImageUrl() );
        passengerEntity.setPrincipal( source.getPrincipal() );
        passengerEntity.setTitlePerson( source.getTitlePerson() );
        passengerEntity.setTypePassengerTcBooking( source.getTypePassengerTcBooking() );
        passengerEntity.setPerson( personConverter.toEntity( source.getPerson() ) );
        passengerEntity.setNationality( nationalityConverter.toEntity( source.getNationality() ) );
        passengerEntity.setDocumentType( documentTypeConverter.toEntity( source.getDocumentType() ) );
        passengerEntity.setQuotation( quotationConverter.toEntity( source.getQuotation() ) );
        passengerEntity.setPassengerType( passengerTypeConverter.toEntity( source.getPassengerType() ) );
        passengerEntity.setIndex( source.getIndex() );

        return passengerEntity;
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

    public IdBaseDomain toDomainIdBase(PassengerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(PassengerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<PassengerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<PassengerDomain> toDomainAllSimpleToDomainCaseDeleteByQuotation(List<PassengerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            PassengerDomain passengerDomain = new PassengerDomain();
            passengerDomain.setId( entity.getId() );
            return passengerDomain;
        }).toList();
    }

    public List<PassengerDomain> toDomainAllSimpleToDomainCaseGetCollectionForLastIndex(List<PassengerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            PassengerDomain passengerDomain = new PassengerDomain();
            passengerDomain.setId( entity.getId() );
            passengerDomain.setIndex( entity.getIndex() );
            return passengerDomain;
        }).toList();
    }

    public List<PassengerDomain> toDomainAllSimpleToDomainCaseResendQuotationToBooking(List<PassengerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            PassengerDomain passengerDomain = new PassengerDomain();
            passengerDomain.setId( entity.getId() );
            passengerDomain.setIndex( entity.getIndex() );
            passengerDomain.setName( entity.getName() );
            passengerDomain.setDateInit( entity.getDateInit() );
            passengerDomain.setDateEnd( entity.getDateEnd() );
            passengerDomain.setDocument( entity.getDocument() );
            passengerDomain.setObservation( entity.getObservation() );
            passengerDomain.setImageUrl( entity.getImageUrl() );

            passengerDomain.setPrincipal( entity.getPrincipal() );

            passengerDomain.setTitlePerson( entity.getTitlePerson() );
            passengerDomain.setTypePassengerTcBooking( entity.getTypePassengerTcBooking() );
            passengerDomain.setIndex( entity.getIndex() );

            if (entity.getPerson() != null &&
                    entity.getPerson().getId() != null &&
                    !entity.getPerson().getId().isEmpty()) {
                passengerDomain.setPerson( new PersonDomain() );
                passengerDomain.getPerson().setId(entity.getPerson().getId());
            }

            if (entity.getNationality() != null &&
                    entity.getNationality().getId() != null &&
                    !entity.getNationality().getId().isEmpty()) {
                passengerDomain.setNationality( new NationalityDomain() );
                passengerDomain.getNationality().setId(entity.getNationality().getId());
            }

            if (entity.getDocumentType() != null &&
                    entity.getDocumentType().getId() != null &&
                    !entity.getDocumentType().getId().isEmpty()) {
                passengerDomain.setDocumentType( new DocumentTypeDomain() );
                passengerDomain.getDocumentType().setId(entity.getDocumentType().getId());
            }

            if (entity.getPassengerType() != null &&
                    entity.getPassengerType().getId() != null &&
                    !entity.getPassengerType().getId().isEmpty()) {
                passengerDomain.setPassengerType( new PassengerTypeDomain() );
                passengerDomain.getPassengerType().setId(entity.getPassengerType().getId());
            }

            passengerDomain.setAddress( addressEmbeddedEntityToAddressEmbeddedDomain( entity.getAddress() )  );
            passengerDomain.setSocialMediaInformation( socialMediaInformationEmbeddedEntityToSocialMediaInformationEmbeddedDomain( entity.getSocialMediaInformation() ) );
            passengerDomain.setWebSiteInformation( webSiteInformationEmbeddedEntityToWebSiteInformationEmbeddedDomain( entity.getWebSiteInformation() ) );
            passengerDomain.setContactInformation( contactInformationEmbeddedEntityToContactInformationEmbeddedDomain( entity.getContactInformation() ) );

            return passengerDomain;
        }).toList();
    }

    public List<PassengerDomain> toDomainAllSimpleToDomainCaseChangeStateOpened(List<PassengerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            PassengerDomain passengerDomain = new PassengerDomain();
            passengerDomain.setId( entity.getId() );
            passengerDomain.setIndex( entity.getIndex() );
            passengerDomain.setName( entity.getName() );
            passengerDomain.setDateInit( entity.getDateInit() );
            passengerDomain.setDateEnd( entity.getDateEnd() );
            passengerDomain.setDocument( entity.getDocument() );
            passengerDomain.setObservation( entity.getObservation() );
            passengerDomain.setImageUrl( entity.getImageUrl() );

            passengerDomain.setPrincipal( entity.getPrincipal() );

            passengerDomain.setTitlePerson( entity.getTitlePerson() );
            passengerDomain.setTypePassengerTcBooking( entity.getTypePassengerTcBooking() );
            passengerDomain.setIndex( entity.getIndex() );

            if (entity.getPerson() != null &&
                    entity.getPerson().getId() != null &&
                    !entity.getPerson().getId().isEmpty()) {
                passengerDomain.setPerson( new PersonDomain() );
                passengerDomain.getPerson().setId(entity.getPerson().getId());
            }

            if (entity.getNationality() != null &&
                    entity.getNationality().getId() != null &&
                    !entity.getNationality().getId().isEmpty()) {
                passengerDomain.setNationality( new NationalityDomain() );
                passengerDomain.getNationality().setId(entity.getNationality().getId());
            }

            if (entity.getDocumentType() != null &&
                    entity.getDocumentType().getId() != null &&
                    !entity.getDocumentType().getId().isEmpty()) {
                passengerDomain.setDocumentType( new DocumentTypeDomain() );
                passengerDomain.getDocumentType().setId(entity.getDocumentType().getId());
            }

            if (entity.getPassengerType() != null &&
                    entity.getPassengerType().getId() != null &&
                    !entity.getPassengerType().getId().isEmpty()) {
                passengerDomain.setPassengerType( new PassengerTypeDomain() );
                passengerDomain.getPassengerType().setId(entity.getPassengerType().getId());
            }

            passengerDomain.setAddress( addressEmbeddedEntityToAddressEmbeddedDomain( entity.getAddress() )  );
            passengerDomain.setSocialMediaInformation( socialMediaInformationEmbeddedEntityToSocialMediaInformationEmbeddedDomain( entity.getSocialMediaInformation() ) );
            passengerDomain.setWebSiteInformation( webSiteInformationEmbeddedEntityToWebSiteInformationEmbeddedDomain( entity.getWebSiteInformation() ) );
            passengerDomain.setContactInformation( contactInformationEmbeddedEntityToContactInformationEmbeddedDomain( entity.getContactInformation() ) );

            return passengerDomain;
        }).toList();
    }
}
