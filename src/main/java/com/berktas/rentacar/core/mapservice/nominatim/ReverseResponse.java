package com.berktas.rentacar.core.mapservice.nominatim;

import java.util.List;

public class ReverseResponse {

    public Integer place_id;
    public String licence;
    public String osm_type;
    public Long osm_id;
    public String lat;
    public String lon;
    public String display_name;
    public Address address;
    public List<String> boundingbox;

    /*
    continent
country, country_code
region, state, state_district, county
municipality, city, town, village
city_district, district, borough, suburb, subdivision
hamlet, croft, isolated_dwelling
neighbourhood, allotments, quarter
city_block, residental, farm, farmyard, industrial, commercial, retail
road
house_number, house_name
emergency, historic, military, natural, landuse, place, railway, man_made, aerialway, boundary, amenity, aeroway, club, craft, leisure, office, mountain_pass, shop, tourism, bridge, tunnel, waterway
     */

    public class Address{
        public String city;
        public String city_district;
        public String road;
        public String suburb;
        public String town;
        public String province;
        public String region;
        public String postcode;
        public String country;
        public String country_code;
        public String neighbourhood;
    }

}
