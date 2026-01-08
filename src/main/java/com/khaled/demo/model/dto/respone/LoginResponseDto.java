package com.khaled.demo.model.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {

    private String accessToken;
    private String refreshToken;
    private String email;
    private String firstName;
    private String lastName;
}

