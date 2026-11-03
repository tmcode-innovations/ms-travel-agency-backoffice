package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base;

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
@Document(collection = "base_persons")
public class PersonEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;

    private String identify;

    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDate dateBirthday;
    private String identifyPerson;

    private String gender;
}
