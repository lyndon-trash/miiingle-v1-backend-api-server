package net.miiingle.api.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorController {

    @AllArgsConstructor
    @Data
    static class APIError {
        Code error;
        String errorDescription;

        enum Code {
            GENERIC
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected APIError handle(Exception e) {
        log.error("Unhandled Exception", e);
        return new APIError(APIError.Code.GENERIC, "Unhandled Error: " + e.getClass());
    }
}
