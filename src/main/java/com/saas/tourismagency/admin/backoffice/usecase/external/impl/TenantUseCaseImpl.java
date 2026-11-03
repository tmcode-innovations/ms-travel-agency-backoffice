package com.saas.tourismagency.admin.backoffice.usecase.external.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.grouppqttourist.GroupPqtTouristRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.grouppqttourist.PersistGroupPqtTouristNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.servicepqttourist.PersistServicePqtTouristNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.servicepqttourist.ServicePqtTouristRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.partner.PartnerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.CompanyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.OrganizationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.PersistCompanyNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documentaccounttype.DocumentAccountTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documentaccounttype.PersistDocAccTypeNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.app.AppRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.objectsecurity.ObjectRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.permission.PermissionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.profile.ProfileRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.user.UserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.userpassword.UserPasswordRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.external.TenantCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.external.TenantResponse;
import com.saas.tourismagency.admin.backoffice.adapter.importdata.excel.ImportObjectSecurity;
import com.saas.tourismagency.admin.backoffice.adapter.importdata.model.security.ObjectSecurity;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.password.PasswordService;
import com.saas.tourismagency.admin.backoffice.domain.account.GroupPqtTouristDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ServicePqtTouristDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.DocumentAccountTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.OrganizationDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.embedded.SettingEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentAccountTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.*;
import com.saas.tourismagency.admin.backoffice.usecase.external.TenantUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TenantUseCaseImpl implements TenantUseCase {

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private OrganizationRepo organizationRepo;

    @Autowired
    private PartnerRepo partnerRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserPasswordRepo userPasswordRepo;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private DocumentAccountTypeRepo documentAccountTypeRepo;

    @Autowired
    private ServicePqtTouristRepo servicePqtTouristRepo;

    @Autowired
    private GroupPqtTouristRepo groupPqtTouristRepo;

    @Autowired
    private AppRepo appRepo;

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private ObjectRepo objectRepo;

    @Autowired
    private PermissionRepo permissionRepo;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    PersistCompanyNumberCallbackImpl persistCompanyNumberCallback;

    @Autowired
    PersistDocAccTypeNumberCallbackImpl persistDocAccTypeNumberCallback;

    @Autowired
    PersistGroupPqtTouristNumberCallbackImpl persistGroupPqtTouristNumberCallback;

    @Autowired
    PersistServicePqtTouristNumberCallbackImpl persistServicePqtTouristNumberCallback;

    @Autowired
    ImportObjectSecurity importObjectSecurity;

    @Override
    @Transactional
    public TenantResponse create(TenantCreateRequest request) {

        OrganizationDomain organizationDomain = new OrganizationDomain();
        organizationDomain.setName(request.getNameOrganization());
        organizationDomain.setTenantId(request.getTenantId());
        organizationDomain.setTenant(request.getTenantId());
        OrganizationDomain createdOrganizationDomain = organizationRepo.create(organizationDomain);

        PartnerDomain partnerDomain = new PartnerDomain();
        partnerDomain.setName(request.getNameCompany());
        partnerDomain.setTenant(request.getTenantId());
        PartnerDomain createdPartnerDomain = partnerRepo.create(partnerDomain);

        CompanyDomain companyDomain = new CompanyDomain();
        companyDomain.setName(request.getNameCompany());
        companyDomain.setTenant(request.getTenantId());
        companyDomain.setIdentify(persistCompanyNumberCallback.generateNumber());
        companyDomain.setSetting(new SettingEmbeddedDomain());
        companyDomain.getSetting().setQuotationType(request.getQuotationType());
        companyDomain.getSetting().setRegisterGroupType(request.getRegisterGroupType());
        companyDomain.getSetting().setGeneralConfigurationType(request.getGeneralConfigurationType());
        companyDomain.setOrganization(createdOrganizationDomain);
        companyDomain.setPartner(createdPartnerDomain);
        CompanyDomain createdCompanyDomain = companyRepo.create(companyDomain);

        AppDomain appDomainDashboard = appRepo.findByName("TABLEROS");
        if (appDomainDashboard == null) {
            appDomainDashboard = new AppDomain();
            appDomainDashboard.setName("TABLEROS");
            appDomainDashboard.setIdentify("09");
            appDomainDashboard.setCode("DASHBOARD");
            appDomainDashboard.setTenant(UserResolver.resolveUser().getTenant());
            appDomainDashboard = appRepo.create(appDomainDashboard);
        }

        AppDomain appDomainSystem = appRepo.findByName("SISTEMAS");
        if (appDomainSystem == null) {
            appDomainSystem = new AppDomain();
            appDomainSystem.setName("SISTEMAS");
            appDomainSystem.setIdentify("01");
            appDomainSystem.setCode("SYSTEM");
            appDomainSystem.setTenant(UserResolver.resolveUser().getTenant());
            appDomainSystem = appRepo.create(appDomainSystem);
        }

        AppDomain appDomainHr = appRepo.findByName("RECURSOS HUMANOS");
        if (appDomainHr == null) {
            appDomainHr = new AppDomain();
            appDomainHr.setName("RECURSOS HUMANOS");
            appDomainHr.setIdentify("02");
            appDomainHr.setCode("HR");
            appDomainHr.setTenant(UserResolver.resolveUser().getTenant());
            appDomainHr = appRepo.create(appDomainHr);
        }

        AppDomain appDomainMaintenance = appRepo.findByName("MANTENIMIENTOS");
        if (appDomainMaintenance == null) {
            appDomainMaintenance = new AppDomain();
            appDomainMaintenance.setName("MANTENIMIENTOS");
            appDomainMaintenance.setIdentify("03");
            appDomainMaintenance.setCode("MAINTENANCE");
            appDomainMaintenance.setTenant(UserResolver.resolveUser().getTenant());
            appDomainMaintenance = appRepo.create(appDomainMaintenance);
        }

        AppDomain appDomainSale = appRepo.findByName("VENTAS");
        if (appDomainSale == null) {
            appDomainSale = new AppDomain();
            appDomainSale.setName("VENTAS");
            appDomainSale.setIdentify("04");
            appDomainSale.setCode("SALE");
            appDomainSale.setTenant(UserResolver.resolveUser().getTenant());
            appDomainSale = appRepo.create(appDomainSale);
        }

        AppDomain appDomainBooking = appRepo.findByName("RESERVAS");
        if (appDomainBooking == null) {
            appDomainBooking = new AppDomain();
            appDomainBooking.setName("RESERVAS");
            appDomainBooking.setIdentify("05");
            appDomainBooking.setCode("BOOKING");
            appDomainBooking.setTenant(UserResolver.resolveUser().getTenant());
            appDomainBooking = appRepo.create(appDomainBooking);
        }

        AppDomain appDomainOperation = appRepo.findByName("OPERACIONES");
        if (appDomainOperation == null) {
            appDomainOperation = new AppDomain();
            appDomainOperation.setName("OPERACIONES");
            appDomainOperation.setIdentify("06");
            appDomainOperation.setCode("OPERATION");
            appDomainOperation.setTenant(UserResolver.resolveUser().getTenant());
            appDomainOperation = appRepo.create(appDomainOperation);
        }

        AppDomain appDomainAccount = appRepo.findByName("FINANZAS");
        if (appDomainAccount == null) {
            appDomainAccount = new AppDomain();
            appDomainAccount.setName("FINANZAS");
            appDomainAccount.setIdentify("07");
            appDomainAccount.setCode("ACCOUNT");
            appDomainAccount.setTenant(UserResolver.resolveUser().getTenant());
            appDomainAccount = appRepo.create(appDomainAccount);
        }

        AppDomain appDomainAnalytics = appRepo.findByName("REPORTES");
        if (appDomainAnalytics == null) {
            appDomainAnalytics = new AppDomain();
            appDomainAnalytics.setName("REPORTES");
            appDomainAnalytics.setIdentify("08");
            appDomainAnalytics.setCode("ANALYTICS");
            appDomainAnalytics.setTenant(UserResolver.resolveUser().getTenant());
            appDomainAnalytics = appRepo.create(appDomainAnalytics);
        }

        List<ObjectDomain> objectSecurityDomainList = new ArrayList<>();

        try {
            List<ObjectSecurity> objectSecurityList = importObjectSecurity.readExcel();
            for(ObjectSecurity objectSecurity: objectSecurityList){
                ObjectDomain objectDomain = objectRepo.findByName(objectSecurity.getName());
                if (objectDomain == null){
                    objectDomain = new ObjectDomain();
                    objectDomain.setIndex(objectSecurity.getIndex());
                    objectDomain.setName(objectSecurity.getName());
                    objectDomain.setIdentify(objectSecurity.getIdentify());
                    objectDomain.setEntity(objectSecurity.getSource());
                    if (objectSecurity.getApp().equals(appDomainSystem.getName())){
                        objectDomain.setApp(appDomainSystem);
                    }else if (objectSecurity.getApp().equals(appDomainHr.getName())){
                        objectDomain.setApp(appDomainHr);
                    }else if (objectSecurity.getApp().equals(appDomainMaintenance.getName())){
                        objectDomain.setApp(appDomainMaintenance);
                    }else if (objectSecurity.getApp().equals(appDomainSale.getName())){
                        objectDomain.setApp(appDomainSale);
                    }else if (objectSecurity.getApp().equals(appDomainBooking.getName())){
                        objectDomain.setApp(appDomainBooking);
                    }else if (objectSecurity.getApp().equals(appDomainOperation.getName())){
                        objectDomain.setApp(appDomainOperation);
                    }else if (objectSecurity.getApp().equals(appDomainAccount.getName())){
                        objectDomain.setApp(appDomainAccount);
                    }else if (objectSecurity.getApp().equals(appDomainAnalytics.getName())){
                        objectDomain.setApp(appDomainAnalytics);
                    }else if (objectSecurity.getApp().equals(appDomainDashboard.getName())){
                        objectDomain.setApp(appDomainDashboard);
                    }
                    objectDomain.setTenant(request.getTenantId());
                    objectRepo.create(objectDomain);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProfileDomain profileDomainManager = profileRepo.findByName("PERFIL DE ADMINISTRACIÓN");
        if (profileDomainManager == null) {
            profileDomainManager = new ProfileDomain();
            profileDomainManager.setName("PERFIL DE ADMINISTRACIÓN");
            profileDomainManager.setTenant(request.getTenantId());
            profileDomainManager.setPermissions(new HashSet<>());
            for (ObjectDomain objectDomain: objectSecurityDomainList){
                PermissionDomain permissionDomain = new PermissionDomain();
                permissionDomain.setTenant(request.getTenantId());
                permissionDomain.setEntity(objectDomain);
                permissionDomain.setPermDelete(true);
                permissionDomain.setPermRead(true);
                permissionDomain.setPermUpdate(true);
                permissionDomain.setPermCreate(true);
                permissionDomain = permissionRepo.create(permissionDomain);
                profileDomainManager.getPermissions().add(permissionDomain);
            }
            profileDomainManager = profileRepo.create(profileDomainManager);
        }

        ProfileDomain profileDomainAccount = profileRepo.findByName("PERFIL DE FINANZAS");
        if (profileDomainAccount == null) {
            profileDomainAccount = new ProfileDomain();
            profileDomainAccount.setName("PERFIL DE FINANZAS");
            profileDomainAccount.setTenant(request.getTenantId());
            profileDomainAccount.setPermissions(new HashSet<>());
            AppDomain finalAppDomainAccount = appDomainAccount;
            List<ObjectDomain> objectSecurityDomainListAccount = objectSecurityDomainList
                    .stream()
                    .filter(objectDomain -> objectDomain.getApp().getId().equals(finalAppDomainAccount.getId()))
                    .collect(Collectors.toList());
            for (ObjectDomain objectDomain: objectSecurityDomainListAccount){
                PermissionDomain permissionDomain = new PermissionDomain();
                permissionDomain.setTenant(request.getTenantId());
                permissionDomain.setEntity(objectDomain);
                permissionDomain.setPermDelete(true);
                permissionDomain.setPermRead(true);
                permissionDomain.setPermUpdate(true);
                permissionDomain.setPermCreate(true);
                permissionDomain = permissionRepo.create(permissionDomain);
                profileDomainAccount.getPermissions().add(permissionDomain);
            }
            profileDomainAccount = profileRepo.create(profileDomainAccount);
        }

        ProfileDomain profileDomainOperation = profileRepo.findByName("PERFIL DE OPERACIONES");
        if (profileDomainOperation == null) {
            profileDomainOperation = new ProfileDomain();
            profileDomainOperation.setName("PERFIL DE OPERACIONES");
            profileDomainOperation.setTenant(request.getTenantId());
            profileDomainOperation.setPermissions(new HashSet<>());
            AppDomain finalAppDomainOperation = appDomainOperation;
            List<ObjectDomain> objectSecurityDomainListOperation = objectSecurityDomainList
                    .stream()
                    .filter(objectDomain -> objectDomain.getApp().getId().equals(finalAppDomainOperation.getId()))
                    .collect(Collectors.toList());
            for (ObjectDomain objectDomain: objectSecurityDomainListOperation){
                PermissionDomain permissionDomain = new PermissionDomain();
                permissionDomain.setTenant(request.getTenantId());
                permissionDomain.setEntity(objectDomain);
                permissionDomain.setPermDelete(true);
                permissionDomain.setPermRead(true);
                permissionDomain.setPermUpdate(true);
                permissionDomain.setPermCreate(true);
                permissionDomain = permissionRepo.create(permissionDomain);
                profileDomainOperation.getPermissions().add(permissionDomain);
            }
            profileDomainOperation = profileRepo.create(profileDomainOperation);
        }

        ProfileDomain profileDomainBooking = profileRepo.findByName("PERFIL DE RESERVAS");
        if (profileDomainBooking == null) {
            profileDomainBooking = new ProfileDomain();
            profileDomainBooking.setName("PERFIL DE RESERVAS");
            profileDomainBooking.setTenant(request.getTenantId());
            profileDomainBooking.setPermissions(new HashSet<>());
            AppDomain finalAppDomainBooking = appDomainBooking;
            List<ObjectDomain> objectSecurityDomainListBooking = objectSecurityDomainList
                    .stream()
                    .filter(objectDomain -> objectDomain.getApp().getId().equals(finalAppDomainBooking.getId()))
                    .collect(Collectors.toList());
            for (ObjectDomain objectDomain: objectSecurityDomainListBooking){
                PermissionDomain permissionDomain = new PermissionDomain();
                permissionDomain.setTenant(request.getTenantId());
                permissionDomain.setEntity(objectDomain);
                permissionDomain.setPermDelete(true);
                permissionDomain.setPermRead(true);
                permissionDomain.setPermUpdate(true);
                permissionDomain.setPermCreate(true);
                permissionDomain = permissionRepo.create(permissionDomain);
                profileDomainBooking.getPermissions().add(permissionDomain);
            }
            profileDomainBooking = profileRepo.create(profileDomainBooking);
        }

        ProfileDomain profileDomainSale = profileRepo.findByName("PERFIL DE VENTAS");
        if (profileDomainSale == null) {
            profileDomainSale = new ProfileDomain();
            profileDomainSale.setName("PERFIL DE VENTAS");
            profileDomainSale.setTenant(request.getTenantId());
            profileDomainSale.setPermissions(new HashSet<>());
            AppDomain finalAppDomainSale = appDomainSale;
            List<ObjectDomain> objectSecurityDomainListSale = objectSecurityDomainList
                    .stream()
                    .filter(objectDomain -> objectDomain.getApp().getId().equals(finalAppDomainSale.getId()))
                    .collect(Collectors.toList());
            for (ObjectDomain objectDomain: objectSecurityDomainListSale){
                PermissionDomain permissionDomain = new PermissionDomain();
                permissionDomain.setTenant(request.getTenantId());
                permissionDomain.setEntity(objectDomain);
                permissionDomain.setPermDelete(true);
                permissionDomain.setPermRead(true);
                permissionDomain.setPermUpdate(true);
                permissionDomain.setPermCreate(true);
                permissionDomain = permissionRepo.create(permissionDomain);
                profileDomainSale.getPermissions().add(permissionDomain);
            }
            profileDomainSale = profileRepo.create(profileDomainSale);
        }

        ProfileDomain profileDomainHr = profileRepo.findByName("PERFIL DE RECURSOS HUMANOS");
        if (profileDomainHr == null) {
            profileDomainHr = new ProfileDomain();
            profileDomainHr.setName("PERFIL DE RECURSOS HUMANOS");
            profileDomainHr.setTenant(request.getTenantId());
            profileDomainHr.setPermissions(new HashSet<>());
            AppDomain finalAppDomainHr = appDomainHr;
            List<ObjectDomain> objectSecurityDomainListHr = objectSecurityDomainList
                    .stream()
                    .filter(objectDomain -> objectDomain.getApp().getId().equals(finalAppDomainHr.getId()))
                    .collect(Collectors.toList());
            for (ObjectDomain objectDomain: objectSecurityDomainListHr){
                PermissionDomain permissionDomain = new PermissionDomain();
                permissionDomain.setTenant(request.getTenantId());
                permissionDomain.setEntity(objectDomain);
                permissionDomain.setPermDelete(true);
                permissionDomain.setPermRead(true);
                permissionDomain.setPermUpdate(true);
                permissionDomain.setPermCreate(true);
                permissionDomain = permissionRepo.create(permissionDomain);
                profileDomainHr.getPermissions().add(permissionDomain);
            }
            profileDomainHr = profileRepo.create(profileDomainHr);
        }

        ProfileDomain profileDomainMaintenance = profileRepo.findByName("PERFIL DE GESTOR DE MANTENIMIENTOS");
        if (profileDomainMaintenance == null) {
            profileDomainMaintenance = new ProfileDomain();
            profileDomainMaintenance.setName("PERFIL DE GESTOR DE MANTENIMIENTOS");
            profileDomainMaintenance.setTenant(request.getTenantId());
            profileDomainMaintenance.setPermissions(new HashSet<>());
            AppDomain finalAppDomainMaintenance = appDomainMaintenance;
            List<ObjectDomain> objectSecurityDomainListMaintenance = objectSecurityDomainList
                    .stream()
                    .filter(objectDomain -> objectDomain.getApp().getId().equals(finalAppDomainMaintenance.getId()))
                    .collect(Collectors.toList());
            for (ObjectDomain objectDomain: objectSecurityDomainListMaintenance){
                PermissionDomain permissionDomain = new PermissionDomain();
                permissionDomain.setTenant(request.getTenantId());
                permissionDomain.setEntity(objectDomain);
                permissionDomain.setPermDelete(true);
                permissionDomain.setPermRead(true);
                permissionDomain.setPermUpdate(true);
                permissionDomain.setPermCreate(true);
                permissionDomain = permissionRepo.create(permissionDomain);
                profileDomainMaintenance.getPermissions().add(permissionDomain);
            }
            profileDomainMaintenance = profileRepo.create(profileDomainMaintenance);
        }

        ProfileDomain profileDomainAnalytics = profileRepo.findByName("PERFIL DE ANALISIS Y REPORTES");
        if (profileDomainAnalytics == null) {
            profileDomainAnalytics = new ProfileDomain();
            profileDomainAnalytics.setName("PERFIL DE ANALISIS Y REPORTES");
            profileDomainAnalytics.setTenant(request.getTenantId());
            profileDomainAnalytics = profileRepo.create(profileDomainAnalytics);
        }

        UserDomain userDomain = new UserDomain();
        userDomain.setUserName(request.getUserNameAdministratorTenant());
        userDomain.setEmail(request.getEmailAdministratorTenant());
        userDomain.setFullName(request.getFullNameAdministratorTenant());
        userDomain.setTenant(request.getTenantId());
        UserDomain createdUserDomain = userRepo.create(userDomain);

        // Se debe generar el password y enviarlo por mail al usuario con los datos de acceso al espacio
        UserPasswordDomain userPasswordDomain = _convertPass("12345", createdUserDomain);
        userPasswordDomain.setTenant(request.getTenantId());
        userPasswordRepo.create(userPasswordDomain);

        SupplierTypeDomain lodging = new SupplierTypeDomain();
        lodging.setIdentify(SupplierTypeDomain.LODGING);
        lodging.setName(SupplierTypeDomain.LODGING_NAME);
        lodging.setTenant(request.getTenantId());
        supplierTypeRepo.create(lodging);

        SupplierTypeDomain restaurant = new SupplierTypeDomain();
        restaurant.setIdentify(SupplierTypeDomain.RESTAURANT);
        restaurant.setName(SupplierTypeDomain.RESTAURANT_NAME);
        restaurant.setTenant(request.getTenantId());
        supplierTypeRepo.create(restaurant);

        SupplierTypeDomain guide = new SupplierTypeDomain();
        guide.setIdentify(SupplierTypeDomain.GUIDE);
        guide.setName(SupplierTypeDomain.GUIDE_NAME);
        guide.setTenant(request.getTenantId());
        supplierTypeRepo.create(guide);

        SupplierTypeDomain transport = new SupplierTypeDomain();
        transport.setIdentify(SupplierTypeDomain.TRANSPORT);
        transport.setName(SupplierTypeDomain.TRANSPORT_NAME);
        transport.setTenant(request.getTenantId());
        supplierTypeRepo.create(transport);

        SupplierTypeDomain operator = new SupplierTypeDomain();
        operator.setIdentify(SupplierTypeDomain.OPERATOR);
        operator.setName(SupplierTypeDomain.OPERATOR_NAME);
        operator.setTenant(request.getTenantId());
        supplierTypeRepo.create(operator);

        SupplierTypeDomain airLine = new SupplierTypeDomain();
        airLine.setIdentify(SupplierTypeDomain.AIR_LINE);
        airLine.setName(SupplierTypeDomain.AIR_LINE_NAME);
        airLine.setTenant(request.getTenantId());
        supplierTypeRepo.create(airLine);

        SupplierTypeDomain lodge = new SupplierTypeDomain();
        lodge.setIdentify(SupplierTypeDomain.LODGE);
        lodge.setName(SupplierTypeDomain.LODGE_NAME);
        lodge.setTenant(request.getTenantId());
        supplierTypeRepo.create(lodge);

        SupplierTypeDomain administrative = new SupplierTypeDomain();
        administrative.setIdentify(SupplierTypeDomain.ADMINISTRATIVE);
        administrative.setName(SupplierTypeDomain.ADMINISTRATIVE_NAME);
        administrative.setTenant(request.getTenantId());
        supplierTypeRepo.create(administrative);

        SupplierTypeDomain institution = new SupplierTypeDomain();
        institution.setIdentify(SupplierTypeDomain.INSTITUTION);
        institution.setName(SupplierTypeDomain.INSTITUTION_NAME);
        institution.setTenant(request.getTenantId());
        supplierTypeRepo.create(institution);

        SupplierTypeDomain glamping = new SupplierTypeDomain();
        glamping.setIdentify(SupplierTypeDomain.GLAMPING);
        glamping.setName(SupplierTypeDomain.GLAMPING_NAME);
        glamping.setTenant(request.getTenantId());
        supplierTypeRepo.create(glamping);

        DocumentAccountTypeDomain documentAccountTypeBill = new DocumentAccountTypeDomain();
        documentAccountTypeBill.setName(DocumentAccountTypeEnum.BILL.getDescription());
        documentAccountTypeBill.setDescription(DocumentAccountTypeEnum.BILL.getDescription());
        documentAccountTypeBill.setDocumentAccountType(DocumentAccountTypeEnum.BILL.getName());
        documentAccountTypeBill.setAbbrev(DocumentAccountTypeEnum.BILL.getAbbrev());
        documentAccountTypeBill.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeBill.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeBill);

        DocumentAccountTypeDomain documentAccountTypeBillSale = new DocumentAccountTypeDomain();
        documentAccountTypeBillSale.setName(DocumentAccountTypeEnum.BILL_SALE.getDescription());
        documentAccountTypeBillSale.setDescription(DocumentAccountTypeEnum.BILL_SALE.getDescription());
        documentAccountTypeBillSale.setDocumentAccountType(DocumentAccountTypeEnum.BILL_SALE.getName());
        documentAccountTypeBillSale.setAbbrev(DocumentAccountTypeEnum.BILL_SALE.getAbbrev());
        documentAccountTypeBillSale.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeBillSale.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeBillSale);

        DocumentAccountTypeDomain documentAccountTypeReceiptsFees = new DocumentAccountTypeDomain();
        documentAccountTypeReceiptsFees.setName(DocumentAccountTypeEnum.RECEIPTS_FOR_FEES.getDescription());
        documentAccountTypeReceiptsFees.setDescription(DocumentAccountTypeEnum.RECEIPTS_FOR_FEES.getDescription());
        documentAccountTypeReceiptsFees.setDocumentAccountType(DocumentAccountTypeEnum.RECEIPTS_FOR_FEES.getName());
        documentAccountTypeReceiptsFees.setAbbrev(DocumentAccountTypeEnum.RECEIPTS_FOR_FEES.getAbbrev());
        documentAccountTypeReceiptsFees.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeReceiptsFees.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeReceiptsFees);

        DocumentAccountTypeDomain documentAccountTypeTicket = new DocumentAccountTypeDomain();
        documentAccountTypeTicket.setName(DocumentAccountTypeEnum.TICKET.getDescription());
        documentAccountTypeTicket.setDescription(DocumentAccountTypeEnum.TICKET.getDescription());
        documentAccountTypeTicket.setDocumentAccountType(DocumentAccountTypeEnum.TICKET.getName());
        documentAccountTypeTicket.setAbbrev(DocumentAccountTypeEnum.TICKET.getAbbrev());
        documentAccountTypeTicket.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeTicket.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeTicket);

        DocumentAccountTypeDomain documentAccountTypeSeveralReceipts = new DocumentAccountTypeDomain();
        documentAccountTypeSeveralReceipts.setName(DocumentAccountTypeEnum.SEVERAL_RECEIPTS.getDescription());
        documentAccountTypeSeveralReceipts.setDescription(DocumentAccountTypeEnum.SEVERAL_RECEIPTS.getDescription());
        documentAccountTypeSeveralReceipts.setDocumentAccountType(DocumentAccountTypeEnum.SEVERAL_RECEIPTS.getName());
        documentAccountTypeSeveralReceipts.setAbbrev(DocumentAccountTypeEnum.SEVERAL_RECEIPTS.getAbbrev());
        documentAccountTypeSeveralReceipts.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeSeveralReceipts.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeSeveralReceipts);

        DocumentAccountTypeDomain documentAccountTypeCollectionDocument = new DocumentAccountTypeDomain();
        documentAccountTypeCollectionDocument.setName(DocumentAccountTypeEnum.COLLECTION_DOCUMENT.getDescription());
        documentAccountTypeCollectionDocument.setDescription(DocumentAccountTypeEnum.COLLECTION_DOCUMENT.getDescription());
        documentAccountTypeCollectionDocument.setDocumentAccountType(DocumentAccountTypeEnum.COLLECTION_DOCUMENT.getName());
        documentAccountTypeCollectionDocument.setAbbrev(DocumentAccountTypeEnum.COLLECTION_DOCUMENT.getAbbrev());
        documentAccountTypeCollectionDocument.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeCollectionDocument.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeCollectionDocument);

        DocumentAccountTypeDomain documentAccountTypeCreditNote = new DocumentAccountTypeDomain();
        documentAccountTypeCreditNote.setName(DocumentAccountTypeEnum.CREDIT_NOTE.getDescription());
        documentAccountTypeCreditNote.setDescription(DocumentAccountTypeEnum.CREDIT_NOTE.getDescription());
        documentAccountTypeCreditNote.setDocumentAccountType(DocumentAccountTypeEnum.CREDIT_NOTE.getName());
        documentAccountTypeCreditNote.setAbbrev(DocumentAccountTypeEnum.CREDIT_NOTE.getAbbrev());
        documentAccountTypeCreditNote.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeCreditNote.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeCreditNote);

        DocumentAccountTypeDomain documentAccountTypeDebitNote = new DocumentAccountTypeDomain();
        documentAccountTypeDebitNote.setName(DocumentAccountTypeEnum.DEBIT_NOTE.getDescription());
        documentAccountTypeDebitNote.setDescription(DocumentAccountTypeEnum.DEBIT_NOTE.getDescription());
        documentAccountTypeDebitNote.setDocumentAccountType(DocumentAccountTypeEnum.DEBIT_NOTE.getName());
        documentAccountTypeDebitNote.setAbbrev(DocumentAccountTypeEnum.DEBIT_NOTE.getAbbrev());
        documentAccountTypeDebitNote.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeDebitNote.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeDebitNote);

        DocumentAccountTypeDomain documentAccountTypeVacationSchedule = new DocumentAccountTypeDomain();
        documentAccountTypeVacationSchedule.setName(DocumentAccountTypeEnum.VACATION_SCHEDULE.getDescription());
        documentAccountTypeVacationSchedule.setDescription(DocumentAccountTypeEnum.VACATION_SCHEDULE.getDescription());
        documentAccountTypeVacationSchedule.setDocumentAccountType(DocumentAccountTypeEnum.VACATION_SCHEDULE.getName());
        documentAccountTypeVacationSchedule.setAbbrev(DocumentAccountTypeEnum.VACATION_SCHEDULE.getAbbrev());
        documentAccountTypeVacationSchedule.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeVacationSchedule.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeVacationSchedule);

        DocumentAccountTypeDomain documentAccountTypeSalarySheet = new DocumentAccountTypeDomain();
        documentAccountTypeSalarySheet.setName(DocumentAccountTypeEnum.SALARY_SHEET.getDescription());
        documentAccountTypeSalarySheet.setDescription(DocumentAccountTypeEnum.SALARY_SHEET.getDescription());
        documentAccountTypeSalarySheet.setDocumentAccountType(DocumentAccountTypeEnum.SALARY_SHEET.getName());
        documentAccountTypeSalarySheet.setAbbrev(DocumentAccountTypeEnum.SALARY_SHEET.getAbbrev());
        documentAccountTypeSalarySheet.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeSalarySheet.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeSalarySheet);

        DocumentAccountTypeDomain documentAccountTypeCtsWorkshop = new DocumentAccountTypeDomain();
        documentAccountTypeCtsWorkshop.setName(DocumentAccountTypeEnum.CTS_WORKSHOP.getDescription());
        documentAccountTypeCtsWorkshop.setDescription(DocumentAccountTypeEnum.CTS_WORKSHOP.getDescription());
        documentAccountTypeCtsWorkshop.setDocumentAccountType(DocumentAccountTypeEnum.CTS_WORKSHOP.getName());
        documentAccountTypeCtsWorkshop.setAbbrev(DocumentAccountTypeEnum.CTS_WORKSHOP.getAbbrev());
        documentAccountTypeCtsWorkshop.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeCtsWorkshop.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeCtsWorkshop);

        DocumentAccountTypeDomain documentAccountTypeAfpReturn = new DocumentAccountTypeDomain();
        documentAccountTypeAfpReturn.setName(DocumentAccountTypeEnum.AFP_RETURN.getDescription());
        documentAccountTypeAfpReturn.setDescription(DocumentAccountTypeEnum.AFP_RETURN.getDescription());
        documentAccountTypeAfpReturn.setDocumentAccountType(DocumentAccountTypeEnum.AFP_RETURN.getName());
        documentAccountTypeAfpReturn.setAbbrev(DocumentAccountTypeEnum.AFP_RETURN.getAbbrev());
        documentAccountTypeAfpReturn.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeAfpReturn.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeAfpReturn);

        DocumentAccountTypeDomain documentAccountTypeTransfers = new DocumentAccountTypeDomain();
        documentAccountTypeTransfers.setName(DocumentAccountTypeEnum.TRANSFERS.getDescription());
        documentAccountTypeTransfers.setDescription(DocumentAccountTypeEnum.TRANSFERS.getDescription());
        documentAccountTypeTransfers.setDocumentAccountType(DocumentAccountTypeEnum.TRANSFERS.getName());
        documentAccountTypeTransfers.setAbbrev(DocumentAccountTypeEnum.TRANSFERS.getAbbrev());
        documentAccountTypeTransfers.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeTransfers.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeTransfers);

        DocumentAccountTypeDomain documentAccountTypeReturn = new DocumentAccountTypeDomain();
        documentAccountTypeReturn.setName(DocumentAccountTypeEnum.RETURN.getDescription());
        documentAccountTypeReturn.setDescription(DocumentAccountTypeEnum.RETURN.getDescription());
        documentAccountTypeReturn.setDocumentAccountType(DocumentAccountTypeEnum.RETURN.getName());
        documentAccountTypeReturn.setAbbrev(DocumentAccountTypeEnum.RETURN.getAbbrev());
        documentAccountTypeReturn.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeReturn.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeReturn);

        DocumentAccountTypeDomain documentAccountTypeDetraction = new DocumentAccountTypeDomain();
        documentAccountTypeDetraction.setName(DocumentAccountTypeEnum.DETRACTION.getDescription());
        documentAccountTypeDetraction.setDescription(DocumentAccountTypeEnum.DETRACTION.getDescription());
        documentAccountTypeDetraction.setDocumentAccountType(DocumentAccountTypeEnum.DETRACTION.getName());
        documentAccountTypeDetraction.setAbbrev(DocumentAccountTypeEnum.DETRACTION.getAbbrev());
        documentAccountTypeDetraction.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeDetraction.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeDetraction);

        DocumentAccountTypeDomain documentAccountTypeSettlementExpenses = new DocumentAccountTypeDomain();
        documentAccountTypeSettlementExpenses.setName(DocumentAccountTypeEnum.SETTLEMENT_EXPENSES.getDescription());
        documentAccountTypeSettlementExpenses.setDescription(DocumentAccountTypeEnum.SETTLEMENT_EXPENSES.getDescription());
        documentAccountTypeSettlementExpenses.setDocumentAccountType(DocumentAccountTypeEnum.SETTLEMENT_EXPENSES.getName());
        documentAccountTypeSettlementExpenses.setAbbrev(DocumentAccountTypeEnum.SETTLEMENT_EXPENSES.getAbbrev());
        documentAccountTypeSettlementExpenses.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeSettlementExpenses.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeSettlementExpenses);

        DocumentAccountTypeDomain documentAccountTypeOther = new DocumentAccountTypeDomain();
        documentAccountTypeOther.setName(DocumentAccountTypeEnum.OTHER.getDescription());
        documentAccountTypeOther.setDescription(DocumentAccountTypeEnum.OTHER.getDescription());
        documentAccountTypeOther.setDocumentAccountType(DocumentAccountTypeEnum.OTHER.getName());
        documentAccountTypeOther.setAbbrev(DocumentAccountTypeEnum.OTHER.getAbbrev());
        documentAccountTypeOther.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        documentAccountTypeOther.setTenant(request.getTenantId());
        documentAccountTypeRepo.create(documentAccountTypeOther);

        GroupPqtTouristDomain groupPqtTouristDomain1 = new GroupPqtTouristDomain();
        groupPqtTouristDomain1.setName("ALIMENTACION EN RESTAURANT");
        groupPqtTouristDomain1.setDescription("ALIMENTACION EN RESTAURANT");
        groupPqtTouristDomain1.setIdentify(persistGroupPqtTouristNumberCallback.generateNumber());
        groupPqtTouristDomain1.setTenant(request.getTenantId());
        groupPqtTouristRepo.create(groupPqtTouristDomain1);

        GroupPqtTouristDomain groupPqtTouristDomain2 = new GroupPqtTouristDomain();
        groupPqtTouristDomain2.setName("GUIADOS");
        groupPqtTouristDomain2.setDescription("GUIADOS");
        groupPqtTouristDomain2.setIdentify(persistGroupPqtTouristNumberCallback.generateNumber());
        groupPqtTouristDomain2.setTenant(request.getTenantId());
        groupPqtTouristRepo.create(groupPqtTouristDomain2);

        GroupPqtTouristDomain groupPqtTouristDomain3 = new GroupPqtTouristDomain();
        groupPqtTouristDomain3.setName("ENTRADA DE ESPECTACULOS");
        groupPqtTouristDomain3.setDescription("ENTRADA DE ESPECTACULOS");
        groupPqtTouristDomain3.setIdentify(persistGroupPqtTouristNumberCallback.generateNumber());
        groupPqtTouristDomain3.setTenant(request.getTenantId());
        groupPqtTouristRepo.create(groupPqtTouristDomain3);

        GroupPqtTouristDomain groupPqtTouristDomain4 = new GroupPqtTouristDomain();
        groupPqtTouristDomain4.setName("TRANSPORTE TURISTICO");
        groupPqtTouristDomain4.setDescription("TRANSPORTE TURISTICO");
        groupPqtTouristDomain4.setIdentify(persistGroupPqtTouristNumberCallback.generateNumber());
        groupPqtTouristDomain4.setTenant(request.getTenantId());
        groupPqtTouristRepo.create(groupPqtTouristDomain4);

        GroupPqtTouristDomain groupPqtTouristDomain5 = new GroupPqtTouristDomain();
        groupPqtTouristDomain5.setName("SERVICIO CONFORMANTE DEL PAQUE");
        groupPqtTouristDomain5.setDescription("SERVICIO CONFORMANTE DEL PAQUE");
        groupPqtTouristDomain5.setIdentify(persistGroupPqtTouristNumberCallback.generateNumber());
        groupPqtTouristDomain5.setTenant(request.getTenantId());
        groupPqtTouristRepo.create(groupPqtTouristDomain5);

        GroupPqtTouristDomain groupPqtTouristDomain6 = new GroupPqtTouristDomain();
        groupPqtTouristDomain6.setName("DOCUMENTO DE COBRANZA");
        groupPqtTouristDomain6.setDescription("DOCUMENTO DE COBRANZA");
        groupPqtTouristDomain6.setIdentify(persistGroupPqtTouristNumberCallback.generateNumber());
        groupPqtTouristDomain6.setTenant(request.getTenantId());
        groupPqtTouristRepo.create(groupPqtTouristDomain6);

        GroupPqtTouristDomain groupPqtTouristDomain7 = new GroupPqtTouristDomain();
        groupPqtTouristDomain7.setName("SERVICIO TURISTICO");
        groupPqtTouristDomain7.setDescription("SERVICIO TURISTICO");
        groupPqtTouristDomain7.setIdentify(persistGroupPqtTouristNumberCallback.generateNumber());
        groupPqtTouristDomain7.setTenant(request.getTenantId());
        groupPqtTouristRepo.create(groupPqtTouristDomain7);

        ServicePqtTouristDomain servicePqtTouristDomain1 = servicePqtTouristRepo.findByName("HOTELES Y ALOJAMIENTO");
        if (servicePqtTouristDomain1 == null) {
            servicePqtTouristDomain1 = new ServicePqtTouristDomain();
            servicePqtTouristDomain1.setName("HOTELES Y ALOJAMIENTO");
            servicePqtTouristDomain1.setDescription("HOTELES Y ALOJAMIENTO");
            servicePqtTouristDomain1.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain1.setAffectServiceIgv(false);
            servicePqtTouristDomain1.setCostServiceIgv(false);
            servicePqtTouristDomain1.setDocumentAccountType(documentAccountTypeTicket);
            servicePqtTouristDomain1.setGroupPqtTourist(groupPqtTouristDomain5);
            servicePqtTouristDomain1.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain1);
        }

        ServicePqtTouristDomain servicePqtTouristDomain2 = servicePqtTouristRepo.findByName("RESTAURANTE");
        if (servicePqtTouristDomain2 == null) {
            servicePqtTouristDomain2 = new ServicePqtTouristDomain();
            servicePqtTouristDomain2.setName("RESTAURANTE");
            servicePqtTouristDomain2.setDescription("RESTAURANTE");
            servicePqtTouristDomain2.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain2.setAffectServiceIgv(true);
            servicePqtTouristDomain2.setCostServiceIgv(false);
            servicePqtTouristDomain2.setDocumentAccountType(documentAccountTypeBill);
            servicePqtTouristDomain2.setGroupPqtTourist(groupPqtTouristDomain1);
            servicePqtTouristDomain2.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain2);
        }

        ServicePqtTouristDomain servicePqtTouristDomain3 = servicePqtTouristRepo.findByName("GUIAS DE TURISMO");
        if (servicePqtTouristDomain3 == null) {
            servicePqtTouristDomain3 = new ServicePqtTouristDomain();
            servicePqtTouristDomain3.setName("GUIAS DE TURISMO");
            servicePqtTouristDomain3.setDescription("GUIAS DE TURISMO");
            servicePqtTouristDomain3.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain3.setAffectServiceIgv(false);
            servicePqtTouristDomain3.setCostServiceIgv(false);
            servicePqtTouristDomain3.setDocumentAccountType(documentAccountTypeBillSale);
            servicePqtTouristDomain3.setGroupPqtTourist(groupPqtTouristDomain5);
            servicePqtTouristDomain3.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain3);
        }

        ServicePqtTouristDomain servicePqtTouristDomain4 = servicePqtTouristRepo.findByName("TREN");
        if (servicePqtTouristDomain4 == null) {
            servicePqtTouristDomain4 = new ServicePqtTouristDomain();
            servicePqtTouristDomain4.setName("TREN");
            servicePqtTouristDomain4.setDescription("TREN");
            servicePqtTouristDomain4.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain4.setAffectServiceIgv(false);
            servicePqtTouristDomain4.setCostServiceIgv(false);
            servicePqtTouristDomain4.setDocumentAccountType(documentAccountTypeBillSale);
            servicePqtTouristDomain4.setGroupPqtTourist(groupPqtTouristDomain5);
            servicePqtTouristDomain4.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain4);
        }

        ServicePqtTouristDomain servicePqtTouristDomain5 = servicePqtTouristRepo.findByName("VUELOS");
        if (servicePqtTouristDomain5 == null) {
            servicePqtTouristDomain5 = new ServicePqtTouristDomain();
            servicePqtTouristDomain5.setName("VUELOS");
            servicePqtTouristDomain5.setDescription("VUELOS");
            servicePqtTouristDomain5.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain5.setAffectServiceIgv(false);
            servicePqtTouristDomain5.setCostServiceIgv(false);
            servicePqtTouristDomain5.setDocumentAccountType(documentAccountTypeCollectionDocument);
            servicePqtTouristDomain5.setGroupPqtTourist(groupPqtTouristDomain6);
            servicePqtTouristDomain5.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain5);
        }

        ServicePqtTouristDomain servicePqtTouristDomain6 = servicePqtTouristRepo.findByName("SERVICIO DE GUIADO");
        if (servicePqtTouristDomain6 == null) {
            servicePqtTouristDomain6 = new ServicePqtTouristDomain();
            servicePqtTouristDomain6.setName("SERVICIO DE GUIADO");
            servicePqtTouristDomain6.setDescription("SERVICIO DE GUIADO");
            servicePqtTouristDomain6.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain6.setAffectServiceIgv(true);
            servicePqtTouristDomain6.setCostServiceIgv(false);
            servicePqtTouristDomain6.setDocumentAccountType(documentAccountTypeBill);
            servicePqtTouristDomain6.setGroupPqtTourist(groupPqtTouristDomain2);
            servicePqtTouristDomain6.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain6);
        }

        ServicePqtTouristDomain servicePqtTouristDomain7 = servicePqtTouristRepo.findByName("SERVICIO DE TRANSPORTE TURISTICO");
        if (servicePqtTouristDomain7 == null) {
            servicePqtTouristDomain7 = new ServicePqtTouristDomain();
            servicePqtTouristDomain7.setName("SERVICIO DE TRANSPORTE TURISTICO");
            servicePqtTouristDomain7.setDescription("SERVICIO DE TRANSPORTE TURISTICO");
            servicePqtTouristDomain7.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain7.setAffectServiceIgv(true);
            servicePqtTouristDomain7.setCostServiceIgv(false);
            servicePqtTouristDomain7.setDocumentAccountType(documentAccountTypeBill);
            servicePqtTouristDomain7.setGroupPqtTourist(groupPqtTouristDomain1);
            servicePqtTouristDomain7.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain7);
        }

        ServicePqtTouristDomain servicePqtTouristDomain8 = servicePqtTouristRepo.findByName("TRANSPORTE");
        if (servicePqtTouristDomain8 == null) {
            servicePqtTouristDomain8 = new ServicePqtTouristDomain();
            servicePqtTouristDomain8.setName("TRANSPORTE");
            servicePqtTouristDomain8.setDescription("TRANSPORTE");
            servicePqtTouristDomain8.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain8.setAffectServiceIgv(false);
            servicePqtTouristDomain8.setCostServiceIgv(false);
            servicePqtTouristDomain8.setDocumentAccountType(documentAccountTypeBillSale);
            servicePqtTouristDomain8.setGroupPqtTourist(groupPqtTouristDomain5);
            servicePqtTouristDomain8.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain8);
        }

        ServicePqtTouristDomain servicePqtTouristDomain9 = servicePqtTouristRepo.findByName("TICKETS ENTRADAS - MUSEOS");
        if (servicePqtTouristDomain9 == null) {
            servicePqtTouristDomain9 = new ServicePqtTouristDomain();
            servicePqtTouristDomain9.setName("TICKETS ENTRADAS - MUSEOS");
            servicePqtTouristDomain9.setDescription("TICKETS ENTRADAS - MUSEOS");
            servicePqtTouristDomain9.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain9.setAffectServiceIgv(false);
            servicePqtTouristDomain9.setCostServiceIgv(false);
            servicePqtTouristDomain9.setDocumentAccountType(documentAccountTypeBillSale);
            servicePqtTouristDomain9.setGroupPqtTourist(groupPqtTouristDomain5);
            servicePqtTouristDomain9.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain9);
        }

        ServicePqtTouristDomain servicePqtTouristDomain10 = servicePqtTouristRepo.findByName("ORGANIZACION DE SERVICIOS - OPERADORES");
        if (servicePqtTouristDomain10 == null) {
            servicePqtTouristDomain10 = new ServicePqtTouristDomain();
            servicePqtTouristDomain10.setName("ORGANIZACION DE SERVICIOS - OPERADORES");
            servicePqtTouristDomain10.setDescription("ORGANIZACION DE SERVICIOS - OPERADORES");
            servicePqtTouristDomain10.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain10.setAffectServiceIgv(true);
            servicePqtTouristDomain10.setCostServiceIgv(false);
            servicePqtTouristDomain10.setDocumentAccountType(documentAccountTypeBillSale);
            servicePqtTouristDomain10.setGroupPqtTourist(groupPqtTouristDomain5);
            servicePqtTouristDomain10.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain10);
        }

        ServicePqtTouristDomain servicePqtTouristDomain11 = servicePqtTouristRepo.findByName("SERVICIO DE ESPECTACULOS");
        if (servicePqtTouristDomain11 == null) {
            servicePqtTouristDomain11 = new ServicePqtTouristDomain();
            servicePqtTouristDomain11.setName("SERVICIO DE ESPECTACULOS");
            servicePqtTouristDomain11.setDescription("SERVICIO DE ESPECTACULOS");
            servicePqtTouristDomain11.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain11.setAffectServiceIgv(false);
            servicePqtTouristDomain11.setCostServiceIgv(true);
            servicePqtTouristDomain11.setDocumentAccountType(documentAccountTypeBill);
            servicePqtTouristDomain11.setGroupPqtTourist(groupPqtTouristDomain3);
            servicePqtTouristDomain11.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain11);
        }

        ServicePqtTouristDomain servicePqtTouristDomain12 = servicePqtTouristRepo.findByName("TRANSPORTE AEREO (SERVICIO TURISTICO)");
        if (servicePqtTouristDomain12 == null) {
            servicePqtTouristDomain12 = new ServicePqtTouristDomain();
            servicePqtTouristDomain12.setName("TRANSPORTE AEREO (SERVICIO TURISTICO)");
            servicePqtTouristDomain12.setDescription("TRANSPORTE AEREO (SERVICIO TURISTICO)");
            servicePqtTouristDomain12.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain12.setAffectServiceIgv(true);
            servicePqtTouristDomain12.setCostServiceIgv(false);
            servicePqtTouristDomain12.setDocumentAccountType(documentAccountTypeBill);
            servicePqtTouristDomain12.setGroupPqtTourist(groupPqtTouristDomain4);
            servicePqtTouristDomain12.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain12);
        }

        ServicePqtTouristDomain servicePqtTouristDomain13 = servicePqtTouristRepo.findByName("TRANSPORTE TERRESTRE");
        if (servicePqtTouristDomain13 == null) {
            servicePqtTouristDomain13 = new ServicePqtTouristDomain();
            servicePqtTouristDomain13.setName("TRANSPORTE TERRESTRE");
            servicePqtTouristDomain13.setDescription("TRANSPORTE TERRESTRE");
            servicePqtTouristDomain13.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain13.setAffectServiceIgv(false);
            servicePqtTouristDomain13.setCostServiceIgv(false);
            servicePqtTouristDomain13.setDocumentAccountType(documentAccountTypeBillSale);
            servicePqtTouristDomain13.setGroupPqtTourist(groupPqtTouristDomain5);
            servicePqtTouristDomain13.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain13);
        }

        ServicePqtTouristDomain servicePqtTouristDomain14 = servicePqtTouristRepo.findByName("TRANSPORTE ACUATICO (SERVICIO TURISTICO)");
        if (servicePqtTouristDomain14 == null) {
            servicePqtTouristDomain14 = new ServicePqtTouristDomain();
            servicePqtTouristDomain14.setName("TRANSPORTE ACUATICO (SERVICIO TURISTICO)");
            servicePqtTouristDomain14.setDescription("TRANSPORTE ACUATICO (SERVICIO TURISTICO)");
            servicePqtTouristDomain14.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain14.setAffectServiceIgv(true);
            servicePqtTouristDomain14.setCostServiceIgv(false);
            servicePqtTouristDomain14.setDocumentAccountType(documentAccountTypeBill);
            servicePqtTouristDomain14.setGroupPqtTourist(groupPqtTouristDomain4);
            servicePqtTouristDomain14.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain14);
        }

        ServicePqtTouristDomain servicePqtTouristDomain15 = servicePqtTouristRepo.findByName("SERVICIO DE ALIMENTACION");
        if (servicePqtTouristDomain15 == null) {
            servicePqtTouristDomain15 = new ServicePqtTouristDomain();
            servicePqtTouristDomain15.setName("SERVICIO DE ALIMENTACION");
            servicePqtTouristDomain15.setDescription("SERVICIO DE ALIMENTACION");
            servicePqtTouristDomain15.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain15.setAffectServiceIgv(true);
            servicePqtTouristDomain15.setCostServiceIgv(false);
            servicePqtTouristDomain15.setDocumentAccountType(documentAccountTypeBill);
            servicePqtTouristDomain15.setGroupPqtTourist(groupPqtTouristDomain1);
            servicePqtTouristDomain15.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain15);
        }

        ServicePqtTouristDomain servicePqtTouristDomain16 = servicePqtTouristRepo.findByName("TRANSPORTE HTL/APTO - APTO/HTL");
        if (servicePqtTouristDomain16 == null) {
            servicePqtTouristDomain16 = new ServicePqtTouristDomain();
            servicePqtTouristDomain16.setName("TRANSPORTE HTL/APTO - APTO/HTL");
            servicePqtTouristDomain16.setDescription("TRANSPORTE HTL/APTO - APTO/HTL");
            servicePqtTouristDomain16.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain16.setAffectServiceIgv(false);
            servicePqtTouristDomain16.setCostServiceIgv(true);
            servicePqtTouristDomain16.setDocumentAccountType(documentAccountTypeBillSale);
            servicePqtTouristDomain16.setGroupPqtTourist(groupPqtTouristDomain4);
            servicePqtTouristDomain16.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain16);
        }

        ServicePqtTouristDomain servicePqtTouristDomain17 = servicePqtTouristRepo.findByName("TRANSPORTES CON IGV");
        if (servicePqtTouristDomain17 == null) {
            servicePqtTouristDomain17 = new ServicePqtTouristDomain();
            servicePqtTouristDomain17.setName("TRANSPORTES CON IGV");
            servicePqtTouristDomain17.setDescription("TRANSPORTES CON IGV");
            servicePqtTouristDomain17.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain17.setAffectServiceIgv(true);
            servicePqtTouristDomain17.setCostServiceIgv(true);
            servicePqtTouristDomain17.setDocumentAccountType(documentAccountTypeBillSale);
            servicePqtTouristDomain17.setGroupPqtTourist(groupPqtTouristDomain4);
            servicePqtTouristDomain17.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain17);
        }

        ServicePqtTouristDomain servicePqtTouristDomain18 = servicePqtTouristRepo.findByName("SELVA - EXONERADO DEL IGV");
        if (servicePqtTouristDomain18 == null) {
            servicePqtTouristDomain18 = new ServicePqtTouristDomain();
            servicePqtTouristDomain18.setName("SELVA - EXONERADO DEL IGV");
            servicePqtTouristDomain18.setDescription("SELVA - EXONERADO DEL IGV");
            servicePqtTouristDomain18.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain18.setAffectServiceIgv(true);
            servicePqtTouristDomain18.setCostServiceIgv(true);
            servicePqtTouristDomain18.setDocumentAccountType(documentAccountTypeBillSale);
            servicePqtTouristDomain18.setGroupPqtTourist(groupPqtTouristDomain5);
            servicePqtTouristDomain18.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain18);
        }

        ServicePqtTouristDomain servicePqtTouristDomain19 = servicePqtTouristRepo.findByName("TURISMO VIVENCIAL");
        if (servicePqtTouristDomain19 == null) {
            servicePqtTouristDomain19 = new ServicePqtTouristDomain();
            servicePqtTouristDomain19.setName("TURISMO VIVENCIAL");
            servicePqtTouristDomain19.setDescription("TURISMO VIVENCIAL");
            servicePqtTouristDomain19.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain19.setAffectServiceIgv(true);
            servicePqtTouristDomain19.setCostServiceIgv(false);
            servicePqtTouristDomain19.setDocumentAccountType(documentAccountTypeBill);
            servicePqtTouristDomain19.setGroupPqtTourist(groupPqtTouristDomain7);
            servicePqtTouristDomain19.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain19);
        }

        ServicePqtTouristDomain servicePqtTouristDomain20 = servicePqtTouristRepo.findByName("TURISMO DE AVENTURA");
        if (servicePqtTouristDomain20 == null) {
            servicePqtTouristDomain20 = new ServicePqtTouristDomain();
            servicePqtTouristDomain20.setName("TURISMO DE AVENTURA");
            servicePqtTouristDomain20.setDescription("TURISMO DE AVENTURA");
            servicePqtTouristDomain20.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain20.setAffectServiceIgv(true);
            servicePqtTouristDomain20.setCostServiceIgv(false);
            servicePqtTouristDomain20.setDocumentAccountType(documentAccountTypeBill);
            servicePqtTouristDomain20.setGroupPqtTourist(groupPqtTouristDomain7);
            servicePqtTouristDomain20.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain20);
        }

        ServicePqtTouristDomain servicePqtTouristDomain21 = servicePqtTouristRepo.findByName("SERVICIO RURAL");
        if (servicePqtTouristDomain21 == null) {
            servicePqtTouristDomain21 = new ServicePqtTouristDomain();
            servicePqtTouristDomain21.setName("SERVICIO RURAL");
            servicePqtTouristDomain21.setDescription("SERVICIO RURAL");
            servicePqtTouristDomain21.setIdentify(persistServicePqtTouristNumberCallback.generateNumber());
            servicePqtTouristDomain21.setAffectServiceIgv(true);
            servicePqtTouristDomain21.setCostServiceIgv(false);
            servicePqtTouristDomain21.setDocumentAccountType(documentAccountTypeBill);
            servicePqtTouristDomain21.setGroupPqtTourist(groupPqtTouristDomain7);
            servicePqtTouristDomain21.setTenant(request.getTenantId());
            servicePqtTouristRepo.create(servicePqtTouristDomain21);
        }

        return TenantResponse.from(createdOrganizationDomain.getId(), createdCompanyDomain.getId(), createdUserDomain.getId());
    }

    private UserPasswordDomain _convertPass(String plainPassword, UserDomain user) {
        String salt = UUID.randomUUID().toString();
        String passHash = passwordService.hash(plainPassword, salt);
        return new UserPasswordDomain(
                salt,
                passHash,
                user
        );
    }
}
