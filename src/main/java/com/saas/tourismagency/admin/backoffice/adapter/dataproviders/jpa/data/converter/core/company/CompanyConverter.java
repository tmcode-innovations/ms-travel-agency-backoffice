package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.core.company;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.area.AreaConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.partner.PartnerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.core.organization.OrganizationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.bank.BankConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.*;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.AreaEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.CompanyEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.embedded.SettingEmbeddedEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.AreaDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.embedded.SettingEmbeddedDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class CompanyConverter implements Converter<CompanyDomain, CompanyEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PartnerConverter partnerConverter;

    @Autowired
    private OrganizationConverter organizationConverter;

    @Autowired
    private SupplierLodgingConverter supplierLodgingConverter;

    @Autowired
    private SupplierLodgerConverter supplierLodgerConverter;

    @Autowired
    private SupplierOperatorConverter supplierOperatorConverter;

    @Autowired
    private SupplierRestaurantConverter supplierRestaurantConverter;

    @Autowired
    private SupplierGuideConverter supplierGuideConverter;

    @Autowired
    private SupplierTransportConverter supplierTransportConverter;

    @Autowired
    private SupplierAirLineConverter supplierAirLineConverter;

    @Autowired
    private SupplierAdministrativeConverter supplierAdministrativeConverter;

    @Autowired
    private SupplierGlampingConverter supplierGlampingConverter;

    @Autowired
    private SupplierInstitutionConverter supplierInstitutionConverter;

    @Autowired
    private CurrencyConverter currencyConverter;

    @Autowired
    private BankConverter bankConverter;

    @Autowired
    @Lazy
    private AreaConverter areaConverter;

    @Override
    public CompanyDomain toDomain(CompanyEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CompanyDomain companyDomain = new CompanyDomain();

        companyDomain.setId( source.getId() );
        companyDomain.setDeleted( source.getDeleted() );
        companyDomain.setEnabled( source.getEnabled() );
        companyDomain.setMigrated( source.getMigrated() );
        companyDomain.setCreatedAt( source.getCreatedAt() );
        companyDomain.setUpdatedAt( source.getUpdatedAt() );
        companyDomain.setDeletedAt( source.getDeletedAt() );
        companyDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        companyDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        companyDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        companyDomain.setIdentifierName( source.getIdentifierName() );
        companyDomain.setTenant( source.getTenant() );
        companyDomain.setName( source.getName() );
        companyDomain.setDescription( source.getDescription() );
        companyDomain.setIdentify( source.getIdentify() );
        companyDomain.setPartner( partnerConverter.toDomain( source.getPartner() ) );
        companyDomain.setOrganization( organizationConverter.toDomain( source.getOrganization() ) );
        companyDomain.setSetting( settingEmbeddedEntityToSettingEmbeddedDomain( source.getSetting() ) );

        return companyDomain;
    }

    @Override
    public CompanyEntity toEntity(CompanyDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CompanyEntity companyEntity = new CompanyEntity();

        companyEntity.setId( source.getId() );
        companyEntity.setDeleted( source.getDeleted() );
        companyEntity.setEnabled( source.getEnabled() );
        companyEntity.setMigrated( source.getMigrated() );
        companyEntity.setCreatedAt( source.getCreatedAt() );
        companyEntity.setUpdatedAt( source.getUpdatedAt() );
        companyEntity.setDeletedAt( source.getDeletedAt() );
        companyEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        companyEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        companyEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        companyEntity.setIdentifierName( source.getIdentifierName() );
        companyEntity.setTenant( source.getTenant() );
        companyEntity.setName( source.getName() );
        companyEntity.setDescription( source.getDescription() );
        companyEntity.setIdentify( source.getIdentify() );
        companyEntity.setPartner( partnerConverter.toEntity( source.getPartner() ) );
        companyEntity.setOrganization( organizationConverter.toEntity( source.getOrganization() ) );
        companyEntity.setSetting( settingEmbeddedDomainToSettingEmbeddedEntity( source.getSetting() ) );

        return companyEntity;
    }

    protected SettingEmbeddedDomain settingEmbeddedEntityToSettingEmbeddedDomain(SettingEmbeddedEntity settingEmbeddedEntity) {
        if ( settingEmbeddedEntity == null ) {
            return null;
        }

        SettingEmbeddedDomain settingEmbeddedDomain = new SettingEmbeddedDomain();

        settingEmbeddedDomain.setId( settingEmbeddedEntity.getId() );
        settingEmbeddedDomain.setName( settingEmbeddedEntity.getName() );
        settingEmbeddedDomain.setDescription( settingEmbeddedEntity.getDescription() );
        settingEmbeddedDomain.setQuotationType( settingEmbeddedEntity.getQuotationType() );
        settingEmbeddedDomain.setRegisterGroupType( settingEmbeddedEntity.getRegisterGroupType() );
        settingEmbeddedDomain.setGeneralConfigurationType( settingEmbeddedEntity.getGeneralConfigurationType() );
        settingEmbeddedDomain.setUrlSaveDocumentQuotation( settingEmbeddedEntity.getUrlSaveDocumentQuotation() );
        settingEmbeddedDomain.setUrlSaveDocumentBooking( settingEmbeddedEntity.getUrlSaveDocumentBooking() );
        settingEmbeddedDomain.setUrlSaveDocumentOperation( settingEmbeddedEntity.getUrlSaveDocumentOperation() );
        settingEmbeddedDomain.setUrlSaveDocumentAccount( settingEmbeddedEntity.getUrlSaveDocumentAccount() );
        settingEmbeddedDomain.setCompanyNameReportIntern( settingEmbeddedEntity.getCompanyNameReportIntern() );
        settingEmbeddedDomain.setCompanyNameReportExtern( settingEmbeddedEntity.getCompanyNameReportExtern() );
        settingEmbeddedDomain.setDistributionInvoiceExpenses( settingEmbeddedEntity.getDistributionInvoiceExpenses());
        settingEmbeddedDomain.setDistributionTicketExpenses( settingEmbeddedEntity.getDistributionTicketExpenses() );
        if (settingEmbeddedEntity.getLodging() != null &&
                settingEmbeddedEntity.getLodging().getId() != null &&
                !settingEmbeddedEntity.getLodging().getId().isEmpty()){
            settingEmbeddedDomain.setLodging( supplierLodgingConverter.toDomain( settingEmbeddedEntity.getLodging() ) );
        }else{
            settingEmbeddedDomain.setLodging( null );
        }
        if (settingEmbeddedEntity.getLodger() != null &&
                settingEmbeddedEntity.getLodger().getId() != null &&
                !settingEmbeddedEntity.getLodger().getId().isEmpty()){
            settingEmbeddedDomain.setLodger( supplierLodgerConverter.toDomain( settingEmbeddedEntity.getLodger() ) );
        }else{
            settingEmbeddedDomain.setLodger( null );
        }
        if (settingEmbeddedEntity.getOperator() != null &&
                settingEmbeddedEntity.getOperator().getId() != null &&
                !settingEmbeddedEntity.getOperator().getId().isEmpty()){
            settingEmbeddedDomain.setOperator( supplierOperatorConverter.toDomain( settingEmbeddedEntity.getOperator() ) );
        }else{
            settingEmbeddedDomain.setOperator( null );
        }
        if (settingEmbeddedEntity.getRestaurant() != null &&
                settingEmbeddedEntity.getRestaurant().getId() != null &&
                !settingEmbeddedEntity.getRestaurant().getId().isEmpty()){
            settingEmbeddedDomain.setRestaurant( supplierRestaurantConverter.toDomain( settingEmbeddedEntity.getRestaurant() ) );
        }else{
            settingEmbeddedDomain.setRestaurant( null );
        }
        if (settingEmbeddedEntity.getGuide() != null &&
                settingEmbeddedEntity.getGuide().getId() != null &&
                !settingEmbeddedEntity.getGuide().getId().isEmpty()){
            settingEmbeddedDomain.setGuide( supplierGuideConverter.toDomain( settingEmbeddedEntity.getGuide() ) );
        }else{
            settingEmbeddedDomain.setGuide( null );
        }
        if (settingEmbeddedEntity.getTransport() != null &&
                settingEmbeddedEntity.getTransport().getId() != null &&
                !settingEmbeddedEntity.getTransport().getId().isEmpty()){
            settingEmbeddedDomain.setTransport( supplierTransportConverter.toDomain( settingEmbeddedEntity.getTransport() ) );
        }else{
            settingEmbeddedDomain.setTransport( null );
        }
        if (settingEmbeddedEntity.getAirLine() != null &&
                settingEmbeddedEntity.getAirLine().getId() != null &&
                !settingEmbeddedEntity.getAirLine().getId().isEmpty()){
            settingEmbeddedDomain.setAirLine( supplierAirLineConverter.toDomain( settingEmbeddedEntity.getAirLine() ) );
        }else{
            settingEmbeddedDomain.setAirLine( null );
        }
        if (settingEmbeddedEntity.getAdministrative() != null &&
                settingEmbeddedEntity.getAdministrative().getId() != null &&
                !settingEmbeddedEntity.getAdministrative().getId().isEmpty()){
            settingEmbeddedDomain.setAdministrative( supplierAdministrativeConverter.toDomain( settingEmbeddedEntity.getAdministrative() ) );
        }else{
            settingEmbeddedDomain.setAdministrative( null );
        }
        if (settingEmbeddedEntity.getGlamping() != null &&
                settingEmbeddedEntity.getGlamping().getId() != null &&
                !settingEmbeddedEntity.getGlamping().getId().isEmpty()){
            settingEmbeddedDomain.setGlamping( supplierGlampingConverter.toDomain( settingEmbeddedEntity.getGlamping() ) );
        }else{
            settingEmbeddedDomain.setGlamping( null );
        }
        if (settingEmbeddedEntity.getInstitution() != null &&
                settingEmbeddedEntity.getInstitution().getId() != null &&
                !settingEmbeddedEntity.getInstitution().getId().isEmpty()){
            settingEmbeddedDomain.setInstitution( supplierInstitutionConverter.toDomain( settingEmbeddedEntity.getInstitution() ) );
        }else{
            settingEmbeddedDomain.setInstitution( null );
        }
        if (settingEmbeddedEntity.getCurrencySolesDefault() != null &&
                settingEmbeddedEntity.getCurrencySolesDefault().getId() != null &&
                !settingEmbeddedEntity.getCurrencySolesDefault().getId().isEmpty()){
            settingEmbeddedDomain.setCurrencySolesDefault( currencyConverter.toDomain( settingEmbeddedEntity.getCurrencySolesDefault() ) );
        }else{
            settingEmbeddedDomain.setCurrencySolesDefault( null );
        }
        if (settingEmbeddedEntity.getCurrencyUsdDefault() != null &&
                settingEmbeddedEntity.getCurrencyUsdDefault().getId() != null &&
                !settingEmbeddedEntity.getCurrencyUsdDefault().getId().isEmpty()){
            settingEmbeddedDomain.setCurrencyUsdDefault( currencyConverter.toDomain( settingEmbeddedEntity.getCurrencyUsdDefault() ) );
        }else{
            settingEmbeddedDomain.setCurrencyUsdDefault( null );
        }
        settingEmbeddedDomain.setLodgingCantBoxLunchDateTime( settingEmbeddedEntity.getLodgingCantBoxLunchDateTime() );
        settingEmbeddedDomain.setLodgingCantBreakfastDateTime( settingEmbeddedEntity.getLodgingCantBreakfastDateTime() );
        settingEmbeddedDomain.setLodgingCantDinnerDateTime( settingEmbeddedEntity.getLodgingCantDinnerDateTime() );
        settingEmbeddedDomain.setLodgingCantLunchDateTime( settingEmbeddedEntity.getLodgingCantLunchDateTime() );

        settingEmbeddedDomain.setLodgeCantBoxLunchDateTime( settingEmbeddedEntity.getLodgeCantBoxLunchDateTime() );
        settingEmbeddedDomain.setLodgeCantBreakfastDateTime( settingEmbeddedEntity.getLodgeCantBreakfastDateTime() );
        settingEmbeddedDomain.setLodgeCantDinnerDateTime( settingEmbeddedEntity.getLodgeCantDinnerDateTime() );
        settingEmbeddedDomain.setLodgeCantLunchDateTime( settingEmbeddedEntity.getLodgeCantLunchDateTime() );

        settingEmbeddedDomain.setAreaListQuotationFilter( areaEntitySetToAreaDomainSet( settingEmbeddedEntity.getAreaListQuotationFilter() ) );

        if (settingEmbeddedEntity.getCurrencyVoucherEgressDefault() != null &&
                settingEmbeddedEntity.getCurrencyVoucherEgressDefault().getId() != null &&
                !settingEmbeddedEntity.getCurrencyVoucherEgressDefault().getId().isEmpty()){
            settingEmbeddedDomain.setCurrencyVoucherEgressDefault( currencyConverter.toDomain( settingEmbeddedEntity.getCurrencyVoucherEgressDefault() ) );
        }else{
            settingEmbeddedDomain.setCurrencyVoucherEgressDefault( null );
        }
        if (settingEmbeddedEntity.getBankVoucherEgressDefault() != null &&
                settingEmbeddedEntity.getBankVoucherEgressDefault().getId() != null &&
                !settingEmbeddedEntity.getBankVoucherEgressDefault().getId().isEmpty()){
            settingEmbeddedDomain.setBankVoucherEgressDefault( bankConverter.toDomain( settingEmbeddedEntity.getBankVoucherEgressDefault() ) );
        }else{
            settingEmbeddedDomain.setBankVoucherEgressDefault( null );
        }
        settingEmbeddedDomain.setVoucherTypeVoucherEgressDefault( settingEmbeddedEntity.getVoucherTypeVoucherEgressDefault() );
        settingEmbeddedDomain.setValueTypeShowSupplierVoucherEgressDefault( settingEmbeddedEntity.getValueTypeShowSupplierVoucherEgressDefault() );
        return settingEmbeddedDomain;
    }

    protected SettingEmbeddedEntity settingEmbeddedDomainToSettingEmbeddedEntity(SettingEmbeddedDomain settingEmbeddedDomain) {
        if ( settingEmbeddedDomain == null ) {
            return null;
        }

        SettingEmbeddedEntity settingEmbeddedEntity = new SettingEmbeddedEntity();

        settingEmbeddedEntity.setId( settingEmbeddedDomain.getId() );
        settingEmbeddedEntity.setName( settingEmbeddedDomain.getName() );
        settingEmbeddedEntity.setDescription( settingEmbeddedDomain.getDescription() );
        settingEmbeddedEntity.setQuotationType( settingEmbeddedDomain.getQuotationType() );
        settingEmbeddedEntity.setRegisterGroupType( settingEmbeddedDomain.getRegisterGroupType() );
        settingEmbeddedEntity.setGeneralConfigurationType( settingEmbeddedDomain.getGeneralConfigurationType() );

        settingEmbeddedEntity.setUrlSaveDocumentQuotation( settingEmbeddedDomain.getUrlSaveDocumentQuotation() );
        settingEmbeddedEntity.setUrlSaveDocumentBooking( settingEmbeddedDomain.getUrlSaveDocumentBooking() );
        settingEmbeddedEntity.setUrlSaveDocumentOperation( settingEmbeddedDomain.getUrlSaveDocumentOperation() );
        settingEmbeddedEntity.setUrlSaveDocumentAccount( settingEmbeddedDomain.getUrlSaveDocumentAccount() );

        settingEmbeddedEntity.setCompanyNameReportIntern( settingEmbeddedDomain.getCompanyNameReportIntern() );
        settingEmbeddedEntity.setCompanyNameReportExtern( settingEmbeddedDomain.getCompanyNameReportExtern() );

        settingEmbeddedEntity.setDistributionInvoiceExpenses( settingEmbeddedDomain.getDistributionInvoiceExpenses());
        settingEmbeddedEntity.setDistributionTicketExpenses( settingEmbeddedDomain.getDistributionTicketExpenses() );

        settingEmbeddedEntity.setLodging( supplierLodgingConverter.toEntity( settingEmbeddedDomain.getLodging() ) );
        settingEmbeddedEntity.setLodger( supplierLodgerConverter.toEntity( settingEmbeddedDomain.getLodger() ) );
        settingEmbeddedEntity.setOperator( supplierOperatorConverter.toEntity( settingEmbeddedDomain.getOperator() ) );
        settingEmbeddedEntity.setRestaurant( supplierRestaurantConverter.toEntity( settingEmbeddedDomain.getRestaurant() ) );
        settingEmbeddedEntity.setGuide( supplierGuideConverter.toEntity( settingEmbeddedDomain.getGuide() ) );
        settingEmbeddedEntity.setTransport( supplierTransportConverter.toEntity( settingEmbeddedDomain.getTransport() ) );
        settingEmbeddedEntity.setAirLine( supplierAirLineConverter.toEntity( settingEmbeddedDomain.getAirLine() ) );
        settingEmbeddedEntity.setAdministrative( supplierAdministrativeConverter.toEntity( settingEmbeddedDomain.getAdministrative() ) );
        settingEmbeddedEntity.setGlamping( supplierGlampingConverter.toEntity( settingEmbeddedDomain.getGlamping() ) );
        settingEmbeddedEntity.setInstitution( supplierInstitutionConverter.toEntity( settingEmbeddedDomain.getInstitution() ) );
        settingEmbeddedEntity.setCurrencyUsdDefault( currencyConverter.toEntity( settingEmbeddedDomain.getCurrencyUsdDefault() ) );
        settingEmbeddedEntity.setCurrencySolesDefault( currencyConverter.toEntity( settingEmbeddedDomain.getCurrencySolesDefault() ) );

        settingEmbeddedEntity.setLodgingCantBoxLunchDateTime( settingEmbeddedDomain.getLodgingCantBoxLunchDateTime() );
        settingEmbeddedEntity.setLodgingCantBreakfastDateTime( settingEmbeddedDomain.getLodgingCantBreakfastDateTime() );
        settingEmbeddedEntity.setLodgingCantDinnerDateTime( settingEmbeddedDomain.getLodgingCantDinnerDateTime() );
        settingEmbeddedEntity.setLodgingCantLunchDateTime( settingEmbeddedDomain.getLodgingCantLunchDateTime() );

        settingEmbeddedEntity.setLodgeCantBoxLunchDateTime( settingEmbeddedDomain.getLodgeCantBoxLunchDateTime() );
        settingEmbeddedEntity.setLodgeCantBreakfastDateTime( settingEmbeddedDomain.getLodgeCantBreakfastDateTime() );
        settingEmbeddedEntity.setLodgeCantDinnerDateTime( settingEmbeddedDomain.getLodgeCantDinnerDateTime() );
        settingEmbeddedEntity.setLodgeCantLunchDateTime( settingEmbeddedDomain.getLodgeCantLunchDateTime() );

        settingEmbeddedEntity.setCurrencyVoucherEgressDefault( currencyConverter.toEntity( settingEmbeddedDomain.getCurrencyVoucherEgressDefault() ) );
        settingEmbeddedEntity.setBankVoucherEgressDefault( bankConverter.toEntity( settingEmbeddedDomain.getBankVoucherEgressDefault() ) );

        settingEmbeddedEntity.setVoucherTypeVoucherEgressDefault( settingEmbeddedDomain.getVoucherTypeVoucherEgressDefault() );
        settingEmbeddedEntity.setValueTypeShowSupplierVoucherEgressDefault( settingEmbeddedDomain.getValueTypeShowSupplierVoucherEgressDefault() );

        settingEmbeddedEntity.setAreaListQuotationFilter( areaDomainSetToAreaEntitySet( settingEmbeddedDomain.getAreaListQuotationFilter() ) );
        return settingEmbeddedEntity;
    }

    public Set<AreaDomain> areaEntitySetToAreaDomainSet(Set<AreaEntity> set) {
        if ( set == null ) {
            return null;
        }
        Set<AreaDomain> set1 = new LinkedHashSet<>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AreaEntity areaEntity : set ) {
            set1.add( areaConverter.toDomainCollection( areaEntity ) );
        }
        return set1;
    }

    protected Set<AreaEntity> areaDomainSetToAreaEntitySet(Set<AreaDomain> set) {
        if ( set == null ) {
            return null;
        }
        Set<AreaEntity> set1 = new LinkedHashSet<AreaEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AreaDomain areaDomain : set ) {
            set1.add( areaConverter.toEntityCollection( areaDomain ) );
        }
        return set1;
    }

    public List<CompanyDomain> toDomainAllSimpleToDomainCaseListCompany(List<CompanyEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CompanyDomain companyDomain = new CompanyDomain();
            companyDomain.setId(entity.getId());
            companyDomain.setIdentify(entity.getIdentify());
            companyDomain.setName(entity.getName());
            return companyDomain;
        }).toList();
    }

    public PageDTO<CompanyDomain> toDomainPageCaseListCompany(Page<CompanyEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<CompanyDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListCompany(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

}
