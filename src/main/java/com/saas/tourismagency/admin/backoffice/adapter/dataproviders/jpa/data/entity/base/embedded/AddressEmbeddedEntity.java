package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressEmbeddedEntity implements Serializable {

    @Id
    protected String id;

    private String name;
    private String description;

    private String city;
    private String zip;
    private String street;
    private String street2;
    private String address1;
    private String address2;
    private String address3;
    private String county;
    private String town;

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
