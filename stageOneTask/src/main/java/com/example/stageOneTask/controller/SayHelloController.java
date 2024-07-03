package com.example.stageOneTask.controller;

import com.example.stageOneTask.response.GenericResponse;
import com.example.stageOneTask.services.HelloService;
import com.example.stageOneTask.response.IpGeolocationResponse;
import com.example.stageOneTask.response.WeatherResponse;
import io.ipgeolocation.api.IPGeolocationAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SayHelloController {
 private final HelloService helloService;
 private final IPGeolocationAPI api = new IPGeolocationAPI("e556e0f5974e4445a6c3c891e41ef165");

//    public SayHelloController(HelloService helloService) {
//        this.helloService = helloService;
//    }

    @GetMapping("/api/hello")
    public ResponseEntity<GenericResponse> sayHello(@RequestParam(name = "visitor_name") String visitorName) throws IOException {
//        String clientIp = request.getRemoteAddr();
        GenericResponse response = helloService.getGreeting(visitorName);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/weather")
    public ResponseEntity<WeatherResponse> weather() throws IOException {
        return ResponseEntity.ok(helloService.getWeatherForNigeria());
    }

    @GetMapping("/api/location")
    public ResponseEntity<?> locator() throws IOException {
//    public ResponseEntity<IpGeolocationResponse> locator() throws IOException {
        log.info("The location of the cal:::::, {}",api.getGeolocation());
        return ResponseEntity.ok(api.getGeolocation().getCountryName());
//        return ResponseEntity.ok(helloService.getLocation());
    }
}
