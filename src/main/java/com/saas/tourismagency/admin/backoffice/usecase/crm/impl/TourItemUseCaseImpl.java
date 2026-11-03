package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tourdate.TourDateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultTourItem;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tourdate.TourDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.touritem.TourItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.ServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.touitem.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.ServiceGroupEnum;
import com.saas.tourismagency.admin.backoffice.usecase.crm.TourItemUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class TourItemUseCaseImpl implements TourItemUseCase {

    @Autowired
    private TourItemRepo repo;

    @Autowired
    private TourDateRepo tourDateRepo;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private TourRepo tourRepo;

    @Autowired
    private TourDateConverter converter;

    @Autowired
    private SetFieldDefaultTourItem setFieldDefault;


    @Override
    @Transactional
    public TourItemResponse create(TourItemCreateRequest request) {
        TourDomain tourDomain = null;
        if (request.getTour() != null && !request.getTour().isEmpty()){
            tourDomain = tourRepo.findById(request.getTour());
        }
        TourDateDomain tourDateDomain = null;
        if (request.getTourDate() != null && !request.getTourDate().isEmpty()){
            tourDateDomain = tourDateRepo.findById(request.getTourDate());
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierRepo.findById(request.getSupplier());
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }
        TourItemDomain newDomain = request.to(tourDomain, tourDateDomain, supplierTypeDomain, supplierDomain, serviceDomain);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
            newDomain.setServiceGroup(ServiceGroupEnum.TRADITIONAL_GROUP.getName());
        }
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        TourItemDomain createdDomain = repo.create(newDomain);
        return TourItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public TourItemResponse create(TourItemCreateRequest request, TourDomain tourDomain) {
        TourDateDomain tourDateDomain = null;
        if (request.getTourDate() != null && !request.getTourDate().isEmpty()){
            tourDateDomain = tourDateRepo.findById(request.getTourDate());
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierRepo.findById(request.getSupplier());
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }
        TourItemDomain newDomain = request.to(tourDomain, tourDateDomain, supplierTypeDomain, supplierDomain, serviceDomain);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
            newDomain.setServiceGroup(ServiceGroupEnum.TRADITIONAL_GROUP.getName());
        }
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIndex(this.getLastIndexTourItem(tourDomain.getId()));
        TourItemDomain createdDomain = repo.create(newDomain);
        return TourItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public List<TourItemResponse> createMultiple(TourItemCreateRequest request, TourDomain tourDomain) {
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        List<TourItemResponse> result = new ArrayList<>();
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierRepo.findById(request.getSupplier());
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        if (request.getTourDates() != null && !request.getTourDates().isEmpty()){
            for(String item: request.getTourDates()){
                TourDateDomain tourDateDomain = null;
                tourDateDomain = tourDateRepo.findById(item);
                TourItemDomain newDomain = request.to(tourDomain, tourDateDomain, supplierTypeDomain, supplierDomain, serviceDomain);
                if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
                    newDomain.setServiceGroup(ServiceGroupEnum.TRADITIONAL_GROUP.getName());
                }
                newDomain.setTenant(UserResolver.resolveUser().getTenant());
                newDomain.setIndex(this.getLastIndexTourItem(tourDomain.getId()));
                TourItemDomain createdDomain = repo.create(newDomain);
                result.add(TourItemResponse.from(createdDomain));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public TourItemResponse edit(String id, TourItemUpdateRequest request) {
        if (id == null) {
            return null;
        }
        TourItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        TourDomain tourDomain = null;
        if (request.getTour() != null && !request.getTour().isEmpty()){
            tourDomain = tourRepo.findById(request.getTour());
        }
        TourDateDomain tourDateDomain = null;
        if (request.getTourDate() != null && !request.getTourDate().isEmpty()){
            tourDateDomain = tourDateRepo.findById(request.getTourDate());
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierRepo.findById(request.getSupplier());
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }
        TourItemDomain tourItemDomain = request.to(tourDomain, tourDateDomain, supplierTypeDomain, supplierDomain, serviceDomain);
        if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
            tourItemDomain.setServiceGroup(ServiceGroupEnum.TRADITIONAL_GROUP.getName());
        }
        TourItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(tourItemDomain, old));
        return TourItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public TourItemResponse edit(String id, TourItemUpdateRequest request, TourDomain tourDomain) {
        if (id == null) {
            return null;
        }
        TourItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        TourDateDomain tourDateDomain = null;
        if (request.getTourDate() != null && !request.getTourDate().isEmpty()){
            tourDateDomain = tourDateRepo.findById(request.getTourDate());
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierRepo.findById(request.getSupplier());
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }
        TourItemDomain tourItemDomain = request.to(tourDomain, tourDateDomain, supplierTypeDomain, supplierDomain, serviceDomain);
        tourItemDomain.setIndex(old.getIndex());
        if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
            tourItemDomain.setServiceGroup(ServiceGroupEnum.TRADITIONAL_GROUP.getName());
        }
        TourItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(tourItemDomain, old));
        return TourItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        TourItemResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourItemResponse> findAll() {
        return repo.findAll(false).stream().map(tourItemDomain -> TourItemResponse.from(tourItemDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TourItemResponse findById(String id) {
        if (id == null) {
            return null;
        }
        TourItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No tour item found with id '%s'.".formatted(id));
        }
        return TourItemResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public TourItemSearchResponse searchAll(TourItemSearchRequest tourItemSearchRequest) {
       PageDTO<TourItemDomain> pageDTO = repo.searchAll(tourItemSearchRequest);
       TourItemSearchResponse tourItemSearchRespose = new TourItemSearchResponse();
       tourItemSearchRespose.setTourItems(pageDTO.getContent().stream().map(tourItemDomain ->  TourItemResponse.from(tourItemDomain)).toList());
       tourItemSearchRespose.setTotalElements(pageDTO.getTotalElements());
       tourItemSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return tourItemSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public TourItemSearchResponse searchFilterAll(TourItemSearchFilterRequest tourItemSearchFilterRequest) {
        PageDTO<TourItemDomain> pageDTO = repo.searchAllFilter(tourItemSearchFilterRequest);
        TourItemSearchResponse tourItemSearchRespose = new TourItemSearchResponse();
        tourItemSearchRespose.setTourItems(pageDTO.getContent().stream().map(tourItemDomain ->  TourItemResponse.from(tourItemDomain)).toList());
        tourItemSearchRespose.setTotalElements(pageDTO.getTotalElements());
        tourItemSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return tourItemSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourItemResponse> searchAllByTour(String idTour) {
        List<TourItemDomain> list = repo.searchAllByTour(idTour);
        return list.stream().map(tourItemDomain ->  TourItemResponse.from(tourItemDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TourItemResponse searchByTourAndTourItem(String idTour, String idTourItem) {
        TourItemDomain tourItemDomain = repo.searchAllByTourAndTourItem(idTour, idTourItem);
        return TourItemResponse.from(tourItemDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourItemDomain> searchAllDomainByTour(String idTour) {
        List<TourItemDomain> list = repo.searchAllByTour(idTour);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourItemDomain> searchAllDomainByTourAndTourDate(String idTourDate, String idTour) {
        List<TourItemDomain> list = repo.searchAllByTourDateAndTour(idTourDate, idTour);
        return list;
    }

    private Integer getLastIndexTourItem(String idTour) {
        Integer index = 0;
        List<TourItemDomain> list = repo.searchAllByTour(idTour).stream().collect(Collectors.toList());
        Collections.sort(list);
        long count = list.stream().count();
        if (count != 0){
            Stream<TourItemDomain> stream = list.stream();
            TourItemDomain tourItemLast = stream.skip(count - 1).findFirst().get();
            index = tourItemLast.getIndex() + 1;
        }else{
            index = 1;
        }
        return index;
    }
}
