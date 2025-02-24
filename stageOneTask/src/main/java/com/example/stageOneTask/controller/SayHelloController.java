package com.example.stageOneTask.controller;

import com.example.stageOneTask.response.GenericResponse;
import com.example.stageOneTask.services.HelloService;
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

    @GetMapping("/api/hello")
    public ResponseEntity<GenericResponse> sayHello(@RequestParam(name = "visitorName") String visitorName) throws IOException {
        GenericResponse response = helloService.getGreeting(visitorName);
        return ResponseEntity.ok(response);
    }
}
