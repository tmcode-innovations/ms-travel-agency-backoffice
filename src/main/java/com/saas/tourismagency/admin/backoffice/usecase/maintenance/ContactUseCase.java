package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.contact.*;

import java.util.List;

public interface ContactUseCase {

    public ContactResponse create(ContactCreateRequest request);

    public ContactResponse edit(String id, ContactUpdateRequest request);

    public void deleteById(String id);

    public List<ContactResponse> findAll();

    public List<ContactResponse> searchAllByContactType(ContactSearchRequestByContactType contactSearchRequestByContactType);

    public ContactResponse findById(String id);

    public ContactSearchResponse searchAll(ContactSearchRequest supplierSearchRequest);

    public ContactSearchResponse searchFilterAll(ContactSearchFilterRequest supplierSearchFilterRequest);

}
