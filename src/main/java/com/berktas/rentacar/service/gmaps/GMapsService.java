package com.berktas.rentacar.service.gmaps;

import com.berktas.rentacar.model.dto.address.AddressDto;
import com.berktas.rentacar.model.dto.address.PositionDto;
import com.berktas.rentacar.model.entity.embedded.Address;

public interface GMapsService {

    PositionDto getPosition(String str);

    PositionDto geocodeFromAddress(AddressDto address);

    Address reverseGeocode(PositionDto position);


}
