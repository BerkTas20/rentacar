package com.berktas.rentacar.core.util.opet;

import com.berktas.rentacar.model.entity.CurrentFuelPricesDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class OpetManager {

    Api mApi;

    private final RestTemplate restTemplate;

    public OpetManager(RestTemplateBuilder restTemplateBuilder) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));

        this.restTemplate = restTemplateBuilder.additionalMessageConverters(converter).build();
    }

    public static void main(String[] args) {
        OpetManager opetManager = new OpetManager(new RestTemplateBuilder());
        CurrentFuelPricesDto dto = opetManager.getPrices();
        System.out.println(dto);
    }

    public CurrentFuelPricesDto getPrices() {
        String provinceCode = "20";
        String includeAllProducts = "true";
        String districtName = "MERKEZEFENDİ";
        String gasolineProductCode = "A100";
        String dieselProductCode = "A121";

        try {
            List<OpetChildDto> opetChildDtoList = Arrays.asList(restTemplate.getForObject(Api.URL + "fuelprices/prices?ProvinceCode={provinceCode}&IncludeAllProducts={includeAllProducts}", OpetChildDto[].class, provinceCode, includeAllProducts));

            Optional<OpetChildDto> opetDtoOptional = opetChildDtoList.stream().filter(opetChildDto -> opetChildDto.getDistrictName().equals(districtName)).findFirst();

            if (opetDtoOptional.isPresent()) {
                OpetChildDto opetChildDto = opetDtoOptional.get();
                List<OpetPricesDto> opetPricesDtoList = opetChildDto.getPrices();
                Optional<OpetPricesDto> gasoline = opetPricesDtoList.stream().filter(opetPricesDto -> gasolineProductCode.equals(opetPricesDto.getProductCode())).findFirst();
                Optional<OpetPricesDto> diesel = opetPricesDtoList.stream().filter(opetPricesDto -> dieselProductCode.equals(opetPricesDto.getProductCode())).findFirst();
                return CurrentFuelPricesDto.builder().priceGasoline(gasoline.get().getAmount().toString()).priceDiesel(diesel.get().getAmount().toString()).priceGas("0").build();
            } else {
                return CurrentFuelPricesDto.builder().priceGasoline("0").priceDiesel("0").priceGas("0").build();
            }

        } catch (RestClientException e) {
            return CurrentFuelPricesDto.builder().priceGasoline("0").priceDiesel("0").priceGas("0").build();
        }
    }

    public interface Api {
        String URL = "https://api.opet.com.tr/api/";
    }

}

