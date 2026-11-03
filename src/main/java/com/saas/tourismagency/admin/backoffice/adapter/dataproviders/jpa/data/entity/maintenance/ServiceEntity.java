package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "maintenance_services")
public class ServiceEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String abbrev;
    private String observation;
    private Boolean noApplyRate;
    private Integer day;
    private Boolean withoutCost;
    private Boolean typeTour;
    private Double cost;

    private String serviceType;

    private @DocumentReference(lazy = true)
    TouristDestineEntity touristDestine;
    private @DocumentReference(lazy = true)
    ServiceCategoryEntity serviceCategory;

}
