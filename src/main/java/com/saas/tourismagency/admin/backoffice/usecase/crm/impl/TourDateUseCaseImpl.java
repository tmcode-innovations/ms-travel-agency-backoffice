package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tourdate.TourDateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultTourDate;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tourdate.TourDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.touritem.TourItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tourdate.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import com.saas.tourismagency.admin.backoffice.usecase.crm.TourDateUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
public class TourDateUseCaseImpl implements TourDateUseCase {

    @Autowired
    private TourDateRepo repo;

    @Autowired
    private TourRepo tourRepo;

    @Autowired
    private TourDateConverter converter;

    @Autowired
    private SetFieldDefaultTourDate setFieldDefault;

    @Override
    @Transactional
    public TourDateResponse create(TourDateCreateRequest request) {
        TourDomain tourDomain = null;
        if (request.getTour() != null && !request.getTour().isEmpty()){
            tourDomain = tourRepo.findById(request.getTour());
        }
        TourDateDomain newDomain = request.to(tourDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        TourDateDomain createdDomain = repo.create(newDomain);
        return TourDateResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public TourDateResponse edit(String id, TourDateUpdateRequest request) {
        if (id == null) {
            return null;
        }
        TourDateDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        TourDomain tourDomain = null;
        if (request.getTour() != null && !request.getTour().isEmpty()){
            tourDomain = tourRepo.findById(request.getTour());
        }
        TourDateDomain tourDateDomain = request.to(tourDomain);
        TourDateDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(tourDateDomain, old));
        return TourDateResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        TourDateResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourDateResponse> findAll() {
        return repo.findAll(false).stream().map(tourDateDomain -> TourDateResponse.from(tourDateDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TourDateResponse findById(String id) {
        if (id == null) {
            return null;
        }
        TourDateDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No tour date found with id '%s'.".formatted(id));
        }
        return TourDateResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public TourDateDomain findByIdDomain(String id) {
        if (id == null) {
            return null;
        }
        TourDateDomain entityDomain = repo.findById(id);
        if (entityDomain == null) {
            throw new NotFoundException("No itinerary date found with id '%s'.".formatted(id));
        }
        return entityDomain;
    }

    @Override
    @Transactional(readOnly = true)
    public TourDateSearchResponse searchAll(TourDateSearchRequest tourDateSearchRequest) {
       PageDTO<TourDateDomain> pageDTO = repo.searchAll(tourDateSearchRequest);
       TourDateSearchResponse tourDateSearchRespose = new TourDateSearchResponse();
       tourDateSearchRespose.setTourDates(pageDTO.getContent().stream().map(tourDateDomain ->  TourDateResponse.from(tourDateDomain)).toList());
       tourDateSearchRespose.setTotalElements(pageDTO.getTotalElements());
       tourDateSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return tourDateSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public TourDateSearchResponse searchFilterAll(TourDateSearchFilterRequest tourDateSearchFilterRequest) {
        PageDTO<TourDateDomain> pageDTO = repo.searchAllFilter(tourDateSearchFilterRequest);
        TourDateSearchResponse tourDateSearchRespose = new TourDateSearchResponse();
        tourDateSearchRespose.setTourDates(pageDTO.getContent().stream().map(tourDateDomain ->  TourDateResponse.from(tourDateDomain)).toList());
        tourDateSearchRespose.setTotalElements(pageDTO.getTotalElements());
        tourDateSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return tourDateSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourDateResponse> searchAllByTour(String idTour) {
        List<TourDateDomain> list = repo.searchAllByTour(idTour);
        return list.stream().map(tourDateDomain ->  TourDateResponse.from(tourDateDomain)).toList();
    }

    @Override
    @Transactional
    public void generateDateTour(TourDomain tour, Integer days) {
        Stream.iterate(0, n -> n + 1)
                .limit(days)
                .forEach(item -> createDateTour(tour, item));
    }

    private void createDateTour(TourDomain tour, Integer day){
        TourDateDomain tourDateDomain = new TourDateDomain();
        Integer indexDay = day + 1;
        tourDateDomain.setName("Dia " + indexDay);
        tourDateDomain.setTour(tour);
        tourDateDomain.setDay(indexDay);
        tourDateDomain.setTenant(UserResolver.resolveUser().getTenant());
        repo.create(tourDateDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourDateDomain> searchAllDomainByTour(String idTour) {
        List<TourDateDomain> list = repo.searchAllByTour(idTour);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public TourDateResponse searchByTourAndTourDate(String idTour, String idTourDate) {
        TourDateDomain tourDateDomain = repo.searchAllByTourAndTourDate(idTour, idTourDate);
        return TourDateResponse.from(tourDateDomain);
    }

}
