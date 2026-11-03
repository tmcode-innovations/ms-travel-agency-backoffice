package com.saas.tourismagency.admin.backoffice.usecase.core.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.ChangeTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.QuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.ServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.app.AppRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.objectsecurity.ObjectRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.permission.PermissionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.profile.ProfileRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.EmployeeUserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.updatedata.UpdateDataCustomerProcessRequest;
import com.saas.tourismagency.admin.backoffice.adapter.importdata.excel.ImportObjectSecurity;
import com.saas.tourismagency.admin.backoffice.adapter.importdata.model.security.ObjectSecurity;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.AppDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.PermissionDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ProfileDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.core.UpdateDataUseCase;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UpdateDataUseCaseImpl implements UpdateDataUseCase {

    @Autowired
    private ItineraryItemRepo itineraryItemRepo;

    @Autowired
    private ChangeTypeRepo changeTypeRepo;

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ImportObjectSecurity importObjectSecurity;

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private ObjectRepo objectRepo;

    @Autowired
    private AppRepo appRepo;

    @Autowired
    private PermissionRepo permissionRepo;

    @Autowired
    private EmployeeUserRepo employeeUserRepo;

    @Override
    @Transactional
    public void updateItineraryItemDateTimeInBooking() {
        List<ItineraryItemDomain> list = itineraryItemRepo.findAll(false);
        for (ItineraryItemDomain itineraryItemDomain : list){
            LocalDateTime localDateTime = LocalDateTime.of(
                    itineraryItemDomain.getItineraryDate().getItineraryDate().getYear(),
                    itineraryItemDomain.getItineraryDate().getItineraryDate().getMonth(),
                    itineraryItemDomain.getItineraryDate().getItineraryDate().getDayOfMonth(),
                    itineraryItemDomain.getItineraryDateTime().getHour(),
                    itineraryItemDomain.getItineraryDateTime().getMinute(),
                    itineraryItemDomain.getItineraryDateTime().getSecond()
            );
            itineraryItemDomain.setItineraryDateTime(localDateTime);
            itineraryItemRepo.edit(itineraryItemDomain);
        }
    }

    @Override
    @Transactional
    public void updateItineraryItemCustomerInBooking() {
        List<ItineraryItemDomain> list = itineraryItemRepo.searchAllSimpleDomainCaseToolUpdateCustomer();
        for (ItineraryItemDomain itineraryItemDomain : list){
            itineraryItemRepo.updateCustomerByIdSimpleDomainCaseUpdateToolCustomer(itineraryItemDomain.getId(), itineraryItemDomain.getRegisterGroup().getCustomer().getId());
        }
    }

    @Override
    @Transactional
    public void updateDateChangeTypeInAccount() {
        List<ChangeTypeDomain> list = changeTypeRepo.searchAllSimpleDomainCaseToolUpdateChangeType();
        for (ChangeTypeDomain changeTypeDomain : list){
            changeTypeRepo.updateCustomerByIdSimpleDomainCaseUpdateToolChangeType(changeTypeDomain.getId(), changeTypeDomain.getDateProcess().getDate());
        }
    }

    @Override
    @Transactional
    public void updateFieldAbbrevInServiceMaintenance() {
        List<ServiceDomain> list = serviceRepo.searchAllSimpleDomainCaseToolUpdateAbbrev();
        for (ServiceDomain serviceDomain : list){
            serviceRepo.updateAbbrevByIdSimpleDomainCaseUpdateToolAbbrev(serviceDomain.getId(), serviceDomain.getName());
        }
    }

    @Override
    @Transactional
    public void updateFieldSummaryInSupplierMaintenance() {
        List<SupplierDomain> list = supplierRepo.searchAllSimpleDomainCaseToolUpdateSummary();
        for (SupplierDomain supplierDomain : list){
            supplierRepo.updateSummaryByIdSimpleDomainCaseUpdateToolSummary(supplierDomain.getId(), supplierDomain.getName());
        }
    }

    @Override
    @Transactional
    public void updateChangeCustomerProcess(UpdateDataCustomerProcessRequest request) {
        IdBaseDomain idQuotationBaseDoman = quotationRepo.findByIdentifyEntity(request.getIdentifyQuotation());
        if (idQuotationBaseDoman == null) {
            throw new NotFoundException("No entity quotation found with id '%s'.".formatted(idQuotationBaseDoman.getId()));
        }

        CustomerDomain customer = customerRepo.findByName(request.getNameCustomer());
        if (customer == null) {
            throw new NotFoundException("No entity customer found with name '%s'.".formatted(request.getNameCustomer()));
        }

        // Quotation update
        quotationRepo.updateIdSimpleDomainCaseCustomerModification(idQuotationBaseDoman.getId(), customer.getId());

        // Register Group update
        RegisterGroupDomain registerGroupDomain = registerGroupRepo.findByIdQuotationCaseGetModificationCustomerData(idQuotationBaseDoman.getId());
        if (registerGroupDomain != null){
            registerGroupRepo.updateIdSimpleDomainCaseCustomerModification(registerGroupDomain.getId(), customer.getId());

            List<ItineraryItemDomain> list = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseDelete(registerGroupDomain.getId());
            if (list != null && !list.isEmpty()){
                for (ItineraryItemDomain itineraryItemDomain : list){
                    itineraryItemRepo.updateIdSimpleDomainCaseCustomerModification(itineraryItemDomain.getId(), customer.getId());
                }
            }
        }

    }

    @Override
    public void addProfileConsultantAccount() {
        AppDomain appDomainAccount = appRepo.findByName("FINANZAS");
        AppDomain appDomainDashboard = appRepo.findByName("TABLEROS");

        ObjectDomain objectDomainTemp = objectRepo.findByName("Consultor de Finanzas");
        if (objectDomainTemp == null){
            objectDomainTemp = new ObjectDomain();
            objectDomainTemp.setIndex(126);
            objectDomainTemp.setName("Consultor de Finanzas");
            objectDomainTemp.setIdentify("CODE_PERM_ACCOUNT_CONSULTANT");
            objectDomainTemp.setEntity("Account - Consultant");
            objectDomainTemp.setApp(appDomainAccount);

            objectDomainTemp.setTenant(UserResolver.resolveUser().getTenant());
            objectRepo.create(objectDomainTemp);
        }


        if (appDomainAccount != null && appDomainDashboard != null){
            List<ObjectDomain> objectSecurityDomainList = objectRepo.findAll(false);

            ProfileDomain profileDomainAccountConsultant = profileRepo.findByName("PERFIL DE FINANZAS - CONSULTOR");
            if (profileDomainAccountConsultant == null) {
                profileDomainAccountConsultant = new ProfileDomain();
                profileDomainAccountConsultant.setName("PERFIL DE FINANZAS - CONSULTOR");
                profileDomainAccountConsultant.setTenant(UserResolver.resolveUser().getTenant());
                profileDomainAccountConsultant.setPermissions(new HashSet<>());
                AppDomain finalAppDomainAccount = appDomainAccount;
                AppDomain finalAppDomainDashboard = appDomainDashboard;
                List<ObjectDomain> objectSecurityDomainListAccount = objectSecurityDomainList
                        .stream()
                        .filter(objectDomain -> objectDomain.getApp().getId().equals(finalAppDomainAccount.getId()))
                        .collect(Collectors.toList());
                List<ObjectDomain> objectSecurityDomainListDashboard = objectSecurityDomainList
                        .stream()
                        .filter(objectDomain -> objectDomain.getApp().getId().equals(finalAppDomainDashboard.getId()))
                        .collect(Collectors.toList());
                for (ObjectDomain objectDomain: objectSecurityDomainListAccount){
                    PermissionDomain permissionDomain = new PermissionDomain();
                    permissionDomain.setTenant(UserResolver.resolveUser().getTenant());
                    permissionDomain.setEntity(objectDomain);
                    permissionDomain.setPermDelete(false);
                    permissionDomain.setPermRead(true);
                    permissionDomain.setPermUpdate(false);
                    permissionDomain.setPermCreate(false);
                    permissionDomain = permissionRepo.create(permissionDomain);
                    profileDomainAccountConsultant.getPermissions().add(permissionDomain);
                }
                for (ObjectDomain objectDomain: objectSecurityDomainListDashboard){
                    PermissionDomain permissionDomain = new PermissionDomain();
                    permissionDomain.setTenant(UserResolver.resolveUser().getTenant());
                    permissionDomain.setEntity(objectDomain);
                    permissionDomain.setPermDelete(true);
                    permissionDomain.setPermRead(true);
                    permissionDomain.setPermUpdate(true);
                    permissionDomain.setPermCreate(true);
                    permissionDomain = permissionRepo.create(permissionDomain);
                    profileDomainAccountConsultant.getPermissions().add(permissionDomain);
                }
                profileRepo.create(profileDomainAccountConsultant);
            }
        }
    }

    @Override
    @Transactional
    public void updateEmployeeInQuotationByCreatedUser() {
        List<QuotationDomain> list = quotationRepo.searchAllSimpleDomainCaseToolUpdateEmployee();
        for (QuotationDomain quotationDomain : list) {
            if (quotationDomain.getCreatedBy() == null ||
                    quotationDomain.getCreatedBy().getId() == null ||
                    quotationDomain.getCreatedBy().getId().isEmpty()){
                continue;
            }
            EmployeeUserDomain employeeUserDomain = employeeUserRepo.getByUser(quotationDomain.getCreatedBy().getId());
            if (employeeUserDomain != null && employeeUserDomain.getEmployee() != null){
                quotationRepo.updateEmployeeByIdSimpleDomainCaseUpdateToolEmployee(
                        quotationDomain.getId(),
                        employeeUserDomain.getEmployee().getId());
            }else{
                log.info("Quotation {} skipped: no employee mapping for creator user {}", quotationDomain.getId(), quotationDomain.getCreatedBy().getId());
            }
        }
    }

}
