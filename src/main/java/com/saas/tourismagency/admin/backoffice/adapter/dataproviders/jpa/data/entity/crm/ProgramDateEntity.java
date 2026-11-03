package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "crm_program_dates")
public class ProgramDateEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer day;

    private @DocumentReference(lazy = true)
    ProgramEntity program;

    private @DocumentReference(lazy = true)
    Set<TourEntity> tours;
}
