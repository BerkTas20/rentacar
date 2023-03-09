package com.berktas.rentacar.core.mapservice.osrm;

import java.util.List;

public class RouteResponse {
    public String code;
    public List<Waypoint> waypoints;
    public List<Route> routes;

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

    public static class Geometry {
        public List<List<Double>> coordinates;
        public String type;
    }

    public static class Route {
        public List<Leg> legs;
        public String weight_name;
        public Geometry geometry;
        public Integer weight;
        public Double distance;
        public Integer duration;
    }
}
