package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.*;
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
@Document(collection = "maintenance_tourist_destines")
public class TouristDestineEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private @DocumentReference(lazy = true)
    CountryEntity country;
    private @DocumentReference(lazy = true)
    DepartmentRegionEntity departmentRegion;
    private @DocumentReference(lazy = true)
    ProvinceEntity province;
    private @DocumentReference(lazy = true)
    DistrictEntity district;
    private @DocumentReference(lazy = true)
    StateEntity state;
}
