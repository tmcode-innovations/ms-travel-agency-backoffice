package com.saas.tourismagency.admin.backoffice.core.exceptions;

import com.saas.tourismagency.admin.backoffice.usecase.core.ErrorLoggerUseCase;
import dev.mm.javaee.core.exceptions.ApiException;
import dev.mm.javaee.core.exceptions.ValidationException;
import dev.mm.javaee.core.rest.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ErrorLoggerUseCase errorLoggerUseCase;

    /**
     * @param validationExc
     * @param request
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value
            = {ValidationException.class})
    protected ResponseEntity<ApiResponse> handleValidationException(
            ValidationException validationExc,
            WebRequest request
    ) throws Exception {
        HttpStatus status = validationExc.getStatusCode();

        errorLoggerUseCase.log(validationExc, status);

        return new ResponseEntity(
                ApiResponse.build(
                        String.valueOf(status.value()),
                        "Error validating entity",
                        validationExc.getMessages()
                ),
                status
        );
    }


    /**
     * @param apiExc
     * @param request
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value
            = {ApiException.class})
    protected ResponseEntity<ApiResponse> handleApiException(
            ApiException apiExc,
            WebRequest request
    ) throws Exception {
        HttpStatus status = apiExc.status();

        errorLoggerUseCase.log(apiExc, status);

        return new ResponseEntity(
                ApiResponse.build(
                        String.valueOf(status.value()),
                        apiExc.getMessage(),
                        null
                ),
                status
        );
    }

    /**
     * @param ex
     * @param request
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value
            = {HttpClientErrorException.class})
    protected ResponseEntity<ApiResponse> handleHttpClientError(
            HttpClientErrorException ex,
            WebRequest request
    ) throws Exception {
        HttpStatus status = HttpStatus.valueOf(ex.getRawStatusCode());

        errorLoggerUseCase.log(ex, status);

        return new ResponseEntity(
                ApiResponse.build(
                        String.valueOf(status.value()),
                        ex.getResponseBodyAsString(),
                        null
                ),
                status
        );
    }

    /**
     * @param ex
     * @param request
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value
            = {Exception.class})
    protected ResponseEntity<ApiResponse> handleGeneric(
            Exception ex,
            WebRequest request
    ) throws Exception {
        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
            throw ex;
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

            errorLoggerUseCase.log(ex, status);

            return new ResponseEntity(
                    ApiResponse.build(
                            String.valueOf(status.value()),
                            ex.getMessage(),
                            null
                    ),
                    status
            );
        }
    }

}
