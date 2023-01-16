package com.berktas.rentacar.model.entity;

import com.berktas.rentacar.model.entity.base.AbstractTimestampEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import java.sql.Blob;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
public class CarImage extends AbstractTimestampEntity {
    private String name;
    private String type;

    public CarImage(String name, String type, byte[] data, Car car) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.car = car;
    }


    public CarImage(String fileName, String contentType, byte[] bytes) {
    }

    @Lob
    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY)
    private Car car;
}
