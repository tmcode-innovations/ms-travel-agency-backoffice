package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
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
@Document(collection = "crm_cost_tours")
public class CostTourEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer numMaxPax;

    private Double subTotalAmount;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity itineraryDate;

    private @DocumentReference(lazy = true)
    QuotationEntity quotation;

    private @DocumentReference(lazy = true)
    TourEntity tour;

}
