package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics;

import java.time.LocalDate;

public class RegisterGroupReportItem {

    private String name;
    private String identify;
    private String customerName;
    private String responsibleName;
    private String programName;
    private Integer cantPax;
    private Integer cantPaxAdult;
    private Integer cantPaxChildren;
    private Integer tC;
    private Integer tCLocal;
    private LocalDate initTour;
    private LocalDate endTour;
    private String arriveAir;
    private String departureAir;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getIdentify() { return identify; }
    public void setIdentify(String identify) { this.identify = identify; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getResponsibleName() { return responsibleName; }
    public void setResponsibleName(String responsibleName) { this.responsibleName = responsibleName; }
    public String getProgramName() { return programName; }
    public void setProgramName(String programName) { this.programName = programName; }
    public Integer getCantPax() { return cantPax; }
    public void setCantPax(Integer cantPax) { this.cantPax = cantPax; }
    public Integer getCantPaxAdult() { return cantPaxAdult; }
    public void setCantPaxAdult(Integer cantPaxAdult) { this.cantPaxAdult = cantPaxAdult; }
    public Integer getCantPaxChildren() { return cantPaxChildren; }
    public void setCantPaxChildren(Integer cantPaxChildren) { this.cantPaxChildren = cantPaxChildren; }
    public Integer gettC() { return tC; }
    public void settC(Integer tC) { this.tC = tC; }
    public Integer gettCLocal() { return tCLocal; }
    public void settCLocal(Integer tCLocal) { this.tCLocal = tCLocal; }
    public LocalDate getInitTour() { return initTour; }
    public void setInitTour(LocalDate initTour) { this.initTour = initTour; }
    public LocalDate getEndTour() { return endTour; }
    public void setEndTour(LocalDate endTour) { this.endTour = endTour; }
    public String getArriveAir() { return arriveAir; }
    public void setArriveAir(String arriveAir) { this.arriveAir = arriveAir; }
    public String getDepartureAir() { return departureAir; }
    public void setDepartureAir(String departureAir) { this.departureAir = departureAir; }
}