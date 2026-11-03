package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.quotation.FileItineraryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultFileItinerary;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.FileItineraryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.QuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.fileitinerary.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.crm.FileItineraryDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.usecase.crm.FileItineraryUseCase;
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
public class FileItineraryUseCaseImpl implements FileItineraryUseCase {

    @Autowired
    private FileItineraryRepo repo;

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private FileItineraryConverter converter;

    @Autowired
    private SetFieldDefaultFileItinerary setFieldDefault;

    @Override
    @Transactional
    public FileItineraryResponse create(FileItineraryCreateRequest request) {
        FileItineraryDomain fileItineraryDomainExist = repo.findByName(request.getName());
        if (fileItineraryDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        QuotationDomain quotationDomain = null;
        if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
            quotationDomain = quotationRepo.findById(request.getQuotation());
        }
        FileItineraryDomain newDomain = request.to(quotationDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        FileItineraryDomain createdDomain = repo.create(newDomain);
        return FileItineraryResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public FileItineraryResponse edit(String id, FileItineraryUpdateRequest request) {
        if (id == null) {
            return null;
        }
        FileItineraryDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        FileItineraryDomain fileItineraryDomainExist = repo.findByName(request.getName());
        if (fileItineraryDomainExist != null && !fileItineraryDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        QuotationDomain quotationDomain = null;
        if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
            quotationDomain = quotationRepo.findById(request.getQuotation());
        }

        FileItineraryDomain fileItineraryDomain = request.to(quotationDomain);
        FileItineraryDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(fileItineraryDomain, old));
        return FileItineraryResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        FileItineraryResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileItineraryResponse> findAll() {
        return repo.findAll(false).stream().map(fileItineraryDomain -> FileItineraryResponse.from(fileItineraryDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public FileItineraryResponse findById(String id) {
        if (id == null) {
            return null;
        }
        FileItineraryDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No file itinerary found with id '%s'.".formatted(id));
        }
        return FileItineraryResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public FileItinerarySearchResponse searchAll(FileItinerarySearchRequest searchRequest) {
       PageDTO<FileItineraryDomain> pageDTO = repo.searchAll(searchRequest);
       FileItinerarySearchResponse searchRespose = new FileItinerarySearchResponse();
       searchRespose.setFileItineraries(pageDTO.getContent().stream().map(fileItineraryDomain ->  FileItineraryResponse.from(fileItineraryDomain)).toList());
       searchRespose.setTotalElements(pageDTO.getTotalElements());
       searchRespose.setTotalPages(pageDTO.getTotalPages());
       return searchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public FileItinerarySearchResponse searchFilterAll(FileItinerarySearchFilterRequest searchFilterRequest) {
        PageDTO<FileItineraryDomain> pageDTO = repo.searchAllFilter(searchFilterRequest);
        FileItinerarySearchResponse searchRespose = new FileItinerarySearchResponse();
        searchRespose.setFileItineraries(pageDTO.getContent().stream().map(fileItineraryDomain ->  FileItineraryResponse.from(fileItineraryDomain)).toList());
        searchRespose.setTotalElements(pageDTO.getTotalElements());
        searchRespose.setTotalPages(pageDTO.getTotalPages());
        return searchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public FileItineraryResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        FileItineraryDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No file itinerary found with name '%s'.".formatted(name));
        }
        return FileItineraryResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public FileItineraryResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        FileItineraryDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No file itinerary found with identify '%s'.".formatted(identify));
        }
        return FileItineraryResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileItineraryResponse> searchAllByQuotation(String idQuotation) {
        List<FileItineraryDomain> list = repo.searchAllByQuotation(idQuotation);
        return list.stream().map(fileItineraryDomain ->  FileItineraryResponse.from(fileItineraryDomain)).toList();
    }

    @Override
    @Transactional
    public ByteArrayResource downloadDocument(String id) {
        if (id == null) {
            return null;
        }
        FileItineraryDomain old = repo.findById(id);
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
