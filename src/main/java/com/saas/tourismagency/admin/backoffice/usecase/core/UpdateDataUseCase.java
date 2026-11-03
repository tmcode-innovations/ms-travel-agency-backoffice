package com.saas.tourismagency.admin.backoffice.usecase.core;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.updatedata.UpdateDataCustomerProcessRequest;

public interface UpdateDataUseCase {

    public void updateItineraryItemDateTimeInBooking();

    public void updateItineraryItemCustomerInBooking();

    public void updateDateChangeTypeInAccount();

    public void updateFieldAbbrevInServiceMaintenance();

    public void updateFieldSummaryInSupplierMaintenance();

    public void updateChangeCustomerProcess(UpdateDataCustomerProcessRequest request);

    public void addProfileConsultantAccount();

    public void updateEmployeeInQuotationByCreatedUser();
}
