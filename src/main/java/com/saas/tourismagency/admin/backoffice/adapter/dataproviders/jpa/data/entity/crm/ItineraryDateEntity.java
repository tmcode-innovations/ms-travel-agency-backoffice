package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.IdiomEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "crm_itinerary_dates")
public class ItineraryDateEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private LocalDate itineraryDate;
    private Integer index;
    private String itemItinerary;

    private @DocumentReference(lazy = true)
    QuotationEntity quotation;

    private @DocumentReference(lazy = true)
    Set<TourEntity> tours;

}
