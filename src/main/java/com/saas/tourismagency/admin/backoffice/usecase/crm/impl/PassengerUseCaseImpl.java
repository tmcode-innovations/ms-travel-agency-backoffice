package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.passenger.PassengerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultPerson;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultPassenger;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.nationality.NationalityRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.person.PersonRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.passenger.PassengerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.QuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documenttype.DocumentTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.passengertype.PassengerTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.passenger.*;
import com.saas.tourismagency.admin.backoffice.adapter.events.crm.event.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.PassengerDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.crm.PassengerUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PassengerUseCaseImpl implements PassengerUseCase {

    @Autowired
    private PassengerRepo repo;

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private NationalityRepo nationalityRepo;

    @Autowired
    private DocumentTypeRepo documentTypeRepo;

    @Autowired
    private PassengerTypeRepo passengerTypeRepo;

    @Autowired
    private PassengerConverter converter;

    @Autowired
    private SetFieldDefaultPassenger setFieldDefault;

    @Autowired
    private SetFieldDefaultPerson setFieldDefaultPerson;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Override
    @Transactional
    public PassengerResponse create(PassengerCreateRequest request) {
        QuotationDomain quotationDomain = null;
        if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
            quotationDomain = quotationRepo.findById(request.getQuotation());
        }
        NationalityDomain nationalityDomain = null;
        if (request.getNationality() != null && !request.getNationality().isEmpty()){
            nationalityDomain = nationalityRepo.findById(request.getNationality());
        }
        DocumentTypeDomain documentTypeDomain = null;
        if (request.getDocumentType() != null && !request.getDocumentType().isEmpty()){
            documentTypeDomain = documentTypeRepo.findById(request.getDocumentType());
        }
        PersonDomain newPersonDomain = request.getPerson().to();
        if (newPersonDomain.getDateBirthday() != null){
            newPersonDomain.setAge(Period.between(newPersonDomain.getDateBirthday(), LocalDate.now()).getYears());
        }
        PassengerTypeDomain passengerTypeDomain = null;
        if (request.getPassengerType() != null && !request.getPassengerType().isEmpty()){
            passengerTypeDomain = passengerTypeRepo.findById(request.getPassengerType());
        }
        newPersonDomain.setTenant(UserResolver.resolveUser().getTenant());
        PersonDomain createdPersonDomain = personRepo.create(newPersonDomain);
        PassengerDomain newDomain = request.to(createdPersonDomain, nationalityDomain, documentTypeDomain, quotationDomain, passengerTypeDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIndex(this.getLastIndexItineraryItem(quotationDomain.getId()));
        PassengerDomain createdDomain = repo.create(newDomain);
        if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
            if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                this.eventPublisher.publishEvent(new PassengerCrmCreateEvent(this, createdDomain));
            }
        }
        return PassengerResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public PassengerResponse create(PassengerCreateRequest request, QuotationDomain quotationDomain) {
        NationalityDomain nationalityDomain = null;
        if (request.getNationality() != null && !request.getNationality().isEmpty()){
            nationalityDomain = nationalityRepo.findById(request.getNationality());
        }
        DocumentTypeDomain documentTypeDomain = null;
        if (request.getDocumentType() != null && !request.getDocumentType().isEmpty()){
            documentTypeDomain = documentTypeRepo.findById(request.getDocumentType());
        }
        PersonDomain newPersonDomain = request.getPerson().to();
        if (newPersonDomain.getDateBirthday() != null){
            newPersonDomain.setAge(Period.between(newPersonDomain.getDateBirthday(), LocalDate.now()).getYears());
        }
        PassengerTypeDomain passengerTypeDomain = null;
        if (request.getPassengerType() != null && !request.getPassengerType().isEmpty()){
            passengerTypeDomain = passengerTypeRepo.findById(request.getPassengerType());
        }
        newPersonDomain.setTenant(UserResolver.resolveUser().getTenant());
        PersonDomain createdPersonDomain = personRepo.create(newPersonDomain);
        PassengerDomain newDomain = request.to(createdPersonDomain, nationalityDomain, documentTypeDomain, quotationDomain, passengerTypeDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIndex(this.getLastIndexItineraryItem(quotationDomain.getId()));
        PassengerDomain createdDomain = repo.create(newDomain);
        if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
            if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                this.eventPublisher.publishEvent(new PassengerCrmCreateEvent(this, createdDomain));
            }
        }
        return PassengerResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public PassengerResponse edit(String id, PassengerUpdateRequest request) {
        if (id == null) {
            return null;
        }
        PassengerDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        PersonDomain oldPerson = personRepo.findById(request.getPerson().getId());
        if (oldPerson == null) {
            throw new NotFoundException("No person found with id '%s'.".formatted(request.getPerson().getId()));
        }
        QuotationDomain quotationDomain = null;
        if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
            quotationDomain = quotationRepo.findById(request.getQuotation());
        }
        NationalityDomain nationalityDomain = null;
        if (request.getNationality() != null && !request.getNationality().isEmpty()){
            nationalityDomain = nationalityRepo.findById(request.getNationality());
        }
        DocumentTypeDomain documentTypeDomain = null;
        if (request.getDocumentType() != null && !request.getDocumentType().isEmpty()){
            documentTypeDomain = documentTypeRepo.findById(request.getDocumentType());
        }
        PassengerTypeDomain passengerTypeDomain = null;
        if (request.getPassengerType() != null && !request.getPassengerType().isEmpty()){
            passengerTypeDomain = passengerTypeRepo.findById(request.getPassengerType());
        }
        PersonDomain updatePersonDomain = request.getPerson().to();
        if (updatePersonDomain.getDateBirthday() != null){
            updatePersonDomain.setAge(Period.between(updatePersonDomain.getDateBirthday(), LocalDate.now()).getYears());
        }
        PersonDomain editedDomainPerson = personRepo.edit(setFieldDefaultPerson.setDefaultFieldBaseUpdated(updatePersonDomain, oldPerson));

        PassengerDomain passengerDomain = request.to(editedDomainPerson, nationalityDomain, documentTypeDomain, quotationDomain, passengerTypeDomain);
        passengerDomain.setIndex(old.getIndex());
        PassengerDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(passengerDomain, old));
        if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
            if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                this.eventPublisher.publishEvent(new PassengerCrmEditEvent(this, editedDomain));
            }
        }
        return PassengerResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public PassengerResponse edit(String id, PassengerUpdateRequest request, QuotationDomain quotationDomain) {
        if (id == null) {
            return null;
        }
        PassengerDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        PersonDomain oldPerson = personRepo.findById(request.getPerson().getId());
        if (oldPerson == null) {
            throw new NotFoundException("No person found with id '%s'.".formatted(request.getPerson().getId()));
        }
        NationalityDomain nationalityDomain = null;
        if (request.getNationality() != null && !request.getNationality().isEmpty()){
            nationalityDomain = nationalityRepo.findById(request.getNationality());
        }
        DocumentTypeDomain documentTypeDomain = null;
        if (request.getDocumentType() != null && !request.getDocumentType().isEmpty()){
            documentTypeDomain = documentTypeRepo.findById(request.getDocumentType());
        }
        PassengerTypeDomain passengerTypeDomain = null;
        if (request.getPassengerType() != null && !request.getPassengerType().isEmpty()){
            passengerTypeDomain = passengerTypeRepo.findById(request.getPassengerType());
        }
        PersonDomain updatePersonDomain = request.getPerson().to();
        if (updatePersonDomain.getDateBirthday() != null){
            updatePersonDomain.setAge(Period.between(updatePersonDomain.getDateBirthday(), LocalDate.now()).getYears());
        }
        PersonDomain editedDomainPerson = personRepo.edit(setFieldDefaultPerson.setDefaultFieldBaseUpdated(updatePersonDomain, oldPerson));

        PassengerDomain passengerDomain = request.to(editedDomainPerson, nationalityDomain, documentTypeDomain, quotationDomain, passengerTypeDomain);
        passengerDomain.setIndex(old.getIndex());
        PassengerDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(passengerDomain, old));
        if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
            if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                this.eventPublisher.publishEvent(new PassengerCrmEditEvent(this, editedDomain));
            }
        }
        return PassengerResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        PassengerResponse old = findById(id);
        repo.deleteById(old.getId());
        if (old.getQuotation().getIdentifyFile() != null && !old.getQuotation().getIdentifyFile().isEmpty()){
            CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
            if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
                this.eventPublisher.publishEvent(new PassengerCrmDeleteEvent(this, repo.findById(id)));
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerResponse> findAll() {
        return repo.findAll(false).stream().map(passengerDomain -> PassengerResponse.from(passengerDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PassengerResponse findById(String id) {
        if (id == null) {
            return null;
        }
        PassengerDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No passenger found with id '%s'.".formatted(id));
        }
        return PassengerResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public PassengerSearchResponse searchAll(PassengerSearchRequest passengerSearchRequest) {
       PageDTO<PassengerDomain> pageDTO = repo.searchAll(passengerSearchRequest);
       PassengerSearchResponse passengerSearchRespose = new PassengerSearchResponse();
       passengerSearchRespose.setPassengers(pageDTO.getContent().stream().map(passengerDomain ->  PassengerResponse.from(passengerDomain)).toList());
       passengerSearchRespose.setTotalElements(pageDTO.getTotalElements());
       passengerSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return passengerSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public PassengerSearchResponse searchFilterAll(PassengerSearchFilterRequest passengerSearchFilterRequest) {
        PageDTO<PassengerDomain> pageDTO = repo.searchAllFilter(passengerSearchFilterRequest);
        PassengerSearchResponse passengerSearchRespose = new PassengerSearchResponse();
        passengerSearchRespose.setPassengers(pageDTO.getContent().stream().map(passengerDomain ->  PassengerResponse.from(passengerDomain)).toList());
        passengerSearchRespose.setTotalElements(pageDTO.getTotalElements());
        passengerSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return passengerSearchRespose;
    }

    @Override
    @Transactional
    public List<PassengerResponse> searchAllByQuotation(String idQuotation) {
        List<PassengerDomain> list = repo.searchAllByQuotation(idQuotation);
        if (list != null && !list.isEmpty()){
            list = list.stream().collect(Collectors.toList());
            PassengerDomain first = list.getFirst();
            if (first.getIndex() == null){
                Integer index = 1;
                for (PassengerDomain passengerDomain : list){
                    passengerDomain.setIndex(index);
                    index = index + 1;
                    repo.edit(passengerDomain);
                }
            }
            Collections.sort(list);
        }
        return list.stream().map(passengerDomain ->  PassengerResponse.from(passengerDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PassengerResponse searchByQuotationAndPassenger(String idQuotation, String idPassenger) {
        PassengerDomain passengerDomain = repo.searchAllByQuotationAndPassenger(idQuotation, idPassenger);
        return PassengerResponse.from(passengerDomain);
    }

    private Integer getLastIndexItineraryItem(String idQuotation) {
        Integer index = 0;
        List<PassengerDomain> list = repo.searchAllByQuotationSimpleDomainCaseGetCollectionForLastIndex(idQuotation).stream().collect(Collectors.toList());
        list.sort(Comparator.comparing(PassengerDomain::getIndex));
        long count = list.size();
        if (count != 0){
            index = list.getLast().getIndex() + 1;
        }else{
            index = 1;
        }
        return index;
    }

}
