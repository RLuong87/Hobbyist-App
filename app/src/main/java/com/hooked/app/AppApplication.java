package com.hooked.app;

import com.hooked.app.models.auth.ERole;
import com.hooked.app.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@SpringBootApplication
public class AppApplication {

    @Autowired
    public RoleRepository roleRepository;

    @Value("${spring.datasource.driver-class-name}")
    public String myDriver;

    @Value("${spring.datasource.url}")
    public String myUrl;

    @Value("${spring.datasource.username}")
    public String username;

    @Value("${spring.datasource.password}")
    public String password;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

//    @Bean
//    public WebMvcConfigurer configurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowCredentials(true).allowedOrigins("*").allowedMethods("*");
//            }
//        };
//    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        int roleCheck = roleRepository.isRoleEmpty();

        if (roleCheck < ERole.values().length) {
            int id = 1;
            for (ERole role : ERole.values()) {
                if (roleRepository.findByName(role).isEmpty()) {
                    try {
                        Connection conn = DriverManager.getConnection(myUrl, username, password);
                        Class.forName(myDriver);
                        String query = "Insert into role (id, name) values (?,?)";
                        PreparedStatement statement = conn.prepareStatement(query);

                        statement.setString(1, Integer.toString(id));
                        statement.setString(2, role.toString());

                        statement.executeUpdate();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                id++;
            }
        }
        return builder.build();
    }
}
