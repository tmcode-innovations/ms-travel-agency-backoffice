package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.quotation.FileQuotationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultFileQuotation;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.FileQuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.QuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.filequotation.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.crm.FileQuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.usecase.crm.FileQuotationUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
public class FileQuotationUseCaseImpl implements FileQuotationUseCase {

    @Autowired
    private FileQuotationRepo repo;

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private FileQuotationConverter converter;

    @Autowired
    private SetFieldDefaultFileQuotation setFieldDefault;

    @Override
    @Transactional
    public FileQuotationResponse create(FileQuotationCreateRequest request) {
        FileQuotationDomain fileQuotationDomainExist = repo.findByName(request.getName());
        if (fileQuotationDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        QuotationDomain quotationDomain = null;
        if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
            quotationDomain = quotationRepo.findById(request.getQuotation());
        }
        FileQuotationDomain newDomain = request.to(quotationDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        FileQuotationDomain createdDomain = repo.create(newDomain);
        return FileQuotationResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public FileQuotationResponse edit(String id, FileQuotationUpdateRequest request) {
        if (id == null) {
            return null;
        }
        FileQuotationDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        FileQuotationDomain fileQuotationDomainExist = repo.findByName(request.getName());
        if (fileQuotationDomainExist != null && !fileQuotationDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        QuotationDomain quotationDomain = null;
        if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
            quotationDomain = quotationRepo.findById(request.getQuotation());
        }

        FileQuotationDomain fileQuotationDomain = request.to(quotationDomain);
        FileQuotationDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(fileQuotationDomain, old));
        return FileQuotationResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        FileQuotationResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileQuotationResponse> findAll() {
        return repo.findAll(false).stream().map(fileQuotationDomain -> FileQuotationResponse.from(fileQuotationDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public FileQuotationResponse findById(String id) {
        if (id == null) {
            return null;
        }
        FileQuotationDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No file quotation found with id '%s'.".formatted(id));
        }
        return FileQuotationResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public FileQuotationSearchResponse searchAll(FileQuotationSearchRequest searchRequest) {
       PageDTO<FileQuotationDomain> pageDTO = repo.searchAll(searchRequest);
       FileQuotationSearchResponse searchRespose = new FileQuotationSearchResponse();
       searchRespose.setFileQuotations(pageDTO.getContent().stream().map(fileQuotationDomain ->  FileQuotationResponse.from(fileQuotationDomain)).toList());
       searchRespose.setTotalElements(pageDTO.getTotalElements());
       searchRespose.setTotalPages(pageDTO.getTotalPages());
       return searchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public FileQuotationSearchResponse searchFilterAll(FileQuotationSearchFilterRequest searchFilterRequest) {
        PageDTO<FileQuotationDomain> pageDTO = repo.searchAllFilter(searchFilterRequest);
        FileQuotationSearchResponse searchRespose = new FileQuotationSearchResponse();
        searchRespose.setFileQuotations(pageDTO.getContent().stream().map(fileQuotationDomain ->  FileQuotationResponse.from(fileQuotationDomain)).toList());
        searchRespose.setTotalElements(pageDTO.getTotalElements());
        searchRespose.setTotalPages(pageDTO.getTotalPages());
        return searchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public FileQuotationResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        FileQuotationDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No file quotation found with name '%s'.".formatted(name));
        }
        return FileQuotationResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public FileQuotationResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        FileQuotationDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No file quotation found with identify '%s'.".formatted(identify));
        }
        return FileQuotationResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileQuotationResponse> searchAllByQuotation(String idQuotation) {
        List<FileQuotationDomain> list = repo.searchAllByQuotation(idQuotation);
        return list.stream().map(fileQuotationDomain ->  FileQuotationResponse.from(fileQuotationDomain)).toList();
    }

    @Override
    @Transactional
    public ByteArrayResource downloadDocument(String id) {
        if (id == null) {
            return null;
        }
        FileQuotationDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        ByteArrayResource byteArrayResource = null;
        String dataUrlFile = old.getPath();
        Path file = Paths.get(dataUrlFile);
        if ( Files.exists(file) ){
            try {
                byte[] byteArray = Files.readAllBytes(file);
                byteArrayResource = new ByteArrayResource(byteArray);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayResource;
    }
}
