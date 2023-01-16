package com.berktas.rentacar.core.exception.handler;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private String field;
}
