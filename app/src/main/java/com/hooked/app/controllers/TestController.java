package com.hooked.app.controllers;

import com.hooked.app.payloads.api.response.WeatherAPI;
import com.hooked.app.payloads.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${hooked.app.weatherApiKey}")
    private String apiKey;

    @GetMapping("/weathertest")
    public String weatherTest() {
        return "WEATHER TEST!";
    }

    @GetMapping("/forecast")
    public ResponseEntity<?> getForecast() {

        String LOCATION = "Cranston";
        String uri = "https://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + apiKey;

        WeatherAPI forecast = restTemplate.getForObject(uri, WeatherAPI.class);

        return ResponseEntity.ok(forecast);
    }

    @GetMapping("/forecast/{name}")
    public ResponseEntity<?> getWeatherForecast(@PathVariable String name) {

        String uri = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + name + "&aqi=no";

        WeatherAPI response = restTemplate.getForObject(uri, WeatherAPI.class);

        return ResponseEntity.ok(response.getCurrent());
    }


    @GetMapping("/forecastv4/{name}")
    public Object getWeatherForecastV4(@PathVariable String name) {

        String uri = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + name + "&aqi=no";

        WeatherResponse response = restTemplate.getForObject(uri, WeatherResponse.class);

        return ResponseEntity.ok(response.getWeatherApi());
    }
}
