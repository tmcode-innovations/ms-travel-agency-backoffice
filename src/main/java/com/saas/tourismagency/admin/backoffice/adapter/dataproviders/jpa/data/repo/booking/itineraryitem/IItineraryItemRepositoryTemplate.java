package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupGuideTransferDriveDomain;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IItineraryItemRepositoryTemplate {

    public PageDTO<ItineraryItemDomain> query(Pageable pageable, Query query);

    public List<ItineraryItemDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<RegisterGroupGuideTransferDriveDomain> queryAllGuideTransferDriveRegisterGroup(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomain(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseShowItinerary(Query query);

    public ItineraryItemDomain queryOneIdSimpleDomainCaseUpdateOrderItinerary(Query query);

    public void updateIdSimpleDomainCaseUpdateOrderItinerary(Query query, Update update);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseUpdateOrderItinerary(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseGenerateBooking(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseGenerateBookingAction(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseGenerateBookingActionHotel(Query query);

    public PageDTO<ItineraryItemDomain> queryAllSimpleDomainCaseTransferControl(Pageable pageable, Query query);

    public PageDTO<ItineraryItemDomain> queryAllSimpleDomainCaseTransferControlByTourBible(Pageable pageable, Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseTransferControlByTourBibleExport(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseTransferControlExport(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseValidationExistBooking(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseDeleteTours(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseToolUpdateCustomer(Query query);

    public void updateIdSimpleDomainCaseUpdateToolCustomer(Query query, Update update);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseServiceRequest(Query query);

    public void updateIdSimpleDomainCaseUpdateCantPax(Query query, Update update);


    public Page<ItineraryItemEntity> aggregatePageableTransferControlByTourBible(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    public Page<ItineraryItemEntity> aggregatePageableTransferControlBible(ISearchFilterPageDomain searchFilterDomain, Pageable pageable, List<ObjectId> listSupplierType);

    public List<ItineraryItemEntity> aggregateTransferControlByTourBible(ISearchFilterPageDomain searchFilterDomain);

    public List<ItineraryItemEntity> aggregateTransferControlBible(ISearchFilterPageDomain searchFilterDomain, List<ObjectId> listSupplierType);

    public Page<ItineraryItemEntity> aggregatePageableTransferControlByServiceBible(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    public List<ItineraryItemEntity> aggregateTransferControlByServiceBible(ISearchFilterPageDomain searchFilterDomain);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseVoucherEgressSupplier(Query query);

    public void updateIdSimpleDomainCaseUpdateRegisterBookingItemOperator(Query query, Update update);

    public void updateIdSimpleDomainCaseUpdateRegisterBookingItemLodger(Query query, Update update);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseDeleteByRegisterGroup(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseAdvancedByRegisterGroup(Query query);

    public List<ItineraryItemDomain> queryAllByRegisterGroupAndSupplierSimpleDomainCaseAnnulled(Query query);

    public void updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryAnnulled(Query query, Update update);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseExportItineraryByRegisterGroup(Query query);

    public List<ItineraryItemDomain> queryAllByRegisterGroupSimpleDomainCaseProgram(Query query);

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(Query query, Update update);

    public List<ItineraryItemDomain> queryAllByRegisterGroupAndSupplierSimpleDomainCaseGenerateAllBooking(Query query);

    public void updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryGenerateBookingAll(Query query, Update update);

    public void updateIdSimpleDomainCaseCustomerModification(Query query, Update update);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseVoucherEgress(Query query);

    public ItineraryItemDomain queryOneIdSimpleDomainCaseUpdateOrderItineraryVoucherEgress(Query query);

    public List<ItineraryItemDomain> queryAllSimpleDomainCaseResendFileToSale(Query query);

    public void updateIdSimpleDomainCaseUpdateIdItineraryItemQuotation(Query query, Update update);

}
