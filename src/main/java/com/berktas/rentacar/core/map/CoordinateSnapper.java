package com.berktas.rentacar.core.map;


import com.berktas.rentacar.core.exception.PositionErrorException;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.model.entity.CarLog;
import com.berktas.rentacar.model.entity.LastLog;
import com.berktas.rentacar.model.entity.LatLng;
import com.berktas.rentacar.service.osrm.OsrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CoordinateSnapper {

    @Autowired
    OsrmService osrmService;

    Logger logger = LoggerFactory.getLogger(CoordinateSnapper.class);


    public CarLog snap(CarLog log) throws PositionErrorException {
        //return log;
        Car car = log.getCar();

        //if lastlog null nothing to do
        if (car.getLastLog() == null || car.getLastLog().getId() == null) {
            return log;
        }

        LastLog oldLog = car.getLastLog();
        Double distanceM = DistanceCalculator.calculateDistance(log);

        boolean snapToOld = false;
        //if ignition true and distance < 15 meters --> snap to old
        snapToOld |= log.getIgnition() && Math.abs(distanceM) < 15;
        // if ignition false and distance < 200 meters --> snap to old (oldValue 350)
        snapToOld |= !log.getIgnition() && Math.abs(distanceM) < 200;

        //if ignition different, dont care
        if(!(oldLog.getIgnition().equals(log.getIgnition()))){
            snapToOld = false;
        }

        if (snapToOld) {
            log.setLatLng(oldLog.getLatLng());
            log.setCourse(oldLog.getCourse());
            log.setMsl(oldLog.getMsl());
            log.setAddress(oldLog.getAddress());
            log.setSpeedKmh(0.00);

        } else {

            //Duration
            LocalDateTime oldDt = car.getLastLog().getMqttLogDateTime();
            LocalDateTime newDt = log.getMqttLogDateTime();
            Long durationS = Duration.between(oldDt, newDt).abs().getSeconds();

            //Speed calc
            Double cmps = Math.abs(distanceM) / Math.abs(durationS);
            //180kmh
            if (cmps > 50) {
                throw new PositionErrorException("PositionError");
            }

            //average speed
            float mps = (float) ((oldLog.getSpeedKmh() + log.getSpeedKmh()) / 2);

            //Snap To Road with OsrmService
            LatLng matchedLatLng = osrmService.match(car.getLastLog().getLatLng(),log.getLatLng());
            if(matchedLatLng != null){
                logger.trace("Osrm Service OK",matchedLatLng);

                //match only if distance short
                Double distMatched = DistanceCalculator.calculateDistance(matchedLatLng,log.getLatLng());
                if(Math.abs(distMatched) < 30){
                    log.setLatLng(matchedLatLng);
                }

            }else{
                logger.info("Osrm Service returned null");
            }

            //kalman calc
            /*
            KalmanLatLong kalmanLatLong = new KalmanLatLong(mps);

            Timestamp oldTs = Timestamp.valueOf(oldDt);
            kalmanLatLong.SetState(oldLog.getLatLng().getLat(), oldLog.getLatLng().getLng(), oldLog.getPdop(), oldTs.getTime());

            Timestamp newTs = Timestamp.valueOf(log.getMqttLogDateTime());
            kalmanLatLong.Process(log.getLatLng().getLat(), log.getLatLng().getLng(), log.getPdop(), newTs.getTime());

            LatLng latLng = new LatLng(kalmanLatLong.get_lat(), kalmanLatLong.get_lng());
            log.setLatLng(latLng);*/

        }
        return log;
    }

}
