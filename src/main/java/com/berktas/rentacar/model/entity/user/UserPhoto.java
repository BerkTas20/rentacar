package com.berktas.rentacar.model.entity.user;

import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.model.entity.base.AbstractTimestampEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@Data
@Entity
@Table(name = "userPhoto")
@NoArgsConstructor
public class UserPhoto extends AbstractTimestampEntity {

    private String name;

    private String type;

    public UserPhoto(String fileName, String contentType, byte[] bytes) {
    }

    @Lob
    private byte[] data;


    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}


