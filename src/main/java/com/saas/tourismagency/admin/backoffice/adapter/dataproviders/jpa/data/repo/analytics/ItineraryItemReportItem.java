package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ItineraryItemReportItem {

    private static final DateTimeFormatter FMT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FMT_TIME = DateTimeFormatter.ofPattern("HH:mm");

    private String observation;
    private LocalDateTime itineraryDateTime;

    private Integer paxAdultTotal;
    private Integer paxChildrenTotal;
    private Integer paxTcTotal;
    private Integer paxTcLocalTotal;
    private Integer paxGuideTotal;

    private Boolean operatorPvt;
    private Boolean operatorSic;

    private String serviceName;
    private String serviceAbbrev;
    private String supplierName;
    private String departmentRegionName;
    private String registerGroupName;
    private String registerGroupIdentify;
    private String customerName;
    private String responsibleName;
    private String touristDestineIdentify;
    private String identify;

    private String guideTransferDriveName;
    private String guideTransportName;

    private LocalDateTime orderServiceDateTimeService;
    private LocalDateTime orderServiceDateTimePax;

    public String getStrDate() { return itineraryDateTime != null ? itineraryDateTime.format(FMT_DATE) : ""; }
    public String getStrTime() { return itineraryDateTime != null ? itineraryDateTime.format(FMT_TIME) : ""; }
    public String getStrTimeOrderService() { return orderServiceDateTimeService != null ? orderServiceDateTimeService.format(FMT_TIME) : ""; }
    public String getStrServiceComplete() {
        StringBuilder sb = new StringBuilder();
        if (serviceName != null && !serviceName.isBlank()) { sb.append(serviceName); }
        if (serviceAbbrev != null && !serviceAbbrev.isBlank()) { if (sb.length() > 0) { sb.append(" "); } sb.append("(").append(serviceAbbrev).append(")"); }
        return sb.toString();
    }
    public Integer getCantPax() {
        int total = 0;
        if (paxAdultTotal != null) { total += paxAdultTotal; }
        if (paxChildrenTotal != null) { total += paxChildrenTotal; }
        if (paxTcTotal != null) { total += paxTcTotal; }
        if (paxTcLocalTotal != null) { total += paxTcLocalTotal; }
        if (paxGuideTotal != null) { total += paxGuideTotal; }
        return total;
    }

    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }

    public LocalDateTime getItineraryDateTime() { return itineraryDateTime; }
    public void setItineraryDateTime(LocalDateTime itineraryDateTime) { this.itineraryDateTime = itineraryDateTime; }

    public Integer getPaxAdultTotal() { return paxAdultTotal; }
    public void setPaxAdultTotal(Integer paxAdultTotal) { this.paxAdultTotal = paxAdultTotal; }

    public Integer getPaxChildrenTotal() { return paxChildrenTotal; }
    public void setPaxChildrenTotal(Integer paxChildrenTotal) { this.paxChildrenTotal = paxChildrenTotal; }

    public Integer getPaxTcTotal() { return paxTcTotal; }
    public void setPaxTcTotal(Integer paxTcTotal) { this.paxTcTotal = paxTcTotal; }

    public Integer getPaxTcLocalTotal() { return paxTcLocalTotal; }
    public void setPaxTcLocalTotal(Integer paxTcLocalTotal) { this.paxTcLocalTotal = paxTcLocalTotal; }

    public Integer getPaxGuideTotal() { return paxGuideTotal; }
    public void setPaxGuideTotal(Integer paxGuideTotal) { this.paxGuideTotal = paxGuideTotal; }

    public Boolean getOperatorPvt() { return operatorPvt; }
    public void setOperatorPvt(Boolean operatorPvt) { this.operatorPvt = operatorPvt; }

    public Boolean getOperatorSic() { return operatorSic; }
    public void setOperatorSic(Boolean operatorSic) { this.operatorSic = operatorSic; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public String getServiceAbbrev() { return serviceAbbrev; }
    public void setServiceAbbrev(String serviceAbbrev) { this.serviceAbbrev = serviceAbbrev; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public String getDepartmentRegionName() { return departmentRegionName; }
    public void setDepartmentRegionName(String departmentRegionName) { this.departmentRegionName = departmentRegionName; }

    public String getRegisterGroupName() { return registerGroupName; }
    public void setRegisterGroupName(String registerGroupName) { this.registerGroupName = registerGroupName; }

    public String getGuideTransferDriveName() { return guideTransferDriveName; }
    public void setGuideTransferDriveName(String guideTransferDriveName) { this.guideTransferDriveName = guideTransferDriveName; }

    public String getGuideTransportName() { return guideTransportName; }
    public void setGuideTransportName(String guideTransportName) { this.guideTransportName = guideTransportName; }

    public LocalDateTime getOrderServiceDateTimeService() { return orderServiceDateTimeService; }
    public void setOrderServiceDateTimeService(LocalDateTime orderServiceDateTimeService) { this.orderServiceDateTimeService = orderServiceDateTimeService; }

    public LocalDateTime getOrderServiceDateTimePax() { return orderServiceDateTimePax; }
    public void setOrderServiceDateTimePax(LocalDateTime orderServiceDateTimePax) { this.orderServiceDateTimePax = orderServiceDateTimePax; }

    public String getRegisterGroupIdentify() { return registerGroupIdentify; }
    public void setRegisterGroupIdentify(String registerGroupIdentify) { this.registerGroupIdentify = registerGroupIdentify; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getResponsibleName() { return responsibleName; }
    public void setResponsibleName(String responsibleName) { this.responsibleName = responsibleName; }

    public String getTouristDestineIdentify() { return touristDestineIdentify; }
    public void setTouristDestineIdentify(String touristDestineIdentify) { this.touristDestineIdentify = touristDestineIdentify; }

    public String getIdentify() { return identify; }
    public void setIdentify(String identify) { this.identify = identify; }
}