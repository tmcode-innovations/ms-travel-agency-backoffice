package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.itineraryitem;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion.DepartmentRegionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.costtour.CostTourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.costtourserviceitem.CostTourServiceItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.itinerarydate.ItineraryDateCrmConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.quotation.QuotationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tour.TourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.lodgingcategory.LodgingCategoryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.LodgingCategoryDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItineraryItemCrmConverter implements Converter<ItineraryItemDomain, ItineraryItemEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private QuotationConverter quotationConverter;

    @Autowired
    private SupplierConverter supplierConverter;

    @Autowired
    private SupplierLodgingConverter supplierLodgingConverter;

    @Autowired
    private SupplierTypeConverter supplierTypeConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Autowired
    private LodgingCategoryConverter lodgingCategoryConverter;

    @Autowired
    private ItineraryDateCrmConverter itineraryDateCrmConverter;

    @Autowired
    private DepartmentRegionConverter departmentRegionConverter;

    @Autowired
    private TourConverter tourConverter;

    @Autowired
    private CostTourConverter costTourConverter;

    @Autowired
    private CostTourServiceItemConverter costTourServiceItemConverter;

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
            itineraryItemDomain.setItineraryDate( itineraryDateCrmConverter.toDomain( source.getItineraryDate() ) );
        }else{
            itineraryItemDomain.setItineraryDate( null );
        }
        if (source.getQuotation() != null &&
                source.getQuotation().getId() != null &&
                !source.getQuotation().getId().isEmpty()) {
            itineraryItemDomain.setQuotation( quotationConverter.toDomain( source.getQuotation() ) );
        }else{
            itineraryItemDomain.setQuotation( null );
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
        if (source.getTour() != null &&
                source.getTour().getId() != null &&
                !source.getTour().getId().isEmpty()) {
            itineraryItemDomain.setTour( tourConverter.toDomain( source.getTour() ) );
        }else{
            itineraryItemDomain.setTour( null );
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

        itineraryItemDomain.setLodgingCantBreakfast( source.getLodgingCantBreakfast() );
        itineraryItemDomain.setLodgingCantLunch( source.getLodgingCantLunch() );
        itineraryItemDomain.setLodgingCantDinner( source.getLodgingCantDinner() );
        itineraryItemDomain.setLodgingCantBoxLunch( source.getLodgingCantBoxLunch() );
        itineraryItemDomain.setLodgingCantNight( source.getLodgingCantNight() );
        itineraryItemDomain.setLodgingCantBreakfastTcGuide( source.getLodgingCantBreakfastTcGuide() );
        itineraryItemDomain.setLodgingCantLunchTcGuide( source.getLodgingCantLunchTcGuide() );
        itineraryItemDomain.setLodgingCantDinnerTcGuide( source.getLodgingCantDinnerTcGuide() );
        itineraryItemDomain.setLodgingCantBoxLunchTcGuide( source.getLodgingCantBoxLunchTcGuide() );
        itineraryItemDomain.setLodgingCantSwbTcGuide( source.getLodgingCantSwbTcGuide() );
        itineraryItemDomain.setLodgingCantTwinTcGuide( source.getLodgingCantTwinTcGuide() );
        itineraryItemDomain.setLodgingTcCantNight( source.getLodgingTcCantNight() );
        itineraryItemDomain.setLodgingCantBreakfastTcLocalGuide( source.getLodgingCantBreakfastTcLocalGuide() );
        itineraryItemDomain.setLodgingCantLunchTcLocalGuide( source.getLodgingCantLunchTcLocalGuide() );
        itineraryItemDomain.setLodgingCantDinnerTcLocalGuide( source.getLodgingCantDinnerTcLocalGuide() );
        itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuide( source.getLodgingCantBoxLunchTcLocalGuide() );
        itineraryItemDomain.setLodgingCantSwbTcLocalGuide( source.getLodgingCantSwbTcLocalGuide() );
        itineraryItemDomain.setLodgingCantTwinTcLocalGuide( source.getLodgingCantTwinTcLocalGuide() );
        itineraryItemDomain.setLodgingTcLocalCantNight( source.getLodgingTcLocalCantNight() );
        itineraryItemDomain.setLodgingCantBreakfastGuide( source.getLodgingCantBreakfastGuide() );
        itineraryItemDomain.setLodgingCantLunchGuide( source.getLodgingCantLunchGuide() );
        itineraryItemDomain.setLodgingCantDinnerGuide( source.getLodgingCantDinnerGuide() );
        itineraryItemDomain.setLodgingCantBoxLunchGuide( source.getLodgingCantBoxLunchGuide() );
        itineraryItemDomain.setLodgingCantSwbGuide( source.getLodgingCantSwbGuide() );
        itineraryItemDomain.setLodgingCantTwinGuide( source.getLodgingCantTwinGuide() );
        itineraryItemDomain.setLodgingGuideCantNight( source.getLodgingCantNight() );
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

        itineraryItemDomain.setLodgeCantBreakfast( source.getLodgeCantBreakfast() );
        itineraryItemDomain.setLodgeCantLunch( source.getLodgeCantLunch() );
        itineraryItemDomain.setLodgeCantDinner( source.getLodgeCantDinner() );
        itineraryItemDomain.setLodgeCantNight( source.getLodgeCantNight() );
        itineraryItemDomain.setLodgeCantBreakfastTcGuide( source.getLodgeCantBreakfastTcGuide() );
        itineraryItemDomain.setLodgeCantLunchTcGuide( source.getLodgeCantLunchTcGuide() );
        itineraryItemDomain.setLodgeCantDinnerTcGuide( source.getLodgeCantDinnerTcGuide() );
        itineraryItemDomain.setLodgeCantBoxLunchTcGuide( source.getLodgeCantBoxLunchTcGuide() );
        itineraryItemDomain.setLodgeCantSwbTcGuide( source.getLodgeCantSwbTcGuide() );
        itineraryItemDomain.setLodgeCantTwinTcGuide( source.getLodgeCantTwinTcGuide() );
        itineraryItemDomain.setLodgeTcCantNight( source.getLodgeTcCantNight() );
        itineraryItemDomain.setLodgeCantBreakfastTcLocalGuide( source.getLodgeCantBreakfastTcLocalGuide() );
        itineraryItemDomain.setLodgeCantLunchTcLocalGuide( source.getLodgeCantLunchTcLocalGuide() );
        itineraryItemDomain.setLodgeCantDinnerTcLocalGuide( source.getLodgeCantDinnerTcLocalGuide() );
        itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuide( source.getLodgeCantBoxLunchTcLocalGuide() );
        itineraryItemDomain.setLodgeCantSwbTcLocalGuide( source.getLodgeCantSwbTcLocalGuide() );
        itineraryItemDomain.setLodgeCantTwinTcLocalGuide( source.getLodgeCantTwinTcLocalGuide() );
        itineraryItemDomain.setLodgeTcLocalCantNight( source.getLodgeTcLocalCantNight() );
        itineraryItemDomain.setLodgeCantBreakfastGuide( source.getLodgeCantBreakfastGuide() );
        itineraryItemDomain.setLodgeCantLunchGuide( source.getLodgeCantLunchGuide() );
        itineraryItemDomain.setLodgeCantDinnerGuide( source.getLodgeCantDinnerGuide() );
        itineraryItemDomain.setLodgeCantBoxLunchGuide( source.getLodgeCantBoxLunchGuide() );
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
        itineraryItemDomain.setOperatorSic( source.getOperatorSic() );
        itineraryItemDomain.setLodgingForOperatorStr( source.getLodgingForOperatorStr() );
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
            itineraryItemEntity.setItineraryDate( itineraryDateCrmConverter.toEntity( source.getItineraryDate() ) );
        }else{
            itineraryItemEntity.setItineraryDate( null );
        }
        if (source.getQuotation() != null &&
                source.getQuotation().getId() != null &&
                !source.getQuotation().getId().isEmpty()) {
            itineraryItemEntity.setQuotation( quotationConverter.toEntity( source.getQuotation() ) );
        }else{
            itineraryItemEntity.setQuotation( null );
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
        if (source.getTour() != null &&
                source.getTour().getId() != null &&
                !source.getTour().getId().isEmpty()) {
            itineraryItemEntity.setTour( tourConverter.toEntity( source.getTour() ) );
        }else{
            itineraryItemEntity.setTour( null );
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

        itineraryItemEntity.setLodgingCantBreakfast( source.getLodgingCantBreakfast() );
        itineraryItemEntity.setLodgingCantLunch( source.getLodgingCantLunch() );
        itineraryItemEntity.setLodgingCantDinner( source.getLodgingCantDinner() );
        itineraryItemEntity.setLodgingCantBoxLunch( source.getLodgingCantBoxLunch() );
        itineraryItemEntity.setLodgingCantNight( source.getLodgingCantNight() );
        itineraryItemEntity.setLodgingCantBreakfastTcGuide( source.getLodgingCantBreakfastTcGuide() );
        itineraryItemEntity.setLodgingCantLunchTcGuide( source.getLodgingCantLunchTcGuide() );
        itineraryItemEntity.setLodgingCantDinnerTcGuide( source.getLodgingCantDinnerTcGuide() );
        itineraryItemEntity.setLodgingCantBoxLunchTcGuide( source.getLodgingCantBoxLunchTcGuide() );
        itineraryItemEntity.setLodgingCantSwbTcGuide( source.getLodgingCantSwbTcGuide() );
        itineraryItemEntity.setLodgingCantTwinTcGuide( source.getLodgingCantTwinTcGuide() );
        itineraryItemEntity.setLodgingTcCantNight( source.getLodgingTcCantNight() );
        itineraryItemEntity.setLodgingCantBreakfastTcLocalGuide( source.getLodgingCantBreakfastTcLocalGuide() );
        itineraryItemEntity.setLodgingCantLunchTcLocalGuide( source.getLodgingCantLunchTcLocalGuide() );
        itineraryItemEntity.setLodgingCantDinnerTcLocalGuide( source.getLodgingCantDinnerTcLocalGuide() );
        itineraryItemEntity.setLodgingCantBoxLunchTcLocalGuide( source.getLodgingCantBoxLunchTcLocalGuide() );
        itineraryItemEntity.setLodgingCantSwbTcLocalGuide( source.getLodgingCantSwbTcLocalGuide() );
        itineraryItemEntity.setLodgingCantTwinTcLocalGuide( source.getLodgingCantTwinTcLocalGuide() );
        itineraryItemEntity.setLodgingTcLocalCantNight( source.getLodgingTcLocalCantNight() );
        itineraryItemEntity.setLodgingCantBreakfastGuide( source.getLodgingCantBreakfastGuide() );
        itineraryItemEntity.setLodgingCantLunchGuide( source.getLodgingCantLunchGuide() );
        itineraryItemEntity.setLodgingCantDinnerGuide( source.getLodgingCantDinnerGuide() );
        itineraryItemEntity.setLodgingCantBoxLunchGuide( source.getLodgingCantBoxLunchGuide() );
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

        itineraryItemEntity.setLodgeCantBreakfast( source.getLodgeCantBreakfast() );
        itineraryItemEntity.setLodgeCantLunch( source.getLodgeCantLunch() );
        itineraryItemEntity.setLodgeCantDinner( source.getLodgeCantDinner() );
        itineraryItemEntity.setLodgeCantBoxLunch( source.getLodgeCantBoxLunch() );
        itineraryItemEntity.setLodgeCantNight( source.getLodgeCantNight() );
        itineraryItemEntity.setLodgeCantBreakfastTcGuide( source.getLodgeCantBreakfastTcGuide() );
        itineraryItemEntity.setLodgeCantLunchTcGuide( source.getLodgeCantLunchTcGuide() );
        itineraryItemEntity.setLodgeCantDinnerTcGuide( source.getLodgeCantDinnerTcGuide() );
        itineraryItemEntity.setLodgeCantBoxLunchTcGuide( source.getLodgeCantBoxLunchTcGuide() );
        itineraryItemEntity.setLodgeCantSwbTcGuide( source.getLodgeCantSwbTcGuide() );
        itineraryItemEntity.setLodgeCantTwinTcGuide( source.getLodgeCantTwinTcGuide() );
        itineraryItemEntity.setLodgeTcCantNight( source.getLodgeTcCantNight() );
        itineraryItemEntity.setLodgeCantBreakfastTcLocalGuide( source.getLodgeCantBreakfastTcLocalGuide() );
        itineraryItemEntity.setLodgeCantLunchTcLocalGuide( source.getLodgeCantLunchTcLocalGuide() );
        itineraryItemEntity.setLodgeCantDinnerTcLocalGuide( source.getLodgeCantDinnerTcLocalGuide() );
        itineraryItemEntity.setLodgeCantBoxLunchTcLocalGuide( source.getLodgeCantBoxLunchTcLocalGuide() );
        itineraryItemEntity.setLodgeCantSwbTcLocalGuide( source.getLodgeCantSwbTcLocalGuide() );
        itineraryItemEntity.setLodgeCantTwinTcLocalGuide( source.getLodgeCantTwinTcLocalGuide() );
        itineraryItemEntity.setLodgeTcLocalCantNight( source.getLodgeTcLocalCantNight() );
        itineraryItemEntity.setLodgeCantBreakfastGuide( source.getLodgeCantBreakfastGuide() );
        itineraryItemEntity.setLodgeCantLunchGuide( source.getLodgeCantLunchGuide() );
        itineraryItemEntity.setLodgeCantDinnerGuide( source.getLodgeCantDinnerGuide() );
        itineraryItemEntity.setLodgeCantBoxLunchGuide( source.getLodgeCantBoxLunchGuide() );
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
        itineraryItemEntity.setLodgingForOperatorStr( source.getLodgingForOperatorStr() );
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
        simpleBaseDomain.setName( source.getName() );

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
            itineraryItemDomain.setPrice(entity.getPrice());
            itineraryItemDomain.setPaxAdultTotal(entity.getPaxAdultTotal());
            itineraryItemDomain.setPriceAdult(entity.getPriceAdult());
            itineraryItemDomain.setPaxChildrenTotal(entity.getPaxChildrenTotal());
            itineraryItemDomain.setPriceChildren(entity.getPriceChildren());
            itineraryItemDomain.setPaxTcTotal(entity.getPaxTcTotal());
            itineraryItemDomain.setPriceTc(entity.getPriceTc());
            itineraryItemDomain.setPaxTcLocalTotal(entity.getPaxTcLocalTotal());
            itineraryItemDomain.setPriceTcLocal(entity.getPriceTcLocal());
            itineraryItemDomain.setPaxGuideTotal(entity.getPaxGuideTotal());
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
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseGetCalculateCost(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            itineraryItemDomain.setPrice(entity.getPrice() );
            itineraryItemDomain.setPriceAdult(entity.getPriceAdult() );
            itineraryItemDomain.setPriceChildren(entity.getPriceChildren() );
            itineraryItemDomain.setPriceTc(entity.getPriceTc() );
            itineraryItemDomain.setPriceTcLocal(entity.getPriceTcLocal() );
            itineraryItemDomain.setPriceGuide(entity.getPriceGuide() );
            itineraryItemDomain.setPaxTotal(entity.getPaxTotal() );
            itineraryItemDomain.setPaxAdultTotal(entity.getPaxAdultTotal() );
            itineraryItemDomain.setPaxChildrenTotal(entity.getPaxChildrenTotal() );
            itineraryItemDomain.setPaxTcTotal(entity.getPaxTcTotal() );
            itineraryItemDomain.setPaxTcLocalTotal(entity.getPaxTcLocalTotal() );
            itineraryItemDomain.setPaxGuideTotal(entity.getPaxGuideTotal() );
            if (entity.getService() != null &&
                    entity.getService().getId() != null &&
                    !entity.getService().getId().isEmpty()) {
                itineraryItemDomain.setService(new ServiceDomain());
                itineraryItemDomain.getService().setId(entity.getService().getId());
                itineraryItemDomain.getService().setWithoutCost(entity.getService().getWithoutCost());
            }
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseGetCollectionForLastIndex(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            return itineraryItemDomain;
        }).toList();
    }

    public ItineraryItemDomain toDomainIdSimpleDomainCaseGeneric(ItineraryItemEntity source) throws RuntimeException {
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

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseDeleteByQuotation(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            return itineraryItemDomain;
        }).toList();
    }

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseResendQuotationToBooking(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            itineraryItemDomain.setName( entity.getName() );
            itineraryItemDomain.setDescription( entity.getDescription() );
            itineraryItemDomain.setIdentify( entity.getIdentify() );
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

            itineraryItemDomain.setPrice(entity.getPrice());
            itineraryItemDomain.setPriceAdult(entity.getPriceAdult());
            itineraryItemDomain.setPriceChildren(entity.getPriceChildren());
            itineraryItemDomain.setPriceTc(entity.getPriceTc());
            itineraryItemDomain.setPriceTcLocal(entity.getPriceTcLocal());
            itineraryItemDomain.setPriceGuide(entity.getPriceGuide());

            itineraryItemDomain.setLodgingCantSwb( entity.getLodgingCantSwb() );
            itineraryItemDomain.setLodgingCantMat( entity.getLodgingCantMat() );
            itineraryItemDomain.setLodgingCantTwin( entity.getLodgingCantTwin() );
            itineraryItemDomain.setLodgingCantTrp( entity.getLodgingCantTrp() );

            if (entity.getLodgingCategory() != null &&
                    entity.getLodgingCategory().getId() != null &&
                    !entity.getLodgingCategory().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCategory( new LodgingCategoryDomain() );
                itineraryItemDomain.getLodgingCategory().setId( entity.getLodgingCategory().getId() );
            }

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
            itineraryItemDomain.setLodgingTcCantNight( entity.getLodgingTcCantNight() );
            itineraryItemDomain.setLodgingTcLocalCantNight( entity.getLodgingTcLocalCantNight() );
            if (entity.getLodgingForOperator() != null &&
                    entity.getLodgingForOperator().getId() != null &&
                    !entity.getLodgingForOperator().getId().isEmpty()) {
                itineraryItemDomain.setLodgingForOperator( supplierLodgingConverter.toDomain( entity.getLodgingForOperator() ) );
            } else {
                itineraryItemDomain.setLodgingForOperator( null );
            }
            itineraryItemDomain.setLodgingForOperatorStr( entity.getLodgingForOperatorStr() );
            itineraryItemDomain.setLodgingCantBreakfastGuide( entity.getLodgingCantBreakfastGuide() );
            itineraryItemDomain.setLodgingCantLunchGuide( entity.getLodgingCantLunchGuide() );
            itineraryItemDomain.setLodgingCantDinnerGuide( entity.getLodgingCantDinnerGuide() );
            itineraryItemDomain.setLodgingCantBoxLunchGuide( entity.getLodgingCantBoxLunchGuide() );

            itineraryItemDomain.setLodgingCantSwbGuide( entity.getLodgingCantSwbGuide() );
            itineraryItemDomain.setLodgingCantTwinGuide( entity.getLodgingCantTwinGuide() );
            itineraryItemDomain.setLodgingGuideCantNight( entity.getLodgingGuideCantNight() );
            itineraryItemDomain.setLodgeCantSwb( entity.getLodgeCantSwb() );
            itineraryItemDomain.setLodgeCantMat( entity.getLodgeCantMat() );
            itineraryItemDomain.setLodgeCantTwin( entity.getLodgeCantTwin() );
            itineraryItemDomain.setLodgeCantTrp( entity.getLodgeCantTrp() );

            if (entity.getLodgeCategory() != null &&
                    entity.getLodgeCategory().getId() != null &&
                    !entity.getLodgeCategory().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCategory( new LodgingCategoryDomain() );
                itineraryItemDomain.getLodgeCategory().setId( entity.getLodgeCategory().getId() );

            }

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
            itineraryItemDomain.setLodgeTcCantNight( entity.getLodgeTcCantNight() );
            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuide( entity.getLodgeCantBreakfastTcLocalGuide() );
            itineraryItemDomain.setLodgeCantLunchTcLocalGuide( entity.getLodgeCantLunchTcLocalGuide() );
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuide( entity.getLodgeCantDinnerTcLocalGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuide( entity.getLodgeCantBoxLunchTcLocalGuide() );

            itineraryItemDomain.setLodgeCantSwbTcLocalGuide( entity.getLodgeCantSwbTcLocalGuide() );
            itineraryItemDomain.setLodgeCantTwinTcLocalGuide( entity.getLodgeCantTwinTcLocalGuide() );
            itineraryItemDomain.setLodgeTcLocalCantNight( entity.getLodgeTcLocalCantNight() );
            itineraryItemDomain.setLodgeCantBreakfastGuide( entity.getLodgeCantBreakfastGuide() );
            itineraryItemDomain.setLodgeCantLunchGuide( entity.getLodgeCantLunchGuide() );
            itineraryItemDomain.setLodgeCantDinnerGuide( entity.getLodgeCantDinnerGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchGuide( entity.getLodgeCantBoxLunchGuide() );

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

    public List<ItineraryItemDomain> toDomainAllSimpleToDomainCaseChangeStateOpened(List<ItineraryItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId( entity.getId() );
            itineraryItemDomain.setIndex( entity.getIndex() );
            itineraryItemDomain.setName( entity.getName() );
            itineraryItemDomain.setDescription( entity.getDescription() );
            itineraryItemDomain.setIdentify( entity.getIdentify() );
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

            itineraryItemDomain.setPrice(entity.getPrice());
            itineraryItemDomain.setPriceAdult(entity.getPriceAdult());
            itineraryItemDomain.setPriceChildren(entity.getPriceChildren());
            itineraryItemDomain.setPriceTc(entity.getPriceTc());
            itineraryItemDomain.setPriceTcLocal(entity.getPriceTcLocal());
            itineraryItemDomain.setPriceGuide(entity.getPriceGuide());

            itineraryItemDomain.setLodgingCantSwb( entity.getLodgingCantSwb() );
            itineraryItemDomain.setLodgingCantMat( entity.getLodgingCantMat() );
            itineraryItemDomain.setLodgingCantTwin( entity.getLodgingCantTwin() );
            itineraryItemDomain.setLodgingCantTrp( entity.getLodgingCantTrp() );

            if (entity.getLodgingCategory() != null &&
                    entity.getLodgingCategory().getId() != null &&
                    !entity.getLodgingCategory().getId().isEmpty()) {
                itineraryItemDomain.setLodgingCategory( new LodgingCategoryDomain() );
                itineraryItemDomain.getLodgingCategory().setId( entity.getLodgingCategory().getId() );
            }

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
            itineraryItemDomain.setLodgingTcCantNight( entity.getLodgingTcCantNight() );
            itineraryItemDomain.setLodgingTcLocalCantNight( entity.getLodgingTcLocalCantNight() );
            if (entity.getLodgingForOperator() != null &&
                    entity.getLodgingForOperator().getId() != null &&
                    !entity.getLodgingForOperator().getId().isEmpty()) {
                itineraryItemDomain.setLodgingForOperator( supplierLodgingConverter.toDomain( entity.getLodgingForOperator() ) );
            } else {
                itineraryItemDomain.setLodgingForOperator( null );
            }
            itineraryItemDomain.setLodgingForOperatorStr( entity.getLodgingForOperatorStr() );
            itineraryItemDomain.setLodgingCantBreakfastGuide( entity.getLodgingCantBreakfastGuide() );
            itineraryItemDomain.setLodgingCantLunchGuide( entity.getLodgingCantLunchGuide() );
            itineraryItemDomain.setLodgingCantDinnerGuide( entity.getLodgingCantDinnerGuide() );
            itineraryItemDomain.setLodgingCantBoxLunchGuide( entity.getLodgingCantBoxLunchGuide() );

            itineraryItemDomain.setLodgingCantSwbGuide( entity.getLodgingCantSwbGuide() );
            itineraryItemDomain.setLodgingCantTwinGuide( entity.getLodgingCantTwinGuide() );
            itineraryItemDomain.setLodgingGuideCantNight( entity.getLodgingGuideCantNight() );
            itineraryItemDomain.setLodgeCantSwb( entity.getLodgeCantSwb() );
            itineraryItemDomain.setLodgeCantMat( entity.getLodgeCantMat() );
            itineraryItemDomain.setLodgeCantTwin( entity.getLodgeCantTwin() );
            itineraryItemDomain.setLodgeCantTrp( entity.getLodgeCantTrp() );

            if (entity.getLodgeCategory() != null &&
                    entity.getLodgeCategory().getId() != null &&
                    !entity.getLodgeCategory().getId().isEmpty()) {
                itineraryItemDomain.setLodgeCategory( new LodgingCategoryDomain() );
                itineraryItemDomain.getLodgeCategory().setId( entity.getLodgeCategory().getId() );

            }

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
            itineraryItemDomain.setLodgeTcCantNight( entity.getLodgeTcCantNight() );
            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuide( entity.getLodgeCantBreakfastTcLocalGuide() );
            itineraryItemDomain.setLodgeCantLunchTcLocalGuide( entity.getLodgeCantLunchTcLocalGuide() );
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuide( entity.getLodgeCantDinnerTcLocalGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuide( entity.getLodgeCantBoxLunchTcLocalGuide() );

            itineraryItemDomain.setLodgeCantSwbTcLocalGuide( entity.getLodgeCantSwbTcLocalGuide() );
            itineraryItemDomain.setLodgeCantTwinTcLocalGuide( entity.getLodgeCantTwinTcLocalGuide() );
            itineraryItemDomain.setLodgeTcLocalCantNight( entity.getLodgeTcLocalCantNight() );
            itineraryItemDomain.setLodgeCantBreakfastGuide( entity.getLodgeCantBreakfastGuide() );
            itineraryItemDomain.setLodgeCantLunchGuide( entity.getLodgeCantLunchGuide() );
            itineraryItemDomain.setLodgeCantDinnerGuide( entity.getLodgeCantDinnerGuide() );
            itineraryItemDomain.setLodgeCantBoxLunchGuide( entity.getLodgeCantBoxLunchGuide() );

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
