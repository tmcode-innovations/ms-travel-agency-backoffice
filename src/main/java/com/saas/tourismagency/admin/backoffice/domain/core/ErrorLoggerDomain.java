package com.saas.tourismagency.admin.backoffice.domain.core;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ErrorLoggerDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;

    private String clazz;
    private String stackTrace;
    private String importance;
    private String message;
    private String statusCode;
    private String extraData;

}
