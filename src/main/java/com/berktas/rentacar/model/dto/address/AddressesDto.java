package com.berktas.rentacar.model.dto.address;

import com.berktas.rentacar.model.entity.adresses.District;
import com.berktas.rentacar.model.entity.adresses.Neighborhood;
import com.berktas.rentacar.model.entity.adresses.Province;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressesDto {

    private List<Province> province;

    private List<District> district;

    private List<Neighborhood> neighborhood;

}
