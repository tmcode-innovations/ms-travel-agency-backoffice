package com.saas.tourismagency.admin.backoffice.usecase.statistics.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.program.ProgramRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.QuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.ServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.statistics.CountBaseResponse;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.statistics.GroupedDataDomain;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.statistics.StatisticsUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StatisticsUseCaseImpl implements StatisticsUseCase {

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private ProgramRepo programRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private TourRepo tourRepo;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Override
    @Transactional
    public CountBaseResponse countQuotation() {
        return CountBaseResponse.from(quotationRepo.countQuotation());
    }

    @Override
    @Transactional
    public CountBaseResponse countRegisterGroup() {
        return CountBaseResponse.from(registerGroupRepo.countRegisterGroup());
    }

    @Override
    @Transactional
    public CountBaseResponse countProgram() {
        return CountBaseResponse.from(programRepo.countProgram());
    }

    @Override
    @Transactional
    public CountBaseResponse countCustomer() {
        return CountBaseResponse.from(customerRepo.countCustomer());
    }

    @Override
    @Transactional
    public CountBaseResponse countSupplier() {
        return CountBaseResponse.from(supplierRepo.countSupplier());
    }

    @Override
    @Transactional
    public CountBaseResponse countService() {
        return CountBaseResponse.from(serviceRepo.countService());
    }

    @Override
    @Transactional
    public CountBaseResponse countTour() {
        return CountBaseResponse.from(tourRepo.countTour());
    }

    @Override
    @Transactional
    public List<Integer> supplierByType() {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        List<Integer> result = new ArrayList<>();
        List<GroupedDataDomain> list = supplierRepo.aggregateSupplierByType();
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGuide = supplierTypeRepo.findOne("3", null);
        SupplierTypeDomain supplierTypeDomainTransport = supplierTypeRepo.findOne("4", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainAirLine = supplierTypeRepo.findOne("6", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainAdministrative = supplierTypeRepo.findOne("8", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);
        result.add(list.stream().filter(groupedDataDomain -> supplierTypeDomainLodging.getId().equals(groupedDataDomain.getId())).findFirst().orElse(new GroupedDataDomain()).getCount());
        result.add(list.stream().filter(groupedDataDomain -> supplierTypeDomainRestaurant.getId().equals(groupedDataDomain.getId())).findFirst().orElse(new GroupedDataDomain()).getCount());
        result.add(list.stream().filter(groupedDataDomain -> supplierTypeDomainGuide.getId().equals(groupedDataDomain.getId())).findFirst().orElse(new GroupedDataDomain()).getCount());
        result.add(list.stream().filter(groupedDataDomain -> supplierTypeDomainTransport.getId().equals(groupedDataDomain.getId())).findFirst().orElse(new GroupedDataDomain()).getCount());
        result.add(list.stream().filter(groupedDataDomain -> supplierTypeDomainOperator.getId().equals(groupedDataDomain.getId())).findFirst().orElse(new GroupedDataDomain()).getCount());
        result.add(list.stream().filter(groupedDataDomain -> supplierTypeDomainAirLine.getId().equals(groupedDataDomain.getId())).findFirst().orElse(new GroupedDataDomain()).getCount());
        result.add(list.stream().filter(groupedDataDomain -> supplierTypeDomainLodge.getId().equals(groupedDataDomain.getId())).findFirst().orElse(new GroupedDataDomain()).getCount());
        result.add(list.stream().filter(groupedDataDomain -> supplierTypeDomainAdministrative.getId().equals(groupedDataDomain.getId())).findFirst().orElse(new GroupedDataDomain()).getCount());
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            result.add(list.stream().filter(groupedDataDomain -> supplierTypeDomainGlamping.getId().equals(groupedDataDomain.getId())).findFirst().orElse(new GroupedDataDomain()).getCount());
            result.add(list.stream().filter(groupedDataDomain -> supplierTypeDomainInstitution.getId().equals(groupedDataDomain.getId())).findFirst().orElse(new GroupedDataDomain()).getCount());
        }
        return result;
    }
}
