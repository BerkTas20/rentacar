package com.berktas.rentacar.model.entity.adresses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "streets")
@NoArgsConstructor
@AllArgsConstructor
public class Streets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "street_id")
    private Long id;

    private String streetName;

    private Long neighbourhoodId;

    private String neighbourhoodName;

    private Long districtId;

    private String districtName;

    private Long provinceId;

    private String provinceName;

}
