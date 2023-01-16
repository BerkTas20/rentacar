package com.berktas.rentacar.model.entity.adresses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "mahalleler")
@NoArgsConstructor
@AllArgsConstructor
public class Neighborhood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mahalle_id")
    private Long id;

    @Column(name = "mahalle_adi")
    private String neighborhoodName;

    @Column(name = "ilce_id")
    private Long districtId;

    @Column(name = "ilce_adi")
    private String districtName;

    @Column(name = "il_id")
    private Long provinceId;

    @Column(name = "il_adi")
    private String provinceName;

}
