package com.example.projectname.utils;

import com.example.projectname.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthUtil {
    private static RestTemplate restTemplate ;
    public AuthUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public static UserDto validateToken(String token) {
        // make a http call from one service to other like product to user
        // using via rest template
        ResponseEntity<UserDto> responseEntity= restTemplate.getForObject(
                "http://localhost:8082/users/validate/token",
                ResponseEntity.class
        );
     if(responseEntity!=null){
         responseEntity.getBody();
     }
     return null;


    }
}
