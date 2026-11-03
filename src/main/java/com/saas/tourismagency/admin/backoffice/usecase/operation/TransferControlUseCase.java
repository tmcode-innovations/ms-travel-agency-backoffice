package com.saas.tourismagency.admin.backoffice.usecase.operation;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;

import java.util.List;

public interface TransferControlUseCase {

    public TransferControlSearchResponse searchFilterAllTransferControl(TransferControlSearchFilterRequest searchFilterRequest);

    public TransferControlByTourBibleSearchResponse searchFilterAllTransferControlByTourBible(TransferControlByTourBibleSearchFilterRequest searchFilterRequest);

    public byte[] searchFilterAllTransferControlExport(TransferControlSearchFilterRequest searchFilterRequest);

    public byte[] searchFilterAllTransferControlByTourBibleExportPdf(TransferControlByTourBibleSearchFilterRequest searchFilterRequest);

    public byte[] searchFilterAllTransferControlByTourBibleExportExcel(TransferControlByTourBibleSearchFilterRequest searchFilterRequest);

}
