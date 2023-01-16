package com.berktas.rentacar.model.entity.embedded;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Position {

    private Double latitude;
    private Double longitude;
}
