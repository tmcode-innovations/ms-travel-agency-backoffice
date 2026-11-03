package com.saas.tourismagency.admin.backoffice.domain.maintenance;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class SupplierOperatorDomain extends SupplierDomain implements Serializable {

    private String supplierTypeOperator;

    private Integer availableOrderService;

}
