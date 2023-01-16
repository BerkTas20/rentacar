package com.berktas.rentacar.core.map;



import com.berktas.rentacar.model.entity.CarLog;
import com.berktas.rentacar.model.entity.LatLng;
import com.berktas.rentacar.model.entity.embedded.Address;
import com.berktas.rentacar.service.nominatim.NominatimService;
import com.berktas.rentacar.service.osrm.OsrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressSnapper {

    private final NominatimService nominatimService;
    private final OsrmService osrmService;

    @Autowired
    public AddressSnapper(NominatimService nominatimService,
                          OsrmService osrmService) {
        this.nominatimService = nominatimService;
        this.osrmService = osrmService;
    }

    public CarLog snap(CarLog log) {

        LatLng oldLatLng;

        if (log.getCar().getLastLog() == null || log.getCar().getLastLog().getId() == null) {
            oldLatLng = log.getLatLng();
        } else {
            oldLatLng = log.getCar().getLastLog().getLatLng();
        }

        LatLng latLng = log.getLatLng();

        LatLng snapped = osrmService.match(oldLatLng, latLng);
        Address address = null;

        if (snapped != null) {
            address = snap(snapped);
        } else {
            address = snap(latLng);
        }

        if (address != null) {
            log.setAddress(address);
            return log;
        }

        return log;
    }

    private Address snap(LatLng latLng) {
        Address address = null;

        //try nominatim
        address = nominatimService.reverse(latLng);

        return address;
    }


}
