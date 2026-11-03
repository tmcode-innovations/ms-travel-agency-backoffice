package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "core_persist_consecutive_numbers")
public class PersistConsecutiveNumberEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;

    private String objective;
    private Integer number;
    private Integer year;
    private Integer month;
    private Integer day;

}
