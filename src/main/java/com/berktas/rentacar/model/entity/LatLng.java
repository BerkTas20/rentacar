package com.berktas.rentacar.model.entity;

import com.google.maps.model.SnappedPoint;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.Point;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Data
@Embeddable
public class LatLng implements Serializable {

    private Double lat = 0.0;
    private Double lng = 0.0;

    public LatLng() {

    }

    public LatLng(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public static LatLng from(SnappedPoint point) {
        LatLng latLng = new LatLng();
        latLng.lat = point.location.lat;
        latLng.lng = point.location.lng;
        return latLng;
    }

    public com.google.maps.model.LatLng toGoogleLatLng() {
        return new com.google.maps.model.LatLng(lat, lng);
    }

    public Point toGeoCalcPoint() {
        return Point.at(Coordinate.fromDegrees(lat), Coordinate.fromDegrees(lng));
    }
}
