package com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RptCuponGenericDetail {

    @EqualsAndHashCode.Include
    protected String id;

    protected RptCuponGeneric parent;

}
