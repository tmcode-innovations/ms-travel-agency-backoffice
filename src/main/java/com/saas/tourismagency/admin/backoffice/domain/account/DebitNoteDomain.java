package com.saas.tourismagency.admin.backoffice.domain.account;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class DebitNoteDomain extends DocumentFinanceDomain implements Serializable {

    private String identifyDocumentFinance;

    private String documentAccountTypeAssociate;

    private DocumentFinanceDomain documentFinanceAssociate;

}
