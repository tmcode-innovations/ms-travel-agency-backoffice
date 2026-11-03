package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "maintenance_detractions")
public class DetractionEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;

    private Boolean available;
    private Boolean valueDefault;
    private Double percent;
    private Double amount;
    private LocalDate date;
    private String observation;

}
