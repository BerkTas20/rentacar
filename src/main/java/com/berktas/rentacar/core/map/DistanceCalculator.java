package com.berktas.rentacar.core.map;

import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.model.entity.CarLog;
import com.berktas.rentacar.model.entity.LatLng;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;

public class DistanceCalculator {

    public static Double calculateDistance(CarLog log) {
        if (log.getCar().getLastLog() == null || log.getCar().getLastLog().getId() == null) {
            return 0.0;
        }

        Point oldP = log.getCar().getLastLog().getLatLng().toGeoCalcPoint();
        Point newP = log.getLatLng().toGeoCalcPoint();

        //TODO BEST METHOD haversine?
        //return EarthCalc.gcd.distance(oldP, newP);
        return EarthCalc.haversine.distance(oldP, newP);
    }

    public static Double calculateDistance(LatLng p1, LatLng p2){
        Point oldP = p1.toGeoCalcPoint();
        Point newP = p2.toGeoCalcPoint();

        //TODO BEST METHOD haversine?
        //return EarthCalc.gcd.distance(oldP, newP);
        return EarthCalc.haversine.distance(oldP, newP);
    }

    public static Car addDistance(Car car, Long distanceM) {
        car.setTotalDistance(car.getTotalDistance() + distanceM);
        return car;
    }

    public static Car setDistance(CarLog log) {
        Long distance = calculateDistance(log).longValue() + 1;
        return addDistance(log.getCar(), distance);
    }

    public static long calculateDistanceLong(CarLog log) {
        return calculateDistance(log).longValue() + 1;
    }

}
