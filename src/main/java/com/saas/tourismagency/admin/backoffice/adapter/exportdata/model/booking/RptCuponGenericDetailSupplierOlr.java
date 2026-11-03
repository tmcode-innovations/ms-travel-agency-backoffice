package com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RptCuponGenericDetailSupplierOlr extends RptCuponGenericDetail {

    private String dateService;
    private String nameService;
    private String observationService;
    private String typeService;

}
