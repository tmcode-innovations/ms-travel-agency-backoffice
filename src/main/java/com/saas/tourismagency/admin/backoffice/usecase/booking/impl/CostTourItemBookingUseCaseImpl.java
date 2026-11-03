package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultBookingCostTourItem;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtour.CostTourBookingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtouritem.CostTourItemBookingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtourserviceitem.CostTourServiceItemBookRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.passengertype.PassengerTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtouritem.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.CostTourItemBookingUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CostTourItemBookingUseCaseImpl implements CostTourItemBookingUseCase {

    @Autowired
    private CostTourItemBookingRepo repo;

    @Autowired
    private CostTourBookingRepo costTourRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private ItineraryDateRepo itineraryDateRepo;

    @Autowired
    private TourRepo tourRepo;

    @Autowired
    private PassengerTypeRepo passengerTypeRepo;

    @Autowired
    private CostTourServiceItemBookRepo costTourServiceItemBookingRepo;

    @Autowired
    private SetFieldDefaultBookingCostTourItem setFieldDefault;

    @Override
    @Transactional
    public CostTourItemResponse create(CostTourItemCreateRequest request) {
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
        PassengerTypeDomain passengerTypeDomain = null;
        if (request.getPassengerType() != null && !request.getPassengerType().isEmpty()){
            IdBaseDomain passengerTypeIdBaseDomain = passengerTypeRepo.findByIdEntity(request.getPassengerType());
            passengerTypeDomain = new PassengerTypeDomain();
            passengerTypeDomain.setId(passengerTypeIdBaseDomain.getId());
        }
        CostTourServiceItemDomain costTourServiceItemDomain = null;
        if (request.getCostTourServiceItem() != null && !request.getCostTourServiceItem().isEmpty()){
            IdBaseDomain costTourServiceItemIdBaseDomain = costTourServiceItemBookingRepo.findByIdEntity(request.getCostTourServiceItem());
            costTourServiceItemDomain = new CostTourServiceItemDomain();
            costTourServiceItemDomain.setId(costTourServiceItemIdBaseDomain.getId());
        }

        CostTourItemDomain newDomain = request.to(costTourDomain, registerGroupDomain, itineraryDateDomain, tourDomain, passengerTypeDomain, costTourServiceItemDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        CostTourItemDomain createdDomain = repo.create(newDomain);
        return CostTourItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CostTourItemResponse create(CostTourItemCreateRequest request, RegisterGroupDomain registerGroupDomain, CostTourDomain costTourDomain) {
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
        PassengerTypeDomain passengerTypeDomain = null;
        if (request.getPassengerType() != null && !request.getPassengerType().isEmpty()){
            IdBaseDomain passengerTypeIdBaseDomain = passengerTypeRepo.findByIdEntity(request.getPassengerType());
            passengerTypeDomain = new PassengerTypeDomain();
            passengerTypeDomain.setId(passengerTypeIdBaseDomain.getId());
        }
        CostTourServiceItemDomain costTourServiceItemDomain = null;
        if (request.getCostTourServiceItem() != null && !request.getCostTourServiceItem().isEmpty()){
            IdBaseDomain costTourServiceItemIdBaseDomain = costTourServiceItemBookingRepo.findByIdEntity(request.getCostTourServiceItem());
            costTourServiceItemDomain = new CostTourServiceItemDomain();
            costTourServiceItemDomain.setId(costTourServiceItemIdBaseDomain.getId());
        }
        CostTourItemDomain newDomain = request.to(costTourDomain, registerGroupDomain, itineraryDateDomain, tourDomain, passengerTypeDomain, costTourServiceItemDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        CostTourItemDomain createdDomain = repo.create(newDomain);
        return CostTourItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CostTourItemResponse edit(String id, CostTourItemUpdateRequest request) {
        if (id == null) {
            return null;
        }
        CostTourItemDomain old = repo.findById(id);
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
        PassengerTypeDomain passengerTypeDomain = null;
        if (request.getPassengerType() != null && !request.getPassengerType().isEmpty()){
            IdBaseDomain passengerTypeIdBaseDomain = passengerTypeRepo.findByIdEntity(request.getPassengerType());
            passengerTypeDomain = new PassengerTypeDomain();
            passengerTypeDomain.setId(passengerTypeIdBaseDomain.getId());
        }
        CostTourServiceItemDomain costTourServiceItemDomain = null;
        if (request.getCostTourServiceItem() != null && !request.getCostTourServiceItem().isEmpty()){
            IdBaseDomain costTourServiceItemIdBaseDomain = costTourServiceItemBookingRepo.findByIdEntity(request.getCostTourServiceItem());
            costTourServiceItemDomain = new CostTourServiceItemDomain();
            costTourServiceItemDomain.setId(costTourServiceItemIdBaseDomain.getId());
        }
        CostTourItemDomain costTourItemDomain = request.to(costTourDomain, registerGroupDomain, itineraryDateDomain, tourDomain, passengerTypeDomain, costTourServiceItemDomain);
        CostTourItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(costTourItemDomain, old));
        return CostTourItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public CostTourItemResponse edit(String id, CostTourItemUpdateRequest request, RegisterGroupDomain registerGroupDomain, CostTourDomain costTourDomain) {
        if (id == null) {
            return null;
        }
        CostTourItemDomain old = repo.findById(id);
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
        PassengerTypeDomain passengerTypeDomain = null;
        if (request.getPassengerType() != null && !request.getPassengerType().isEmpty()){
            IdBaseDomain passengerTypeIdBaseDomain = passengerTypeRepo.findByIdEntity(request.getPassengerType());
            passengerTypeDomain = new PassengerTypeDomain();
            passengerTypeDomain.setId(passengerTypeIdBaseDomain.getId());
        }
        CostTourServiceItemDomain costTourServiceItemDomain = null;
        if (request.getCostTourServiceItem() != null && !request.getCostTourServiceItem().isEmpty()){
            IdBaseDomain costTourServiceItemIdBaseDomain = costTourServiceItemBookingRepo.findByIdEntity(request.getCostTourServiceItem());
            costTourServiceItemDomain = new CostTourServiceItemDomain();
            costTourServiceItemDomain.setId(costTourServiceItemIdBaseDomain.getId());
        }
        CostTourItemDomain costTourItemDomain = request.to(costTourDomain, registerGroupDomain, itineraryDateDomain, tourDomain, passengerTypeDomain, costTourServiceItemDomain);
        CostTourItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(costTourItemDomain, old));
        return CostTourItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        CostTourItemResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostTourItemResponse> findAll() {
        return repo.findAll(false).stream().map(costTourItemDomain -> CostTourItemResponse.from(costTourItemDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourItemResponse findById(String id) {
        if (id == null) {
            return null;
        }
        CostTourItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No cost tour item found with id '%s'.".formatted(id));
        }
        return CostTourItemResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourItemSearchResponse searchAll(CostTourItemSearchRequest costTourItemSearchRequest) {
       PageDTO<CostTourItemDomain> pageDTO = repo.searchAll(costTourItemSearchRequest);
       CostTourItemSearchResponse costTourItemSearchRespose = new CostTourItemSearchResponse();
       costTourItemSearchRespose.setCostTourItems(pageDTO.getContent().stream().map(costTourItemDomain ->  CostTourItemResponse.from(costTourItemDomain)).toList());
       costTourItemSearchRespose.setTotalElements(pageDTO.getTotalElements());
       costTourItemSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return costTourItemSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourItemSearchResponse searchFilterAll(CostTourItemSearchFilterRequest costTourItemSearchFilterRequest) {
        PageDTO<CostTourItemDomain> pageDTO = repo.searchAllFilter(costTourItemSearchFilterRequest);
        CostTourItemSearchResponse costTourItemSearchRespose = new CostTourItemSearchResponse();
        costTourItemSearchRespose.setCostTourItems(pageDTO.getContent().stream().map(costTourItemDomain ->  CostTourItemResponse.from(costTourItemDomain)).toList());
        costTourItemSearchRespose.setTotalElements(pageDTO.getTotalElements());
        costTourItemSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return costTourItemSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostTourItemResponse> searchAllByRegisterGroup(String idRegisterGroup) {
        List<CostTourItemDomain> list = repo.searchAllByRegisterGroup(idRegisterGroup);
        return list.stream().map(costTourItemDomain ->  CostTourItemResponse.from(costTourItemDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourItemResponse searchByRegisterGroupAndCostTourItem(String idRegisterGroup, String idCostTourItem) {
        CostTourItemDomain costTourItemDomain = repo.searchAllByRegisterGroupAndCostTourItem(idRegisterGroup, idCostTourItem);
        return CostTourItemResponse.from(costTourItemDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourItemResponse searchByRegisterGroupAndCostTourAndCostTourItem(String idRegisterGroup, String idCostTour, String idCostTourItem) {
        CostTourItemDomain costTourItemDomain = repo.searchAllByRegisterGroupAndCostTourAndCostTourItem(idRegisterGroup, idCostTour, idCostTourItem);
        return CostTourItemResponse.from(costTourItemDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostTourItemDomain> searchAllDomainByRegisterGroup(String idRegisterGroup) {
        List<CostTourItemDomain> list = repo.searchAllByRegisterGroup(idRegisterGroup);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostTourItemDomain> searchAllDomainByRegisterGroupAndCostTour(String idCostTour, String idRegisterGroup) {
        List<CostTourItemDomain> list = repo.searchAllByCostTourAndRegisterGroup(idCostTour, idRegisterGroup);
        return list;
    }

}
