package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.itineraryitem.ItineraryItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.itineraryitem.ItineraryItemReportConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.passengergroup.PassengerGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodging.RegisterBookingLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.EntityBaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.PassengerGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingRestaurantEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.booking.AnalyticsBookingSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.booking.AnalyticsBookingSearchReportFilterRequest;
import com.saas.tourismagency.admin.backoffice.domain.base.EntityBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsBookingRepo {

    @Autowired
    private IAnalyticsBookingRepoTemplate repositoryTemplate;

    @Autowired
    private ItineraryItemConverter itineraryItemConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private PassengerGroupConverter passengerGroupConverter;

    @Autowired
    private RegisterBookingLodgingConverter registerBookingLodgingConverter;

    @Autowired
    private ItineraryItemReportConverter itineraryItemReportConverter;

    public PageDTO<ItineraryItemDomain> searchPageableItinerary(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<ItineraryItemEntity> result = repositoryTemplate.aggregatePageableItinerary(request, pageConfig);
        return toItineraryItemPageDTO(result);
    }

    public PageDTO<RegisterGroupDomain> searchPageableGroup(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<RegisterGroupEntity> result = repositoryTemplate.aggregatePageableGroup(request, pageConfig);
        return toRegisterGroupPageDTO(result);
    }

    public PageDTO<ItineraryItemDomain> searchPageableBibleActivity(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<ItineraryItemEntity> result = repositoryTemplate.aggregatePageableBibleActivity(request, pageConfig);
        return toItineraryItemPageDTO(result);
    }

    public PageDTO<PassengerGroupDomain> searchPageablePassenger(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<PassengerGroupEntity> result = repositoryTemplate.aggregatePageablePassenger(request, pageConfig);
        return toPassengerGroupPageDTO(result);
    }

    public PageDTO<RegisterBookingLodgingDomain> searchPageableHotel(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<RegisterBookingLodgingEntity> result = repositoryTemplate.aggregatePageableHotel(request, pageConfig);
        return toRegisterBookingLodgingPageDTO(result);
    }

    public PageDTO<RegisterBookingLodgingDomain> searchPageableOperator(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<RegisterBookingOperatorEntity> result = repositoryTemplate.aggregatePageableOperator(request, pageConfig);
        return toRegisterBookingOperatorPageDTO(result);
    }

    public PageDTO<RegisterBookingLodgingDomain> searchPageableRestaurant(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<RegisterBookingRestaurantEntity> result = repositoryTemplate.aggregatePageableRestaurant(request, pageConfig);
        return toRegisterBookingRestaurantPageDTO(result);
    }

    public PageDTO<ItineraryItemDomain> searchPageableTransport(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<ItineraryItemEntity> result = repositoryTemplate.aggregatePageableTransport(request, pageConfig);
        return toItineraryItemPageDTO(result);
    }

    public PageDTO<ItineraryItemDomain> searchPageableAirLine(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<ItineraryItemEntity> result = repositoryTemplate.aggregatePageableAirLine(request, pageConfig);
        return toItineraryItemPageDTO(result);
    }

    public PageDTO<RegisterGroupDomain> searchPageableGroupByProgram(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<RegisterGroupEntity> result = repositoryTemplate.aggregatePageableGroupByProgram(request, pageConfig);
        return toRegisterGroupPageDTO(result);
    }

    public PageDTO<ItineraryItemDomain> searchPageableGroupByActivity(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<ItineraryItemEntity> result = repositoryTemplate.aggregatePageableGroupByActivity(request, pageConfig);
        return toItineraryItemPageDTO(result);
    }

    public PageDTO<RegisterBookingLodgingDomain> searchPageableHotelExpiration(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<RegisterBookingLodgingEntity> result = repositoryTemplate.aggregatePageableHotelExpiration(request, pageConfig);
        return toRegisterBookingLodgingPageDTO(result);
    }

    @SuppressWarnings("unchecked")
    public PageDTO<Map> searchPageableStatisticsForeignOperator(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<Map> result = repositoryTemplate.aggregatePageableStatistics(request, pageConfig, "operator");
        return toMapPageDTO(result);
    }

    @SuppressWarnings("unchecked")
    public PageDTO<Map> searchPageableStatisticsCountry(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<Map> result = repositoryTemplate.aggregatePageableStatistics(request, pageConfig, "country");
        return toMapPageDTO(result);
    }

    @SuppressWarnings("unchecked")
    public PageDTO<Map> searchPageableStatisticsResponsible(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<Map> result = repositoryTemplate.aggregatePageableStatistics(request, pageConfig, "responsible");
        return toMapPageDTO(result);
    }

    @SuppressWarnings("unchecked")
    public PageDTO<Map> searchPageableStatisticsHotel(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<Map> result = repositoryTemplate.aggregatePageableStatistics(request, pageConfig, "hotel");
        return toMapPageDTO(result);
    }

    public PageDTO<ItineraryItemDomain> searchPageableGuide(AnalyticsBookingSearchFilterRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize(), Sort.by(List.of()));
        Page<ItineraryItemEntity> result = repositoryTemplate.aggregatePageableGuide(request, pageConfig);
        return toItineraryItemPageDTO(result);
    }

    public List<ItineraryItemDomain> searchAllItinerary(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        return searchAllFromItineraryAggregation(filterRequest);
    }

    public List<RegisterGroupReportItem> searchAllGroup(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        Page<RegisterGroupReportItem> result = repositoryTemplate.aggregateAllRegisterGroupReport(filterRequest, PageRequest.of(filterRequest.getPage(), filterRequest.getSize(), Sort.by(List.of())));
        return result != null && result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<ItineraryItemDomain> searchAllBibleActivity(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        return searchAllFromItineraryAggregation(filterRequest);
    }

    public List<PassengerGroupDomain> searchAllPassenger(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<PassengerGroupDomain> result = searchPageablePassenger(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<RegisterBookingLodgingDomain> searchAllHotel(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<RegisterBookingLodgingDomain> result = searchPageableHotel(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<RegisterBookingLodgingDomain> searchAllOperator(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<RegisterBookingLodgingDomain> result = searchPageableOperator(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<RegisterBookingLodgingDomain> searchAllRestaurant(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<RegisterBookingLodgingDomain> result = searchPageableRestaurant(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<ItineraryItemDomain> searchAllTransport(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        return searchAllFromItineraryAggregation(filterRequest);
    }

    public List<ItineraryItemDomain> searchAllAirLine(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        return searchAllFromItineraryAggregation(filterRequest);
    }

    public List<RegisterGroupReportItem> searchAllGroupByProgram(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        Page<RegisterGroupReportItem> result = repositoryTemplate.aggregateAllRegisterGroupReport(filterRequest, PageRequest.of(filterRequest.getPage(), filterRequest.getSize(), Sort.by(List.of())));
        return result != null && result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<ItineraryItemDomain> searchAllGroupByActivity(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        return searchAllFromItineraryAggregation(filterRequest);
    }

    public List<RegisterBookingLodgingDomain> searchAllHotelExpiration(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<RegisterBookingLodgingDomain> result = searchPageableHotelExpiration(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<Map> searchAllStatisticsForeignOperator(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<Map> result = searchPageableStatisticsForeignOperator(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<Map> searchAllStatisticsCountry(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<Map> result = searchPageableStatisticsCountry(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<Map> searchAllStatisticsResponsible(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<Map> result = searchPageableStatisticsResponsible(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<Map> searchAllStatisticsHotel(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<Map> result = searchPageableStatisticsHotel(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<ItineraryItemDomain> searchAllGuide(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<ItineraryItemDomain> result = searchPageableGuide(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<ItineraryItemDomain> searchAllGuideReport(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = toFilterRequest(request);
        return searchAllFromItineraryAggregation(filterRequest);
    }

    private List<ItineraryItemDomain> searchAllFromItineraryAggregation(AnalyticsBookingSearchFilterRequest filterRequest) {
        Page<ItineraryItemReportItem> result = repositoryTemplate.aggregatePageableGuideReport(filterRequest, PageRequest.of(filterRequest.getPage(), filterRequest.getSize(), Sort.by(List.of())));
        List<ItineraryItemDomain> content = new ArrayList<>();
        if (result != null && result.getContent() != null) {
            for (ItineraryItemReportItem item : result.getContent()) {
                content.add(itineraryItemReportConverter.toDomain(item));
            }
        }
        return content;
    }

    private AnalyticsBookingSearchFilterRequest toFilterRequest(AnalyticsBookingSearchReportFilterRequest request) {
        AnalyticsBookingSearchFilterRequest filterRequest = new AnalyticsBookingSearchFilterRequest();
        filterRequest.setPage(0);
        filterRequest.setSize(Integer.MAX_VALUE);
        filterRequest.setFilter(request.getFilter());
        filterRequest.setSort(request.getSort());
        return filterRequest;
    }

    private PageDTO<ItineraryItemDomain> toItineraryItemPageDTO(Page<ItineraryItemEntity> page) {
        if (page == null) {
            return new PageDTO<>();
        }
        PageDTO<ItineraryItemDomain> pageDTO = new PageDTO<>();
        List<ItineraryItemDomain> content = new ArrayList<>();
        if (page.getContent() != null) {
            for (ItineraryItemEntity entity : page.getContent()) {
                content.add(itineraryItemConverter.toDomain(entity));
            }
        }
        pageDTO.setContent(content);
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    private PageDTO<RegisterGroupDomain> toRegisterGroupPageDTO(Page<RegisterGroupEntity> page) {
        if (page == null) {
            return new PageDTO<>();
        }
        PageDTO<RegisterGroupDomain> pageDTO = new PageDTO<>();
        List<RegisterGroupDomain> content = new ArrayList<>();
        if (page.getContent() != null) {
            for (RegisterGroupEntity entity : page.getContent()) {
                content.add(registerGroupConverter.toDomain(entity));
            }
        }
        pageDTO.setContent(content);
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    private PageDTO<PassengerGroupDomain> toPassengerGroupPageDTO(Page<PassengerGroupEntity> page) {
        if (page == null) {
            return new PageDTO<>();
        }
        PageDTO<PassengerGroupDomain> pageDTO = new PageDTO<>();
        List<PassengerGroupDomain> content = new ArrayList<>();
        if (page.getContent() != null) {
            for (PassengerGroupEntity entity : page.getContent()) {
                content.add(passengerGroupConverter.toDomain(entity));
            }
        }
        pageDTO.setContent(content);
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    private PageDTO<EntityBaseDomain> toEntityBasePageDTO(Page<EntityBaseEntity> page) {
        if (page == null) {
            return new PageDTO<>();
        }
        PageDTO<EntityBaseDomain> pageDTO = new PageDTO<>();
        List<EntityBaseDomain> content = new ArrayList<>();
        if (page.getContent() != null) {
            for (EntityBaseEntity entity : page.getContent()) {
                EntityBaseDomain domain = new EntityBaseDomain();
                domain.setId(entity.getId());
                domain.setName(entity.getName());
                domain.setDescription(entity.getDescription());
                domain.setIdentify(entity.getIdentify());
                domain.setTypeInheritance(entity.getTypeInheritance());
                domain.setDeleted(entity.getDeleted());
                domain.setTenant(entity.getTenant());
                domain.setCreatedAt(entity.getCreatedAt());
                domain.setUpdatedAt(entity.getUpdatedAt());
                content.add(domain);
            }
        }
        pageDTO.setContent(content);
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    private PageDTO<RegisterBookingLodgingDomain> toRegisterBookingLodgingPageDTO(Page<RegisterBookingLodgingEntity> page) {
        if (page == null) {
            return new PageDTO<>();
        }
        PageDTO<RegisterBookingLodgingDomain> pageDTO = new PageDTO<>();
        List<RegisterBookingLodgingDomain> content = new ArrayList<>();
        if (page.getContent() != null) {
            for (RegisterBookingLodgingEntity entity : page.getContent()) {
                content.add(registerBookingLodgingConverter.toDomain(entity));
            }
        }
        pageDTO.setContent(content);
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    @SuppressWarnings("unchecked")
    private PageDTO<Map> toMapPageDTO(Page<Map> page) {
        if (page == null) {
            return new PageDTO<>();
        }
        PageDTO<Map> pageDTO = new PageDTO<>();
        pageDTO.setContent(page.getContent());
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    private PageDTO<RegisterBookingLodgingDomain> toRegisterBookingOperatorPageDTO(Page<RegisterBookingOperatorEntity> page) {
        if (page == null) {
            return new PageDTO<>();
        }
        PageDTO<RegisterBookingLodgingDomain> pageDTO = new PageDTO<>();
        List<RegisterBookingLodgingDomain> content = new ArrayList<>();
        if (page.getContent() != null) {
            for (RegisterBookingOperatorEntity entity : page.getContent()) {
                RegisterBookingLodgingDomain domain = new RegisterBookingLodgingDomain();
                domain.setId(entity.getId());
                domain.setName(entity.getName());
                domain.setDescription(entity.getDescription());
                domain.setIdentify(entity.getIdentify());
                domain.setCantPax(entity.getCantPax());
                domain.setDateAperture(entity.getDateAperture());
                domain.setStateBooking(entity.getStateBooking());
                if (entity.getRegisterGroup() != null) {
                    RegisterGroupDomain rgDomain = new RegisterGroupDomain();
                    rgDomain.setId(entity.getRegisterGroup().getId());
                    rgDomain.setIdentify(entity.getRegisterGroup().getIdentify());
                    domain.setRegisterGroup(rgDomain);
                }
                if (entity.getSupplier() != null) {
                    com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain sDomain = new com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain();
                    sDomain.setId(entity.getSupplier().getId());
                    sDomain.setName(entity.getSupplier().getName());
                    domain.setSupplier(sDomain);
                }
                content.add(domain);
            }
        }
        pageDTO.setContent(content);
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    private PageDTO<RegisterBookingLodgingDomain> toRegisterBookingRestaurantPageDTO(Page<RegisterBookingRestaurantEntity> page) {
        if (page == null) {
            return new PageDTO<>();
        }
        PageDTO<RegisterBookingLodgingDomain> pageDTO = new PageDTO<>();
        List<RegisterBookingLodgingDomain> content = new ArrayList<>();
        if (page.getContent() != null) {
            for (RegisterBookingRestaurantEntity entity : page.getContent()) {
                RegisterBookingLodgingDomain domain = new RegisterBookingLodgingDomain();
                domain.setId(entity.getId());
                domain.setName(entity.getName());
                domain.setDescription(entity.getDescription());
                domain.setIdentify(entity.getIdentify());
                domain.setCantPax(entity.getCantPax());
                domain.setDateAperture(entity.getDateAperture());
                domain.setStateBooking(entity.getStateBooking());
                if (entity.getRegisterGroup() != null) {
                    RegisterGroupDomain rgDomain = new RegisterGroupDomain();
                    rgDomain.setId(entity.getRegisterGroup().getId());
                    rgDomain.setIdentify(entity.getRegisterGroup().getIdentify());
                    domain.setRegisterGroup(rgDomain);
                }
                if (entity.getSupplier() != null) {
                    com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain sDomain = new com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain();
                    sDomain.setId(entity.getSupplier().getId());
                    sDomain.setName(entity.getSupplier().getName());
                    domain.setSupplier(sDomain);
                }
                content.add(domain);
            }
        }
        pageDTO.setContent(content);
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

}
