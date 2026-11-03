package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.itineraryitem;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion.DepartmentRegionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.costtour.CostTourBookingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.costtourserviceitem.CostTourServiceItemBookingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.itinerarydate.ItineraryDateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingbase.RegisterBookingBaseConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodger.RegisterBookingItemLodgerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemoperator.RegisterBookingItemOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tour.TourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.guide.GuideConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.lodgingcategory.LodgingCategoryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.orderserviceitem.OrderServiceItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryDateEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItineraryItemConverter implements Converter<ItineraryItemDomain, ItineraryItemEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private SupplierConverter supplierConverter;

    @Autowired
    private SupplierLodgingConverter supplierLodgingConverter;

    @Autowired
    private SupplierTypeConverter supplierTypeConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private ItineraryDateConverter itineraryDateConverter;

    @Autowired
    private LodgingCategoryConverter lodgingCategoryConverter;

    @Autowired
    private DepartmentRegionConverter departmentRegionConverter;

    @Autowired
    private RegisterBookingBaseConverter registerBookingBaseConverter;

    @Autowired
    private GuideConverter guideConverter;

    @Autowired
    private OrderServiceItemConverter orderServiceItemConverter;

    @Autowired
    private RegisterBookingItemOperatorConverter registerBookingItemOperatorConverter;

    @Autowired
    private RegisterBookingItemLodgerConverter registerBookingItemLodgerConverter;

    @Autowired
    private TourConverter tourConverter;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private CostTourBookingConverter costTourConverter;

    @Autowired
    private CostTourServiceItemBookingConverter costTourServiceItemConverter;

    @Override
    public ItineraryItemDomain toDomain(ItineraryItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();

        itineraryItemDomain.setId( source.getId() );
        itineraryItemDomain.setDeleted( source.getDeleted() );
        itineraryItemDomain.setEnabled( source.getEnabled() );
        itineraryItemDomain.setMigrated( source.getMigrated() );
        itineraryItemDomain.setCreatedAt( source.getCreatedAt() );
        itineraryItemDomain.setUpdatedAt( source.getUpdatedAt() );
        itineraryItemDomain.setDeletedAt( source.getDeletedAt() );
        itineraryItemDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        itineraryItemDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        itineraryItemDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        itineraryItemDomain.setIdentifierName( source.getIdentifierName() );
        itineraryItemDomain.setTenant( source.getTenant() );
        itineraryItemDomain.setName( source.getName() );
        itineraryItemDomain.setDescription( source.getDescription() );
        itineraryItemDomain.setIdentify( source.getIdentify() );
        itineraryItemDomain.setObservation( source.getObservation() );
        itineraryItemDomain.setServiceGroup( source.getServiceGroup() );
        itineraryItemDomain.setItineraryDateTime( source.getItineraryDateTime() );
        itineraryItemDomain.setIndex( source.getIndex() );
        itineraryItemDomain.setPaxTotal( source.getPaxTotal() );
        itineraryItemDomain.setPrice( source.getPrice() );
        itineraryItemDomain.setPaxAdultTotal( source.getPaxAdultTotal() );
        itineraryItemDomain.setPriceAdult( source.getPriceAdult() );
        itineraryItemDomain.setPaxChildrenTotal( source.getPaxChildrenTotal() );
        itineraryItemDomain.setPriceChildren( source.getPriceChildren() );
        itineraryItemDomain.setPaxTcTotal( source.getPaxTcTotal() );
        itineraryItemDomain.setPriceTc( source.getPriceTc() );
        itineraryItemDomain.setPaxTcLocalTotal( source.getPaxTcLocalTotal() );
        itineraryItemDomain.setPriceTcLocal( source.getPriceTcLocal() );
        itineraryItemDomain.setPaxGuideTotal( source.getPaxGuideTotal() );
        itineraryItemDomain.setPriceGuide( source.getPriceGuide() );

        if (source.getItineraryDate() != null &&
                source.getItineraryDate().getId() != null &&
                !source.getItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setItineraryDate( itineraryDateConverter.toDomain( source.getItineraryDate() ) );
        }else{
            itineraryItemDomain.setItineraryDate( null );
        }
        if (source.getRegisterGroup() != null &&
                source.getRegisterGroup().getId() != null &&
                !source.getRegisterGroup().getId().isEmpty()) {
            itineraryItemDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );
        }else{
            itineraryItemDomain.setRegisterGroup( null );
        }
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            itineraryItemDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        }else{
            itineraryItemDomain.setSupplierType( null );
        }
        if (source.getSupplier() != null &&
                source.getSupplier().getId() != null &&
                !source.getSupplier().getId().isEmpty()) {
            itineraryItemDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        }else{
            itineraryItemDomain.setSupplier( null );
        }
        if (source.getService() != null &&
                source.getService().getId() != null &&
                !source.getService().getId().isEmpty()) {
            itineraryItemDomain.setService( serviceConverter.toDomain( source.getService() ) );
        }else{
            itineraryItemDomain.setService( null );
        }
        if (source.getDepartmentRegion() != null &&
                source.getDepartmentRegion().getId() != null &&
                !source.getDepartmentRegion().getId().isEmpty()) {
            itineraryItemDomain.setDepartmentRegion( departmentRegionConverter.toDomain( source.getDepartmentRegion() ) );
        }else{
            itineraryItemDomain.setDepartmentRegion( null );
        }
        if (source.getRegisterBookingBase() != null &&
                source.getRegisterBookingBase().getId() != null &&
                !source.getRegisterBookingBase().getId().isEmpty()) {
            itineraryItemDomain.setRegisterBookingBase( registerBookingBaseConverter.toDomain( source.getRegisterBookingBase() ) );
        }else{
            itineraryItemDomain.setRegisterBookingBase( null );
        }
        if (source.getGuideTransport() != null &&
                source.getGuideTransport().getId() != null &&
                !source.getGuideTransport().getId().isEmpty()) {
            itineraryItemDomain.setGuideTransport(guideConverter.toDomain(source.getGuideTransport()));
        }else{
            itineraryItemDomain.setGuideTransport( null );
        }
        if (source.getGuideTransferDrive() != null &&
                source.getGuideTransferDrive().getId() != null &&
                !source.getGuideTransferDrive().getId().isEmpty()) {
            itineraryItemDomain.setGuideTransferDrive( guideConverter.toDomain(source.getGuideTransferDrive() ) );
        }else{
            itineraryItemDomain.setGuideTransferDrive( null );
        }
        if (source.getTour() != null &&
                source.getTour().getId() != null &&
                !source.getTour().getId().isEmpty()) {
            itineraryItemDomain.setTour( tourConverter.toDomain(source.getTour() ) );
        }else{
            itineraryItemDomain.setTour( null );
        }
        if (source.getCustomer() != null &&
                source.getCustomer().getId() != null &&
                !source.getCustomer().getId().isEmpty()) {
            itineraryItemDomain.setCustomer( customerConverter.toDomain(source.getCustomer() ) );
        }else{
            itineraryItemDomain.setCustomer( null );
        }
        if (source.getOrderServiceItem() != null &&
                source.getOrderServiceItem().getId() != null &&
                !source.getOrderServiceItem().getId().isEmpty()) {
            itineraryItemDomain.setOrderServiceItem( orderServiceItemConverter.toDomain( source.getOrderServiceItem() ) );
        }else{
            itineraryItemDomain.setOrderServiceItem( null );
        }
        if (source.getCostTour() != null &&
                source.getCostTour().getId() != null &&
                !source.getCostTour().getId().isEmpty()) {
            itineraryItemDomain.setCostTour( costTourConverter.toDomain( source.getCostTour() ) );
        }else{
            itineraryItemDomain.setCostTour( null );
        }
        if (source.getCostTourServiceItem() != null &&
                source.getCostTourServiceItem().getId() != null &&
                !source.getCostTourServiceItem().getId().isEmpty()) {
            itineraryItemDomain.setCostTourServiceItem( costTourServiceItemConverter.toDomain( source.getCostTourServiceItem() ) );
        }else{
            itineraryItemDomain.setCostTourServiceItem( null );
        }
        if (source.getRegisterBookingItemOperator() != null &&
                source.getRegisterBookingItemOperator().getId() != null &&
                !source.getRegisterBookingItemOperator().getId().isEmpty()) {
            itineraryItemDomain.setRegisterBookingItemOperator( registerBookingItemOperatorConverter.toDomain( source.getRegisterBookingItemOperator() ) );
        }else{
            itineraryItemDomain.setRegisterBookingItemOperator( null );
        }

        if (source.getRegisterBookingItemLodger() != null &&
                source.getRegisterBookingItemLodger().getId() != null &&
                !source.getRegisterBookingItemLodger().getId().isEmpty()) {
            itineraryItemDomain.setRegisterBookingItemLodger( registerBookingItemLodgerConverter.toDomain( source.getRegisterBookingItemLodger() ) );
        }else{
            itineraryItemDomain.setRegisterBookingItemLodger( null );
        }

        itineraryItemDomain.setLodgingCantSwb( source.getLodgingCantSwb() );
        itineraryItemDomain.setLodgingCantMat( source.getLodgingCantMat() );
        itineraryItemDomain.setLodgingCantTwin( source.getLodgingCantTwin() );
        itineraryItemDomain.setLodgingCantTrp( source.getLodgingCantTrp() );
        if (source.getLodgingCategory() != null &&
                source.getLodgingCategory().getId() != null &&
                !source.getLodgingCategory().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCategory( lodgingCategoryConverter.toDomain( source.getLodgingCategory() ) );
        }else{
            itineraryItemDomain.setLodgingCategory( null );
        }

        itineraryItemDomain.setLodgingCategoryStr( source.getLodgingCategoryStr() );
        itineraryItemDomain.setLodgingCantBreakfast( source.getLodgingCantBreakfast() );
        itineraryItemDomain.setLodgingCantLunch( source.getLodgingCantLunch() );
        itineraryItemDomain.setLodgingCantDinner( source.getLodgingCantDinner() );
        itineraryItemDomain.setLodgingCantBoxLunch( source.getLodgingCantBoxLunch() );
        itineraryItemDomain.setLodgingCantBreakfastDateTime( source.getLodgingCantBreakfastDateTime() );
        itineraryItemDomain.setLodgingCantLunchDateTime( source.getLodgingCantLunchDateTime() );
        itineraryItemDomain.setLodgingCantDinnerDateTime( source.getLodgingCantDinnerDateTime() );
        itineraryItemDomain.setLodgingCantBoxLunchDateTime( source.getLodgingCantBoxLunchDateTime() );
        itineraryItemDomain.setLodgingCantBreakfastDate( source.getLodgingCantBreakfastDate() );
        itineraryItemDomain.setLodgingCantLunchDate( source.getLodgingCantLunchDate() );
        itineraryItemDomain.setLodgingCantDinnerDate( source.getLodgingCantDinnerDate() );
        itineraryItemDomain.setLodgingCantBoxLunchDate( source.getLodgingCantBoxLunchDate() );
        if (source.getLodgingCantBreakfastItineraryDate() != null &&
                source.getLodgingCantBreakfastItineraryDate().getId() != null &&
                !source.getLodgingCantBreakfastItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantBreakfastItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantBreakfastItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantBreakfastItineraryDate( null );
        }
        if (source.getLodgingCantLunchItineraryDate() != null &&
                source.getLodgingCantLunchItineraryDate().getId() != null &&
                !source.getLodgingCantLunchItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantLunchItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantLunchItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantLunchItineraryDate( null );
        }
        if (source.getLodgingCantDinnerItineraryDate() != null &&
                source.getLodgingCantDinnerItineraryDate().getId() != null &&
                !source.getLodgingCantDinnerItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantDinnerItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantDinnerItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantDinnerItineraryDate( null );
        }
        if (source.getLodgingCantBoxLunchItineraryDate() != null &&
                source.getLodgingCantBoxLunchItineraryDate().getId() != null &&
                !source.getLodgingCantBoxLunchItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantBoxLunchItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantBoxLunchItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantBoxLunchItineraryDate( null );
        }

        itineraryItemDomain.setLodgingCantNight( source.getLodgingCantNight() );
        itineraryItemDomain.setLodgingCantBreakfastTcGuide( source.getLodgingCantBreakfastTcGuide() );
        itineraryItemDomain.setLodgingCantLunchTcGuide( source.getLodgingCantLunchTcGuide() );
        itineraryItemDomain.setLodgingCantDinnerTcGuide( source.getLodgingCantDinnerTcGuide() );
        itineraryItemDomain.setLodgingCantBoxLunchTcGuide( source.getLodgingCantBoxLunchTcGuide() );
        itineraryItemDomain.setLodgingCantBreakfastTcGuideDateTime( source.getLodgingCantBreakfastTcGuideDateTime() );
        itineraryItemDomain.setLodgingCantLunchTcGuideDateTime( source.getLodgingCantLunchTcGuideDateTime() );
        itineraryItemDomain.setLodgingCantDinnerTcGuideDateTime( source.getLodgingCantDinnerTcGuideDateTime() );
        itineraryItemDomain.setLodgingCantBoxLunchTcGuideDateTime( source.getLodgingCantBoxLunchTcGuideDateTime() );
        itineraryItemDomain.setLodgingCantBreakfastTcGuideDate( source.getLodgingCantBreakfastTcGuideDate() );
        itineraryItemDomain.setLodgingCantLunchTcGuideDate( source.getLodgingCantLunchTcGuideDate() );
        itineraryItemDomain.setLodgingCantDinnerTcGuideDate( source.getLodgingCantDinnerTcGuideDate() );
        itineraryItemDomain.setLodgingCantBoxLunchTcGuideDate( source.getLodgingCantBoxLunchTcGuideDate() );
        if (source.getLodgingCantBreakfastTcGuideItineraryDate() != null &&
                source.getLodgingCantBreakfastTcGuideItineraryDate().getId() != null &&
                !source.getLodgingCantBreakfastTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantBreakfastTcGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantBreakfastTcGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantBreakfastTcGuideItineraryDate( null );
        }
        if (source.getLodgingCantLunchTcGuideItineraryDate() != null &&
                source.getLodgingCantLunchTcGuideItineraryDate().getId() != null &&
                !source.getLodgingCantLunchTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantLunchTcGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantLunchTcGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantLunchTcGuideItineraryDate( null );
        }
        if (source.getLodgingCantDinnerTcGuideItineraryDate() != null &&
                source.getLodgingCantDinnerTcGuideItineraryDate().getId() != null &&
                !source.getLodgingCantDinnerTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantDinnerTcGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantDinnerTcGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantDinnerTcGuideItineraryDate( null );
        }
        if (source.getLodgingCantBoxLunchTcGuideItineraryDate() != null &&
                source.getLodgingCantBoxLunchTcGuideItineraryDate().getId() != null &&
                !source.getLodgingCantBoxLunchTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantBoxLunchTcGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantBoxLunchTcGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantBoxLunchTcGuideItineraryDate( null );
        }

        itineraryItemDomain.setLodgingCantSwbTcGuide( source.getLodgingCantSwbTcGuide() );
        itineraryItemDomain.setLodgingCantTwinTcGuide( source.getLodgingCantTwinTcGuide() );
        itineraryItemDomain.setLodgingCantBreakfastTcLocalGuide( source.getLodgingCantBreakfastTcLocalGuide() );
        itineraryItemDomain.setLodgingCantLunchTcLocalGuide( source.getLodgingCantLunchTcLocalGuide() );
        itineraryItemDomain.setLodgingCantDinnerTcLocalGuide( source.getLodgingCantDinnerTcLocalGuide() );
        itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuide( source.getLodgingCantBoxLunchTcLocalGuide() );
        itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideDateTime( source.getLodgingCantBreakfastTcLocalGuideDateTime() );
        itineraryItemDomain.setLodgingCantLunchTcLocalGuideDateTime( source.getLodgingCantLunchTcLocalGuideDateTime() );
        itineraryItemDomain.setLodgingCantDinnerTcLocalGuideDateTime( source.getLodgingCantDinnerTcLocalGuideDateTime() );
        itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideDateTime( source.getLodgingCantBoxLunchTcLocalGuideDateTime() );
        itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideDate( source.getLodgingCantBreakfastTcLocalGuideDate() );
        itineraryItemDomain.setLodgingCantLunchTcLocalGuideDate( source.getLodgingCantLunchTcLocalGuideDate() );
        itineraryItemDomain.setLodgingCantDinnerTcLocalGuideDate( source.getLodgingCantDinnerTcLocalGuideDate() );
        itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideDate( source.getLodgingCantBoxLunchTcLocalGuideDate() );
        if (source.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null &&
                source.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantBreakfastTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideItineraryDate( null );
        }
        if (source.getLodgingCantLunchTcLocalGuideItineraryDate() != null &&
                source.getLodgingCantLunchTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgingCantLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantLunchTcLocalGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantLunchTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantLunchTcLocalGuideItineraryDate( null );
        }
        if (source.getLodgingCantDinnerTcLocalGuideItineraryDate() != null &&
                source.getLodgingCantDinnerTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgingCantDinnerTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantDinnerTcLocalGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantDinnerTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantDinnerTcLocalGuideItineraryDate( null );
        }
        if (source.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null &&
                source.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantBoxLunchTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideItineraryDate( null );
        }

        itineraryItemDomain.setLodgingCantSwbTcLocalGuide( source.getLodgingCantSwbTcLocalGuide() );
        itineraryItemDomain.setLodgingCantTwinTcLocalGuide( source.getLodgingCantTwinTcLocalGuide() );
        itineraryItemDomain.setLodgingCantBreakfastGuide( source.getLodgingCantBreakfastGuide() );
        itineraryItemDomain.setLodgingCantLunchGuide( source.getLodgingCantLunchGuide() );
        itineraryItemDomain.setLodgingCantDinnerGuide( source.getLodgingCantDinnerGuide() );
        itineraryItemDomain.setLodgingCantBoxLunchGuide( source.getLodgingCantBoxLunchGuide() );
        itineraryItemDomain.setLodgingCantBreakfastGuideDateTime( source.getLodgingCantBreakfastGuideDateTime() );
        itineraryItemDomain.setLodgingCantLunchGuideDateTime( source.getLodgingCantLunchGuideDateTime() );
        itineraryItemDomain.setLodgingCantDinnerGuideDateTime( source.getLodgingCantDinnerGuideDateTime() );
        itineraryItemDomain.setLodgingCantBoxLunchGuideDateTime( source.getLodgingCantBoxLunchGuideDateTime() );
        itineraryItemDomain.setLodgingCantBreakfastGuideDate( source.getLodgingCantBreakfastGuideDate() );
        itineraryItemDomain.setLodgingCantLunchGuideDate( source.getLodgingCantLunchGuideDate() );
        itineraryItemDomain.setLodgingCantDinnerGuideDate( source.getLodgingCantDinnerGuideDate() );
        itineraryItemDomain.setLodgingCantBoxLunchGuideDate( source.getLodgingCantBoxLunchGuideDate() );
        if (source.getLodgingCantBreakfastGuideItineraryDate() != null &&
                source.getLodgingCantBreakfastGuideItineraryDate().getId() != null &&
                !source.getLodgingCantBreakfastGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantBreakfastGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantBreakfastGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantBreakfastGuideItineraryDate( null );
        }
        if (source.getLodgingCantLunchGuideItineraryDate() != null &&
                source.getLodgingCantLunchGuideItineraryDate().getId() != null &&
                !source.getLodgingCantLunchGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantLunchGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantLunchGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantLunchGuideItineraryDate( null );
        }
        if (source.getLodgingCantDinnerGuideItineraryDate() != null &&
                source.getLodgingCantDinnerGuideItineraryDate().getId() != null &&
                !source.getLodgingCantDinnerGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantDinnerGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantDinnerGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantDinnerGuideItineraryDate( null );
        }
        if (source.getLodgingCantBoxLunchGuideItineraryDate() != null &&
                source.getLodgingCantBoxLunchGuideItineraryDate().getId() != null &&
                !source.getLodgingCantBoxLunchGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgingCantBoxLunchGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgingCantBoxLunchGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgingCantBoxLunchGuideItineraryDate( null );
        }

        itineraryItemDomain.setLodgingCantSwbGuide( source.getLodgingCantSwbGuide() );
        itineraryItemDomain.setLodgingCantTwinGuide( source.getLodgingCantTwinGuide() );
        itineraryItemDomain.setLodgingGuideCantNight( source.getLodgingGuideCantNight() );
        itineraryItemDomain.setLodgeCantSwb( source.getLodgeCantSwb() );
        itineraryItemDomain.setLodgeCantMat( source.getLodgeCantMat() );
        itineraryItemDomain.setLodgeCantTwin( source.getLodgeCantTwin() );
        itineraryItemDomain.setLodgeCantTrp( source.getLodgeCantTrp() );
        if (source.getLodgeCategory() != null &&
                source.getLodgeCategory().getId() != null &&
                !source.getLodgeCategory().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCategory( lodgingCategoryConverter.toDomain( source.getLodgeCategory() ) );
        }else{
            itineraryItemDomain.setLodgeCategory( null );
        }

        itineraryItemDomain.setLodgeCategoryStr( source.getLodgeCategoryStr() );
        itineraryItemDomain.setLodgeCantBreakfast( source.getLodgeCantBreakfast() );
        itineraryItemDomain.setLodgeCantLunch( source.getLodgeCantLunch() );
        itineraryItemDomain.setLodgeCantDinner( source.getLodgeCantDinner() );
        itineraryItemDomain.setLodgeCantBoxLunch( source.getLodgeCantBoxLunch() );
        itineraryItemDomain.setLodgeCantBreakfastDateTime( source.getLodgeCantBreakfastDateTime() );
        itineraryItemDomain.setLodgeCantLunchDateTime( source.getLodgeCantLunchDateTime() );
        itineraryItemDomain.setLodgeCantDinnerDateTime( source.getLodgeCantDinnerDateTime() );
        itineraryItemDomain.setLodgeCantBoxLunchDateTime( source.getLodgeCantBoxLunchDateTime() );
        itineraryItemDomain.setLodgeCantBreakfastDate( source.getLodgeCantBreakfastDate() );
        itineraryItemDomain.setLodgeCantLunchDate( source.getLodgeCantLunchDate() );
        itineraryItemDomain.setLodgeCantDinnerDate( source.getLodgeCantDinnerDate() );
        itineraryItemDomain.setLodgeCantBoxLunchDate( source.getLodgeCantBoxLunchDate() );
        if (source.getLodgeCantBreakfastItineraryDate() != null &&
                source.getLodgeCantBreakfastItineraryDate().getId() != null &&
                !source.getLodgeCantBreakfastItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantBreakfastItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantBreakfastItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantBreakfastItineraryDate( null );
        }
        if (source.getLodgeCantLunchItineraryDate() != null &&
                source.getLodgeCantLunchItineraryDate().getId() != null &&
                !source.getLodgeCantLunchItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantLunchItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantLunchItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantLunchItineraryDate( null );
        }
        if (source.getLodgeCantDinnerItineraryDate() != null &&
                source.getLodgeCantDinnerItineraryDate().getId() != null &&
                !source.getLodgeCantDinnerItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantDinnerItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantDinnerItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantDinnerItineraryDate( null );
        }
        if (source.getLodgeCantBoxLunchItineraryDate() != null &&
                source.getLodgeCantBoxLunchItineraryDate().getId() != null &&
                !source.getLodgeCantBoxLunchItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantBoxLunchItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantBoxLunchItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantBoxLunchItineraryDate( null );
        }

        itineraryItemDomain.setLodgeCantNight( source.getLodgeCantNight() );
        itineraryItemDomain.setLodgeCantBreakfastTcGuide( source.getLodgeCantBreakfastTcGuide() );
        itineraryItemDomain.setLodgeCantLunchTcGuide( source.getLodgeCantLunchTcGuide() );
        itineraryItemDomain.setLodgeCantDinnerTcGuide( source.getLodgeCantDinnerTcGuide() );
        itineraryItemDomain.setLodgeCantBoxLunchTcGuide( source.getLodgeCantBoxLunchTcGuide() );
        itineraryItemDomain.setLodgeCantBreakfastTcGuideDateTime( source.getLodgeCantBreakfastTcGuideDateTime() );
        itineraryItemDomain.setLodgeCantLunchTcGuideDateTime( source.getLodgeCantLunchTcGuideDateTime() );
        itineraryItemDomain.setLodgeCantDinnerTcGuideDateTime( source.getLodgeCantDinnerTcGuideDateTime() );
        itineraryItemDomain.setLodgeCantBoxLunchTcGuideDateTime( source.getLodgeCantBoxLunchTcGuideDateTime() );
        itineraryItemDomain.setLodgeCantBreakfastTcGuideDate( source.getLodgeCantBreakfastTcGuideDate() );
        itineraryItemDomain.setLodgeCantLunchTcGuideDate( source.getLodgeCantLunchTcGuideDate() );
        itineraryItemDomain.setLodgeCantDinnerTcGuideDate( source.getLodgeCantDinnerTcGuideDate() );
        itineraryItemDomain.setLodgeCantBoxLunchTcGuideDate( source.getLodgeCantBoxLunchTcGuideDate() );
        if (source.getLodgeCantBreakfastTcGuideItineraryDate() != null &&
                source.getLodgeCantBreakfastTcGuideItineraryDate().getId() != null &&
                !source.getLodgeCantBreakfastTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantBreakfastTcGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantBreakfastTcGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantBreakfastTcGuideItineraryDate( null );
        }
        if (source.getLodgeCantLunchTcGuideItineraryDate() != null &&
                source.getLodgeCantLunchTcGuideItineraryDate().getId() != null &&
                !source.getLodgeCantLunchTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantLunchTcGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantLunchTcGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantLunchTcGuideItineraryDate( null );
        }
        if (source.getLodgeCantDinnerTcGuideItineraryDate() != null &&
                source.getLodgeCantDinnerTcGuideItineraryDate().getId() != null &&
                !source.getLodgeCantDinnerTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantDinnerTcGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantDinnerTcGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantDinnerTcGuideItineraryDate( null );
        }
        if (source.getLodgeCantBoxLunchTcGuideItineraryDate() != null &&
                source.getLodgeCantBoxLunchTcGuideItineraryDate().getId() != null &&
                !source.getLodgeCantBoxLunchTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantBoxLunchTcGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantBoxLunchTcGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantBoxLunchTcGuideItineraryDate( null );
        }

        itineraryItemDomain.setLodgeCantSwbTcGuide( source.getLodgeCantSwbTcGuide() );
        itineraryItemDomain.setLodgeCantTwinTcGuide( source.getLodgeCantTwinTcGuide() );
        itineraryItemDomain.setLodgeTcCantNight( source.getLodgeTcCantNight() );
        itineraryItemDomain.setLodgeCantBreakfastTcLocalGuide( source.getLodgeCantBreakfastTcLocalGuide() );
        itineraryItemDomain.setLodgeCantLunchTcLocalGuide( source.getLodgeCantLunchTcLocalGuide() );
        itineraryItemDomain.setLodgeCantDinnerTcLocalGuide( source.getLodgeCantDinnerTcLocalGuide() );
        itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuide( source.getLodgeCantBoxLunchTcLocalGuide() );
        itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideDateTime( source.getLodgeCantBreakfastTcLocalGuideDateTime() );
        itineraryItemDomain.setLodgeCantLunchTcLocalGuideDateTime( source.getLodgeCantLunchTcLocalGuideDateTime() );
        itineraryItemDomain.setLodgeCantDinnerTcLocalGuideDateTime( source.getLodgeCantDinnerTcLocalGuideDateTime() );
        itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideDateTime( source.getLodgeCantBoxLunchTcLocalGuideDateTime() );
        itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideDate( source.getLodgeCantBreakfastTcLocalGuideDate() );
        itineraryItemDomain.setLodgeCantLunchTcLocalGuideDate( source.getLodgeCantLunchTcLocalGuideDate() );
        itineraryItemDomain.setLodgeCantDinnerTcLocalGuideDate( source.getLodgeCantDinnerTcLocalGuideDate() );
        itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideDate( source.getLodgeCantBoxLunchTcLocalGuideDate() );
        if (source.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null &&
                source.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantBreakfastTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideItineraryDate( null );
        }
        if (source.getLodgeCantLunchTcLocalGuideItineraryDate() != null &&
                source.getLodgeCantLunchTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgeCantLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantLunchTcLocalGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantLunchTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantLunchTcLocalGuideItineraryDate( null );
        }
        if (source.getLodgeCantDinnerTcLocalGuideItineraryDate() != null &&
                source.getLodgeCantDinnerTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgeCantDinnerTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantDinnerTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuideItineraryDate( null );
        }
        if (source.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null &&
                source.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantBoxLunchTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideItineraryDate( null );
        }

        itineraryItemDomain.setLodgeCantSwbTcLocalGuide( source.getLodgeCantSwbTcLocalGuide() );
        itineraryItemDomain.setLodgeCantTwinTcLocalGuide( source.getLodgeCantTwinTcLocalGuide() );
        itineraryItemDomain.setLodgeTcLocalCantNight( source.getLodgeTcLocalCantNight() );
        itineraryItemDomain.setLodgeCantBreakfastGuide( source.getLodgeCantBreakfastGuide() );
        itineraryItemDomain.setLodgeCantLunchGuide( source.getLodgeCantLunchGuide() );
        itineraryItemDomain.setLodgeCantDinnerGuide( source.getLodgeCantDinnerGuide() );
        itineraryItemDomain.setLodgeCantBoxLunchGuide( source.getLodgeCantBoxLunchGuide() );
        itineraryItemDomain.setLodgeCantBreakfastGuideDateTime( source.getLodgeCantBreakfastGuideDateTime() );
        itineraryItemDomain.setLodgeCantLunchGuideDateTime( source.getLodgeCantLunchGuideDateTime() );
        itineraryItemDomain.setLodgeCantDinnerGuideDateTime( source.getLodgeCantDinnerGuideDateTime() );
        itineraryItemDomain.setLodgeCantBoxLunchGuideDateTime( source.getLodgeCantBoxLunchGuideDateTime() );
        itineraryItemDomain.setLodgeCantBreakfastGuideDate( source.getLodgeCantBreakfastGuideDate() );
        itineraryItemDomain.setLodgeCantLunchGuideDate( source.getLodgeCantLunchGuideDate() );
        itineraryItemDomain.setLodgeCantDinnerGuideDate( source.getLodgeCantDinnerGuideDate() );
        itineraryItemDomain.setLodgeCantBoxLunchGuideDate( source.getLodgeCantBoxLunchGuideDate() );
        if (source.getLodgeCantBreakfastGuideItineraryDate() != null &&
                source.getLodgeCantBreakfastGuideItineraryDate().getId() != null &&
                !source.getLodgeCantBreakfastGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantBreakfastGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantBreakfastGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantBreakfastGuideItineraryDate( null );
        }
        if (source.getLodgeCantLunchGuideItineraryDate() != null &&
                source.getLodgeCantLunchGuideItineraryDate().getId() != null &&
                !source.getLodgeCantLunchGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantLunchGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantLunchGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantLunchGuideItineraryDate( null );
        }
        if (source.getLodgeCantDinnerGuideItineraryDate() != null &&
                source.getLodgeCantDinnerGuideItineraryDate().getId() != null &&
                !source.getLodgeCantDinnerGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantDinnerGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantDinnerGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantDinnerGuideItineraryDate( null );
        }
        if (source.getLodgeCantBoxLunchGuideItineraryDate() != null &&
                source.getLodgeCantBoxLunchGuideItineraryDate().getId() != null &&
                !source.getLodgeCantBoxLunchGuideItineraryDate().getId().isEmpty()) {
            itineraryItemDomain.setLodgeCantBoxLunchGuideItineraryDate( itineraryDateConverter.toDomain( source.getLodgeCantBoxLunchGuideItineraryDate() ) );
        }else{
            itineraryItemDomain.setLodgeCantBoxLunchGuideItineraryDate( null );
        }
        itineraryItemDomain.setLodgeCantSwbGuide( source.getLodgeCantSwbGuide() );
        itineraryItemDomain.setLodgeCantTwinGuide( source.getLodgeCantTwinGuide() );
        itineraryItemDomain.setLodgeGuideCantNight( source.getLodgeGuideCantNight() );
        itineraryItemDomain.setLodgePvt( source.getLodgePvt() );
        itineraryItemDomain.setLodgeSic( source.getLodgeSic() );
        itineraryItemDomain.setRestaurantCantBreakfast( source.getRestaurantCantBreakfast() );
        itineraryItemDomain.setRestaurantCantLunch( source.getRestaurantCantLunch() );
        itineraryItemDomain.setRestaurantCantDinner( source.getRestaurantCantDinner() );
        itineraryItemDomain.setRestaurantCantBoxLunch( source.getRestaurantCantBoxLunch() );
        itineraryItemDomain.setRestaurantCantBreakfastTcGuide( source.getRestaurantCantBreakfastTcGuide() );
        itineraryItemDomain.setRestaurantCantLunchTcGuide( source.getRestaurantCantLunchTcGuide() );
        itineraryItemDomain.setRestaurantCantDinnerTcGuide( source.getRestaurantCantDinnerTcGuide() );
        itineraryItemDomain.setRestaurantCantBoxLunchTcGuide( source.getRestaurantCantBoxLunchTcGuide() );
        itineraryItemDomain.setRestaurantCantBreakfastTcLocalGuide( source.getRestaurantCantBreakfastTcLocalGuide() );
        itineraryItemDomain.setRestaurantCantLunchTcLocalGuide( source.getRestaurantCantLunchTcLocalGuide() );
        itineraryItemDomain.setRestaurantCantDinnerTcLocalGuide( source.getRestaurantCantDinnerTcLocalGuide() );
        itineraryItemDomain.setRestaurantCantBoxLunchTcLocalGuide( source.getRestaurantCantBoxLunchTcLocalGuide() );
        itineraryItemDomain.setRestaurantCantBreakfastGuide( source.getRestaurantCantBreakfastGuide() );
        itineraryItemDomain.setRestaurantCantLunchGuide( source.getRestaurantCantLunchGuide() );
        itineraryItemDomain.setRestaurantCantDinnerGuide( source.getRestaurantCantDinnerGuide() );
        itineraryItemDomain.setRestaurantCantBoxLunchGuide( source.getRestaurantCantBoxLunchGuide() );
        itineraryItemDomain.setRestaurantCantBreakfastDrive( source.getRestaurantCantBreakfastDrive() );
        itineraryItemDomain.setRestaurantCantLunchDrive( source.getRestaurantCantLunchDrive() );
        itineraryItemDomain.setRestaurantCantDinnerDrive( source.getRestaurantCantDinnerDrive() );
        itineraryItemDomain.setRestaurantCantBoxLunchDrive( source.getRestaurantCantBoxLunchDrive() );
        itineraryItemDomain.setOperatorPvt( source.getOperatorPvt() );
        itineraryItemDomain.setOperatorSic( source.getOperatorSic() );
        if (source.getLodgingForOperator() != null &&
                source.getLodgingForOperator().getId() != null &&
                !source.getLodgingForOperator().getId().isEmpty()) {
            itineraryItemDomain.setLodgingForOperator( supplierLodgingConverter.toDomain( source.getLodgingForOperator() ) );
        }else{
            itineraryItemDomain.setLodgingForOperator( null );
        }
        itineraryItemDomain.setIdItineraryItemQuotation( source.getIdItineraryItemQuotation() );
        itineraryItemDomain.setLodgingForOperatorStr( source.getLodgingForOperatorStr() );
        itineraryItemDomain.setStateItineraryService( source.getStateItineraryService() );
        itineraryItemDomain.setNote( source.getNote() );

        itineraryItemDomain.setStateServiceInvoice( source.getStateServiceInvoice() );
        itineraryItemDomain.setNoteInvoice( source.getNoteInvoice() );
        itineraryItemDomain.setDocumentDateInvoice( source.getDocumentDateInvoice() );
        itineraryItemDomain.setServiceForOperation( source.getServiceForOperation() );
        itineraryItemDomain.setGeneratedBy( source.getGeneratedBy() );
        return itineraryItemDomain;
    }

    @Override
    public ItineraryItemEntity toEntity(ItineraryItemDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ItineraryItemEntity itineraryItemEntity = new ItineraryItemEntity();

        itineraryItemEntity.setId( source.getId() );
        itineraryItemEntity.setDeleted( source.getDeleted() );
        itineraryItemEntity.setEnabled( source.getEnabled() );
        itineraryItemEntity.setMigrated( source.getMigrated() );
        itineraryItemEntity.setCreatedAt( source.getCreatedAt() );
        itineraryItemEntity.setUpdatedAt( source.getUpdatedAt() );
        itineraryItemEntity.setDeletedAt( source.getDeletedAt() );
        itineraryItemEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        itineraryItemEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        itineraryItemEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        itineraryItemEntity.setIdentifierName( source.getIdentifierName() );
        itineraryItemEntity.setTenant( source.getTenant() );
        itineraryItemEntity.setName( source.getName() );
        itineraryItemEntity.setDescription( source.getDescription() );
        itineraryItemEntity.setIdentify( source.getIdentify() );
        itineraryItemEntity.setObservation( source.getObservation() );
        itineraryItemEntity.setServiceGroup( source.getServiceGroup() );
        itineraryItemEntity.setItineraryDateTime( source.getItineraryDateTime() );
        itineraryItemEntity.setIndex( source.getIndex() );
        itineraryItemEntity.setPaxTotal( source.getPaxTotal() );
        itineraryItemEntity.setPrice( source.getPrice() );
        itineraryItemEntity.setPaxAdultTotal( source.getPaxAdultTotal() );
        itineraryItemEntity.setPriceAdult( source.getPriceAdult() );
        itineraryItemEntity.setPaxChildrenTotal( source.getPaxChildrenTotal() );
        itineraryItemEntity.setPriceChildren( source.getPriceChildren() );
        itineraryItemEntity.setPaxTcTotal( source.getPaxTcTotal() );
        itineraryItemEntity.setPriceTc( source.getPriceTc() );
        itineraryItemEntity.setPaxTcLocalTotal( source.getPaxTcLocalTotal() );
        itineraryItemEntity.setPriceTcLocal( source.getPriceTcLocal() );
        itineraryItemEntity.setPaxGuideTotal( source.getPaxGuideTotal() );
        itineraryItemEntity.setPriceGuide( source.getPriceGuide() );
        if (source.getItineraryDate() != null &&
                source.getItineraryDate().getId() != null &&
                !source.getItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setItineraryDate( itineraryDateConverter.toEntity( source.getItineraryDate() ) );
        }else{
            itineraryItemEntity.setItineraryDate( null );
        }
        if (source.getRegisterGroup() != null &&
                source.getRegisterGroup().getId() != null &&
                !source.getRegisterGroup().getId().isEmpty()) {
            itineraryItemEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );
        }else{
            itineraryItemEntity.setRegisterGroup( null );
        }
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            itineraryItemEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        }else{
            itineraryItemEntity.setSupplierType( null );
        }
        if (source.getSupplier() != null &&
                source.getSupplier().getId() != null &&
                !source.getSupplier().getId().isEmpty()) {
            itineraryItemEntity.setSupplier( supplierConverter.toEntity( source.getSupplier() ) );
        }else{
            itineraryItemEntity.setSupplier( null );
        }
        if (source.getService() != null &&
                source.getService().getId() != null &&
                !source.getService().getId().isEmpty()) {
            itineraryItemEntity.setService( serviceConverter.toEntity( source.getService() ) );
        }else{
            itineraryItemEntity.setService( null );
        }
        if (source.getDepartmentRegion() != null &&
                source.getDepartmentRegion().getId() != null &&
                !source.getDepartmentRegion().getId().isEmpty()) {
            itineraryItemEntity.setDepartmentRegion( departmentRegionConverter.toEntity( source.getDepartmentRegion() ) );
        }else{
            itineraryItemEntity.setDepartmentRegion( null );
        }
        if (source.getRegisterBookingBase() != null &&
                source.getRegisterBookingBase().getId() != null &&
                !source.getRegisterBookingBase().getId().isEmpty()) {
            itineraryItemEntity.setRegisterBookingBase( registerBookingBaseConverter.toEntity( source.getRegisterBookingBase() ) );
        }else{
            itineraryItemEntity.setRegisterBookingBase( null );
        }
        if (source.getGuideTransport() != null &&
                source.getGuideTransport().getId() != null &&
                !source.getGuideTransport().getId().isEmpty()) {
            itineraryItemEntity.setGuideTransport(guideConverter.toEntity(source.getGuideTransport()));
        }else{
            itineraryItemEntity.setGuideTransport( null );
        }
        if (source.getGuideTransferDrive() != null &&
                source.getGuideTransferDrive().getId() != null &&
                !source.getGuideTransferDrive().getId().isEmpty()) {
            itineraryItemEntity.setGuideTransferDrive( guideConverter.toEntity(source.getGuideTransferDrive() ) );
        }else{
            itineraryItemEntity.setGuideTransferDrive( null );
        }
        if (source.getTour() != null &&
                source.getTour().getId() != null &&
                !source.getTour().getId().isEmpty()) {
            itineraryItemEntity.setTour( tourConverter.toEntity(source.getTour() ) );
        }else{
            itineraryItemEntity.setTour( null );
        }
        if (source.getCustomer() != null &&
                source.getCustomer().getId() != null &&
                !source.getCustomer().getId().isEmpty()) {
            itineraryItemEntity.setCustomer( customerConverter.toEntity(source.getCustomer() ) );
        }else{
            itineraryItemEntity.setCustomer( null );
        }
        if (source.getOrderServiceItem() != null &&
                source.getOrderServiceItem().getId() != null &&
                !source.getOrderServiceItem().getId().isEmpty()) {
            itineraryItemEntity.setOrderServiceItem( orderServiceItemConverter.toEntity( source.getOrderServiceItem() ) );
        }else{
            itineraryItemEntity.setOrderServiceItem( null );
        }
        if (source.getRegisterBookingItemOperator() != null &&
                source.getRegisterBookingItemOperator().getId() != null &&
                !source.getRegisterBookingItemOperator().getId().isEmpty()) {
            itineraryItemEntity.setRegisterBookingItemOperator( registerBookingItemOperatorConverter.toEntity( source.getRegisterBookingItemOperator() ) );
        }else{
            itineraryItemEntity.setRegisterBookingItemOperator( null );
        }
        if (source.getRegisterBookingItemLodger() != null &&
                source.getRegisterBookingItemLodger().getId() != null &&
                !source.getRegisterBookingItemLodger().getId().isEmpty()) {
            itineraryItemEntity.setRegisterBookingItemLodger( registerBookingItemLodgerConverter.toEntity( source.getRegisterBookingItemLodger() ) );
        }else{
            itineraryItemEntity.setRegisterBookingItemLodger( null );
        }
        if (source.getCostTour() != null &&
                source.getCostTour().getId() != null &&
                !source.getCostTour().getId().isEmpty()) {
            itineraryItemEntity.setCostTour( costTourConverter.toEntity( source.getCostTour() ) );
        }else{
            itineraryItemEntity.setCostTour( null );
        }
        if (source.getCostTourServiceItem() != null &&
                source.getCostTourServiceItem().getId() != null &&
                !source.getCostTourServiceItem().getId().isEmpty()) {
            itineraryItemEntity.setCostTourServiceItem( costTourServiceItemConverter.toEntity( source.getCostTourServiceItem() ) );
        }else{
            itineraryItemEntity.setCostTourServiceItem( null );
        }
        itineraryItemEntity.setLodgingCantSwb( source.getLodgingCantSwb() );
        itineraryItemEntity.setLodgingCantMat( source.getLodgingCantMat() );
        itineraryItemEntity.setLodgingCantTwin( source.getLodgingCantTwin() );
        itineraryItemEntity.setLodgingCantTrp( source.getLodgingCantTrp() );
        if (source.getLodgingCategory() != null &&
                source.getLodgingCategory().getId() != null &&
                !source.getLodgingCategory().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCategory( lodgingCategoryConverter.toEntity( source.getLodgingCategory() ) );
        }else{
            itineraryItemEntity.setLodgingCategory( null );
        }
        itineraryItemEntity.setLodgingCategoryStr( source.getLodgingCategoryStr() );
        itineraryItemEntity.setLodgingCantBreakfast( source.getLodgingCantBreakfast() );
        itineraryItemEntity.setLodgingCantLunch( source.getLodgingCantLunch() );
        itineraryItemEntity.setLodgingCantDinner( source.getLodgingCantDinner() );
        itineraryItemEntity.setLodgingCantBoxLunch( source.getLodgingCantBoxLunch() );
        itineraryItemEntity.setLodgingCantBreakfastDateTime( source.getLodgingCantBreakfastDateTime() );
        itineraryItemEntity.setLodgingCantLunchDateTime( source.getLodgingCantLunchDateTime() );
        itineraryItemEntity.setLodgingCantDinnerDateTime( source.getLodgingCantDinnerDateTime() );
        itineraryItemEntity.setLodgingCantBoxLunchDateTime( source.getLodgingCantBoxLunchDateTime() );
        itineraryItemEntity.setLodgingCantBreakfastDate( source.getLodgingCantBreakfastDate() );
        itineraryItemEntity.setLodgingCantLunchDate( source.getLodgingCantLunchDate() );
        itineraryItemEntity.setLodgingCantDinnerDate( source.getLodgingCantDinnerDate() );
        itineraryItemEntity.setLodgingCantBoxLunchDate( source.getLodgingCantBoxLunchDate() );

        if (source.getLodgingCantBreakfastItineraryDate() != null &&
                source.getLodgingCantBreakfastItineraryDate().getId() != null &&
                !source.getLodgingCantBreakfastItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantBreakfastItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantBreakfastItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantBreakfastItineraryDate( null );
        }
        if (source.getLodgingCantLunchItineraryDate() != null &&
                source.getLodgingCantLunchItineraryDate().getId() != null &&
                !source.getLodgingCantLunchItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantLunchItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantLunchItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantLunchItineraryDate( null );
        }
        if (source.getLodgingCantDinnerItineraryDate() != null &&
                source.getLodgingCantDinnerItineraryDate().getId() != null &&
                !source.getLodgingCantDinnerItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantDinnerItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantDinnerItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantDinnerItineraryDate( null );
        }
        if (source.getLodgingCantBoxLunchItineraryDate() != null &&
                source.getLodgingCantBoxLunchItineraryDate().getId() != null &&
                !source.getLodgingCantBoxLunchItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantBoxLunchItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantBoxLunchItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantBoxLunchItineraryDate( null );
        }

        itineraryItemEntity.setLodgingCantNight( source.getLodgingCantNight() );
        itineraryItemEntity.setLodgingCantBreakfastTcGuide( source.getLodgingCantBreakfastTcGuide() );
        itineraryItemEntity.setLodgingCantLunchTcGuide( source.getLodgingCantLunchTcGuide() );
        itineraryItemEntity.setLodgingCantDinnerTcGuide( source.getLodgingCantDinnerTcGuide() );
        itineraryItemEntity.setLodgingCantBoxLunchTcGuide( source.getLodgingCantBoxLunchTcGuide() );
        itineraryItemEntity.setLodgingCantBreakfastTcGuideDateTime( source.getLodgingCantBreakfastTcGuideDateTime() );
        itineraryItemEntity.setLodgingCantLunchTcGuideDateTime( source.getLodgingCantLunchTcGuideDateTime() );
        itineraryItemEntity.setLodgingCantDinnerTcGuideDateTime( source.getLodgingCantDinnerTcGuideDateTime() );
        itineraryItemEntity.setLodgingCantBoxLunchTcGuideDateTime( source.getLodgingCantBoxLunchTcGuideDateTime() );
        itineraryItemEntity.setLodgingCantBreakfastTcGuideDate( source.getLodgingCantBreakfastTcGuideDate() );
        itineraryItemEntity.setLodgingCantLunchTcGuideDate( source.getLodgingCantLunchTcGuideDate() );
        itineraryItemEntity.setLodgingCantDinnerTcGuideDate( source.getLodgingCantDinnerTcGuideDate() );
        itineraryItemEntity.setLodgingCantBoxLunchTcGuideDate( source.getLodgingCantBoxLunchTcGuideDate() );

        if (source.getLodgingCantBreakfastTcGuideItineraryDate() != null &&
                source.getLodgingCantBreakfastTcGuideItineraryDate().getId() != null &&
                !source.getLodgingCantBreakfastTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantBreakfastTcGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantBreakfastTcGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantBreakfastTcGuideItineraryDate( null );
        }
        if (source.getLodgingCantLunchTcGuideItineraryDate() != null &&
                source.getLodgingCantLunchTcGuideItineraryDate().getId() != null &&
                !source.getLodgingCantLunchTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantLunchTcGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantLunchTcGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantLunchTcGuideItineraryDate( null );
        }
        if (source.getLodgingCantDinnerTcGuideItineraryDate() != null &&
                source.getLodgingCantDinnerTcGuideItineraryDate().getId() != null &&
                !source.getLodgingCantDinnerTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantDinnerTcGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantDinnerTcGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantDinnerTcGuideItineraryDate( null );
        }
        if (source.getLodgingCantBoxLunchTcGuideItineraryDate() != null &&
                source.getLodgingCantBoxLunchTcGuideItineraryDate().getId() != null &&
                !source.getLodgingCantBoxLunchTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantBoxLunchTcGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantBoxLunchTcGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantBoxLunchTcGuideItineraryDate( null );
        }

        itineraryItemEntity.setLodgingCantSwbTcGuide( source.getLodgingCantSwbTcGuide() );
        itineraryItemEntity.setLodgingCantTwinTcGuide( source.getLodgingCantTwinTcGuide() );
        itineraryItemEntity.setLodgingCantBreakfastTcLocalGuide( source.getLodgingCantBreakfastTcLocalGuide() );
        itineraryItemEntity.setLodgingCantLunchTcLocalGuide( source.getLodgingCantLunchTcLocalGuide() );
        itineraryItemEntity.setLodgingCantDinnerTcLocalGuide( source.getLodgingCantDinnerTcLocalGuide() );
        itineraryItemEntity.setLodgingCantBoxLunchTcLocalGuide( source.getLodgingCantBoxLunchTcLocalGuide() );
        itineraryItemEntity.setLodgingCantBreakfastTcLocalGuideDateTime( source.getLodgingCantBreakfastTcLocalGuideDateTime() );
        itineraryItemEntity.setLodgingCantLunchTcLocalGuideDateTime( source.getLodgingCantLunchTcLocalGuideDateTime() );
        itineraryItemEntity.setLodgingCantDinnerTcLocalGuideDateTime( source.getLodgingCantDinnerTcLocalGuideDateTime() );
        itineraryItemEntity.setLodgingCantBoxLunchTcLocalGuideDateTime( source.getLodgingCantBoxLunchTcLocalGuideDateTime() );
        itineraryItemEntity.setLodgingCantBreakfastTcLocalGuideDate( source.getLodgingCantBreakfastTcLocalGuideDate() );
        itineraryItemEntity.setLodgingCantLunchTcLocalGuideDate( source.getLodgingCantLunchTcLocalGuideDate() );
        itineraryItemEntity.setLodgingCantDinnerTcLocalGuideDate( source.getLodgingCantDinnerTcLocalGuideDate() );
        itineraryItemEntity.setLodgingCantBoxLunchTcLocalGuideDate( source.getLodgingCantBoxLunchTcLocalGuideDate() );

        if (source.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null &&
                source.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantBreakfastTcLocalGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantBreakfastTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantBreakfastTcLocalGuideItineraryDate( null );
        }
        if (source.getLodgingCantLunchTcLocalGuideItineraryDate() != null &&
                source.getLodgingCantLunchTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgingCantLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantLunchTcLocalGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantLunchTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantLunchTcLocalGuideItineraryDate( null );
        }
        if (source.getLodgingCantDinnerTcLocalGuideItineraryDate() != null &&
                source.getLodgingCantDinnerTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgingCantDinnerTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantDinnerTcLocalGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantDinnerTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantDinnerTcLocalGuideItineraryDate( null );
        }
        if (source.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null &&
                source.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantBoxLunchTcLocalGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantBoxLunchTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantBoxLunchTcLocalGuideItineraryDate( null );
        }

        itineraryItemEntity.setLodgingCantSwbTcLocalGuide( source.getLodgingCantSwbTcLocalGuide() );
        itineraryItemEntity.setLodgingCantTwinTcLocalGuide( source.getLodgingCantTwinTcLocalGuide() );
        itineraryItemEntity.setLodgingCantBreakfastGuide( source.getLodgingCantBreakfastGuide() );
        itineraryItemEntity.setLodgingCantLunchGuide( source.getLodgingCantLunchGuide() );
        itineraryItemEntity.setLodgingCantDinnerGuide( source.getLodgingCantDinnerGuide() );
        itineraryItemEntity.setLodgingCantBoxLunchGuide( source.getLodgingCantBoxLunchGuide() );
        itineraryItemEntity.setLodgingCantBreakfastGuideDateTime( source.getLodgingCantBreakfastGuideDateTime() );
        itineraryItemEntity.setLodgingCantLunchGuideDateTime( source.getLodgingCantLunchGuideDateTime() );
        itineraryItemEntity.setLodgingCantDinnerGuideDateTime( source.getLodgingCantDinnerGuideDateTime() );
        itineraryItemEntity.setLodgingCantBoxLunchGuideDateTime( source.getLodgingCantBoxLunchGuideDateTime() );
        itineraryItemEntity.setLodgingCantBreakfastGuideDate( source.getLodgingCantBreakfastGuideDate() );
        itineraryItemEntity.setLodgingCantLunchGuideDate( source.getLodgingCantLunchGuideDate() );
        itineraryItemEntity.setLodgingCantDinnerGuideDate( source.getLodgingCantDinnerGuideDate() );
        itineraryItemEntity.setLodgingCantBoxLunchGuideDate( source.getLodgingCantBoxLunchGuideDate() );

        if (source.getLodgingCantBreakfastGuideItineraryDate() != null &&
                source.getLodgingCantBreakfastGuideItineraryDate().getId() != null &&
                !source.getLodgingCantBreakfastGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantBreakfastGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantBreakfastGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantBreakfastGuideItineraryDate( null );
        }
        if (source.getLodgingCantLunchGuideItineraryDate() != null &&
                source.getLodgingCantLunchGuideItineraryDate().getId() != null &&
                !source.getLodgingCantLunchGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantLunchGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantLunchGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantLunchGuideItineraryDate( null );
        }
        if (source.getLodgingCantDinnerGuideItineraryDate() != null &&
                source.getLodgingCantDinnerGuideItineraryDate().getId() != null &&
                !source.getLodgingCantDinnerGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantDinnerGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantDinnerGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantDinnerGuideItineraryDate( null );
        }
        if (source.getLodgingCantBoxLunchGuideItineraryDate() != null &&
                source.getLodgingCantBoxLunchGuideItineraryDate().getId() != null &&
                !source.getLodgingCantBoxLunchGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgingCantBoxLunchGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgingCantBoxLunchGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgingCantBoxLunchGuideItineraryDate( null );
        }

        itineraryItemEntity.setLodgingCantSwbGuide( source.getLodgingCantSwbGuide() );
        itineraryItemEntity.setLodgingCantTwinGuide( source.getLodgingCantTwinGuide() );
        itineraryItemEntity.setLodgingGuideCantNight( source.getLodgingGuideCantNight() );
        itineraryItemEntity.setLodgeCantSwb( source.getLodgeCantSwb() );
        itineraryItemEntity.setLodgeCantMat( source.getLodgeCantMat() );
        itineraryItemEntity.setLodgeCantTwin( source.getLodgeCantTwin() );
        itineraryItemEntity.setLodgeCantTrp( source.getLodgeCantTrp() );
        if (source.getLodgeCategory() != null &&
                source.getLodgeCategory().getId() != null &&
                !source.getLodgeCategory().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCategory( lodgingCategoryConverter.toEntity( source.getLodgeCategory() ) );
        }else{
            itineraryItemEntity.setLodgeCategory( null );
        }
        itineraryItemEntity.setLodgeCategoryStr( source.getLodgeCategoryStr() );
        itineraryItemEntity.setLodgeCantBreakfast( source.getLodgeCantBreakfast() );
        itineraryItemEntity.setLodgeCantLunch( source.getLodgeCantLunch() );
        itineraryItemEntity.setLodgeCantDinner( source.getLodgeCantDinner() );
        itineraryItemEntity.setLodgeCantBoxLunch( source.getLodgeCantBoxLunch() );
        itineraryItemEntity.setLodgeCantBreakfastDateTime( source.getLodgeCantBreakfastDateTime() );
        itineraryItemEntity.setLodgeCantLunchDateTime( source.getLodgeCantLunchDateTime() );
        itineraryItemEntity.setLodgeCantDinnerDateTime( source.getLodgeCantDinnerDateTime() );
        itineraryItemEntity.setLodgeCantBoxLunchDateTime( source.getLodgeCantBoxLunchDateTime() );
        itineraryItemEntity.setLodgeCantBreakfastDate( source.getLodgeCantBreakfastDate() );
        itineraryItemEntity.setLodgeCantLunchDate( source.getLodgeCantLunchDate() );
        itineraryItemEntity.setLodgeCantDinnerDate( source.getLodgeCantDinnerDate() );
        itineraryItemEntity.setLodgeCantBoxLunchDate( source.getLodgeCantBoxLunchDate() );

        if (source.getLodgeCantBreakfastItineraryDate() != null &&
                source.getLodgeCantBreakfastItineraryDate().getId() != null &&
                !source.getLodgeCantBreakfastItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantBreakfastItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantBreakfastItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantBreakfastItineraryDate( null );
        }
        if (source.getLodgeCantLunchItineraryDate() != null &&
                source.getLodgeCantLunchItineraryDate().getId() != null &&
                !source.getLodgeCantLunchItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantLunchItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantLunchItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantLunchItineraryDate( null );
        }
        if (source.getLodgeCantDinnerItineraryDate() != null &&
                source.getLodgeCantDinnerItineraryDate().getId() != null &&
                !source.getLodgeCantDinnerItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantDinnerItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantDinnerItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantDinnerItineraryDate( null );
        }
        if (source.getLodgeCantBoxLunchItineraryDate() != null &&
                source.getLodgeCantBoxLunchItineraryDate().getId() != null &&
                !source.getLodgeCantBoxLunchItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantBoxLunchItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantBoxLunchItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantBoxLunchItineraryDate( null );
        }

        itineraryItemEntity.setLodgeCantNight( source.getLodgeCantNight() );
        itineraryItemEntity.setLodgeCantBreakfastTcGuide( source.getLodgeCantBreakfastTcGuide() );
        itineraryItemEntity.setLodgeCantLunchTcGuide( source.getLodgeCantLunchTcGuide() );
        itineraryItemEntity.setLodgeCantDinnerTcGuide( source.getLodgeCantDinnerTcGuide() );
        itineraryItemEntity.setLodgeCantBoxLunchTcGuide( source.getLodgeCantBoxLunchTcGuide() );
        itineraryItemEntity.setLodgeCantBreakfastTcGuideDateTime( source.getLodgeCantBreakfastTcGuideDateTime() );
        itineraryItemEntity.setLodgeCantLunchTcGuideDateTime( source.getLodgeCantLunchTcGuideDateTime() );
        itineraryItemEntity.setLodgeCantDinnerTcGuideDateTime( source.getLodgeCantDinnerTcGuideDateTime() );
        itineraryItemEntity.setLodgeCantBoxLunchTcGuideDateTime( source.getLodgeCantBoxLunchTcGuideDateTime() );
        itineraryItemEntity.setLodgeCantBreakfastTcGuideDate( source.getLodgeCantBreakfastTcGuideDate() );
        itineraryItemEntity.setLodgeCantLunchTcGuideDate( source.getLodgeCantLunchTcGuideDate() );
        itineraryItemEntity.setLodgeCantDinnerTcGuideDate( source.getLodgeCantDinnerTcGuideDate() );
        itineraryItemEntity.setLodgeCantBoxLunchTcGuideDate( source.getLodgeCantBoxLunchTcGuideDate() );

        if (source.getLodgeCantBreakfastTcGuideItineraryDate() != null &&
                source.getLodgeCantBreakfastTcGuideItineraryDate().getId() != null &&
                !source.getLodgeCantBreakfastTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantBreakfastTcGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantBreakfastTcGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantBreakfastTcGuideItineraryDate( null );
        }
        if (source.getLodgeCantLunchTcGuideItineraryDate() != null &&
                source.getLodgeCantLunchTcGuideItineraryDate().getId() != null &&
                !source.getLodgeCantLunchTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantLunchTcGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantLunchTcGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantLunchTcGuideItineraryDate( null );
        }
        if (source.getLodgeCantDinnerTcGuideItineraryDate() != null &&
                source.getLodgeCantDinnerTcGuideItineraryDate().getId() != null &&
                !source.getLodgeCantDinnerTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantDinnerTcGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantDinnerTcGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantDinnerTcGuideItineraryDate( null );
        }
        if (source.getLodgeCantBoxLunchTcGuideItineraryDate() != null &&
                source.getLodgeCantBoxLunchTcGuideItineraryDate().getId() != null &&
                !source.getLodgeCantBoxLunchTcGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantBoxLunchTcGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantBoxLunchTcGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantBoxLunchTcGuideItineraryDate( null );
        }

        itineraryItemEntity.setLodgeCantSwbTcGuide( source.getLodgeCantSwbTcGuide() );
        itineraryItemEntity.setLodgeCantTwinTcGuide( source.getLodgeCantTwinTcGuide() );
        itineraryItemEntity.setLodgeTcCantNight( source.getLodgeTcCantNight() );
        itineraryItemEntity.setLodgeCantBreakfastTcLocalGuide( source.getLodgeCantBreakfastTcLocalGuide() );
        itineraryItemEntity.setLodgeCantLunchTcLocalGuide( source.getLodgeCantLunchTcLocalGuide() );
        itineraryItemEntity.setLodgeCantDinnerTcLocalGuide( source.getLodgeCantDinnerTcLocalGuide() );
        itineraryItemEntity.setLodgeCantBoxLunchTcLocalGuide( source.getLodgeCantBoxLunchTcLocalGuide() );
        itineraryItemEntity.setLodgeCantBreakfastTcLocalGuideDateTime( source.getLodgeCantBreakfastTcLocalGuideDateTime() );
        itineraryItemEntity.setLodgeCantLunchTcLocalGuideDateTime( source.getLodgeCantLunchTcLocalGuideDateTime() );
        itineraryItemEntity.setLodgeCantDinnerTcLocalGuideDateTime( source.getLodgeCantDinnerTcLocalGuideDateTime() );
        itineraryItemEntity.setLodgeCantBoxLunchTcLocalGuideDateTime( source.getLodgeCantBoxLunchTcLocalGuideDateTime() );
        itineraryItemEntity.setLodgeCantBreakfastTcLocalGuideDate( source.getLodgeCantBreakfastTcLocalGuideDate() );
        itineraryItemEntity.setLodgeCantLunchTcLocalGuideDate( source.getLodgeCantLunchTcLocalGuideDate() );
        itineraryItemEntity.setLodgeCantDinnerTcLocalGuideDate( source.getLodgeCantDinnerTcLocalGuideDate() );
        itineraryItemEntity.setLodgeCantBoxLunchTcLocalGuideDate( source.getLodgeCantBoxLunchTcLocalGuideDate() );

        if (source.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null &&
                source.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantBreakfastTcLocalGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantBreakfastTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantBreakfastTcLocalGuideItineraryDate( null );
        }
        if (source.getLodgeCantLunchTcLocalGuideItineraryDate() != null &&
                source.getLodgeCantLunchTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgeCantLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantLunchTcLocalGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantLunchTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantLunchTcLocalGuideItineraryDate( null );
        }
        if (source.getLodgeCantDinnerTcLocalGuideItineraryDate() != null &&
                source.getLodgeCantDinnerTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgeCantDinnerTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantDinnerTcLocalGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantDinnerTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantDinnerTcLocalGuideItineraryDate( null );
        }
        if (source.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null &&
                source.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getId() != null &&
                !source.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantBoxLunchTcLocalGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantBoxLunchTcLocalGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantBoxLunchTcLocalGuideItineraryDate( null );
        }

        itineraryItemEntity.setLodgeCantSwbTcLocalGuide( source.getLodgeCantSwbTcLocalGuide() );
        itineraryItemEntity.setLodgeCantTwinTcLocalGuide( source.getLodgeCantTwinTcLocalGuide() );
        itineraryItemEntity.setLodgeTcLocalCantNight( source.getLodgeTcLocalCantNight() );
        itineraryItemEntity.setLodgeCantBreakfastGuide( source.getLodgeCantBreakfastGuide() );
        itineraryItemEntity.setLodgeCantLunchGuide( source.getLodgeCantLunchGuide() );
        itineraryItemEntity.setLodgeCantDinnerGuide( source.getLodgeCantDinnerGuide() );
        itineraryItemEntity.setLodgeCantBoxLunchGuide( source.getLodgeCantBoxLunchGuide() );
        itineraryItemEntity.setLodgeCantBreakfastGuideDateTime( source.getLodgeCantBreakfastGuideDateTime() );
        itineraryItemEntity.setLodgeCantLunchGuideDateTime( source.getLodgeCantLunchGuideDateTime() );
        itineraryItemEntity.setLodgeCantDinnerGuideDateTime( source.getLodgeCantDinnerGuideDateTime() );
        itineraryItemEntity.setLodgeCantBoxLunchGuideDateTime( source.getLodgeCantBoxLunchGuideDateTime() );
        itineraryItemEntity.setLodgeCantBreakfastGuideDate( source.getLodgeCantBreakfastGuideDate() );
        itineraryItemEntity.setLodgeCantLunchGuideDate( source.getLodgeCantLunchGuideDate() );
        itineraryItemEntity.setLodgeCantDinnerGuideDate( source.getLodgeCantDinnerGuideDate() );
        itineraryItemEntity.setLodgeCantBoxLunchGuideDate( source.getLodgeCantBoxLunchGuideDate() );

        if (source.getLodgeCantBreakfastGuideItineraryDate() != null &&
                source.getLodgeCantBreakfastGuideItineraryDate().getId() != null &&
                !source.getLodgeCantBreakfastGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantBreakfastGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantBreakfastGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantBreakfastGuideItineraryDate( null );
        }
        if (source.getLodgeCantLunchGuideItineraryDate() != null &&
                source.getLodgeCantLunchGuideItineraryDate().getId() != null &&
                !source.getLodgeCantLunchGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantLunchGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantLunchGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantLunchGuideItineraryDate( null );
        }
        if (source.getLodgeCantDinnerGuideItineraryDate() != null &&
                source.getLodgeCantDinnerGuideItineraryDate().getId() != null &&
                !source.getLodgeCantDinnerGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantDinnerGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantDinnerGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantDinnerGuideItineraryDate( null );
        }
        if (source.getLodgeCantBoxLunchGuideItineraryDate() != null &&
                source.getLodgeCantBoxLunchGuideItineraryDate().getId() != null &&
                !source.getLodgeCantBoxLunchGuideItineraryDate().getId().isEmpty()) {
            itineraryItemEntity.setLodgeCantBoxLunchGuideItineraryDate( itineraryDateConverter.toEntity( source.getLodgeCantBoxLunchGuideItineraryDate() ) );
        }else{
            itineraryItemEntity.setLodgeCantBoxLunchGuideItineraryDate( null );
        }

        itineraryItemEntity.setLodgeCantSwbGuide( source.getLodgeCantSwbGuide() );
        itineraryItemEntity.setLodgeCantTwinGuide( source.getLodgeCantTwinGuide() );
        itineraryItemEntity.setLodgeGuideCantNight( source.getLodgeGuideCantNight() );
        itineraryItemEntity.setLodgePvt( source.getLodgePvt() );
        itineraryItemEntity.setLodgeSic( source.getLodgeSic() );
        itineraryItemEntity.setRestaurantCantBreakfast( source.getRestaurantCantBreakfast() );
        itineraryItemEntity.setRestaurantCantLunch( source.getRestaurantCantLunch() );
        itineraryItemEntity.setRestaurantCantDinner( source.getRestaurantCantDinner() );
        itineraryItemEntity.setRestaurantCantBoxLunch( source.getRestaurantCantBoxLunch() );
        itineraryItemEntity.setRestaurantCantBreakfastTcGuide( source.getRestaurantCantBreakfastTcGuide() );
        itineraryItemEntity.setRestaurantCantLunchTcGuide( source.getRestaurantCantLunchTcGuide() );
        itineraryItemEntity.setRestaurantCantDinnerTcGuide( source.getRestaurantCantDinnerTcGuide() );
        itineraryItemEntity.setRestaurantCantBoxLunchTcGuide( source.getRestaurantCantBoxLunchTcGuide() );
        itineraryItemEntity.setRestaurantCantBreakfastTcLocalGuide( source.getRestaurantCantBreakfastTcLocalGuide() );
        itineraryItemEntity.setRestaurantCantLunchTcLocalGuide( source.getRestaurantCantLunchTcLocalGuide() );
        itineraryItemEntity.setRestaurantCantDinnerTcLocalGuide( source.getRestaurantCantDinnerTcLocalGuide() );
        itineraryItemEntity.setRestaurantCantBoxLunchTcLocalGuide( source.getRestaurantCantBoxLunchTcLocalGuide() );
        itineraryItemEntity.setRestaurantCantBreakfastGuide( source.getRestaurantCantBreakfastGuide() );
        itineraryItemEntity.setRestaurantCantLunchGuide( source.getRestaurantCantLunchGuide() );
        itineraryItemEntity.setRestaurantCantDinnerGuide( source.getRestaurantCantDinnerGuide() );
        itineraryItemEntity.setRestaurantCantBoxLunchGuide( source.getRestaurantCantBoxLunchGuide() );
        itineraryItemEntity.setRestaurantCantBreakfastDrive( source.getRestaurantCantBreakfastDrive() );
        itineraryItemEntity.setRestaurantCantLunchDrive( source.getRestaurantCantLunchDrive() );
        itineraryItemEntity.setRestaurantCantDinnerDrive( source.getRestaurantCantDinnerDrive() );
        itineraryItemEntity.setRestaurantCantBoxLunchDrive( source.getRestaurantCantBoxLunchDrive() );
        itineraryItemEntity.setOperatorPvt( source.getOperatorPvt() );
        itineraryItemEntity.setOperatorSic( source.getOperatorSic() );
        if (source.getLodgingForOperator() != null &&
                source.getLodgingForOperator().getId() != null &&
                !source.getLodgingForOperator().getId().isEmpty()) {
            itineraryItemEntity.setLodgingForOperator( supplierLodgingConverter.toEntity( source.getLodgingForOperator() ) );
        }else{
            itineraryItemEntity.setLodgingForOperator( null );
        }
        itineraryItemEntity.setIdItineraryItemQuotation( source.getIdItineraryItemQuotation() );
        itineraryItemEntity.setLodgingForOperatorStr( source.getLodgingForOperatorStr() );

        itineraryItemEntity.setStateItineraryService( source.getStateItineraryService() );
        itineraryItemEntity.setNote( source.getNote() );

        itineraryItemEntity.setStateServiceInvoice( source.getStateServiceInvoice() );
        itineraryItemEntity.setNoteInvoice( source.getNoteInvoice() );
        itineraryItemEntity.setDocumentDateInvoice( source.getDocumentDateInvoice() );

        itineraryItemEntity.setServiceForOperation( source.getServiceForOperation() );
        itineraryItemEntity.setGeneratedBy( source.getGeneratedBy() );
        return itineraryItemEntity;
    }

    public IdBaseDomain toDomainIdBase(ItineraryItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ItineraryItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setIndex(source.getIndex() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomain(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId(entity.getId());
            itineraryItemDomain.setIndex(entity.getIndex());
            itineraryItemDomain.setItineraryDateTime( entity.getItineraryDateTime() );
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseShowItinerary(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId(entity.getId());
            itineraryItemDomain.setIndex(entity.getIndex());
            itineraryItemDomain.setItineraryDateTime(entity.getItineraryDateTime());

            itineraryItemDomain.setSupplierType(new SupplierTypeDomain());
            itineraryItemDomain.getSupplierType().setId(entity.getSupplierType().getId());
            itineraryItemDomain.getSupplierType().setName(entity.getSupplierType().getName());

            itineraryItemDomain.setSupplier(new SupplierDomain());
            itineraryItemDomain.getSupplier().setId(entity.getSupplier().getId());
            itineraryItemDomain.getSupplier().setName(entity.getSupplier().getName());

            itineraryItemDomain.setService(new ServiceDomain());
            itineraryItemDomain.getService().setId(entity.getService().getId());
            itineraryItemDomain.getService().setName(entity.getService().getName());

            itineraryItemDomain.setPaxTotal(entity.getPaxTotal());
            itineraryItemDomain.setPaxAdultTotal(entity.getPaxAdultTotal());
            itineraryItemDomain.setPaxChildrenTotal(entity.getPaxChildrenTotal());
            itineraryItemDomain.setPaxTcTotal(entity.getPaxTcTotal());
            itineraryItemDomain.setPaxTcLocalTotal(entity.getPaxTcLocalTotal());
            itineraryItemDomain.setPaxGuideTotal(entity.getPaxGuideTotal());
            itineraryItemDomain.setPrice(entity.getPrice());
            itineraryItemDomain.setPriceAdult(entity.getPriceAdult());
            itineraryItemDomain.setPriceChildren(entity.getPriceChildren());
            itineraryItemDomain.setPriceTc(entity.getPriceTc());
            itineraryItemDomain.setPriceTcLocal(entity.getPriceTcLocal());
            itineraryItemDomain.setPriceGuide(entity.getPriceGuide());
            itineraryItemDomain.setObservation(entity.getObservation());

            if (entity.getTour() != null &&
                    entity.getTour().getId() != null &&
                    !entity.getTour().getId().isEmpty()) {
                itineraryItemDomain.setTour(new TourDomain());
                itineraryItemDomain.getTour().setId(entity.getTour().getId());
                itineraryItemDomain.getTour().setName(entity.getTour().getName());
            }

            return itineraryItemDomain;
        }).toList();
    }

    public ItineraryItemDomain toDomainIdSimpleDomainCaseUpdateOrderItinerary(ItineraryItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();

        itineraryItemDomain.setId( source.getId() );
        itineraryItemDomain.setIndex( source.getIndex() );
        itineraryItemDomain.setItineraryDate( new ItineraryDateDomain() );
        itineraryItemDomain.getItineraryDate().setId( source.getItineraryDate().getId() );
        itineraryItemDomain.getItineraryDate().setItineraryDate( source.getItineraryDate().getItineraryDate() );
        itineraryItemDomain.setItineraryDateTime( source.getItineraryDateTime() );
        return itineraryItemDomain;
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseUpdateOrderItinerary(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            itineraryItemDomain.setItineraryDate( new ItineraryDateDomain() );
            itineraryItemDomain.getItineraryDate().setId( entity.getItineraryDate().getId() );
            itineraryItemDomain.getItineraryDate().setItineraryDate( entity.getItineraryDate().getItineraryDate() );
            itineraryItemDomain.setItineraryDateTime( entity.getItineraryDateTime() );
            itineraryItemDomain.setSupplierType( new SupplierTypeDomain() );
            itineraryItemDomain.getSupplierType().setId( entity.getSupplierType().getId() );
            itineraryItemDomain.getSupplierType().setName( entity.getSupplierType().getName() );
            itineraryItemDomain.getSupplierType().setDescription( entity.getSupplierType().getDescription() );
            itineraryItemDomain.getSupplierType().setIdentify( entity.getSupplierType().getIdentify() );
            itineraryItemDomain.setServiceGroup( entity.getServiceGroup() );

            if (entity.getLodgingCantBreakfastItineraryDate() != null &&
                    entity.getLodgingCantBreakfastItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setId( entity.getLodgingCantBreakfastItineraryDate().getId() );
            }

            if (entity.getLodgingCantBreakfastTcGuideItineraryDate() != null &&
                    entity.getLodgingCantBreakfastTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setId( entity.getLodgingCantBreakfastTcGuideItineraryDate().getId() );
            }

            if (entity.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null &&
                    entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setId( entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId() );
            }

            if (entity.getLodgingCantBreakfastGuideItineraryDate() != null &&
                    entity.getLodgingCantBreakfastGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setId( entity.getLodgingCantBreakfastGuideItineraryDate().getId() );
            }

            if (entity.getLodgeCantBreakfastItineraryDate() != null &&
                    entity.getLodgeCantBreakfastItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setId( entity.getLodgeCantBreakfastItineraryDate().getId() );
            }

            if (entity.getLodgeCantBreakfastTcGuideItineraryDate() != null &&
                    entity.getLodgeCantBreakfastTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setId( entity.getLodgeCantBreakfastTcGuideItineraryDate().getId() );
            }

            if (entity.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null &&
                    entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setId( entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId() );
            }

            if (entity.getLodgeCantBreakfastGuideItineraryDate() != null &&
                    entity.getLodgeCantBreakfastGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setId( entity.getLodgeCantBreakfastGuideItineraryDate().getId() );
            }

            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseGenerateBooking(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            itineraryItemDomain.setItineraryDateTime( entity.getItineraryDateTime() );
            itineraryItemDomain.setSupplierType( new SupplierTypeDomain() );
            itineraryItemDomain.getSupplierType().setId( entity.getSupplierType().getId() );
            itineraryItemDomain.getSupplierType().setName( entity.getSupplierType().getName() );
            itineraryItemDomain.getSupplierType().setDescription( entity.getSupplierType().getDescription() );
            itineraryItemDomain.getSupplierType().setIdentify( entity.getSupplierType().getIdentify() );
            itineraryItemDomain.setSupplier( new SupplierDomain() );
            itineraryItemDomain.getSupplier().setId( entity.getSupplier().getId() );
            itineraryItemDomain.getSupplier().setName( entity.getSupplier().getName() );
            itineraryItemDomain.getSupplier().setDescription( entity.getSupplier().getDescription() );
            itineraryItemDomain.getSupplier().setIdentify( entity.getSupplier().getIdentify() );
            itineraryItemDomain.getSupplier().setPartner( new PartnerDomain());
            itineraryItemDomain.getSupplier().getPartner().setId(entity.getSupplier().getPartner().getId());
            itineraryItemDomain.getSupplier().getPartner().setAddress(new AddressEmbeddedDomain());
            itineraryItemDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
            itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
            itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseVoucherEgressSupplier(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            itineraryItemDomain.setItineraryDateTime( entity.getItineraryDateTime() );
            itineraryItemDomain.setSupplierType( new SupplierTypeDomain() );
            itineraryItemDomain.getSupplierType().setId( entity.getSupplierType().getId() );
            itineraryItemDomain.getSupplierType().setName( entity.getSupplierType().getName() );
            itineraryItemDomain.getSupplierType().setDescription( entity.getSupplierType().getDescription() );
            itineraryItemDomain.getSupplierType().setIdentify( entity.getSupplierType().getIdentify() );
            itineraryItemDomain.setSupplier( new SupplierDomain() );
            itineraryItemDomain.getSupplier().setId( entity.getSupplier().getId() );
            itineraryItemDomain.getSupplier().setName( entity.getSupplier().getName() );
            itineraryItemDomain.getSupplier().setDescription( entity.getSupplier().getDescription() );
            itineraryItemDomain.getSupplier().setIdentify( entity.getSupplier().getIdentify() );
            itineraryItemDomain.getSupplier().setPartner( new PartnerDomain());
            itineraryItemDomain.getSupplier().getPartner().setId(entity.getSupplier().getPartner().getId());
            itineraryItemDomain.getSupplier().getPartner().setAddress(new AddressEmbeddedDomain());
            itineraryItemDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
            itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
            itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseGenerateBookingAction(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            itineraryItemDomain.setSupplierType( new SupplierTypeDomain() );
            itineraryItemDomain.getSupplierType().setId( entity.getSupplierType().getId() );
            itineraryItemDomain.setSupplier( new SupplierDomain() );
            itineraryItemDomain.getSupplier().setId( entity.getSupplier().getId() );
            itineraryItemDomain.setService( new ServiceDomain() );
            itineraryItemDomain.getService().setId( entity.getService().getId() );
            itineraryItemDomain.setObservation( entity.getObservation() );
            itineraryItemDomain.setOperatorPvt( entity.getOperatorPvt() );
            itineraryItemDomain.setOperatorSic( entity.getOperatorSic() );
            itineraryItemDomain.setLodgePvt( entity.getLodgePvt() );
            itineraryItemDomain.setLodgeSic( entity.getLodgeSic() );
            itineraryItemDomain.setItineraryDateTime( entity.getItineraryDateTime() );
            itineraryItemDomain.setPaxTotal(entity.getPaxTotal());
            itineraryItemDomain.setPaxAdultTotal(entity.getPaxAdultTotal());
            itineraryItemDomain.setPaxChildrenTotal(entity.getPaxChildrenTotal());
            itineraryItemDomain.setPaxTcTotal(entity.getPaxTcTotal());
            itineraryItemDomain.setPaxTcLocalTotal(entity.getPaxTcLocalTotal());
            itineraryItemDomain.setPaxGuideTotal(entity.getPaxGuideTotal());
            itineraryItemDomain.setItineraryDate( new ItineraryDateDomain() );
            itineraryItemDomain.getItineraryDate().setId( entity.getItineraryDate().getId() );
            itineraryItemDomain.getItineraryDate().setItineraryDate(entity.getItineraryDate().getItineraryDate());
            itineraryItemDomain.getItineraryDate().setItemItinerary(entity.getItineraryDate().getItemItinerary());
            itineraryItemDomain.setGeneratedBy(entity.getGeneratedBy());
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseGenerateBookingActionHotel(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            itineraryItemDomain.setSupplierType( new SupplierTypeDomain() );
            itineraryItemDomain.getSupplierType().setId( entity.getSupplierType().getId() );
            itineraryItemDomain.setSupplier( new SupplierDomain() );
            itineraryItemDomain.getSupplier().setId( entity.getSupplier().getId() );
            itineraryItemDomain.setService( new ServiceDomain() );
            itineraryItemDomain.getService().setId( entity.getService().getId() );
            itineraryItemDomain.setServiceGroup( entity.getServiceGroup() );
            itineraryItemDomain.setObservation( entity.getObservation() );
            itineraryItemDomain.setItineraryDateTime( entity.getItineraryDateTime() );
            itineraryItemDomain.setItineraryDate( new ItineraryDateDomain() );
            itineraryItemDomain.getItineraryDate().setId( entity.getItineraryDate().getId() );
            itineraryItemDomain.getItineraryDate().setItineraryDate(entity.getItineraryDate().getItineraryDate());
            itineraryItemDomain.getItineraryDate().setItemItinerary(entity.getItineraryDate().getItemItinerary());

            itineraryItemDomain.setPaxTotal(entity.getPaxTotal());
            itineraryItemDomain.setPaxAdultTotal(entity.getPaxAdultTotal());
            itineraryItemDomain.setPaxChildrenTotal( entity.getPaxChildrenTotal() );
            itineraryItemDomain.setPaxTcTotal( entity.getPaxTcTotal() );
            itineraryItemDomain.setPaxTcLocalTotal( entity.getPaxTcLocalTotal() );
            itineraryItemDomain.setPaxGuideTotal( entity.getPaxGuideTotal() );

            itineraryItemDomain.setLodgingCantSwb( entity.getLodgingCantSwb() );
            itineraryItemDomain.setLodgingCantMat( entity.getLodgingCantMat() );
            itineraryItemDomain.setLodgingCantTwin( entity.getLodgingCantTwin() );
            itineraryItemDomain.setLodgingCantTrp( entity.getLodgingCantTrp() );

            itineraryItemDomain.setLodgingCategory( new LodgingCategoryDomain() );
            itineraryItemDomain.getLodgingCategory().setId( entity.getLodgingCategory().getId() );

            itineraryItemDomain.setLodgingCategoryStr( entity.getLodgingCategoryStr() );
            itineraryItemDomain.setLodgingCantBreakfast( entity.getLodgingCantBreakfast() );
            itineraryItemDomain.setLodgingCantLunch( entity.getLodgingCantLunch() );
            itineraryItemDomain.setLodgingCantDinner( entity.getLodgingCantDinner() );
            itineraryItemDomain.setLodgingCantBoxLunch( entity.getLodgingCantBoxLunch() );
            itineraryItemDomain.setLodgingCantBreakfastDateTime( entity.getLodgingCantBreakfastDateTime() );
            itineraryItemDomain.setLodgingCantLunchDateTime( entity.getLodgingCantLunchDateTime() );
            itineraryItemDomain.setLodgingCantDinnerDateTime( entity.getLodgingCantDinnerDateTime() );
            itineraryItemDomain.setLodgingCantBoxLunchDateTime( entity.getLodgingCantBoxLunchDateTime() );
            itineraryItemDomain.setLodgingCantBreakfastDate( entity.getLodgingCantBreakfastDate() );
            itineraryItemDomain.setLodgingCantLunchDate( entity.getLodgingCantLunchDate() );
            itineraryItemDomain.setLodgingCantDinnerDate( entity.getLodgingCantDinnerDate() );
            itineraryItemDomain.setLodgingCantBoxLunchDate( entity.getLodgingCantBoxLunchDate() );
            if (entity.getLodgingCantBreakfastItineraryDate() != null &&
                    entity.getLodgingCantBreakfastItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setId(entity.getLodgingCantBreakfastItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setIndex(entity.getLodgingCantBreakfastItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setItineraryDate(entity.getLodgingCantBreakfastItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantBreakfastItineraryDate( null );
            }
            if (entity.getLodgingCantLunchItineraryDate() != null &&
                    entity.getLodgingCantLunchItineraryDate().getId() != null &&
                    !entity.getLodgingCantLunchItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantLunchItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setId(entity.getLodgingCantLunchItineraryDate().getId());
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setIndex(entity.getLodgingCantLunchItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setItineraryDate(entity.getLodgingCantLunchItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantLunchItineraryDate( null );
            }
            if (entity.getLodgingCantDinnerItineraryDate() != null &&
                    entity.getLodgingCantDinnerItineraryDate().getId() != null &&
                    !entity.getLodgingCantDinnerItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantDinnerItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setId(entity.getLodgingCantDinnerItineraryDate().getId());
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setIndex(entity.getLodgingCantDinnerItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setItineraryDate(entity.getLodgingCantDinnerItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantDinnerItineraryDate( null );
            }
            if (entity.getLodgingCantBoxLunchItineraryDate() != null &&
                    entity.getLodgingCantBoxLunchItineraryDate().getId() != null &&
                    !entity.getLodgingCantBoxLunchItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBoxLunchItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setId(entity.getLodgingCantBoxLunchItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setIndex(entity.getLodgingCantBoxLunchItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setItineraryDate(entity.getLodgingCantBoxLunchItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantBoxLunchItineraryDate( null );
            }
            itineraryItemDomain.setLodgingCantNight( entity.getLodgingCantNight() );
            itineraryItemDomain.setLodgingCantBreakfastTcGuide( entity.getLodgingCantBreakfastTcGuide() );
            itineraryItemDomain.setLodgingCantLunchTcGuide( entity.getLodgingCantLunchTcGuide() );
            itineraryItemDomain.setLodgingCantDinnerTcGuide( entity.getLodgingCantDinnerTcGuide() );
            itineraryItemDomain.setLodgingCantBoxLunchTcGuide( entity.getLodgingCantBoxLunchTcGuide() );
            itineraryItemDomain.setLodgingCantBreakfastTcGuideDateTime( entity.getLodgingCantBreakfastTcGuideDateTime() );
            itineraryItemDomain.setLodgingCantLunchTcGuideDateTime( entity.getLodgingCantLunchTcGuideDateTime() );
            itineraryItemDomain.setLodgingCantDinnerTcGuideDateTime( entity.getLodgingCantDinnerTcGuideDateTime() );
            itineraryItemDomain.setLodgingCantBoxLunchTcGuideDateTime( entity.getLodgingCantBoxLunchTcGuideDateTime() );
            itineraryItemDomain.setLodgingCantBreakfastTcGuideDate( entity.getLodgingCantBreakfastTcGuideDate() );
            itineraryItemDomain.setLodgingCantLunchTcGuideDate( entity.getLodgingCantLunchTcGuideDate() );
            itineraryItemDomain.setLodgingCantDinnerTcGuideDate( entity.getLodgingCantDinnerTcGuideDate() );
            itineraryItemDomain.setLodgingCantBoxLunchTcGuideDate( entity.getLodgingCantBoxLunchTcGuideDate() );
            if (entity.getLodgingCantBreakfastTcGuideItineraryDate() != null &&
                    entity.getLodgingCantBreakfastTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setId(entity.getLodgingCantBreakfastTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setIndex(entity.getLodgingCantBreakfastTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setItineraryDate(entity.getLodgingCantBreakfastTcGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantBreakfastTcGuideItineraryDate( null );
            }
            if (entity.getLodgingCantLunchTcGuideItineraryDate() != null &&
                    entity.getLodgingCantLunchTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantLunchTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantLunchTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setId(entity.getLodgingCantLunchTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setIndex(entity.getLodgingCantLunchTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setItineraryDate(entity.getLodgingCantLunchTcGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantLunchTcGuideItineraryDate( null );
            }
            if (entity.getLodgingCantDinnerTcGuideItineraryDate() != null &&
                    entity.getLodgingCantDinnerTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantDinnerTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantDinnerTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setId(entity.getLodgingCantDinnerTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setIndex(entity.getLodgingCantDinnerTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setItineraryDate(entity.getLodgingCantDinnerTcGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantDinnerTcGuideItineraryDate( null );
            }
            if (entity.getLodgingCantBoxLunchTcGuideItineraryDate() != null &&
                    entity.getLodgingCantBoxLunchTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBoxLunchTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBoxLunchTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setId(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setIndex(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setItineraryDate(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantBoxLunchTcGuideItineraryDate( null );
            }
            itineraryItemDomain.setLodgingCantSwbTcGuide( entity.getLodgingCantSwbTcGuide() );
            itineraryItemDomain.setLodgingCantTwinTcGuide( entity.getLodgingCantTwinTcGuide() );
            itineraryItemDomain.setLodgingCantBreakfastTcLocalGuide( entity.getLodgingCantBreakfastTcLocalGuide() );
            itineraryItemDomain.setLodgingCantLunchTcLocalGuide( entity.getLodgingCantLunchTcLocalGuide() );
            itineraryItemDomain.setLodgingCantDinnerTcLocalGuide( entity.getLodgingCantDinnerTcLocalGuide() );
            itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuide( entity.getLodgingCantBoxLunchTcLocalGuide() );
            itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideDateTime( entity.getLodgingCantBreakfastTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgingCantLunchTcLocalGuideDateTime( entity.getLodgingCantLunchTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgingCantDinnerTcLocalGuideDateTime( entity.getLodgingCantDinnerTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideDateTime( entity.getLodgingCantBoxLunchTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideDate( entity.getLodgingCantBreakfastTcLocalGuideDate() );
            itineraryItemDomain.setLodgingCantLunchTcLocalGuideDate( entity.getLodgingCantLunchTcLocalGuideDate() );
            itineraryItemDomain.setLodgingCantDinnerTcLocalGuideDate( entity.getLodgingCantDinnerTcLocalGuideDate() );
            itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideDate( entity.getLodgingCantBoxLunchTcLocalGuideDate() );
            if (entity.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null &&
                    entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setId(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setIndex(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgingCantLunchTcLocalGuideItineraryDate() != null &&
                    entity.getLodgingCantLunchTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantLunchTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setId(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setIndex(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantLunchTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgingCantDinnerTcLocalGuideItineraryDate() != null &&
                    entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantDinnerTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setId(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setIndex(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantDinnerTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null &&
                    entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setId(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setIndex(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideItineraryDate( null );
            }
            itineraryItemDomain.setLodgingCantSwbTcLocalGuide( entity.getLodgingCantSwbTcLocalGuide() );
            itineraryItemDomain.setLodgingCantTwinTcLocalGuide( entity.getLodgingCantTwinTcLocalGuide() );
            itineraryItemDomain.setLodgingCantBreakfastGuide( entity.getLodgingCantBreakfastGuide() );
            itineraryItemDomain.setLodgingCantLunchGuide( entity.getLodgingCantLunchGuide() );
            itineraryItemDomain.setLodgingCantDinnerGuide( entity.getLodgingCantDinnerGuide() );
            itineraryItemDomain.setLodgingCantBoxLunchGuide( entity.getLodgingCantBoxLunchGuide() );
            itineraryItemDomain.setLodgingCantBreakfastGuideDateTime( entity.getLodgingCantBreakfastGuideDateTime() );
            itineraryItemDomain.setLodgingCantLunchGuideDateTime( entity.getLodgingCantLunchGuideDateTime() );
            itineraryItemDomain.setLodgingCantDinnerGuideDateTime( entity.getLodgingCantDinnerGuideDateTime() );
            itineraryItemDomain.setLodgingCantBoxLunchGuideDateTime( entity.getLodgingCantBoxLunchGuideDateTime() );
            itineraryItemDomain.setLodgingCantBreakfastGuideDate( entity.getLodgingCantBreakfastGuideDate() );
            itineraryItemDomain.setLodgingCantLunchGuideDate( entity.getLodgingCantLunchGuideDate() );
            itineraryItemDomain.setLodgingCantDinnerGuideDate( entity.getLodgingCantDinnerGuideDate() );
            itineraryItemDomain.setLodgingCantBoxLunchGuideDate( entity.getLodgingCantBoxLunchGuideDate() );
            if (entity.getLodgingCantBreakfastGuideItineraryDate() != null &&
                    entity.getLodgingCantBreakfastGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setId(entity.getLodgingCantBreakfastGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setIndex(entity.getLodgingCantBreakfastGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setItineraryDate(entity.getLodgingCantBreakfastGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantBreakfastGuideItineraryDate( null );
            }
            if (entity.getLodgingCantLunchGuideItineraryDate() != null &&
                    entity.getLodgingCantLunchGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantLunchGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantLunchGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setId(entity.getLodgingCantLunchGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setIndex(entity.getLodgingCantLunchGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setItineraryDate(entity.getLodgingCantLunchGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantLunchGuideItineraryDate( null );
            }
            if (entity.getLodgingCantDinnerGuideItineraryDate() != null &&
                    entity.getLodgingCantDinnerGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantDinnerGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantDinnerGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setId(entity.getLodgingCantDinnerGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setIndex(entity.getLodgingCantDinnerGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setItineraryDate(entity.getLodgingCantDinnerGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantDinnerGuideItineraryDate( null );
            }
            if (entity.getLodgingCantBoxLunchGuideItineraryDate() != null &&
                    entity.getLodgingCantBoxLunchGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBoxLunchGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBoxLunchGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setId(entity.getLodgingCantBoxLunchGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setIndex(entity.getLodgingCantBoxLunchGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setItineraryDate(entity.getLodgingCantBoxLunchGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgingCantBoxLunchGuideItineraryDate( null );
            }

            itineraryItemDomain.setLodgingCantSwbGuide( entity.getLodgingCantSwbGuide() );
            itineraryItemDomain.setLodgingCantTwinGuide( entity.getLodgingCantTwinGuide() );
            itineraryItemDomain.setLodgingGuideCantNight( entity.getLodgingGuideCantNight() );
            itineraryItemDomain.setLodgeCantSwb( entity.getLodgeCantSwb() );
            itineraryItemDomain.setLodgeCantMat( entity.getLodgeCantMat() );
            itineraryItemDomain.setLodgeCantTwin( entity.getLodgeCantTwin() );
            itineraryItemDomain.setLodgeCantTrp( entity.getLodgeCantTrp() );

            itineraryItemDomain.setLodgeCategory( new LodgingCategoryDomain() );
            itineraryItemDomain.getLodgeCategory().setId( entity.getLodgeCategory().getId() );

            itineraryItemDomain.setLodgeCategoryStr( entity.getLodgeCategoryStr() );
            itineraryItemDomain.setLodgeCantBreakfast( entity.getLodgeCantBreakfast() );
            itineraryItemDomain.setLodgeCantLunch( entity.getLodgeCantLunch() );
            itineraryItemDomain.setLodgeCantDinner( entity.getLodgeCantDinner() );
            itineraryItemDomain.setLodgeCantBoxLunch( entity.getLodgeCantBoxLunch() );
            itineraryItemDomain.setLodgeCantBreakfastDateTime( entity.getLodgeCantBreakfastDateTime() );
            itineraryItemDomain.setLodgeCantLunchDateTime( entity.getLodgeCantLunchDateTime() );
            itineraryItemDomain.setLodgeCantDinnerDateTime( entity.getLodgeCantDinnerDateTime() );
            itineraryItemDomain.setLodgeCantBoxLunchDateTime( entity.getLodgeCantBoxLunchDateTime() );
            itineraryItemDomain.setLodgeCantBreakfastDate( entity.getLodgeCantBreakfastDate() );
            itineraryItemDomain.setLodgeCantLunchDate( entity.getLodgeCantLunchDate() );
            itineraryItemDomain.setLodgeCantDinnerDate( entity.getLodgeCantDinnerDate() );
            itineraryItemDomain.setLodgeCantBoxLunchDate( entity.getLodgeCantBoxLunchDate() );
            if (entity.getLodgeCantBreakfastItineraryDate() != null &&
                    entity.getLodgeCantBreakfastItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setId(entity.getLodgeCantBreakfastItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setIndex(entity.getLodgeCantBreakfastItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setItineraryDate(entity.getLodgeCantBreakfastItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantBreakfastItineraryDate( null );
            }
            if (entity.getLodgeCantLunchItineraryDate() != null &&
                    entity.getLodgeCantLunchItineraryDate().getId() != null &&
                    !entity.getLodgeCantLunchItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantLunchItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setId(entity.getLodgeCantLunchItineraryDate().getId());
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setIndex(entity.getLodgeCantLunchItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setItineraryDate(entity.getLodgeCantLunchItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantLunchItineraryDate( null );
            }
            if (entity.getLodgeCantDinnerItineraryDate() != null &&
                    entity.getLodgeCantDinnerItineraryDate().getId() != null &&
                    !entity.getLodgeCantDinnerItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantDinnerItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setId(entity.getLodgeCantDinnerItineraryDate().getId());
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setIndex(entity.getLodgeCantDinnerItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setItineraryDate(entity.getLodgeCantDinnerItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantDinnerItineraryDate( null );
            }
            if (entity.getLodgeCantBoxLunchItineraryDate() != null &&
                    entity.getLodgeCantBoxLunchItineraryDate().getId() != null &&
                    !entity.getLodgeCantBoxLunchItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBoxLunchItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setId(entity.getLodgeCantBoxLunchItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setIndex(entity.getLodgeCantBoxLunchItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setItineraryDate(entity.getLodgeCantBoxLunchItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantBoxLunchItineraryDate( null );
            }

            itineraryItemDomain.setLodgeCantNight( entity.getLodgeCantNight() );
            itineraryItemDomain.setLodgeCantBreakfastTcGuide( entity.getLodgeCantBreakfastTcGuide() );
            itineraryItemDomain.setLodgeCantLunchTcGuide( entity.getLodgeCantLunchTcGuide() );
            itineraryItemDomain.setLodgeCantDinnerTcGuide( entity.getLodgeCantDinnerTcGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchTcGuide( entity.getLodgeCantBoxLunchTcGuide() );
            itineraryItemDomain.setLodgeCantBreakfastTcGuideDateTime( entity.getLodgeCantBreakfastTcGuideDateTime() );
            itineraryItemDomain.setLodgeCantLunchTcGuideDateTime( entity.getLodgeCantLunchTcGuideDateTime() );
            itineraryItemDomain.setLodgeCantDinnerTcGuideDateTime( entity.getLodgeCantDinnerTcGuideDateTime() );
            itineraryItemDomain.setLodgeCantBoxLunchTcGuideDateTime( entity.getLodgeCantBoxLunchTcGuideDateTime() );
            itineraryItemDomain.setLodgeCantBreakfastTcGuideDate( entity.getLodgeCantBreakfastTcGuideDate() );
            itineraryItemDomain.setLodgeCantLunchTcGuideDate( entity.getLodgeCantLunchTcGuideDate() );
            itineraryItemDomain.setLodgeCantDinnerTcGuideDate( entity.getLodgeCantDinnerTcGuideDate() );
            itineraryItemDomain.setLodgeCantBoxLunchTcGuideDate( entity.getLodgeCantBoxLunchTcGuideDate() );
            if (entity.getLodgeCantBreakfastTcGuideItineraryDate() != null &&
                    entity.getLodgeCantBreakfastTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setId(entity.getLodgeCantBreakfastTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setIndex(entity.getLodgeCantBreakfastTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setItineraryDate(entity.getLodgeCantBreakfastTcGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantBreakfastTcGuideItineraryDate( null );
            }
            if (entity.getLodgeCantLunchTcGuideItineraryDate() != null &&
                    entity.getLodgeCantLunchTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantLunchTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantLunchTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setId(entity.getLodgeCantLunchTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setIndex(entity.getLodgeCantLunchTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setItineraryDate(entity.getLodgeCantLunchTcGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantLunchTcGuideItineraryDate( null );
            }
            if (entity.getLodgeCantDinnerTcGuideItineraryDate() != null &&
                    entity.getLodgeCantDinnerTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantDinnerTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantDinnerTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setId(entity.getLodgeCantDinnerTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setIndex(entity.getLodgeCantDinnerTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setItineraryDate(entity.getLodgeCantDinnerTcGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantDinnerTcGuideItineraryDate( null );
            }
            if (entity.getLodgeCantBoxLunchTcGuideItineraryDate() != null &&
                    entity.getLodgeCantBoxLunchTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBoxLunchTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBoxLunchTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setId(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setIndex(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setItineraryDate(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantBoxLunchTcGuideItineraryDate( null );
            }

            itineraryItemDomain.setLodgeCantSwbTcGuide( entity.getLodgeCantSwbTcGuide() );
            itineraryItemDomain.setLodgeCantTwinTcGuide( entity.getLodgeCantTwinTcGuide() );
            itineraryItemDomain.setLodgeTcCantNight( entity.getLodgeTcCantNight() );
            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuide( entity.getLodgeCantBreakfastTcLocalGuide() );
            itineraryItemDomain.setLodgeCantLunchTcLocalGuide( entity.getLodgeCantLunchTcLocalGuide() );
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuide( entity.getLodgeCantDinnerTcLocalGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuide( entity.getLodgeCantBoxLunchTcLocalGuide() );
            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideDateTime( entity.getLodgeCantBreakfastTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgeCantLunchTcLocalGuideDateTime( entity.getLodgeCantLunchTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuideDateTime( entity.getLodgeCantDinnerTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideDateTime( entity.getLodgeCantBoxLunchTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideDate( entity.getLodgeCantBreakfastTcLocalGuideDate() );
            itineraryItemDomain.setLodgeCantLunchTcLocalGuideDate( entity.getLodgeCantLunchTcLocalGuideDate() );
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuideDate( entity.getLodgeCantDinnerTcLocalGuideDate() );
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideDate( entity.getLodgeCantBoxLunchTcLocalGuideDate() );
            if (entity.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null &&
                    entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setId(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setIndex(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgeCantLunchTcLocalGuideItineraryDate() != null &&
                    entity.getLodgeCantLunchTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantLunchTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setId(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setIndex(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantLunchTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgeCantDinnerTcLocalGuideItineraryDate() != null &&
                    entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantDinnerTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setId(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setIndex(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantDinnerTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null &&
                    entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setId(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setIndex(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideItineraryDate( null );
            }

            itineraryItemDomain.setLodgeCantSwbTcLocalGuide( entity.getLodgeCantSwbTcLocalGuide() );
            itineraryItemDomain.setLodgeCantTwinTcLocalGuide( entity.getLodgeCantTwinTcLocalGuide() );
            itineraryItemDomain.setLodgeTcLocalCantNight( entity.getLodgeTcLocalCantNight() );
            itineraryItemDomain.setLodgeCantBreakfastGuide( entity.getLodgeCantBreakfastGuide() );
            itineraryItemDomain.setLodgeCantLunchGuide( entity.getLodgeCantLunchGuide() );
            itineraryItemDomain.setLodgeCantDinnerGuide( entity.getLodgeCantDinnerGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchGuide( entity.getLodgeCantBoxLunchGuide() );
            itineraryItemDomain.setLodgeCantBreakfastGuideDateTime( entity.getLodgeCantBreakfastGuideDateTime() );
            itineraryItemDomain.setLodgeCantLunchGuideDateTime( entity.getLodgeCantLunchGuideDateTime() );
            itineraryItemDomain.setLodgeCantDinnerGuideDateTime( entity.getLodgeCantDinnerGuideDateTime() );
            itineraryItemDomain.setLodgeCantBoxLunchGuideDateTime( entity.getLodgeCantBoxLunchGuideDateTime() );
            itineraryItemDomain.setLodgeCantBreakfastGuideDate( entity.getLodgeCantBreakfastGuideDate() );
            itineraryItemDomain.setLodgeCantLunchGuideDate( entity.getLodgeCantLunchGuideDate() );
            itineraryItemDomain.setLodgeCantDinnerGuideDate( entity.getLodgeCantDinnerGuideDate() );
            itineraryItemDomain.setLodgeCantBoxLunchGuideDate( entity.getLodgeCantBoxLunchGuideDate() );

            if (entity.getLodgeCantBreakfastGuideItineraryDate() != null &&
                    entity.getLodgeCantBreakfastGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setId(entity.getLodgeCantBreakfastGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setIndex(entity.getLodgeCantBreakfastGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setItineraryDate(entity.getLodgeCantBreakfastGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantBreakfastGuideItineraryDate( null );
            }
            if (entity.getLodgeCantLunchGuideItineraryDate() != null &&
                    entity.getLodgeCantLunchGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantLunchGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantLunchGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setId(entity.getLodgeCantLunchGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setIndex(entity.getLodgeCantLunchGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setItineraryDate(entity.getLodgeCantLunchGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantLunchGuideItineraryDate( null );
            }
            if (entity.getLodgeCantDinnerGuideItineraryDate() != null &&
                    entity.getLodgeCantDinnerGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantDinnerGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantDinnerGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setId(entity.getLodgeCantDinnerGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setIndex(entity.getLodgeCantDinnerGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setItineraryDate(entity.getLodgeCantDinnerGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantDinnerGuideItineraryDate( null );
            }
            if (entity.getLodgeCantBoxLunchGuideItineraryDate() != null &&
                    entity.getLodgeCantBoxLunchGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBoxLunchGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBoxLunchGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setId(entity.getLodgeCantBoxLunchGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setIndex(entity.getLodgeCantBoxLunchGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setItineraryDate(entity.getLodgeCantBoxLunchGuideItineraryDate().getItineraryDate());
            }else{
                itineraryItemDomain.setLodgeCantBoxLunchGuideItineraryDate( null );
            }
            itineraryItemDomain.setLodgeCantSwbGuide( entity.getLodgeCantSwbGuide() );
            itineraryItemDomain.setLodgeCantTwinGuide( entity.getLodgeCantTwinGuide() );
            itineraryItemDomain.setLodgeGuideCantNight( entity.getLodgeGuideCantNight() );
            itineraryItemDomain.setLodgePvt( entity.getLodgePvt() );
            itineraryItemDomain.setLodgeSic( entity.getLodgeSic() );

            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseTransferControl(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            if (entity.getSupplierType() != null){
                itineraryItemDomain.setSupplierType( new SupplierTypeDomain() );
                itineraryItemDomain.getSupplierType().setId( entity.getSupplierType().getId() );
            }
            if (entity.getSupplier() != null){
                itineraryItemDomain.setSupplier( new SupplierDomain() );
                itineraryItemDomain.getSupplier().setId( entity.getSupplier().getId() );
                itineraryItemDomain.getSupplier().setName( entity.getSupplier().getName() );
                if (entity.getSupplier().getPartner() != null){
                    itineraryItemDomain.getSupplier().setPartner( new PartnerDomain());
                    itineraryItemDomain.getSupplier().getPartner().setId(entity.getSupplier().getPartner().getId());
                    if (entity.getSupplier().getPartner().getAddress() != null){
                        itineraryItemDomain.getSupplier().getPartner().setAddress(new AddressEmbeddedDomain());
                        if (entity.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                            itineraryItemDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                            itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
                            itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
                        }
                    }
                }
            }
            if (entity.getService() != null){
                itineraryItemDomain.setService( new ServiceDomain() );
                itineraryItemDomain.getService().setId( entity.getService().getId() );
                itineraryItemDomain.getService().setName( entity.getService().getName() );
            }
            if (entity.getRegisterGroup() != null){
                itineraryItemDomain.setRegisterGroup( new RegisterGroupDomain() );
                itineraryItemDomain.getRegisterGroup().setId( entity.getRegisterGroup().getId() );
                itineraryItemDomain.getRegisterGroup().setName( entity.getRegisterGroup().getName() );
                if (entity.getRegisterGroup().getCustomer() != null){
                    itineraryItemDomain.getRegisterGroup().setCustomer( new CustomerDomain() );
                    itineraryItemDomain.getRegisterGroup().getCustomer().setId( entity.getRegisterGroup().getCustomer().getId() );
                    itineraryItemDomain.getRegisterGroup().getCustomer().setName( entity.getRegisterGroup().getCustomer().getName() );
                }
            }
            if (entity.getTour() != null &&
                    entity.getTour().getId() != null &&
                    !entity.getTour().getId().isEmpty()) {
                itineraryItemDomain.setTour( new TourDomain() );
                itineraryItemDomain.getTour().setId( entity.getTour().getId() );
                itineraryItemDomain.getTour().setName( entity.getTour().getName() );
            }
            itineraryItemDomain.setObservation( entity.getObservation() );
            itineraryItemDomain.setItineraryDateTime( entity.getItineraryDateTime() );
            if (entity.getItineraryDate() != null){
                itineraryItemDomain.setItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getItineraryDate().setId( entity.getItineraryDate().getId() );
                itineraryItemDomain.getItineraryDate().setItineraryDate(entity.getItineraryDate().getItineraryDate());
                itineraryItemDomain.getItineraryDate().setItemItinerary(entity.getItineraryDate().getItemItinerary());
            }
            if (entity.getOrderServiceItem() != null){
                itineraryItemDomain.setOrderServiceItem( new OrderServiceItemDomain() );
                itineraryItemDomain.getOrderServiceItem().setId(entity.getOrderServiceItem().getId());
                itineraryItemDomain.getOrderServiceItem().setDateTimePax(entity.getOrderServiceItem().getDateTimePax());
                itineraryItemDomain.getOrderServiceItem().setDateTimeRecj(entity.getOrderServiceItem().getDateTimeRecj());
                if (entity.getOrderServiceItem().getDrive() != null){
                    itineraryItemDomain.getOrderServiceItem().setDrive( new GuideDomain() );
                    itineraryItemDomain.getOrderServiceItem().getDrive().setId(entity.getOrderServiceItem().getDrive().getId());
                    if (entity.getOrderServiceItem().getDrive().getPerson() != null){
                        itineraryItemDomain.getOrderServiceItem().getDrive().setPerson( new PersonDomain() );
                        itineraryItemDomain.getOrderServiceItem().getDrive().getPerson().setId(entity.getOrderServiceItem().getDrive().getPerson().getId());
                        itineraryItemDomain.getOrderServiceItem().getDrive().getPerson().setLastName(entity.getOrderServiceItem().getDrive().getPerson().getLastName());
                        itineraryItemDomain.getOrderServiceItem().getDrive().getPerson().setFirstName(entity.getOrderServiceItem().getDrive().getPerson().getFirstName());
                    }
                }
                if (entity.getOrderServiceItem().getGuideTransfer() != null){
                    itineraryItemDomain.getOrderServiceItem().setGuideTransfer( new GuideDomain() );
                    itineraryItemDomain.getOrderServiceItem().getGuideTransfer().setId(entity.getOrderServiceItem().getGuideTransfer().getId());
                    if (entity.getOrderServiceItem().getGuideTransfer().getPerson() != null){
                        itineraryItemDomain.getOrderServiceItem().getGuideTransfer().setPerson( new PersonDomain() );
                        itineraryItemDomain.getOrderServiceItem().getGuideTransfer().getPerson().setId(entity.getOrderServiceItem().getGuideTransfer().getPerson().getId());
                        itineraryItemDomain.getOrderServiceItem().getGuideTransfer().getPerson().setLastName(entity.getOrderServiceItem().getGuideTransfer().getPerson().getLastName());
                        itineraryItemDomain.getOrderServiceItem().getGuideTransfer().getPerson().setFirstName(entity.getOrderServiceItem().getGuideTransfer().getPerson().getFirstName());
                    }
                }
            }
            itineraryItemDomain.setPaxTotal(entity.getPaxTotal());
            itineraryItemDomain.setPaxChildrenTotal( entity.getPaxChildrenTotal() );
            itineraryItemDomain.setPaxTcTotal( entity.getPaxTcTotal() );
            itineraryItemDomain.setPaxTcLocalTotal( entity.getPaxTcLocalTotal() );
            itineraryItemDomain.setPaxGuideTotal( entity.getPaxGuideTotal() );
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseTransferControlExport(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            if (entity.getSupplierType() != null){
                itineraryItemDomain.setSupplierType( new SupplierTypeDomain() );
                itineraryItemDomain.getSupplierType().setId( entity.getSupplierType().getId() );
            }
            if (entity.getSupplier() != null){
                itineraryItemDomain.setSupplier( new SupplierDomain() );
                itineraryItemDomain.getSupplier().setId( entity.getSupplier().getId() );
                itineraryItemDomain.getSupplier().setName( entity.getSupplier().getName() );
            }
            if (entity.getService() != null){
                itineraryItemDomain.setService( new ServiceDomain() );
                itineraryItemDomain.getService().setId( entity.getService().getId() );
                itineraryItemDomain.getService().setName( entity.getService().getName() );
            }
            if (entity.getRegisterGroup() != null){
                itineraryItemDomain.setRegisterGroup( new RegisterGroupDomain() );
                itineraryItemDomain.getRegisterGroup().setId( entity.getRegisterGroup().getId() );
                itineraryItemDomain.getRegisterGroup().setName( entity.getRegisterGroup().getName() );
                if (entity.getRegisterGroup().getCustomer() != null){
                    itineraryItemDomain.getRegisterGroup().setCustomer( new CustomerDomain() );
                    itineraryItemDomain.getRegisterGroup().getCustomer().setId( entity.getRegisterGroup().getCustomer().getId() );
                    itineraryItemDomain.getRegisterGroup().getCustomer().setName( entity.getRegisterGroup().getCustomer().getName() );
                }
            }
            if (entity.getTour() != null &&
                    entity.getTour().getId() != null &&
                    !entity.getTour().getId().isEmpty()) {
                itineraryItemDomain.setTour( new TourDomain() );
                itineraryItemDomain.getTour().setId( entity.getTour().getId() );
                itineraryItemDomain.getTour().setName( entity.getTour().getName() );
            }
            itineraryItemDomain.setObservation( entity.getObservation() );
            itineraryItemDomain.setItineraryDateTime( entity.getItineraryDateTime() );
            if (entity.getItineraryDate() != null){
                itineraryItemDomain.setItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getItineraryDate().setId( entity.getItineraryDate().getId() );
                itineraryItemDomain.getItineraryDate().setItineraryDate(entity.getItineraryDate().getItineraryDate());
                itineraryItemDomain.getItineraryDate().setItemItinerary(entity.getItineraryDate().getItemItinerary());
            }
            itineraryItemDomain.setPaxTotal(entity.getPaxTotal());
            itineraryItemDomain.setPaxChildrenTotal( entity.getPaxChildrenTotal() );
            itineraryItemDomain.setPaxTcTotal( entity.getPaxTcTotal() );
            itineraryItemDomain.setPaxTcLocalTotal( entity.getPaxTcLocalTotal() );
            itineraryItemDomain.setPaxGuideTotal( entity.getPaxGuideTotal() );
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseTransferControlByTourBible(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            if (entity.getSupplierType() != null){
                itineraryItemDomain.setSupplierType( new SupplierTypeDomain() );
                itineraryItemDomain.getSupplierType().setId( entity.getSupplierType().getId() );
                itineraryItemDomain.getSupplierType().setName( entity.getSupplierType().getName() );
                itineraryItemDomain.getSupplierType().setIdentify( entity.getSupplierType().getIdentify() );
            }
            if (entity.getSupplier() != null){
                itineraryItemDomain.setSupplier( new SupplierDomain() );
                itineraryItemDomain.getSupplier().setId( entity.getSupplier().getId() );
                itineraryItemDomain.getSupplier().setName( entity.getSupplier().getName() );
            }
            if (entity.getCustomer() != null){
                itineraryItemDomain.setCustomer( new CustomerDomain() );
                itineraryItemDomain.getCustomer().setId( entity.getCustomer().getId() );
                itineraryItemDomain.getCustomer().setName( entity.getCustomer().getName() );
            }
            if (entity.getService() != null){
                itineraryItemDomain.setService( new ServiceDomain() );
                itineraryItemDomain.getService().setId( entity.getService().getId() );
                itineraryItemDomain.getService().setName( entity.getService().getName() );
            }
            if (entity.getRegisterGroup() != null){
                itineraryItemDomain.setRegisterGroup( new RegisterGroupDomain() );
                itineraryItemDomain.getRegisterGroup().setId( entity.getRegisterGroup().getId() );
                itineraryItemDomain.getRegisterGroup().setName( entity.getRegisterGroup().getName() );
                itineraryItemDomain.getRegisterGroup().setIdentify( entity.getRegisterGroup().getIdentify() );
                itineraryItemDomain.getRegisterGroup().setLink( entity.getRegisterGroup().getLink() );
                itineraryItemDomain.getRegisterGroup().setLinkWetravel( entity.getRegisterGroup().getLinkWetravel() );
                itineraryItemDomain.getRegisterGroup().setLinkBitrix( entity.getRegisterGroup().getLinkBitrix() );
                itineraryItemDomain.getRegisterGroup().setMobilePassengerPrincipal(entity.getRegisterGroup().getMobilePassengerPrincipal());
                itineraryItemDomain.getRegisterGroup().setWhatsappPassengerPrincipal(entity.getRegisterGroup().getWhatsappPassengerPrincipal());
                itineraryItemDomain.getRegisterGroup().setEmailPassengerPrincipal(entity.getRegisterGroup().getEmailPassengerPrincipal());
                itineraryItemDomain.getRegisterGroup().setStateRegisterGroup(entity.getRegisterGroup().getStateRegisterGroup());
                if (entity.getRegisterGroup().getQuotation() != null &&
                        entity.getRegisterGroup().getQuotation().getId() != null &&
                        !entity.getRegisterGroup().getQuotation().getId().isEmpty()){
                    itineraryItemDomain.getRegisterGroup().setQuotation(new QuotationDomain());
                    itineraryItemDomain.getRegisterGroup().getQuotation().setId(entity.getRegisterGroup().getQuotation().getId());
                    if (entity.getRegisterGroup().getQuotation().getEmployee() != null &&
                            entity.getRegisterGroup().getQuotation().getEmployee().getId() != null &&
                            !entity.getRegisterGroup().getQuotation().getEmployee().getId().isEmpty()){
                        itineraryItemDomain.getRegisterGroup().getQuotation().setEmployee(new EmployeeDomain());
                        itineraryItemDomain.getRegisterGroup().getQuotation().getEmployee().setId(entity.getRegisterGroup().getQuotation().getEmployee().getId());
                        if (entity.getRegisterGroup().getQuotation().getEmployee().getPerson() != null &&
                                entity.getRegisterGroup().getQuotation().getEmployee().getPerson().getId() != null &&
                                !entity.getRegisterGroup().getQuotation().getEmployee().getPerson().getId().isEmpty()){
                            itineraryItemDomain.getRegisterGroup().getQuotation().getEmployee().setPerson(new PersonDomain());
                            itineraryItemDomain.getRegisterGroup().getQuotation().getEmployee().getPerson().setId(entity.getRegisterGroup().getQuotation().getEmployee().getPerson().getId());
                            itineraryItemDomain.getRegisterGroup().getQuotation().getEmployee().getPerson().setFirstName(entity.getRegisterGroup().getQuotation().getEmployee().getPerson().getFirstName());
                            itineraryItemDomain.getRegisterGroup().getQuotation().getEmployee().getPerson().setLastName(entity.getRegisterGroup().getQuotation().getEmployee().getPerson().getLastName());
                        }
                    }
                }
                if (entity.getRegisterGroup().getIdiom() != null &&
                        entity.getRegisterGroup().getIdiom().getId() != null &&
                        !entity.getRegisterGroup().getIdiom().getId().isEmpty()){
                    itineraryItemDomain.getRegisterGroup().setIdiom(new IdiomDomain());
                    itineraryItemDomain.getRegisterGroup().getIdiom().setId(entity.getRegisterGroup().getIdiom().getId());
                    itineraryItemDomain.getRegisterGroup().getIdiom().setName(entity.getRegisterGroup().getIdiom().getName());
                }
            }
            if (entity.getTour() != null &&
                    entity.getTour().getId() != null &&
                    !entity.getTour().getId().isEmpty()) {
                itineraryItemDomain.setTour( new TourDomain() );
                itineraryItemDomain.getTour().setId( entity.getTour().getId() );
                itineraryItemDomain.getTour().setName( entity.getTour().getName() );
            }

            itineraryItemDomain.setObservation( entity.getObservation() );
            itineraryItemDomain.setItineraryDateTime( entity.getItineraryDateTime() );
            if (entity.getItineraryDate() != null){
                itineraryItemDomain.setItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getItineraryDate().setId( entity.getItineraryDate().getId() );
                itineraryItemDomain.getItineraryDate().setItineraryDate(entity.getItineraryDate().getItineraryDate());
                itineraryItemDomain.getItineraryDate().setItemItinerary(entity.getItineraryDate().getItemItinerary());
            }
            itineraryItemDomain.setPaxTotal(entity.getPaxTotal());
            itineraryItemDomain.setPaxAdultTotal(entity.getPaxAdultTotal());
            itineraryItemDomain.setLodgingForOperatorStr(entity.getLodgingForOperatorStr() );
            itineraryItemDomain.setStateItineraryService( entity.getStateItineraryService() );
            itineraryItemDomain.setNote( entity.getNote() );
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseTransferControlByServiceBible(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            if (entity.getSupplierType() != null){
                itineraryItemDomain.setSupplierType( new SupplierTypeDomain() );
                itineraryItemDomain.getSupplierType().setId( entity.getSupplierType().getId() );
                itineraryItemDomain.getSupplierType().setName( entity.getSupplierType().getName() );
                itineraryItemDomain.getSupplierType().setIdentify( entity.getSupplierType().getIdentify() );
            }
            if (entity.getSupplier() != null){
                itineraryItemDomain.setSupplier( new SupplierDomain() );
                itineraryItemDomain.getSupplier().setId( entity.getSupplier().getId() );
                itineraryItemDomain.getSupplier().setName( entity.getSupplier().getName() );
                if (entity.getSupplier().getPartner() != null){
                    itineraryItemDomain.getSupplier().setPartner( new PartnerDomain());
                    itineraryItemDomain.getSupplier().getPartner().setId(entity.getSupplier().getPartner().getId());
                    if (entity.getSupplier().getPartner().getAddress() != null){
                        itineraryItemDomain.getSupplier().getPartner().setAddress(new AddressEmbeddedDomain());
                        if (entity.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                            itineraryItemDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                            itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
                            itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
                        }
                    }
                }
            }
            if (entity.getCustomer() != null){
                itineraryItemDomain.setCustomer( new CustomerDomain() );
                itineraryItemDomain.getCustomer().setId( entity.getCustomer().getId() );
                itineraryItemDomain.getCustomer().setName( entity.getCustomer().getName() );
            }
            if (entity.getService() != null){
                itineraryItemDomain.setService( new ServiceDomain() );
                itineraryItemDomain.getService().setId( entity.getService().getId() );
                itineraryItemDomain.getService().setName( entity.getService().getName() );
            }
            if (entity.getRegisterGroup() != null){
                itineraryItemDomain.setRegisterGroup( new RegisterGroupDomain() );
                itineraryItemDomain.getRegisterGroup().setId( entity.getRegisterGroup().getId() );
                itineraryItemDomain.getRegisterGroup().setName( entity.getRegisterGroup().getName() );
                itineraryItemDomain.getRegisterGroup().setIdentify( entity.getRegisterGroup().getIdentify() );
                itineraryItemDomain.getRegisterGroup().setLink( entity.getRegisterGroup().getLink() );
                itineraryItemDomain.getRegisterGroup().setLinkWetravel( entity.getRegisterGroup().getLinkWetravel() );
                itineraryItemDomain.getRegisterGroup().setLinkBitrix( entity.getRegisterGroup().getLinkBitrix() );
                itineraryItemDomain.getRegisterGroup().setMobilePassengerPrincipal(entity.getRegisterGroup().getMobilePassengerPrincipal());
                itineraryItemDomain.getRegisterGroup().setWhatsappPassengerPrincipal(entity.getRegisterGroup().getWhatsappPassengerPrincipal());
                itineraryItemDomain.getRegisterGroup().setEmailPassengerPrincipal(entity.getRegisterGroup().getEmailPassengerPrincipal());
                itineraryItemDomain.getRegisterGroup().setStateRegisterGroup(entity.getRegisterGroup().getStateRegisterGroup());
                if (entity.getRegisterGroup().getQuotation() != null &&
                        entity.getRegisterGroup().getQuotation().getId() != null &&
                        !entity.getRegisterGroup().getQuotation().getId().isEmpty()){
                    itineraryItemDomain.getRegisterGroup().setQuotation(new QuotationDomain());
                    itineraryItemDomain.getRegisterGroup().getQuotation().setId(entity.getRegisterGroup().getQuotation().getId());
                    if (entity.getRegisterGroup().getQuotation().getEmployee() != null &&
                            entity.getRegisterGroup().getQuotation().getEmployee().getId() != null &&
                            !entity.getRegisterGroup().getQuotation().getEmployee().getId().isEmpty()){
                        itineraryItemDomain.getRegisterGroup().getQuotation().setEmployee(new EmployeeDomain());
                        itineraryItemDomain.getRegisterGroup().getQuotation().getEmployee().setId(entity.getRegisterGroup().getQuotation().getEmployee().getId());
                        if (entity.getRegisterGroup().getQuotation().getEmployee().getPerson() != null &&
                                entity.getRegisterGroup().getQuotation().getEmployee().getPerson().getId() != null &&
                                !entity.getRegisterGroup().getQuotation().getEmployee().getPerson().getId().isEmpty()){
                            itineraryItemDomain.getRegisterGroup().getQuotation().getEmployee().setPerson(new PersonDomain());
                            itineraryItemDomain.getRegisterGroup().getQuotation().getEmployee().getPerson().setId(entity.getRegisterGroup().getQuotation().getEmployee().getPerson().getId());
                            itineraryItemDomain.getRegisterGroup().getQuotation().getEmployee().getPerson().setFirstName(entity.getRegisterGroup().getQuotation().getEmployee().getPerson().getFirstName());
                            itineraryItemDomain.getRegisterGroup().getQuotation().getEmployee().getPerson().setLastName(entity.getRegisterGroup().getQuotation().getEmployee().getPerson().getLastName());
                        }
                    }
                }
                if (entity.getRegisterGroup().getIdiom() != null &&
                        entity.getRegisterGroup().getIdiom().getId() != null &&
                        !entity.getRegisterGroup().getIdiom().getId().isEmpty()){
                    itineraryItemDomain.getRegisterGroup().setIdiom(new IdiomDomain());
                    itineraryItemDomain.getRegisterGroup().getIdiom().setId(entity.getRegisterGroup().getIdiom().getId());
                    itineraryItemDomain.getRegisterGroup().getIdiom().setName(entity.getRegisterGroup().getIdiom().getName());
                }
            }
            if (entity.getTour() != null &&
                    entity.getTour().getId() != null &&
                    !entity.getTour().getId().isEmpty()) {
                itineraryItemDomain.setTour( new TourDomain() );
                itineraryItemDomain.getTour().setId( entity.getTour().getId() );
                itineraryItemDomain.getTour().setName( entity.getTour().getName() );
            }

            itineraryItemDomain.setObservation( entity.getObservation() );
            itineraryItemDomain.setItineraryDateTime( entity.getItineraryDateTime() );
            if (entity.getItineraryDate() != null){
                itineraryItemDomain.setItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getItineraryDate().setId( entity.getItineraryDate().getId() );
                itineraryItemDomain.getItineraryDate().setItineraryDate(entity.getItineraryDate().getItineraryDate());
                itineraryItemDomain.getItineraryDate().setItemItinerary(entity.getItineraryDate().getItemItinerary());
            }
            itineraryItemDomain.setPaxTotal(entity.getPaxTotal());
            itineraryItemDomain.setPaxAdultTotal(entity.getPaxAdultTotal());
            itineraryItemDomain.setLodgingForOperatorStr(entity.getLodgingForOperatorStr() );
            itineraryItemDomain.setStateItineraryService( entity.getStateItineraryService() );
            itineraryItemDomain.setNote( entity.getNote() );

            itineraryItemDomain.setNoteInvoice(entity.getNoteInvoice());
            itineraryItemDomain.setStateServiceInvoice(entity.getStateServiceInvoice());
            itineraryItemDomain.setDocumentDateInvoice(entity.getDocumentDateInvoice());
            return itineraryItemDomain;
        }).toList();
    }

    public PageDTO<ItineraryItemDomain> toDomainPageCaseTransferControl(Page<ItineraryItemEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ItineraryItemDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseTransferControl(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public PageDTO<ItineraryItemDomain> toDomainPageCaseTransferControlByTourBible(Page<ItineraryItemEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ItineraryItemDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseTransferControlByTourBible(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public PageDTO<ItineraryItemDomain> toDomainPageCaseTransferControlByServiceBible(Page<ItineraryItemEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ItineraryItemDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseTransferControlByServiceBible(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public List<ItineraryItemDomain> toDomainPageCaseTransferControlByTourBibleExport(List<ItineraryItemEntity> list) throws RuntimeException {
        return toDomainAllSimpleToDomainCaseTransferControlByTourBible(list);
    }

    public List<ItineraryItemDomain> toDomainPageCaseTransferControlByServiceBibleExport(List<ItineraryItemEntity> list) throws RuntimeException {
        return toDomainAllSimpleToDomainCaseTransferControlByServiceBible(list);
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseValidationExistBooking(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()) {
                itineraryItemDomain.setSupplier( new SupplierDomain() );
                itineraryItemDomain.getSupplier().setId( entity.getSupplier().getId() );
            }
            if (entity.getSupplierType() != null &&
                    entity.getSupplierType().getId() != null &&
                    !entity.getSupplierType().getId().isEmpty()) {
                itineraryItemDomain.setSupplierType( new SupplierTypeDomain() );
                itineraryItemDomain.getSupplierType().setId( entity.getSupplierType().getId() );
            }
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseDeleteTours(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            itineraryItemDomain.setItineraryDate( new ItineraryDateDomain() );
            itineraryItemDomain.getItineraryDate().setId( entity.getItineraryDate().getId() );
            itineraryItemDomain.getItineraryDate().setItineraryDate( entity.getItineraryDate().getItineraryDate() );
            itineraryItemDomain.setItineraryDateTime( entity.getItineraryDateTime() );
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseToolUpdateCustomer(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            if (entity.getRegisterGroup() != null) {
                itineraryItemDomain.setRegisterGroup(new RegisterGroupDomain());
                itineraryItemDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                if (entity.getRegisterGroup().getCustomer() != null) {
                    itineraryItemDomain.getRegisterGroup().setCustomer(new CustomerDomain());
                    itineraryItemDomain.getRegisterGroup().getCustomer().setId(entity.getRegisterGroup().getCustomer().getId());
                }
            }
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseServiceRequest(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            itineraryItemDomain.setLodgingForOperatorStr(entity.getLodgingForOperatorStr() );
            itineraryItemDomain.setPaxAdultTotal(entity.getPaxAdultTotal());
            itineraryItemDomain.setObservation(entity.getObservation());
            itineraryItemDomain.setItineraryDateTime(entity.getItineraryDateTime());
            if (entity.getService() != null) {
                itineraryItemDomain.setService(new ServiceDomain());
                itineraryItemDomain.getService().setId(entity.getService().getId());
                itineraryItemDomain.getService().setName(entity.getService().getName());
            }
            if (entity.getItineraryDate() != null) {
                itineraryItemDomain.setItineraryDate(new ItineraryDateDomain());
                itineraryItemDomain.getItineraryDate().setId(entity.getItineraryDate().getId());
                itineraryItemDomain.getItineraryDate().setItineraryDate(entity.getItineraryDate().getItineraryDate());
            }
            if (entity.getRegisterGroup() != null) {
                itineraryItemDomain.setRegisterGroup(new RegisterGroupDomain());
                itineraryItemDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                itineraryItemDomain.getRegisterGroup().setWhatsappPassengerPrincipal(entity.getRegisterGroup().getWhatsappPassengerPrincipal());
                if (entity.getRegisterGroup().getIdiom() != null) {
                    itineraryItemDomain.getRegisterGroup().setIdiom(new IdiomDomain());
                    itineraryItemDomain.getRegisterGroup().getIdiom().setId(entity.getRegisterGroup().getIdiom().getId());
                    itineraryItemDomain.getRegisterGroup().getIdiom().setName(entity.getRegisterGroup().getIdiom().getName());
                }
                if (entity.getRegisterGroup().getCustomer() != null) {
                    itineraryItemDomain.getRegisterGroup().setCustomer(new CustomerDomain());
                    itineraryItemDomain.getRegisterGroup().getCustomer().setId(entity.getRegisterGroup().getCustomer().getId());
                    itineraryItemDomain.getRegisterGroup().getCustomer().setName(entity.getRegisterGroup().getCustomer().getName());
                }
            }
            if (entity.getCustomer() != null) {
                itineraryItemDomain.setCustomer(new CustomerDomain());
                itineraryItemDomain.getCustomer().setId(entity.getCustomer().getId());
                itineraryItemDomain.getCustomer().setName(entity.getCustomer().getName());
            }
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseDeleteByRegisterGroup(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseAdvancedByRegisterGroup(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            if (entity.getSupplierType() != null &&
                    entity.getSupplierType().getId() != null &&
                    !entity.getSupplierType().getId().isEmpty()) {
                itineraryItemDomain.setSupplierType( new SupplierTypeDomain() );
                itineraryItemDomain.getSupplierType().setId( entity.getSupplierType().getId() );
                itineraryItemDomain.getSupplierType().setName( entity.getSupplierType().getName() );
                itineraryItemDomain.getSupplierType().setDescription( entity.getSupplierType().getDescription() );
                itineraryItemDomain.getSupplierType().setIdentify( entity.getSupplierType().getIdentify() );
            }
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()) {
                itineraryItemDomain.setSupplier( new SupplierDomain() );
                itineraryItemDomain.getSupplier().setId( entity.getSupplier().getId() );
                itineraryItemDomain.getSupplier().setName( entity.getSupplier().getName() );
                itineraryItemDomain.getSupplier().setIdentify( entity.getSupplier().getIdentify() );
                itineraryItemDomain.getSupplier().setDescription( entity.getSupplier().getDescription() );
            }
            if (entity.getService() != null &&
                    entity.getService().getId() != null &&
                    !entity.getService().getId().isEmpty()) {
                itineraryItemDomain.setService( new ServiceDomain() );
                itineraryItemDomain.getService().setId( entity.getService().getId() );
                itineraryItemDomain.getService().setName( entity.getService().getName() );
                itineraryItemDomain.getService().setIdentify( entity.getService().getIdentify() );
                itineraryItemDomain.getService().setDescription( entity.getService().getName() );
            }
            itineraryItemDomain.setServiceGroup( entity.getServiceGroup() );
            itineraryItemDomain.setObservation( entity.getObservation() );
            itineraryItemDomain.setItineraryDateTime( entity.getItineraryDateTime() );
            if (entity.getItineraryDate() != null &&
                    entity.getItineraryDate().getId() != null &&
                    !entity.getItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getItineraryDate().setId( entity.getItineraryDate().getId() );
                itineraryItemDomain.getItineraryDate().setName( entity.getItineraryDate().getName() );
                itineraryItemDomain.getItineraryDate().setIdentify( entity.getItineraryDate().getIdentify() );
                itineraryItemDomain.getItineraryDate().setDescription( entity.getItineraryDate().getDescription() );
                itineraryItemDomain.getItineraryDate().setItineraryDate(entity.getItineraryDate().getItineraryDate());
                itineraryItemDomain.getItineraryDate().setItemItinerary(entity.getItineraryDate().getItemItinerary());
            }
            itineraryItemDomain.setPaxTotal(entity.getPaxTotal());
            itineraryItemDomain.setPaxAdultTotal(entity.getPaxAdultTotal());
            itineraryItemDomain.setPaxChildrenTotal( entity.getPaxChildrenTotal() );
            itineraryItemDomain.setPaxTcTotal( entity.getPaxTcTotal() );
            itineraryItemDomain.setPaxTcLocalTotal( entity.getPaxTcLocalTotal() );
            itineraryItemDomain.setPaxGuideTotal( entity.getPaxGuideTotal() );

            itineraryItemDomain.setLodgingCantSwb( entity.getLodgingCantSwb() );
            itineraryItemDomain.setLodgingCantMat( entity.getLodgingCantMat() );
            itineraryItemDomain.setLodgingCantTwin( entity.getLodgingCantTwin() );
            itineraryItemDomain.setLodgingCantTrp( entity.getLodgingCantTrp() );

            itineraryItemDomain.setLodgingCategory( new LodgingCategoryDomain() );
            itineraryItemDomain.getLodgingCategory().setId( entity.getLodgingCategory().getId() );

            itineraryItemDomain.setLodgingCategoryStr( entity.getLodgingCategoryStr() );
            itineraryItemDomain.setLodgingCantBreakfast( entity.getLodgingCantBreakfast() );
            itineraryItemDomain.setLodgingCantLunch( entity.getLodgingCantLunch() );
            itineraryItemDomain.setLodgingCantDinner( entity.getLodgingCantDinner() );
            itineraryItemDomain.setLodgingCantBoxLunch( entity.getLodgingCantBoxLunch() );
            itineraryItemDomain.setLodgingCantBreakfastDateTime( entity.getLodgingCantBreakfastDateTime() );
            itineraryItemDomain.setLodgingCantLunchDateTime( entity.getLodgingCantLunchDateTime() );
            itineraryItemDomain.setLodgingCantDinnerDateTime( entity.getLodgingCantDinnerDateTime() );
            itineraryItemDomain.setLodgingCantBoxLunchDateTime( entity.getLodgingCantBoxLunchDateTime() );
            itineraryItemDomain.setLodgingCantBreakfastDate( entity.getLodgingCantBreakfastDate() );
            itineraryItemDomain.setLodgingCantLunchDate( entity.getLodgingCantLunchDate() );
            itineraryItemDomain.setLodgingCantDinnerDate( entity.getLodgingCantDinnerDate() );
            itineraryItemDomain.setLodgingCantBoxLunchDate( entity.getLodgingCantBoxLunchDate() );
            if (entity.getLodgingCantBreakfastItineraryDate() != null &&
                    entity.getLodgingCantBreakfastItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setId(entity.getLodgingCantBreakfastItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setIndex(entity.getLodgingCantBreakfastItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setItineraryDate(entity.getLodgingCantBreakfastItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setName(entity.getLodgingCantBreakfastItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setDescription(entity.getLodgingCantBreakfastItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setIdentify(entity.getLodgingCantBreakfastItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setItemItinerary(entity.getLodgingCantBreakfastItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBreakfastItineraryDate( null );
            }
            if (entity.getLodgingCantLunchItineraryDate() != null &&
                    entity.getLodgingCantLunchItineraryDate().getId() != null &&
                    !entity.getLodgingCantLunchItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantLunchItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setId(entity.getLodgingCantLunchItineraryDate().getId());
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setIndex(entity.getLodgingCantLunchItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setItineraryDate(entity.getLodgingCantLunchItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setName(entity.getLodgingCantLunchItineraryDate().getName());
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setDescription(entity.getLodgingCantLunchItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setIdentify(entity.getLodgingCantLunchItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setItemItinerary(entity.getLodgingCantBreakfastItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantLunchItineraryDate( null );
            }
            if (entity.getLodgingCantDinnerItineraryDate() != null &&
                    entity.getLodgingCantDinnerItineraryDate().getId() != null &&
                    !entity.getLodgingCantDinnerItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantDinnerItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setId(entity.getLodgingCantDinnerItineraryDate().getId());
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setIndex(entity.getLodgingCantDinnerItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setItineraryDate(entity.getLodgingCantDinnerItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setName(entity.getLodgingCantDinnerItineraryDate().getName());
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setDescription(entity.getLodgingCantDinnerItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setIdentify(entity.getLodgingCantDinnerItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setItemItinerary(entity.getLodgingCantDinnerItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantDinnerItineraryDate( null );
            }
            if (entity.getLodgingCantBoxLunchItineraryDate() != null &&
                    entity.getLodgingCantBoxLunchItineraryDate().getId() != null &&
                    !entity.getLodgingCantBoxLunchItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBoxLunchItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setId(entity.getLodgingCantBoxLunchItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setIndex(entity.getLodgingCantBoxLunchItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setItineraryDate(entity.getLodgingCantBoxLunchItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setName(entity.getLodgingCantBoxLunchItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setDescription(entity.getLodgingCantBoxLunchItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setIdentify(entity.getLodgingCantBoxLunchItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setItemItinerary(entity.getLodgingCantBoxLunchItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBoxLunchItineraryDate( null );
            }
            itineraryItemDomain.setLodgingCantNight( entity.getLodgingCantNight() );
            itineraryItemDomain.setLodgingCantBreakfastTcGuide( entity.getLodgingCantBreakfastTcGuide() );
            itineraryItemDomain.setLodgingCantLunchTcGuide( entity.getLodgingCantLunchTcGuide() );
            itineraryItemDomain.setLodgingCantDinnerTcGuide( entity.getLodgingCantDinnerTcGuide() );
            itineraryItemDomain.setLodgingCantBoxLunchTcGuide( entity.getLodgingCantBoxLunchTcGuide() );
            itineraryItemDomain.setLodgingCantBreakfastTcGuideDateTime( entity.getLodgingCantBreakfastTcGuideDateTime() );
            itineraryItemDomain.setLodgingCantLunchTcGuideDateTime( entity.getLodgingCantLunchTcGuideDateTime() );
            itineraryItemDomain.setLodgingCantDinnerTcGuideDateTime( entity.getLodgingCantDinnerTcGuideDateTime() );
            itineraryItemDomain.setLodgingCantBoxLunchTcGuideDateTime( entity.getLodgingCantBoxLunchTcGuideDateTime() );
            itineraryItemDomain.setLodgingCantBreakfastTcGuideDate( entity.getLodgingCantBreakfastTcGuideDate() );
            itineraryItemDomain.setLodgingCantLunchTcGuideDate( entity.getLodgingCantLunchTcGuideDate() );
            itineraryItemDomain.setLodgingCantDinnerTcGuideDate( entity.getLodgingCantDinnerTcGuideDate() );
            itineraryItemDomain.setLodgingCantBoxLunchTcGuideDate( entity.getLodgingCantBoxLunchTcGuideDate() );
            if (entity.getLodgingCantBreakfastTcGuideItineraryDate() != null &&
                    entity.getLodgingCantBreakfastTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setId(entity.getLodgingCantBreakfastTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setIndex(entity.getLodgingCantBreakfastTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setItineraryDate(entity.getLodgingCantBreakfastTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setName(entity.getLodgingCantBreakfastTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setDescription(entity.getLodgingCantBreakfastTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setIdentify(entity.getLodgingCantBreakfastTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setItemItinerary(entity.getLodgingCantBreakfastTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBreakfastTcGuideItineraryDate( null );
            }
            if (entity.getLodgingCantLunchTcGuideItineraryDate() != null &&
                    entity.getLodgingCantLunchTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantLunchTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantLunchTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setId(entity.getLodgingCantLunchTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setIndex(entity.getLodgingCantLunchTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setItineraryDate(entity.getLodgingCantLunchTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setName(entity.getLodgingCantLunchTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setDescription(entity.getLodgingCantLunchTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setIdentify(entity.getLodgingCantLunchTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setItemItinerary(entity.getLodgingCantLunchTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantLunchTcGuideItineraryDate( null );
            }
            if (entity.getLodgingCantDinnerTcGuideItineraryDate() != null &&
                    entity.getLodgingCantDinnerTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantDinnerTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantDinnerTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setId(entity.getLodgingCantDinnerTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setIndex(entity.getLodgingCantDinnerTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setItineraryDate(entity.getLodgingCantDinnerTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setName(entity.getLodgingCantDinnerTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setDescription(entity.getLodgingCantDinnerTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setIdentify(entity.getLodgingCantDinnerTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setItemItinerary(entity.getLodgingCantDinnerTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantDinnerTcGuideItineraryDate( null );
            }
            if (entity.getLodgingCantBoxLunchTcGuideItineraryDate() != null &&
                    entity.getLodgingCantBoxLunchTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBoxLunchTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBoxLunchTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setId(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setIndex(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setItineraryDate(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setName(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setDescription(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setIdentify(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setItemItinerary(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBoxLunchTcGuideItineraryDate( null );
            }
            itineraryItemDomain.setLodgingCantSwbTcGuide( entity.getLodgingCantSwbTcGuide() );
            itineraryItemDomain.setLodgingCantTwinTcGuide( entity.getLodgingCantTwinTcGuide() );
            itineraryItemDomain.setLodgingCantBreakfastTcLocalGuide( entity.getLodgingCantBreakfastTcLocalGuide() );
            itineraryItemDomain.setLodgingCantLunchTcLocalGuide( entity.getLodgingCantLunchTcLocalGuide() );
            itineraryItemDomain.setLodgingCantDinnerTcLocalGuide( entity.getLodgingCantDinnerTcLocalGuide() );
            itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuide( entity.getLodgingCantBoxLunchTcLocalGuide() );
            itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideDateTime( entity.getLodgingCantBreakfastTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgingCantLunchTcLocalGuideDateTime( entity.getLodgingCantLunchTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgingCantDinnerTcLocalGuideDateTime( entity.getLodgingCantDinnerTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideDateTime( entity.getLodgingCantBoxLunchTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideDate( entity.getLodgingCantBreakfastTcLocalGuideDate() );
            itineraryItemDomain.setLodgingCantLunchTcLocalGuideDate( entity.getLodgingCantLunchTcLocalGuideDate() );
            itineraryItemDomain.setLodgingCantDinnerTcLocalGuideDate( entity.getLodgingCantDinnerTcLocalGuideDate() );
            itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideDate( entity.getLodgingCantBoxLunchTcLocalGuideDate() );
            if (entity.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null &&
                    entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setId(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setIndex(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setName(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setDescription(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setIdentify(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgingCantLunchTcLocalGuideItineraryDate() != null &&
                    entity.getLodgingCantLunchTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantLunchTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setId(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setIndex(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setName(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setDescription(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setIdentify(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantLunchTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgingCantDinnerTcLocalGuideItineraryDate() != null &&
                    entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantDinnerTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setId(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setIndex(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setName(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setDescription(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setIdentify(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantDinnerTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null &&
                    entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setId(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setIndex(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setName(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setDescription(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setIdentify(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideItineraryDate( null );
            }
            itineraryItemDomain.setLodgingCantSwbTcLocalGuide( entity.getLodgingCantSwbTcLocalGuide() );
            itineraryItemDomain.setLodgingCantTwinTcLocalGuide( entity.getLodgingCantTwinTcLocalGuide() );
            itineraryItemDomain.setLodgingCantBreakfastGuide( entity.getLodgingCantBreakfastGuide() );
            itineraryItemDomain.setLodgingCantLunchGuide( entity.getLodgingCantLunchGuide() );
            itineraryItemDomain.setLodgingCantDinnerGuide( entity.getLodgingCantDinnerGuide() );
            itineraryItemDomain.setLodgingCantBoxLunchGuide( entity.getLodgingCantBoxLunchGuide() );
            itineraryItemDomain.setLodgingCantBreakfastGuideDateTime( entity.getLodgingCantBreakfastGuideDateTime() );
            itineraryItemDomain.setLodgingCantLunchGuideDateTime( entity.getLodgingCantLunchGuideDateTime() );
            itineraryItemDomain.setLodgingCantDinnerGuideDateTime( entity.getLodgingCantDinnerGuideDateTime() );
            itineraryItemDomain.setLodgingCantBoxLunchGuideDateTime( entity.getLodgingCantBoxLunchGuideDateTime() );
            itineraryItemDomain.setLodgingCantBreakfastGuideDate( entity.getLodgingCantBreakfastGuideDate() );
            itineraryItemDomain.setLodgingCantLunchGuideDate( entity.getLodgingCantLunchGuideDate() );
            itineraryItemDomain.setLodgingCantDinnerGuideDate( entity.getLodgingCantDinnerGuideDate() );
            itineraryItemDomain.setLodgingCantBoxLunchGuideDate( entity.getLodgingCantBoxLunchGuideDate() );
            if (entity.getLodgingCantBreakfastGuideItineraryDate() != null &&
                    entity.getLodgingCantBreakfastGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setId(entity.getLodgingCantBreakfastGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setIndex(entity.getLodgingCantBreakfastGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setItineraryDate(entity.getLodgingCantBreakfastGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setName(entity.getLodgingCantBreakfastGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setDescription(entity.getLodgingCantBreakfastGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setIdentify(entity.getLodgingCantBreakfastGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setItemItinerary(entity.getLodgingCantBreakfastGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBreakfastGuideItineraryDate( null );
            }
            if (entity.getLodgingCantLunchGuideItineraryDate() != null &&
                    entity.getLodgingCantLunchGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantLunchGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantLunchGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setId(entity.getLodgingCantLunchGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setIndex(entity.getLodgingCantLunchGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setItineraryDate(entity.getLodgingCantLunchGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setName(entity.getLodgingCantLunchGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setDescription(entity.getLodgingCantLunchGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setIdentify(entity.getLodgingCantLunchGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setItemItinerary(entity.getLodgingCantLunchGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantLunchGuideItineraryDate( null );
            }
            if (entity.getLodgingCantDinnerGuideItineraryDate() != null &&
                    entity.getLodgingCantDinnerGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantDinnerGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantDinnerGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setId(entity.getLodgingCantDinnerGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setIndex(entity.getLodgingCantDinnerGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setItineraryDate(entity.getLodgingCantDinnerGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setName(entity.getLodgingCantDinnerGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setDescription(entity.getLodgingCantDinnerGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setIdentify(entity.getLodgingCantDinnerGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setItemItinerary(entity.getLodgingCantDinnerGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantDinnerGuideItineraryDate( null );
            }
            if (entity.getLodgingCantBoxLunchGuideItineraryDate() != null &&
                    entity.getLodgingCantBoxLunchGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBoxLunchGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBoxLunchGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setId(entity.getLodgingCantBoxLunchGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setIndex(entity.getLodgingCantBoxLunchGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setItineraryDate(entity.getLodgingCantBoxLunchGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setName(entity.getLodgingCantBoxLunchGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setDescription(entity.getLodgingCantBoxLunchGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setIdentify(entity.getLodgingCantBoxLunchGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setItemItinerary(entity.getLodgingCantBoxLunchGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBoxLunchGuideItineraryDate( null );
            }

            itineraryItemDomain.setLodgingCantSwbGuide( entity.getLodgingCantSwbGuide() );
            itineraryItemDomain.setLodgingCantTwinGuide( entity.getLodgingCantTwinGuide() );
            itineraryItemDomain.setLodgingGuideCantNight( entity.getLodgingGuideCantNight() );
            itineraryItemDomain.setLodgeCantSwb( entity.getLodgeCantSwb() );
            itineraryItemDomain.setLodgeCantMat( entity.getLodgeCantMat() );
            itineraryItemDomain.setLodgeCantTwin( entity.getLodgeCantTwin() );
            itineraryItemDomain.setLodgeCantTrp( entity.getLodgeCantTrp() );

            itineraryItemDomain.setLodgeCategory( new LodgingCategoryDomain() );
            itineraryItemDomain.getLodgeCategory().setId( entity.getLodgeCategory().getId() );

            itineraryItemDomain.setLodgeCategoryStr( entity.getLodgeCategoryStr() );
            itineraryItemDomain.setLodgeCantBreakfast( entity.getLodgeCantBreakfast() );
            itineraryItemDomain.setLodgeCantLunch( entity.getLodgeCantLunch() );
            itineraryItemDomain.setLodgeCantDinner( entity.getLodgeCantDinner() );
            itineraryItemDomain.setLodgeCantBoxLunch( entity.getLodgeCantBoxLunch() );
            itineraryItemDomain.setLodgeCantBreakfastDateTime( entity.getLodgeCantBreakfastDateTime() );
            itineraryItemDomain.setLodgeCantLunchDateTime( entity.getLodgeCantLunchDateTime() );
            itineraryItemDomain.setLodgeCantDinnerDateTime( entity.getLodgeCantDinnerDateTime() );
            itineraryItemDomain.setLodgeCantBoxLunchDateTime( entity.getLodgeCantBoxLunchDateTime() );
            itineraryItemDomain.setLodgeCantBreakfastDate( entity.getLodgeCantBreakfastDate() );
            itineraryItemDomain.setLodgeCantLunchDate( entity.getLodgeCantLunchDate() );
            itineraryItemDomain.setLodgeCantDinnerDate( entity.getLodgeCantDinnerDate() );
            itineraryItemDomain.setLodgeCantBoxLunchDate( entity.getLodgeCantBoxLunchDate() );
            if (entity.getLodgeCantBreakfastItineraryDate() != null &&
                    entity.getLodgeCantBreakfastItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setId(entity.getLodgeCantBreakfastItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setIndex(entity.getLodgeCantBreakfastItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setItineraryDate(entity.getLodgeCantBreakfastItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setName(entity.getLodgeCantBreakfastItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setDescription(entity.getLodgeCantBreakfastItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setIdentify(entity.getLodgeCantBreakfastItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setItemItinerary(entity.getLodgeCantBreakfastItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBreakfastItineraryDate( null );
            }
            if (entity.getLodgeCantLunchItineraryDate() != null &&
                    entity.getLodgeCantLunchItineraryDate().getId() != null &&
                    !entity.getLodgeCantLunchItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantLunchItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setId(entity.getLodgeCantLunchItineraryDate().getId());
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setIndex(entity.getLodgeCantLunchItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setItineraryDate(entity.getLodgeCantLunchItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setName(entity.getLodgeCantLunchItineraryDate().getName());
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setDescription(entity.getLodgeCantLunchItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setIdentify(entity.getLodgeCantLunchItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setItemItinerary(entity.getLodgeCantLunchItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantLunchItineraryDate( null );
            }
            if (entity.getLodgeCantDinnerItineraryDate() != null &&
                    entity.getLodgeCantDinnerItineraryDate().getId() != null &&
                    !entity.getLodgeCantDinnerItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantDinnerItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setId(entity.getLodgeCantDinnerItineraryDate().getId());
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setIndex(entity.getLodgeCantDinnerItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setItineraryDate(entity.getLodgeCantDinnerItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setName(entity.getLodgeCantDinnerItineraryDate().getName());
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setDescription(entity.getLodgeCantDinnerItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setIdentify(entity.getLodgeCantDinnerItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setItemItinerary(entity.getLodgeCantDinnerItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantDinnerItineraryDate( null );
            }
            if (entity.getLodgeCantBoxLunchItineraryDate() != null &&
                    entity.getLodgeCantBoxLunchItineraryDate().getId() != null &&
                    !entity.getLodgeCantBoxLunchItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBoxLunchItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setId(entity.getLodgeCantBoxLunchItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setIndex(entity.getLodgeCantBoxLunchItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setItineraryDate(entity.getLodgeCantBoxLunchItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setName(entity.getLodgeCantBoxLunchItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setDescription(entity.getLodgeCantBoxLunchItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setIdentify(entity.getLodgeCantBoxLunchItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setItemItinerary(entity.getLodgeCantBoxLunchItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBoxLunchItineraryDate( null );
            }

            itineraryItemDomain.setLodgeCantNight( entity.getLodgeCantNight() );
            itineraryItemDomain.setLodgeCantBreakfastTcGuide( entity.getLodgeCantBreakfastTcGuide() );
            itineraryItemDomain.setLodgeCantLunchTcGuide( entity.getLodgeCantLunchTcGuide() );
            itineraryItemDomain.setLodgeCantDinnerTcGuide( entity.getLodgeCantDinnerTcGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchTcGuide( entity.getLodgeCantBoxLunchTcGuide() );
            itineraryItemDomain.setLodgeCantBreakfastTcGuideDateTime( entity.getLodgeCantBreakfastTcGuideDateTime() );
            itineraryItemDomain.setLodgeCantLunchTcGuideDateTime( entity.getLodgeCantLunchTcGuideDateTime() );
            itineraryItemDomain.setLodgeCantDinnerTcGuideDateTime( entity.getLodgeCantDinnerTcGuideDateTime() );
            itineraryItemDomain.setLodgeCantBoxLunchTcGuideDateTime( entity.getLodgeCantBoxLunchTcGuideDateTime() );
            itineraryItemDomain.setLodgeCantBreakfastTcGuideDate( entity.getLodgeCantBreakfastTcGuideDate() );
            itineraryItemDomain.setLodgeCantLunchTcGuideDate( entity.getLodgeCantLunchTcGuideDate() );
            itineraryItemDomain.setLodgeCantDinnerTcGuideDate( entity.getLodgeCantDinnerTcGuideDate() );
            itineraryItemDomain.setLodgeCantBoxLunchTcGuideDate( entity.getLodgeCantBoxLunchTcGuideDate() );
            if (entity.getLodgeCantBreakfastTcGuideItineraryDate() != null &&
                    entity.getLodgeCantBreakfastTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setId(entity.getLodgeCantBreakfastTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setIndex(entity.getLodgeCantBreakfastTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setItineraryDate(entity.getLodgeCantBreakfastTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setName(entity.getLodgeCantBreakfastTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setDescription(entity.getLodgeCantBreakfastTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setIdentify(entity.getLodgeCantBreakfastTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setItemItinerary(entity.getLodgeCantBreakfastTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBreakfastTcGuideItineraryDate( null );
            }
            if (entity.getLodgeCantLunchTcGuideItineraryDate() != null &&
                    entity.getLodgeCantLunchTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantLunchTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantLunchTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setId(entity.getLodgeCantLunchTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setIndex(entity.getLodgeCantLunchTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setItineraryDate(entity.getLodgeCantLunchTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setName(entity.getLodgeCantLunchTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setDescription(entity.getLodgeCantLunchTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setIdentify(entity.getLodgeCantLunchTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setItemItinerary(entity.getLodgeCantLunchTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantLunchTcGuideItineraryDate( null );
            }
            if (entity.getLodgeCantDinnerTcGuideItineraryDate() != null &&
                    entity.getLodgeCantDinnerTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantDinnerTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantDinnerTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setId(entity.getLodgeCantDinnerTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setIndex(entity.getLodgeCantDinnerTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setItineraryDate(entity.getLodgeCantDinnerTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setName(entity.getLodgeCantDinnerTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setDescription(entity.getLodgeCantDinnerTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setIdentify(entity.getLodgeCantDinnerTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setItemItinerary(entity.getLodgeCantDinnerTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantDinnerTcGuideItineraryDate( null );
            }
            if (entity.getLodgeCantBoxLunchTcGuideItineraryDate() != null &&
                    entity.getLodgeCantBoxLunchTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBoxLunchTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBoxLunchTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setId(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setIndex(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setItineraryDate(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setName(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setDescription(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setIdentify(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setItemItinerary(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBoxLunchTcGuideItineraryDate( null );
            }

            itineraryItemDomain.setLodgeCantSwbTcGuide( entity.getLodgeCantSwbTcGuide() );
            itineraryItemDomain.setLodgeCantTwinTcGuide( entity.getLodgeCantTwinTcGuide() );
            itineraryItemDomain.setLodgeTcCantNight( entity.getLodgeTcCantNight() );
            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuide( entity.getLodgeCantBreakfastTcLocalGuide() );
            itineraryItemDomain.setLodgeCantLunchTcLocalGuide( entity.getLodgeCantLunchTcLocalGuide() );
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuide( entity.getLodgeCantDinnerTcLocalGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuide( entity.getLodgeCantBoxLunchTcLocalGuide() );
            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideDateTime( entity.getLodgeCantBreakfastTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgeCantLunchTcLocalGuideDateTime( entity.getLodgeCantLunchTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuideDateTime( entity.getLodgeCantDinnerTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideDateTime( entity.getLodgeCantBoxLunchTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideDate( entity.getLodgeCantBreakfastTcLocalGuideDate() );
            itineraryItemDomain.setLodgeCantLunchTcLocalGuideDate( entity.getLodgeCantLunchTcLocalGuideDate() );
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuideDate( entity.getLodgeCantDinnerTcLocalGuideDate() );
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideDate( entity.getLodgeCantBoxLunchTcLocalGuideDate() );
            if (entity.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null &&
                    entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setId(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setIndex(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setName(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setDescription(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setIdentify(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgeCantLunchTcLocalGuideItineraryDate() != null &&
                    entity.getLodgeCantLunchTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantLunchTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setId(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setIndex(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setName(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setDescription(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setIdentify(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantLunchTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgeCantDinnerTcLocalGuideItineraryDate() != null &&
                    entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantDinnerTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setId(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setIndex(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setName(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setDescription(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setIdentify(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantDinnerTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null &&
                    entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setId(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setIndex(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setName(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setDescription(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setIdentify(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideItineraryDate( null );
            }

            itineraryItemDomain.setLodgeCantSwbTcLocalGuide( entity.getLodgeCantSwbTcLocalGuide() );
            itineraryItemDomain.setLodgeCantTwinTcLocalGuide( entity.getLodgeCantTwinTcLocalGuide() );
            itineraryItemDomain.setLodgeTcLocalCantNight( entity.getLodgeTcLocalCantNight() );
            itineraryItemDomain.setLodgeCantBreakfastGuide( entity.getLodgeCantBreakfastGuide() );
            itineraryItemDomain.setLodgeCantLunchGuide( entity.getLodgeCantLunchGuide() );
            itineraryItemDomain.setLodgeCantDinnerGuide( entity.getLodgeCantDinnerGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchGuide( entity.getLodgeCantBoxLunchGuide() );
            itineraryItemDomain.setLodgeCantBreakfastGuideDateTime( entity.getLodgeCantBreakfastGuideDateTime() );
            itineraryItemDomain.setLodgeCantLunchGuideDateTime( entity.getLodgeCantLunchGuideDateTime() );
            itineraryItemDomain.setLodgeCantDinnerGuideDateTime( entity.getLodgeCantDinnerGuideDateTime() );
            itineraryItemDomain.setLodgeCantBoxLunchGuideDateTime( entity.getLodgeCantBoxLunchGuideDateTime() );
            itineraryItemDomain.setLodgeCantBreakfastGuideDate( entity.getLodgeCantBreakfastGuideDate() );
            itineraryItemDomain.setLodgeCantLunchGuideDate( entity.getLodgeCantLunchGuideDate() );
            itineraryItemDomain.setLodgeCantDinnerGuideDate( entity.getLodgeCantDinnerGuideDate() );
            itineraryItemDomain.setLodgeCantBoxLunchGuideDate( entity.getLodgeCantBoxLunchGuideDate() );

            if (entity.getLodgeCantBreakfastGuideItineraryDate() != null &&
                    entity.getLodgeCantBreakfastGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setId(entity.getLodgeCantBreakfastGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setIndex(entity.getLodgeCantBreakfastGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setItineraryDate(entity.getLodgeCantBreakfastGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setName(entity.getLodgeCantBreakfastGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setDescription(entity.getLodgeCantBreakfastGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setIdentify(entity.getLodgeCantBreakfastGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setItemItinerary(entity.getLodgeCantBreakfastGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBreakfastGuideItineraryDate( null );
            }
            if (entity.getLodgeCantLunchGuideItineraryDate() != null &&
                    entity.getLodgeCantLunchGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantLunchGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantLunchGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setId(entity.getLodgeCantLunchGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setIndex(entity.getLodgeCantLunchGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setItineraryDate(entity.getLodgeCantLunchGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setName(entity.getLodgeCantLunchGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setDescription(entity.getLodgeCantLunchGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setIdentify(entity.getLodgeCantLunchGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setItemItinerary(entity.getLodgeCantLunchGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantLunchGuideItineraryDate( null );
            }
            if (entity.getLodgeCantDinnerGuideItineraryDate() != null &&
                    entity.getLodgeCantDinnerGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantDinnerGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantDinnerGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setId(entity.getLodgeCantDinnerGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setIndex(entity.getLodgeCantDinnerGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setItineraryDate(entity.getLodgeCantDinnerGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setName(entity.getLodgeCantDinnerGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setDescription(entity.getLodgeCantDinnerGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setIdentify(entity.getLodgeCantDinnerGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setItemItinerary(entity.getLodgeCantDinnerGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantDinnerGuideItineraryDate( null );
            }
            if (entity.getLodgeCantBoxLunchGuideItineraryDate() != null &&
                    entity.getLodgeCantBoxLunchGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBoxLunchGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBoxLunchGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setId(entity.getLodgeCantBoxLunchGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setIndex(entity.getLodgeCantBoxLunchGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setItineraryDate(entity.getLodgeCantBoxLunchGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setName(entity.getLodgeCantBoxLunchGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setDescription(entity.getLodgeCantBoxLunchGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setIdentify(entity.getLodgeCantBoxLunchGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setItemItinerary(entity.getLodgeCantBoxLunchGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBoxLunchGuideItineraryDate( null );
            }
            itineraryItemDomain.setLodgeCantSwbGuide( entity.getLodgeCantSwbGuide() );
            itineraryItemDomain.setLodgeCantTwinGuide( entity.getLodgeCantTwinGuide() );
            itineraryItemDomain.setLodgeGuideCantNight( entity.getLodgeGuideCantNight() );
            itineraryItemDomain.setLodgePvt( entity.getLodgePvt() );
            itineraryItemDomain.setLodgeSic( entity.getLodgeSic() );

            itineraryItemDomain.setOperatorPvt( entity.getOperatorPvt() );
            itineraryItemDomain.setOperatorSic( entity.getOperatorSic() );

            itineraryItemDomain.setRestaurantCantBreakfast(entity.getRestaurantCantBreakfast());
            itineraryItemDomain.setRestaurantCantLunch(entity.getRestaurantCantLunch());
            itineraryItemDomain.setRestaurantCantDinner(entity.getRestaurantCantDinner());
            itineraryItemDomain.setRestaurantCantBoxLunch(entity.getRestaurantCantBoxLunch());

            itineraryItemDomain.setRestaurantCantBreakfastTcGuide(entity.getRestaurantCantBreakfastTcGuide());
            itineraryItemDomain.setRestaurantCantLunchTcGuide(entity.getRestaurantCantLunchTcGuide());
            itineraryItemDomain.setRestaurantCantDinnerTcGuide(entity.getRestaurantCantDinnerTcGuide());
            itineraryItemDomain.setRestaurantCantBoxLunchTcGuide(entity.getRestaurantCantBoxLunchTcGuide());

            itineraryItemDomain.setRestaurantCantBreakfastTcLocalGuide(entity.getRestaurantCantBreakfastTcLocalGuide());
            itineraryItemDomain.setRestaurantCantLunchTcLocalGuide(entity.getRestaurantCantLunchTcLocalGuide());
            itineraryItemDomain.setRestaurantCantDinnerTcLocalGuide(entity.getRestaurantCantDinnerTcLocalGuide());
            itineraryItemDomain.setRestaurantCantBoxLunchTcLocalGuide(entity.getRestaurantCantBoxLunchTcLocalGuide());

            itineraryItemDomain.setRestaurantCantBreakfastGuide(entity.getRestaurantCantBreakfastGuide());
            itineraryItemDomain.setRestaurantCantLunchGuide(entity.getRestaurantCantLunchGuide());
            itineraryItemDomain.setRestaurantCantDinnerGuide(entity.getRestaurantCantDinnerGuide());
            itineraryItemDomain.setRestaurantCantBoxLunchGuide(entity.getRestaurantCantBoxLunchGuide());

            itineraryItemDomain.setRestaurantCantBreakfastDrive(entity.getRestaurantCantBreakfastDrive());
            itineraryItemDomain.setRestaurantCantLunchDrive(entity.getRestaurantCantLunchDrive());
            itineraryItemDomain.setRestaurantCantDinnerDrive(entity.getRestaurantCantDinnerDrive());
            itineraryItemDomain.setRestaurantCantBoxLunchDrive(entity.getRestaurantCantBoxLunchDrive());

            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseAnnulledByRegisterGroupAndSupplier(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseExportItineraryByRegisterGroup(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            if (entity.getSupplier() != null){
                itineraryItemDomain.setSupplier( new SupplierDomain() );
                itineraryItemDomain.getSupplier().setId( entity.getSupplier().getId() );
                itineraryItemDomain.getSupplier().setName( entity.getSupplier().getName() );
                itineraryItemDomain.getSupplier().setIdentify( entity.getSupplier().getIdentify() );
                itineraryItemDomain.getSupplier().setDescription( entity.getSupplier().getDescription() );
                itineraryItemDomain.getSupplier().setTypeInheritance( entity.getSupplier().getTypeInheritance() );
                itineraryItemDomain.getSupplier().setSummary( entity.getSupplier().getSummary() );

                if (entity.getSupplier().getPartner() != null){
                    itineraryItemDomain.getSupplier().setPartner( new PartnerDomain());
                    itineraryItemDomain.getSupplier().getPartner().setId(entity.getSupplier().getPartner().getId());
                    if (entity.getSupplier().getPartner().getAddress() != null){
                        itineraryItemDomain.getSupplier().getPartner().setAddress(new AddressEmbeddedDomain());
                        if (entity.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                            itineraryItemDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                            itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
                            itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
                        }
                    }
                }
            }
            if (entity.getService() != null &&
                    entity.getService().getId() != null &&
                    !entity.getService().getId().isEmpty()) {
                itineraryItemDomain.setService( new ServiceDomain() );
                itineraryItemDomain.getService().setId( entity.getService().getId() );
                itineraryItemDomain.getService().setName( entity.getService().getName() );
                itineraryItemDomain.getService().setAbbrev( entity.getService().getAbbrev() );
            }
            itineraryItemDomain.setObservation( entity.getObservation() );
            itineraryItemDomain.setItineraryDateTime( entity.getItineraryDateTime() );
            if (entity.getItineraryDate() != null &&
                    entity.getItineraryDate().getId() != null &&
                    !entity.getItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getItineraryDate().setId( entity.getItineraryDate().getId() );
                itineraryItemDomain.getItineraryDate().setItineraryDate(entity.getItineraryDate().getItineraryDate());
                itineraryItemDomain.getItineraryDate().setName( entity.getItineraryDate().getName() );
            }
            if (entity.getOrderServiceItem() != null &&
                    entity.getOrderServiceItem().getId() != null &&
                    !entity.getOrderServiceItem().getId().isEmpty()) {
                itineraryItemDomain.setOrderServiceItem( new OrderServiceItemDomain() );
                itineraryItemDomain.getOrderServiceItem().setId( entity.getOrderServiceItem().getId() );
                itineraryItemDomain.getOrderServiceItem().setDateTimeService(entity.getOrderServiceItem().getDateTimeService());
            }

            itineraryItemDomain.setServiceGroup(entity.getServiceGroup());
            itineraryItemDomain.setPaxTotal(entity.getPaxTotal());
            itineraryItemDomain.setPaxAdultTotal(entity.getPaxAdultTotal());
            itineraryItemDomain.setPaxChildrenTotal( entity.getPaxChildrenTotal() );
            itineraryItemDomain.setPaxTcTotal( entity.getPaxTcTotal() );
            itineraryItemDomain.setPaxTcLocalTotal( entity.getPaxTcLocalTotal() );
            itineraryItemDomain.setPaxGuideTotal( entity.getPaxGuideTotal() );

            itineraryItemDomain.setLodgingCantSwb( entity.getLodgingCantSwb() );
            itineraryItemDomain.setLodgingCantMat( entity.getLodgingCantMat() );
            itineraryItemDomain.setLodgingCantTwin( entity.getLodgingCantTwin() );
            itineraryItemDomain.setLodgingCantTrp( entity.getLodgingCantTrp() );

            itineraryItemDomain.setLodgingCantBreakfast( entity.getLodgingCantBreakfast() );
            itineraryItemDomain.setLodgingCantLunch( entity.getLodgingCantLunch() );
            itineraryItemDomain.setLodgingCantDinner( entity.getLodgingCantDinner() );
            itineraryItemDomain.setLodgingCantBoxLunch( entity.getLodgingCantBoxLunch() );

            itineraryItemDomain.setLodgingCantNight( entity.getLodgingCantNight() );

            itineraryItemDomain.setLodgingCantBreakfastTcGuide( entity.getLodgingCantBreakfastTcGuide() );
            itineraryItemDomain.setLodgingCantLunchTcGuide( entity.getLodgingCantLunchTcGuide() );
            itineraryItemDomain.setLodgingCantDinnerTcGuide( entity.getLodgingCantDinnerTcGuide() );
            itineraryItemDomain.setLodgingCantBoxLunchTcGuide( entity.getLodgingCantBoxLunchTcGuide() );

            itineraryItemDomain.setLodgingCantSwbTcGuide( entity.getLodgingCantSwbTcGuide() );
            itineraryItemDomain.setLodgingCantTwinTcGuide( entity.getLodgingCantTwinTcGuide() );

            itineraryItemDomain.setLodgingCantBreakfastTcLocalGuide( entity.getLodgingCantBreakfastTcLocalGuide() );
            itineraryItemDomain.setLodgingCantLunchTcLocalGuide( entity.getLodgingCantLunchTcLocalGuide() );
            itineraryItemDomain.setLodgingCantDinnerTcLocalGuide( entity.getLodgingCantDinnerTcLocalGuide() );
            itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuide( entity.getLodgingCantBoxLunchTcLocalGuide() );

            itineraryItemDomain.setLodgingCantSwbTcLocalGuide( entity.getLodgingCantSwbTcLocalGuide() );
            itineraryItemDomain.setLodgingCantTwinTcLocalGuide( entity.getLodgingCantTwinTcLocalGuide() );

            itineraryItemDomain.setLodgingCantBreakfastGuide( entity.getLodgingCantBreakfastGuide() );
            itineraryItemDomain.setLodgingCantLunchGuide( entity.getLodgingCantLunchGuide() );
            itineraryItemDomain.setLodgingCantDinnerGuide( entity.getLodgingCantDinnerGuide() );
            itineraryItemDomain.setLodgingCantBoxLunchGuide( entity.getLodgingCantBoxLunchGuide() );

            itineraryItemDomain.setLodgingCantSwbGuide( entity.getLodgingCantSwbGuide() );
            itineraryItemDomain.setLodgingCantTwinGuide( entity.getLodgingCantTwinGuide() );

            itineraryItemDomain.setLodgeCantSwb( entity.getLodgeCantSwb() );
            itineraryItemDomain.setLodgeCantMat( entity.getLodgeCantMat() );
            itineraryItemDomain.setLodgeCantTwin( entity.getLodgeCantTwin() );
            itineraryItemDomain.setLodgeCantTrp( entity.getLodgeCantTrp() );

            itineraryItemDomain.setLodgeCantBreakfast( entity.getLodgeCantBreakfast() );
            itineraryItemDomain.setLodgeCantLunch( entity.getLodgeCantLunch() );
            itineraryItemDomain.setLodgeCantDinner( entity.getLodgeCantDinner() );
            itineraryItemDomain.setLodgeCantBoxLunch( entity.getLodgeCantBoxLunch() );

            itineraryItemDomain.setLodgeCantNight( entity.getLodgeCantNight() );

            itineraryItemDomain.setLodgeCantBreakfastTcGuide( entity.getLodgeCantBreakfastTcGuide() );
            itineraryItemDomain.setLodgeCantLunchTcGuide( entity.getLodgeCantLunchTcGuide() );
            itineraryItemDomain.setLodgeCantDinnerTcGuide( entity.getLodgeCantDinnerTcGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchTcGuide( entity.getLodgeCantBoxLunchTcGuide() );

            itineraryItemDomain.setLodgeCantSwbTcGuide( entity.getLodgeCantSwbTcGuide() );
            itineraryItemDomain.setLodgeCantTwinTcGuide( entity.getLodgeCantTwinTcGuide() );

            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuide( entity.getLodgeCantBreakfastTcLocalGuide() );
            itineraryItemDomain.setLodgeCantLunchTcLocalGuide( entity.getLodgeCantLunchTcLocalGuide() );
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuide( entity.getLodgeCantDinnerTcLocalGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuide( entity.getLodgeCantBoxLunchTcLocalGuide() );

            itineraryItemDomain.setLodgeCantSwbTcLocalGuide( entity.getLodgeCantSwbTcLocalGuide() );
            itineraryItemDomain.setLodgeCantTwinTcLocalGuide( entity.getLodgeCantTwinTcLocalGuide() );

            itineraryItemDomain.setLodgeCantBreakfastGuide( entity.getLodgeCantBreakfastGuide() );
            itineraryItemDomain.setLodgeCantLunchGuide( entity.getLodgeCantLunchGuide() );
            itineraryItemDomain.setLodgeCantDinnerGuide( entity.getLodgeCantDinnerGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchGuide( entity.getLodgeCantBoxLunchGuide() );

            itineraryItemDomain.setLodgeCantSwbGuide( entity.getLodgeCantSwbGuide() );
            itineraryItemDomain.setLodgeCantTwinGuide( entity.getLodgeCantTwinGuide() );

            itineraryItemDomain.setLodgePvt( entity.getLodgePvt() );
            itineraryItemDomain.setLodgeSic( entity.getLodgeSic() );

            itineraryItemDomain.setOperatorPvt( entity.getOperatorPvt() );
            itineraryItemDomain.setOperatorSic( entity.getOperatorSic() );

            itineraryItemDomain.setRestaurantCantBreakfast(entity.getRestaurantCantBreakfast());
            itineraryItemDomain.setRestaurantCantLunch(entity.getRestaurantCantLunch());
            itineraryItemDomain.setRestaurantCantDinner(entity.getRestaurantCantDinner());
            itineraryItemDomain.setRestaurantCantBoxLunch(entity.getRestaurantCantBoxLunch());

            itineraryItemDomain.setRestaurantCantBreakfastTcGuide(entity.getRestaurantCantBreakfastTcGuide());
            itineraryItemDomain.setRestaurantCantLunchTcGuide(entity.getRestaurantCantLunchTcGuide());
            itineraryItemDomain.setRestaurantCantDinnerTcGuide(entity.getRestaurantCantDinnerTcGuide());
            itineraryItemDomain.setRestaurantCantBoxLunchTcGuide(entity.getRestaurantCantBoxLunchTcGuide());

            itineraryItemDomain.setRestaurantCantBreakfastTcLocalGuide(entity.getRestaurantCantBreakfastTcLocalGuide());
            itineraryItemDomain.setRestaurantCantLunchTcLocalGuide(entity.getRestaurantCantLunchTcLocalGuide());
            itineraryItemDomain.setRestaurantCantDinnerTcLocalGuide(entity.getRestaurantCantDinnerTcLocalGuide());
            itineraryItemDomain.setRestaurantCantBoxLunchTcLocalGuide(entity.getRestaurantCantBoxLunchTcLocalGuide());

            itineraryItemDomain.setRestaurantCantBreakfastGuide(entity.getRestaurantCantBreakfastGuide());
            itineraryItemDomain.setRestaurantCantLunchGuide(entity.getRestaurantCantLunchGuide());
            itineraryItemDomain.setRestaurantCantDinnerGuide(entity.getRestaurantCantDinnerGuide());
            itineraryItemDomain.setRestaurantCantBoxLunchGuide(entity.getRestaurantCantBoxLunchGuide());

            itineraryItemDomain.setRestaurantCantBreakfastDrive(entity.getRestaurantCantBreakfastDrive());
            itineraryItemDomain.setRestaurantCantLunchDrive(entity.getRestaurantCantLunchDrive());
            itineraryItemDomain.setRestaurantCantDinnerDrive(entity.getRestaurantCantDinnerDrive());
            itineraryItemDomain.setRestaurantCantBoxLunchDrive(entity.getRestaurantCantBoxLunchDrive());

            if (entity.getGuideTransport() != null &&
                    entity.getGuideTransport().getId() != null &&
                    !entity.getGuideTransport().getId().isEmpty()){
                itineraryItemDomain.setGuideTransport(new GuideDomain());
                itineraryItemDomain.getGuideTransport().setId(entity.getGuideTransport().getId());
                if (entity.getGuideTransport().getPerson() != null &&
                        entity.getGuideTransport().getPerson().getId() != null &&
                        !entity.getGuideTransport().getPerson().getId().isEmpty()){
                    itineraryItemDomain.getGuideTransport().setPerson(new PersonDomain());
                    itineraryItemDomain.getGuideTransport().getPerson().setId(entity.getGuideTransport().getPerson().getId());
                    itineraryItemDomain.getGuideTransport().getPerson().setFirstName(entity.getGuideTransport().getPerson().getFirstName());
                    itineraryItemDomain.getGuideTransport().getPerson().setLastName(entity.getGuideTransport().getPerson().getLastName());
                }
            }

            if (entity.getGuideTransferDrive() != null &&
                    entity.getGuideTransferDrive().getId() != null &&
                    !entity.getGuideTransferDrive().getId().isEmpty()){
                itineraryItemDomain.setGuideTransferDrive(new GuideDomain());
                itineraryItemDomain.getGuideTransferDrive().setId(entity.getGuideTransferDrive().getId());
                if (entity.getGuideTransferDrive().getPerson() != null &&
                        entity.getGuideTransferDrive().getPerson().getId() != null &&
                        !entity.getGuideTransferDrive().getPerson().getId().isEmpty()){
                    itineraryItemDomain.getGuideTransferDrive().setPerson(new PersonDomain());
                    itineraryItemDomain.getGuideTransferDrive().getPerson().setId(entity.getGuideTransferDrive().getPerson().getId());
                    itineraryItemDomain.getGuideTransferDrive().getPerson().setFirstName(entity.getGuideTransferDrive().getPerson().getFirstName());
                    itineraryItemDomain.getGuideTransferDrive().getPerson().setLastName(entity.getGuideTransferDrive().getPerson().getLastName());
                }
            }

            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseProgramByRegisterGroup(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            if (entity.getSupplierType() != null &&
                    entity.getSupplierType().getId() != null &&
                    !entity.getSupplierType().getId().isEmpty()) {
                itineraryItemDomain.setSupplierType( new SupplierTypeDomain() );
                itineraryItemDomain.getSupplierType().setId( entity.getSupplierType().getId() );
            }
            if (entity.getItineraryDate() != null &&
                    entity.getItineraryDate().getId() != null &&
                    !entity.getItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getItineraryDate().setId( entity.getItineraryDate().getId() );
            }
            itineraryItemDomain.setPaxTotal(entity.getPaxTotal());
            itineraryItemDomain.setPaxAdultTotal(entity.getPaxAdultTotal());
            itineraryItemDomain.setPaxChildrenTotal(entity.getPaxChildrenTotal());
            itineraryItemDomain.setPaxTcTotal(entity.getPaxTcTotal());
            itineraryItemDomain.setPaxTcLocalTotal(entity.getPaxTcLocalTotal());
            itineraryItemDomain.setPaxGuideTotal(entity.getPaxGuideTotal());
            itineraryItemDomain.setServiceGroup(entity.getServiceGroup());
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseTransferControlBible(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            itineraryItemDomain.setOperatorPvt( entity.getOperatorPvt() );
            itineraryItemDomain.setOperatorSic( entity.getOperatorSic() );
            itineraryItemDomain.setLodgePvt( entity.getLodgePvt() );
            itineraryItemDomain.setLodgeSic( entity.getLodgeSic() );
            if (entity.getSupplierType() != null){
                itineraryItemDomain.setSupplierType( new SupplierTypeDomain() );
                itineraryItemDomain.getSupplierType().setId( entity.getSupplierType().getId() );
            }
            if (entity.getSupplier() != null){
                itineraryItemDomain.setSupplier( new SupplierDomain() );
                itineraryItemDomain.getSupplier().setId( entity.getSupplier().getId() );
                itineraryItemDomain.getSupplier().setName( entity.getSupplier().getName() );
                itineraryItemDomain.getSupplier().setSummary( entity.getSupplier().getSummary() );
                if (entity.getSupplier().getPartner() != null){
                    itineraryItemDomain.getSupplier().setPartner( new PartnerDomain());
                    itineraryItemDomain.getSupplier().getPartner().setId(entity.getSupplier().getPartner().getId());
                    if (entity.getSupplier().getPartner().getAddress() != null){
                        itineraryItemDomain.getSupplier().getPartner().setAddress(new AddressEmbeddedDomain());
                        if (entity.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                            itineraryItemDomain.getSupplier().getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                            itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setId(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getId());
                            itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().setName(entity.getSupplier().getPartner().getAddress().getDepartmentRegion().getName());
                        }
                    }
                }
            }
            if (entity.getService() != null){
                itineraryItemDomain.setService( new ServiceDomain() );
                itineraryItemDomain.getService().setId( entity.getService().getId() );
                itineraryItemDomain.getService().setName( entity.getService().getName() );
                itineraryItemDomain.getService().setAbbrev( entity.getService().getAbbrev() );
            }
            if (entity.getRegisterGroup() != null){
                itineraryItemDomain.setRegisterGroup( new RegisterGroupDomain() );
                itineraryItemDomain.getRegisterGroup().setId( entity.getRegisterGroup().getId() );
                itineraryItemDomain.getRegisterGroup().setName( entity.getRegisterGroup().getName() );
                if (entity.getRegisterGroup().getCustomer() != null){
                    itineraryItemDomain.getRegisterGroup().setCustomer( new CustomerDomain() );
                    itineraryItemDomain.getRegisterGroup().getCustomer().setId( entity.getRegisterGroup().getCustomer().getId() );
                    itineraryItemDomain.getRegisterGroup().getCustomer().setName( entity.getRegisterGroup().getCustomer().getName() );
                }
            }
            if (entity.getTour() != null &&
                    entity.getTour().getId() != null &&
                    !entity.getTour().getId().isEmpty()) {
                itineraryItemDomain.setTour( new TourDomain() );
                itineraryItemDomain.getTour().setId( entity.getTour().getId() );
                itineraryItemDomain.getTour().setName( entity.getTour().getName() );
            }
            itineraryItemDomain.setObservation( entity.getObservation() );
            itineraryItemDomain.setItineraryDateTime( entity.getItineraryDateTime() );
            if (entity.getItineraryDate() != null){
                itineraryItemDomain.setItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getItineraryDate().setId( entity.getItineraryDate().getId() );
                itineraryItemDomain.getItineraryDate().setItineraryDate(entity.getItineraryDate().getItineraryDate());
                itineraryItemDomain.getItineraryDate().setItemItinerary(entity.getItineraryDate().getItemItinerary());
            }
            if (entity.getOrderServiceItem() != null){
                itineraryItemDomain.setOrderServiceItem( new OrderServiceItemDomain() );
                itineraryItemDomain.getOrderServiceItem().setId(entity.getOrderServiceItem().getId());
                itineraryItemDomain.getOrderServiceItem().setDateTimePax(entity.getOrderServiceItem().getDateTimePax());
                itineraryItemDomain.getOrderServiceItem().setDateTimeRecj(entity.getOrderServiceItem().getDateTimeRecj());
                if (entity.getOrderServiceItem().getOrderService() != null){
                    itineraryItemDomain.getOrderServiceItem().setOrderService( new OrderServiceDomain() );
                    itineraryItemDomain.getOrderServiceItem().getOrderService().setId(entity.getOrderServiceItem().getOrderService().getId());
                    if (entity.getOrderServiceItem().getOrderService().getSupplierLodging() != null){
                        itineraryItemDomain.getOrderServiceItem().getOrderService().setSupplierLodging( new SupplierLodgingDomain() );
                        itineraryItemDomain.getOrderServiceItem().getOrderService().getSupplierLodging().setId(entity.getOrderServiceItem().getOrderService().getSupplierLodging().getId());
                        itineraryItemDomain.getOrderServiceItem().getOrderService().getSupplierLodging().setName(entity.getOrderServiceItem().getOrderService().getSupplierLodging().getName());
                        itineraryItemDomain.getOrderServiceItem().getOrderService().getSupplierLodging().setSummary(entity.getOrderServiceItem().getOrderService().getSupplierLodging().getSummary());
                    }
                }
                if (entity.getOrderServiceItem().getDrive() != null){
                    itineraryItemDomain.getOrderServiceItem().setDrive( new GuideDomain() );
                    itineraryItemDomain.getOrderServiceItem().getDrive().setId(entity.getOrderServiceItem().getDrive().getId());
                    if (entity.getOrderServiceItem().getDrive().getPerson() != null){
                        itineraryItemDomain.getOrderServiceItem().getDrive().setPerson( new PersonDomain() );
                        itineraryItemDomain.getOrderServiceItem().getDrive().getPerson().setId(entity.getOrderServiceItem().getDrive().getPerson().getId());
                        itineraryItemDomain.getOrderServiceItem().getDrive().getPerson().setLastName(entity.getOrderServiceItem().getDrive().getPerson().getLastName());
                        itineraryItemDomain.getOrderServiceItem().getDrive().getPerson().setFirstName(entity.getOrderServiceItem().getDrive().getPerson().getFirstName());
                    }
                }
                if (entity.getOrderServiceItem().getGuideTransfer() != null){
                    itineraryItemDomain.getOrderServiceItem().setGuideTransfer( new GuideDomain() );
                    itineraryItemDomain.getOrderServiceItem().getGuideTransfer().setId(entity.getOrderServiceItem().getGuideTransfer().getId());
                    if (entity.getOrderServiceItem().getGuideTransfer().getPerson() != null){
                        itineraryItemDomain.getOrderServiceItem().getGuideTransfer().setPerson( new PersonDomain() );
                        itineraryItemDomain.getOrderServiceItem().getGuideTransfer().getPerson().setId(entity.getOrderServiceItem().getGuideTransfer().getPerson().getId());
                        itineraryItemDomain.getOrderServiceItem().getGuideTransfer().getPerson().setLastName(entity.getOrderServiceItem().getGuideTransfer().getPerson().getLastName());
                        itineraryItemDomain.getOrderServiceItem().getGuideTransfer().getPerson().setFirstName(entity.getOrderServiceItem().getGuideTransfer().getPerson().getFirstName());
                    }
                }
            }
            itineraryItemDomain.setPaxTotal(entity.getPaxTotal());
            itineraryItemDomain.setPaxAdultTotal(entity.getPaxAdultTotal());
            itineraryItemDomain.setPaxChildrenTotal( entity.getPaxChildrenTotal() );
            itineraryItemDomain.setPaxTcTotal( entity.getPaxTcTotal() );
            itineraryItemDomain.setPaxTcLocalTotal( entity.getPaxTcLocalTotal() );
            itineraryItemDomain.setPaxGuideTotal( entity.getPaxGuideTotal() );
            return itineraryItemDomain;
        }).toList();
    }

    public PageDTO<ItineraryItemDomain> toDomainPageCaseTransferControlBible(Page<ItineraryItemEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ItineraryItemDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseTransferControlBible(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public List<ItineraryItemDomain> toDomainPageCaseTransferControlBibleExport(List<ItineraryItemEntity> list) throws RuntimeException {
        return toDomainAllSimpleToDomainCaseTransferControlBible(list);
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseGenerateAllBookingByRegisterGroupAndSupplier(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseVoucherEgress(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setPaxTotal( entity.getPaxTotal() );
            return itineraryItemDomain;
        }).toList();
    }

    public ItineraryItemDomain toDomainIdSimpleDomainCaseUpdateOrderItineraryVoucherEgress(ItineraryItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();

        itineraryItemDomain.setId( source.getId() );
        itineraryItemDomain.setIndex( source.getIndex() );
        itineraryItemDomain.setItineraryDate( new ItineraryDateDomain() );
        itineraryItemDomain.getItineraryDate().setId( source.getItineraryDate().getId() );
        itineraryItemDomain.getItineraryDate().setItineraryDate( source.getItineraryDate().getItineraryDate() );
        itineraryItemDomain.setService( new ServiceDomain() );
        itineraryItemDomain.getService().setId( source.getService().getId() );
        itineraryItemDomain.getService().setName( source.getService().getName() );
        itineraryItemDomain.getService().setCost( source.getService().getCost() );
        itineraryItemDomain.setTour( new TourDomain() );
        itineraryItemDomain.getTour().setId( source.getTour().getId() );
        itineraryItemDomain.getTour().setName( source.getTour().getName() );
        return itineraryItemDomain;
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseRsendFileToSaleByRegisterGroup(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            if (entity.getSupplierType() != null &&
                    entity.getSupplierType().getId() != null &&
                    !entity.getSupplierType().getId().isEmpty()) {
                itineraryItemDomain.setSupplierType( new SupplierTypeDomain() );
                itineraryItemDomain.getSupplierType().setId( entity.getSupplierType().getId() );
                itineraryItemDomain.getSupplierType().setName( entity.getSupplierType().getName() );
                itineraryItemDomain.getSupplierType().setDescription( entity.getSupplierType().getDescription() );
                itineraryItemDomain.getSupplierType().setIdentify( entity.getSupplierType().getIdentify() );
            }
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()) {
                itineraryItemDomain.setSupplier( new SupplierDomain() );
                itineraryItemDomain.getSupplier().setId( entity.getSupplier().getId() );
                itineraryItemDomain.getSupplier().setName( entity.getSupplier().getName() );
                itineraryItemDomain.getSupplier().setIdentify( entity.getSupplier().getIdentify() );
                itineraryItemDomain.getSupplier().setDescription( entity.getSupplier().getDescription() );
            }
            if (entity.getService() != null &&
                    entity.getService().getId() != null &&
                    !entity.getService().getId().isEmpty()) {
                itineraryItemDomain.setService( new ServiceDomain() );
                itineraryItemDomain.getService().setId( entity.getService().getId() );
                itineraryItemDomain.getService().setName( entity.getService().getName() );
                itineraryItemDomain.getService().setIdentify( entity.getService().getIdentify() );
                itineraryItemDomain.getService().setDescription( entity.getService().getName() );
            }
            itineraryItemDomain.setServiceGroup( entity.getServiceGroup() );
            itineraryItemDomain.setObservation( entity.getObservation() );
            itineraryItemDomain.setItineraryDateTime( entity.getItineraryDateTime() );
            if (entity.getItineraryDate() != null &&
                    entity.getItineraryDate().getId() != null &&
                    !entity.getItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getItineraryDate().setId( entity.getItineraryDate().getId() );
                itineraryItemDomain.getItineraryDate().setName( entity.getItineraryDate().getName() );
                itineraryItemDomain.getItineraryDate().setIdentify( entity.getItineraryDate().getIdentify() );
                itineraryItemDomain.getItineraryDate().setDescription( entity.getItineraryDate().getDescription() );
                itineraryItemDomain.getItineraryDate().setItineraryDate(entity.getItineraryDate().getItineraryDate());
                itineraryItemDomain.getItineraryDate().setItemItinerary(entity.getItineraryDate().getItemItinerary());
            }
            itineraryItemDomain.setPaxTotal(entity.getPaxTotal());
            itineraryItemDomain.setPaxAdultTotal(entity.getPaxAdultTotal());
            itineraryItemDomain.setPaxChildrenTotal( entity.getPaxChildrenTotal() );
            itineraryItemDomain.setPaxTcTotal( entity.getPaxTcTotal() );
            itineraryItemDomain.setPaxTcLocalTotal( entity.getPaxTcLocalTotal() );
            itineraryItemDomain.setPaxGuideTotal( entity.getPaxGuideTotal() );

            if (entity.getTour() != null &&
                    entity.getTour().getId() != null &&
                    !entity.getTour().getId().isEmpty()) {
                itineraryItemDomain.setTour( new TourDomain() );
                itineraryItemDomain.getTour().setId( entity.getTour().getId() );
                itineraryItemDomain.getTour().setName( entity.getTour().getName() );
                itineraryItemDomain.getTour().setIdentify( entity.getTour().getIdentify() );
                itineraryItemDomain.getTour().setDescription( entity.getTour().getName() );
            }
            if (entity.getDepartmentRegion() != null &&
                    entity.getDepartmentRegion().getId() != null &&
                    !entity.getDepartmentRegion().getId().isEmpty()) {
                itineraryItemDomain.setDepartmentRegion( new DepartmentRegionDomain() );
                itineraryItemDomain.getDepartmentRegion().setId( entity.getDepartmentRegion().getId() );
                itineraryItemDomain.getDepartmentRegion().setName( entity.getDepartmentRegion().getName() );
                itineraryItemDomain.getDepartmentRegion().setIdentify( entity.getDepartmentRegion().getIdentify() );
                itineraryItemDomain.getDepartmentRegion().setDescription( entity.getDepartmentRegion().getName() );
            }

            itineraryItemDomain.setLodgingCantSwb( entity.getLodgingCantSwb() );
            itineraryItemDomain.setLodgingCantMat( entity.getLodgingCantMat() );
            itineraryItemDomain.setLodgingCantTwin( entity.getLodgingCantTwin() );
            itineraryItemDomain.setLodgingCantTrp( entity.getLodgingCantTrp() );

            itineraryItemDomain.setLodgingCategory( new LodgingCategoryDomain() );
            itineraryItemDomain.getLodgingCategory().setId( entity.getLodgingCategory().getId() );

            itineraryItemDomain.setLodgingCategoryStr( entity.getLodgingCategoryStr() );
            itineraryItemDomain.setLodgingCantBreakfast( entity.getLodgingCantBreakfast() );
            itineraryItemDomain.setLodgingCantLunch( entity.getLodgingCantLunch() );
            itineraryItemDomain.setLodgingCantDinner( entity.getLodgingCantDinner() );
            itineraryItemDomain.setLodgingCantBoxLunch( entity.getLodgingCantBoxLunch() );
            itineraryItemDomain.setLodgingCantBreakfastDateTime( entity.getLodgingCantBreakfastDateTime() );
            itineraryItemDomain.setLodgingCantLunchDateTime( entity.getLodgingCantLunchDateTime() );
            itineraryItemDomain.setLodgingCantDinnerDateTime( entity.getLodgingCantDinnerDateTime() );
            itineraryItemDomain.setLodgingCantBoxLunchDateTime( entity.getLodgingCantBoxLunchDateTime() );
            itineraryItemDomain.setLodgingCantBreakfastDate( entity.getLodgingCantBreakfastDate() );
            itineraryItemDomain.setLodgingCantLunchDate( entity.getLodgingCantLunchDate() );
            itineraryItemDomain.setLodgingCantDinnerDate( entity.getLodgingCantDinnerDate() );
            itineraryItemDomain.setLodgingCantBoxLunchDate( entity.getLodgingCantBoxLunchDate() );
            if (entity.getLodgingCantBreakfastItineraryDate() != null &&
                    entity.getLodgingCantBreakfastItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setId(entity.getLodgingCantBreakfastItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setIndex(entity.getLodgingCantBreakfastItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setItineraryDate(entity.getLodgingCantBreakfastItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setName(entity.getLodgingCantBreakfastItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setDescription(entity.getLodgingCantBreakfastItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setIdentify(entity.getLodgingCantBreakfastItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBreakfastItineraryDate().setItemItinerary(entity.getLodgingCantBreakfastItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBreakfastItineraryDate( null );
            }
            if (entity.getLodgingCantLunchItineraryDate() != null &&
                    entity.getLodgingCantLunchItineraryDate().getId() != null &&
                    !entity.getLodgingCantLunchItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantLunchItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setId(entity.getLodgingCantLunchItineraryDate().getId());
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setIndex(entity.getLodgingCantLunchItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setItineraryDate(entity.getLodgingCantLunchItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setName(entity.getLodgingCantLunchItineraryDate().getName());
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setDescription(entity.getLodgingCantLunchItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setIdentify(entity.getLodgingCantLunchItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantLunchItineraryDate().setItemItinerary(entity.getLodgingCantBreakfastItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantLunchItineraryDate( null );
            }
            if (entity.getLodgingCantDinnerItineraryDate() != null &&
                    entity.getLodgingCantDinnerItineraryDate().getId() != null &&
                    !entity.getLodgingCantDinnerItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantDinnerItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setId(entity.getLodgingCantDinnerItineraryDate().getId());
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setIndex(entity.getLodgingCantDinnerItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setItineraryDate(entity.getLodgingCantDinnerItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setName(entity.getLodgingCantDinnerItineraryDate().getName());
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setDescription(entity.getLodgingCantDinnerItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setIdentify(entity.getLodgingCantDinnerItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantDinnerItineraryDate().setItemItinerary(entity.getLodgingCantDinnerItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantDinnerItineraryDate( null );
            }
            if (entity.getLodgingCantBoxLunchItineraryDate() != null &&
                    entity.getLodgingCantBoxLunchItineraryDate().getId() != null &&
                    !entity.getLodgingCantBoxLunchItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBoxLunchItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setId(entity.getLodgingCantBoxLunchItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setIndex(entity.getLodgingCantBoxLunchItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setItineraryDate(entity.getLodgingCantBoxLunchItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setName(entity.getLodgingCantBoxLunchItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setDescription(entity.getLodgingCantBoxLunchItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setIdentify(entity.getLodgingCantBoxLunchItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().setItemItinerary(entity.getLodgingCantBoxLunchItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBoxLunchItineraryDate( null );
            }
            itineraryItemDomain.setLodgingCantNight( entity.getLodgingCantNight() );
            itineraryItemDomain.setLodgingCantBreakfastTcGuide( entity.getLodgingCantBreakfastTcGuide() );
            itineraryItemDomain.setLodgingCantLunchTcGuide( entity.getLodgingCantLunchTcGuide() );
            itineraryItemDomain.setLodgingCantDinnerTcGuide( entity.getLodgingCantDinnerTcGuide() );
            itineraryItemDomain.setLodgingCantBoxLunchTcGuide( entity.getLodgingCantBoxLunchTcGuide() );
            itineraryItemDomain.setLodgingCantBreakfastTcGuideDateTime( entity.getLodgingCantBreakfastTcGuideDateTime() );
            itineraryItemDomain.setLodgingCantLunchTcGuideDateTime( entity.getLodgingCantLunchTcGuideDateTime() );
            itineraryItemDomain.setLodgingCantDinnerTcGuideDateTime( entity.getLodgingCantDinnerTcGuideDateTime() );
            itineraryItemDomain.setLodgingCantBoxLunchTcGuideDateTime( entity.getLodgingCantBoxLunchTcGuideDateTime() );
            itineraryItemDomain.setLodgingCantBreakfastTcGuideDate( entity.getLodgingCantBreakfastTcGuideDate() );
            itineraryItemDomain.setLodgingCantLunchTcGuideDate( entity.getLodgingCantLunchTcGuideDate() );
            itineraryItemDomain.setLodgingCantDinnerTcGuideDate( entity.getLodgingCantDinnerTcGuideDate() );
            itineraryItemDomain.setLodgingCantBoxLunchTcGuideDate( entity.getLodgingCantBoxLunchTcGuideDate() );
            if (entity.getLodgingCantBreakfastTcGuideItineraryDate() != null &&
                    entity.getLodgingCantBreakfastTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setId(entity.getLodgingCantBreakfastTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setIndex(entity.getLodgingCantBreakfastTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setItineraryDate(entity.getLodgingCantBreakfastTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setName(entity.getLodgingCantBreakfastTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setDescription(entity.getLodgingCantBreakfastTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setIdentify(entity.getLodgingCantBreakfastTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().setItemItinerary(entity.getLodgingCantBreakfastTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBreakfastTcGuideItineraryDate( null );
            }
            if (entity.getLodgingCantLunchTcGuideItineraryDate() != null &&
                    entity.getLodgingCantLunchTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantLunchTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantLunchTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setId(entity.getLodgingCantLunchTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setIndex(entity.getLodgingCantLunchTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setItineraryDate(entity.getLodgingCantLunchTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setName(entity.getLodgingCantLunchTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setDescription(entity.getLodgingCantLunchTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setIdentify(entity.getLodgingCantLunchTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().setItemItinerary(entity.getLodgingCantLunchTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantLunchTcGuideItineraryDate( null );
            }
            if (entity.getLodgingCantDinnerTcGuideItineraryDate() != null &&
                    entity.getLodgingCantDinnerTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantDinnerTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantDinnerTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setId(entity.getLodgingCantDinnerTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setIndex(entity.getLodgingCantDinnerTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setItineraryDate(entity.getLodgingCantDinnerTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setName(entity.getLodgingCantDinnerTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setDescription(entity.getLodgingCantDinnerTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setIdentify(entity.getLodgingCantDinnerTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().setItemItinerary(entity.getLodgingCantDinnerTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantDinnerTcGuideItineraryDate( null );
            }
            if (entity.getLodgingCantBoxLunchTcGuideItineraryDate() != null &&
                    entity.getLodgingCantBoxLunchTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBoxLunchTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBoxLunchTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setId(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setIndex(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setItineraryDate(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setName(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setDescription(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setIdentify(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().setItemItinerary(entity.getLodgingCantBoxLunchTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBoxLunchTcGuideItineraryDate( null );
            }
            itineraryItemDomain.setLodgingCantSwbTcGuide( entity.getLodgingCantSwbTcGuide() );
            itineraryItemDomain.setLodgingCantTwinTcGuide( entity.getLodgingCantTwinTcGuide() );
            itineraryItemDomain.setLodgingCantBreakfastTcLocalGuide( entity.getLodgingCantBreakfastTcLocalGuide() );
            itineraryItemDomain.setLodgingCantLunchTcLocalGuide( entity.getLodgingCantLunchTcLocalGuide() );
            itineraryItemDomain.setLodgingCantDinnerTcLocalGuide( entity.getLodgingCantDinnerTcLocalGuide() );
            itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuide( entity.getLodgingCantBoxLunchTcLocalGuide() );
            itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideDateTime( entity.getLodgingCantBreakfastTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgingCantLunchTcLocalGuideDateTime( entity.getLodgingCantLunchTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgingCantDinnerTcLocalGuideDateTime( entity.getLodgingCantDinnerTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideDateTime( entity.getLodgingCantBoxLunchTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideDate( entity.getLodgingCantBreakfastTcLocalGuideDate() );
            itineraryItemDomain.setLodgingCantLunchTcLocalGuideDate( entity.getLodgingCantLunchTcLocalGuideDate() );
            itineraryItemDomain.setLodgingCantDinnerTcLocalGuideDate( entity.getLodgingCantDinnerTcLocalGuideDate() );
            itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideDate( entity.getLodgingCantBoxLunchTcLocalGuideDate() );
            if (entity.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null &&
                    entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setId(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setIndex(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setName(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setDescription(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setIdentify(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgingCantBreakfastTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgingCantLunchTcLocalGuideItineraryDate() != null &&
                    entity.getLodgingCantLunchTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantLunchTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setId(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setIndex(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setName(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setDescription(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setIdentify(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgingCantLunchTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantLunchTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgingCantDinnerTcLocalGuideItineraryDate() != null &&
                    entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantDinnerTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setId(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setIndex(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setName(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setDescription(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setIdentify(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgingCantDinnerTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantDinnerTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null &&
                    entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setId(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setIndex(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setName(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setDescription(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setIdentify(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideItineraryDate( null );
            }
            itineraryItemDomain.setLodgingCantSwbTcLocalGuide( entity.getLodgingCantSwbTcLocalGuide() );
            itineraryItemDomain.setLodgingCantTwinTcLocalGuide( entity.getLodgingCantTwinTcLocalGuide() );
            itineraryItemDomain.setLodgingCantBreakfastGuide( entity.getLodgingCantBreakfastGuide() );
            itineraryItemDomain.setLodgingCantLunchGuide( entity.getLodgingCantLunchGuide() );
            itineraryItemDomain.setLodgingCantDinnerGuide( entity.getLodgingCantDinnerGuide() );
            itineraryItemDomain.setLodgingCantBoxLunchGuide( entity.getLodgingCantBoxLunchGuide() );
            itineraryItemDomain.setLodgingCantBreakfastGuideDateTime( entity.getLodgingCantBreakfastGuideDateTime() );
            itineraryItemDomain.setLodgingCantLunchGuideDateTime( entity.getLodgingCantLunchGuideDateTime() );
            itineraryItemDomain.setLodgingCantDinnerGuideDateTime( entity.getLodgingCantDinnerGuideDateTime() );
            itineraryItemDomain.setLodgingCantBoxLunchGuideDateTime( entity.getLodgingCantBoxLunchGuideDateTime() );
            itineraryItemDomain.setLodgingCantBreakfastGuideDate( entity.getLodgingCantBreakfastGuideDate() );
            itineraryItemDomain.setLodgingCantLunchGuideDate( entity.getLodgingCantLunchGuideDate() );
            itineraryItemDomain.setLodgingCantDinnerGuideDate( entity.getLodgingCantDinnerGuideDate() );
            itineraryItemDomain.setLodgingCantBoxLunchGuideDate( entity.getLodgingCantBoxLunchGuideDate() );
            if (entity.getLodgingCantBreakfastGuideItineraryDate() != null &&
                    entity.getLodgingCantBreakfastGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBreakfastGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBreakfastGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setId(entity.getLodgingCantBreakfastGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setIndex(entity.getLodgingCantBreakfastGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setItineraryDate(entity.getLodgingCantBreakfastGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setName(entity.getLodgingCantBreakfastGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setDescription(entity.getLodgingCantBreakfastGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setIdentify(entity.getLodgingCantBreakfastGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().setItemItinerary(entity.getLodgingCantBreakfastGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBreakfastGuideItineraryDate( null );
            }
            if (entity.getLodgingCantLunchGuideItineraryDate() != null &&
                    entity.getLodgingCantLunchGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantLunchGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantLunchGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setId(entity.getLodgingCantLunchGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setIndex(entity.getLodgingCantLunchGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setItineraryDate(entity.getLodgingCantLunchGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setName(entity.getLodgingCantLunchGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setDescription(entity.getLodgingCantLunchGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setIdentify(entity.getLodgingCantLunchGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().setItemItinerary(entity.getLodgingCantLunchGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantLunchGuideItineraryDate( null );
            }
            if (entity.getLodgingCantDinnerGuideItineraryDate() != null &&
                    entity.getLodgingCantDinnerGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantDinnerGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantDinnerGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setId(entity.getLodgingCantDinnerGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setIndex(entity.getLodgingCantDinnerGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setItineraryDate(entity.getLodgingCantDinnerGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setName(entity.getLodgingCantDinnerGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setDescription(entity.getLodgingCantDinnerGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setIdentify(entity.getLodgingCantDinnerGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().setItemItinerary(entity.getLodgingCantDinnerGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantDinnerGuideItineraryDate( null );
            }
            if (entity.getLodgingCantBoxLunchGuideItineraryDate() != null &&
                    entity.getLodgingCantBoxLunchGuideItineraryDate().getId() != null &&
                    !entity.getLodgingCantBoxLunchGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCantBoxLunchGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setId(entity.getLodgingCantBoxLunchGuideItineraryDate().getId());
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setIndex(entity.getLodgingCantBoxLunchGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setItineraryDate(entity.getLodgingCantBoxLunchGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setName(entity.getLodgingCantBoxLunchGuideItineraryDate().getName());
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setDescription(entity.getLodgingCantBoxLunchGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setIdentify(entity.getLodgingCantBoxLunchGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().setItemItinerary(entity.getLodgingCantBoxLunchGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgingCantBoxLunchGuideItineraryDate( null );
            }

            itineraryItemDomain.setLodgingCantSwbGuide( entity.getLodgingCantSwbGuide() );
            itineraryItemDomain.setLodgingCantTwinGuide( entity.getLodgingCantTwinGuide() );
            itineraryItemDomain.setLodgingGuideCantNight( entity.getLodgingGuideCantNight() );
            itineraryItemDomain.setLodgeCantSwb( entity.getLodgeCantSwb() );
            itineraryItemDomain.setLodgeCantMat( entity.getLodgeCantMat() );
            itineraryItemDomain.setLodgeCantTwin( entity.getLodgeCantTwin() );
            itineraryItemDomain.setLodgeCantTrp( entity.getLodgeCantTrp() );

            itineraryItemDomain.setLodgeCategory( new LodgingCategoryDomain() );
            itineraryItemDomain.getLodgeCategory().setId( entity.getLodgeCategory().getId() );

            itineraryItemDomain.setLodgeCategoryStr( entity.getLodgeCategoryStr() );
            itineraryItemDomain.setLodgeCantBreakfast( entity.getLodgeCantBreakfast() );
            itineraryItemDomain.setLodgeCantLunch( entity.getLodgeCantLunch() );
            itineraryItemDomain.setLodgeCantDinner( entity.getLodgeCantDinner() );
            itineraryItemDomain.setLodgeCantBoxLunch( entity.getLodgeCantBoxLunch() );
            itineraryItemDomain.setLodgeCantBreakfastDateTime( entity.getLodgeCantBreakfastDateTime() );
            itineraryItemDomain.setLodgeCantLunchDateTime( entity.getLodgeCantLunchDateTime() );
            itineraryItemDomain.setLodgeCantDinnerDateTime( entity.getLodgeCantDinnerDateTime() );
            itineraryItemDomain.setLodgeCantBoxLunchDateTime( entity.getLodgeCantBoxLunchDateTime() );
            itineraryItemDomain.setLodgeCantBreakfastDate( entity.getLodgeCantBreakfastDate() );
            itineraryItemDomain.setLodgeCantLunchDate( entity.getLodgeCantLunchDate() );
            itineraryItemDomain.setLodgeCantDinnerDate( entity.getLodgeCantDinnerDate() );
            itineraryItemDomain.setLodgeCantBoxLunchDate( entity.getLodgeCantBoxLunchDate() );
            if (entity.getLodgeCantBreakfastItineraryDate() != null &&
                    entity.getLodgeCantBreakfastItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setId(entity.getLodgeCantBreakfastItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setIndex(entity.getLodgeCantBreakfastItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setItineraryDate(entity.getLodgeCantBreakfastItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setName(entity.getLodgeCantBreakfastItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setDescription(entity.getLodgeCantBreakfastItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setIdentify(entity.getLodgeCantBreakfastItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBreakfastItineraryDate().setItemItinerary(entity.getLodgeCantBreakfastItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBreakfastItineraryDate( null );
            }
            if (entity.getLodgeCantLunchItineraryDate() != null &&
                    entity.getLodgeCantLunchItineraryDate().getId() != null &&
                    !entity.getLodgeCantLunchItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantLunchItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setId(entity.getLodgeCantLunchItineraryDate().getId());
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setIndex(entity.getLodgeCantLunchItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setItineraryDate(entity.getLodgeCantLunchItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setName(entity.getLodgeCantLunchItineraryDate().getName());
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setDescription(entity.getLodgeCantLunchItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setIdentify(entity.getLodgeCantLunchItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantLunchItineraryDate().setItemItinerary(entity.getLodgeCantLunchItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantLunchItineraryDate( null );
            }
            if (entity.getLodgeCantDinnerItineraryDate() != null &&
                    entity.getLodgeCantDinnerItineraryDate().getId() != null &&
                    !entity.getLodgeCantDinnerItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantDinnerItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setId(entity.getLodgeCantDinnerItineraryDate().getId());
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setIndex(entity.getLodgeCantDinnerItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setItineraryDate(entity.getLodgeCantDinnerItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setName(entity.getLodgeCantDinnerItineraryDate().getName());
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setDescription(entity.getLodgeCantDinnerItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setIdentify(entity.getLodgeCantDinnerItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantDinnerItineraryDate().setItemItinerary(entity.getLodgeCantDinnerItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantDinnerItineraryDate( null );
            }
            if (entity.getLodgeCantBoxLunchItineraryDate() != null &&
                    entity.getLodgeCantBoxLunchItineraryDate().getId() != null &&
                    !entity.getLodgeCantBoxLunchItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBoxLunchItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setId(entity.getLodgeCantBoxLunchItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setIndex(entity.getLodgeCantBoxLunchItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setItineraryDate(entity.getLodgeCantBoxLunchItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setName(entity.getLodgeCantBoxLunchItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setDescription(entity.getLodgeCantBoxLunchItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setIdentify(entity.getLodgeCantBoxLunchItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().setItemItinerary(entity.getLodgeCantBoxLunchItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBoxLunchItineraryDate( null );
            }

            itineraryItemDomain.setLodgeCantNight( entity.getLodgeCantNight() );
            itineraryItemDomain.setLodgeCantBreakfastTcGuide( entity.getLodgeCantBreakfastTcGuide() );
            itineraryItemDomain.setLodgeCantLunchTcGuide( entity.getLodgeCantLunchTcGuide() );
            itineraryItemDomain.setLodgeCantDinnerTcGuide( entity.getLodgeCantDinnerTcGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchTcGuide( entity.getLodgeCantBoxLunchTcGuide() );
            itineraryItemDomain.setLodgeCantBreakfastTcGuideDateTime( entity.getLodgeCantBreakfastTcGuideDateTime() );
            itineraryItemDomain.setLodgeCantLunchTcGuideDateTime( entity.getLodgeCantLunchTcGuideDateTime() );
            itineraryItemDomain.setLodgeCantDinnerTcGuideDateTime( entity.getLodgeCantDinnerTcGuideDateTime() );
            itineraryItemDomain.setLodgeCantBoxLunchTcGuideDateTime( entity.getLodgeCantBoxLunchTcGuideDateTime() );
            itineraryItemDomain.setLodgeCantBreakfastTcGuideDate( entity.getLodgeCantBreakfastTcGuideDate() );
            itineraryItemDomain.setLodgeCantLunchTcGuideDate( entity.getLodgeCantLunchTcGuideDate() );
            itineraryItemDomain.setLodgeCantDinnerTcGuideDate( entity.getLodgeCantDinnerTcGuideDate() );
            itineraryItemDomain.setLodgeCantBoxLunchTcGuideDate( entity.getLodgeCantBoxLunchTcGuideDate() );
            if (entity.getLodgeCantBreakfastTcGuideItineraryDate() != null &&
                    entity.getLodgeCantBreakfastTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setId(entity.getLodgeCantBreakfastTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setIndex(entity.getLodgeCantBreakfastTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setItineraryDate(entity.getLodgeCantBreakfastTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setName(entity.getLodgeCantBreakfastTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setDescription(entity.getLodgeCantBreakfastTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setIdentify(entity.getLodgeCantBreakfastTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().setItemItinerary(entity.getLodgeCantBreakfastTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBreakfastTcGuideItineraryDate( null );
            }
            if (entity.getLodgeCantLunchTcGuideItineraryDate() != null &&
                    entity.getLodgeCantLunchTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantLunchTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantLunchTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setId(entity.getLodgeCantLunchTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setIndex(entity.getLodgeCantLunchTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setItineraryDate(entity.getLodgeCantLunchTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setName(entity.getLodgeCantLunchTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setDescription(entity.getLodgeCantLunchTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setIdentify(entity.getLodgeCantLunchTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().setItemItinerary(entity.getLodgeCantLunchTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantLunchTcGuideItineraryDate( null );
            }
            if (entity.getLodgeCantDinnerTcGuideItineraryDate() != null &&
                    entity.getLodgeCantDinnerTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantDinnerTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantDinnerTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setId(entity.getLodgeCantDinnerTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setIndex(entity.getLodgeCantDinnerTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setItineraryDate(entity.getLodgeCantDinnerTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setName(entity.getLodgeCantDinnerTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setDescription(entity.getLodgeCantDinnerTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setIdentify(entity.getLodgeCantDinnerTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().setItemItinerary(entity.getLodgeCantDinnerTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantDinnerTcGuideItineraryDate( null );
            }
            if (entity.getLodgeCantBoxLunchTcGuideItineraryDate() != null &&
                    entity.getLodgeCantBoxLunchTcGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBoxLunchTcGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBoxLunchTcGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setId(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setIndex(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setItineraryDate(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setName(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setDescription(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setIdentify(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().setItemItinerary(entity.getLodgeCantBoxLunchTcGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBoxLunchTcGuideItineraryDate( null );
            }

            itineraryItemDomain.setLodgeCantSwbTcGuide( entity.getLodgeCantSwbTcGuide() );
            itineraryItemDomain.setLodgeCantTwinTcGuide( entity.getLodgeCantTwinTcGuide() );
            itineraryItemDomain.setLodgeTcCantNight( entity.getLodgeTcCantNight() );
            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuide( entity.getLodgeCantBreakfastTcLocalGuide() );
            itineraryItemDomain.setLodgeCantLunchTcLocalGuide( entity.getLodgeCantLunchTcLocalGuide() );
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuide( entity.getLodgeCantDinnerTcLocalGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuide( entity.getLodgeCantBoxLunchTcLocalGuide() );
            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideDateTime( entity.getLodgeCantBreakfastTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgeCantLunchTcLocalGuideDateTime( entity.getLodgeCantLunchTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuideDateTime( entity.getLodgeCantDinnerTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideDateTime( entity.getLodgeCantBoxLunchTcLocalGuideDateTime() );
            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideDate( entity.getLodgeCantBreakfastTcLocalGuideDate() );
            itineraryItemDomain.setLodgeCantLunchTcLocalGuideDate( entity.getLodgeCantLunchTcLocalGuideDate() );
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuideDate( entity.getLodgeCantDinnerTcLocalGuideDate() );
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideDate( entity.getLodgeCantBoxLunchTcLocalGuideDate() );
            if (entity.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null &&
                    entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setId(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setIndex(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setName(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setDescription(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setIdentify(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgeCantBreakfastTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgeCantLunchTcLocalGuideItineraryDate() != null &&
                    entity.getLodgeCantLunchTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantLunchTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setId(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setIndex(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setName(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setDescription(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setIdentify(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgeCantLunchTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantLunchTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgeCantDinnerTcLocalGuideItineraryDate() != null &&
                    entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantDinnerTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setId(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setIndex(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setName(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setDescription(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setIdentify(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgeCantDinnerTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantDinnerTcLocalGuideItineraryDate( null );
            }
            if (entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null &&
                    entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setId(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setIndex(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setItineraryDate(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setName(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setDescription(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setIdentify(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().setItemItinerary(entity.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideItineraryDate( null );
            }

            itineraryItemDomain.setLodgeCantSwbTcLocalGuide( entity.getLodgeCantSwbTcLocalGuide() );
            itineraryItemDomain.setLodgeCantTwinTcLocalGuide( entity.getLodgeCantTwinTcLocalGuide() );
            itineraryItemDomain.setLodgeTcLocalCantNight( entity.getLodgeTcLocalCantNight() );
            itineraryItemDomain.setLodgeCantBreakfastGuide( entity.getLodgeCantBreakfastGuide() );
            itineraryItemDomain.setLodgeCantLunchGuide( entity.getLodgeCantLunchGuide() );
            itineraryItemDomain.setLodgeCantDinnerGuide( entity.getLodgeCantDinnerGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchGuide( entity.getLodgeCantBoxLunchGuide() );
            itineraryItemDomain.setLodgeCantBreakfastGuideDateTime( entity.getLodgeCantBreakfastGuideDateTime() );
            itineraryItemDomain.setLodgeCantLunchGuideDateTime( entity.getLodgeCantLunchGuideDateTime() );
            itineraryItemDomain.setLodgeCantDinnerGuideDateTime( entity.getLodgeCantDinnerGuideDateTime() );
            itineraryItemDomain.setLodgeCantBoxLunchGuideDateTime( entity.getLodgeCantBoxLunchGuideDateTime() );
            itineraryItemDomain.setLodgeCantBreakfastGuideDate( entity.getLodgeCantBreakfastGuideDate() );
            itineraryItemDomain.setLodgeCantLunchGuideDate( entity.getLodgeCantLunchGuideDate() );
            itineraryItemDomain.setLodgeCantDinnerGuideDate( entity.getLodgeCantDinnerGuideDate() );
            itineraryItemDomain.setLodgeCantBoxLunchGuideDate( entity.getLodgeCantBoxLunchGuideDate() );

            if (entity.getLodgeCantBreakfastGuideItineraryDate() != null &&
                    entity.getLodgeCantBreakfastGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBreakfastGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBreakfastGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setId(entity.getLodgeCantBreakfastGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setIndex(entity.getLodgeCantBreakfastGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setItineraryDate(entity.getLodgeCantBreakfastGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setName(entity.getLodgeCantBreakfastGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setDescription(entity.getLodgeCantBreakfastGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setIdentify(entity.getLodgeCantBreakfastGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().setItemItinerary(entity.getLodgeCantBreakfastGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBreakfastGuideItineraryDate( null );
            }
            if (entity.getLodgeCantLunchGuideItineraryDate() != null &&
                    entity.getLodgeCantLunchGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantLunchGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantLunchGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setId(entity.getLodgeCantLunchGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setIndex(entity.getLodgeCantLunchGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setItineraryDate(entity.getLodgeCantLunchGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setName(entity.getLodgeCantLunchGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setDescription(entity.getLodgeCantLunchGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setIdentify(entity.getLodgeCantLunchGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().setItemItinerary(entity.getLodgeCantLunchGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantLunchGuideItineraryDate( null );
            }
            if (entity.getLodgeCantDinnerGuideItineraryDate() != null &&
                    entity.getLodgeCantDinnerGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantDinnerGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantDinnerGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setId(entity.getLodgeCantDinnerGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setIndex(entity.getLodgeCantDinnerGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setItineraryDate(entity.getLodgeCantDinnerGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setName(entity.getLodgeCantDinnerGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setDescription(entity.getLodgeCantDinnerGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setIdentify(entity.getLodgeCantDinnerGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().setItemItinerary(entity.getLodgeCantDinnerGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantDinnerGuideItineraryDate( null );
            }
            if (entity.getLodgeCantBoxLunchGuideItineraryDate() != null &&
                    entity.getLodgeCantBoxLunchGuideItineraryDate().getId() != null &&
                    !entity.getLodgeCantBoxLunchGuideItineraryDate().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCantBoxLunchGuideItineraryDate( new ItineraryDateDomain() );
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setId(entity.getLodgeCantBoxLunchGuideItineraryDate().getId());
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setIndex(entity.getLodgeCantBoxLunchGuideItineraryDate().getIndex());
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setItineraryDate(entity.getLodgeCantBoxLunchGuideItineraryDate().getItineraryDate());
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setName(entity.getLodgeCantBoxLunchGuideItineraryDate().getName());
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setDescription(entity.getLodgeCantBoxLunchGuideItineraryDate().getDescription());
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setIdentify(entity.getLodgeCantBoxLunchGuideItineraryDate().getIdentify());
                itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().setItemItinerary(entity.getLodgeCantBoxLunchGuideItineraryDate().getItemItinerary());
            }else{
                itineraryItemDomain.setLodgeCantBoxLunchGuideItineraryDate( null );
            }
            itineraryItemDomain.setLodgeCantSwbGuide( entity.getLodgeCantSwbGuide() );
            itineraryItemDomain.setLodgeCantTwinGuide( entity.getLodgeCantTwinGuide() );
            itineraryItemDomain.setLodgeGuideCantNight( entity.getLodgeGuideCantNight() );
            itineraryItemDomain.setLodgePvt( entity.getLodgePvt() );
            itineraryItemDomain.setLodgeSic( entity.getLodgeSic() );

            itineraryItemDomain.setOperatorPvt( entity.getOperatorPvt() );
            itineraryItemDomain.setOperatorSic( entity.getOperatorSic() );

            itineraryItemDomain.setRestaurantCantBreakfast(entity.getRestaurantCantBreakfast());
            itineraryItemDomain.setRestaurantCantLunch(entity.getRestaurantCantLunch());
            itineraryItemDomain.setRestaurantCantDinner(entity.getRestaurantCantDinner());
            itineraryItemDomain.setRestaurantCantBoxLunch(entity.getRestaurantCantBoxLunch());

            itineraryItemDomain.setRestaurantCantBreakfastTcGuide(entity.getRestaurantCantBreakfastTcGuide());
            itineraryItemDomain.setRestaurantCantLunchTcGuide(entity.getRestaurantCantLunchTcGuide());
            itineraryItemDomain.setRestaurantCantDinnerTcGuide(entity.getRestaurantCantDinnerTcGuide());
            itineraryItemDomain.setRestaurantCantBoxLunchTcGuide(entity.getRestaurantCantBoxLunchTcGuide());

            itineraryItemDomain.setRestaurantCantBreakfastTcLocalGuide(entity.getRestaurantCantBreakfastTcLocalGuide());
            itineraryItemDomain.setRestaurantCantLunchTcLocalGuide(entity.getRestaurantCantLunchTcLocalGuide());
            itineraryItemDomain.setRestaurantCantDinnerTcLocalGuide(entity.getRestaurantCantDinnerTcLocalGuide());
            itineraryItemDomain.setRestaurantCantBoxLunchTcLocalGuide(entity.getRestaurantCantBoxLunchTcLocalGuide());

            itineraryItemDomain.setRestaurantCantBreakfastGuide(entity.getRestaurantCantBreakfastGuide());
            itineraryItemDomain.setRestaurantCantLunchGuide(entity.getRestaurantCantLunchGuide());
            itineraryItemDomain.setRestaurantCantDinnerGuide(entity.getRestaurantCantDinnerGuide());
            itineraryItemDomain.setRestaurantCantBoxLunchGuide(entity.getRestaurantCantBoxLunchGuide());

            itineraryItemDomain.setRestaurantCantBreakfastDrive(entity.getRestaurantCantBreakfastDrive());
            itineraryItemDomain.setRestaurantCantLunchDrive(entity.getRestaurantCantLunchDrive());
            itineraryItemDomain.setRestaurantCantDinnerDrive(entity.getRestaurantCantDinnerDrive());
            itineraryItemDomain.setRestaurantCantBoxLunchDrive(entity.getRestaurantCantBoxLunchDrive());

            return itineraryItemDomain;
        }).toList();
    }
}
