package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactInformationEmbeddedEntity implements Serializable {

    @Id
    protected String id;

    private String name;
    private String description;

    private String email;
    private String phone;
    private String mobile;
    private String fax;
}
