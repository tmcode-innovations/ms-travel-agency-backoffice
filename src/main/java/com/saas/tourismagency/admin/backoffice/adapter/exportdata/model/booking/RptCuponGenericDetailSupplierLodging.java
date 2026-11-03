package com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RptCuponGenericDetailSupplierLodging extends RptCuponGenericDetail{

    private String nightLodging;
    private String dateInit;
    private String dateEnd;
    private String rooms;
    private String foods;
    private String dinner;
    private String lunch;
    private String breakfast;
    private String boxLunch;
    private String category;
    private String observation;
    private String companyName;
    private Boolean showExportService;

}
