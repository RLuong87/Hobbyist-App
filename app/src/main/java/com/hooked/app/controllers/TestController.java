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
import com.hooked.app.service.AvatarService;
import com.hooked.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private AvatarService avatarService;

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

    @GetMapping("/getAllAvatar")
    public String home(Model model) {
        List<Avatar> list = avatarService.getAllAvatar();
        model.addAttribute("list", list);
        return "index";
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        Avatar avatar = new Avatar();
        String fileName = file.getOriginalFilename();
        avatar.setUrl(fileName);
        avatar.setContent(file.getBytes());
        avatar.setSize(file.getSize());
        avatarService.createAvatar(avatar);
        model.addAttribute("success", "File Uploaded Successfully!!!");
        return "index";
    }

    @GetMapping("/downloadfile")
    public void downloadFile(@Param("id") Long id, Model model, HttpServletResponse response) throws IOException {
        Optional<Avatar> temp = avatarService.findAvatarById(id);
        if (temp.isPresent()) {
            Avatar avatar = temp.get();
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename = " + avatar.getUrl();
            response.setHeader(headerKey, headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(avatar.getContent());
            outputStream.close();
        }
    }

    @GetMapping("/image")
    public void showImage(@Param("id") Long id, HttpServletResponse response, Optional<Avatar> avatar)
            throws ServletException, IOException {

        avatar = avatarService.findAvatarById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif, image/pdf");
        response.getOutputStream().write(avatar.get().getContent());
        response.getOutputStream().close();
    }
}
