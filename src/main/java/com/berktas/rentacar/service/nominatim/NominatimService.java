package com.berktas.rentacar.service.nominatim;


import com.berktas.rentacar.controller.map.MapSearchRequest;
import com.berktas.rentacar.model.entity.LatLng;
import com.berktas.rentacar.model.entity.embedded.Address;
import com.berktas.rentacar.service.osrm.LngLat;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.core.JsonParser.Feature.IGNORE_UNDEFINED;


@Service
public class NominatimService {

    Api mApi;

    public NominatimService() {
        Retrofit.Builder builder = new Retrofit.Builder();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(IGNORE_UNDEFINED, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        builder.baseUrl(Api.url);
        builder.addConverterFactory(JacksonConverterFactory.create(mapper));

        Retrofit retrofit = builder.build();
        mApi = retrofit.create(Api.class);
    }
    public Address reverse(LatLng latLng) {
        try {
            ReverseResponse response = mApi.reverse(latLng.getLat(), latLng.getLng()).execute().body();
            Address address = new Address();
            address.setProvince(response.address.province);
            address.setDistrict(response.address.town);

            String neighborhood = null;
            if (response.address.suburb != null) {
                neighborhood = response.address.suburb;
            }
            if (response.address.city_district != null) {
                neighborhood = response.address.city_district;
            }
            if (response.address.neighbourhood != null) {
                neighborhood = response.address.neighbourhood;
            }

            address.setNeighborhood(neighborhood);
            address.setStreet(response.address.road);
            address.setFullAddress(response.display_name);
            return address;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<LngLat> search(MapSearchRequest mapSearchRequest) throws IOException {

        String countryCodes = "tr";
        String polygonGeojson = "1";
        String q = mapSearchRequest.getNeighbourhood() + " " + mapSearchRequest.getCounty() + " " + mapSearchRequest.getCity() + " " + mapSearchRequest.getCountry();
        String format = "geojson";
        String limit = "1";
        MapSearchResponse mapSearchResponse = mApi.search(countryCodes, polygonGeojson, format, limit, q).execute().body();
        return mapSearchResponse.getFeatures().get(0).geometry.coordinates.get(0).stream().map((coordinate) -> new LngLat(coordinate.get(0), coordinate.get(1))).collect(Collectors.toList());
    }

    private interface Api {
        //public static final String url = "https://nominatim.openstreetmap.org/";
        String url = "https://nominatim.aselsis.com.tr/";

        @GET("search/")
        Call<MapSearchResponse> search(
                @Query("countrycodes") String countryCodes,
                @Query("polygon_geojson") String polygonGeojson,
                @Query("format") String format,
                @Query("limit") String limit,
                @Query("q") String q

        );

        @GET("reverse?format=json")
        Call<ReverseResponse> reverse(
                @Query("lat") Double lat,
                @Query("lon") Double lon);

    }

    }






