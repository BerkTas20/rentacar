package com.berktas.rentacar.model.entity.embedded;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Lob;

@Embeddable
@Getter
@Setter
public class Address {
    @ColumnDefault("''")
    private String country;

    @ColumnDefault("''")
    private String province;

    @ColumnDefault("''")
    private String district;

    @ColumnDefault("''")
    private String neighborhood;

    @ColumnDefault("''")
    private String street;

    @ColumnDefault("''")
    private String buildingInformation;

    @ColumnDefault("''")
    private String fullAddress;


    @Embedded
    private Position position;
}
