package com.berktas.rentacar.controller.map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapSearchRequest {
    private String country="";
    private String city="";
    private String county="";
    private String neighbourhood="";
}
