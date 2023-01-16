package com.berktas.rentacar.rest;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.springframework.http.HttpStatus;

public class ResponseSpec {

    private ResponseSpec(){

    }

    public static ResponseSpecification isJsonResponse(){
        return new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
    }

    public static ResponseSpecification isOkResponse() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.OK.value()).build();
    }

    public static ResponseSpecification isUnauthorizedResponse() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.UNAUTHORIZED.value()).build();
    }

    public static ResponseSpecification isForbiddenResponse() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.FORBIDDEN.value()).build();
    }

    public static ResponseSpecification isBadRequestResponse() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.BAD_REQUEST.value()).build();
    }

    public static ResponseSpecification isNotFoundResponse() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.NOT_FOUND.value()).build();
    }
}
