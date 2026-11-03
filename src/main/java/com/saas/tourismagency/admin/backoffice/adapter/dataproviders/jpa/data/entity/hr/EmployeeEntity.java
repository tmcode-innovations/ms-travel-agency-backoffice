package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.AreaEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PartnerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PersonEntity;
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
@Document(collection = "hr_employees")
public class EmployeeEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private @DocumentReference(lazy = true)
    PersonEntity person;
    private @DocumentReference(lazy = true)
    AreaEntity area;
    private @DocumentReference(lazy = true)
    PositionEntity position;
    private @DocumentReference(lazy = true)
    PartnerEntity partner;

    private String urlFileImageSign;
    private String fileNameImageSign;
}
