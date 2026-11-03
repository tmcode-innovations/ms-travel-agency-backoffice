package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tour.TourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultTour;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.PersistTourNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tourdate.TourDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.touitem.TourItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.touitem.TourItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.touitem.TourItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tourdate.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import com.saas.tourismagency.admin.backoffice.usecase.crm.*;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
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
public class TourUseCaseImpl implements TourUseCase {

    @Autowired
    private TourRepo repo;

    @Autowired
    private TourConverter converter;

    @Autowired
    private SetFieldDefaultTour setFieldDefault;

    @Autowired
    PersistTourNumberCallbackImpl persistTourNumberCallbackImpl;

    @Autowired
    private TourDateUseCase tourDateUseCase;

    @Autowired
    private TourItemUseCase tourItemUseCase;

    @Autowired
    private TourDateRepo tourDateRepo;

    @Override
    @Transactional
    public TourResponse create(TourCreateRequest request) {
        TourDomain tourDomainExist = repo.findByName(request.getName());
        if (tourDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        TourDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistTourNumberCallbackImpl.generateNumber());
        TourDomain createdDomain = repo.create(newDomain);
        tourDateUseCase.generateDateTour(createdDomain, createdDomain.getCantDay());
        return TourResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public TourResponse edit(String id, TourUpdateRequest request) {
        if (id == null) {
            return null;
        }
        TourDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        TourDomain tourDomainExist = repo.findByName(request.getName());
        if (tourDomainExist != null && !tourDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }

        TourDomain tourDomain = request.to();
        tourDomain.setIdentify(old.getIdentify());
        TourDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(tourDomain, old));
        return TourResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        TourResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourResponse> findAll() {
        return repo.findAll(false).stream().map(tourDomain -> TourResponse.from(tourDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TourResponse findById(String id) {
        if (id == null) {
            return null;
        }
        TourDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(id));
        }
        return TourResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public TourSearchResponse searchAll(TourSearchRequest tourSearchRequest) {
       PageDTO<TourDomain> pageDTO = repo.searchAll(tourSearchRequest);
       TourSearchResponse tourSearchRespose = new TourSearchResponse();
       tourSearchRespose.setTours(pageDTO.getContent().stream().map(tourDomain -> TourResponse.from(tourDomain)).toList());
       tourSearchRespose.setTotalElements(pageDTO.getTotalElements());
       tourSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return tourSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public TourSearchResponse searchFilterAll(TourSearchFilterRequest tourSearchFilterRequest) {
        PageDTO<TourDomain> pageDTO = repo.searchAllFilter(tourSearchFilterRequest);
        TourSearchResponse tourSearchRespose = new TourSearchResponse();
        tourSearchRespose.setTours(pageDTO.getContent().stream().map(tourDomain -> TourResponse.from(tourDomain)).toList());
        tourSearchRespose.setTotalElements(pageDTO.getTotalElements());
        tourSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return tourSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public TourResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        TourDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No tour found with name '%s'.".formatted(name));
        }
        return TourResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public TourResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        TourDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No tour found with identify '%s'.".formatted(identify));
        }
        return TourResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourItemResponse> findAllTourItemByTour(String idTour) {
        if (idTour == null) {
            return null;
        }
        TourDomain tourDomain = repo.findById(idTour);
        if (tourDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(idTour));
        }
        return tourItemUseCase.searchAllByTour(idTour);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourDateResponse> findAllTourDateByTour(String idTour) {
        if (idTour == null) {
            return null;
        }
        TourDomain tourDomain = repo.findById(idTour);
        if (tourDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(idTour));
        }
        return tourDateUseCase.searchAllByTour(idTour);
    }

    @Override
    @Transactional
    public TourItemResponse addTour(TourItemCreateRequest request, String idTour) {
        if (idTour == null) {
            return null;
        }
        TourDomain tourDomain = repo.findById(idTour);
        if (tourDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(idTour));
        }
        TourItemResponse resultCreate = tourItemUseCase.create(request, tourDomain);
        return resultCreate;
    }

    @Override
    @Transactional
    public List<TourItemResponse> addTourMultiple(TourItemCreateRequest request, String idTour) {
        if (idTour == null) {
            return null;
        }
        TourDomain tourDomain = repo.findById(idTour);
        if (tourDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(idTour));
        }
        List<TourItemResponse> resultCreate = tourItemUseCase.createMultiple(request, tourDomain);

        return resultCreate;
    }

    @Override
    @Transactional
    public TourItemResponse editTour(String id, TourItemUpdateRequest request, String idTour) {
        if (idTour == null) {
            return null;
        }
        TourDomain tourDomain = repo.findById(idTour);
        if (tourDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(idTour));
        }
        TourItemResponse resultEdit = tourItemUseCase.edit(id, request, tourDomain);

        return resultEdit;
    }

    @Override
    @Transactional
    public void deleteTour(String id, String idTour) {
        TourDomain tourDomain = repo.findById(idTour);
        if (tourDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(idTour));
        }

        tourItemUseCase.deleteById(id);
    }

    @Override
    @Transactional
    public TourItemResponse findByTourItemIdAndTour(String id, String idTour) {
        if (idTour == null) {
            return null;
        }
        TourDomain tourDomain = repo.findById(idTour);
        if (tourDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(idTour));
        }
        return tourItemUseCase.searchByTourAndTourItem(idTour, id);
    }

    @Override
    @Transactional
    public TourDateResponse findByTourDateIdAndTour(String id, String idTour) {
        if (idTour == null) {
            return null;
        }
        TourDomain tourDomain = repo.findById(idTour);
        if (tourDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(idTour));
        }
        return tourDateUseCase.searchByTourAndTourDate(idTour, id);
    }

    @Override
    @Transactional
    public List<TourDateAllItemResponse> findAllTourDateWithTourItemByTour(String idTour) {
        List<TourDateAllItemResponse> result = new ArrayList<>();
        if (idTour == null) {
            return null;
        }
        TourDomain tourDomain = repo.findById(idTour);
        if (tourDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(idTour));
        }
        List<TourDateDomain> listTourDate = tourDateUseCase.searchAllDomainByTour(idTour);
        if (listTourDate != null && !listTourDate.isEmpty()){
            List<TourDateDomain> listTourDateResult = listTourDate.stream().collect(Collectors.toList());
            Collections.sort(listTourDateResult);
            for (TourDateDomain tourDateDomain: listTourDateResult
            ) {
                List<TourItemDomain> listTourItem = tourItemUseCase.searchAllDomainByTourAndTourDate(tourDateDomain.getId(), idTour);
                List<TourItemDomain> listTourItemResult = new ArrayList<>();
                if (listTourItem != null && !listTourItem.isEmpty()){
                    listTourItemResult = listTourItem.stream().collect(Collectors.toList());
                    Collections.sort(listTourItemResult);
                }
                result.add(TourDateAllItemResponse.from(tourDateDomain, listTourItemResult));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public List<TourModelViewResponse> findAllTourDateWithTourItemByTourModelView(String idTour) {
        List<TourDateAllItemResponse> list = this.findAllTourDateWithTourItemByTour(idTour);
        List<TourModelViewResponse> result = new ArrayList<>();
        if (list != null && !list.isEmpty()){
            for (TourDateAllItemResponse tourDateAllItemResponse: list
            ) {
                List<TourModelViewResponse> details = new ArrayList<>();
                if (tourDateAllItemResponse.getTourItems() != null && !tourDateAllItemResponse.getTourItems().isEmpty()){
                    for (TourItemResponse tourItemResponse: tourDateAllItemResponse.getTourItems()
                    ) {
                        TourModelViewResponse tourModelViewResponseChild = TourModelViewResponse.from(
                                tourItemResponse.getId(),
                                null,
                                tourItemResponse.getSupplierType(),
                                tourItemResponse.getSupplier(),
                                tourItemResponse.getService(),
                                tourItemResponse.getObservation(),
                                false,
                                null
                        );
                        details.add(tourModelViewResponseChild);
                    }
                }
                TourModelViewResponse tourModelViewResponseParent = TourModelViewResponse.from(
                        tourDateAllItemResponse.getId(),
                        tourDateAllItemResponse.getName(),
                        null,
                        null,
                        null,
                        null,
                        true,
                        details
                );
                result.add(tourModelViewResponseParent);
            }
        }
        return result;
    }

    @Override
    @Transactional
    public TourDateResponse editTourDate(String id, TourDateUpdateOnlyNameRequest request, String idTour) {
        if (idTour == null) {
            return null;
        }
        TourDomain tourDomain = repo.findById(idTour);
        if (tourDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(idTour));
        }
        TourDateDomain tourDateDomain = tourDateUseCase.findByIdDomain(id);

        TourDateUpdateRequest tourDateUpdateRequest = new TourDateUpdateRequest();
        tourDateUpdateRequest.setId(request.getId());
        tourDateUpdateRequest.setName(request.getName());
        tourDateUpdateRequest.setDescription(tourDateDomain.getDescription());
        tourDateUpdateRequest.setDay(tourDateDomain.getDay());
        tourDateUpdateRequest.setIdentify(tourDateDomain.getIdentify());
        tourDateUpdateRequest.setTour(request.getTour());

        TourDateResponse resultEdit = tourDateUseCase.edit(id, tourDateUpdateRequest);

        return resultEdit;
    }

    @Override
    @Transactional
    public void changeDateTourDay(String idTour, ChangeTourDayCrmRequest changeTourDayRequest) {
        if (idTour == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        TourDomain tourDomain = repo.findById(idTour);
        if (tourDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(idTour));
        }

        if (tourDomain.getCantDay().equals(changeTourDayRequest.getDays())){

            throw new BadRequestException(MessagesValidation.VALIDATION_SAME_DAY_TOUR);

        }else if (changeTourDayRequest.getDays() > tourDomain.getCantDay()){
            Integer days = changeTourDayRequest.getDays() - tourDomain.getCantDay();
            Stream.iterate(0, n -> n + 1)
                    .limit(days)
                    .forEach(item -> {
                        TourDateDomain tourDateDomain = new TourDateDomain();
                        Integer indexDay = tourDomain.getCantDay() + item + 1;
                        tourDateDomain.setName("Dia " + indexDay);
                        tourDateDomain.setTour(tourDomain);
                        tourDateDomain.setDay(indexDay);
                        tourDateDomain.setTenant(UserResolver.resolveUser().getTenant());
                        tourDateRepo.create(tourDateDomain);
                    });

            tourDomain.setCantDay(changeTourDayRequest.getDays());

            repo.edit(tourDomain);

        }else if (changeTourDayRequest.getDays() < tourDomain.getCantDay()){

            throw new BadRequestException(MessagesValidation.VALIDATION_LESS_THAN_DAY_TOUR);

        }

    }

}
