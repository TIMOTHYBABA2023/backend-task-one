package com.example.stageOneTask.response;

import lombok.Data;

@Data
public class IpGeolocationResponse {
    private String ip;
    private String continent_code;
    private String continent_name;
    private String country_code2;
    private String country_code3;
    private String country_name;
    private String country_name_official;
    private String country_capital;
    private String state_prov;
    private String state_code;
    private String district;
    private String city;
    private String zipcode;
    private String latitude;
    private String longitude;
    private boolean is_eu;
    private String calling_code;
    private String country_tld;
    private String languages;
    private String country_flag;
    private String geoname_id;
    private String isp;
    private String connection_type;
    private String organization;
    private String country_emoji;
    private Currency currency;
    private TimeZone time_zone;

    @Data
    public static class Currency {
        private String code;
        private String name;
        private String symbol;
    }

    @Data
    public static class TimeZone {
        private String name;
        private int offset;
        private int offset_with_dst;
        private String current_time;
        private double current_time_unix;
        private boolean is_dst;
        private int dst_savings;
        private boolean dst_exists;
        private String dst_start;
        private String dst_end;
    }

}
