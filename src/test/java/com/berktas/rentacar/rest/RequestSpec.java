package com.berktas.rentacar.rest;


import com.berktas.rentacar.core.security.CustomUserDetails;
import com.berktas.rentacar.core.security.JwtTokenUtil;
import com.berktas.rentacar.model.entity.user.User;
import io.restassured.RestAssured;
import io.restassured.config.FailureConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {
    public final static String BASE_URI = "http://localhost/api/v1";

    private final RequestSpecification request;

    private RequestSpec(){
        //RestAssured.config().failureConfig(new FailureConfig());
        request = RestAssured.given();
    }
    public static RequestSpec given(){
        return new RequestSpec();
    }
    public RequestSpecification request(){
        return request;
    }

    public RequestSpecification jsonRequest(){
        request.contentType(ContentType.JSON);
        request.accept(ContentType.JSON);
        return request;
    }

    public RequestSpec authenticated(User user){
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String token = jwtTokenUtil.generate(customUserDetails,false);
        return authenticated(token);
    }


    public RequestSpec authenticated(String token) {
        request.header("Authorization", "Bearer " + token);
        return this;
    }

    private RequestSpec body(String token) {
        request.header("Authorization", "Bearer " + token);
        return this;
    }

    public RequestSpec acceptJson() {
        request.accept(ContentType.JSON);
        return this;
    }
}
