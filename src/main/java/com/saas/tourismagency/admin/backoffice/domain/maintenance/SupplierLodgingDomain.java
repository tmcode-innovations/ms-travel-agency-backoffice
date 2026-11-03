package com.saas.tourismagency.admin.backoffice.domain.maintenance;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class SupplierLodgingDomain extends SupplierDomain implements Serializable {

    private String lodgingType;

    private LodgingCategoryDomain lodgingCategory;

}
