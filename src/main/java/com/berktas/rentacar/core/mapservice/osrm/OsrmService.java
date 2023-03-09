package com.berktas.rentacar.core.mapservice.osrm;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.core.JsonParser.Feature.IGNORE_UNDEFINED;

@Service
public class OsrmService {
    Api mApi;

    public OsrmService() {
        Retrofit.Builder builder = new Retrofit.Builder();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(IGNORE_UNDEFINED, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        builder.baseUrl(Api.url);
        builder.addConverterFactory(JacksonConverterFactory.create(mapper));

        Retrofit retrofit = builder.build();
        mApi = retrofit.create(Api.class);
    }

    public List<LngLat>  optimizedRoute(List<LngLat> coordinates) throws IOException {
        String queryString = coordinates.stream().map(LngLat::getLngLat).collect(Collectors.joining());
        queryString= queryString.substring(1);
        Response<OptimizedRouteResponse> optimizedRouteResponse = mApi.optimizedRoute(queryString).execute();

        return optimizedRouteResponse.body().trips.get(0).geometry.coordinates.stream().map(coordinate -> new LngLat(coordinate.get(0), coordinate.get(1))).collect(Collectors.toList());
    }

    private interface Api {

        public static final String url = "https://osrm./";

        @GET("trip/v1/driving/{coordinates}?source=first&destination=last&overview=full&geometries=geojson&steps=true")
        Call<OptimizedRouteResponse> optimizedRoute(@Path("coordinates") String coordinates
        );

        @GET("match/v1/driving/{lng1},{lat1};{lng2},{lat2}?steps=false&geometries=geojson&overview=full&annotations=false")
        Call<MatchResponse> match(
                @Path("lng1") Double lng1,
                @Path("lat1") Double lat1,
                @Path("lng2") Double lng2,
                @Path("lat2") Double lat3);

    }
}