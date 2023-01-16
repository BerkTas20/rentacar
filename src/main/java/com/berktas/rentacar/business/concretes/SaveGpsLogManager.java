package com.berktas.rentacar.business.concretes;

import com.berktas.rentacar.business.abstracts.CarLogService;
import com.berktas.rentacar.business.abstracts.SaveGpsLogService;
import com.berktas.rentacar.core.exception.PositionErrorException;
import com.berktas.rentacar.core.map.AddressSnapper;
import com.berktas.rentacar.core.map.CoordinateSnapper;
import com.berktas.rentacar.core.map.DistanceCalculator;
import com.berktas.rentacar.mapper.CarMapper;
import com.berktas.rentacar.model.dto.CarDto;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.model.entity.CarLog;
import com.berktas.rentacar.repository.CarLogRepository;
import com.berktas.rentacar.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SaveGpsLogManager implements SaveGpsLogService {

    Logger logger = LoggerFactory.getLogger(SaveGpsLogManager.class);

    private final CarLogRepository carLogRepository;
    private final CoordinateSnapper coordinateSnapper;
    private final AddressSnapper addressSnapper;
    private final SimpMessagingTemplate messagingTemplate;
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    private final CarLogService carLogService;

    @Override
    @Transactional
    public void saveGpsLog(CarLog newLog) {

        //try lock vehicle entity
        Car car = carRepository.lockById(newLog.getCar().getId()).get();
        newLog.setCar(car);

        //snap to Road
        try {
            newLog = coordinateSnapper.snap(newLog);
        } catch (PositionErrorException e) {
            logger.warn("Corrdinate could not be snapped: " + newLog.getLatLng().toString());
        }

        //snap Address
        newLog = addressSnapper.snap(newLog);

        //calc distance
        car = DistanceCalculator.setDistance(newLog);

        //set distance
        newLog.setCurrentTotalDistance(car.getTotalDistance());

        // calc distanceSinceIgnition
        calculateDistanceSinceIgnition(car, newLog);

        //calc dailyDistance
        calculateDailyDistance(car, newLog);

        //ignition Set
        if(car.getLastLog() != null){
            if(car.getLastLog().getIgnition() != newLog.getIgnition()){
                if(newLog.getIgnition()){
                    car.setLastIgnitionOnDt(newLog.getMqttLogDateTime());
                }else{
                    car.setLastIgnitionOffDt(newLog.getMqttLogDateTime());
                }
            }
        }

        //save new Log
        carLogRepository.save(newLog);

        //FirebaseMessaging
      //  vehicleLogService.sendNotificationWhenTheChangeInIgnitionState(newLog);

        //save Vehicle Entity
        car.setLastLog(car.vehicleLogToLastLog(newLog));
        carRepository.save(car);

        //vehicleLogService.saveToBursaBelediyeApiFacade(vehicle, newLog);

        //Push to Websocket
        CarDto vehicleDto = carMapper.entityToDto(car);
        messagingTemplate.convertAndSend("vehicle", vehicleDto);
    }

    public void calculateDistanceSinceIgnition(Car car, CarLog newLog) {
        //Sırası bozulursa patlar!!!
        car.calculateDistanceSinceIgnition(newLog, DistanceCalculator.calculateDistanceLong(newLog));
        newLog.updateDistanceSinceIgnitionFromVehicle(car);
    }

    public void calculateDailyDistance(Car car, CarLog newLog) {
        //Sırası bozulursa patlar!!!
        car.calculateDailyDistance(newLog, DistanceCalculator.calculateDistanceLong(newLog));
        newLog.updateDailyDistanceFromVehicle(car);
    }


}
