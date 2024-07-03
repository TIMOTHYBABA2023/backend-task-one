package com.example.stageOneTask.services;

import com.example.stageOneTask.response.GenericResponse;
import com.example.stageOneTask.response.IpGeolocationResponse;
import com.example.stageOneTask.response.WeatherResponse;

import java.io.IOException;

public interface HelloService {
    GenericResponse getGreeting(String visitorName) throws IOException;
    WeatherResponse getWeatherForNigeria() throws IOException;

    IpGeolocationResponse getLocation();
}
