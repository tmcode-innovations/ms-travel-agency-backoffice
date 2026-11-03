package com.saas.tourismagency.admin.backoffice.domain.statistics;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupedDataDomain implements Serializable {

    private String id;
    private Integer count;

}
