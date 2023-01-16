package com.berktas.rentacar.model.entity;

import com.berktas.rentacar.controller.car.SaveCarRequest;
import com.berktas.rentacar.controller.car.UpdateCarRequest;
import com.berktas.rentacar.model.entity.base.AbstractTimestampEntity;
import com.berktas.rentacar.model.entity.embedded.Address;
import com.berktas.rentacar.model.enums.CarStatus;
import com.berktas.rentacar.model.enums.FuelType;
import com.berktas.rentacar.model.enums.RentStatus;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car extends AbstractTimestampEntity {

    @NotEmpty
    private String carName;

//    @Pattern(regexp = "(19|20)[0-9][0-9]")
    private Integer modelYear;

    private double dailyPrice;

    @Lob
    private String description;

    private LocalDateTime rentDate;
    private LocalDateTime returnDate;

//    @Pattern(regexp = "^[A-Z]")
    private String licencePlate;

    private String imei;
    @Enumerated(EnumType.STRING)
    public RentStatus rentStatus;

    @Enumerated(EnumType.STRING)
    private FuelType injectionSystemType;
    @Embedded
    private Address address;
    private Long maxSpeed;

    @Enumerated(value = EnumType.STRING)
    private FuelType fuelType;

    @ColumnDefault("0")
    @NotNull
    private long totalDistance = 0L;


    private long distanceSinceIgnition = 0L;

    private long dailyDistance = 0L;

    private String year;

    private Long maintenanceInterval;

    private Long maintenanceDistance;


    private Integer idleAlarmSecond = 60;


    private Integer idleTime = 0;

    @Embedded
    private LastLog lastLog;

    @Setter
    private LocalDateTime lastIgnitionOnDt;

    @Setter
    private LocalDateTime lastIgnitionOffDt;

    @ManyToOne
    private Brand brand;

    @Enumerated(EnumType.STRING)
    private CarStatus carStatus;
    @ManyToOne
    private Color color;

    public static Car create(SaveCarRequest saveCarRequest){
        Car car = new Car();
        car.setCarName(saveCarRequest.getCarName());
        car.setDescription(saveCarRequest.getDescription());
        car.setDailyPrice(saveCarRequest.getDailyPrice());
        car.setModelYear(saveCarRequest.getModelYear());
        car.setLicencePlate(saveCarRequest.getLicencePlate());
        car.setImei(saveCarRequest.getImei());
        car.setTotalDistance(saveCarRequest.getTotalDistance());
        return car;
    }

    public Car update(UpdateCarRequest updateCarRequest){
        setCarName(updateCarRequest.getCarName());
        setDescription(updateCarRequest.getDescription());
        setModelYear(updateCarRequest.getModelYear());
        setDailyPrice(updateCarRequest.getDailyPrice());
        setLicencePlate(updateCarRequest.getLicencePlate());
        setImei(updateCarRequest.getImei());
        return this;
    }
    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
    public LastLog vehicleLogToLastLog(CarLog carLog) {

        LastLog lastLog = new LastLog();
        lastLog.setId(carLog.getId());
        lastLog.setLatLng(carLog.getLatLng());
        lastLog.setMqttLogDateTime(carLog.getMqttLogDateTime());
        lastLog.setPackageNo(carLog.getPackageNo());
        lastLog.setIgnition(carLog.getIgnition());
        lastLog.setImmobilizer(carLog.getImmobilizer());
        lastLog.setMsl(carLog.getMsl());
        lastLog.setSpeedKmh(carLog.getSpeedKmh());
        lastLog.setCourse(carLog.getCourse());
        lastLog.setPdop(carLog.getPdop());
        lastLog.setGnssInView(carLog.getGnssInView());
        lastLog.setGnssUsed(carLog.getGnssUsed());
        lastLog.setAddress(carLog.getAddress());
        //lastLog.setTotalDistance(vehicleLog.getCurrentTotalDistance());
        //lastLog.setDistanceSinceIgnition(vehicleLog.getDistanceSinceIgnition());
        // lastLog.setDailyDistance(vehicleLog.getDailyDistance());

        return lastLog;
    }
    public void calculateDistanceSinceIgnition(CarLog newLog, long distance) {

        LastLog oldLog = newLog.getCar().getLastLog();
        if(oldLog == null){
            this.distanceSinceIgnition = 0;
            return;
        }

        if(oldLog.getIgnition() == Boolean.FALSE && newLog.getIgnition() == Boolean.TRUE){
            this.distanceSinceIgnition = 0;
        }else if(newLog.getIgnition() == Boolean.TRUE){
            this.distanceSinceIgnition = this.distanceSinceIgnition + distance;
        }

        /*
        if (newLog.getIgnition()) {
            this.distanceSinceIgnition = this.distanceSinceIgnition + distance;
        } else {
            this.distanceSinceIgnition = 0;
        }*/
    }
    public void calculateDailyDistance(CarLog newLog, long distance) {


        if (newLog.getCar().getLastLog() != null) {
            ZonedDateTime lastDt = newLog.getCar().getLastLog().getMqttLogDateTime().atZone(ZoneId.of("UTC+03:00"));
            ZonedDateTime newDt = newLog.getMqttLogDateTime().atZone(ZoneId.of("UTC+03:00"));

            //if another day reset daily
            if (newDt.getDayOfMonth() == lastDt.getDayOfMonth()) {
                this.dailyDistance = this.dailyDistance + distance;
            } else {
                this.dailyDistance = 0;
            }
        } else {
            this.dailyDistance = 0;
        }

    }
    public void updateVehicleStatus(RentStatus carStatus) {
        this.rentStatus = carStatus;
    }
    public Car updateImei(String imei) {
        this.imei = imei;
        return this;
    }
    public Car updateTotalDistance(Long totalDistance) {
        this.totalDistance = totalDistance;
        return this;
    }

}
