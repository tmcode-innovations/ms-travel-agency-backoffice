package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "base_change_request_comments")
public class ChangeRequestCommentEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private LocalDateTime dateTime;

    private @DocumentReference(lazy = true)
    EmployeeEntity employee;
    private @DocumentReference(lazy = true)
    UserEntity user;
    private @DocumentReference(lazy = true)
    ChangeRequestEntity changeRequest;

}
