package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.itineraryitem;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.ItineraryItemReportItem;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ItineraryItemReportConverter {

    public ItineraryItemDomain toDomain(ItineraryItemReportItem item) {
        if (item == null) {
            return null;
        }

        ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
        itineraryItemDomain.setObservation(item.getObservation());
        itineraryItemDomain.setItineraryDateTime(item.getItineraryDateTime());
        itineraryItemDomain.setPaxAdultTotal(item.getPaxAdultTotal());
        itineraryItemDomain.setPaxChildrenTotal(item.getPaxChildrenTotal());
        itineraryItemDomain.setPaxTcTotal(item.getPaxTcTotal());
        itineraryItemDomain.setPaxTcLocalTotal(item.getPaxTcLocalTotal());
        itineraryItemDomain.setPaxGuideTotal(item.getPaxGuideTotal());
        itineraryItemDomain.setOperatorPvt(item.getOperatorPvt());
        itineraryItemDomain.setOperatorSic(item.getOperatorSic());
        itineraryItemDomain.setIdentify(item.getIdentify());

        if (item.getOrderServiceDateTimeService() != null || item.getOrderServiceDateTimePax() != null) {
            OrderServiceItemDomain orderServiceItem = new OrderServiceItemDomain();
            orderServiceItem.setDateTimeService(item.getOrderServiceDateTimeService());
            orderServiceItem.setDateTimePax(item.getOrderServiceDateTimePax());
            itineraryItemDomain.setOrderServiceItem(orderServiceItem);
        }
        if (StringUtils.isNotEmpty(item.getIdentify())) {
            RegisterBookingBaseDomain registerBookingBase = new RegisterBookingBaseDomain();
            registerBookingBase.setIdentifyConfirmation(item.getIdentify());
            itineraryItemDomain.setRegisterBookingBase(registerBookingBase);
        }

        if (StringUtils.isNotEmpty(item.getServiceName()) || StringUtils.isNotEmpty(item.getServiceAbbrev())) {
            ServiceDomain service = new ServiceDomain();
            service.setName(item.getServiceName());
            service.setAbbrev(item.getServiceAbbrev());
            itineraryItemDomain.setService(service);
        }
        if (StringUtils.isNotEmpty(item.getSupplierName())) {
            SupplierDomain supplier = new SupplierDomain();
            supplier.setName(item.getSupplierName());
            itineraryItemDomain.setSupplier(supplier);
        }
        if (StringUtils.isNotEmpty(item.getDepartmentRegionName())) {
            DepartmentRegionDomain departmentRegion = new DepartmentRegionDomain();
            departmentRegion.setName(item.getDepartmentRegionName());
            itineraryItemDomain.setDepartmentRegion(departmentRegion);
        }
        if (StringUtils.isNotEmpty(item.getRegisterGroupName())) {
            RegisterGroupDomain registerGroup = new RegisterGroupDomain();
            registerGroup.setName(item.getRegisterGroupName());
            registerGroup.setIdentify(item.getRegisterGroupIdentify());
            if (StringUtils.isNotEmpty(item.getCustomerName())) {
                CustomerDomain customer = new CustomerDomain();
                customer.setName(item.getCustomerName());
                registerGroup.setCustomer(customer);
            }
            if (StringUtils.isNotEmpty(item.getResponsibleName())) {
                EmployeeDomain responsible = new EmployeeDomain();
                responsible.setName(item.getResponsibleName());
                registerGroup.setResponsible(responsible);
            }
            itineraryItemDomain.setRegisterGroup(registerGroup);
        }
        if (StringUtils.isNotEmpty(item.getGuideTransferDriveName())) {
            GuideDomain guideTransferDrive = new GuideDomain();
            guideTransferDrive.setName(item.getGuideTransferDriveName());
            itineraryItemDomain.setGuideTransferDrive(guideTransferDrive);
        }
        if (StringUtils.isNotEmpty(item.getGuideTransportName())) {
            GuideDomain guideTransport = new GuideDomain();
            guideTransport.setName(item.getGuideTransportName());
            itineraryItemDomain.setGuideTransport(guideTransport);
        }
        if (item.getOrderServiceDateTimeService() != null || item.getOrderServiceDateTimePax() != null) {
            OrderServiceItemDomain orderServiceItem = new OrderServiceItemDomain();
            orderServiceItem.setDateTimeService(item.getOrderServiceDateTimeService());
            orderServiceItem.setDateTimePax(item.getOrderServiceDateTimePax());
            itineraryItemDomain.setOrderServiceItem(orderServiceItem);
        }

        return itineraryItemDomain;
    }
}