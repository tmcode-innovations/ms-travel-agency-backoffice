package com.saas.tourismagency.admin.backoffice.domain.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleBaseDomain implements Serializable {

    private String id;
    private String name;
    private String identify;
    private String description;
    private Integer index;
}
