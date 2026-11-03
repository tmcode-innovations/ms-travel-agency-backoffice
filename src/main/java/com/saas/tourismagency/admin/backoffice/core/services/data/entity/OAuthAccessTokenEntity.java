package com.saas.tourismagency.admin.backoffice.core.services.data.entity;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "security_oauth_access_token")
public class OAuthAccessTokenEntity implements Serializable {

    @Id
    protected String id;

    private byte[] token;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;

    private @DocumentReference(lazy = true)
    UserEntity user;

}
