package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.nomenclature.*;

import java.util.List;

public interface BookingNomenclatureUseCase {

    public List<StateBookingResponse> searchAllStateBooking();

    public List<StateRegisterGroupResponse> searchAllStateRegisterGroup();

    public List<TypeFeedingBookingResponse> searchAllTypeFeedingBooking();

    public List<TypePassengerTcBookingResponse> searchAllTypePassengerTcBooking();

    public List<StateServiceInvoiceResponse> searchAllStateServiceInvoice();

    public List<StateServiceInvoiceResponse> searchAllStateServiceInvoiceWithoutPending();

}
