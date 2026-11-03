package com.saas.tourismagency.admin.backoffice.usecase.core;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.errorlogger.ErrorLoggerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.errorlogger.ErrorLoggerSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.errorlogger.ErrorLoggerSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.errorlogger.ErrorLoggerSearchResponse;
import dev.mm.javaee.core.exceptions.ApiException;
import dev.mm.javaee.core.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

public interface ErrorLoggerUseCase {

    public enum ErrorLoggerImportanceEnum {
        INFO,
        WARNING,
        VALIDATION,
        ERROR,
        CRITICAL;

        @Override
        public String toString() {
            return name().toLowerCase();
        }

        public boolean equals(String statusName) {
            return this.toString().equalsIgnoreCase(statusName);
        }
    }

    public List<ErrorLoggerResponse> findAll();

    public ErrorLoggerResponse findById(String id);

    public ErrorLoggerSearchResponse searchAll(ErrorLoggerSearchRequest errorLoggerSearchRequest);

    public ErrorLoggerSearchResponse searchFilterAll(ErrorLoggerSearchFilterRequest errorLoggerSearchFilterRequest);



    public void log(ValidationException exception, HttpStatus status);

    public void log(ApiException exception, HttpStatus status);

    public void log(HttpClientErrorException exception, HttpStatus status);

    public void log(Exception exception, HttpStatus status);
}
