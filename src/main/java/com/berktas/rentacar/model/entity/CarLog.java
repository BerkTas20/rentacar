package com.berktas.rentacar.model.entity;


import com.berktas.rentacar.core.util.queque.LocationEvent;
import com.berktas.rentacar.model.entity.base.BaseEntity;
import com.berktas.rentacar.model.entity.embedded.Address;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_Car_dt", columnNames = {"car_id", "mqttLogDateTime"})
})
public class CarLog extends BaseEntity {

    @ManyToOne
    @NotNull
    private Car car;

    @Embedded
    private LatLng latLng;

    private LocalDateTime mqttLogDateTime;


    private Integer packageNo;

    private Boolean ignition;

    private Boolean immobilizer;

    private Double msl;

    private Double speedKmh;

    private Double course;

    private Double pdop;

    private Integer gnssInView;

    private Integer gnssUsed;

    @ColumnDefault("0")
    private long currentTotalDistance;  // odometre aracın total kilometresi.

    @ColumnDefault("0")
    private long distanceSinceIgnition; // Kontak açıldığından beri kaç km yapmış

    @ColumnDefault("0")
    private long dailyDistance; // Kontak açıldığından beri kaç km yapmış

    @Embedded
    private Address address;


    public static CarLog from(LocationEvent event, Car car) {
        CarLog log = new CarLog();
        log.car = car;
        log.latLng = event.getLatLng();
        log.mqttLogDateTime = event.getDt();
        log.packageNo = event.getPackageNo();
        log.ignition = event.getIgnition();
        log.immobilizer = event.getLock();
        log.msl = event.getMsl();
        log.speedKmh = event.getSpeed();
        log.course = event.getCourse();
        log.pdop = event.getPdop();
        log.gnssInView = event.getGnssInView();
        log.gnssUsed = event.getGnssUsed();
        return log;
    }


    public void updateDistanceSinceIgnitionFromVehicle(Car car) {
        this.distanceSinceIgnition = car.getDistanceSinceIgnition();
    }

    public void updateDailyDistanceFromVehicle(Car car) {
        this.dailyDistance = car.getDailyDistance();
    }

    public static Boolean catchTheChangeInIgnitionState(CarLog newLog) {

        Car car = newLog.getCar();
        LastLog lastLog = car.getLastLog();

        if (lastLog == null) {
            return false;
        } else if (lastLog.getIgnition() == null) {
            return false;
        }

        return !lastLog.getIgnition().equals(newLog.getIgnition());

    }
}
