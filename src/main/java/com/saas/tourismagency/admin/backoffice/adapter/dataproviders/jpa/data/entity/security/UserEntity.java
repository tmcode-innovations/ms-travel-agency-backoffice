package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security;

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
@Document(collection = "security_users")
public class UserEntity extends BaseEntity implements Serializable {

    private String userName;
    private String email;
    private String fullName;
    private String description;

    private @DocumentReference(lazy = true)
    Set<ProfileEntity> profiles;

    private @DocumentReference(lazy = true)
    Set<AppEntity> apps;
}
