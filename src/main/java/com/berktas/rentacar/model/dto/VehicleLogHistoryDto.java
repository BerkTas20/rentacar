package com.berktas.rentacar.model.dto;


import com.berktas.rentacar.core.util.tracing.MetreToKm;
import com.berktas.rentacar.model.dto.base.BaseDto;
import com.berktas.rentacar.model.entity.LatLng;
import com.berktas.rentacar.model.entity.embedded.Address;
import com.berktas.rentacar.model.enums.CarIgnitionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class VehicleLogHistoryDto extends BaseDto {

    private OnlyCarInformationDto vehicle;

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

    private long currentTotalDistance;

    @JsonProperty(value = "currentTotalDistanceKm")
    private double getCurrentTotalDistanceKm(){
        return MetreToKm.convert(currentTotalDistance,3);
    }

    private Address address;

    @JsonProperty
    public CarIgnitionStatus status() {
        if (!ignition) {
            return CarIgnitionStatus.STOPPED;
        }
        if (speedKmh < 10) {
            return CarIgnitionStatus.STARTED;
        }
        return CarIgnitionStatus.MOVING;
    }

}
