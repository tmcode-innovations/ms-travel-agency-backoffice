package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultCostTourServiceItemBook;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtour.CostTourBookingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtouritem.CostTourItemBookingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtourserviceitem.CostTourServiceItemBookRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.ServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtourserviceitem.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.CostTourServiceItemBookUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CostTourServiceItemBookUseCaseImpl implements CostTourServiceItemBookUseCase {

    @Autowired
    private CostTourServiceItemBookRepo repo;

    @Autowired
    private CostTourBookingRepo costTourRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private ItineraryDateRepo itineraryDateRepo;

    @Autowired
    private ItineraryItemRepo itineraryItemRepo;

    @Autowired
    private TourRepo tourRepo;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private CostTourItemBookingRepo costTourItemRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private SetFieldDefaultCostTourServiceItemBook setFieldDefault;

    @Override
    @Transactional
    public CostTourServiceItemResponse create(CostTourServiceItemCreateRequest request) {
        CostTourDomain costTourDomain = null;
        if (request.getCostTour() != null && !request.getCostTour().isEmpty()){
            IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(request.getCostTour());
            costTourDomain = new CostTourDomain();
            costTourDomain.setId(costTourIdBaseDomain.getId());
        }
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }
        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getItineraryDate());
            itineraryDateDomain = new ItineraryDateDomain();
            itineraryDateDomain.setId(itineraryDateIdBaseDomain.getId());
        }
        TourDomain tourDomain = null;
        if (request.getTour() != null && !request.getTour().isEmpty()){
            IdBaseDomain tourIdBaseDomain = tourRepo.findByIdEntity(request.getTour());
            tourDomain = new TourDomain();
            tourDomain.setId(tourIdBaseDomain.getId());
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
            supplierTypeDomain = new SupplierTypeDomain();
            supplierTypeDomain.setId(supplierTypeIdBaseDomain.getId());
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            supplierDomain = new SupplierDomain();
            supplierDomain.setId(supplierIdBaseDomain.getId());
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            serviceDomain = new ServiceDomain();
            serviceDomain.setId(serviceIdBaseDomain.getId());
        }

        CostTourServiceItemDomain newDomain = request.to(costTourDomain, registerGroupDomain, itineraryDateDomain, tourDomain, supplierTypeDomain, supplierDomain, serviceDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        CostTourServiceItemDomain createdDomain = repo.create(newDomain);
        return CostTourServiceItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CostTourServiceItemResponse create(CostTourServiceItemCreateRequest request, RegisterGroupDomain registerGroupDomain, CostTourDomain costTourDomain) {
        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getItineraryDate());
            itineraryDateDomain = new ItineraryDateDomain();
            itineraryDateDomain.setId(itineraryDateIdBaseDomain.getId());
        }
        TourDomain tourDomain = null;
        if (request.getTour() != null && !request.getTour().isEmpty()){
            IdBaseDomain tourIdBaseDomain = tourRepo.findByIdEntity(request.getTour());
            tourDomain = new TourDomain();
            tourDomain.setId(tourIdBaseDomain.getId());
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
            supplierTypeDomain = new SupplierTypeDomain();
            supplierTypeDomain.setId(supplierTypeIdBaseDomain.getId());
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            supplierDomain = new SupplierDomain();
            supplierDomain.setId(supplierIdBaseDomain.getId());
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            serviceDomain = new ServiceDomain();
            serviceDomain.setId(serviceIdBaseDomain.getId());
        }
        CostTourServiceItemDomain newDomain = request.to(costTourDomain, registerGroupDomain, itineraryDateDomain, tourDomain, supplierTypeDomain, supplierDomain, serviceDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        CostTourServiceItemDomain createdDomain = repo.create(newDomain);
        return CostTourServiceItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CostTourServiceItemResponse edit(String id, CostTourServiceItemUpdateRequest request) {
        if (id == null) {
            return null;
        }
        CostTourServiceItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        CostTourDomain costTourDomain = null;
        if (request.getCostTour() != null && !request.getCostTour().isEmpty()){
            IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(request.getCostTour());
            costTourDomain = new CostTourDomain();
            costTourDomain.setId(costTourIdBaseDomain.getId());
        }
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain quotationIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(quotationIdBaseDomain.getId());
        }
        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getItineraryDate());
            itineraryDateDomain = new ItineraryDateDomain();
            itineraryDateDomain.setId(itineraryDateIdBaseDomain.getId());
        }
        TourDomain tourDomain = null;
        if (request.getTour() != null && !request.getTour().isEmpty()){
            IdBaseDomain tourIdBaseDomain = tourRepo.findByIdEntity(request.getTour());
            tourDomain = new TourDomain();
            tourDomain.setId(tourIdBaseDomain.getId());
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
            supplierTypeDomain = new SupplierTypeDomain();
            supplierTypeDomain.setId(supplierTypeIdBaseDomain.getId());
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            supplierDomain = new SupplierDomain();
            supplierDomain.setId(supplierIdBaseDomain.getId());
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            serviceDomain = new ServiceDomain();
            serviceDomain.setId(serviceIdBaseDomain.getId());
        }

        CostTourServiceItemDomain costTourServiceItemDomain = request.to(costTourDomain, registerGroupDomain, itineraryDateDomain, tourDomain, supplierTypeDomain, supplierDomain, serviceDomain);
        CostTourServiceItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(costTourServiceItemDomain, old));
        return CostTourServiceItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public CostTourServiceItemResponse edit(String id, CostTourServiceItemUpdateRequest request, RegisterGroupDomain registerGroupDomain, CostTourDomain costTourDomain) {
        if (id == null) {
            return null;
        }
        CostTourServiceItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getItineraryDate());
            itineraryDateDomain = new ItineraryDateDomain();
            itineraryDateDomain.setId(itineraryDateIdBaseDomain.getId());
        }
        TourDomain tourDomain = null;
        if (request.getTour() != null && !request.getTour().isEmpty()){
            IdBaseDomain tourIdBaseDomain = tourRepo.findByIdEntity(request.getTour());
            tourDomain = new TourDomain();
            tourDomain.setId(tourIdBaseDomain.getId());
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
            supplierTypeDomain = new SupplierTypeDomain();
            supplierTypeDomain.setId(supplierTypeIdBaseDomain.getId());
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            supplierDomain = new SupplierDomain();
            supplierDomain.setId(supplierIdBaseDomain.getId());
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            serviceDomain = new ServiceDomain();
            serviceDomain.setId(serviceIdBaseDomain.getId());
        }
        CostTourServiceItemDomain costTourServiceItemDomain = request.to(costTourDomain, registerGroupDomain, itineraryDateDomain, tourDomain, supplierTypeDomain, supplierDomain, serviceDomain);
        CostTourServiceItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(costTourServiceItemDomain, old));
        ItineraryItemDomain itineraryItemDomain = itineraryItemRepo.searchAllByRegisterGroupAndCostTour(registerGroupDomain.getId(), costTourDomain.getId());
        // Update Itinerary Item without additional service
        if (itineraryItemDomain != null && itineraryItemDomain.getCostTourServiceItem() == null){
            itineraryItemDomain.setCostTourServiceItem(editedDomain);
            itineraryItemRepo.edit(itineraryItemDomain);
        }
        return CostTourServiceItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        CostTourServiceItemResponse old = findById(id);
        repo.deleteById(old.getId());

        CostTourItemDomain costTourItemDomain = costTourItemRepo.searchAllByRegisterGroupAndCostTourServiceItem(old.getRegisterGroup().getId(), id);
        if (costTourItemDomain != null){
            costTourItemRepo.deleteById(costTourItemDomain.getId());
        }

        ItineraryItemDomain itineraryItemDomain = itineraryItemRepo.searchAllByRegisterGroupAndCostTourAndCostTourServiceItem(old.getRegisterGroup().getId(), old.getCostTour().getId(), old.getId());
        if (itineraryItemDomain != null ){
            itineraryItemRepo.deleteById(itineraryItemDomain.getId());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostTourServiceItemResponse> findAll() {
        return repo.findAll(false).stream().map(costTourServiceItemDomain -> CostTourServiceItemResponse.from(costTourServiceItemDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourServiceItemResponse findById(String id) {
        if (id == null) {
            return null;
        }
        CostTourServiceItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No cost tour service item found with id '%s'.".formatted(id));
        }
        return CostTourServiceItemResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourServiceItemSearchResponse searchAll(CostTourServiceItemSearchRequest costTourServiceItemSearchRequest) {
       PageDTO<CostTourServiceItemDomain> pageDTO = repo.searchAll(costTourServiceItemSearchRequest);
       CostTourServiceItemSearchResponse costTourServiceItemSearchRespose = new CostTourServiceItemSearchResponse();
       costTourServiceItemSearchRespose.setCostTourServiceItems(pageDTO.getContent().stream().map(costTourServiceItemDomain ->  CostTourServiceItemResponse.from(costTourServiceItemDomain)).toList());
       costTourServiceItemSearchRespose.setTotalElements(pageDTO.getTotalElements());
       costTourServiceItemSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return costTourServiceItemSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourServiceItemSearchResponse searchFilterAll(CostTourServiceItemSearchFilterRequest costTourServiceItemSearchFilterRequest) {
        PageDTO<CostTourServiceItemDomain> pageDTO = repo.searchAllFilter(costTourServiceItemSearchFilterRequest);
        CostTourServiceItemSearchResponse costTourServiceItemSearchRespose = new CostTourServiceItemSearchResponse();
        costTourServiceItemSearchRespose.setCostTourServiceItems(pageDTO.getContent().stream().map(costTourServiceItemDomain ->  CostTourServiceItemResponse.from(costTourServiceItemDomain)).toList());
        costTourServiceItemSearchRespose.setTotalElements(pageDTO.getTotalElements());
        costTourServiceItemSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return costTourServiceItemSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostTourServiceItemResponse> searchAllByRegisterGroup(String idRegisterGroup) {
        List<CostTourServiceItemDomain> list = repo.searchAllByRegisterGroup(idRegisterGroup);
        return list.stream().map(costTourServiceItemDomain ->  CostTourServiceItemResponse.from(costTourServiceItemDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourServiceItemResponse searchByRegisterGroupAndCostTourServiceItem(String idRegisterGroup, String idCostTourServiceItem) {
        CostTourServiceItemDomain costTourServiceItemDomain = repo.searchAllByRegisterGroupAndCostTourServiceItem(idRegisterGroup, idCostTourServiceItem);
        return CostTourServiceItemResponse.from(costTourServiceItemDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourServiceItemResponse searchByRegisterGroupAndCostTourAndCostTourServiceItem(String idRegisterGroup, String idCostTour, String idCostTourServiceItem) {
        CostTourServiceItemDomain costTourServiceItemDomain = repo.searchAllByRegisterGroupAndCostTourAndCostTourServiceItem(idRegisterGroup, idCostTour, idCostTourServiceItem);
        return CostTourServiceItemResponse.from(costTourServiceItemDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostTourServiceItemDomain> searchAllDomainByRegisterGroup(String idRegisterGroup) {
        List<CostTourServiceItemDomain> list = repo.searchAllByRegisterGroup(idRegisterGroup);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostTourServiceItemDomain> searchAllDomainByRegisterGroupAndCostTour(String idCostTour, String idRegisterGroup) {
        List<CostTourServiceItemDomain> list = repo.searchAllByCostTourAndRegisterGroup(idCostTour, idRegisterGroup);
        return list;
    }

}
