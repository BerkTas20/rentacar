package com.berktas.rentacar.core.exception.handler;

import com.berktas.rentacar.core.exception.base.BaseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<ErrorResponse> errorResponseList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().stream().forEach((error) -> {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setField(((org.springframework.validation.FieldError) error).getField());
            errorResponse.setMessage(error.getDefaultMessage());
            errorResponseList.add(errorResponse);

        });
        return new ResponseEntity<>(errorResponseList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> baseException(BaseException e) {
        List<ErrorResponse> errorResponsesList = new ArrayList<>();
        ErrorResponse errorResponses = new ErrorResponse();
        errorResponses.setMessage(e.getMessage());
        errorResponses.setField(e.getClass().getSimpleName());
        errorResponsesList.add(errorResponses);
        return new ResponseEntity<>(errorResponsesList, e.getHttpStatus());
    }
}
