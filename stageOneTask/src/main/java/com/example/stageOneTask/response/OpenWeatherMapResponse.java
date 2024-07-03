package com.example.stageOneTask.response;

import com.sun.tools.javac.Main;
import lombok.Data;

@Data
public class OpenWeatherMapResponse {
    private Main main;
    @Data
    public static class Main{
        private float temp;
    }
}
