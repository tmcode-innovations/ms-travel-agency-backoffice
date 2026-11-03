package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documentaccounttype.DocumentAccountTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultDocumentAccountType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documentaccounttype.DocumentAccountTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documentaccounttype.PersistDocAccTypeNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documentaccounttype.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.DocumentAccountTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentAccountTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.DocumentAccountTypeUseCase;
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
public class DocumentAccountTypeUseCaseImpl implements DocumentAccountTypeUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private DocumentAccountTypeRepo repo;

    @Autowired
    private DocumentAccountTypeConverter converter;

    @Autowired
    private SetFieldDefaultDocumentAccountType setFieldDefault;

    @Autowired
    PersistDocAccTypeNumberCallbackImpl persistDocAccTypeNumberCallback;

    @Override
    @Transactional
    public DocAccountTypeResponse create(DocAccountTypeCreateRequest request) {
        DocumentAccountTypeDomain documentAccountTypeDomainExist = repo.findByName(request.getName());
        if (documentAccountTypeDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        DocumentAccountTypeDomain newDomain = request.to();
        newDomain.setIdentify(persistDocAccTypeNumberCallback.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setDocumentAccountType(DocumentAccountTypeEnum.OTHER.getName());
        DocumentAccountTypeDomain createdDomain = repo.create(newDomain);
        return DocAccountTypeResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public DocAccountTypeResponse edit(String id, DocAccountTypeUpdateRequest request) {
        if (id == null) {
            return null;
        }
        DocumentAccountTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No bank found with id '%s'.".formatted(id));
        }
        DocumentAccountTypeDomain documentAccountTypeDomainExist = repo.findByName(request.getName());
        if (documentAccountTypeDomainExist != null && !documentAccountTypeDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        DocumentAccountTypeDomain documentAccountTypeDomain = request.to();
        documentAccountTypeDomain.setIdentify(old.getIdentify());
        documentAccountTypeDomain.setDocumentAccountType(old.getDocumentAccountType());
        DocumentAccountTypeDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(documentAccountTypeDomain, old));
        return DocAccountTypeResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        DocAccountTypeResponse old = findById(id);
        if (referenceGuard.isReferenced("DocumentAccountType", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocAccountTypeResponse> findAll() {
        return repo.findAll(false).stream().map(documentAccountTypeDomain -> DocAccountTypeResponse.from(documentAccountTypeDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DocAccountTypeResponse findById(String id) {
        if (id == null) {
            return null;
        }
        DocumentAccountTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No document account type found with id '%s'.".formatted(id));
        }
        return DocAccountTypeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public DocAccountTypeSearchResponse searchAll(DocAccountTypeSearchRequest docAccountTypeSearchRequest) {
       PageDTO<DocumentAccountTypeDomain> pageDTO = repo.searchAll(docAccountTypeSearchRequest);
       DocAccountTypeSearchResponse docAccountTypeSearchRespose = new DocAccountTypeSearchResponse();
       docAccountTypeSearchRespose.setDocAccountTypes(pageDTO.getContent().stream().map(documentAccountTypeDomain ->  DocAccountTypeResponse.from(documentAccountTypeDomain)).toList());
       docAccountTypeSearchRespose.setTotalElements(pageDTO.getTotalElements());
       docAccountTypeSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return docAccountTypeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public DocAccountTypeSearchResponse searchFilterAll(DocAccountTypeSearchFilterRequest docAccountTypeSearchFilterRequest) {
        PageDTO<DocumentAccountTypeDomain> pageDTO = repo.searchAllFilter(docAccountTypeSearchFilterRequest);
        DocAccountTypeSearchResponse docAccountTypeSearchRespose = new DocAccountTypeSearchResponse();
        docAccountTypeSearchRespose.setDocAccountTypes(pageDTO.getContent().stream().map(documentAccountTypeDomain ->  DocAccountTypeResponse.from(documentAccountTypeDomain)).toList());
        docAccountTypeSearchRespose.setTotalElements(pageDTO.getTotalElements());
        docAccountTypeSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return docAccountTypeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public DocAccountTypeResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        DocumentAccountTypeDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No document account type found with name '%s'.".formatted(name));
        }
        return DocAccountTypeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public DocAccountTypeResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        DocumentAccountTypeDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No document account type found with identify '%s'.".formatted(identify));
        }
        return DocAccountTypeResponse.from(old);
    }
}
