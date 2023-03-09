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

    private Integer modelYear;

    private double dailyPrice;

    @Lob
    private String description;

    private LocalDateTime rentDate;
    private LocalDateTime returnDate;

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

    public static Car create(SaveCarRequest saveCarRequest) {
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

    public Car update(UpdateCarRequest updateCarRequest) {
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
