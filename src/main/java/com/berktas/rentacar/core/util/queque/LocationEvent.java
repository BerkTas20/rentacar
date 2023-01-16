package com.berktas.rentacar.core.util.queque;

import com.berktas.rentacar.model.entity.LatLng;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Getter
@Setter
public class LocationEvent {

    private Integer packageNo;
    private Boolean ignition;
    private Boolean lock;
    @Embedded
    private LatLng latLng;
    private LocalDateTime dt;
    private Double msl = 0.00;
    private Double speed = 0.00;
    private Double course = 0.00;
    private Double pdop = 1.00;
    private Integer gnssInView = 0;
    private Integer gnssUsed = 0;



    public static LocationEvent from(byte[] data) {

        LocationEvent log = new LocationEvent();

      //  HashMap<String, String> map = new HashMap<>();
        String str = new String(data);

        String[] pairStrArray = str.split(";");
        for (String pairStr : pairStrArray) {

            String[] pairArray = pairStr.split(":");
            if (pairArray.length != 2) {
                continue;
            }
            String name = pairArray[0];
            String value = pairArray[1];

            if ("fix".equals(name)) {
                int fix = Integer.parseInt(value);
                if (fix == 0) {
                    return null;
                }
            }

            if ("lock".equals(name)) {
                int lock = Integer.parseInt(value);
                log.lock = lock == 1;
            }

            if ("pno".equals(name)) {
                int pNo = Integer.parseInt(value);
                log.packageNo = pNo;
            }

            if ("ignition".equals(name)) {
                int ignition = Integer.parseInt(value);
                log.ignition = ignition == 1;
            }

            if ("datetime".equals(name)) {
                LocalDateTime localDateTime = LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                log.dt = localDateTime;
            }

            if ("lat".equals(name)) {
                if (log.latLng == null) {
                    log.latLng = new LatLng();
                }
                Double lat = Double.parseDouble(value);
                if (Math.abs(lat) > 90) {
                    return null;
                }
                if (lat == 0) {
                    return null;
                }
                log.latLng.setLat(lat);
            }

            if ("lng".equals(name)) {
                if (log.latLng == null) {
                    log.latLng = new LatLng();
                }
                Double lng = Double.parseDouble(value);
                if (Math.abs(lng) > 180) {
                    return null;
                }
                if (lng == 0) {
                    return null;
                }
                log.latLng.setLng(lng);
            }

            if ("msl".equals(name)) {
                try {
                    log.msl = Double.parseDouble(value);
                } catch (Exception e) {
                    log.msl = 0.0;
                }
            }

            if ("speed".equals(name)) {
                log.speed = Double.parseDouble(value);
                log.speed = 1.852 * log.speed;
            }

            if ("course".equals(name)) {
                log.course = Double.parseDouble(value);
            }

            if ("pdop".equals(name)) {
                log.pdop = Double.parseDouble(value);
            }

            if ("gnssinview".equals(name)) {
                log.gnssInView = Integer.parseInt(value);
            }

            if ("gnssused".equals(name)) {
                log.gnssUsed = Integer.parseInt(value);
            }

        }
        return log;
    }

    public static LocationEvent from(HashMap<String, String> data) {

        LocationEvent log = new LocationEvent();

        int fix = Integer.parseInt(data.get("fix"));
        if (fix == 0) {
            return null;
        }

        log.lock = Integer.parseInt(data.get("lock")) == 1;
        log.packageNo = Integer.parseInt(data.get("pno"));
        log.ignition = Integer.parseInt(data.get("ignition")) == 1;
        LocalDateTime localDateTime = LocalDateTime.parse(data.get("datetime"), DateTimeFormatter.ofPattern("yyyyMMddHHmmss[.SSS]"));
        log.dt = localDateTime;

        if (log.latLng == null) {
            log.latLng = new LatLng();
        }

        Double lat = Double.parseDouble(data.get("lat"));
        if (Math.abs(lat) > 90) {
            return null;
        }
        if (lat == 0) {
            return null;
        }
        log.latLng.setLat(lat);

        Double lng = Double.parseDouble(data.get("lng"));
        if (Math.abs(lng) > 180) {
            return null;
        }
        if (lng == 0) {
            return null;
        }
        log.latLng.setLng(lng);

        try {
            log.msl = Double.parseDouble(data.get("msl"));
        } catch (Exception e) {
            log.msl = 0.0;
        }

        String module = data.get("module");
        if ("gosunc".equals(module)) {
            log.speed = Double.parseDouble(data.get("speed"));
            log.speed = 1.852 * log.speed;
        } else {
            log.speed = Double.parseDouble(data.get("speed"));
            log.speed = 1.852 * log.speed;
        }

        log.course = Double.parseDouble(data.get("course"));
        log.pdop = Double.parseDouble(data.get("pdop"));
        log.gnssInView = Integer.parseInt(data.get("gnssinview"));
        log.gnssUsed = Integer.parseInt(data.get("gnssused"));

        return log;
    }

}
