package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.EntityBaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.PassengerGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingRestaurantEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IAnalyticsBookingRepoTemplate {

    Page<ItineraryItemEntity> aggregatePageableItinerary(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    Page<RegisterGroupEntity> aggregatePageableGroup(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    Page<ItineraryItemEntity> aggregatePageableBibleActivity(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    Page<PassengerGroupEntity> aggregatePageablePassenger(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    Page<EntityBaseEntity> aggregatePageableSupplier(ISearchFilterPageDomain searchFilterDomain, Pageable pageable, String supplierTypeInheritance);

    Page<RegisterGroupEntity> aggregatePageableGroupByProgram(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    Page<ItineraryItemEntity> aggregatePageableGroupByActivity(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    Page<RegisterBookingLodgingEntity> aggregatePageableHotelExpiration(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    Page<ItineraryItemEntity> aggregatePageableTransport(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    Page<ItineraryItemEntity> aggregatePageableAirLine(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    Page<RegisterBookingLodgingEntity> aggregatePageableHotel(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    Page<RegisterBookingOperatorEntity> aggregatePageableOperator(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    Page<RegisterBookingRestaurantEntity> aggregatePageableRestaurant(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    Page<Map> aggregatePageableStatistics(ISearchFilterPageDomain searchFilterDomain, Pageable pageable, String groupField);

    Page<ItineraryItemEntity> aggregatePageableGuide(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    Page<ItineraryItemReportItem> aggregatePageableGuideReport(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    Page<RegisterGroupReportItem> aggregateAllRegisterGroupReport(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

}
