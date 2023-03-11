package com.berktas.rentacar.core.util.petroloffice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
@Data
public class FuelResponse {
    @JsonProperty("DtPriceListDate")
    public Date dtPriceListDate;
    @JsonProperty("District")
    public String district;
    @JsonProperty("City")
    public String city;
    @JsonProperty("Benzin")
    public String benzin;
    @JsonProperty("GazYagi")
    public String gazYagi;
    @JsonProperty("BenzinUltimate")
    public String benzinUltimate;
    @JsonProperty("MotorinUltimate")
    public String motorinUltimate;
    @JsonProperty("Motorin")
    public String motorin;
    @JsonProperty("FuelOil")
    public String fuelOil;
    @JsonProperty("KaloriferYakiti")
    public String kaloriferYakiti;
    @JsonProperty("FuelOilYuksekKukurt")
    public String fuelOilYuksekKukurt;
    @JsonProperty("LpgPrice")
    public String lpgPrice;
}
