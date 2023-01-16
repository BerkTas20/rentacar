package com.berktas.rentacar.model.entity.adresses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ilceler")
@NoArgsConstructor
@AllArgsConstructor
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ilce_id")
    private Long id;

    @Column(name = "ilce_adi")
    private String districtName;

    @Column(name = "il_id")
    private Long provinceId;

    @Column(name = "il_adi")
    private String provinceName;

}
