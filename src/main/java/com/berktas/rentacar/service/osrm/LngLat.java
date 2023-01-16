package com.berktas.rentacar.service.osrm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LngLat {

    private Double lng;
    private Double lat;

    String getLngLat(){
        return ";"+lng+","+lat;
    }
}
