package com.saas.tourismagency.admin.backoffice.domain.base.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactInformationEmbeddedDomain implements Serializable {

    private String id;
    private String name;
    private String description;

    private String email;
    private String phone;
    private String mobile;
    private String fax;

}
