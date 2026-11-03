package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.guide;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion.DepartmentRegionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.district.DistrictConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.idiom.IdiomConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.person.PersonConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.province.ProvinceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.state.StateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.IdiomEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.AddressEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.ContactInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.SocialMediaInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded.WebSiteInformationEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.GuideEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class GuideConverter implements Converter<GuideDomain, GuideEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private SupplierConverter supplierConverter;

    @Autowired
    private IdiomConverter idiomConverter;

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
    public GuideDomain toDomain(GuideEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        GuideDomain guideDomain = new GuideDomain();

        guideDomain.setId( source.getId() );
        guideDomain.setDeleted( source.getDeleted() );
        guideDomain.setEnabled( source.getEnabled() );
        guideDomain.setMigrated( source.getMigrated() );
        guideDomain.setCreatedAt( source.getCreatedAt() );
        guideDomain.setUpdatedAt( source.getUpdatedAt() );
        guideDomain.setDeletedAt( source.getDeletedAt() );
        guideDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        guideDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        guideDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        guideDomain.setIdentifierName( source.getIdentifierName() );
        guideDomain.setTenant( source.getTenant() );
        guideDomain.setName( source.getName() );
        guideDomain.setDescription( source.getDescription() );
        guideDomain.setIdentify( source.getIdentify() );
        guideDomain.setRuc( source.getRuc() );
        guideDomain.setPerson( personConverter.toDomain( source.getPerson() ) );
        guideDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        guideDomain.setIdioms( idiomEntitySetToIdiomDomainSet( source.getIdioms() ) );
        Set<String> set1 = source.getGuideTypes();
        if ( set1 != null ) {
            guideDomain.setGuideTypes( new LinkedHashSet<String>( set1 ) );
        }
        guideDomain.setAddress( addressEmbeddedEntityToAddressEmbeddedDomain( source.getAddress() ) );
        guideDomain.setSocialMediaInformation( socialMediaInformationEmbeddedEntityToSocialMediaInformationEmbeddedDomain( source.getSocialMediaInformation() ) );
        guideDomain.setWebSiteInformation( webSiteInformationEmbeddedEntityToWebSiteInformationEmbeddedDomain( source.getWebSiteInformation() ) );
        guideDomain.setContactInformation( contactInformationEmbeddedEntityToContactInformationEmbeddedDomain( source.getContactInformation() ) );

        return guideDomain;
    }

    @Override
    public GuideEntity toEntity(GuideDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        GuideEntity guideEntity = new GuideEntity();

        guideEntity.setId( source.getId() );
        guideEntity.setDeleted( source.getDeleted() );
        guideEntity.setEnabled( source.getEnabled() );
        guideEntity.setMigrated( source.getMigrated() );
        guideEntity.setCreatedAt( source.getCreatedAt() );
        guideEntity.setUpdatedAt( source.getUpdatedAt() );
        guideEntity.setDeletedAt( source.getDeletedAt() );
        guideEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        guideEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        guideEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        guideEntity.setIdentifierName( source.getIdentifierName() );
        guideEntity.setTenant( source.getTenant() );
        guideEntity.setName( source.getName() );
        guideEntity.setDescription( source.getDescription() );
        guideEntity.setIdentify( source.getIdentify() );
        guideEntity.setRuc( source.getRuc() );
        guideEntity.setPerson( personConverter.toEntity( source.getPerson() ) );
        guideEntity.setSupplier( supplierConverter.toEntity( source.getSupplier() ) );
        guideEntity.setIdioms( idiomDomainSetToIdiomEntitySet( source.getIdioms() ) );
        Set<String> set1 = source.getGuideTypes();
        if ( set1 != null ) {
            guideEntity.setGuideTypes( new LinkedHashSet<String>( set1 ) );
        }
        guideEntity.setAddress( addressEmbeddedDomainToAddressEmbeddedEntity( source.getAddress() ) );
        guideEntity.setSocialMediaInformation( socialMediaInformationEmbeddedDomainToSocialMediaInformationEmbeddedEntity( source.getSocialMediaInformation() ) );
        guideEntity.setWebSiteInformation( webSiteInformationEmbeddedDomainToWebSiteInformationEmbeddedEntity( source.getWebSiteInformation() ) );
        guideEntity.setContactInformation( contactInformationEmbeddedDomainToContactInformationEmbeddedEntity( source.getContactInformation() ) );

        return guideEntity;
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

    public Set<IdiomDomain> idiomEntitySetToIdiomDomainSet(Set<IdiomEntity> set) {
        if ( set == null ) {
            return null;
        }
        Set<IdiomDomain> set1 = new LinkedHashSet<>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( IdiomEntity idiomEntity : set ) {
            set1.add( idiomConverter.toDomain( idiomEntity ) );
        }
        return set1;
    }

    protected Set<IdiomEntity> idiomDomainSetToIdiomEntitySet(Set<IdiomDomain> set) {
        if ( set == null ) {
            return null;
        }
        Set<IdiomEntity> set1 = new LinkedHashSet<IdiomEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( IdiomDomain idiomDomain : set ) {
            set1.add( idiomConverter.toEntity( idiomDomain ) );
        }
        return set1;
    }

    public IdBaseDomain toDomainIdBase(GuideEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(GuideEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getPerson().getFirstName() + " " + source.getPerson().getLastName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<GuideEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public GuideDomain toDomainSimpleBaseGuide(GuideEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        GuideDomain guideDomain = new GuideDomain();

        guideDomain.setId( source.getId() );
        guideDomain.setName( source.getPerson().getFirstName() + " " + source.getPerson().getLastName() );

        return guideDomain;
    }

    public List<GuideDomain> toDomainAllSimpleGuide(List<GuideEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseGuide(entity)).toList();
    }

    public List<GuideDomain> toDomainAllSimpleToDomainCaseListGuide(List<GuideEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            GuideDomain guideDomain = new GuideDomain();
            guideDomain.setId(entity.getId());
            guideDomain.setIdentify(entity.getIdentify());
            guideDomain.setRuc(entity.getRuc());
            if (entity.getPerson() != null && entity.getPerson().getId() != null && !entity.getPerson().getId().isEmpty()){
                guideDomain.setPerson(new PersonDomain());
                guideDomain.getPerson().setId(entity.getPerson().getId());
                guideDomain.getPerson().setFirstName(entity.getPerson().getFirstName());
                guideDomain.getPerson().setLastName(entity.getPerson().getLastName());
            }
            if (entity.getContactInformation() != null){
                guideDomain.setContactInformation(new ContactInformationEmbeddedDomain());
                guideDomain.getContactInformation().setEmail(entity.getContactInformation().getEmail());
                guideDomain.getContactInformation().setMobile(entity.getContactInformation().getMobile());
            }
            if (entity.getSupplier() != null && entity.getSupplier().getId() != null && !entity.getSupplier().getId().isEmpty()){
                guideDomain.setSupplier(new SupplierDomain());
                guideDomain.getSupplier().setId(entity.getSupplier().getId());
                guideDomain.getSupplier().setName(entity.getSupplier().getName());
            }
            if (entity.getGuideTypes() != null && !entity.getGuideTypes().isEmpty()){
                guideDomain.setGuideTypes(new LinkedHashSet<String>(entity.getGuideTypes()));
            }
            if (entity.getIdioms() != null && !entity.getIdioms().isEmpty()){
                Set<IdiomDomain> idiomDomainSet = new LinkedHashSet<>();
                for (IdiomEntity idiomEntity : entity.getIdioms()){
                    if (idiomEntity != null && idiomEntity.getId() != null && !idiomEntity.getId().isEmpty()){
                        IdiomDomain idiomDomain = new IdiomDomain();
                        idiomDomain.setId(idiomEntity.getId());
                        idiomDomain.setName(idiomEntity.getName());
                        idiomDomainSet.add(idiomDomain);
                    }
                }
                guideDomain.setIdioms(idiomDomainSet);
            }
            return guideDomain;
        }).toList();
    }

    public PageDTO<GuideDomain> toDomainPageCaseListGuide(Page<GuideEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<GuideDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListGuide(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
