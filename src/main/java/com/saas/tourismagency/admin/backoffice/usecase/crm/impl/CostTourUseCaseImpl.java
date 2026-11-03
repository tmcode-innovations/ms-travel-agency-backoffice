package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.costtour.CostTourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultCostTour;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtour.CostTourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itinerarydate.ItineraryDateCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.QuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import com.saas.tourismagency.admin.backoffice.usecase.crm.CostTourUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CostTourUseCaseImpl implements CostTourUseCase {

    @Autowired
    private CostTourRepo repo;

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private ItineraryDateCrmRepo itineraryDateRepo;

    @Autowired
    private TourRepo tourRepo;

    @Autowired
    private CostTourConverter converter;

    @Autowired
    private SetFieldDefaultCostTour setFieldDefault;

    @Override
    @Transactional
    public CostTourResponse create(CostTourCreateRequest request) {
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

        CostTourDomain newDomain = request.to(quotationDomain, itineraryDateDomain, tourDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        CostTourDomain createdDomain = repo.create(newDomain);
        return CostTourResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CostTourResponse create(CostTourCreateRequest request, QuotationDomain quotationDomain) {
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

        CostTourDomain newDomain = request.to(quotationDomain, itineraryDateDomain, tourDomain);
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
        CostTourDomain costTourDomain = request.to(quotationDomain, itineraryDateDomain, tourDomain);
        costTourDomain.setSubTotalAmount(old.getSubTotalAmount());
        CostTourDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(costTourDomain, old));
        return CostTourResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public CostTourResponse edit(String id, CostTourUpdateRequest request, QuotationDomain quotationDomain) {
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
        CostTourDomain costTourDomain = request.to(quotationDomain, itineraryDateDomain, tourDomain);
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
    public List<CostTourResponse> searchAllByQuotation(String idQuotation) {
        List<CostTourDomain> list = repo.searchAllByQuotation(idQuotation);
        return list.stream().map(costTourDomain ->  CostTourResponse.from(costTourDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostTourDomain> searchAllDomainByQuotation(String idQuotation) {
        List<CostTourDomain> list = repo.searchAllByQuotation(idQuotation);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public CostTourResponse searchByQuotationAndCostTour(String idQuotation, String idCostTour) {
        CostTourDomain costTourDomain = repo.searchAllByQuotationAndCostTour(idQuotation, idCostTour);
        return CostTourResponse.from(costTourDomain);
    }

}
