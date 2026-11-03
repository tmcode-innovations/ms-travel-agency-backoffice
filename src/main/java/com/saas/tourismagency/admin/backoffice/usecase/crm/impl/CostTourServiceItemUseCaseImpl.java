package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultCostTourServiceItem;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtour.CostTourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtouritem.CostTourItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtourserviceitem.CostTourServiceItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itinerarydate.ItineraryDateCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itineraryitem.ItineraryItemCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.QuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.ServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtourserviceitem.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.crm.CostTourServiceItemUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CostTourServiceItemUseCaseImpl implements CostTourServiceItemUseCase {

    @Autowired
    private CostTourServiceItemRepo repo;

    @Autowired
    private CostTourRepo costTourRepo;

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private ItineraryDateCrmRepo itineraryDateRepo;

    @Autowired
    private ItineraryItemCrmRepo itineraryItemRepo;

    @Autowired
    private TourRepo tourRepo;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private CostTourItemRepo costTourItemRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private SetFieldDefaultCostTourServiceItem setFieldDefault;

    @Override
    @Transactional
    public CostTourServiceItemResponse create(CostTourServiceItemCreateRequest request) {
        CostTourDomain costTourDomain = null;
        if (request.getCostTour() != null && !request.getCostTour().isEmpty()){
            IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(request.getCostTour());
            costTourDomain = new CostTourDomain();
            costTourDomain.setId(costTourIdBaseDomain.getId());
        }
        QuotationDomain quotationDomain = null;
        if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
            IdBaseDomain quotationIdBaseDomain = quotationRepo.findByIdEntity(request.getQuotation());
            quotationDomain = new QuotationDomain();
            quotationDomain.setId(quotationIdBaseDomain.getId());
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

        CostTourServiceItemDomain newDomain = request.to(costTourDomain, quotationDomain, itineraryDateDomain, tourDomain, supplierTypeDomain, supplierDomain, serviceDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        CostTourServiceItemDomain createdDomain = repo.create(newDomain);
        return CostTourServiceItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CostTourServiceItemResponse create(CostTourServiceItemCreateRequest request, QuotationDomain quotationDomain, CostTourDomain costTourDomain) {
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
        CostTourServiceItemDomain newDomain = request.to(costTourDomain, quotationDomain, itineraryDateDomain, tourDomain, supplierTypeDomain, supplierDomain, serviceDomain);
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
        QuotationDomain quotationDomain = null;
        if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
            IdBaseDomain quotationIdBaseDomain = quotationRepo.findByIdEntity(request.getQuotation());
            quotationDomain = new QuotationDomain();
            quotationDomain.setId(quotationIdBaseDomain.getId());
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

        CostTourServiceItemDomain costTourServiceItemDomain = request.to(costTourDomain, quotationDomain, itineraryDateDomain, tourDomain, supplierTypeDomain, supplierDomain, serviceDomain);
        CostTourServiceItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(costTourServiceItemDomain, old));
        return CostTourServiceItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public CostTourServiceItemResponse edit(String id, CostTourServiceItemUpdateRequest request, QuotationDomain quotationDomain, CostTourDomain costTourDomain) {
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
        CostTourServiceItemDomain costTourServiceItemDomain = request.to(costTourDomain, quotationDomain, itineraryDateDomain, tourDomain, supplierTypeDomain, supplierDomain, serviceDomain);
        CostTourServiceItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(costTourServiceItemDomain, old));
        /*ItineraryItemDomain itineraryItemDomain = itineraryItemRepo.searchAllByQuotationAndCostTour(quotationDomain.getId(), costTourDomain.getId());
        // Update Itinerary Item without additional service
        if (itineraryItemDomain != null && itineraryItemDomain.getCostTourServiceItem() == null){
            itineraryItemDomain.setCostTourServiceItem(editedDomain);
            itineraryItemRepo.edit(itineraryItemDomain);
        }*/
        ItineraryItemDomain itineraryItemDomain = itineraryItemRepo.searchAllByQuotationAndCostTourAndCostTourServiceItem(quotationDomain.getId(), costTourDomain.getId(), editedDomain.getId());
        if (itineraryItemDomain != null){
            itineraryItemDomain.setItineraryDate(itineraryDateDomain);
            itineraryItemDomain.setSupplierType(supplierTypeDomain);
            itineraryItemDomain.setSupplier(supplierDomain);
            itineraryItemDomain.setService(serviceDomain);
            itineraryItemDomain.setObservation(editedDomain.getObservation());
            itineraryItemRepo.edit(itineraryItemDomain);
        }
        return CostTourServiceItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        CostTourServiceItemResponse old = findById(id);
        repo.deleteById(old.getId());

        CostTourItemDomain costTourItemDomain = costTourItemRepo.searchAllByQuotationAndCostTourServiceItem(old.getQuotation().getId(), id);
        if (costTourItemDomain != null){
            costTourItemRepo.deleteById(costTourItemDomain.getId());
        }

        ItineraryItemDomain itineraryItemDomain = itineraryItemRepo.searchAllByQuotationAndCostTourAndCostTourServiceItem(old.getQuotation().getId(), old.getCostTour().getId(), old.getId());
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
    public List<CostTourServiceItemResponse> searchAllByQuotation(String idQuotation) {
        List<CostTourServiceItemDomain> list = repo.searchAllByQuotation(idQuotation);
        return list.stream().map(costTourServiceItemDomain ->  CostTourServiceItemResponse.from(costTourServiceItemDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourServiceItemResponse searchByQuotationAndCostTourServiceItem(String idQuotation, String idCostTourServiceItem) {
        CostTourServiceItemDomain costTourServiceItemDomain = repo.searchAllByQuotationAndCostTourServiceItem(idQuotation, idCostTourServiceItem);
        return CostTourServiceItemResponse.from(costTourServiceItemDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourServiceItemResponse searchByQuotationAndCostTourAndCostTourServiceItem(String idQuotation, String idCostTour, String idCostTourServiceItem) {
        CostTourServiceItemDomain costTourServiceItemDomain = repo.searchAllByQuotationAndCostTourAndCostTourServiceItem(idQuotation, idCostTour, idCostTourServiceItem);
        return CostTourServiceItemResponse.from(costTourServiceItemDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostTourServiceItemDomain> searchAllDomainByQuotation(String idQuotation) {
        List<CostTourServiceItemDomain> list = repo.searchAllByQuotation(idQuotation);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostTourServiceItemDomain> searchAllDomainByQuotationAndCostTour(String idCostTour, String idQuotation) {
        List<CostTourServiceItemDomain> list = repo.searchAllByCostTourAndQuotation(idCostTour, idQuotation);
        return list;
    }

}
