package dev.mm.javaee.core.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class UserBaseDomain extends BaseDomain implements Serializable {

    private String userName;
    private String email;
    private String fullName;

}
