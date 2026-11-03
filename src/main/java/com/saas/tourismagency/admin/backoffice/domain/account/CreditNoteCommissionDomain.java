package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class CreditNoteCommissionDomain extends DocumentFinanceDomain implements Serializable {

    private String reference;

    private String identifyDocumentFinance;

    private String documentAccountTypeAssociate;

    private DocumentFinanceDomain documentFinanceAssociate;

    private SupplierDomain supplier;

}
