package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.TransferControlByTourBibleSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.TransferControlByTourBibleSearchResponse;

public interface TransferControlServiceUseCase {

    public TransferControlByTourBibleSearchResponse searchFilterAllTransferControlByServiceBible(TransferControlByTourBibleSearchFilterRequest searchFilterRequest);

    public byte[] searchFilterAllTransferControlByServiceBibleExportPdf(TransferControlByTourBibleSearchFilterRequest searchFilterRequest);

    public byte[] searchFilterAllTransferControlByServiceBibleExportExcel(TransferControlByTourBibleSearchFilterRequest searchFilterRequest);

}
