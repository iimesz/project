package com.khaled.demo.model.dto.respone;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PetInfoDto {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String type;
}
