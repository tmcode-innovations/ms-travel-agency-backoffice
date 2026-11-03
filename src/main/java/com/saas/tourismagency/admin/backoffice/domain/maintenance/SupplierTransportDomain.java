package com.saas.tourismagency.admin.backoffice.domain.maintenance;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class SupplierTransportDomain extends SupplierDomain implements Serializable {

    private TransportTypeDomain transportType;

}
