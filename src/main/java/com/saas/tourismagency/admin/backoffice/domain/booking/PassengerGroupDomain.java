package com.saas.tourismagency.admin.backoffice.domain.booking;

import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.TitlePersonEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class PassengerGroupDomain extends BaseDomain implements Serializable, Comparable<PassengerGroupDomain> {

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

    private RegisterGroupDomain registerGroup;

    private String idPassengerQuotation;

    private PassengerTypeDomain passengerType;

    private Integer index;

    @Getter(AccessLevel.NONE)
    private String titlePersonDescription;

    public String getTitlePersonDescription(){
        if (titlePerson != null && !titlePerson.isEmpty() && !titlePerson.isBlank()){
            TitlePersonEnum.TitlePersonModel titlePersonModelData = EnumMappeableService
                    .map(TitlePersonEnum.class)
                    .stream().filter(
                            titlePersonModel -> titlePersonModel.name().equals(this.getTitlePerson()))
                    .findFirst().get();
            return titlePersonModelData.description();
        }
        return null;
    }

    @Override
    public int compareTo(PassengerGroupDomain other) {
        int value = 0;
        if ( index != null && other.getIndex() != null ){
            value = index.compareTo(other.getIndex());
        }
        return value;
    }
}
