package com.hooked.app.controllers;

import com.hooked.app.models.Customer;
import com.hooked.app.models.auth.User;
import com.hooked.app.payloads.api.response.StormGlass;
import com.hooked.app.payloads.api.response.WeatherAPI;
import com.hooked.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Value("${hooked.app.weatherApiKey}")
    private String weatherApiKey;

    @Value("${hooked.app.stormGlassKey}")
    private String stormKey;

    @GetMapping("/all")
    public String allAccess() {
        return "public content";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User content";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String modAccess() {
        return "Mod content";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "admin content";
    }

    @GetMapping("/weathertest")
    public String weatherTest() {
        return "WEATHER TEST!";
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/forecast")
    public ResponseEntity<?> getForecast() {

        String LOCATION = "Cranston";
        String uri = "https://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + weatherApiKey;

        WeatherAPI forecast = restTemplate.getForObject(uri, WeatherAPI.class);

        return ResponseEntity.ok(forecast);
    }

    @GetMapping("/forecast/{name}")
    public ResponseEntity<?> getWeatherForecast(@PathVariable String name) {

        String uri = "http://api.weatherapi.com/v1/current.json?key=" + weatherApiKey + "&q=" + name + "&aqi=no";

        WeatherAPI response = restTemplate.getForObject(uri, WeatherAPI.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/forecastv4")
    public ResponseEntity<StormGlass> getWeatherForecastV4(@RequestParam String lat,
                                                           @RequestParam String lng,
                                                           @RequestParam String params) {

        String uri = "https://api.stormglass.io/v2/weather/point";

        StormGlass response = restTemplate.getForObject(uri, StormGlass.class);

        return ResponseEntity.ok(response);
    }

//    @PostMapping
//    public ResponseEntity<User> createUser()

}
