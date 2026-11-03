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
@Document(collection = "core_error_loggers")
public class ErrorLoggerEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;

    private String clazz;
    private String stackTrace;
    private String importance;
    private String message;
    private String statusCode;
    private String extraData;

}
