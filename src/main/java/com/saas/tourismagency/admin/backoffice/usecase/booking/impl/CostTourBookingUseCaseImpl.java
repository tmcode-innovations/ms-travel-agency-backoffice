package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.costtour.CostTourBookingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultBookingCostTour;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtour.CostTourBookingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtour.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.CostTourDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.CostTourBookingUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CostTourBookingUseCaseImpl implements CostTourBookingUseCase {

    @Autowired
    private CostTourBookingRepo repo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private ItineraryDateRepo itineraryDateRepo;

    @Autowired
    private TourRepo tourRepo;

    @Autowired
    private CostTourBookingConverter converter;

    @Autowired
    private SetFieldDefaultBookingCostTour setFieldDefault;

    @Override
    @Transactional
    public CostTourResponse create(CostTourCreateRequest request) {
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

        CostTourDomain newDomain = request.to(registerGroupDomain, itineraryDateDomain, tourDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        CostTourDomain createdDomain = repo.create(newDomain);
        return CostTourResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CostTourResponse create(CostTourCreateRequest request, RegisterGroupDomain registerGroupDomain) {
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

        CostTourDomain newDomain = request.to(registerGroupDomain, itineraryDateDomain, tourDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        CostTourDomain createdDomain = repo.create(newDomain);
        return CostTourResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CostTourResponse edit(String id, CostTourUpdateRequest request) {
        if (id == null) {
            return null;
        }
        CostTourDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
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
        CostTourDomain costTourDomain = request.to(registerGroupDomain, itineraryDateDomain, tourDomain);
        costTourDomain.setSubTotalAmount(old.getSubTotalAmount());
        CostTourDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(costTourDomain, old));
        return CostTourResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public CostTourResponse edit(String id, CostTourUpdateRequest request, RegisterGroupDomain registerGroupDomain) {
        if (id == null) {
            return null;
        }
        CostTourDomain old = repo.findById(id);
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
        CostTourDomain costTourDomain = request.to(registerGroupDomain, itineraryDateDomain, tourDomain);
        costTourDomain.setSubTotalAmount(old.getSubTotalAmount());
        CostTourDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(costTourDomain, old));
        return CostTourResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        CostTourResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostTourResponse> findAll() {
        return repo.findAll(false).stream().map(costTourDomain -> CostTourResponse.from(costTourDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourResponse findById(String id) {
        if (id == null) {
            return null;
        }
        CostTourDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No itinerary date found with id '%s'.".formatted(id));
        }
        return CostTourResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourDomain findByIdDomain(String id) {
        if (id == null) {
            return null;
        }
        CostTourDomain entityDomain = repo.findById(id);
        if (entityDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(id));
        }
        return entityDomain;
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourSearchResponse searchAll(CostTourSearchRequest searchRequest) {
       PageDTO<CostTourDomain> pageDTO = repo.searchAll(searchRequest);
       CostTourSearchResponse costTourSearchResponse = new CostTourSearchResponse();
       costTourSearchResponse.setCostTours(pageDTO.getContent().stream().map( costTourDomain ->  CostTourResponse.from(costTourDomain)).toList());
       costTourSearchResponse.setTotalElements(pageDTO.getTotalElements());
       costTourSearchResponse.setTotalPages(pageDTO.getTotalPages());
       return costTourSearchResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourSearchResponse searchFilterAll(CostTourSearchFilterRequest costTourSearchFilterRequest) {
        PageDTO<CostTourDomain> pageDTO = repo.searchAllFilter(costTourSearchFilterRequest);
        CostTourSearchResponse costTourSearchRespose = new CostTourSearchResponse();
        costTourSearchRespose.setCostTours(pageDTO.getContent().stream().map(costTourDomain ->  CostTourResponse.from(costTourDomain)).toList());
        costTourSearchRespose.setTotalElements(pageDTO.getTotalElements());
        costTourSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return costTourSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostTourResponse> searchAllByRegisterGroup(String idRegisterGroup) {
        List<CostTourDomain> list = repo.searchAllByRegisterGroup(idRegisterGroup);
        return list.stream().map(costTourDomain ->  CostTourResponse.from(costTourDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostTourDomain> searchAllDomainByRegisterGroup(String idRegisterGroup) {
        List<CostTourDomain> list = repo.searchAllByRegisterGroup(idRegisterGroup);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourResponse searchByRegisterGroupAndCostTour(String idRegisterGroup, String idCostTour) {
        CostTourDomain costTourDomain = repo.searchAllByRegisterGroupAndCostTour(idRegisterGroup, idCostTour);
        return CostTourResponse.from(costTourDomain);
    }

}
