package com.berktas.rentacar.core.mapservice.nominatim;


import com.berktas.rentacar.core.mapservice.osrm.LngLat;
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

   //burada lat lng kodu olmalı

    private interface Api {
        //public static final String url = "https://nominatim.openstreetmap.org/";
        String url = "https://nominatim./";

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






