package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core;

import java.util.List;

public final class BaseEntityClassAliases {

    private BaseEntityClassAliases() {
    }

    public static final String CUSTOMER_ENTITY_DATA = "CustomerEntityData";

    public static final List<String> CUSTOMER = List.of(
            CUSTOMER_ENTITY_DATA);

    public static final List<String> SUPPLIER = List.of(
            "SupplierEntityData",
            "SupplierLodgingEntityData",
            "SupplierRestaurantEntityData",
            "SupplierGuideEntityData",
            "SupplierTransportEntityData",
            "SupplierOperatorEntityData",
            "SupplierAirLineEntityData",
            "SupplierLodgerEntityData",
            "SupplierAdministrativeEntityData",
            "SupplierGlampingEntityData",
            "SupplierInstitutionEntityData");
}
