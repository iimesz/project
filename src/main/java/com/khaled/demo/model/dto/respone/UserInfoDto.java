package com.khaled.demo.model.dto.respone;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInfoDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
