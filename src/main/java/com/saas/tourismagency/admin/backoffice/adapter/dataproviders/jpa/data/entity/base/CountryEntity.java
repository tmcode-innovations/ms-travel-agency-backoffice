package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "base_countries")
public class CountryEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;

    private String identify;
    private String countryIso;
    private String identifyAlfa3;
    private String identifyNumeric;

}
