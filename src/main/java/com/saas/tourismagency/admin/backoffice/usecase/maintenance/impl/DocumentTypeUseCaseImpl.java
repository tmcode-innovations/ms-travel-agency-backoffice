package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documenttype.DocumentTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultDocumentType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documenttype.DocumentTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documenttype.PersistDocTypeNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.DocumentTypeUseCase;
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
public class DocumentTypeUseCaseImpl implements DocumentTypeUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private DocumentTypeRepo repo;

    @Autowired
    private DocumentTypeConverter converter;

    @Autowired
    private SetFieldDefaultDocumentType setFieldDefault;

    @Autowired
    PersistDocTypeNumberCallbackImpl persistDocTypeNumberCallback;

    @Override
    @Transactional
    public DocumentTypeResponse create(DocumentTypeCreateRequest request) {
        DocumentTypeDomain documentTypeDomainExist = repo.findByName(request.getName());
        if (documentTypeDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        DocumentTypeDomain newDomain = request.to();
        newDomain.setIdentify(persistDocTypeNumberCallback.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        DocumentTypeDomain createdDomain = repo.create(newDomain);
        return DocumentTypeResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public DocumentTypeResponse edit(String id, DocumentTypeUpdateRequest request) {
        if (id == null) {
            return null;
        }
        DocumentTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No document type found with id '%s'.".formatted(id));
        }
        DocumentTypeDomain documentTypeDomainExist = repo.findByName(request.getName());
        if (documentTypeDomainExist != null && !documentTypeDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        DocumentTypeDomain documentTypeDomain = request.to();
        documentTypeDomain.setIdentify(old.getIdentify());
        DocumentTypeDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(documentTypeDomain, old));
        return DocumentTypeResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        DocumentTypeResponse old = findById(id);
        if (referenceGuard.isReferenced("DocumentType", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentTypeResponse> findAll() {
        return repo.findAll(false).stream().map(documentTypeDomain -> DocumentTypeResponse.from(documentTypeDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentTypeResponse findById(String id) {
        if (id == null) {
            return null;
        }
        DocumentTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No document type found with id '%s'.".formatted(id));
        }
        return DocumentTypeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentTypeSearchResponse searchAll(DocumentTypeSearchRequest documentTypeSearchRequest) {
       PageDTO<DocumentTypeDomain> pageDTO = repo.searchAll(documentTypeSearchRequest);
       DocumentTypeSearchResponse documentTypeSearchRespose = new DocumentTypeSearchResponse();
       documentTypeSearchRespose.setDocumentTypes(pageDTO.getContent().stream().map(documentTypeDomain ->  DocumentTypeResponse.from(documentTypeDomain)).toList());
       documentTypeSearchRespose.setTotalElements(pageDTO.getTotalElements());
       documentTypeSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return documentTypeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentTypeSearchResponse searchFilterAll(DocumentTypeSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<DocumentTypeDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        DocumentTypeSearchResponse documentTypeSearchRespose = new DocumentTypeSearchResponse();
        documentTypeSearchRespose.setDocumentTypes(pageDTO.getContent().stream().map(documentTypeDomain ->  DocumentTypeResponse.from(documentTypeDomain)).toList());
        documentTypeSearchRespose.setTotalElements(pageDTO.getTotalElements());
        documentTypeSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return documentTypeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentTypeResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        DocumentTypeDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No document type found with name '%s'.".formatted(name));
        }
        return DocumentTypeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentTypeResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        DocumentTypeDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No document type found with identify '%s'.".formatted(identify));
        }
        return DocumentTypeResponse.from(old);
    }
}
