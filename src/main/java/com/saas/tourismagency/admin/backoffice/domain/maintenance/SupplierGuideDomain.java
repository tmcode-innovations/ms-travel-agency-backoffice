package com.saas.tourismagency.admin.backoffice.domain.maintenance;

import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class SupplierGuideDomain extends SupplierDomain implements Serializable {

    private Set<IdiomDomain> idioms;

}
