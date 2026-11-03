package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.nomenclature.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.*;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.MaintenanceNomenclatureUseCase;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class MaintenanceNomenclatureUseCaseImpl implements MaintenanceNomenclatureUseCase {

    @Override
    @Transactional(readOnly = true)
    public List<AffectIgvResponse> searchAllAffectIgv() {
        return EnumMappeableService
                .map(AffectIgvEnum.class)
                .stream()
                .map(affectIgvModel -> AffectIgvResponse.from(affectIgvModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingClassificationResponse> searchAllBookingClassification() {
        return EnumMappeableService
                .map(BookingClassificationEnum.class)
                .stream()
                .map(bookingClassificationModel -> BookingClassificationResponse.from(bookingClassificationModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactTypeResponse> searchAllContactType() {
        return EnumMappeableService
                .map(ContactTypeEnum.class)
                .stream()
                .map(contactTypeModel -> ContactTypeResponse.from(contactTypeModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerTypeResponse> searchAllCustomerType() {
        return EnumMappeableService
                .map(CustomerTypeEnum.class)
                .stream()
                .map(customerTypeModel -> CustomerTypeResponse.from(customerTypeModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GuideTypeResponse> searchAllGuideType() {
        return EnumMappeableService
                .map(GuideTypeEnum.class)
                .stream()
                .map(guideTypeModel -> GuideTypeResponse.from(guideTypeModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LodgingTypeResponse> searchAllLodgingType() {
        return EnumMappeableService
                .map(LodgingTypeEnum.class)
                .stream()
                .map(lodgingTypeModel -> LodgingTypeResponse.from(lodgingTypeModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceGroupResponse> searchAllServiceGroup() {
        return EnumMappeableService
                .map(ServiceGroupEnum.class)
                .stream()
                .map(serviceGroupModel -> ServiceGroupResponse.from(serviceGroupModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceTypeResponse> searchAllServiceType() {
        return EnumMappeableService
                .map(ServiceTypeEnum.class)
                .stream()
                .map(serviceTypeModel -> ServiceTypeResponse.from(serviceTypeModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierTypeOperatorResponse> searchAllSupplierTypeOperator() {
        return EnumMappeableService
                .map(SupplierTypeOperatorEnum.class)
                .stream()
                .map(supplierTypeOperatorModel -> SupplierTypeOperatorResponse.from(supplierTypeOperatorModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeTourConductResponse> searchAllTypeTourConduct() {
        return EnumMappeableService
                .map(TypeTourConductEnum.class)
                .stream()
                .map(typeTourConductModel -> TypeTourConductResponse.from(typeTourConductModel))
                .toList();
    }

}
