package com.berktas.rentacar.model.entity.adresses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sokaklar")
@NoArgsConstructor
@AllArgsConstructor
public class Sokaklar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sokak_id")
    private Long id;

    @Column(name = "sokak_adi")
    private String sokakAdi;

    @Column(name = "mahalle_id")
    private Long mahalleId;

    @Column(name = "mahalle_adi")
    private String mahalleAdi;

    @Column(name = "ilce_id")
    private Long ilceId;

    @Column(name = "ilce_adi")
    private String ilceAdi;

    @Column(name = "il_id")
    private Long ilId;

    @Column(name = "il_adi")
    private String ilAdi;

}
