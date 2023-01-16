package com.berktas.rentacar.model.entity;

import com.berktas.rentacar.model.entity.embedded.Address;
import com.berktas.rentacar.model.enums.CarIgnitionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Embeddable
@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class LastLog implements Serializable {

    private Long id;

    @Type(type = "json")
    @Column(columnDefinition = "json")
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

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Address address;

    private Boolean isMaxSpeedAlarm;

    private Boolean isIdleAlarm;

    private Boolean isSafeAreaAlarm;

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
