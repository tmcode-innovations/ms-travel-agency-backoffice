package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.nomenclature.*;

import java.util.List;

public interface MaintenanceNomenclatureUseCase {

    public List<AffectIgvResponse> searchAllAffectIgv();

    public List<BookingClassificationResponse> searchAllBookingClassification();

    public List<ContactTypeResponse> searchAllContactType();

    public List<CustomerTypeResponse> searchAllCustomerType();

    public List<GuideTypeResponse> searchAllGuideType();

    public List<LodgingTypeResponse> searchAllLodgingType();

    public List<ServiceGroupResponse> searchAllServiceGroup();

    public List<ServiceTypeResponse> searchAllServiceType();

    public List<SupplierTypeOperatorResponse> searchAllSupplierTypeOperator();

    public List<TypeTourConductResponse> searchAllTypeTourConduct();

}
