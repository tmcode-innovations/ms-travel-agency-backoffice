package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("dateBirthday")
    private LocalDate dateBirthday;

    @JsonProperty("identifyPerson")
    private String identifyPerson;

    @JsonProperty("gender")
    private String gender;

    public PersonDomain to() {
        PersonDomain personDomain = new PersonDomain();
        personDomain.setName(name);
        personDomain.setDescription(description);
        personDomain.setIdentify(identify);
        personDomain.setFirstName(firstName);
        personDomain.setLastName(lastName);
        personDomain.setAge(age);
        personDomain.setDateBirthday(dateBirthday);
        personDomain.setIdentifyPerson(identifyPerson);
        personDomain.setGender(gender);
        return personDomain;
    }
}
