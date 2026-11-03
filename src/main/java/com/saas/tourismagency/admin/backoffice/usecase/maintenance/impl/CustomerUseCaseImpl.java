package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultPartner;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultCustomer;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.partner.PartnerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.bank.PersistBankNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.PersistCustomerNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerUpdateRequest;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.TypeInheritanceEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.BankDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.CustomerUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CustomerUseCaseImpl implements CustomerUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private CustomerRepo repo;

    @Autowired
    private PartnerRepo partnerRepo;

    @Autowired
    private CountryRepo repoCountry;

    @Autowired
    private CustomerConverter converter;

    @Autowired
    private SetFieldDefaultCustomer setFieldDefault;

    @Autowired
    private SetFieldDefaultPartner setFieldDefaultPartner;

    @Autowired
    PersistCustomerNumberCallbackImpl persistCustomerNumberCallbackImpl;

    @Override
    @Transactional
    public CustomerResponse create(CustomerCreateRequest request) {
        CustomerDomain customerDomainExist = repo.findByNameAndTypeInheritance(request.getName(), TypeInheritanceEnum.CUSTOMER.getName());
        if (customerDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        PartnerDomain newPartnerDomain = request.getPartner().to();
        CountryDomain countryDomain = null;
        if (request.getPartner().getAddress().getCountry() != null
                && request.getPartner().getAddress().getCountry().getId() != null
                && !request.getPartner().getAddress().getCountry().getId().isEmpty()) {
            countryDomain = repoCountry.findById(request.getPartner().getAddress().getCountry().getId());
        }
        newPartnerDomain.getAddress().setCountry(countryDomain);
        newPartnerDomain.setTenant(UserResolver.resolveUser().getTenant());
        PartnerDomain createdPartnerDomain = partnerRepo.create(newPartnerDomain);
        CustomerDomain newDomain = request.to(createdPartnerDomain);
        newDomain.setTypeInheritance(TypeInheritanceEnum.CUSTOMER.getName());
        newDomain.setIdentify(persistCustomerNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        CustomerDomain createdDomain = repo.create(newDomain);
        return CustomerResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CustomerResponse edit(String id, CustomerUpdateRequest request) {
        if (id == null) {
            return null;
        }
        CustomerDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No customer found with id '%s'.".formatted(id));
        }
        CustomerDomain customerDomainExist = repo.findByNameAndTypeInheritance(request.getName(), TypeInheritanceEnum.CUSTOMER.getName());
        if (customerDomainExist != null && !customerDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        PartnerDomain oldPartner = partnerRepo.findById(request.getPartner().getId());
        if (oldPartner == null) {
            throw new NotFoundException("No partner found with id '%s'.".formatted(request.getPartner().getId()));
        }
        PartnerDomain updatePartnerDomain = request.getPartner().to();
        CountryDomain countryDomain = null;
        if (request.getPartner().getAddress().getCountry().getId() != null
                && request.getPartner().getAddress().getCountry() != null
                && !request.getPartner().getAddress().getCountry().getId().isEmpty()) {
            countryDomain = repoCountry.findById(request.getPartner().getAddress().getCountry().getId());
        }
        updatePartnerDomain.getAddress().setCountry(countryDomain);
        PartnerDomain editedDomainPartner = partnerRepo.edit(setFieldDefaultPartner.setDefaultFieldBaseUpdated(updatePartnerDomain, oldPartner));

        CustomerDomain customerDomain = request.to(editedDomainPartner);
        customerDomain.setTypeInheritance(TypeInheritanceEnum.CUSTOMER.getName());
        customerDomain.setIdentify(old.getIdentify());
        CustomerDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(customerDomain, old));
        return CustomerResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        CustomerResponse old = findById(id);
        if (referenceGuard.isReferenced("Customer", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> findAll() {
        return repo.findAll(TypeInheritanceEnum.CUSTOMER.getName()).stream().map(customerDomain -> CustomerResponse.from(customerDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse findById(String id) {
        if (id == null) {
            return null;
        }
        CustomerDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No customer found with id '%s'.".formatted(id));
        }
        return CustomerResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerSearchResponse searchAll(CustomerSearchRequest customerSearchRequest) {
       PageDTO<CustomerDomain> pageDTO = repo.searchAll(customerSearchRequest);
       CustomerSearchResponse customerSearchRespose = new CustomerSearchResponse();
       customerSearchRespose.setCustomers(pageDTO.getContent().stream().map(customerDomain ->  CustomerResponse.from(customerDomain)).toList());
       customerSearchRespose.setTotalElements(pageDTO.getTotalElements());
       customerSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return customerSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerSearchResponse searchFilterAll(CustomerSearchFilterRequest customerSearchFilterRequest) {
        PageDTO<CustomerDomain> pageDTO = repo.searchAllFilter(customerSearchFilterRequest);
        CustomerSearchResponse customerSearchRespose = new CustomerSearchResponse();
        customerSearchRespose.setCustomers(pageDTO.getContent().stream().map(customerDomain ->  CustomerResponse.from(customerDomain)).toList());
        customerSearchRespose.setTotalElements(pageDTO.getTotalElements());
        customerSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return customerSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        CustomerDomain old = repo.findByNameAndTypeInheritance(name, TypeInheritanceEnum.CUSTOMER.getName());
        if (old == null) {
            throw new NotFoundException("No customer found with name '%s'.".formatted(name));
        }
        return CustomerResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        CustomerDomain old = repo.findByIdentifyAndTypeInheritance(identify, TypeInheritanceEnum.CUSTOMER.getName());
        if (old == null) {
            throw new NotFoundException("No customer found with identify '%s'.".formatted(identify));
        }
        return CustomerResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBaseResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }
}
