package com.berktas.rentacar.service.gmaps;


import com.berktas.rentacar.core.exception.AddressNotFoundException;
import com.berktas.rentacar.model.dto.address.AddressDto;
import com.berktas.rentacar.model.dto.address.PositionDto;
import com.berktas.rentacar.model.entity.adresses.Neighborhood;
import com.berktas.rentacar.model.entity.embedded.Address;
import com.berktas.rentacar.repository.address.NeighborhoodRepository;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class GMapsServiceImpl implements GMapsService {

    private static final String APIKEY = "AIzaSyCEFnjpPxBARaB2s33qe8hdT6ahBB3HGRc";

    GeoApiContext context;

    @Autowired
    NeighborhoodRepository neighborhoodRepository;


    public GMapsServiceImpl() {
        this.context = new GeoApiContext.Builder()
                .apiKey(APIKEY)
                .build();
    }

    @Override
    public PositionDto getPosition(String str) {

        GeocodingApiRequest request = GeocodingApi.geocode(context, str);
        GeocodingResult[] result = request.awaitIgnoreError();

        if (result != null) {
            if (result.length > 0) {
                LatLng latLng = result[0].geometry.location;
                PositionDto position = new PositionDto();
                position.setLatitude(latLng.lat);
                position.setLongitude(latLng.lng);
                return position;
            }
        }
        return new PositionDto();
    }


    @Override
    public PositionDto geocodeFromAddress(AddressDto address) {

        String str = address.getProvince() + " " + address.getDistrict() + " " + address.getNeighborhood() + " " + address.getStreet() +
                " " + address.getBuildingInformation()+" "+ address.getFullAddress();

        return getPosition(str);
    }


    @Override
    public Address reverseGeocode(PositionDto position) {

        LatLng latLng = new LatLng();
        latLng.lat = position.getLatitude();
        latLng.lng = position.getLongitude();
        GeocodingApiRequest request = GeocodingApi.reverseGeocode(context, latLng);
        GeocodingResult[] results = new GeocodingResult[0];

        try {
            results = request.await();
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }

        if (results != null) {

            for (GeocodingResult result : results) {

                Address address = new Address();

                for (AddressComponent component : result.addressComponents) {

                    for (AddressComponentType addressComponentType : component.types) {

                        switch (addressComponentType) {

                            case ADMINISTRATIVE_AREA_LEVEL_1:
                                address.setProvince(component.longName);
                                break;

                            case ADMINISTRATIVE_AREA_LEVEL_2:
                                address.setDistrict(component.longName);
                                break;

                            case ADMINISTRATIVE_AREA_LEVEL_4:
                                address.setNeighborhood(component.longName);
                                break;

                            case ROUTE:
                                address.setStreet(component.longName);
                                if (address.getFullAddress()==null){
                                    address.setFullAddress("");
                                }
                                address.setFullAddress(address.getFullAddress()+" "+component.longName);
                                break;

                            case STREET_NUMBER:
                                address.setBuildingInformation(component.longName);
                                if (address.getFullAddress()==null){
                                    address.setFullAddress("");
                                }
                                address.setFullAddress(address.getFullAddress()+" No:"+component.longName);
                                break;

                        }
                    }
                }

                if (StringUtils.isNoneEmpty(address.getProvince()) && StringUtils.isNoneEmpty(address.getDistrict()) && StringUtils.isNoneEmpty(address.getNeighborhood())) {

                    Optional<Neighborhood> neighborhood =
                            neighborhoodRepository.findFirstByProvinceNameIgnoreCaseAndDistrictNameIgnoreCaseAndNeighborhoodNameContainsIgnoreCase(address.getProvince(), address.getDistrict(), address.getNeighborhood());


                    if (neighborhood.isPresent()) {
                        address.setProvince(neighborhood.get().getProvinceName());
                        address.setDistrict(neighborhood.get().getDistrictName());
                        address.setNeighborhood(neighborhood.get().getNeighborhoodName());
                        return address;
                    }

                }
            }

        } else {

            throw new AddressNotFoundException("Adres bulunamadı.");

        }
        throw new AddressNotFoundException("Adres bulunamadı.");
    }



}

