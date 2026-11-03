package com.saas.tourismagency.admin.backoffice.domain.crm;

import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class QuotationDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer cantDayTour;

    private Integer paxTotal;

    private Integer paxAdult;
    private Integer paxChild;
    private Integer paxTc;
    private Integer paxTcLocal;
    private Integer guide;

    private LocalDate dateInitTour;
    private LocalDate dateEndTour;

    private String observation;

    private String stateQuotation;

    private Double amount;
    private Double profitPercent;
    private Double otherExpenses;

    private CustomerDomain customer;
    private ProgramDomain program;
    private EmployeeDomain employee;
    private IdiomDomain idiom;
    private CountryDomain country;

    private EmployeeDomain responsibleFile;

    private CurrencyDomain currency;

    private String identifyFile;

    private String quotationType;

    private String urlFileQuotation;
    private String urlFileItinerary;
    private String fileNameQuotation;
    private String fileNameItinerary;

    private String linkGDrive;
    private String linkBitrix;
    private String linkWetravel;

    private String emailPassengerPrincipal;
    private String mobilePassengerPrincipal;
    private String whatsappPassengerPrincipal;

    @Getter(AccessLevel.NONE)
    private String amountFormat;

    public String getAmountFormat() {
        return NumberUtil.getImportFormat(amount);
    }
}
