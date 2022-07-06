package com.hooked.app.controllers;

import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.auth.User;
import com.hooked.app.models.avatar.Avatar;
import com.hooked.app.payloads.api.response.StormGlass;
import com.hooked.app.payloads.api.response.WeatherAPI;
import com.hooked.app.repositories.AnglerRepository;
import com.hooked.app.repositories.AvatarRepository;
import com.hooked.app.service.UserService;
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
    private AnglerRepository anglerRepository;

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private UserService userService;

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

//    @GetMapping("/{name}")
//    public ResponseEntity<List<Angler>> findByName(@PathVariable String name) {
//        User currentUser = userService.getCurrentUser();
//
//        if (currentUser == null) {
//            return null;
//        }
//        return new ResponseEntity<>(anglerRepository.findByName(name), HttpStatus.OK);
//    }

//    @GetMapping("/{location}")
//    public ResponseEntity<List<Angler>> findByLocation(@PathVariable String location) {
//        User currentUser = userService.getCurrentUser();
//
//        if (currentUser == null) {
//            return null;
//        }
//        return new ResponseEntity<>(anglerRepository.findByLocation(location), HttpStatus.OK);
//    }
//
//    @GetMapping("/{status}")
//    public ResponseEntity<List<Angler>> findByStatus(@PathVariable String status) {
//        User currentUser = userService.getCurrentUser();
//
//        if (currentUser == null) {
//            return null;
//        }
//        return new ResponseEntity<>(anglerRepository.findByStatus(status), HttpStatus.OK);
//    }

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

//    @PostMapping("/uploadImage")
//    public ResponseEntity<Avatar> createAvatar(@RequestBody Avatar avatar) {
//
//        User currentUser = userService.getCurrentUser();
//
//        if (currentUser == null) {
//            return null;
//        }
//        avatar.setAngler(currentUser);
//
//        Avatar avatar1 = avatarRepository.save(avatar);
//
//        return new ResponseEntity<>(avatarRepository.save(avatar), HttpStatus.CREATED);
//    }

}
