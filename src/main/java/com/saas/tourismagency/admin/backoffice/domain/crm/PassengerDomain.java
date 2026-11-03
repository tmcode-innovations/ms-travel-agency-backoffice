package com.saas.tourismagency.admin.backoffice.domain.crm;

import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class PassengerDomain extends BaseDomain implements Serializable, Comparable<PassengerDomain> {

    private String name;
    private String description;
    private String identify;

    private SocialMediaInformationEmbeddedDomain socialMediaInformation;
    private WebSiteInformationEmbeddedDomain webSiteInformation;
    private ContactInformationEmbeddedDomain contactInformation;
    private AddressEmbeddedDomain address;

    private LocalDate dateInit;
    private LocalDate dateEnd;
    private String document;
    private String observation;
    private String imageUrl;

    private Boolean principal;

    private String titlePerson;
    private String typePassengerTcBooking;

    private PersonDomain person;

    private NationalityDomain nationality;

    private DocumentTypeDomain documentType;

    private QuotationDomain quotation;

    private PassengerTypeDomain passengerType;

    private Integer index;

    @Override
    public int compareTo(PassengerDomain other) {
        int value = 0;
        if ( index != null && other.getIndex() != null ){
            value = index.compareTo(other.getIndex());
        }
        return value;
    }
}
