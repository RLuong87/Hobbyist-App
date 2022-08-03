package com.hooked.app.controllers;

import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.angler.Comment;
import com.hooked.app.models.auth.User;
import com.hooked.app.models.avatar.Avatar;
import com.hooked.app.models.content.Content;
import com.hooked.app.payloads.api.response.StormGlass;
import com.hooked.app.payloads.api.response.WeatherAPI;
import com.hooked.app.repositories.AnglerRepository;
import com.hooked.app.repositories.AvatarRepository;
import com.hooked.app.repositories.CommentRepository;
import com.hooked.app.repositories.ContentRepository;
import com.hooked.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

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
    private ContentRepository contentRepository;

    @Autowired
    private CommentRepository commentRepository;

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

    @GetMapping
    public ResponseEntity<List<Angler>> getAllAnglers() {
        return new ResponseEntity<>(anglerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Angler>> findAnglerByName(@PathVariable String name) {
        return new ResponseEntity<>(anglerRepository.findByName(name), HttpStatus.OK);
    }

//    @GetMapping("/query/{query}")
//    public ResponseEntity<List<Angler>> findAnglerByQuery(@PathVariable String query) {
//        User currentUser = userService.getCurrentUser();
//
//        if (currentUser == null) {
//            return null;
//        }
//
//        switch (query) {
//            case "":
//        }
//        return new ResponseEntity<>(anglerRepository.findByName(query), HttpStatus.OK);
//
//    }

    @PostMapping("/comment")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        Angler currentAngler = anglerRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        comment.setAngler(currentAngler);

        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.CREATED);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Angler>> findAnglerByLocation(@PathVariable String location) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        return new ResponseEntity<>(anglerRepository.findByLocation(location), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Angler>> findAnglerByStatus(@PathVariable String status) {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        return new ResponseEntity<>(anglerRepository.findByStatus(status), HttpStatus.OK);
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
}
