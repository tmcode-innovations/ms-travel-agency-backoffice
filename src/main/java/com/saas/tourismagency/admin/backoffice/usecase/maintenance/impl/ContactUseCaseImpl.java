package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;

import dev.mm.javaee.core.exceptions.BadRequestException;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.contact.ContactConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultPerson;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultContact;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.person.PersonRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.contact.ContactRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.contact.ContactCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.contact.ContactResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.contact.ContactUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.contact.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ContactDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.ContactTypeEnum;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.ContactUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ContactUseCaseImpl implements ContactUseCase {

    @Autowired
    private ContactRepo repo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private ContactConverter converter;

    @Autowired
    private SetFieldDefaultContact setFieldDefault;

    @Autowired
    private SetFieldDefaultPerson setFieldDefaultPerson;

    @Override
    @Transactional
    public ContactResponse create(ContactCreateRequest request) {
        if (repo.findByName(request.getName()) != null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        PersonDomain newPersonDomain = request.getPerson().to();
        CustomerDomain customerDomain = null;
        SupplierTypeDomain supplierTypeDomain = null;
        SupplierDomain supplierDomain = null;
        if (request.getContactType().equals(ContactTypeEnum.CUSTOMER.getName())){
            if (request.getCustomer() != null && !request.getCustomer().isEmpty()){
                customerDomain = customerRepo.findById(request.getCustomer());
            }
        }else if (request.getContactType().equals(ContactTypeEnum.SUPPLIER.getName())){
            if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
                supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
            }
            if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
                supplierDomain = supplierRepo.findById(request.getSupplier());
            }
        }
        newPersonDomain.setTenant(UserResolver.resolveUser().getTenant());
        PersonDomain createdPersonDomain = personRepo.create(newPersonDomain);
        ContactDomain newDomain = null;
        if (request.getContactType().equals(ContactTypeEnum.CUSTOMER.getName())){
            newDomain = request.to(createdPersonDomain, customerDomain);
        }else if (request.getContactType().equals(ContactTypeEnum.SUPPLIER.getName())){
            newDomain = request.to(createdPersonDomain, supplierDomain, supplierTypeDomain);
        }
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        ContactDomain createdDomain = repo.create(newDomain);
        return ContactResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ContactResponse edit(String id, ContactUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ContactDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No contact found with id '%s'.".formatted(id));
        }
        if (repo.findByName(request.getName()) != null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        PersonDomain oldPerson = personRepo.findById(request.getPerson().getId());
        if (oldPerson == null) {
            throw new NotFoundException("No person found with id '%s'.".formatted(request.getPerson().getId()));
        }
        PersonDomain updatePersonDomain = request.getPerson().to();
        CustomerDomain customerDomain = null;
        SupplierTypeDomain supplierTypeDomain = null;
        SupplierDomain supplierDomain = null;
        if (request.getContactType().equals(ContactTypeEnum.CUSTOMER.getName())){
            if (request.getCustomer() != null && !request.getCustomer().isEmpty()){
                customerDomain = customerRepo.findById(request.getCustomer());
            }
        }else if (request.getContactType().equals(ContactTypeEnum.SUPPLIER.getName())){
            if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
                supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
            }
            if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
                supplierDomain = supplierRepo.findById(request.getSupplier());
            }
        }
        PersonDomain editedDomainPerson = personRepo.edit(setFieldDefaultPerson.setDefaultFieldBaseUpdated(updatePersonDomain, oldPerson));
        ContactDomain contactDomain = null;
        if (request.getContactType().equals(ContactTypeEnum.CUSTOMER.getName())){
            contactDomain = request.to(editedDomainPerson, customerDomain);
        }else if (request.getContactType().equals(ContactTypeEnum.SUPPLIER.getName())){
            contactDomain = request.to(editedDomainPerson, supplierDomain, supplierTypeDomain);
        }
        ContactDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(contactDomain, old));
        return ContactResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ContactResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactResponse> findAll() {
        return repo.findAll( false).stream().map(contactDomain -> ContactResponse.from(contactDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactResponse> searchAllByContactType(ContactSearchRequestByContactType contactSearchRequestByContactType) {
        return repo.findAll(contactSearchRequestByContactType.getContactType(), false).stream().map(contactDomain -> ContactResponse.from(contactDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ContactResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ContactDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No contact found with id '%s'.".formatted(id));
        }
        return ContactResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ContactSearchResponse searchAll(ContactSearchRequest contactSearchRequest) {
       PageDTO<ContactDomain> pageDTO = repo.searchAll(contactSearchRequest);
       ContactSearchResponse contactSearchRespose = new ContactSearchResponse();
       contactSearchRespose.setContacts(pageDTO.getContent().stream().map(contactDomain ->  ContactResponse.from(contactDomain)).toList());
       contactSearchRespose.setTotalElements(pageDTO.getTotalElements());
       contactSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return contactSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ContactSearchResponse searchFilterAll(ContactSearchFilterRequest contactSearchFilterRequest) {
        PageDTO<ContactDomain> pageDTO = repo.searchAllFilter(contactSearchFilterRequest);
        ContactSearchResponse contactSearchRespose = new ContactSearchResponse();
        contactSearchRespose.setContacts(pageDTO.getContent().stream().map(contactDomain ->  ContactResponse.from(contactDomain)).toList());
        contactSearchRespose.setTotalElements(pageDTO.getTotalElements());
        contactSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return contactSearchRespose;
    }
}
