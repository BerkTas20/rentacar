package com.berktas.rentacar.service.osrm;

import java.util.List;

public class MatchResponse {
    public String code;
    public List<Tracepoint> tracepoints;
    public List<Matching> matchings;

    public static class Tracepoint {
        public Integer alternatives_count;
        public List<Double> location;
        public Double distance;
        public String hint;
        public String name;
        public Integer matchings_index;
        public Integer waypoint_index;
    }

    public static class Leg {
        public List<Object> steps;
        public Double weight;
        public Double distance;
        public String summary;
        public Double duration;
    }

    public static class Geometry {
        public List<List<Double>> coordinates;
        public String type;
    }

    public static class Matching {
        public Double duration;
        public Double distance;
        public Double weight;
        public Geometry geometry;
        public Double confidence;
        public String weight_name;
        public List<Leg> legs;
    }
}
