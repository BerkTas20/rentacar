package com.berktas.rentacar.service.nominatim;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MapSearchResponse {

    public String type;
    public String licence;
    public List<Feature> features;


    public static class Feature {
        public String type;
        public Properties properties;
        public List<Double> bbox;
        public Geometry geometry;
    }


    public static class Properties {
        public Long place_id;
        public String osm_type;
        public Long osm_id;
        public String display_name;
        public Integer place_rank;
        public String category;
        public String type;
        public Double importance;

    }

    public static class Geometry {
        public String type;
        public List<List<List<Double>>> coordinates;

    }
}
