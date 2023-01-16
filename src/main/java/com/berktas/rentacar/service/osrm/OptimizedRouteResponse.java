package com.berktas.rentacar.service.osrm;

import lombok.Data;

import java.util.List;

@Data
public class OptimizedRouteResponse {
    String code;
    List<Trip> trips;
    List<Waypoint> waypoints;

    public static class Trip {
        public List<Leg> legs;
        public String weight_name;
        public Geometry geometry;
        public Integer weight;
        public Double distance;
        public Integer duration;
    }

    public static class Geometry {
        public List<List<Double>> coordinates;
        public String type;
    }

    public static class Waypoint {
        public String hint;
        public Double distance;
        public List<Double> location;
        public String name;
    }
    public static class Leg {
        public List<Object> steps;
        public Integer weight;
        public Double distance;
        public String summary;
        public Integer duration;
    }
}
