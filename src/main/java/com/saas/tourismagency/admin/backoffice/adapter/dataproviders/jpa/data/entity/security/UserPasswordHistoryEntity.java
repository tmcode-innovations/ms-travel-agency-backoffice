package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "security_user_password_histories")
public class UserPasswordHistoryEntity extends BaseEntity implements Serializable {

    private String salt;
    private String passwordHash;

    private @DocumentReference(lazy = true)
    UserEntity user;
}
