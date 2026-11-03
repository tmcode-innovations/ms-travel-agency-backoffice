package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr;

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
@Document(collection = "hr_positions")
public class PositionEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

}
