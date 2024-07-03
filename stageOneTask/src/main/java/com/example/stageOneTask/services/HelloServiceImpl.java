package com.example.stageOneTask.services;

import com.example.stageOneTask.response.GenericResponse;
import com.example.stageOneTask.response.IpGeolocationResponse;
import com.example.stageOneTask.response.WeatherResponse;
import com.google.gson.Gson;
import io.ipgeolocation.api.IPGeolocationAPI;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class HelloServiceImpl implements HelloService {
//    @Value("${ipstack.api.key}")
//    private String ipstackApiKey;
//
//    @Value("${geolocation.io/ipgeo}")
//    private String ipgeolocationUrl;
//
//    @Value("${e556e0f5974e4445a6c3c891e41ef165}")
//    private String ipgeolocationId;

    @Value("${openweathermap.url}")
    private String openWeatherMapUrl;

    @Value("${appid.openweather}")
    private String openWeatherAppid;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

//    private final RestTemplate restTemplate;
    IPGeolocationAPI api = new IPGeolocationAPI("e556e0f5974e4445a6c3c891e41ef165");




    @Override
    public GenericResponse getGreeting(String visitorName) throws IOException {
        WeatherResponse weather = getWeatherForNigeria();
        IpGeolocationResponse location = getLocation();
        int intTemperature = ((int) weather.getMain().getTemp());
        String temperature = String.valueOf(intTemperature);
        String city = location.getState_prov();
        log.info("Temperature:::, {}", temperature);
        log.info("City:::, {}", city);
        String greeting = String.format("Hello, %s!, the temperature is %s degrees Celsius in %s", visitorName, temperature, city);

        return GenericResponse.builder()
                .client_ip("23344454")
                .location(city)
                .greeting(greeting)
                .build();

//
    }


    @Override
    public WeatherResponse getWeatherForNigeria() throws IOException {
        Request request = new Request.Builder()
                .url(openWeatherMapUrl+"?q=Nigeria&appid="+openWeatherAppid+"&units=metric")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            log.info("The weather response, {}", response);
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            assert response.body() != null;
            String jsonResponse = response.body().string();
            return gson.fromJson(jsonResponse, WeatherResponse.class);
        }
    }
    @Override
    public IpGeolocationResponse getLocation() {
        Request request = new Request.Builder()
                .url("https://api.ipgeolocation.io/ipgeo?apiKey=e556e0f5974e4445a6c3c891e41ef165")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
//            log.info("The weather response, {}", response);
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            assert response.body() != null;
            String jsonResponse = response.body().string();
            return gson.fromJson(jsonResponse, IpGeolocationResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//    String city = "Unknown location";
//        int temperature = 0;
//
//        try {
//            // Get location data from ipstack
//            String ipstackUrl = String.format("http://api.ipstack.com/%s?access_key=%s", clientIp);
//            IpstackResponse ipstackResponse = restTemplate.getForObject(ipstackUrl, IpstackResponse.class);
//            if (ipstackResponse != null) {
//                city = ipstackResponse.getCity();
//            }
//        } catch (HttpClientErrorException e) {
//            // Handle error
//            e.printStackTrace();
//        }
//
//        try {
//            // Get weather data from OpenWeatherMap
//            String openWeatherMapUrl = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s", city, openWeatherMapApiKey);
//            OpenWeatherMapResponse weatherResponse = restTemplate.getForObject(openWeatherMapUrl, OpenWeatherMapResponse.class);
//            if (weatherResponse != null) {
//                temperature = (int) weatherResponse.getMain().getTemp();
//            }
//        } catch (HttpClientErrorException e) {
//            // Handle error
//            e.printStackTrace();
//        }
//
//        String greeting = String.format("Hello, %s!, the temperature is %d degrees Celsius in %s", visitorName, temperature, city);
//
//        return new GenericResponse(clientIp, city, greeting);
}
