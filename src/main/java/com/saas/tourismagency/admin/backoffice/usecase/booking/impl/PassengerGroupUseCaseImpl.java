package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.passengergroup.PassengerGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultPerson;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultPassengerGroup;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.nationality.NationalityRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.person.PersonRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.PassengerGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documenttype.DocumentTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.passengertype.PassengerTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.*;
import com.saas.tourismagency.admin.backoffice.adapter.events.booking.event.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.PassengerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.PassengerGroupUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
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
public class PassengerGroupUseCaseImpl implements PassengerGroupUseCase {

    @Autowired
    private PassengerGroupRepo repo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private NationalityRepo nationalityRepo;

    @Autowired
    private DocumentTypeRepo documentTypeRepo;

    @Autowired
    private PassengerTypeRepo passengerTypeRepo;

    @Autowired
    private PassengerGroupConverter converter;

    @Autowired
    private SetFieldDefaultPassengerGroup setFieldDefault;

    @Autowired
    private SetFieldDefaultPerson setFieldDefaultPerson;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Override
    @Transactional
    public PassengerGroupResponse create(PassengerGroupCreateRequest request) {
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
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
        PassengerGroupDomain newDomain = request.to(createdPersonDomain, nationalityDomain, documentTypeDomain, registerGroupDomain, passengerTypeDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIndex(this.getLastIndexItineraryItem(registerGroupDomain.getId()));
        PassengerGroupDomain createdDomain = repo.create(newDomain);
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            this.eventPublisher.publishEvent(new PassengerCreateEvent(this, createdDomain));
        }*/
        return PassengerGroupResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public PassengerGroupResponse create(PassengerGroupCreateRequest request, RegisterGroupDomain registerGroupDomain) {
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
        PassengerGroupDomain newDomain = request.to(createdPersonDomain, nationalityDomain, documentTypeDomain, registerGroupDomain, passengerTypeDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIndex(this.getLastIndexItineraryItem(registerGroupDomain.getId()));
        PassengerGroupDomain createdDomain = repo.create(newDomain);
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            this.eventPublisher.publishEvent(new PassengerCreateEvent(this, createdDomain));
        }*/
        return PassengerGroupResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public PassengerGroupResponse edit(String id, PassengerGroupUpdateRequest request) {
        if (id == null) {
            return null;
        }
        PassengerGroupDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        PersonDomain oldPerson = personRepo.findById(request.getPerson().getId());
        if (oldPerson == null) {
            throw new NotFoundException("No person found with id '%s'.".formatted(request.getPerson().getId()));
        }
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
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

        PassengerGroupDomain passengerGroupDomain = request.to(editedDomainPerson, nationalityDomain, documentTypeDomain, registerGroupDomain, passengerTypeDomain);

        passengerGroupDomain.setIdPassengerQuotation(old.getIdPassengerQuotation());
        passengerGroupDomain.setIndex(old.getIndex());
        PassengerGroupDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(passengerGroupDomain, old));
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            this.eventPublisher.publishEvent(new PassengerEditEvent(this, editedDomain));
        }*/

        return PassengerGroupResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public PassengerGroupResponse edit(String id, PassengerGroupUpdateRequest request, RegisterGroupDomain registerGroupDomain) {
        if (id == null) {
            return null;
        }
        PassengerGroupDomain old = repo.findById(id);
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

        PassengerGroupDomain passengerGroupDomain = request.to(editedDomainPerson, nationalityDomain, documentTypeDomain, registerGroupDomain, passengerTypeDomain);

        passengerGroupDomain.setIdPassengerQuotation(old.getIdPassengerQuotation());
        passengerGroupDomain.setIndex(old.getIndex());
        PassengerGroupDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(passengerGroupDomain, old));

        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            this.eventPublisher.publishEvent(new PassengerEditEvent(this, editedDomain));
        }*/

        return PassengerGroupResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        PassengerGroupResponse old = findById(id);
        repo.deleteById(old.getId());
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            this.eventPublisher.publishEvent(new PassengerDeleteEvent(this, repo.findById(id)));
        }*/
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerGroupResponse> findAll() {
        return repo.findAll(false).stream().map(passengerGroupDomain -> PassengerGroupResponse.from(passengerGroupDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PassengerGroupResponse findById(String id) {
        if (id == null) {
            return null;
        }
        PassengerGroupDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return PassengerGroupResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public PassengerGroupSearchResponse searchAll(PassengerGroupSearchRequest passengerGroupSearchRequest) {
       PageDTO<PassengerGroupDomain> pageDTO = repo.searchAll(passengerGroupSearchRequest);
       PassengerGroupSearchResponse bankSearchRespose = new PassengerGroupSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  PassengerGroupResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public PassengerGroupSearchResponse searchFilterAll(PassengerGroupSearchFilterRequest passengerGroupSearchFilterRequest) {
        PageDTO<PassengerGroupDomain> pageDTO = repo.searchAllFilter(passengerGroupSearchFilterRequest);
        PassengerGroupSearchResponse bankSearchRespose = new PassengerGroupSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  PassengerGroupResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional
    public List<PassengerGroupResponse> searchAllByRegisterGroup(String idRegisterGroup) {
        List<PassengerGroupDomain> list = repo.searchAllByRegisterGroup(idRegisterGroup);
        if (list != null && !list.isEmpty()){
            list = list.stream().collect(Collectors.toList());
            PassengerGroupDomain first = list.getFirst();
            if (first.getIndex() == null){
                Integer index = 1;
                for (PassengerGroupDomain passengerGroupDomain : list){
                    passengerGroupDomain.setIndex(index);
                    index = index + 1;
                    repo.edit(passengerGroupDomain);
                }
            }
            Collections.sort(list);
        }
        return list.stream().map(passengerGroupDomain ->  PassengerGroupResponse.from(passengerGroupDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PassengerGroupResponse searchByRegisterGroupAndPassengerGroup(String idRegisterGroup, String idPassengerGroup) {
        PassengerGroupDomain passengerGroupDomain = repo.searchAllByRegisterGroupAndPassengerGroup(idRegisterGroup, idPassengerGroup);
        return PassengerGroupResponse.from(passengerGroupDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerGroupDomain> searchAllByRegisterGroupDomain(String idRegisterGroup) {
        List<PassengerGroupDomain> list = repo.searchAllByRegisterGroup(idRegisterGroup);
        return list;
    }

    private Integer getLastIndexItineraryItem(String idRegisterGroup) {
        Integer index = 0;
        List<PassengerGroupDomain> list = repo.searchAllByRegisterGroupSimpleDomainCaseGetCollectionForLastIndex(idRegisterGroup).stream().collect(Collectors.toList());
        list.sort(Comparator.comparing(PassengerGroupDomain::getIndex));
        long count = list.size();
        if (count != 0){
            index = list.getLast().getIndex() + 1;
        }else{
            index = 1;
        }
        return index;
    }
}
