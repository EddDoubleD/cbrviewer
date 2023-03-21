package com.eddoubled.cbrviewer.model.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtResponse {
    String token;
    String type;
    String id;
    String username;
    String email;

    List<String> roles;


    public JwtResponse(String token, String id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.type = "Bearer";
        this.roles = roles;
    }

}
