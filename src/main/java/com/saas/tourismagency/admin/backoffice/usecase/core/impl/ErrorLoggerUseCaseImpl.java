package com.saas.tourismagency.admin.backoffice.usecase.core.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.core.errorlogger.ErrorLoggerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.core.SetFieldDefaultErrorLogger;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ErrorLoggerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.errorlogger.ErrorLoggerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.errorlogger.ErrorLoggerSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.errorlogger.ErrorLoggerSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.errorlogger.ErrorLoggerSearchResponse;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.core.ErrorLoggerDomain;
import com.saas.tourismagency.admin.backoffice.usecase.core.ErrorLoggerUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.ApiException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.exceptions.ValidationException;
import dev.mm.javaee.core.utils.jackson.JACKSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ErrorLoggerUseCaseImpl implements ErrorLoggerUseCase {

    @Autowired
    private ErrorLoggerRepo repo;

    @Autowired
    private ErrorLoggerConverter converter;

    @Autowired
    private SetFieldDefaultErrorLogger setFieldDefault;

    @Override
    @Transactional(readOnly = true)
    public List<ErrorLoggerResponse> findAll() {
        return repo.findAll(false).stream().map(errorLoggerDomain -> ErrorLoggerResponse.from(errorLoggerDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ErrorLoggerResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ErrorLoggerDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No company found with id '%s'.".formatted(id));
        }
        return ErrorLoggerResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ErrorLoggerSearchResponse searchAll(ErrorLoggerSearchRequest errorLoggerSearchRequest) {
       PageDTO<ErrorLoggerDomain> pageDTO = repo.searchAll(errorLoggerSearchRequest);
       ErrorLoggerSearchResponse errorLoggerSearchRespose = new ErrorLoggerSearchResponse();
       errorLoggerSearchRespose.setErrorLoggers(pageDTO.getContent().stream().map(errorLoggerDomain ->  ErrorLoggerResponse.from(errorLoggerDomain)).toList());
       errorLoggerSearchRespose.setTotalElements(pageDTO.getTotalElements());
       errorLoggerSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return errorLoggerSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ErrorLoggerSearchResponse searchFilterAll(ErrorLoggerSearchFilterRequest errorLoggerSearchFilterRequest) {
        PageDTO<ErrorLoggerDomain> pageDTO = repo.searchAllFilter(errorLoggerSearchFilterRequest);
        ErrorLoggerSearchResponse errorLoggerSearchRespose = new ErrorLoggerSearchResponse();
        errorLoggerSearchRespose.setErrorLoggers(pageDTO.getContent().stream().map(errorLoggerDomain ->  ErrorLoggerResponse.from(errorLoggerDomain)).toList());
        errorLoggerSearchRespose.setTotalElements(pageDTO.getTotalElements());
        errorLoggerSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return errorLoggerSearchRespose;
    }

    @Override
    @Transactional
    public void log(ValidationException exception, HttpStatus status) {
        logGeneral(
                exception,
                status,
                ValidationException.class.toString(),
                ErrorLoggerImportanceEnum.VALIDATION,
                "Error validating entity",
                exception.getMessages()
        );
    }

    @Override
    @Transactional
    public void log(ApiException exception, HttpStatus status) {
        logGeneral(
                exception,
                status,
                ApiException.class.toString(),
                ErrorLoggerImportanceEnum.ERROR,
                exception.getMessage(),
                "no extra data provided"
        );
    }

    @Override
    @Transactional
    public void log(HttpClientErrorException exception, HttpStatus status) {
        logGeneral(
                exception,
                status,
                HttpClientErrorException.class.toString(),
                ErrorLoggerImportanceEnum.ERROR,
                exception.getResponseBodyAsString(),
                "no extra data provided"
        );
    }

    @Override
    @Transactional
    public void log(Exception exception, HttpStatus status) {
        exception.printStackTrace();

        logGeneral(
                exception,
                status,
                Exception.class.toString(),
                ErrorLoggerImportanceEnum.CRITICAL,
                exception.getMessage(),
                "no extra data provided"
        );
    }

    private void logGeneral(Exception exception, HttpStatus status, String clazz, ErrorLoggerImportanceEnum importance, String message, Object extraDataObject) {
        try {
            String stackTrace = Arrays.toString(
                    exception.getStackTrace()
            );
            stackTrace = stackTrace.substring(
                    0,
                    Math.min(
                            stackTrace.length(),
                            16777215
                    )
            );
            message = message.substring(
                    0,
                    Math.min(
                            message.length(),
                            65535
                    )
            );
            String username = "unknown username";
            String extraData = "unknown extra data";

            try {
                extraData = JACKSON.toString(extraDataObject);
                extraData = extraData.substring(
                        0,
                        Math.min(
                                extraData.length(),
                                65535
                        )
                );
            } catch (Exception e) {
            }

            ErrorLoggerDomain domain = new ErrorLoggerDomain(
                    "",
                    "",
                    clazz,
                    stackTrace,
                    importance.toString(),
                    message,
                    status.toString(),
                    extraData
            );
            domain.setTenant(UserResolver.resolveUser().getTenant());

            log.error("Logeando excepcion con msg: %s".formatted(message));

            repo.create(domain);
        } catch (Exception e) {
            log.error("Error logeando 'Exception': %s".formatted(e.getMessage()));
        }
    }
}
