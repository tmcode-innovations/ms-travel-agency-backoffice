package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.passengergroup;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion.DepartmentRegionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.district.DistrictConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.nationality.NationalityConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.person.PersonConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.province.ProvinceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.state.StateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documenttype.DocumentTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.passengertype.PassengerTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.AddressEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.ContactInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.SocialMediaInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.WebSiteInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.PassengerGroupEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerGroupConverter implements Converter<PassengerGroupDomain, PassengerGroupEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private NationalityConverter nationalityConverter;

    @Autowired
    private DocumentTypeConverter documentTypeConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

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
    private PassengerTypeConverter passengerTypeConverter;

    @Override
    public PassengerGroupDomain toDomain(PassengerGroupEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PassengerGroupDomain passengerGroupDomain = new PassengerGroupDomain();

        passengerGroupDomain.setId( source.getId() );
        passengerGroupDomain.setDeleted( source.getDeleted() );
        passengerGroupDomain.setEnabled( source.getEnabled() );
        passengerGroupDomain.setMigrated( source.getMigrated() );
        passengerGroupDomain.setCreatedAt( source.getCreatedAt() );
        passengerGroupDomain.setUpdatedAt( source.getUpdatedAt() );
        passengerGroupDomain.setDeletedAt( source.getDeletedAt() );
        passengerGroupDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        passengerGroupDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        passengerGroupDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        passengerGroupDomain.setIdentifierName( source.getIdentifierName() );
        passengerGroupDomain.setTenant( source.getTenant() );
        passengerGroupDomain.setName( source.getName() );
        passengerGroupDomain.setDescription( source.getDescription() );
        passengerGroupDomain.setIdentify( source.getIdentify() );
        passengerGroupDomain.setSocialMediaInformation( socialMediaInformationEmbeddedEntityToSocialMediaInformationEmbeddedDomain( source.getSocialMediaInformation() ) );
        passengerGroupDomain.setWebSiteInformation( webSiteInformationEmbeddedEntityToWebSiteInformationEmbeddedDomain( source.getWebSiteInformation() ) );
        passengerGroupDomain.setContactInformation( contactInformationEmbeddedEntityToContactInformationEmbeddedDomain( source.getContactInformation() ) );
        passengerGroupDomain.setAddress( addressEmbeddedEntityToAddressEmbeddedDomain( source.getAddress() ) );
        passengerGroupDomain.setDateInit( source.getDateInit() );
        passengerGroupDomain.setDateEnd( source.getDateEnd() );
        passengerGroupDomain.setDocument( source.getDocument() );
        passengerGroupDomain.setObservation( source.getObservation() );
        passengerGroupDomain.setImageUrl( source.getImageUrl() );
        passengerGroupDomain.setPrincipal( source.getPrincipal() );
        passengerGroupDomain.setTitlePerson( source.getTitlePerson() );
        passengerGroupDomain.setTypePassengerTcBooking( source.getTypePassengerTcBooking() );
        passengerGroupDomain.setPerson( personConverter.toDomain( source.getPerson() ) );
        passengerGroupDomain.setNationality( nationalityConverter.toDomain( source.getNationality() ) );
        passengerGroupDomain.setDocumentType( documentTypeConverter.toDomain( source.getDocumentType() ) );
        passengerGroupDomain.setRegisterGroup(registerGroupConverter.toDomain(source.getRegisterGroup()) );
        passengerGroupDomain.setIdPassengerQuotation( source.getIdPassengerQuotation() );
        passengerGroupDomain.setPassengerType( passengerTypeConverter.toDomain( source.getPassengerType() ) );
        passengerGroupDomain.setIndex( source.getIndex() );

        return passengerGroupDomain;
    }

    @Override
    public PassengerGroupEntity toEntity(PassengerGroupDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PassengerGroupEntity passengerGroupEntity = new PassengerGroupEntity();

        passengerGroupEntity.setId( source.getId() );
        passengerGroupEntity.setDeleted( source.getDeleted() );
        passengerGroupEntity.setEnabled( source.getEnabled() );
        passengerGroupEntity.setMigrated( source.getMigrated() );
        passengerGroupEntity.setCreatedAt( source.getCreatedAt() );
        passengerGroupEntity.setUpdatedAt( source.getUpdatedAt() );
        passengerGroupEntity.setDeletedAt( source.getDeletedAt() );
        passengerGroupEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        passengerGroupEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        passengerGroupEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        passengerGroupEntity.setIdentifierName( source.getIdentifierName() );
        passengerGroupEntity.setTenant( source.getTenant() );
        passengerGroupEntity.setName( source.getName() );
        passengerGroupEntity.setDescription( source.getDescription() );
        passengerGroupEntity.setIdentify( source.getIdentify() );
        passengerGroupEntity.setSocialMediaInformation( socialMediaInformationEmbeddedDomainToSocialMediaInformationEmbeddedEntity( source.getSocialMediaInformation() ) );
        passengerGroupEntity.setWebSiteInformation( webSiteInformationEmbeddedDomainToWebSiteInformationEmbeddedEntity( source.getWebSiteInformation() ) );
        passengerGroupEntity.setContactInformation( contactInformationEmbeddedDomainToContactInformationEmbeddedEntity( source.getContactInformation() ) );
        passengerGroupEntity.setAddress( addressEmbeddedDomainToAddressEmbeddedEntity( source.getAddress() ) );
        passengerGroupEntity.setDateInit( source.getDateInit() );
        passengerGroupEntity.setDateEnd( source.getDateEnd() );
        passengerGroupEntity.setDocument( source.getDocument() );
        passengerGroupEntity.setObservation( source.getObservation() );
        passengerGroupEntity.setImageUrl( source.getImageUrl() );
        passengerGroupEntity.setPrincipal( source.getPrincipal() );
        passengerGroupEntity.setTitlePerson( source.getTitlePerson() );
        passengerGroupEntity.setTypePassengerTcBooking( source.getTypePassengerTcBooking() );
        passengerGroupEntity.setPerson( personConverter.toEntity( source.getPerson() ) );
        passengerGroupEntity.setNationality( nationalityConverter.toEntity( source.getNationality() ) );
        passengerGroupEntity.setDocumentType(documentTypeConverter.toEntity(source.getDocumentType()) );
        passengerGroupEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()) );
        passengerGroupEntity.setIdPassengerQuotation( source.getIdPassengerQuotation() );
        passengerGroupEntity.setPassengerType( passengerTypeConverter.toEntity( source.getPassengerType() ) );
        passengerGroupEntity.setIndex( source.getIndex() );

        return passengerGroupEntity;
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

    public IdBaseDomain toDomainIdBase(PassengerGroupEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(PassengerGroupEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<PassengerGroupEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<PassengerGroupDomain> toDomainAllSimpleToDomainCaseListPassengerGroup(List<PassengerGroupEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            PassengerGroupDomain passengerGroupDomain = new PassengerGroupDomain();
            passengerGroupDomain.setId(entity.getId());
            passengerGroupDomain.setTitlePerson(entity.getTitlePerson());
            passengerGroupDomain.setDocument(entity.getDocument());
            passengerGroupDomain.setTypePassengerTcBooking(entity.getTypePassengerTcBooking());
            if (entity.getDocumentType() != null &&
                    entity.getDocumentType().getId() != null &&
                    !entity.getDocumentType().getId().isEmpty()){
                passengerGroupDomain.setDocumentType(new DocumentTypeDomain());
                passengerGroupDomain.getDocumentType().setId(entity.getDocumentType().getId());
                passengerGroupDomain.getDocumentType().setName(entity.getDocumentType().getName());
            }
            if (entity.getNationality() != null &&
                    entity.getNationality().getId() != null &&
                    !entity.getNationality().getId().isEmpty()){
                passengerGroupDomain.setNationality(new NationalityDomain());
                passengerGroupDomain.getNationality().setId(entity.getNationality().getId());
                passengerGroupDomain.getNationality().setName(entity.getNationality().getName());
            }
            if (entity.getPerson() != null &&
                    entity.getPerson().getId() != null &&
                    !entity.getPerson().getId().isEmpty()){
                passengerGroupDomain.setPerson(new PersonDomain());
                passengerGroupDomain.getPerson().setId(entity.getPerson().getId());
                passengerGroupDomain.getPerson().setFirstName(entity.getPerson().getFirstName());
                passengerGroupDomain.getPerson().setLastName(entity.getPerson().getLastName());
                passengerGroupDomain.getPerson().setDateBirthday(entity.getPerson().getDateBirthday());
                passengerGroupDomain.getPerson().setAge(entity.getPerson().getAge());
                passengerGroupDomain.getPerson().setGender(entity.getPerson().getGender());
            }
            if (entity.getRegisterGroup() != null &&
                    entity.getRegisterGroup().getId() != null &&
                    !entity.getRegisterGroup().getId().isEmpty()){
                passengerGroupDomain.setRegisterGroup(new RegisterGroupDomain());
                passengerGroupDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                passengerGroupDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                passengerGroupDomain.getRegisterGroup().setDateAperture(entity.getRegisterGroup().getDateAperture());
            }
            return passengerGroupDomain;
        }).toList();
    }

    public PageDTO<PassengerGroupDomain> toDomainPageCaseListPassengerGroup(Page<PassengerGroupEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<PassengerGroupDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListPassengerGroup(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public List<PassengerGroupDomain> toDomainAllSimpleToDomainCaseDeleteByRegisterGroup(List<PassengerGroupEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            PassengerGroupDomain passengerGroupDomain = new PassengerGroupDomain();
            passengerGroupDomain.setId( entity.getId() );
            return passengerGroupDomain;
        }).toList();
    }

    public List<PassengerGroupDomain> toDomainAllSimpleToDomainCaseGetCollectionForLastIndex(List<PassengerGroupEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            PassengerGroupDomain passengerGroupDomain = new PassengerGroupDomain();
            passengerGroupDomain.setId( entity.getId() );
            passengerGroupDomain.setIndex( entity.getIndex() );
            return passengerGroupDomain;
        }).toList();
    }
}
